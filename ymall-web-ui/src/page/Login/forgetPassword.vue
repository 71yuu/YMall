<template>
  <div class="login v2">
    <div class="wrapper">
      <div class="dialog dialog-shadow" style="display: block; margin-top: -236px;">

        <div class="registered" v-show="step1">
          <h4>忘记密码</h4>
          <div class="content" style="margin-top: 20px;">
            <el-form :model="forget1" :rules="forget1Rules" ref="forget1" class="forget-form">
              <el-form-item prop="account">
                <el-input v-model="forget1.account" size="large" placeholder="手机号/邮箱" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item>
                <div id="captcha">
                  <p id="wait">正在加载验证码...</p>
                </div>
              </el-form-item>
              <el-button type="primary" @click="forget1NextStep" :disabled="verAccount ? false : true" style="width: 370px; height: 48px;">
                下一步
              </el-button>
            </el-form>
          </div>
        </div>

        <div class="registered" v-show="step2">
          <h4>安全验证</h4>
          <div class="content" style="margin-top: 20px;">
            <el-form :model="forget2" :rules="forget2Rules" ref="forget2" class="forget-form">
              <el-form-item>
                <el-input v-model="forget1.account" auto-complete="off" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item >
                <el-col :span="14">
                  <el-form-item prop="verCode">
                    <el-input v-model="forget2.verCode" size="large" placeholder="验证码" style="width: 188px;" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="10">
                  <el-form-item>
                    <el-button @click="sendVerCode" type="primary" size="large" :disabled="sendAuthCode ? false : true"  style="width: 154px; height: 50px;">
                      <span v-show="sendAuthCode">获取验证码</span>
                      <span v-show="!sendAuthCode">重新发送({{authTime}})</span>
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-form-item>
              <el-button type="primary" @click="forget2NextStep" :disabled="verVerCode ? false : true" style="width: 370px; height: 48px;">
                下一步
              </el-button>
            </el-form>
          </div>
        </div>

        <div class="registered" v-show="step3">
          <h4>修改密码</h4>
          <div class="content" style="margin-top: 20px;">
            <el-form :model="forget3" :rules="forget3Rules" ref="forget3" class="forget-form">
              <el-form-item prop="password">
                <el-input type="password" v-model="forget3.password" placeholder="新密码" auto-complete="off" ></el-input>
              </el-form-item>
              <el-form-item prop="repass">
                <el-input type="password" v-model="forget3.repass" placeholder="确认密码" auto-complete="off"></el-input>
              </el-form-item>
              <el-button type="primary" @click="_updatePassword" :disabled="verPassword && verRePassword ? false : true" style="width: 370px; height: 48px;">
                确认更改密码
              </el-button>
            </el-form>
          </div>
        </div>

        <div class="registered" v-show="step4">
          <h4>密码重置成功</h4>
          <div class="content" style="padding: 30px 40px 24px;">
            <div class="content-success">
              <div class="content-success-img">
                <img src="/static/images/success.png"/>
              </div>
              <div class="content-success-title">
                <span>您的密码已经重置成功</span>
              </div>
              <div class="content-success-second">
                <span>{{second}} 秒后系统自动跳转</span>
              </div>
              <div class="border"></div>
              <div style="text-align: center;">
                <a href="/login">登录</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script src="../../../static/geetest/gt.js"></script>
