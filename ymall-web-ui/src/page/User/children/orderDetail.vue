<template>
  <div>
    <y-shelf v-bind:title="orderTitle">
      <div slot="content">
        <div v-loading="loading" element-loading-text="加载中..." style="min-height: 10vw;" v-if="orderList.length">
          <div class="orderStatus" v-if="orderStatus !== 0 && orderStatus !== 5">
            <el-steps :space="200" :active="orderStatus+1">
              <el-step title="下单" v-bind:description="createTime"></el-step>
              <el-step title="付款" v-bind:description="payTime"></el-step>
              <el-step title="待发货" v-bind:description="noconsignTxt"></el-step>
              <el-step title="已发货" v-bind:description="consignTime"></el-step>
              <el-step title="交易成功" v-bind:description="finishTime"></el-step>
            </el-steps>
          </div>
          <div class="orderStatus-close" v-if="orderStatus === 5">
            <el-steps :space="780" :active="2">
              <el-step title="下单" v-bind:description="createTime"></el-step>
              <el-step title="交易关闭" v-bind:description="closeTime"></el-step>
            </el-steps>
          </div>
          <div class="status-now" v-if="orderStatus === 0" style="margin-top: 30px;">
            <ul>
              <li class="status-title"><h3>订单状态：待付款</h3></li>
              <li class="button">
                <el-button @click="orderPayment(orderId)" type="primary" size="small">现在付款</el-button>
                <el-button @click="_cancelOrder(orderId)" size="small">取消订单</el-button>
              </li>
            </ul>
            <p class="realtime">
              <span>您的付款时间还有 </span>
              <span class="red"><countDown v-if="countTime" v-bind:endTime="countTime" endText="已结束"></countDown></span>
              <span>，超时后订单将自动取消。</span>
            </p>
          </div>
          <div class="status-now" v-if="orderStatus === 2">
            <ul>
              <li class="status-title"><h3>订单状态：待发货</h3></li>
            </ul>
            <p class="realtime">
              <span>我们将在 72 小时内为您发货</span>
            </p>
          </div>
          <div class="status-now" v-if="orderStatus === 5">
            <ul>
              <li class="status-title"><h3>订单状态：已关闭</h3></li>
            </ul>
            <p class="realtime">
              <span>您的订单已关闭。</span>
            </p>
          </div>
          <div class="status-now" v-if="orderStatus === 4">
            <ul>
              <li class="status-title"><h3>订单状态：已完成</h3></li>
            </ul>
            <p class="realtime">
              <span>您的订单已交易成功，感谢您的惠顾！</span>
            </p>
          </div>

          <div class="gray-sub-title cart-title">
            <div class="first">
              <div>
                <span class="order-id">商品名称</span>
              </div>
              <div class="f-bc">
                <span class="price">单价</span>
                <span class="num">数量</span>
                <span class="operation">小计</span>
              </div>
            </div>
          </div>
          <!-- 订单商品 -->
          <div class="goods-table">
            <div class="cart-items" v-for="(item,i) in orderList" :key="i">
              <a @click="goodsDetails(item.productId)" class="img-box"><img :src="item.productImg" alt=""></a>
              <div class="name-cell ellipsis">
                <a @click="goodsDetails(item.productId)" title="" target="_blank">{{item.productName}}</a>
              </div>
              <div class="n-b">
                <div class="price">¥ {{Number(item.salePrice).toFixed(2)}}</div>
                <div class="goods-num">{{item.productNum}}</div>
                <div class="subtotal"> ¥ {{Number(item.salePrice * item.productNum).toFixed(2)}}</div>
              </div>
            </div>
          </div>
          <!-- 合计 -->
          <div class="order-discount-line">
            <p style="font-size: 14px;font-weight: bolder;"> <span style="padding-right:47px">商品总计：</span>
              <span style="font-size: 16px;font-weight: 500;line-height: 32px;">¥ {{Number(orderTotal).toFixed(2)}}</span>
            </p>
            <p class="price-total"><span>应付金额：</span><span class="price-red">¥ {{Number(orderTotal).toFixed(2)}}</span></p>
          </div>
          <!-- 收货信息 -->
          <div class="gray-sub-title cart-title">
            <div class="first">
              <div>
                <span class="order-id">收货信息</span>
              </div>
            </div>
          </div>
          <div style="height: 155px;padding: 20px 30px;">
            <p class="address">姓名：{{ userName }}</p>
            <p class="address">联系电话：{{ tel }}</p>
            <p class="address">详细地址：{{ streetName }}</p>
          </div>

          <!-- 物流信息 -->
          <div class="gray-sub-title cart-title" v-if="orderStatus === 3 || orderStatus === 4">
            <div class="first">
              <div>
                <span class="order-id">物流信息</span>
              </div>
            </div>
          </div>
          <div style="height: 155px;padding: 20px 30px;">
            <p class="address">物流名称：{{ shippingName }}</p>
            <p class="address">物流单号：{{ shippingCode }}</p>
          </div>
        </div>

        <div v-loading="loading" element-loading-text="加载中..." v-else>
          <div style="padding: 100px 0;text-align: center">
            获取该订单信息失败
          </div>
        </div>
      </div>
    </y-shelf>
  </div>
