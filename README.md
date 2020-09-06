## YMall

## 开发环境

- 操作系统：Windows 10 Enterprise
- 开发工具：Intellij IDEA
- 数据库：MySQL 8.0.13
- Java SDK：Oracle JDK 1.8.152

## 项目管理工具

- 项目构建：Maven
- 代码管理：Git

## 后台主要技术栈

- 核心框架：Spring + Spring MVC + MyBatis
- 数据库连接池：Alibaba Druid
- 数据库缓存：Redis
- 接口文档引擎：Swagger2 RESTful 风格 API 文档生成
- 全文检索引擎：Elasticsearch
- 系统任务调度：Quartz

## 前后分离

- 前端框架：NodeJS + Vue + Axios
- 前端模板：ElementUI

## 项目截图

### YMall 商城前台

- 首页

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/首页.png)


![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/首页-1.png)

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/首页-2.png)

- 分类商品页

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/分类商品页.png)

- 商品详情页

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/商品详情页-1.png)

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/商品详情页-2.png)

- 购物车

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/购物车.png)

- 下单

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/下单-1.png)

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/下单-2.png)

- 支付

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/支付-1.png)

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/支付-2.png)

- 会员中心-我的订单

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/会员中心-1.png)

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/会员中心-1-1.png)

- 会员中心-账号资料

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/会员中心-2.png)

- 会员中心-修改地址

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/会员中心-3.png)

- 注册

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/注册.png)

- 登录

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/登录.png)

- 忘记密码

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/忘记密码.png)



### YMall 商城后台

- 首页

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/后台首页.png)

## 项目部署
### 1. 运行项目所需环境

- JDK 1.8
- IDEA
- MySQL 5.5 以上
- Tomcat 8
- Elasticsearch 5.6
- Redis

确保你已经安装上述环境，以上安装教程可自行百度...，安装记得修改 `ymall-web-api` 和 `ymall-web-admin` 的 Redis 和 Elasticsearch 的连接地址
![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_2020-09-06_13-13-20.png)

- 还需要七牛云的图片服务器和阿里云沙箱支付的密钥，请自行申请

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_2020-09-06_13-51-23.png)

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_2020-09-06_13-51-45.png)

### 2. 导入项目

- `IDEA` -> `open`

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113094747.png)


- 手动安装 `alipay` 依赖到本地仓库

    因为 Mavan 中央仓库没有 alipay 的依赖所以需要手动安装依赖本地仓库，`alipay` 的 jar 文件在 `ymall-dependencies` 下的 `lib` 目录
    
    在 jar 包的目录，执行 Maven 安装依赖的命令：
    ```java
    mvn install:install-file -DgroupId=com.alipay -DartifactId=alipay-trade-sdk -Dversion=20161215 -Dpackaging=jar -Dfile=alipay-trade-sdk-20161215.jar
    ```

- 刷新依赖

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113095010.png)

###　3. 导入 SQL 文件

 sql 文件在 `sql\ymall.sql`

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113103213.png)

### ４. 部署接口项目

  由于前台使用的是前后分离，所以接口项目是给前台 Vue 项目使用的

- 配置 `Tomcat`

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113103534.png)

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113103657.png)



- 选择文件部署

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113104301.png)

- 配置端口号 `9090` JMX port `1100`

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113104354.png)

- 运行

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113104506.png)

### 5. 部署后台项目

后台项目暂时为 SSM + Jsp 项目，后期可能会重构为 VUE 项目

部署方式与接口项目一致，只需要改端口号和 JMX port 即可

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113104946.png)

### 6. 启动前台 vue 项目

- 确保已安装 `node.js`
- `npm install` 安装依赖
- `npm install node-sass` 安装 sass
- `npm run dev` 运行项目

