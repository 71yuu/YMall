import http from './public'
// 登陆
export const memberLogin = (params) => {
  return http.fetchPost('/api/member/login', params)
}
// 极验验证码
export const geetest = (params) => {
  return http.fetchGet('/api/member/geetestInit?t=' + (new Date()).getTime(), params)
}
// 阿里验证码
export const vercode = (params) => {
  return http.fetchGet('/api/member/sendsms/' + params.phone, null)
}
// 验证手机号是否存在
export const checkPhone = (params) => {
  return http.fetchGet('/api/member/checkphone/' + params.value, null)
}
// 验证邮箱是否存在
export const checkEmail = (params) => {
  return http.fetchGet('/api/member/checkEmail', params)
}
// 验证手机号或邮箱是存在
export const checkAccount = (params) => {
  return http.fetchGet('/api/member/checkAccount', params)
}
// 发送手机或邮箱验证码
export const forgetVerCode = (params) => {
  return http.fetchGet('/api/member/forgetVerCode', params)
}
// 修改密码
export const updatePassword = (params) => {
  return http.fetchPost('/api/member/updatePassword', params)
}
// 用户信息
export const userInfo = (params) => {
  return http.fetchGet('/api/member/checkLogin', params)
}
// 注册账号
export const register = (params) => {
  return http.fetchPost('/api/member/register', params)
}
// 首页接口
export const productHome = (params) => {
  return http.fetchGet('/api/goods/home', params)
}
// 获取分类信息
export const cateList = (params) => {
  return http.fetchGet('/api/goods/cateList', params)
}
// 推荐板块
export const recommend = (params) => {
  return http.fetchGet('/api/goods/recommend', params)
}

