import http from './public'
// 提交订单
export const submitOrder = (params) => {
  return http.fetchPost('/api/order/addOrder', params)
}
// 获取订单详情
export const getOrderDet = (params) => {
  return http.fetchGet('/api/order/getOrderDet', params)
}
// 订单支付
export const payment = (params) => {
  return http.fetchPost('/api/order/payment', params)
}
// 获取订单支付状态
export const getOrderStatus = (params) => {
  return http.fetchGet('/api/order/getOrderStatus', params)
}
// 获取会员订单
export const getOrderList = (params) => {
  return http.fetchGet('/api/order/orderList', params)
}
// 确认收货
export const confirmReceipt = (params) => {
  return http.fetchPost('/api/order/confirmReceipt', params)
}
// 删除订单
export const deleteOrder = (params) => {
  return http.fetchPost('/api/order/deleteOrder', params)
}
// 取消订单
export const cancelOrder = (params) => {
  return http.fetchPost('/api/order/cancelOrder', params)
}
