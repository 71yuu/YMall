<template>
  <div class="footer">
    <div class="container">
      <div class="copyright">
        <h4 class="content-c2">Copyright ©2019 <a href="https://www.71yuu.com/" target="_blank">71yuu.com</a> All Rights Reserved.本网站设计内容大部分属锤子科技</h4>
        <ul class="privacy">
          <li class="content-c1"><a class="content-c0" @click="open1">法律声明</a></li>
          <li class="content-c1"><a class="content-c0" @click="open2">隐私条款</a></li>
          <li class="content-c1"><a class="content-c0" target="_blank" href="https://github.com/71yuu">开发者中心</a></li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
      }
    },
    methods: {
      open1 () {
        this.$notify.info({
          title: '法律声明',
          message: '不承担任何法律问题~'
        })
      },
      open2 () {
        this.$notify.info({
          title: '隐私条款',
          message: '您在本网站将没有任何隐私~'
        })
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .footer {
    border-top: 1px solid #e6e6e6;
    background: #fafafa;
    margin-top: 60px;
    height: 171px;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 41px;
    padding-left: 150px;
  }

  .container {
    width: 1220px;
  }

  .copyright {
    text-align: center;
    color: #434d55;
    font-size: 20px;
    padding: 40px 0 0;
    display: flex;
    align-items: left;
    margin:0 auto;
  }

  .privacy {
    float: left;
    margin: 0 0 0 12px;
  }

  .content-c0 {
    color: #5079d9;
    cursor: pointer;
    text-decoration: none;
    &:hover {
      color: #3A5FCD;
    }
  }

  .content-c1 {
    float: left;
    line-height: 12px;
    padding: 1px 10px 0;
    border-left: 1px solid #ccc;
  }

  .content-c2 {
    float: left;
    height: 15px;
    line-height: 15px;
    color: #757575;
  }

  .cop {
    clear: both;
    padding: 10px 0 0;
    height: 15px;
  }

  .content-c3 {
    margin-right: 20px;
    color: #bdbdbd;
    font-size: 12px;
    height: 12px;
    line-height: 1;
  }
</style>
