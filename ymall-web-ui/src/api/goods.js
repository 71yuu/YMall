import http from './public'
// 商品列表
export const getAllGoods = (params) => {
  return http.fetchGet('/api/goods/getCategoryGoods', params)
}
// 商品详情
export const productDet = (params) => {
  return http.fetchGet('/api/goods/productDet', params)
}
// 商品列表
export const getSearch = (params) => {
  return http.fetchGet('/api/goods/search', params)
}
// 快速搜索
export const getQuickSearch = (params) => {
  return http.fetchGet('/api/goods/quickSearch', params)
}
