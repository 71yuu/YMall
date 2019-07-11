<template>
  <div class="w" style="padding-bottom: 50px;">
    <y-shelf title="支付订单">
      <div slot="content">
        <div class="box-inner order-info">
          <h3>提交订单成功，请选择支付方式</h3>
          <p class="payment-detail">请在 <span style="color: #d44d44; font-weight: 700;">2 小时内 </span>完成支付，超时订单将自动取消。</p>
          <p class="payment-detail">我们将在您完成支付后的 72 小时内发货</p>
        </div>
        <!-- 支付方式 -->
        <div class="pay-type">
          <div class="p-title">支付方式</div>
          <div class="pay-item">
            <div class="active"><img src="/static/images/alipay@2x.png" alt=""></div>
          </div>
          <div class="nav-user-wrapper pa active">
            <div class="nav-user-list">
              <div class="code-viewer">
                <canvas id="msg"></canvas>
              </div>
              <p class="tip">请使用支付宝扫一扫</p>
              <p class="tip">扫描二维码支付</p>
            </div>
          </div>
        </div>
        <!-- 订单金额 -->
        <div>
          <div class="box-inner">
            <div>
              <span>
                订单金额：
              </span>
              <em><span>¥</span>{{orderTotal.toFixed(2)}}</em>
              <span>
                实际应付金额：
              </span>
              <em><span>¥</span>{{orderTotal.toFixed(2)}}</em>
            </div>
          </div>
        </div>
      </div>
    </y-shelf>

    <!-- 地址信息 -->
    <div class="p-msg w">
      <div class="confirm-detail">
        <div class="info-title">收货信息</div>
        <p class="info-detail">姓名：{{address.userName}}</p>
        <p class="info-detail">联系电话：{{address.tel}}</p>
        <p class="info-detail">详细地址：{{address.streetName}}</p></div>
    </div>

    <!-- 商品信息 -->
    <div class="confirm-table-title">
      <span class="name">商品信息</span>
      <div>
        <span class="price">单价</span>
        <span class="num">数量</span>
        <span class="subtotal">小计</span>
      </div>
    </div>
    <div class="confirm-goods-table">
      <div class="cart-items" v-for="(item,i) in cartList" :key="i">
        <div class="name">
          <div class="name-cell ellipsis">
            <a @click="goodsDetails(item.productId)" title="" target="_blank">{{item.productName}}</a>
          </div>
        </div>
        <div class="n-b">
          <div class="price">¥ {{item.salePrice.toFixed(2)}}</div>
          <div class="goods-num">{{item.productNum}}</div>
          <div class="subtotal">
            <div class="subtotal-cell"> ¥ {{(item.salePrice * item.productNum).toFixed(2)}}<br></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 合计 -->
    <div class="order-discount-line">
      <p style="font-size: 14px;font-weight: bolder;"> <span style="padding-right:47px">商品总计：</span>
        <span style="font-size: 16px;font-weight: 500;line-height: 32px;">¥ {{orderTotal.toFixed(2)}}</span>
      </p>
    </div>
  </div>
</template>
<script>
  import YShelf from '/components/shelf'
  import { getStore } from '/utils/storage'
  import { getOrderDet, payment, getOrderStatus } from '/api/order'
  import QRCode from 'qrcode'

  export default {
    data () {
      return {
        userId: '',
        orderId: '',
        cartList: [],
        address: {
          userName: '',
          tel: '',
          streetName: ''
        },
        orderTotal: 0,
        qrCode: '',
        myInterval: ''
      }
    },
    watch: {
      // 通过监听获取数据
      qrCode (val) {
        // 获取页面的canvas
        let msg = document.getElementById('msg')
        // 将获取到的数据（val）画到msg（canvas）上
        QRCode.toCanvas(msg, val, function (error) {
          console.log(error)
        })
      }
    },
    methods: {
      // 获取订单详情
      _getOrderDet (orderId) {
        let params = {
          params: {
            orderId: orderId
          }
        }
        getOrderDet(params).then(res => {
          if (res.status === 200) {
            if (res.result.orderStatus === 0) {
              console.log('订单未支付')
              this.cartList = res.result.goodsList
              this.address.userName = res.result.tbAddress.userName
              this.address.tel = res.result.tbAddress.tel
              this.address.streetName = res.result.tbAddress.streetName
              this.orderTotal = res.result.orderTotal
              console.log(this.orderId)
              this.getPay()
            } else {
              // todo
              this.$router.push({
                path: '/user/orderDetail',
                query: {
                  orderId: orderId
                }
              })
            }
          } else {
            this.$router.push({
              path: '/'
            })
          }
        })
      },
      // 商品详情
      goodsDetails (id) {
        window.open(window.location.origin + '/goodsDetails?productId=' + id)
      },
      // 获取付款二维码
      getPay () {
        payment({
          orderId: this.orderId,
          orderTotal: this.orderTotal
        }).then(res => {
          this.qrCode = res.qrCode
          console.log(this.qrCode)
          this._getOrderStatus()
        })
      },
      // 获取订单支付状态
      _getOrderStatus () {
        let orderId = this.orderId
        let params = {
          params: {
            orderId: orderId
          }
        }
        this.myInterval = setInterval(() => {
          setTimeout(() => {
            getOrderStatus(params).then(res => {
              console.log(res)
              if (res === 'SUCCESS') {
                clearInterval(this.myInterval)
                this.$router.push({
                  path: '/order/paysuccess',
                  query: {
                    orderId: orderId
                  }
                })
              }
            })
          }, 1)
        }, 3000)
      }
    },
    created () {
      this.userId = getStore('userId')
      this.orderId = this.$route.query.orderId
      let orderId = this.orderId
      if (this.orderId) {
        this._getOrderDet(orderId)
      } else {
        this.$router.push({
          path: '/'
        })
      }
    },
    destroyed () {
      clearInterval(this.myInterval)
    },
    components: {
      YShelf,
      QRCode
    }
  }
