<template>
  <div>
    <y-shelf title="我的订单">
      <div slot="content">
        <div v-loading="loading" element-loading-text="加载中..." v-if="orderList.length" style="min-height: 10vw;">
          <div v-for="(item,i) in orderList" :key="i">
            <div class="gray-sub-title cart-title">
              <div class="first">
                <div>
                  <span class="date" v-text="item.createDate"></span>
                  <span class="order-id"> 订单号： <a @click="orderDetail(item.orderId)">{{item.orderId}}</a> </span>
                </div>
                <div class="f-bc">
                  <span class="price">单价</span>
                  <span class="num">数量</span>
                  <span class="operation">商品操作</span>
                </div>
              </div>
              <div class="last">
                <span class="sub-total">实付金额</span>
                <span class="order-detail"> <a @click="orderDetail(item.orderId)">查看详情 ><em class="icon-font"></em></a> </span>
              </div>
            </div>
            <div class="pr">
              <div class="cart" v-for="(good,j) in item.goodsList" :key="j">
                <div class="cart-l" :class="{bt:j>0}">
                  <div class="car-l-l">
                    <div class="img-box"><a @click="goodsDetails(good.productId)"><img :src="good.productImg" alt=""></a></div>
                    <div class="ellipsis"><a style="color: #626262;" @click="goodsDetails(good.productId)">{{good.productName}}</a></div>
                  </div>
                  <div class="cart-l-r">
                    <div>¥ {{Number(good.salePrice).toFixed(2)}}</div>
                    <div class="num">{{good.productNum}}</div>
                    <div class="type">
                      <el-button style="margin-left:20px" @click="_delOrder(item.orderId,i,item.orderStatus)" :disabled="item.orderStatus === 2 ? true : false" type="danger" size="small" v-if="j<1" class="del-order">{{getOperate(item.orderStatus)}}</el-button>
                    </div>
                  </div>
                </div>
                <div class="cart-r">
                  <span></span>
                  <span></span>
                </div>
              </div>
              <div class="prod-operation pa" style="right: 0;top: 0;">
                <div class="total">¥ {{item.orderTotal}}</div>
                <div v-if="item.orderStatus === '0'">
                  <el-button @click="orderPayment(item.orderId)" type="primary" size="small">现在付款</el-button>
                </div>
                <div class="status" v-if="item.orderStatus !== '0'"> {{getOrderStatus(item.orderStatus)}}  </div>
              </div>
            </div>
          </div>
        </div>
        <div v-loading="loading" element-loading-text="加载中..." class="no-info" v-else>
          <div style="padding: 100px 0;text-align: center">
            你还未创建过订单
          </div>
        </div>
      </div>
    </y-shelf>
    <div style="float:right">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import YShelf from '/components/shelf'
  import { getStore } from '/utils/storage'
  import { getOrderList, confirmReceipt, deleteOrder } from '/api/order'

  export default {
    data () {
      return {
        userId: '',
        currentPage: 1,
        pageSize: 5,
        orderList: '',
        loading: true,
        total: 0
      }
    },
    methods: {
      // 确认删除
      confirmDelete (orderId) {
        this.$confirm('您确认删除订单号为' + orderId + '的订单吗？此操作不可恢复', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteOrder({orderId: orderId, userId: this.userId}).then(res => {
            if (res.status === 200) {
              this._orderList()
              this.messageSuccess(res.message)
            } else {
              this.messageFail(res.message)
            }
          })
        })
      },
      // 成功消息提示
      messageSuccess (m) {
        this.$message({
          message: m,
          type: 'success'
        })
      },
      // 失败消息提示
      messageFail (m) {
        this.$message.error({
          message: m
        })
      },
      // 获取订单列表
      _orderList () {
        let params = {
          params: {
            userId: this.userId,
            page: this.currentPage,
            size: this.pageSize
          }
        }
        getOrderList(params).then(res => {
          this.orderList = res.result.data
          this.total = res.result.total
          this.loading = false
        })
      },
      // 更改分页大小
      handleSizeChange (val) {
        this.pageSize = val
        this._orderList()
      },
      // 更改当前页数
      handleCurrentChange (val) {
        this.currentPage = val
        this._orderList()
      },
      // 获取订单状态
      getOrderStatus (status) {
        if (status === 0) {
          return '待支付'
        } else if (status === 1) {
          return '已付款'
        } else if (status === 2) {
          return '待发货'
        } else if (status === 3) {
          return '已发货'
        } else if (status === 4) {
          return '交易成功'
        } else if (status === 5) {
          return '交易失败'
        } else {
          return '交易失败'
        }
      },
      // 获取订单操作
      getOperate (status) {
        if (status === 0) {
          return '去支付'
        } else if (status === 1 || status === 2 || status === 3) {
          return '确认收货'
        } else if (status === 4 || status === 5) {
          return '删除订单'
        } else {
          return '交易失败'
        }
      },
      // 操作订单
      _delOrder (orderId, i, status) {
        if (status === 0) {
          this.$router.push({
            path: '/order/payment',
            query: {
              'orderId': orderId
            }
          })
        } else if (status === 1 || status === 2 || status === 3) {
          console.log('确认收货')
          confirmReceipt({orderId: orderId, userId: this.userId}).then(res => {
            if (res.status === 200) {
              this._orderList()
              this.messageSuccess(res.message)
            } else {
              this.messageFail(res.message)
            }
          })
        } else if (status === 4 || status === 5) {
          console.log('删除订单')
          this.confirmDelete(orderId)
          return '删除订单'
        } else {
          return '交易失败'
        }
      },
      // 查看订单详情
      goodsDetails (productId) {
        this.$router.push({
          path: '/goodsDetails',
          query: {
            productId: productId
          }
        })
      },
      // 查看详情
      orderDetail (orderId) {
        this.$router.push({
          path: '/user/orderDetail',
          query: {
            orderId: orderId
          }
        })
      }
    },
    created () {
      this.userId = getStore('userId')
      this._orderList()
    },
    components: {
      YShelf
    }
  }