<script>
import {checkAccount, geetest, forgetVerCode, updatePassword} from '/api/index.js'
require('../../../static/geetest/gt.js')
var captcha
export default {
  data () {
    let vAccount = async (rule, value, callback) => {
      const phoneReg = /^1[3|4|5|7|8][0-9]\d{8}$/
      const emailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
      if (!value) {
        this.verAccount = false
        return callback(new Error('请输入手机号或邮箱'))
      } else if (!phoneReg.test(value) && !emailReg.test(value)) {
        this.verAccount = false
        return callback(new Error('手机号/邮箱格式错误'))
      }
      let params = {
        params: {
          account: value
        }
      }
      await checkAccount(params).then(res => {
        if (res.result === false) {
          this.verAccount = false
          this.existAccount = false
        } else {
          this.verAccount = true
          this.existAccount = true
        }
      })
      if (!this.existAccount) {
        return callback(new Error('手机号/邮箱不存在'))
      }
      return callback()
    }
    let vVerCode = (rule, value, callback) => {
      let verCodeReg = /^\d{6}$/
      if (!verCodeReg.test(value)) {
        this.verVerCode = false
        return callback()
      }
      if (value !== this.verCode) {
        this.verVerCode = false
        return callback(new Error('验证码错误'))
      }
      this.verVerCode = true
      return callback()
    }
    let vPassword = (rule, value, callback) => {
      const passReg = /^(?![0-9]+$)(?![a-zjnnnpA-Z]+$)[0-9A-Za-z]{6,16}$/
      if (!value) {
        this.verPassword = false
        return callback(new Error('请输入密码'))
      } else if (!passReg.test(value)) {
        this.verPassword = false
        return callback(new Error('密码长度 6-16 位，数字字母至少包含两种'))
      } else {
        this.$refs.forget3.validateField('repass')
      }
      this.verPassword = true
      return callback()
    }
    let vRepass = (rule, value, callback) => {
      if (!value) {
        this.verRePassword = false
        return callback(new Error('请再次输入密码'))
      } else if (value !== this.forget3.password) {
        this.verRePassword = false
        return callback(new Error('两次输入密码不一致!'))
      }
      this.verRePassword = true
      return callback()
    }
    return {
      forget1: {
        account: ''
      },
      forget2: {
        verCode: ''
      },
      forget3: {
        password: '',
        repass: ''
      },
      forget1Rules: {
        account: [
          {validator: vAccount, trigger: 'change,blur'}
        ]
      },
      forget2Rules: {
        verCode: [
          {validator: vVerCode, trigger: 'change,blur'}
        ]
      },
      forget3Rules: {
        password: [
          {validator: vPassword, trigger: 'change,blur'}
        ],
        repass: [
          {validator: vRepass, trigger: 'change,blur'}
        ]
      },
      verAccount: false,
      verVerCode: false,
      verPassword: false,
      verRePassword: false,
      verCode: '',
      existAccount: false,
      step1: true,
      step2: false,
      step3: false,
      step4: false,
      sendAuthCode: true,
      authTime: 60,
      second: 5
    }
  },
  methods: {
    message (m) {
      this.$message.error({
        message: m
      })
    },
    initGeetest () {
      geetest().then(res => {
        this.statusKey = res.statusKey
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
    forget1NextStep () {
      let result = captcha.getValidate()
      if (!result) {
        this.message('请先完成验证')
        return false
      }
      this.step1 = false
      this.step2 = true
    },
    forget2NextStep () {
      this.step2 = false
      this.step3 = true
    },
    sendVerCode () {
      let account = this.forget1.account
      this.sendAuthCode = false
      let authTimetimer = setInterval(() => {
        this.authTime--
        if (this.authTime <= 0) {
          this.sendAuthCode = true
          clearInterval(authTimetimer)
          this.authTime = 60
        }
      }, 1000)
      let params = {
        params: {
          account: account
        }
      }
      forgetVerCode(params).then(res => {
        console.log(res.result)
        this.verCode = res.result
      })
    },
    async _updatePassword () {
      let account = this.forget1.account
      let password = this.forget3.password
      await updatePassword({
        account,
        password
      }).then(res => {
        if (res.status === 200) {
          this.step3 = false
          this.step4 = true
          this.secondReuce()
        } else {
          this.message('请求服务器异常,请检查您的网络信息')
        }
      })
    },
    secondReuce () {
      let secondT = setInterval(() => {
        this.second--
        if (this.second <= 0) {
          clearInterval(secondT)
          this.$router.push({
            path: '/login'
          })
        }
      }, 1000)
    }
  },
  mounted () {
    this.initGeetest()
  }
}
</script>

<style lang="scss" rel="stylesheet/scss">
  * {
    box-sizing: content-box;
  }

  .forget-form .el-input__inner {
    height: 50px;
  }

  .content-success {
    text-align: center;
  }

  .content-success-img {
    margin-top: 15px;
  }

  .content-success-img img {
    height: 80px;
    width: 80px;
  }

  .content-success-title {
    margin-top: 40px;
    font-size: 22px;
    color: #333;
  }

  .content-success-second {
    margin-top: 10px;
    color: #999;
    line-height: 1em;
    font-size: 14px;
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
      background: linear-gradient(#fff, #f5f5f5);
      height: auto;
      overflow: visible;
      box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
      position: relative;
      background-image: url(/static/images/smartisan_4ada7fecea.png);
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
    .register {
      padding: 1px 10px 0;
      border-right: 1px solid #ccc;
    }
    .border {
      margin-top: 10px;
      border-bottom: 1px solid #ccc;
      margin-bottom: 10px;
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
</style>