</script>
<style lang="scss" scoped rel="stylesheet/scss">

  .nav-user-wrapper {
    top: 18px;
    visibility: visible;
    opacity: 1;
    -webkit-transition: opacity .15s ease-out;
    transition: opacity .15s ease-out;
  }

  .nav-user-wrapper {
    width: 182px;
    transform: translate(-50%);
    left: 50%;
  }

  .nav-user-wrapper.active {
    top: 380px;
    left: 235px;
    visibility: visible;
    opacity: 1;
    -webkit-transition: opacity .15s ease-out;
    transition: opacity .15s ease-out;
  }

  .nav-user-wrapper {
    right: 0;
    width: 360px;
    .nav-user-list {
      &:before {
        right: 34px;
      }
    }
  }

  .nav-user-wrapper {
    position: absolute;
    z-index: 30;
    padding-top: 18px;
    opacity: 0;
    visibility: hidden;
    top: -3000px;
  }

  .nav-user-list {
    width: 182px;
    height: 250px;
    text-align: center;
    &:before {
      left: 50%;
    }
  }

  .nav-user-list .tip {
    display: block;
    padding-top: 8px;
    color: #a1a1a1;
    font-size: 12px;
  }

  .nav-user-list .code-viewer img{
    display: block;
    width: 133px;
    height: 133px;
    margin: 0 auto;
    border: 0;
  }

  .w {
    padding-top: 39px;
  }

  .order-info {
    padding: 60px 0 55px;
    color: #333;
    background: #fff !important;
    h3 {
      padding-bottom: 14px;
      line-height: 36px;
      text-align: center;
      font-size: 36px;
      color: #212121;
    }
    .payment-detail {
      text-align: center;
      line-height: 24px;
      font-size: 14px;
      color: #999;
    }
  }

  /*支付类型*/
  .pay-type {
    margin: 0 auto;
    width: 90%;
    padding-bottom: 30px;
    height: 420px;
    .p-title {
      font-size: 18px;
      line-height: 40px;
      padding: 0 10px;
      position: relative;
      &:before {
        content: ' ';
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        border-bottom: 1px solid #ccc;
      }
    }

  }

  .pay-type {
    .pay-item {
      display: flex;
      align-items: center;
      div {
        margin-top: 20px;
        width: 175px;
        height: 50px;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px solid #E5E5E5;
        cursor: pointer;
        border-radius: 6px;
        margin-right: 10px;
        background: #FAFAFA;
        &.active {
          border-color: #6A8FE5;
          background: #fff;
        }
        img {
          display: block;
          height: 34px;
          margin: 8px auto;
        }
      }
    }
  }

  .box-inner {
    line-height: 60px;
    background: #f9f9f9;
    border-top: 1px solid #e5e5e5;
    box-sizing: border-box;
    > div {
      display: flex;
      justify-content: flex-end;
      align-items: center;
      padding: 0 20px;
    }
    em {
      margin-left: 5px;
      font-size: 24px;
      color: #d44d44;
      font-weight: 700;
      margin-right: 20px;
      span {
        margin-right: 4px;
        font-size: 16px;

      }
    }
  }

  .confirm-detail {
    padding: 0 30px 25px;
    border-top: 1px solid #d5d5d5;
    .info-title {
      height: 14px;
      margin: 30px 0 17px;
      line-height: 14px;
      font-weight: bolder;
      color: #333;
    }
    .info-detail {
      line-height: 24px;
      color: #666;
    }
  }

  .confirm-table-title {
    padding: 3px 0 0 33px;
    border-top: 1px solid #D5D5D5;
    line-height: 54px;
    font-weight: bolder;
    color: #000;
    display: flex;
    justify-content: space-between;
    span {
      display: inline-block;
      width: 175px;
      text-align: left;
    }
    .price {
      padding-left: 80px;
    }
    .num {
      padding-left: 75px;
    }
    .subtotal {
      padding-left: 72px;
    }
  }

  .confirm-goods-table {
    border-top: 1px solid #D5D5D5;
    .cart-items {
      height: 80px;
      display: flex;
      align-items: center;
      justify-content: space-between;
      a {
        color: #333;
      }
    }
    .n-b {
      display: flex;
      align-items: center;
      justify-content: center;
      > div {
        color: #626262;
        font-weight: 700;
        width: 175px;
        text-align: center;
      }
    }
  }

  .order-discount-line {
    padding: 22px 30px 53px;
    border-top: 1px solid #D5D5D5;
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

  .name {
    width: 40%;
  }

  .name-cell {
    padding-left: 33px;
  }

  .input {
    width:30%;
    margin:0 0 1vw 38px;
  }

  .pay-info {
    margin-top: -2vw;
    text-align: center;
  }

  .money-select {
    width: 31%;
    padding-left: 10px;
    margin-bottom: 1vw;
  }
</style>
