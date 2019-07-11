<template>
  <div>
    <y-shelf title="账户资料">
      <div slot="content">
        <!-- 头像 -->
        <div class="avatar-box">
          <div class=img-box><img :src="userInfo.info.file" alt=""></div>
          <div class="r-box">
            <h3 style="margin-left: 13px;">修改头像</h3>
            <y-button text="上传头像" classStyle="main-btn" style="margin: 0;" @btnClick="editAvatar()"></y-button>
          </div>
        </div>

        <!-- 账户昵称 -->
        <div class="avatar-box">
          <div class="status-box">
            <div class="safe-center-title">
              <h3>账户昵称</h3>
            </div>
            <p>{{userTxt}}</p>
          </div>
          <div class="safe-todo">
            <a @click="updateUsername">{{userOperate}}</a>
          </div>
        </div>

        <!-- 手机号 -->
        <div class="avatar-box">
          <div class="status-box">
            <div class="safe-center-title">
              <h3>手机验证</h3>
            </div>
            <p>{{phoneTxt}}</p>
          </div>
          <div class="safe-todo">
            <a @click="updatePhone">{{phoneOperate}}</a>
          </div>
        </div>

        <!-- 登录密码 -->
        <div class="avatar-box">
          <div class="status-box">
            <div class="safe-center-title">
              <h3>登录密码</h3>
            </div>
            <p>互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</p>
          </div>
          <div class="safe-todo">
            <a @click="openPass">修改</a>
          </div>
        </div>

        <!-- 邮箱 -->
        <div class="avatar-box" style="border: none">
          <div class="status-box">
            <div class="safe-center-title">
              <h3>邮箱验证</h3>
            </div>
            <p>{{emailTxt}}</p>
          </div>
          <div class="safe-todo">
            <a @click="openEmail">{{emailOperate}}</a>
          </div>
        </div>

        <!-- 修改用户名 -->
        <div class="edit-nick" v-show="editNickShow">
          <y-shelf title="修改昵称">
            <span slot="right">
              <span class="close" @click="editNickShow=false">
                <svg t="1501234940517" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="3014" xmlns:xlink="http://www.w3.org/1999/xlink"
                     width="20" height="20"><path
                  d="M941.576 184.248l-101.824-101.824L512 410.176 184.248 82.424 82.424 184.248 410.168 512l-327.744 327.752 101.824 101.824L512 613.824l327.752 327.752 101.824-101.824L613.832 512z"
                  fill="#cdcdcd" p-id="3015"></path></svg>
              </span>
            </span>
            <div slot="content" class="content">
              <el-form :model="nickVer" :rules="nickVerRules" ref="nickVer" class="register-form">
                <el-form-item prop="nick">
                  <el-input v-model="nickVer.nick" size="large" placeholder="昵称" auto-complete="off" style="width:296px;"></el-input>
                </el-form-item>
              </el-form>

              <div class="bootom-btn pa">
                <el-button @click="editNickShow=false" style="width: 140px; height: 40px;">
                  取消
                </el-button>
                <el-button @click="saveUsername({userId:userId, token: token, username: nickVer.nick})" type="primary" :disabled="verNick ? false : true"  style="width: 140px; height: 40px;">
                  确定
                </el-button>
              </div>
            </div>
          </y-shelf>
        </div>

        <!-- 手机号验证 -->
        <div class="edit-phone" v-show="verPhoneShow">
          <y-shelf title="安全验证">
            <span slot="right">
              <span class="close" @click="verPhoneShow=false">
                <svg t="1501234940517" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="3014" xmlns:xlink="http://www.w3.org/1999/xlink"
                     width="20" height="20"><path
                  d="M941.576 184.248l-101.824-101.824L512 410.176 184.248 82.424 82.424 184.248 410.168 512l-327.744 327.752 101.824 101.824L512 613.824l327.752 327.752 101.824-101.824L613.832 512z"
                  fill="#cdcdcd" p-id="3015"></path></svg>
              </span>
            </span>
            <div slot="content" class="content">
              <el-form :model="Ver" :rules="VerRules" ref="Ver" class="register-form">
                <el-form-item>
                  <el-input v-model="this.VPhone" size="large" placeholder="手机号" disabled auto-complete="off" style="width:296px;"></el-input>
                </el-form-item>
                <el-form-item >
                  <el-col :span="14">
                    <el-form-item prop="verCode">
                      <el-input v-model="Ver.verCode" size="large" placeholder="验证码" style="width: 130px;" auto-complete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item>
                      <el-button @click="sendVer()" type="primary" size="large" :disabled="sendAuthCode ? false : true"  style="width: 125px; height: 50px;">
                        <span v-show="sendAuthCode">获取验证码</span>
                        <span v-show="!sendAuthCode">重新发送({{authTime}})</span>
                      </el-button>
                    </el-form-item>
                  </el-col>
                </el-form-item>
              </el-form>

              <div class="bootom-btn pa">
                <el-button @click="verPhoneShow=false" style="width: 140px; height: 40px;">
                  取消
                </el-button>
                <el-button @click="verNext" type="primary" :disabled="vvCode ? false : true"  style="width: 140px; height: 40px;">
                  下一步
                </el-button>
              </div>
            </div>
          </y-shelf>
        </div>

        <!-- 修改手机号 -->
        <div class="edit-phone" v-show="editPhoneShow">
          <y-shelf title="修改手机号">
            <span slot="right">
              <span class="close" @click="editPhoneShow=false">
                <svg t="1501234940517" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="3014" xmlns:xlink="http://www.w3.org/1999/xlink"
                     width="20" height="20"><path
                  d="M941.576 184.248l-101.824-101.824L512 410.176 184.248 82.424 82.424 184.248 410.168 512l-327.744 327.752 101.824 101.824L512 613.824l327.752 327.752 101.824-101.824L613.832 512z"
                  fill="#cdcdcd" p-id="3015"></path></svg>
              </span>
            </span>
            <div slot="content" class="content">
              <el-form :model="phoneVer" :rules="phoneRules" ref="phoneVer" class="register-form">
                <el-form-item prop="phone">
                  <el-input v-model="phoneVer.phone" size="large" placeholder="手机号" auto-complete="off" style="width:296px;"></el-input>
                </el-form-item>
                <el-form-item >
                  <el-col :span="14">
                    <el-form-item prop="verCode">
                      <el-input v-model="phoneVer.verCode" size="large" placeholder="验证码" style="width: 130px;" auto-complete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item>
                      <el-button @click="sendVerCode()" type="primary" size="large" :disabled="verPhone && sendAuth ? false : true"  style="width: 125px; height: 50px;">
                        <span v-show="sendAuth">获取验证码</span>
                        <span v-show="!sendAuth">重新发送({{sendTime}})</span>
                      </el-button>
                    </el-form-item>
                  </el-col>
                </el-form-item>
              </el-form>

              <div class="bootom-btn pa">
                <el-button @click="editPhoneShow=false" style="width: 140px; height: 40px;">
                  取消
                </el-button>
                <el-button @click="succPhone({userId:userId, token: token, phone: phoneVer.phone})" type="primary" :disabled="verPhone && codeV ? false : true"  style="width: 140px; height: 40px;">
                  确定
                </el-button>
              </div>
            </div>
          </y-shelf>
        </div>

        <!-- 修改密码 -->
        <div class="edit-pass" v-show="editPassShow">
          <y-shelf title="修改密码">
            <span slot="right">
              <span class="close" @click="editPassShow=false">
                <svg t="1501234940517" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="3014" xmlns:xlink="http://www.w3.org/1999/xlink"
                     width="20" height="20"><path
                  d="M941.576 184.248l-101.824-101.824L512 410.176 184.248 82.424 82.424 184.248 410.168 512l-327.744 327.752 101.824 101.824L512 613.824l327.752 327.752 101.824-101.824L613.832 512z"
                  fill="#cdcdcd" p-id="3015"></path></svg>
              </span>
            </span>
            <div slot="content" class="content">
              <el-form :model="passVer" :rules="passRules" ref="passVer" class="register-form">
                <el-form-item prop="oldPass">
                  <el-input type="password" v-model="passVer.oldPass" size="large" placeholder="旧密码" auto-complete="off" style="width:296px;"></el-input>
                </el-form-item>
                <el-form-item prop="newPass">
                  <el-input type="password" v-model="passVer.newPass" size="large" placeholder="新密码" auto-complete="off" style="width:296px;"></el-input>
                </el-form-item>
                <el-form-item prop="rePass">
                  <el-input type="password" v-model="passVer.rePass" size="large" placeholder="确认密码" auto-complete="off" style="width:296px;"></el-input>
                </el-form-item>
              </el-form>

              <div class="bootom-btn pa">
                <el-button @click="editPassShow=false" style="width: 140px; height: 40px;">
                  取消
                </el-button>
                <el-button @click="updatePass({userId:userId, token: token, password: passVer.newPass})" type="primary" :disabled="vOPass && vNPass && vRPass ? false : true"  style="width: 140px; height: 40px;">
                  确定
                </el-button>
              </div>
            </div>
          </y-shelf>
        </div>

        <!-- 邮箱验证 -->
        <div class="edit-phone" v-show="verEmailOneShow">
          <y-shelf title="安全验证">
            <span slot="right">
              <span class="close" @click="verEmailOneShow=false">
                <svg t="1501234940517" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="3014" xmlns:xlink="http://www.w3.org/1999/xlink"
                     width="20" height="20"><path
                  d="M941.576 184.248l-101.824-101.824L512 410.176 184.248 82.424 82.424 184.248 410.168 512l-327.744 327.752 101.824 101.824L512 613.824l327.752 327.752 101.824-101.824L613.832 512z"
                  fill="#cdcdcd" p-id="3015"></path></svg>
              </span>
            </span>
            <div slot="content" class="content">
              <el-form :model="VerEmailOne" :rules="VerEmailOneRules" ref="VerEmail" class="register-form">
                <el-form-item>
                  <el-input v-model="VerEmailOne.email" size="large" placeholder="邮箱" disabled auto-complete="off" style="width:296px;"></el-input>
                </el-form-item>
                <el-form-item >
                  <el-col :span="14">
                    <el-form-item prop="verCode">
                      <el-input v-model="VerEmailOne.verCode" size="large" placeholder="验证码" style="width: 130px;" auto-complete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item>
                      <el-button @click="sendEmailOne()" type="primary" size="large" :disabled="sendEmailAuthCodeOne ? false : true"  style="width: 125px; height: 50px;">
                        <span v-show="sendEmailAuthCodeOne">获取验证码</span>
                        <span v-show="!sendEmailAuthCodeOne">重新发送({{authEmailOneTime}})</span>
                      </el-button>
                    </el-form-item>
                  </el-col>
                </el-form-item>
              </el-form>

              <div class="bootom-btn pa">
                <el-button @click="verEmailOneShow=false" style="width: 140px; height: 40px;">
                  取消
                </el-button>
                <el-button @click="verEmailNext" type="primary" :disabled="vEmailOne ? false : true"  style="width: 140px; height: 40px;">
                  下一步
                </el-button>
              </div>
            </div>
          </y-shelf>
        </div>

        <!-- 更新邮箱 -->
        <div class="edit-phone" v-show="verEmailTwoShow">
          <y-shelf title="安全验证">
            <span slot="right">
              <span class="close" @click="verEmailTwoShow=false">
                <svg t="1501234940517" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="3014" xmlns:xlink="http://www.w3.org/1999/xlink"
                     width="20" height="20"><path
                  d="M941.576 184.248l-101.824-101.824L512 410.176 184.248 82.424 82.424 184.248 410.168 512l-327.744 327.752 101.824 101.824L512 613.824l327.752 327.752 101.824-101.824L613.832 512z"
                  fill="#cdcdcd" p-id="3015"></path></svg>
              </span>
            </span>
            <div slot="content" class="content">
              <el-form :model="VerEmailTwo" :rules="VerEmailTwoRules" ref="VerEmailTwo" class="register-form">
                <el-form-item prop="email">
                  <el-input v-model="VerEmailTwo.email" size="large" placeholder="邮箱" auto-complete="off" style="width:296px;"></el-input>
                </el-form-item>
                <el-form-item >
                  <el-col :span="14">
                    <el-form-item prop="verCode">
                      <el-input v-model="VerEmailTwo.verCode" size="large" placeholder="验证码" style="width: 130px;" auto-complete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item>
                      <el-button @click="sendEmailTwo()" type="primary" size="large" :disabled="verEmailTwoEmail & sendEmailAuthCodeTwo ? false : true"  style="width: 125px; height: 50px;">
                        <span v-show="sendEmailAuthCodeTwo">获取验证码</span>
                        <span v-show="!sendEmailAuthCodeTwo">重新发送({{authEmailTwoTime}})</span>
                      </el-button>
                    </el-form-item>
                  </el-col>
                </el-form-item>
              </el-form>

              <div class="bootom-btn pa">
                <el-button @click="verEmailTwoShow=false" style="width: 140px; height: 40px;">
                  取消
                </el-button>
                <el-button @click="_updateEmail({userId:userId, token: token, email: VerEmailTwo.email})" type="primary" :disabled="verEmailTwoEmail & verEmailTwoCode ? false : true"  style="width: 140px; height: 40px;">
                  确认
                </el-button>
              </div>
            </div>
          </y-shelf>
        </div>

        <!-- 上传头像 -->
        <div class="edit-avatar" v-if="editAvatarShow">
          <y-shelf title="设置头像">
            <span slot="right">
              <span class="close" @click="editAvatarShow=false">
                <svg t="1501234940517" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="3014" xmlns:xlink="http://www.w3.org/1999/xlink"
                     width="20" height="20"><path
                  d="M941.576 184.248l-101.824-101.824L512 410.176 184.248 82.424 82.424 184.248 410.168 512l-327.744 327.752 101.824 101.824L512 613.824l327.752 327.752 101.824-101.824L613.832 512z"
                  fill="#cdcdcd" p-id="3015"></path></svg>
              </span>
            </span>
            <div slot="content" class="content">
              <div class="edit-l">
                <div style="width: 100px;height: 100px;border: 1px solid #ccc;margin-bottom: 20px;overflow: hidden;">
                  <div class="show-preview"
                       :style="{'width': previews.w + 'px','height': previews.h + 'px','overflow': 'hidden','zoom':option.zoom}">
                    <div :style="previews.div">
                      <img :src="option.img"
                           :style="previews.img">
                    </div>
                  </div>
                </div>
                <div style="padding: 10px 0 ">头像预览</div>
                <div class="btn">
                  <a href="javascript:;">重新选择</a>
                  <input type="file" value="上传头像" @change="upimg($event)">
                </div>
              </div>
              <div class="edit-r">
                <div>
                  <div class="big" id="cropper-target" v-if="option.img">
                    <vueCropper
                      :img="option.img"
                      @realTime="realTime"
                      ref="cropper"
                      :outputSize="example2.size"
                      :info="example2.info"
                      :canScale="example2.canScale"
                      :autoCrop="example2.autoCrop"
                      :autoCropWidth="example2.width"
                      :autoCropHeight="example2.height"
                      :fixed="example2.fixed"
                    ></vueCropper>
                  </div>
                </div>

              </div>
              <div class="bootom-btn pa">
                <y-button style="width: 140px;height: 40px;line-height: 40px"
                          text="取消"
                          @btnClick="editAvatarShow=false">
                </y-button>
                <y-button style="width: 140px;height: 40px;line-height: 40px"
                          text="确定"
                          classStyle="main-btn"
                          @btnClick="cropper">
                </y-button>
              </div>
            </div>
          </y-shelf>
        </div>
      </div>
    </y-shelf>
  </div>
