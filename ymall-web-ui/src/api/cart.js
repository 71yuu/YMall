import http from './public'
// 添加商品到购物车
export const addCartProduct = (params) => {
  return http.fetchPost('/api/member/cart/addProduct', params)
}
// 获取购物车列表
export const getCartList = (params) => {
  return http.fetchGet('/api/member/cart/getCartList', params)
}
// 删除购物车商品
export const delCartProduct = (params) => {
  return http.fetchPost('/api/member/cart/delProduct', params)
}
// 修改购物车商品
export const editCartProduct = (params) => {
  return http.fetchPost('/api/member/cart/editProduct', params)
}
// 编辑全选购物车
export const editCheckAll = (params) => {
  return http.fetchPost('/api/member/cart/editCheckAll', params)
}
// 删除购物车已选中
export const delCartChecked = (params) => {
  return http.fetchPost('/api/member/cart/delChecked', params)
}