</template>
<script>
  import YShelf from '/components/shelf'
  import { getStore } from '/utils/storage'
  import { getOrderDet, cancelOrder } from '/api/order'
  import countDown from '/components/countDown'

  export default {
    data () {
      return {
        userId: '',
        orderId: '',
        orderTitle: '',
        loading: true,
        orderList: [0],
        orderStatus: 0,
        orderTotal: 0,
        userName: '',
        tel: '',
        streetName: '',
        createTime: '',
        closeTime: '',
        payTime: '',
        finishTime: '',
        countTime: '',
        noconsignTxt: '待发货',
        shippingName: '',
        shippingCode: ''
      }
    },
    methods: {
      // 成功消息提示
      messageSuccess (m) {
        this.$message({
          message: m,
          type: 'success'
        })
      },
      // 失败消息提示
      messageFail (m) {
        this.$message.error({
          message: m
        })
      },
      // 确认删除
      confirmCancel (orderId) {
        this.$confirm('您确认取消订单号为' + orderId + '的订单吗？此操作不可恢复', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          cancelOrder({userId: this.userId, orderId: orderId}).then(res => {
            if (res.status === 200) {
              this._getOrderDet()
            } else {
              this.messageFail(res.message)
            }
          })
        })
      },
      // 获取订单详情
      _getOrderDet () {
        let params = {
          params: {
            orderId: this.orderId
          }
        }
        getOrderDet(params).then(res => {
          this.orderStatus = res.result.orderStatus
          this.orderList = res.result.goodsList
          this.orderTotal = res.result.orderTotal
          this.userName = res.result.tbAddress.userName
          this.tel = res.result.tbAddress.tel
          this.streetName = res.result.tbAddress.streetName
          this.createTime = res.result.createDate
          this.consignTime = res.result.consignDate
          this.closeTime = res.result.closeDate
          this.payTime = res.result.payDate
          this.finishTime = res.result.finishDate
          this.countTime = res.result.countTime
          this.shippingName = res.result.shippingName
          this.shippingCode = res.result.shippingCode
          if (this.consignTime !== null) {
            this.noconsignTxt = '订单已发货'
          } else {
            this.noconsignTxt = '订单未发货'
          }
          this.loading = false
        })
      },
      // 去付款
      orderPayment (orderId) {
        window.open(window.location.origin + '/order/payment?orderId=' + orderId)
      },
      // 取消订单
      _cancelOrder (orderId) {
        this.confirmCancel(orderId)
      },
      // 商品详情
      goodsDetails (productId) {
        window.open(window.location.origin + '/goodsDetails?productId=' + productId)
      }
    },
    created () {
      this.userId = getStore('userId')
      this.orderId = this.$route.query.orderId
      this.orderTitle = '订单号：' + this.orderId
      this._getOrderDet()
    },
    components: {
      YShelf,
      countDown
    }
  }
