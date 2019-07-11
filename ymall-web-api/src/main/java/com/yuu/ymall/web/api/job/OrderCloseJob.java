package com.yuu.ymall.web.api.job;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.yuu.ymall.commons.consts.Consts;
import com.yuu.ymall.web.api.common.config.AlipayConfig;
import com.yuu.ymall.web.api.mapper.TbOrderMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.List;

/**
 * 订单超时关闭任务
 *
 * @author by Yuu
 * @classname OrderCloseJob
 * @date 2019/7/7 12:25
 */
public class OrderCloseJob implements Job {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        // 查询出所有已超时的订单
        List<String> tbOrderIds = tbOrderMapper.selectOrderOutTime();

        // 更新订单状态
        for (String tbOrderId : tbOrderIds) {
            // 更新数据库订单状态
            tbOrderMapper.updateOrderStatus(tbOrderId, Consts.ORDER_STATUS_CLOSE);
            // 更新关闭时间和状态
            tbOrderMapper.updateCloseTimeAndStatus(tbOrderId);
            // 通知支付宝订单关闭
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,"json",AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
            AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
            request.setBizContent("{" +
                    "\"out_trade_no\":\""+ tbOrderId +"\"," +
                    "\"trade_no\":\"\"" +
                    "  }");
            AlipayTradeCancelResponse response = null;
            try {
                response = alipayClient.execute(request);
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
        }

    }
}

