<template>
  <div class="login v2">
    <div class="wrapper">
      <div class="dialog dialog-shadow" style="display: block; margin-top: -362px;">
        <div class="title">
          <h4>使用 YMall 账号 登录官网</h4>
        </div>
        <div class="content">
          <el-form :model="login" :rules="rules" ref="login" class="login-form">
            <el-form-item prop="account">
              <el-input v-model="login.account" size="large" placeholder="手机号/邮箱" auto-complete="off" ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="login.password" size="large" placeholder="密码" auto-complete="off" ></el-input>
            </el-form-item>
            <el-form-item>
              <div id="captcha">
                <p id="wait">正在加载验证码...</p>
              </div>
            </el-form-item>
            <el-form-item>
              <el-col :span="12">
                <el-checkbox class="auto-login" v-model="login.auto">自动登录</el-checkbox>
              </el-col>
              <el-col :span="12" style="float: right;">
                <a href="javascript:;" @click="toRegister">注册 YMall 账号</a>
                <a style="padding: 1px 0 0 10px" @click="toForgetPassword">忘记密码 ?</a>
              </el-col>
            </el-form-item>
            <el-button type="primary" @click="toLogin" :disabled="verAccount && verPassword ? false : true" style="width: 370px; height: 48px;">{{loginTxt}}</el-button>
            <div class="border"></div>
            <div class="footer">
              <div class="other">其它账号登录：</div>
              <a><img @click="open('待开发','此功能开发中...')" style="height: 15px; margin-top: 22px;" src="/static/images/other-login.png"></a>
            </div>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>