</script>
<style lang="scss" scoped>
  @import "../../../assets/style/mixin";

  .orderStatus {
    display: flex;
    align-items: center;
    flex-direction: row;
    margin: 50px -150px 20px 60px;
  }

  .orderStatus-close {
    display: flex;
    align-items: center;
    flex-direction: row;
    margin: 50px -800px 20px 60px;
  }

  .status-now {
    background: #F6F6F6;
    border: 1px solid #dadada;
    border-radius: 5px;
    padding: 22px 30px 20px;
    margin: 0 30px 30px 30px;
    line-height: 38px;
  }

  .status-title {
    font-size: 18px;
    color: #666;
  }

  .button {
    float: right;
    margin: -37px 0 20px 0;
  }

  .realtime {
    border-top: 1px solid #dcdcdc;
    margin-top: 20px;
    padding-top: 26px;
  }

  .red {
    color: #d44d44;
  }

  .address {
    line-height: 38px;
    word-wrap: break-word;
    word-spacing: normal;
    word-break: break-all;
    color: #626262;
  }

  .img-box {
    border: 1px solid #EBEBEB;
    margin-left: -80px;
  }

  img {
    display: block;
    @include wh(80px);
  }

  .goods-table {
    .n-b {
      display: flex;
      align-items: center;
      justify-content: center;
      > div {
        color: #626262;
        font-weight: 700;
        width: 165px;
        text-align: center;
      }
    }
    .cart-items {
      display: flex;
      align-items: center;
      justify-content: space-between;
      height: 110px;
      padding: 15px 0 15px 111px;
      border-bottom: 1px solid #EFEFEF;
      a {
        color: #333;
      }
    }
    .price {
      padding-left: 107px;
    }
    .goods-num {
      padding-left: 60px;
    }
  }

  .order-discount-line {
    padding: 22px 30px 20px;
    line-height: 24px;
    text-align: right;
    font-size: 12px;
    &:first-child {
      line-height: 32px;
      text-align: right;
      font-size: 14px;
      font-weight: bolder;
    }
  }

  .gray-sub-title {
    height: 38px;
    padding: 0 24px;
    background: #EEE;
    border-top: 1px solid #DBDBDB;
    border-bottom: 1px solid #DBDBDB;
    line-height: 38px;
    font-size: 12px;
    color: #666;
    display: flex;
    span {
      display: inline-block;
      height: 100%;
    }
    .first {
      display: flex;
      justify-content: space-between;
      flex: 1;
      .f-bc {
        > span {
          width: 130px;
          text-align: center;
        }
      }
    }
    .last {
      width: 230px;
      text-align: center;
      display: flex;
      border-left: 1px solid #ccc;
      span {
        flex: 1;
      }
    }
  }

  .bt {
    border-top: 1px solid #EFEFEF;
  }

  .date {
    padding-left: 0px;
  }

  .order-id {
    margin-left: 10px;
  }

  .cart {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 24px;
    &:hover {
      .del-order {
        display: block;
      }
    }
    .del-order {
      display: none;
    }
    .cart-l {
      display: flex;
      align-items: center;
      flex: 1;
      padding: 15px 0;
      justify-content: space-between;
      position: relative;
      &:before {
        position: absolute;
        content: ' ';
        right: -1px;
        top: 0;
        width: 1px;
        background-color: #EFEFEF;
        height: 100%;
      }
      .ellipsis {
        margin-left: 20px;
        width: 220px;
      }
      .img-box {
        border: 1px solid #EBEBEB;
      }
      img {
        display: block;
        @include wh(80px);
      }
      .cart-l-r {
        display: flex;
        > div {
          text-align: center;
          width: 112px;
        }
      }
      .car-l-l {
        display: flex;
        align-items: center;
      }
    }
    .cart-r {
      width: 230px;
      display: flex;
      span {
        text-align: center;
        flex: 1;
      }
    }
  }

  .prod-operation {
    height: 110px;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 254px;
    div {
      width: 100%;
      text-align: center;
    }
    div:last-child {
      padding-right: 24px;
    }
  }

  .price-total {
    height: 54px;
    line-height: 54px;
    font-size: 18px;
  }

  .price-red {
    font-weight: 700;
    color: #d44d44;
  }
</style>
