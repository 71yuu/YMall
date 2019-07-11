<template>
  <div class="good-item">
    <div style="">
      <div class="good-img">
        <a @click="openProduct(msg.productId)">
          <img v-lazy="msg.picUrl" :alt="msg.productName" :key="msg.picUrl">
        </a>
      </div>
      <h6 class="good-title" v-html="msg.productName">{{msg.productName}}</h6>
      <h3 class="sub-title ellipsis">{{msg.subTitle}}</h3>
      <div class="good-price pr">
        <div class="ds pa">
          <a @click="openProduct(msg.productId)">
            <y-button text="查看详情" style="margin: 0 5px"></y-button>
          </a>
          <y-button text="加入购物车"
                    style="margin: 0 5px"
                    @btnClick="addCart(msg.productId, msg.salePrice, msg.productName, msg.picUrl, msg.limit)"
                    classStyle="main-btn">
          </y-button>
        </div>
        <p><span style="font-size:14px">￥</span>{{Number(msg.salePrice).toFixed(2)}}</p>
      </div>
    </div>
  </div>
</template>
<script>
  import YButton from './YButton'
  import { mapState, mapMutations } from 'vuex'
  import { addCartProduct } from '../api/cart'
  import { getStore } from '../utils/storage'

  export default {
    props: {
      msg: {
        salePrice: 0,
        limit: 10
      }
    },
    data () {
      return {}
    },
    methods: {
      open (t, m) {
        this.$notify.info({
          title: t,
          message: m
        })
      },
      ...mapMutations(['ADD_CART', 'ADD_ANIMATION', 'SHOW_CART']),
      // 查看详情
      openProduct (id) {
        // 新打开一个窗口
        window.open('//' + window.location.host + '/goodsDetails?productId=' + id)
      },
      // 加入购物车
      addCart (id, price, name, img, limit) {
        let cart = this.cartList
        let flag = true
        cart.forEach(cart => {
          if (cart.productId === id && cart.productNum >= limit) {
            flag = false
          }
        })
        if (!flag) {
          let msg = '该商品限购' + limit + '件'
          this.open('限购', msg)
          return
        }

        // 动画是否在运动，先不添加
        if (!this.showMoveImg) {
          // 登录了直接存在会员下
          if (this.login) {
            addCartProduct({userId: getStore('userId'), productId: id, productNum: 1}).then(res => {
              // 并不重新请求数据
              this.ADD_CART({productId: id, salePrice: price, productName: name, productImg: img})
            })
          } else {
            // 未登录 vuex 存
            this.ADD_CART({productId: id, salePrice: price, productName: name, productImg: img, limitNum: limit})
          }

          // 加入购物车动画
          var dom = event.target
          // 获取点击的坐标
          let elLeft = dom.getBoundingClientRect().left + (dom.offsetWidth / 2)
          let elTop = dom.getBoundingClientRect().top + (dom.offsetHeight / 2)
          // 需要触发
          this.ADD_ANIMATION({moveShow: true, elLeft: elLeft, elTop: elTop, img: img})
          if (!this.showCart) {
            this.SHOW_CART({showCart: true})
          }
        }
      }
    },
    computed: {
      ...mapState(['login', 'cartList'])
    },
    components: {YButton}
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../assets/style/mixin";
  @import "../assets/style/theme";

  .good-item {
    background: #fff;
    width: 25%;
    transition: all .5s;
    height: 430px;
    &:hover {
      transform: translateY(-3px);
      box-shadow: 1px 1px 20px #999;
      .good-price p {
        display: none;
      }
      .ds {
        display: flex;
      }
    }
    .ds {
      @extend %block-center;
      width: 100%;
      display: none;
    }

    .good-img {
      img {
        margin: 50px auto 10px;
        @include wh(206px);
        display: block;
      }
    }
    .good-price {
      margin: 15px 0;
      height: 30px;
      text-align: center;
      line-height: 30px;
      color: #d44d44;
      font-family: Arial;
      font-size: 18px;
      font-weight: 700;
    }
    .good-title {
      line-height: 1.2;
      font-size: 16px;
      color: #424242;
      margin: 0 auto;
      padding: 0 14px;
      text-align: center;
      overflow: hidden;
    }
    h3 {
      text-align: center;
      line-height: 1.2;
      font-size: 12px;
      color: #d0d0d0;
      padding: 10px;
    }

  }
</style>