</script>
<style lang="scss" scoped>
  @import "../../../assets/style/mixin";

  .gray-sub-title {
    height: 38px;
    padding: 0 24px;
    background: #EEE;
    border-top: 1px solid #DBDBDB;
    border-bottom: 1px solid #DBDBDB;
    line-height: 38px;
    font-size: 12px;
    color: #666;
    display: flex;
    span {
      display: inline-block;
      height: 100%;
    }
    .first {
      display: flex;
      justify-content: space-between;
      flex: 1;
      .f-bc {
        > span {
          width: 112px;
          text-align: center;
        }
      }
    }
    .last {
      width: 230px;
      text-align: center;
      display: flex;
      border-left: 1px solid #ccc;
      span {
        flex: 1;
      }
    }
  }

  .bt {
    border-top: 1px solid #EFEFEF;
  }

  .date {
    padding-left: 0px;
  }

  .order-id {
    margin-left: 25px;
  }

  .cart {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 24px;
    &:hover {
      .del-order {
        display: block;
      }
    }
    .del-order {
      display: none;
    }
    .cart-l {
      display: flex;
      align-items: center;
      flex: 1;
      padding: 15px 0;
      justify-content: space-between;
      position: relative;
      &:before {
        position: absolute;
        content: ' ';
        right: -1px;
        top: 0;
        width: 1px;
        background-color: #EFEFEF;
        height: 100%;
      }
      .ellipsis {
        margin-left: 20px;
        width: 220px;
      }
      .img-box {
        border: 1px solid #EBEBEB;
      }
      img {
        display: block;
        @include wh(80px);
      }
      .cart-l-r {
        display: flex;
        > div {
          text-align: center;
          width: 112px;
        }
      }
      .car-l-l {
        display: flex;
        align-items: center;
      }
    }
    .cart-r {
      width: 230px;
      display: flex;
      span {
        text-align: center;
        flex: 1;
      }
    }
  }

  .prod-operation {
    height: 110px;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 254px;
    div {
      width: 100%;
      text-align: center;
    }
    div:last-child {
      padding-right: 24px;
    }
  }
</style>
