<template>
  <div class="checkout">
    <y-header>
      <div slot="nav"></div>
    </y-header>
    <div class="w" style="padding-top: 40px;">

      <!-- 收货地址 -->
      <y-shelf title="收货信息">
        <div slot="content">
          <ul class="address-item-list clearfix">
            <li v-for="(item,i) in addList"
                :key="i"
                class="address pr"
                :class="{checked:addressId === item.id}"
                @click="chooseAddress(item.id, item.userName, item.tel, item.streetName)">
               <span v-if="addressId === item.id" class="pa">
                 <svg viewBox="0 0 1473 1024" width="17.34375" height="12">
                   <path
                     d="M1388.020 57.589c-15.543-15.787-37.146-25.569-61.033-25.569s-45.491 9.782-61.023 25.558l-716.054 723.618-370.578-374.571c-15.551-15.769-37.151-25.537-61.033-25.537s-45.482 9.768-61.024 25.527c-15.661 15.865-25.327 37.661-25.327 61.715 0 24.053 9.667 45.849 25.327 61.715l431.659 436.343c15.523 15.814 37.124 25.615 61.014 25.615s45.491-9.802 61.001-25.602l777.069-785.403c15.624-15.868 25.271-37.66 25.271-61.705s-9.647-45.837-25.282-61.717M1388.020 57.589z"
                     fill="#6A8FE5" p-id="1025">
                   </path>
                 </svg>
               </span>
              <p>{{item.userName}}   {{item.isDefault ? '(默认地址)' : ''}}</p>
              <p>{{item.tel}}</p>
              <p class="street-name ellipsis">{{item.state}} {{item.city}} {{item.district}}</p>
              <p class="street-name ellipsis">{{item.streetName}}</p>
              <div class="operation-section">
                <span class="update-btn" style="font-size:12px" @click="update(item)">修改</span>
                <span class="delete-btn" style="font-size:12px" :data-id="item.id" @click="_addressDel(item.id)">删除</span>
              </div>
            </li>

            <li class="add-address-item" @click="update()">
              <img src="../../../static/svg/jia.svg" alt="">
              <p>使用新地址</p>
            </li>
          </ul>
        </div>
      </y-shelf>

      <!-- 购物清单 -->
      <y-shelf title="购物清单">
        <div slot="content">
          <div class="box-inner ui-cart">
            <div>
              <!--标题-->
              <div class="cart-table-title">
                <span class="name">商品信息</span>
                <span class="subtotal">小计</span>
                <span class="num">数量</span>
                <span class="price">单价</span>
              </div>
              <!--列表-->
              <div class="cart-table" v-for="(item,i) in cartList" :key="i" v-if="item.checked === '1'">
                <div class="cart-group divide pr" :data-productid="item.productId">
                  <div class="cart-top-items">
                    <div class="cart-items clearfix">
                      <!--图片-->
                      <div class="items-thumb fl">
                        <img :alt="item.productName"
                             :src="item.productImg">
                        <a @click="goodsDetails(item.productId)" :title="item.productName" target="_blank"></a>
                      </div>
                      <!--信息-->
                      <div class="name hide-row fl">
                        <div class="name-table">
                          <a @click="goodsDetails(item.productId)" :title="item.productName" target="_blank"
                             v-text="item.productName"></a>
                          <!-- <ul class="attribute">
                            <li>白色</li>
                          </ul> -->
                        </div>
                      </div>
                      <!--商品数量-->
                      <div>
                        <!--总价格-->
                        <div class="subtotal" style="font-size: 14px">¥ {{item.salePrice * item.productNum}}</div>
                        <!--数量-->
                        <div class="item-cols-num">
                          <span v-text="item.productNum"></span>
                        </div>
                        <!--价格-->
                        <div class="price">¥ {{item.salePrice}}</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- 合计 -->
            <div class="cart-bottom-bg fix-bottom">
              <div class="fix-bottom-inner">
                <div class="shipping">
                  <div class="shipping-box" style="padding: 0 40px;">
                    <div class="shipping-total shipping-price"><h4
                      class="highlight">应付总额：<em>￥{{checkPrice}}</em>
                    </h4>
                    </div>
                  </div>
                  <y-button class="big-main-btn"
                            :classStyle="!submit||!goods.length>0?'disabled-btn':'main-btn'"
                            style="margin: 0;width: 130px;height: 50px;line-height: 50px;font-size: 16px"
                            :text="submitOrder"
                            @btnClick="_submitOrder">
                  </y-button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </y-shelf>

      <!-- 新增或修改地址 -->
      <div class="edit-address" v-show="editAddressShow">
        <y-shelf :title="addressTxt">
            <span slot="right">
              <span class="close" @click="editAddressShow=false">
                <svg t="1501234940517" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="3014" xmlns:xlink="http://www.w3.org/1999/xlink"
                     width="20" height="20"><path
                  d="M941.576 184.248l-101.824-101.824L512 410.176 184.248 82.424 82.424 184.248 410.168 512l-327.744 327.752 101.824 101.824L512 613.824l327.752 327.752 101.824-101.824L613.832 512z"
                  fill="#cdcdcd" p-id="3015"></path></svg>
              </span>
            </span>
          <div slot="content" class="content">
            <el-form :model="addressVer" :rules="addressVerRules" ref="addressVer" class="register-form">
              <el-form-item prop="userName">
                <el-input v-model="addressVer.userName" size="large" placeholder="收货人姓名" auto-complete="off" style="width:296px;"></el-input>
              </el-form-item>
              <el-form-item prop="phone">
                <el-input v-model="addressVer.phone" size="large" placeholder="手机号" auto-complete="off" style="width:296px;"></el-input>
              </el-form-item>
              <el-form-item>
                <el-cascader
                  placeholder="请选择地址"
                  size="large"
                  :options="options"
                  v-model="selectedOptions"
                  @change="handleChange" style="width:296px;">
                </el-cascader>
              </el-form-item>
              <el-form-item prop="streetName">
                <el-input v-model="addressVer.streetName" size="large" placeholder="街道地址" auto-complete="off" style="width:296px;"></el-input>
              </el-form-item>
              <el-checkbox class="auto-login" v-model="addressVer.isDefault">设为默认</el-checkbox>
            </el-form>

            <div class="bootom-btn pa">
              <el-button @click="editAddressShow=false" style="width: 140px; height: 40px;">
                取消
              </el-button>
              <el-button @click="save({userId:userId,id:addressVer.addressId,userName:addressVer.userName,tel:addressVer.phone,state: addressVer.state, city: addressVer.city, district: addressVer.district, streetName:addressVer.streetName,isDefault:addressVer.isDefault})" type="primary" :disabled="btnHighlight ? false : true"  style="width: 140px; height: 40px;">
                确定
              </el-button>
            </div>
          </div>
        </y-shelf>
      </div>

      <!--<y-popup :open="popupOpen" @close='popupOpen=false' :title="popupTitle">
        <div slot="content" class="md" :data-id="msg.addressId">
          <div>
            <input type="text" placeholder="收货人姓名" v-model="msg.userName">
          </div>
          <div>
            <input type="number" placeholder="手机号码" v-model="msg.tel">
          </div>
          <div>
            <input type="text" placeholder="收货地址" v-model="msg.streetName">
          </div>
          <div>
            <el-checkbox class="auto-login" v-model="msg.isDefault">设为默认</el-checkbox>
          </div>
          <y-button text='保存'
                    class="btn"
                    :classStyle="btnHighlight?'main-btn':'disabled-btn'"
                    @btnClick="save({userId:userId,id:msg.addressId,userName:msg.userName,tel:msg.tel,streetName:msg.streetName,isDefault:msg.isDefault})">
          </y-button>
        </div>
      </y-popup>-->
    </div>
    <y-footer></y-footer>
  </div>