</template>
<script>
  import YButton from '/components/YButton'
  import YPopup from '/components/popup'
  import { uploadImg, updateUsername, updatePhone, updatePass, logout, sendEmailCode, updateEmail } from '/api/member'
  import YShelf from '/components/shelf'
  import vueCropper from 'vue-cropper'
  import { mapState, mapMutations } from 'vuex'
  import { getStore, removeStore } from '/utils/storage'
  import { checkPhone, checkEmail, vercode } from '/api/index'
  import md5 from 'js-md5'
  export default {
    data () {
      // 验证昵称
      let vNick = (rule, value, callback) => {
        if (!value) {
          this.verNick = false
          return callback(new Error('昵称不能为空'))
        }
        this.verNick = true
        return callback()
      }
      // 验证手机号
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
        this.$refs.phoneVer.validateField('verCode')
        return callback()
      }
      // 验证第二次验证码
      let vVerCode = (rule, value, callback) => {
        let phone = this.phoneVer.phone
        if (value !== this.code) {
          this.codeV = false
          return callback(new Error('验证码错误'))
        } else if (phone !== this.codePhone) {
          this.codeV = false
          return callback(new Error('验证码错误'))
        }
        this.codeV = true
        return callback()
      }
      // 验证第一次验证码
      let vCode = (rule, value, callback) => {
        if (value !== this.verCode) {
          this.vvCode = false
          return callback(new Error('验证码错误'))
        }
        this.vvCode = true
        return callback()
      }
      // 验证旧密码
      let vOldPass = (rule, value, callback) => {
        value = md5(value)
        if (value !== this.userInfo.info.password) {
          this.vOPass = false
          return callback(new Error('旧密码错误'))
        }
        this.vOPass = true
        return callback()
      }
      // 验证新密码
      let vNewPass = (rule, value, callback) => {
        const passReg = /^(?![0-9]+$)(?![a-zjnnnpA-Z]+$)[0-9A-Za-z]{6,16}$/
        if (!value) {
          this.vNPass = false
          return callback(new Error('请输入密码'))
        } else if (!passReg.test(value)) {
          this.vNPass = false
          return callback(new Error('密码长度 6-16 位，数字字母至少包含两种'))
        } else {
          this.$refs.passVer.validateField('rePass')
        }
        this.vNPass = true
        return callback()
      }
      // 验证确认密码
      let vRePass = (rule, value, callback) => {
        if (!value) {
          this.vRPass = false
          return callback(new Error('请再次输入密码'))
        } else if (value !== this.passVer.newPass) {
          this.vRPass = false
          return callback(new Error('两次输入密码不一致!'))
        }
        this.vRPass = true
        return callback()
      }
      // 第一次安全验证邮箱
      let vEmailOneCode = (rule, value, callback) => {
        if (!value) {
          this.vEmailOne = false
          return callback(new Error('请输入验证码'))
        } else if (value !== this.verEmailOneCode) {
          this.vEmailOne = false
          return callback(new Error('验证码错误'))
        }
        this.vEmailOne = true
        return callback()
      }
      // 更改邮箱邮箱验证邮箱是否存在
      let vEmailTwoEmail = async (rule, value, callback) => {
        const emailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
        if (!value) {
          this.verEmailTwoEmail = false
          return callback(new Error('请输入邮箱'))
        }
        if (!emailReg.test(value)) {
          this.verEmailTwoEmail = false
          return callback(new Error('请输入正确的邮箱'))
        }
        let params = {
          params: {
            email: value
          }
        }
        await checkEmail(params).then(res => {
          if (res.result === true) {
            this.verEmailTwoEmail = false
            this.existEmail = true
          } else {
            this.verEmailTwoEmail = true
            this.existEmail = false
          }
        })
        if (this.existEmail) {
          return callback(new Error('此邮箱已注册'))
        }
        this.$refs.VerEmailTwo.validateField('verCode')
        return callback()
      }
      // 更改邮箱验证验证码
      let vEmailTwoCode = (rule, value, callback) => {
        let email = this.VerEmailTwo.email
        if (value !== this.twoCode) {
          this.verEmailTwoCode = false
          return callback(new Error('验证码错误'))
        } else if (email !== this.codeEmail) {
          this.verEmailTwoCode = false
          return callback(new Error('验证码错误'))
        }
        this.verEmailTwoCode = true
        return callback()
      }
      return {
        editAvatarShow: false,
        imgSrc: '',
        cropContext: '',
        cropperImg: '',
        previews: {},
        option: {
          img: '',
          zoom: 0
        },
        example2: {
          info: true,
          size: 1,
          canScale: false,
          autoCrop: true,
          // 只有自动截图开启 宽度高度才生效
          autoCropWidth: 300,
          autoCropHeight: 250,
          // 开启宽度和高度比例
          fixed: true
        },
        userId: '',
        token: '',
        username: '',
        phone: '',
        email: '',
        userTxt: '',
        phoneTxt: '',
        emailTxt: '',
        userOperate: '',
        phoneOperate: '',
        emailOperate: '',
        editNickShow: false,
        nickVer: {
          nick: ''
        },
        nickVerRules: {
          nick: [
            {validator: vNick, trigger: 'change, blur'}
          ]
        },
        popupOpen2: false,
        popupTitle2: '',
        sendAuthCode: true,
        authTime: 60,
        verVerCode: false,
        popupOpen3: false,
        popupTitle3: '',
        sendAuthCode1: false,
        authTime1: 60,
        verPhoneShow: false,
        VPhone: '',
        Ver: {
          verCode: ''
        },
        VerRules: {
          verCode: [
            {validator: vCode, trigger: 'change, blur'}
          ]
        },
        verNick: false,
        vvCode: false,
        editPhoneShow: false,
        phoneVer: {
          phone: '',
          verCode: ''
        },
        phoneRules: {
          phone: [
            {validator: vPhone, trigger: 'change,blur'}
          ],
          verCode: [
            {validator: vVerCode, trigger: 'change, blur'}
          ]
        },
        verPhone: false,
        sendAuth: true,
        sendTime: 60,
        codeV: false,
        code: '',
        codePhone: '',
        authTimetimer: '',
        authTimer: '',
        editPassShow: false,
        passVer: {
          oldPass: '',
          newPass: '',
          rePass: ''
        },
        passRules: {
          oldPass: [
            {validator: vOldPass, trigger: 'blur'}
          ],
          newPass: [
            {validator: vNewPass, trigger: 'change,blur'}
          ],
          rePass: [
            {validator: vRePass, trigger: 'change,blur'}
          ]
        },
        vOPass: false,
        vNPass: false,
        vRPass: false,
        verEmailOneShow: false,
        VerEmailOne: {
          email: '',
          verCode: ''
        },
        VerEmailOneRules: {
          verCode: [
            {validator: vEmailOneCode, trigger: 'change,blur'}
          ]
        },
        sendEmailAuthCodeOne: true,
        authEmailOneTimer: '',
        authEmailOneTime: 60,
        verEmailOneCode: '',
        vEmailOne: false,
        verEmailTwoShow: false,
        VerEmailTwo: {
          email: '',
          verCode: ''
        },
        VerEmailTwoRules: {
          email: [
            {validator: vEmailTwoEmail, trigger: 'change,blur'}
          ],
          verCode: [
            {validator: vEmailTwoCode, trigger: 'change,blur'}
          ]
        },
        sendEmailAuthCodeTwo: true,
        authEmailTwoTimer: '',
        authEmailTwoTime: 60,
        verEmailTwoCode: false,
        verEmailTwoEmail: false,
        vVerCode: '',
        twoCode: '',
        existEmail: false,
        codeEmail: ''
      }
    },
    computed: {
      ...mapState(['userInfo'])
    },
    methods: {
      ...mapMutations([
        'RECORD_USERINFO'
      ]),
      message (m) {
        this.$message(m)
      },
      messageSuccess (m) {
        this.$message({
          message: m,
          type: 'success'
        })
      },
      messageFail (m) {
        this.$message.error({
          message: m
        })
      },
      upimg (e) {
        let file = e.target.files[0]
        if (file.size > 1048576) {
          this.messageFail('图片大小不得超过1Mb')
          return false
        }
        if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG)$/.test(e.target.value)) {
          this.messageFail('图片类型仅支持.gif,jpeg,jpg,png,bmp')
          return false
        }
        var reader = new FileReader()
        reader.readAsDataURL(file)
        reader.onload = (e) => {
          this.option.img = e.target.result
        }
      },
      // 上传图片
      cropper () {
        this.message('上传中...')
        if (this.option.img) {
          this.$refs.cropper.getCropData((data) => {
            this.imgSrc = data
            uploadImg({userId: this.userId, token: this.token, imgData: data}).then(res => {
              if (res.status === 200) {
                let path = res.result
                let info = this.userInfo
                info.file = path
                this.RECORD_USERINFO({info: info})
                this.editAvatarShow = false
                this.messageSuccess('上传头像成功')
              } else {
                this.messageFail(res.message)
              }
            })
          })
        } else {
          this.messageFail('请先选择头像')
        }
      },
      // 编辑头像
      editAvatar () {
        this.editAvatarShow = true
      },
      realTime (data) {
        this.previews = data
        let w = 100 / data.w
        this.option.zoom = w
      },
      // 设置用户信息
      setUserInfo () {
        let username = this.userInfo.info.username
        let phone = this.userInfo.info.phone
        let email = this.userInfo.info.email
        this.username = username
        this.phone = phone
        this.email = email
        if (username === null || username === '') {
          this.userTxt = '未设置。'
          this.userOperate = '现在设置'
        } else {
          this.userTxt = username
          this.userOperate = '修改'
        }
        if (phone === null || phone === '') {
          this.phoneTxt = '未设置。'
          this.phoneOperate = '现在绑定'
        } else {
          let phoneTxt = '您验证的手机：' + phone.substring(0, 3).concat('****').concat(phone.substring(7, 11))
          this.phoneTxt = phoneTxt
          this.phoneOperate = '修改'
        }
        if (email === null || email === '') {
          this.emailTxt = '未设置。'
          this.emailOperate = '现在绑定'
        } else {
          this.emailTxt = email
          this.emailOperate = '修改'
        }
      },
      // 修改用户名
      updateUsername () {
        this.$refs['nickVer'].resetFields()
        let username = this.username
        this.nickVer.nick = username
        this.editNickShow = true
      },
      // 保存用户名
      saveUsername (params) {
        this.editNickShow = false
        updateUsername(params).then(res => {
          if (res.status === 200) {
            let username = res.result.username
            this.RECORD_USERINFO({info: res.result})
            this.userTxt = username
            this.username = username
            this.messageSuccess('修改昵称成功')
          } else {
            this.messageFail(res.message)
          }
        })
      },
      // 发送验证码
      sendVerCode () {
        let phone = this.phoneVer.phone
        this.sendAuth = false
        this.authTimer = setInterval(() => {
          this.sendTime--
          if (this.sendTime <= 0) {
            this.sendAuth = true
            clearInterval(this.authTimer)
          }
        }, 1000)
        vercode({
          phone
        }).then(res => {
          this.code = res.result
          this.codePhone = phone
        })
      },
      // 发送第一次验证手机号
      sendCode () {
        let phone = this.phone
        this.sendAuthCode = false
        this.authTimetimer = setInterval(() => {
          this.authTime--
          if (this.authTime <= 0) {
            this.sendAuthCode = true
            clearInterval(this.authTimetimer)
          }
        }, 1000)
        vercode({
          phone
        }).then(res => {
          console.log(res.result)
          this.verCode = res.result
        })
      },
      // 修改手机号
      updatePhone () {
        this.$refs['Ver'].resetFields()
        let phone = this.phone.substring(0, 3).concat('****').concat(this.phone.substring(7, 11))
        this.vvCode = false
        this.authTime = 60
        this.VPhone = phone
        this.Ver.verCode = ''
        this.verPhoneShow = true
        this.sendCode()
      },
      // 安全验证通过，下一步
      verNext () {
        this.$refs['phoneVer'].resetFields()
        this.vvCode = false
        this.Ver.verCode = ''
        this.authTime = 60
        this.verPhoneShow = false
        this.editPhoneShow = true
        clearInterval(this.authTimetimer)
      },
      // 向服务器发起修改手机号请求
      succPhone (params) {
        updatePhone(params).then(res => {
          if (res.status === 200) {
            let phone = res.result.phone
            this.RECORD_USERINFO({info: res.result})
            this.phoneVer.phone = ''
            this.phoneVer.verCode = ''
            this.sendAuth = true
            this.phone = phone
            this.phoneTxt = '您验证的手机：' + phone.substring(0, 3).concat('****').concat(phone.substring(7, 11))
            this.editPhoneShow = false
            clearInterval(this.authTimer)
            this.messageSuccess('修改手机号成功')
          } else {
            this.editPhoneShow = false
            this.messageFail(res.message)
          }
        })
        this.sendTime = 60
      },
      // 打开修改密码
      openPass () {
        this.$refs['passVer'].resetFields()
        this.editPassShow = false
        this.passVer.oldPass = ''
        this.passVer.newPass = ''
        this.passVer.rePass = ''
        this.vOPass = false
        this.vNPass = false
        this.vRPass = false
        this.editPassShow = true
      },
      // 修改密码
      updatePass (params) {
        updatePass(params).then(res => {
          this.editPassShow = false
          this.passVer.oldPass = ''
          this.passVer.newPass = ''
          this.passVer.rePass = ''
          this.vOPass = false
          this.vNPass = false
          this.vRPass = false
          if (res.status === 200) {
            this.RECORD_USERINFO({info: res.result})
            this.messageSuccess('修改密码成功')
            let params = {
              params: {
                token: this.token
              }
            }
            logout(params).then(res => {
              removeStore('token')
              removeStore('userId')
              removeStore('userInfo')
              removeStore('buyCart')
              window.location.href = '/login'
            })
          } else {
            this.editPassShow = false
            this.messageFail(res.message)
          }
        })
      },
      // 打开修改邮箱
      openEmail () {
        this.$refs['VerEmailTwo'].resetFields()
        let email = this.userInfo.info.email
        if (email === null || email === '') {
          this.verEmailTwoShow = true
          this.VerEmailTwo.email = this.userInfo.info.email
          this.VerEmailTwo.verCode = ''
          this.authEmailTwoTimer = ''
          this.authEmailTwoTime = 60
          this.verEmailTwoCode = ''
          this.vEmailTwo = false
          clearInterval(this.authEmailTwoTimer)
        } else {
          this.verEmailOneShow = true
          this.VerEmailOne.email = this.userInfo.info.email
          this.VerEmailOne.verCode = ''
          this.authEmailOneTimer = ''
          this.authEmailOneTime = 60
          this.verEmailOneCode = ''
          this.vEmailOne = false
          clearInterval(this.authEmailOneTimer)
          this.sendEmailOne()
        }
      },
      // 发送第一次安全验证邮件
      sendEmailOne () {
        let email = this.VerEmailOne.email
        this.sendEmailAuthCodeOne = false
        this.authEmailOneTime = 60
        this.authEmailOneTimer = setInterval(() => {
          this.authEmailOneTime--
          if (this.authEmailOneTime <= 0) {
            this.sendEmailAuthCodeOne = true
            clearInterval(this.authEmailOneTimer)
            this.authTime = 60
          }
        }, 1000)
        let params = {
          params: {
            email: email
          }
        }
        sendEmailCode(params).then(res => {
          this.verEmailOneCode = res.result
        })
      },
      // 邮箱安全验证通过，下一步
      verEmailNext () {
        this.$refs['VerEmailTwo'].resetFields()
        this.verEmailOneShow = false
        this.verEmailTwoShow = true
      },
      // 更换邮箱第二次验证
      sendEmailTwo () {
        let email = this.VerEmailTwo.email
        this.sendEmailAuthCodeTwo = false
        this.authEmailTwoTime = 60
        this.authEmailTwoTimer = setInterval(() => {
          this.authEmailTwoTime--
          if (this.authEmailTwoTime <= 0) {
            this.sendEmailAuthCodeTwo = true
            clearInterval(this.authEmailTwoTimer)
            this.authEmailTwoTime = 60
          }
        }, 1000)
        let params = {
          params: {
            email: email
          }
        }
        sendEmailCode(params).then(res => {
          this.twoCode = res.result
          this.codeEmail = email
        })
      },
      // 更换邮箱
      _updateEmail (params) {
        updateEmail(params).then(res => {
          this.verEmailTwoShow = false
          this.VerEmailTwo.email = ''
          this.VerEmailTwo.verCode = ''
          this.sendEmailAuthCodeTwo = false
          this.authEmailTwoTimer = ''
          this.authEmailTwoTime = 60
          this.verEmailTwoCode = false
          this.verEmailTwoEmail = false
          this.vVerCode = ''
          this.twoCode = ''
          this.existEmail = false
          this.codeEmail = ''
          if (res.status === 200) {
            this.RECORD_USERINFO({info: res.result})
            this.emailTxt = res.result.email
            this.messageSuccess('修改邮箱成功')
          } else {
            this.messageFail(res.message)
          }
        })
      }
    },
    created () {
      this.userId = getStore('userId')
      this.token = getStore('token')
      this.setUserInfo()
    },
    components: {
      YButton,
      YShelf,
      vueCropper,
      YPopup
    }
  }
