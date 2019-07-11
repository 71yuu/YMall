<template>
  <div class="w">
    <y-shelf title="支付成功" style="text-align: center">
      <div slot="content" class="content">
        <p>恭喜您支付成功<br></p>
        <img src="/static/images/paysuccess.png" style="margin-top: 25px; margin-bottom: 5px;">
        <div style="margin-top: 20px;">
            <y-button text="查看订单" style="width: 150px;height: 50px;line-height: 48px;font-size: 20px"
                      classStyle="main-btn" @btnClick="orderDetail"></y-button>
          <router-link to="/home">
            <y-button text="继续选购" style="width: 150px;height: 50px;line-height: 48px;font-size: 20px"
                      classStyle="main-btn"></y-button>

          </router-link>
        </div>
      </div>
    </y-shelf>
  </div>
</template>
<script>
  import YShelf from '/components/shelf'
  import YButton from '/components/YButton'
  export default {
    data () {
      return {
        orderId: ''
      }
    },
    components: {
      YShelf,
      YButton
    },
    methods: {
      orderDetail () {
        this.$router.push({
          path: '/user/orderDetail',
          query: {
            orderId: this.orderId
          }
        })
      }
    },
    created () {
      this.orderId = this.$route.query.orderId
    }
  }
</script>
<style lang="scss" scoped>
  .content {
    padding: 150px 0;
    font-size: 30px;
  }

  .icon-success {
    font-size: 33px;
    color: #01b401;
  }

  span.price {
    color: #d44d44;
    font-weight: 700;
    line-height: 20px;
    text-align: right;
  }
</style>
