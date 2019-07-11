package com.yuu.ymall.web.api.service.impl;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.TradeStatus;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuu.ymall.commons.consts.Consts;
import com.yuu.ymall.commons.dto.BaseResult;
import com.yuu.ymall.commons.redis.RedisCacheManager;
import com.yuu.ymall.commons.utils.IDUtil;
import com.yuu.ymall.commons.utils.MapperUtil;
import com.yuu.ymall.domain.*;
import com.yuu.ymall.web.api.common.config.AlipayConfig;
import com.yuu.ymall.web.api.common.utils.IPInfoUtil;
import com.yuu.ymall.web.api.dto.*;
import com.yuu.ymall.web.api.mapper.*;
import com.yuu.ymall.web.api.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author by Yuu
 * @classname OrderServiceImpl
 * @date 2019/7/6 2:14
 */
@Service
@Transactional(readOnly = false)
public class OrderServiceImpl implements OrderService {

    @Value("${ADD_ORDER}")
    private String ADD_ORDER;

    @Value("${CART_PRE}")
    private String CART_PRE;

    @Value("${ORDER_PAY}")
    private String ORDER_PAY;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private TbMemberMapper tbMemberMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Value("${PRODUCT_ITEM}")
    private String PRODUCT_ITEM;

    @Transactional
    @Override
    public BaseResult addOrder(OrderInfo orderInfo, HttpServletRequest request) {

        // 用户 id
        Long userId = orderInfo.getUserId();

        // 地址收货人
        String username = orderInfo.getUserName();

        // 订单商品
        List<CartProduct> goods = orderInfo.getGoodsList();

        if (userId == null || StringUtils.isBlank(username) || goods == null || goods.size() == 0) {
            return BaseResult.fail("请求信息异常");
        }

        // 请求 ip 地址
        String ip = IPInfoUtil.getIpAddr(request);
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip="127.0.0.1";
        }

        // Redis key，防止恶意请求
        String redisKey = ADD_ORDER + ":" + ip;
        String temp = (String) redisCacheManager.get(redisKey);
        if (StringUtils.isNotBlank(temp)) {
            return BaseResult.fail("您提交的太频繁了，请您稍后再试");
        }

        TbMember tbMember = tbMemberMapper.selectByPrimaryKey(userId);
        if (tbMember == null) {
            return BaseResult.fail("获取下单用户失败");
        }

        // 生成订单
        TbOrder tbOrder = new TbOrder();
        tbOrder.setId(String.valueOf(IDUtil.getRandomId()));
        tbOrder.setUserId(userId);
        if (StringUtils.isBlank(tbMember.getUsername())) {
            tbOrder.setBuyerNick(tbMember.getPhone());
        } else {
            tbOrder.setBuyerNick(tbMember.getUsername());
        }
        tbOrder.setPayment(orderInfo.getOrderTotal());
        tbOrder.setCreated(new Date());
        tbOrder.setUpdated(new Date());
        // 0: 未付款 1: 已付款 2: 未发货 3: 已发货 4: 交易成功 5: 交易关闭
        tbOrder.setStatus(0);

        // 添加订单
        tbOrderMapper.insert(tbOrder);

        // 生成订单信息
        List<CartProduct> goodsList = orderInfo.getGoodsList();
        for (CartProduct cartProduct : goodsList) {
            TbOrderItem tbOrderItem = new TbOrderItem();
            Long orderItemId = IDUtil.getRandomId();
            tbOrderItem.setId(orderItemId.toString());
            tbOrderItem.setItemId(cartProduct.getProductId().toString());
            tbOrderItem.setOrderId(tbOrder.getId());
            tbOrderItem.setNum(cartProduct.getProductNum());
            tbOrderItem.setTitle(cartProduct.getProductName());
            tbOrderItem.setPrice(cartProduct.getSalePrice());
            tbOrderItem.setTotalFee(cartProduct.getSalePrice().multiply(BigDecimal.valueOf(cartProduct.getProductNum())));
            tbOrderItem.setPicPath(cartProduct.getProductImg());
            tbOrderItemMapper.insert(tbOrderItem);

            // 删除会员购物车中的该商品
            // Redis key
            String cartProductKey = CART_PRE + ":" + orderInfo.getUserId();
            // Hash Key
            String hashKey = cartProduct.getProductId().toString();
            // 删除记录
            redisCacheManager.deleteHash(cartProductKey, hashKey);
        }