</script>
<style lang="scss">
  @import "../../../assets/style/mixin";

  .avatar-box {
    height: 124px;
    display: flex;
    margin: 0 30px 30px;
    border-bottom: #dadada solid 1px;
    line-height: 30px;
    display: flex;
    align-items: center;
    .img-box {
      @include wh(80px);
      border-radius: 5px;
      overflow: hidden;
    }
    img {
      display: block;
      @include wh(100%)
    }
    .r-box {
      margin-left: 20px;
      h3 {
        font-size: 18px;
        font-weight: 400;
        color: #333;
      }
    }
  }

  .safe-center-title {
    font-size: 18px;
    font-weight: 400;
    color: #333;
  }

  .status-box {
    position: relative;
    height: 60px;
    padding-bottom: 30px;
    margin: 0 30px 30px;
    line-height: 30px;
  }

  .status-box p {
    clear: both;
    width: 420px;
    color: #999;
    float: left;
  }

  .safe-todo {
    position: relative;
    left: 348px;
    margin-top: -30px;
    text-align: right;
  }

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

  // 修改头像
  .edit-avatar {
    z-index: 9999;
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
      @include wh(700px, 500px);
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

  // 修改昵称
  .edit-nick {
    z-index: 9999;
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
      @include wh(500px, 262px);
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

  // 修改手机号
  .edit-phone {
    z-index: 9999;
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
      @include wh(500px, 322px);
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

  // 修改密码
  .edit-pass {
    z-index: 9999;
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
      @include wh(500px, 400px);
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

  .image-container {
    width: 100px;
    height: 100px;
    border: 1px solid #ccc;
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

  .big {
    display: block;
    width: 320px;
    height: 320px;
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

  .register-form .el-input__inner {
    height: 50px;
  }
</style>
