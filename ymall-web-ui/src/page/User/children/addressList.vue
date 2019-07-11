<template>
  <div>
    <y-shelf title="收货地址">
      <span slot="right"><y-button text="添加收货地址" style="margin: 0" @btnClick="update()"></y-button></span>
      <div slot="content">
        <!--标题-->
        <div class="table-title">
          <span class="name">姓名</span> <span class="address">详细地址</span> <span class="tel">电话</span>
        </div>
        <div v-if="addList.length">
          <div class="address-item" v-for="(item,i) in addList" :key="i">
            <div class="name">{{item.userName}}</div>
            <div class="address-msg">{{item.detailsAddress}}</div>
            <div class="telephone">{{item.tel}}</div>
            <div class="defalut">
              <a @click="changeDef(item)"
                 href="javascript:;"
                 v-text="item.isDefault?'( 默认地址 )':'设为默认'"
                 :class="{'defalut-address':item.isDefault}"></a>
            </div>
            <div class="operation">
              <el-button type="primary" icon="edit" size="small"  @click="update(item)"></el-button>
              <el-button type="danger" icon="delete" size="small" :data-id="item.id" @click="del(item.id,i)"></el-button>
            </div>
          </div>
        </div>
        <div v-else>
          <div style="padding: 80px 0;text-align: center">
            <div style="font-size: 20px">你还未添加收货地址</div>
            <div style="margin: 20px ">
              <y-button text="添加地址" @btnClick="update()"></y-button>
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
  </div>
</template>
<script>
  import { addressList, addressUpdate, addressAdd, addressDel } from '/api/member'
  import YButton from '/components/YButton'
  import YPopup from '/components/popup'
  import YShelf from '/components/shelf'
  import { getStore } from '/utils/storage'
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
        detailedAddress: ''
      }
    },
    computed: {
      btnHighlight () {
        return this.verUserName && this.verPhone && this.addressVer.state && this.addressVer.city && this.addressVer.district && this.verStreetName
      }
    },
    methods: {
      // 错误消息提示
      message (m) {
        this.$message.error({
          message: m
        })
      },
      // 获取会员地址列表
      _addressList () {
        let params = {
          params: {
            userId: this.userId
          }
        }
        addressList(params).then(res => {
          let data = res.result
          if (data.length) {
            this.addList = res.result
          } else {
            this.addList = []
          }
        })
      },
      // 更新会员地址
      _addressUpdate (params) {
        addressUpdate(params).then(res => {
          this._addressList()
        })
      },
      // 新增会员地址
      _addressAdd (params) {
        addressAdd(params).then(res => {
          if (res.status === 200) {
            this._addressList()
          } else {
            this.message(res.message)
          }
        })
      },
      // 修改默认地址
      changeDef (item) {
        item.userId = this.userId
        if (!item.isDefault) {
          item.isDefault = true
          this._addressUpdate(item)
        }
        return false
      },
      // 保存会员地址
      save (p) {
        this.editAddressShow = false
        if (p.id) {
          this._addressUpdate(p)
        } else {
          delete p.id
          this._addressAdd(p)
        }
      },
      // 删除会员地址
      del (addressId, i) {
        addressDel({id: addressId}).then(res => {
          if (res.status === 200) {
            this.addList.splice(i, 1)
          } else {
            this.message('删除失败')
          }
        })
      },
      // 修改会员地址
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
      }
    },
    created () {
      this.userId = getStore('userId')
      this._addressList()
    },
    components: {
      YButton,
      YPopup,
      YShelf
    }
  }
</script>
<style scoped lang="scss">
  @import "../../../assets/style/mixin";

  .el-cascader-menus {
    z-index: '9999!important';
  }

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

  .table-title {
    position: relative;
    z-index: 1;
    line-height: 38px;
    height: 38px;
    padding: 0 0 0 38px;
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
      float: left;
      text-align: center;
      color: #838383;
    }
    .address {
      margin-left: 115px;
    }
    .tel {
      margin-left: 195px;
    }
  }

  .address-item {
    display: flex;
    align-items: center;
    height: 115px;
    text-align: center;
    .name {
      width: 106px;
    }
    .address-msg {
      flex: 1;
    }
    .telephone {
      width: 160px;
    }
    .defalut {
      width: 80px;
      > a {
        text-align: center;
        /*display: none;*/
      }
    }
    .operation {
      width: 135px;
      a {
        padding: 10px 5px;
      }
    }
    &:hover {
      .defalut > a {
        display: block;
      }
    }
  }

  .address-item + .address-item {
    border-top: 1px solid #CFCFCF;
  }

  .defalut-address {
    color: #626262;
    display: block;
    pointer-events: none;
    cursor: default;
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
</style>
