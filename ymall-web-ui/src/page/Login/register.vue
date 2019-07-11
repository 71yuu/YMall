<template>
  <div class="login v2">
    <div class="wrapper">
      <div class="dialog dialog-shadow" style="display: block; margin-top: -318px;">
        <div class="registered">
          <h4>注册 YMall 账号</h4>
          <div class="content" style="margin-top: 20px;">
            <el-form :model="registered" :rules="rules" ref="registered" class="register-form">
              <el-form-item prop="phone">
                <el-input v-model="registered.phone" size="large" placeholder="手机号" auto-complete="off" ></el-input>
              </el-form-item>
              <el-form-item >
                <el-col :span="14">
                  <el-form-item prop="verCode">
                    <el-input v-model="registered.verCode" size="large" placeholder="验证码" style="width: 188px;" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="10">
                  <el-form-item>
                    <el-button @click="sendVerCode" type="primary" size="large" :disabled="verPhone && sendAuthCode ? false : true"  style="width: 154px; height: 50px;">
                      <span v-show="sendAuthCode">获取验证码</span>
                      <span v-show="!sendAuthCode">重新发送({{authTime}})</span>
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-form-item>
              <el-form-item prop="pass">
                <el-input type="password" v-model="registered.pass" size="large"  placeholder="密码" auto-complete="off" style="height: 50px;"></el-input>
              </el-form-item>
              <el-form-item prop="pass2">
                <el-input type="password" v-model="registered.pass2" size="large"  placeholder="确认密码" auto-complete="off" style="height: 50px;"></el-input>
              </el-form-item>
                <el-checkbox class="agree" v-model="agreement">
                  我已阅读并同意遵守
                  <a @click="open('法律声明','此仅为个人练习项目，不承担任何法律问题')">法律声明</a> 和
                  <a @click="open('隐私条款','本网站将不会严格遵守有关法律法规和本隐私政策所载明的内容收集、使用您的信息')">隐私条款</a>
                </el-checkbox>
                <el-button type="primary" @click="regist" :disabled="verPhone && verVerCode && verPass && verPass2 && agreement ? false : true" style="width: 370px; height: 48px;">{{registerTxt}}</el-button>
              <div class="border"></div>
              <span class="agree" style="margin-left: 50px;">
                如果您已拥有 YMall 账号，可在此
                <a @click="toLogin">登录</a>
              </span>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import YFooter from '/common/footer'
import YButton from '/components/YButton'
import {checkPhone, vercode, register} from '/api/index.js'
export default {
  data () {
    let vPhone = async (rule, value, callback) => {
      const phoneReg = /^1[3|4|5|7|8][0-9]\d{8}$/
      if (!value) {
        this.verPhone = false
        return callback(new Error('请输入手机号'))
      }
      if (!phoneReg.test(value)) {
        this.verPhone = false
        return callback(new Error('请输入正确的手机号'))
      }
      await checkPhone({
        value
      }).then(res => {
        if (res.result === true) {
          this.verPhone = false
          this.existPhone = true
        } else {
          this.verPhone = true
          this.existPhone = false
        }
      })
      if (this.existPhone) {
        return callback(new Error('此手机号已注册'))
      }
      this.$refs.registered.validateField('verCode')
      return callback()
    }
    let vVerCode = (rule, value, callback) => {
      let phone = this.registered.phone
      if (value !== this.verCode) {
        this.verVerCode = false
        return callback(new Error('验证码错误'))
      } else if (phone !== this.codePhone) {
        this.verVerCode = false
        return callback(new Error('验证码错误'))
      }
      this.verVerCode = true
      return callback()
    }
    let vPass = (rule, value, callback) => {
      const passReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/
      if (!value) {
        this.verPass = false
        return callback(new Error('请输入密码'))
      } else if (!passReg.test(value)) {
        this.verPass = false
        return callback(new Error('密码长度 6-16 位，数字字母至少包含两种'))
      } else {
        this.$refs.registered.validateField('pass2')
      }
      this.verPass = true
      return callback()
    }
    let vPass2 = (rule, value, callback) => {
      if (!value) {
        this.verPass2 = false
        return callback(new Error('请再次输入密码'))
      } else if (value !== this.registered.pass) {
        this.verPass2 = false
        return callback(new Error('两次输入密码不一致!'))
      }
      this.verPass2 = true
      return callback()
    }
    return {
      registered: {
        phone: '',
        verCode: '',
        pass: '',
        pass2: ''
      },
      rules: {
        phone: [
          {validator: vPhone, trigger: 'change,blur'}
        ],
        verCode: [
          {validator: vVerCode, trigger: 'blur'}
        ],
        pass: [
          {validator: vPass, trigger: 'change,blur'}
        ],
        pass2: [
          {validator: vPass2, trigger: 'change,blur'}
        ]
      },
      verPhone: false,
      verVerCode: false,
      verPass: false,
      verPass2: false,
      existPhone: false,
      sendAuthCode: true,
      authTime: 60,
      verCode: '',
      codePhone: '',
      agreement: false,
      registerTxt: '注册',
      cart: []
    }
  },
  methods: {
    sendVerCode () {
      let phone = this.registered.phone
      this.sendAuthCode = false
      let authTimetimer = setInterval(() => {
        this.authTime--
        if (this.authTime <= 0) {
          this.sendAuthCode = true
          clearInterval(authTimetimer)
        }
      }, 1000)
      vercode({
        phone
      }).then(res => {
        console.log(res.result)
        this.codePhone = phone
        this.verCode = res.result
      })
    },
    regist () {
      let phone = this.registered.phone
      let password = this.registered.pass
      this.registerTxt = '注册中...'
      register({
        phone,
        password
      }).then(res => {
        if (res.status === 200) {
          this.messageSuccess()
          this.toLogin()
        } else {
          this.message(res.message)
          this.registerTxt = '注册'
        }
      })
    },
    open (t, m) {
      this.$notify.info({
        title: t,
        message: m
      })
    },
    messageSuccess () {
      this.$message({
        message: '恭喜您，注册成功！赶紧区登录体验吧',
        type: 'success'
      })
    },
    toLogin () {
      this.$router.push({
        path: '/login'
      })
    }
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

.register-form .el-input__inner {
  height: 50px;
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