        // 生成物流信息
        TbOrderShipping tbOrderShipping = new TbOrderShipping();
        tbOrderShipping.setOrderId(tbOrder.getId());
        tbOrderShipping.setReceiverName(orderInfo.getUserName());
        tbOrderShipping.setReceiverAddress(orderInfo.getStreetName());
        tbOrderShipping.setReceiverPhone(orderInfo.getTel());
        tbOrderShipping.setCreated(new Date());
        tbOrderShipping.setUpdated(new Date());
        tbOrderShippingMapper.insert(tbOrderShipping);

        // 修改商品库存信息
        for (CartProduct good : goods) {
            Long productId = good.getProductId();
            // 商品库存
            tbItemMapper.reduceItemNum(productId, good.getProductNum());
            // 删除 Redis 商品缓存信息
            String redisItemKey = PRODUCT_ITEM + ":" + productId;
            redisCacheManager.del(redisItemKey);
        }

        // 设置 Redis ip 缓存
        redisCacheManager.set(redisKey, "ADD_ORDER");
        redisCacheManager.expire(redisKey, 60);
        return BaseResult.success((Object)tbOrder.getId());
    }

    @Override
    public BaseResult getOrderDet(String orderId) {

        Order order = new Order();

        // 获取订单信息
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
        if (tbOrder == null) {
            return BaseResult.fail("通过id获取订单详情失败");
        }

        // 订单 id
        order.setOrderId(tbOrder.getId());

        // 会员 id
        order.setUserId(tbOrder.getUserId().toString());

        // 订单状态
        order.setOrderStatus(tbOrder.getStatus());

        // 未支付，最晚支付时间
        if (order.getOrderStatus() == 0) {
            Date createDate = tbOrder.getCreated();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(createDate);
            calendar.add(Calendar.HOUR, 2);
            String countTime = calendar.getTime().getTime() + "";
            order.setCountTime(countTime);
        }

        // 订单创建时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDate = simpleDateFormat.format(tbOrder.getCreated());
        order.setCreateDate(createDate);

        // 订单支付时间
        if (tbOrder.getPaymentTime() != null) {
            String payDate = simpleDateFormat.format(tbOrder.getPaymentTime());
            order.setPayDate(payDate);
        }

        // 订单发货时间
        if (tbOrder.getConsignTime() != null) {
            String consignDate = simpleDateFormat.format(tbOrder.getConsignTime());
            order.setConsignDate(consignDate);
        }

        // 订单关闭时间
        if (tbOrder.getCloseTime() != null) {
            String closeDate = simpleDateFormat.format(tbOrder.getCloseTime());
            order.setCloseDate(closeDate);
        }

        // 订单完成时间
        if (tbOrder.getEndTime() != null && tbOrder.getStatus() == 4) {
            String endDate = simpleDateFormat.format(tbOrder.getEndTime());
            order.setFinishDate(endDate);
        }

        // 地址
        TbOrderShipping tbOrderShipping = tbOrderShippingMapper.selectByPrimaryKey(tbOrder.getId());
        TbAddress tbAddress = new TbAddress();
        tbAddress.setUserName(tbOrderShipping.getReceiverName());
        tbAddress.setStreetName(tbOrderShipping.getReceiverAddress());
        tbAddress.setTel(tbOrderShipping.getReceiverPhone());
        order.setTbAddress(tbAddress);

        // 订单总计
        order.setOrderTotal(tbOrder.getPayment());

        // 订单商品列表
        List<TbOrderItem> tbOrderItemList = tbOrderItemMapper.selectByOrderId(tbOrder.getId());
        List<CartProduct> cartProductList = new ArrayList<>();
        for (TbOrderItem tbOrderItem : tbOrderItemList) {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setProductId(Long.parseLong(tbOrderItem.getItemId()));
            cartProduct.setProductName(tbOrderItem.getTitle());
            cartProduct.setSalePrice(tbOrderItem.getPrice());
            cartProduct.setProductNum(tbOrderItem.getNum());
            cartProduct.setProductImg(tbOrderItem.getPicPath());
            cartProductList.add(cartProduct);
        }

        // 订单物流信息，如果已发货，或者交易完成查询物流信息
        if (tbOrder.getStatus() == 3 || tbOrder.getStatus() == 4) {
            order.setShippingName(tbOrder.getShippingName());
            order.setShippingCode(tbOrder.getShippingCode());
        }

        order.setGoodsList(cartProductList);
        return BaseResult.success(order);
    }

    @Transactional
    @Override
    public AlipayTradePrecreateResponse payment(OrderPay orderPay) {

        // 一定要在创建AlipayTradeService之前设置参数
        Configs.init("zfbinfo.properties");
        AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = orderPay.getOrderId().toString();

        // (必填) 订单标题，粗略描述用户的支付目的。如“喜士多（浦东店）消费”
        String subject = "YMall";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = orderPay.getOrderTotal().toString();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "YMall";

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        AlipayTradePrecreateRequestBuilder builder =new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject)
                .setTotalAmount(totalAmount)
                .setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount)
                .setSellerId(sellerId)
                .setBody(body)
                .setOperatorId(operatorId)
                .setStoreId(storeId)
                .setTimeoutExpress(timeoutExpress)
                // 支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setNotifyUrl(AlipayConfig.notify_url);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        AlipayTradePrecreateResponse response = result.getResponse();

        // 将订单信息保存在 redis 中
        // redisKey
        String redisKey = ORDER_PAY + ":" + orderPay.getOrderId();
        orderPay.setOrderStatus(Consts.ORDER_STATUS_NOPAY.toString());
        try {
            String orderPayJson = MapperUtil.obj2json(orderPay);
            if (orderPayJson != null) {
                redisCacheManager.set(redisKey, orderPayJson);
                redisCacheManager.expire(redisKey, 60 * 60 * 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Transactional
    @Override
    public int updateOrderStatus(String orderId, Integer orderStatus) {

        // 修改缓存状态
        String redisKey = ORDER_PAY + ":" + orderId;

        // 更新缓存
        String orderPayJson = (String) redisCacheManager.get(redisKey);

        if (orderPayJson != null) {
            try {
                OrderPay orderPay = MapperUtil.json2pojo(orderPayJson, OrderPay.class);
                orderPay.setOrderStatus((Consts.ORDER_STATUS_PAY).toString());
                String newPayJson = MapperUtil.obj2json(orderPay);
                if (newPayJson != null) {
                    redisCacheManager.set(redisKey, newPayJson);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tbOrderMapper.updateOrderStatus(orderId, orderStatus);
    }

    @Override
    public TradeStatus getOrderStatus(String orderId) {

        // 一定要在创建AlipayTradeService之前设置参数
        Configs.init("zfbinfo.properties");

        AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
        String outTradeNo = orderId;
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                .setOutTradeNo(outTradeNo);
        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        TradeStatus tradeStatus = result.getTradeStatus();
        return tradeStatus;

    }

    @Override
    public BaseResult getOrderList(String userId, int page, int size) {

        // 设置 PageHelper
        PageHelper.startPage(page, size);

        PageOrder pageOrder = new PageOrder();
        List<Order> orders = new ArrayList<>();

        List<TbOrder> tbOrderList = tbOrderMapper.selectByUserId(userId);
        for (TbOrder tbOrder : tbOrderList) {
            Order order = new Order();
            // 订单id
            order.setOrderId(tbOrder.getId());
            // 会员id
            order.setUserId(tbOrder.getUserId().toString());
            // 订单状态
            order.setOrderStatus(tbOrder.getStatus());
            // 创建时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created = simpleDateFormat.format(tbOrder.getCreated());
            order.setCreateDate(created);
            // 订单支付时间
            if (tbOrder.getPaymentTime() != null) {
                String payDate = simpleDateFormat.format(tbOrder.getPaymentTime());
                order.setPayDate(payDate);
            }
            // 订单发货时间
            if (tbOrder.getConsignTime() != null) {
                String consignDate = simpleDateFormat.format(tbOrder.getConsignTime());
                order.setConsignDate(consignDate);
            }
            // 订单关闭时间
            if (tbOrder.getCloseTime() != null) {
                String closeDate = simpleDateFormat.format(tbOrder.getCloseTime());
                order.setCloseDate(closeDate);
            }
            // 订单完成时间
            if (tbOrder.getEndTime() != null && tbOrder.getStatus() == 4) {
                String endDate = simpleDateFormat.format(tbOrder.getEndTime());
                order.setFinishDate(endDate);
            }
            // 地址
            TbOrderShipping tbOrderShipping = tbOrderShippingMapper.selectByPrimaryKey(tbOrder.getId());
            TbAddress tbAddress = new TbAddress();
            tbAddress.setUserName(tbOrderShipping.getReceiverName());
            tbAddress.setStreetName(tbOrderShipping.getReceiverAddress());
            tbAddress.setTel(tbOrderShipping.getReceiverPhone());
            order.setTbAddress(tbAddress);
            // 订单总计
            order.setOrderTotal(tbOrder.getPayment());
            // 订单商品列表
            List<TbOrderItem> tbOrderItemList = tbOrderItemMapper.selectByOrderId(tbOrder.getId());
            List<CartProduct> cartProductList = new ArrayList<>();
            for (TbOrderItem tbOrderItem : tbOrderItemList) {
                CartProduct cartProduct = new CartProduct();
                cartProduct.setProductId(Long.parseLong(tbOrderItem.getItemId()));
                cartProduct.setProductName(tbOrderItem.getTitle());
                cartProduct.setSalePrice(tbOrderItem.getPrice());
                cartProduct.setProductNum(tbOrderItem.getNum());
                cartProduct.setProductImg(tbOrderItem.getPicPath());
                cartProductList.add(cartProduct);
            }
            order.setGoodsList(cartProductList);
            orders.add(order);
        }

        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        pageOrder.setTotal(tbOrderMapper.getMemberOrderCount(userId));
        pageOrder.setData(orders);

        return BaseResult.success(pageOrder);
    }

    @Transactional
    @Override
    public BaseResult confirmReceipt(Order order) {
        // 会员 id
        String userId = order.getUserId();

        // 订单 id
        String orderId = order.getOrderId();

        // 请求信息为空
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(orderId)) {
            return BaseResult.fail("操作异常");
        }

        // 查询订单操作是否正常
        TbOrder tbOrder = tbOrderMapper.selectByUserIdAndOrderId(userId, orderId);
        if (tbOrder == null) {
            return BaseResult.fail("操作异常");
        }

        // 确认收货，修改数据库 status 字段
        tbOrderMapper.confirmReceipt(order.getOrderId());
        return BaseResult.success("确认收货成功");
    }

    @Override
    public BaseResult deleteService(Order order) {
        // 会员 id
        String userId = order.getUserId();

        // 订单 id
        String orderId = order.getOrderId();

        // 请求信息为空
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(orderId)) {
            return BaseResult.fail("操作异常");
        }

        // 查询订单操作是否正常
        TbOrder tbOrder = tbOrderMapper.selectByUserIdAndOrderId(userId, orderId);
        if (tbOrder == null) {
            return BaseResult.fail("操作异常");
        }

        // 删除订单
        tbOrderMapper.deleteByPrimaryKey(orderId);

        // 删除订单商品
        tbOrderItemMapper.deleteByOrderId(orderId);

        // 删除订单物流信息
        tbOrderShippingMapper.deleteByPrimaryKey(orderId);
        return BaseResult.success("删除订单成功");
    }

    @Transactional
    @Override
    public int paySuccess(String orderId) {
        // 修改支付时间和状态
        return tbOrderMapper.updatePayTimeAndStatus(orderId);
    }

    @Override
    public BaseResult cancelService(Order order) {
        // 修改关闭时间和状态
        tbOrderMapper.cancelOrder(order.getOrderId());

        // 修改商品库存
        // 查询出该订单的所有商品集合
        List<TbOrderItem> tbOrderItemList = tbOrderItemMapper.selectByOrderId(order.getOrderId());
        for (TbOrderItem tbOrderItem : tbOrderItemList) {
            String productId = tbOrderItem.getItemId();
            int productNum = tbOrderItem.getNum();
            // 增加商品库存
            tbItemMapper.addItemNum(productId, productNum);
            // 更新 Redis 缓存
            String redisKey = PRODUCT_ITEM + ":" + productId;
            redisCacheManager.del(redisKey);
        }
        return BaseResult.success("取消订单成功");
    }

}
