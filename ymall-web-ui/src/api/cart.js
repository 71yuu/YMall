import http from './public'
// 添加商品到购物车
export const addCartProduct = (params) => {
  return http.fetchPost('/api/member/cart/addProduct', params)
}
// 获取购物车列表
export const getCartList = (params) => {
  return http.fetchGet('/api/member/cart/getCartList', params)
}
export const delCartProduct = (params) => {
  return http.fetchPost('/api/member/cart/delProduct', params)
}
