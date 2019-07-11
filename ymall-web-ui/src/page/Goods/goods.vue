<template>
  <div class="goods">
    <!-- 查询条件 -->
    <div class="nav">
      <div class="w">
        <a href="javascript:;" :class="{active:sortType===1}" @click="reset">综合排序</a>
        <a href="javascript:;" :class="{active:sortType===2}" @click="sortByOrder">销量排序</a>
        <a href="javascript:;" :class="{active:sortType===3}" @click="sortByPrice(3)">价格从低到高</a>
        <a href="javascript:;" :class="{active:sortType===4}" @click="sortByPrice(4)">价格从高到低</a>
        <div class="price-interval">
          <el-input type="number" class="input" placeholder="价格" v-model="min" style="width:83px;"></el-input>
          <span style="margin: 0 5px"> - </span>
          <el-input type="number" placeholder="价格" v-model="max" style="width:83px;"></el-input>
          <!--<y-button text="确定" :classStyle="min && max ? main-btn : default-btn" @btnClick="_getAllGoods" style="margin-left: 10px;"></y-button>-->
          <el-button type="primary" @click="_getAllGoods" :disabled="min && max && min < max ? false : true" style="margin-left: 10px;">确定</el-button>
        </div>
      </div>
    </div>

      <!-- 查询内容 -->
      <div v-loading="loading" element-loading-text="加载中..." style="min-height: 35vw;">
        <div class="img-item" v-if="!noResult">
          <!-- 商品 -->
          <div class="goods-box w">
            <mall-goods v-for="(item, i) in goods" :key="i" :msg="item"></mall-goods>
          </div>

          <!-- 分页 -->
          <el-pagination
            v-if="!noResult&&!error"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[8, 20, 40, 80]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
        </div>
        <!-- 没有结果 -->
        <div class="no-info" v-if="noResult">
          <div class="no-data">
            <img src="/static/images/no-search.png">
            <br>抱歉！暂时还没有商品
          </div>
          <section class="section">
            <y-shelf :title="recommendPanel.name">
              <div slot="content" class="recommend">
                <mall-goods :msg="item" v-for="(item,i) in recommendPanel.panelContentDtos" :key="i"></mall-goods>
              </div>
            </y-shelf>
          </section>
        </div>
        <!-- 查询错误 -->
        <div class="no-info" v-if="error">
          <div class="no-data">
            <img src="/static/images/error.png">
            <br> 抱歉！出错了...
          </div>
          <section class="section">
            <y-shelf :title="recommendPanel.name">
              <div slot="content" class="recommend">
                <mall-goods :msg="item" v-for="(item,i) in recommendPanel.panelContentDtos" :key="i"></mall-goods>
              </div>
            </y-shelf>
          </section>
        </div>
    </div>
  </div>
</template>
<script>
  import MallGoods from '/components/mallGoods'
  import { recommend } from '/api/index'
  import YShelf from '/components/shelf'
  import { getAllGoods } from '/api/goods'

  export default {
    data () {
      return {
        recommendPanel: {
          name: '',
          panelContentDtos: []
        },
        error: false,
        noResult: false,
        loading: false,
        total: 0,
        goods: [],
        pageSize: 20,
        currentPage: 1,
        sort: '',
        sortType: 1,
        min: '',
        max: ''
      }
    },
    methods: {
      handleSizeChange (val) {
        this.pageSize = val
        this._getAllGoods()
        this.loading = true
      },
      handleCurrentChange (val) {
        this.currentPage = val
        this._getAllGoods()
        this.loading = true
      },
      _getAllGoods () {
        let cid = this.$route.query.category
        let params = {
          params: {
            page: this.currentPage,
            size: this.pageSize,
            sort: this.sort,
            priceGt: this.min,
            priceLte: this.max,
            cid: cid
          }
        }
        getAllGoods(params).then(res => {
          if (res.status === 200) {
            this.total = res.result.total
            this.goods = res.result.data
            this.noResult = false
            if (this.total === 0) {
              this.noResult = true
            }
            this.error = false
          } else {
            this.error = true
          }
          this.loading = false
        })
      },
      // 默认排序
      reset () {
        this.sortType = 1
        this.sort = 1
        this.currentPage = 1
        this.loading = true
        this._getAllGoods()
      },
      // 销量排序
      sortByOrder () {
        this.sortType = 2
        this.sort = 2
        this.currentPage = 1
        this.loading = true
        this._getAllGoods()
      },
      // 价格排序
      sortByPrice (v) {
        this.sortType = v
        this.sort = v
        this.currentPage = 1
        this.loading = true
        this._getAllGoods()
      }
    },
    watch: {
      $route (to, from) {
        if (to.fullPath.indexOf('/goods?category=') >= 0) {
          this.cid = to.query.cid
          this._getAllGoods()
        }
      }
    },
    mounted () {
      this._getAllGoods()
      recommend().then(res => {
        if (res.status === 200) {
          this.recommendPanel = res.result
          this.error = false
        } else {
          this.error = true
        }
      })
    },
    components: {
      MallGoods,
      YShelf
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "../../assets/style/mixin";
  @import "../../assets/style/theme";

  .nav {
    height: 60px;
    line-height: 60px;
    > div {
      display: flex;
      align-items: center;
      a {
        padding: 0 15px;
        height: 100%;
        @extend %block-center;
        font-size: 12px;
        color: #999;
        &.active {
          color: #5683EA;
        }
        &:hover {
          color: #5683EA;
        }
      }
      input {
        @include wh(80px, 30px);
        border: 1px solid #ccc;
      }
      input + input {
        margin-left: 10px;
      }
    }
    .price-interval {
      padding: 0 15px;
      @extend %block-center;
      input[type=number] {
        border: 1px solid #ccc;
        text-align: center;
        background: none;
        border-radius: 5px;
      }
    }
  }

  .goods-box {
    > div {
      float: left;
      border: 1px solid #efefef;
    }
  }

  .no-info {
    padding: 100px 0;
    text-align: center;
    font-size: 30px;
    display: flex;
    flex-direction: column;
    .no-data{
      align-self: center;
    }
  }

  .img-item{
    display: flex;
    flex-direction: column;
  }

  .el-pagination{
    align-self: flex-end;
    margin: 3vw 10vw 2vw;
  }

  .section {
    padding-top: 8vw;
    margin-bottom: -5vw;
    width: 1218px;
    align-self: center;
  }

  .recommend {
    display: flex;
    > div {
      flex: 1;
      width: 25%;
    }
  }



</style>
