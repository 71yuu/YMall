import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/'
import VueLazyload from 'vue-lazyload'
import infiniteScroll from 'vue-infinite-scroll'
import VueCookie from 'vue-cookie'
// import PicZoom from 'vue-piczoom'
import { userInfo } from './api'
import { Button, Pagination, Checkbox, Col, Menu, Submenu, MenuItem, MenuItemGroup, Icon, Autocomplete, Loading, Message, Notification, Steps, Step, Form, FormItem, Table, TableColumn, Input, Dialog, Select, Option, MessageBox, Cascader } from 'element-ui'
import { getStore } from './utils/storage'
import VueContentPlaceholders from 'vue-content-placeholders'
Vue.use(VueContentPlaceholders)
Vue.use(Button)
Vue.use(Pagination)
Vue.use(Checkbox)
Vue.use(Col)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(MenuItemGroup)
Vue.use(Icon)
Vue.use(Autocomplete)
Vue.use(Steps)
Vue.use(Step)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Input)
Vue.use(Dialog)
Vue.use(Select)
Vue.use(Option)
Vue.use(Cascader)
Vue.component(MessageBox)
Vue.use(Loading.directive)
Vue.prototype.$loading = Loading.service
Vue.prototype.$notify = Notification
Vue.prototype.$message = Message
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$confirm = MessageBox.confirm
Vue.use(infiniteScroll)
Vue.use(VueCookie)
// Vue.use(PicZoom)
Vue.use(VueLazyload, {
  // preLoad: 1.3,
  // error: 'dist/error.png',
  loading: '/static/images/load.gif'
  // attempt: 1
})
Vue.config.productionTip = false
const whiteList = ['/home', '/goods', '/login', '/register', '/forgetPassword', '/goodsDetails', '/search', '/cart', '/refreshsearch', '/refreshgoods'] // 不需要登陆的页面
router.beforeEach(function (to, from, next) {
  let params = {
    params: {
      token: getStore('token')
    }
  }
  userInfo(params).then(res => {
    if (res.status === 500) { // 没登录
      if (whiteList.indexOf(to.path) !== -1) { // 白名单
        next()
      } else {
        next('/login')
      }
    } else {
      store.commit('RECORD_USERINFO', {info: res.result})
      if (to.path === '/login') { //  跳转到
        next({path: '/'})
      }
      next()
    }
  })
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
