import Vue from 'vue'
import Router from 'vue-router'
const Index = () => import('/page/index.vue')
const Login = () => import('/page/Login/login.vue')
const Register = () => import('/page/Login/register.vue')
const forgetPassword = () => import('/page/Login/forgetPassword.vue')
const Home = () => import('/page/Home/home.vue')
const GoodS = () => import('/page/Goods/goods.vue')
const goodsDetails = () => import('/page/Goods/goodsDetails.vue')
const Cart = () => import('/page/Cart/cart.vue')
const order = () => import('/page/Order/order.vue')
const user = () => import('/page/User/user.vue')
const orderList = () => import('/page/User/children/order.vue')
const information = () => import('/page/User/children/information.vue')
const addressList = () => import('/page/User/children/addressList.vue')
const checkout = () => import('/page/Checkout/checkout.vue')
const payment = () => import('/page/Order/payment.vue')
const paysuccess = () => import('/page/Order/paysuccess.vue')
const Search = () => import('/page/Search/search.vue')
const RefreshSearch = () => import('/page/Refresh/refreshsearch.vue')
const orderDetail = () => import('/page/User/children/orderDetail.vue')
Vue.use(Router)
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Index,
      name: 'index',
      redirect: '/home',
      children: [
        {path: 'home', component: Home},
        {path: 'goods', component: GoodS},
        {path: 'search', name: 'search', component: Search},
        {path: 'goodsDetails', name: 'goodsDetails', component: goodsDetails}
      ]
    },
    {path: '/login', name: 'login', component: Login},
    {path: '/register', name: 'register', component: Register},
    {path: '/forgetPassword', name: 'forgetPassword', component: forgetPassword},
    {path: '/cart', name: 'cart', component: Cart},
    {path: '/refreshsearch', name: 'refreshsearch', component: RefreshSearch},
    {
      path: '/order',
      name: 'order',
      component: order,
      children: [
        {path: 'paysuccess', name: 'paysuccess', component: paysuccess},
        {path: 'payment', name: 'payment', component: payment}
      ]
    },
    {
      path: '/user',
      name: 'user',
      component: user,
      redirect: '/user/orderList',
      children: [
        {path: 'orderList', name: '订单列表', component: orderList},
        {path: 'orderDetail', name: '订单详情', component: orderDetail},
        {path: 'information', name: '账户资料', component: information},
        {path: 'addressList', name: '收货地址', component: addressList}
      ]
    },
    {path: '/checkout', name: 'checkout', component: checkout},
    {path: '*', redirect: '/home'}
  ]
})