<script src="../../../static/geetest/gt.js"></script>
<script>
import YFooter from '/common/footer'
import YButton from '/components/YButton'
import { addCartProduct } from '/api/cart.js'
import {memberLogin, geetest} from '/api/index.js'
import { setStore, getStore, removeStore } from '../../utils/storage'
require('../../../static/geetest/gt.js')
var captcha
export default {
  data () {
    let vAccount = (rule, value, callback) => {
      const phoneReg = /^1[3|4|5|7|8][0-9]\d{8}$/
      const emailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
      if (!value) {
        this.verAccount = false
        return callback(new Error('请输入手机号或邮箱'))
      } else if (!phoneReg.test(value) && !emailReg.test(value)) {
        this.verAccount = false
        return callback(new Error('手机号/邮箱格式错误'))
      } else {
        this.verAccount = true
        return callback()
      }
    }
    let vPassword = (rule, value, callback) => {
      if (!value) {
        this.verPassword = false
        return callback(new Error('请输入密码'))
      } else {
        this.verPassword = true
        return callback()
      }
    }
    return {
      login: {
        account: '',
        password: '',
        auto: true
      },
      rules: {
        account: [
          {validator: vAccount, trigger: 'change,blur'}
        ],
        password: [
          {validator: vPassword, trigger: 'change,blur'}
        ]
      },
      verAccount: false,
      verPassword: false,
      loginTxt: '登录',
      cart: []
    }
  },
  methods: {
    message (m) {
      this.$message.error({
        message: m
      })
    },
    open (t, m) {
      this.$notify.info({
        title: t,
        message: m
      })
    },
    toRegister () {
      this.$router.push({
        path: '/register'
      })
    },
    toForgetPassword () {
      this.$router.push({
        path: '/forgetPassword'
      })
    },
    initGeetest () {
      geetest().then(res => {
        window.initGeetest({
          gt: res.gt,
          challenge: res.challenge,
          new_captcha: res.new_captcha,
          offline: !res.success,
          product: 'popup',
          width: '100%'
        }, function (captchaObj) {
          captcha = captchaObj
          captchaObj.appendTo('#captcha')
          captchaObj.onReady(function () {
            document.getElementById('wait').style.display = 'none'
          })
        })
      })
    },
    autoLogin () {
      if (this.login.auto === true) {
        setStore('autoLogin', 'true')
      } else {
        setStore('autoLogin', 'false')
      }
    },
    // 登录时将本地的添加到用户购物车
    login_addCart () {
      let cartArr = []
      let localCart = JSON.parse(getStore('buyCart'))
      if (localCart && localCart.length) {
        localCart.forEach(item => {
          cartArr.push({
            userId: getStore('userId'),
            productId: item.productId,
            productNum: item.productNum
          })
        })
      }
      this.cart = cartArr
    },
    toLogin () {
      this.loginTxt = '登录中...'
      this.autoLogin()
      let result = captcha.getValidate()
      if (!result) {
        this.message('请完成验证')
        this.loginTxt = '登录'
        return false
      }
      let account = this.login.account
      let password = this.login.password
      let auto = this.login.auto
      memberLogin({
        account,
        password,
        auto
      }).then(res => {
        if (res.status === 200) {
          setStore('token', res.result.token)
          setStore('userId', res.result.id)
          // 登录后添加当前缓存中的购物车
          this.login_addCart()
          if (this.cart.length) {
            for (let i = 0; i < this.cart.length; i++) {
              addCartProduct(this.cart[i]).then(res => {
              })
            }
            removeStore('buyCart')
            this.$router.push({
              path: '/'
            })
          }

          this.$router.push({
            path: '/'
          })
        } else {
          this.loginTxt = '登录'
          this.message(res.message)
          captcha.reset()
          return false
        }
      })
    }
  },
  mounted () {
    let autoLogin = getStore('autoLogin')
    if (autoLogin === 'true') {
      this.login.auto = true
    } else if (autoLogin === 'false') {
      this.login.auto = false
    } else {
      this.login.auto = true
    }
    this.initGeetest()
  },
  components: {
    YFooter,
    YButton
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
* {
  box-sizing: content-box;
}

.login .el-input__inner {
  height: 50px;
}

.phone-login {
  margin-top: 20px;
  margin-left: 125px;
  color: #999;
}

.login {
  overflow-x: hidden;
  overflow-y: hidden;
  .input {
    height: 50px;
    display: flex;
    align-items: center;
    input {
      font-size: 16px;
      width: 100%;
      height: 100%;
      padding: 10px 15px;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 6px;
    }
  }
  .wrapper {
    background: url(/static/images/bg_9b9dcb65ff.png) repeat;
    background-size: 100px;
    min-height: 800px;
    min-width: 630px;
  }
}

.v2 .dialog {
  width: 450px;
  border: 1px solid #dadada;
  border-radius: 10px;
  top: 50%;
  left: 50%;
  margin-left: -225px;
  position: absolute;
  .title {
    color: #333;
    font-weight: 400;
    background: linear-gradient(#fff, #f5f5f5);
    height: auto;
    overflow: visible;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
    position: relative;
    background-image: url(/static/images/smart-Y.png);
    background-size: 140px;
    background-position: top center;
    background-repeat: no-repeat;
    height: 92px;
    margin: 23px 0 50px;
    padding: 75px 0 0;
    box-shadow: none;
    h4 {
      padding: 0;
      text-align: center;
      color: #666;
      border-bottom: 1px solid #dcdcdc;
      -webkit-box-shadow: none;
      -moz-box-shadow: none;
      box-shadow: none;
      font-weight: 700;
      position: absolute;
      bottom: 0;
      width: 100%;
      text-align: center;
      margin: 0;
      padding: 0;
      border-bottom: 0;
      -webkit-box-shadow: none;
      -moz-box-shadow: none;
      box-shadow: none;
      line-height: 1em;
      height: auto;
      color: #333;
      font-weight: 400;
    }
  }
  .content {
    padding: 0 40px 22px;
    height: auto;
    .common-form {
      li {
        clear: both;
        margin-bottom: 15px;
        position: relative;
      }
    }
  }
}

.dialog-shadow,
.v2 .bbs .dialog-shadow,
.v2 .dialog-shadow {
  -webkit-box-shadow: 0 9px 30px -6px rgba(0, 0, 0, 0.2),
    0 18px 20px -10px rgba(0, 0, 0, 0.04), 0 18px 20px -10px rgba(0, 0, 0, 0.04),
    0 10px 20px -10px rgba(0, 0, 0, 0.04);
  -moz-box-shadow: 0 9px 30px -6px rgba(0, 0, 0, 0.2),
    0 18px 20px -10px rgba(0, 0, 0, 0.04), 0 18px 20px -10px rgba(0, 0, 0, 0.04),
    0 10px 20px -10px rgba(0, 0, 0, 0.04);
  box-shadow: 0 9px 30px -6px rgba(0, 0, 0, 0.2),
    0 18px 20px -10px rgba(0, 0, 0, 0.04), 0 18px 20px -10px rgba(0, 0, 0, 0.04),
    0 10px 20px -10px rgba(0, 0, 0, 0.04);
}

@media screen and (min-width: 737px),
  screen and (-webkit-max-device-pixel-ratio: 1.9) and (max-width: 736px) and (min-device-width: 737px) {
  .wrapper {
    background: url(/static/images/con-bg_04f25dbf8e.jpg) repeat-x;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
  }
  .dialog {
    background: url(/static/images/dialog-gray-bg.png) #fff bottom repeat-x;
    border-radius: 12px;
    display: none;
    margin: -163px 0 0 -218px;
    width: 436px;
    position: fixed;
    left: 50%;
    top: 50%;
  }
  .dialog .title h4 {
    border-bottom: #d1d1d1 solid 1px;
    box-shadow: 0 2px 6px #d1d1d1;
    color: #666;
    font-size: 20px;
    height: 61px;
    line-height: 61px;
    padding: 0 0 0 35px;
  }
  .common-form li {
    clear: both;
    margin-bottom: 15px;
    position: relative;
  }
  .auto-login {
    position: absolute;
    top: 0px;
    left: 2px;
    color: #999;
  }
  .register {
    padding: 1px 10px 0;
    border-right: 1px solid #ccc;
  }
  .border {
    margin-top: 10px;
    border-bottom: 1px solid #ccc;
  }
  .other {
    margin: 20px 5px 0 0;
    width: auto;
    color: #bbb;
    font-size: 12px;
    cursor: default;
    color: #999;
  }
  .footer {
    display: flex;
    flex-direction: row;
  }
  .agree {
    margin-bottom: 30px;
    color: #999;
  }
}

.registered {
  h4 {
    padding: 0;
    text-align: center;
    color: #666;
    border-bottom: 1px solid #dcdcdc;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
    font-weight: 700;
    font-size: 20px;
    height: 60px;
    line-height: 60px;
  }
}

#wait {
  text-align: left;
  color: #999;
  margin: 0;
}
</style>