</template>
<script>
  import YHeader from '/common/header'
  import YFooter from '/common/footer'
  import YShelf from '/components/shelf'
  import YButton from '/components/YButton'
  import { addressList, addressUpdate, addressAdd, addressDel } from '/api/member'
  import { getCartList } from '/api/cart'
  import { getStore } from '/utils/storage'
  import YPopup from '/components/popup'
  import { submitOrder } from '/api/order'
  import { productDet } from '/api/goods'
  import { regionData, CodeToText, TextToCode } from 'element-china-area-data'

  export default {
    data () {
      // 验证收货人
      let vUserName = (rule, value, callback) => {
        if (!value) {
          this.verUserName = false
          return callback(new Error('请输入收货人姓名'))
        }
        this.verUserName = true
        return callback()
      }
      // 验收收货手机号
      let vPhone = (rule, value, callback) => {
        const phoneReg = /^1[3|4|5|7|8][0-9]\d{8}$/
        if (!value) {
          this.verPhone = false
          return callback(new Error('请输入手机号'))
        }
        if (!phoneReg.test(value)) {
          this.verPhone = false
          return callback(new Error('请输入正确的手机号'))
        }
        this.verPhone = true
        return callback()
      }
      // 验证街道
      let vStreetName = (rule, value, callback) => {
        if (!value) {
          this.verStreetName = false
          return callback(new Error('请输入街道地址'))
        }
        this.verStreetName = true
        return callback()
      }
      return {
        addList: [],
        addressId: '0',
        userName: '',
        tel: '',
        streetName: '',
        userId: '',
        addressTxt: '新增收货地址',
        editAddressShow: false,
        addressVer: {
          addressId: '',
          userName: '',
          phone: '',
          state: '',
          city: '',
          district: '',
          streetName: '',
          isDefault: false
        },
        addressVerRules: {
          userName: [
            {validator: vUserName, trigger: 'change, blur'}
          ],
          phone: [
            {validator: vPhone, trigger: 'change, blur'}
          ],
          streetName: [
            {validator: vStreetName, trigger: 'change, blur'}
          ]
        },
        verUserName: false,
        verPhone: false,
        verStreetName: false,
        options: regionData,
        selectedOptions: [],
        // 详细地址
        detailedAddress: '',
        cartList: [],
        submitOrder: '提交订单',
        submit: false,
        orderTotal: 0,
        goods: []
      }
    },
    computed: {
      btnHighlight () {
        return this.verUserName && this.verPhone && this.addressVer.state && this.addressVer.city && this.addressVer.district && this.verStreetName
      },
      // 选中的总价格
      checkPrice () {
        let totalPrice = 0
        this.cartList && this.cartList.forEach(item => {
          if (item.checked === '1') {
            totalPrice += (item.productNum * item.salePrice)
          }
        })
        this.orderTotal = totalPrice
        return totalPrice
      }
    },
    methods: {
      // 获取地址列表
      _addressList () {
        let params = {
          params: {
            userId: this.userId
          }
        }
        addressList(params).then(res => {
          if (res.status === 200) {
            let data = res.result
            if (data.length) {
              this.addList = data
              this.addressId = data[0].id
              this.userName = data[0].userName
              this.tel = data[0].tel
              this.streetName = data[0].streetName
              this.submit = true
            } else {
              this.addList = []
              this.submit = false
            }
          }
        })
      },
      // 选择地址
      chooseAddress (addressId, userName, tel, streetName) {
        this.addressId = addressId
        this.userName = userName
        this.tel = tel
        this.streetName = streetName
      },
      // 修改地址
      update (item) {
        this.editAddressShow = true
        if (item) {
          this.addressTxt = '修改收货地址'
          let state = TextToCode[item.state].code
          let city = TextToCode[item.state][item.city].code
          let district = TextToCode[item.state][item.city][item.district].code
          this.selectedOptions.push(state, city, district)
          this.addressVer.userName = item.userName
          this.addressVer.phone = item.tel
          this.addressVer.state = item.state
          this.addressVer.city = item.city
          this.addressVer.district = item.district
          this.addressVer.streetName = item.streetName
          this.addressVer.isDefault = item.isDefault
          this.addressVer.addressId = item.id
        } else {
          this.addressTxt = '新增收货地址'
          this.addressVer.userName = ''
          this.addressVer.phone = ''
          this.addressVer.state = ''
          this.addressVer.city = ''
          this.addressVer.district = ''
          this.selectedOptions = []
          this.addressVer.streetName = ''
          this.addressVer.isDefault = false
          this.addressVer.addressId = ''
        }
      },
      // 修改城市选择
      handleChange (value) {
        console.log(this.selectedOptions)
        let addArr = value
        let state = CodeToText[(addArr[0])]
        let city = CodeToText[(addArr[1])]
        let district = CodeToText[(addArr[2])]
        this.addressVer.state = state
        this.addressVer.city = city
        this.addressVer.district = district
      },
      // 保存
      save (p) {
        this.editAddressShow = false
        console.log(p.id)
        if (p.id) {
          this._addressUpdate(p)
        } else {
          delete p.addressId
          this._addressAdd(p)
        }
      },
      // 修改地址
      _addressUpdate (params) {
        addressUpdate(params).then(res => {
          this._addressList()
        })
      },
      // 新增地址
      _addressAdd (params) {
        addressAdd(params).then(res => {
          this._addressList()
        })
      },
      // 删除地址
      _addressDel (params) {
        addressDel({id: params}).then(res => {
          this._addressList()
        })
      },
      // 获取购物车列表
      _getCartList () {
        let userId = this.userId
        let params = {
          params: {
            userId: userId
          }
        }
        getCartList(params).then(res => {
          if (res.status === 200) {
            this.cartList = res.result
            for (let i = 0; i < this.cartList.length; i++) {
              if (this.cartList[i].checked === '1') {
                this.goods.push(this.cartList[i])
              }
            }
          }
        })
      },
      // 跳转到商品详情页
      goodsDetails (id) {
        window.open(window.location.origin + '/goodsDetails?productId=' + id)
      },
      // 错误消息提示
      message (m) {
        this.$message.error({
          message: m
        })
      },
      // 获取商品详情
      _productDet (productId) {
        productDet({params: {productId}}).then(res => {
          let item = res.result
          item.checked = '1'
          item.productImg = item.productImageBig
          item.productNum = this.num
          item.productPrice = item.salePrice
          this.cartList.push(item)
          this.goods.push(item)
        })
      },
      // 提交订单后跳转到付款页面
      _submitOrder () {
        this.submitOrder = '提交订单中...'
        this.submit = true
        if (this.addressId === '0') {
          this.message('请选择收货地址')
          this.submitOrder = '提交订单'
          this.submit = false
          return
        }
        if (this.cartList.length === 0) {
          this.message('非法操作')
          this.submitOrder = '提交订单'
          this.submit = false
          return
        }
        let params = {
          userId: this.userId,
          addressId: this.addressId,
          tel: this.tel,
          userName: this.userName,
          streetName: this.streetName,
          orderTotal: this.orderTotal,
          goodsList: this.goods
        }
        submitOrder(params).then(res => {
          if (res.status === 200) {
            this.payment(res.result)
          } else {
            this.message(res.message)
            this.submitOrder = '提交订单'
            this.submit = false
          }
        })
      },
      // 路由到付款页面
      payment (orderId) {
        // 需要拿到地址 id
        this.$router.push({
          path: '/order/payment',
          query: {
            'orderId': orderId
          }
        })
      }
    },
    created () {
      this.userId = getStore('userId')
      let query = this.$route.query
      if (query.productId && query.num) {
        this.productId = query.productId
        this.num = query.num
        this._productDet(this.productId)
      } else {
        this._getCartList()
      }
      this._addressList()
    },
    components: {
      YHeader,
      YFooter,
      YShelf,
      YPopup,
      YButton
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../../assets/style/mixin";

  // 修改会员地址
  .edit-address {
    z-index: 1999;
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    @include wh(100%);
    background-color: rgba(0, 0, 0, .5);
    @extend %block-center;
    .content {
      display: flex;
      padding: 45px 100px 0;
    }
    > div {
      box-sizing: content-box;
      @include wh(500px, 460px);
      margin: 0;
    }
    .btn {
      width: 80px;
      height: 30px;
      margin-left: 10px;
      position: relative;
      text-align: center;
      line-height: 30px;
      text-shadow: rgba(255, 255, 255, .496094) 0 1px 0;
      border: 1px solid #E6E6E6;
      border-radius: 10px;
      &:hover {
      }
      a {
        color: #666;
        display: block;
        @include wh(100%);
      }
    }
    input[type=file] {
      position: absolute;
      right: 0;
      left: 0;
      top: 0;
      opacity: 0;
      width: 80px;
      height: 30px;
      cursor: pointer;
      box-sizing: border-box;
      border: 15px solid #000;
      overflow: hidden;
    }
    .edit-l {
      width: 100px;
      text-align: center;
    }
    .edit-r {
      margin-left: 20px;
      flex: 1;
      > div {
        border: 1px solid #ccc;
        width: 320px;
        height: 320px;
      }
    }
  }

  .close {
    position: absolute;
    right: 10px;
    top: 0;
    bottom: 0;
    padding: 0 10px;
    @extend %block-center;
    &:hover {
      svg {
        transition: all 1s;
        transform: rotate(360deg);
        transform-origin: 50% 50%;
      }
      path {
        fill: #8a8a8a;
      }
    }
  }

  .bootom-btn {
    padding: 0 15px;
    border-top: 1px solid #E6E6E6;
    bottom: 0;
    height: 60px;
    right: 0;
    left: 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  // 收货地址
  .address-item-list {
    padding: 30px 13px 0;
    .address {
      padding: 19px 14px 0 19px;
      p {
        line-height: 26px;
      }
    }
    li.checked {
      border-color: #6A8FE5;
      position: relative;
      background: #fff;
      .pa {
        right: 15px;
        top: 18px;
      }
      &:hover {
        background: #fff;
      }
    }
    li {
      position: relative;
      overflow: hidden;
      float: left;
      width: 276px;
      height: 158px;
      margin: 0 0 30px 16px;
      border: 1px solid #E5E5E5;
      border-radius: 3px;
      background: #FAFAFA;
      line-height: 14px;
      text-align: left;
      word-wrap: break-word;
      word-break: normal;
      color: #626262;
      cursor: pointer;
      -moz-user-select: -moz-none;
      -webkit-user-select: none;
      -o-user-select: none;
      user-select: none;
      &:hover {
        background: #F2F2F2;
        .operation-section {
          visibility: visible;
          transform: translate(0, 0);
        }
      }
      &.add-address-item {
        text-align: center;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        p {
          margin-top: 5px;
        }
      }
      .operation-section {
        visibility: hidden;
        position: absolute;
        left: 0;
        bottom: 0;
        width: 100%;
        height: 28px;
        background: #E1E1E1;
        border-top: 1px solid #E1E1E1;
        transition: all .2s ease;
        transform: translate(0, 29px);
        border-top: 1px solid #E1E1E1;
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 11;
        span {
          background: #fff;
          display: flex;
          align-items: center;
          justify-content: center;
          flex: 1;
          height: 100%;
          &:hover {
            background: #F2F2F2;
          }
        }

        span + span {
          border-left: 1px solid #E1E1E1;
        }

      }
    }
  }

  .s-content {
    .md {
      > div {
        text-align: left;
        margin-bottom: 15px;
        > input {
          width: 100%;
          height: 50px;
          font-size: 18px;
          padding: 10px 20px;
          border: 1px solid #ccc;
          border-radius: 6px;
          box-shadow: 0 3px 5px -4px rgba(0, 0, 0, .4) inset, -1px 0 3px -2px rgba(0, 0, 0, .1) inset;
          line-height: 46px;
        }
      }
    }

    .btn {
      margin: 0;
      width: 100%;
      height: 50px;
      font-size: 14px;
      line-height: 48px
    }
  }

  .ui-cart {
    img {
      width: 80px;
      height: 80px;
    }

    .cart-table-title {
      position: relative;
      z-index: 1;
      line-height: 38px;
      height: 38px;
      padding: 0 0 0 30px;
      font-size: 12px;
      background: #eee;
      border-bottom: 1px solid #dbdbdb;
      border-bottom-color: rgba(0, 0, 0, .08);
      .name {
        float: left;
        text-align: left;
      }
      span {
        width: 137px;
        float: right;
        text-align: center;
        color: #838383;
      }
    }
    .cart-group.divide {
      .cart-items {
        border-top: 1px dashed #eee;
      }
    }
    .cart-items {
      position: relative;
      height: 140px;
      margin-left: 74px;
      .subtotal {
        font-weight: 700;
      }
      .item-cols-num,
      .price,
      .subtotal {
        overflow: hidden;
        float: right;
        width: 137px;
        text-align: center;
        color: #666;
        line-height: 140px;
      }
      /*数量*/
      .subtotal,
      .item-cols-num {
        padding-top: 50px;
        line-height: 40px;
      }
      .select {
        width: 112px;
        height: 40px;
        padding-top: 4px;
        margin: 0 auto;
        line-height: 40px;
        .down {
          background-position: 0 -60px;
        }
        .down.down-disabled:hover {
          background-position: 0 -300px;
          cursor: not-allowed;
        }
        .num {
          position: relative;
          overflow: hidden;
          display: inline-block;
          width: 36px;
          height: 18px;
          margin: 7px 0 0;
          border: none;
          border-radius: 3px;
          line-height: 18px;
          text-align: center;
          font-size: 14px;
          transition: all .2s ease-out;
        }
      }

    }
    .down.down-disabled {
      background-position: 0 -300px;
      cursor: not-allowed;
    }
  }

  .cart-group.divide .cart-top-items:first-child .cart-items {
    border-top: none;
  }

  .items-choose {
    position: absolute;
    left: -74px;
    top: 0;
    width: 74px;
    height: 20px;
    padding: 60px 0 0 31px;
    font-size: 12px;
    color: #999;
  }

  .items-thumb {
    position: relative;
    margin-top: 30px;
    overflow: hidden;
    width: 80px;
    height: 80px;
  }

  .cart-items .items-thumb > a, .ui-cart .cart-items .items-thumb > i {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    border: 1px solid #fff;
    border-radius: 3px;
    border: 0 solid rgba(255, 255, 255, .1);
    box-shadow: inset 0 0 0 1px rgba(0, 0, 0, .06);
  }

  .name {
    width: 380px;
    margin-left: 20px;
    color: #323232;
    display: table;
    a {
      color: #333;
      font-size: 16px;
    }
  }

  .name-table {
    display: table-cell;
    vertical-align: middle;
    height: 140px;
  }

  .fix-bottom {
    padding: 22px 29px 19px 30px;
    height: 90px;
    width: 100%;
    z-index: 1;
    background-position: center;
    background: #fdfdfd;
    background: -webkit-linear-gradient(#fdfdfd, #f9f9f9);
    background: linear-gradient(#fdfdfd, #f9f9f9);
    border-top: 1px solid #e9e9e9;
    box-shadow: 0 -3px 8px rgba(0, 0, 0, .04);
    .cart-bottom-bg {
      height: 80px;
      border-top: 1px solid #D9D9D9;
      border-radius: 0 0 8px 8px;
    }
    .fix-bottom-inner {
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: flex-end;
    }
    .shipping {
      display: flex;
      align-items: center;
    }
    em {
      display: inline-block;
      position: relative;
      top: 3px;
      margin-top: -4px;
      font-size: 24px;
      color: #d44d44;
      font-weight: 700;
    }
  }

  .attribute, .name p {
    color: #999;
    font-size: 12px;
    padding-top: 4px;
    line-height: 17px;
  }


</style>
