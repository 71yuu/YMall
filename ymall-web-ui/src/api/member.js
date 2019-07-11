import http from './public'
// 获取会员地址列表
export const addressList = (params) => {
  return http.fetchGet('/api/member/addressList', params)
}
// 修改会员地址
export const addressUpdate = (params) => {
  return http.fetchPost('/api/member/addressUpdate', params)
}
// 新增会员地址
export const addressAdd = (params) => {
  return http.fetchPost('/api/member/addressAdd', params)
}
// 删除会员地址
export const addressDel = (params) => {
  return http.fetchPost('/api/member/addressDel', params)
}
// 会员修改头像
export const uploadImg = (params) => {
  return http.fetchPost('/api/member/uploadImg', params)
}
// 修改会员昵称
export const updateUsername = (params) => {
  return http.fetchPost('/api/member/updateUsername', params)
}
// 修改手机号
export const updatePhone = (params) => {
  return http.fetchPost('/api/member/updatePhone', params)
}
// 修改密码
export const updatePass = (params) => {
  return http.fetchPost('/api/member/updatePass', params)
}
// 退出登陆
export const logout = (params) => {
  return http.fetchGet('/api/member/logout', params)
}
// 更改邮箱发送邮件验证
export const sendEmailCode = (params) => {
  return http.fetchGet('/api/member/sendEmailCode', params)
}
// 修改邮箱
export const updateEmail = (params) => {
  return http.fetchPost('/api/member/updateEmail', params)
}
