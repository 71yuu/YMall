## YMALL 部署文档

### 1. 运行项目所需环境

- JDK 1.8
- Intellij IDEA
- MySQL 5.5 以上
- Tomcat 8
- Maven
- Elasticsearch
- Redis

确保你已经安装上述环境

## 2. 导入项目

- `IDEA` -> `open`

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113094747.png)

- 手动安装 `alipay` 依赖

  手动 `mvn clean` 增加 `alipay` 依赖

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113102543.png)

- 刷新依赖

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113095010.png)

## 2. 导入 SQL 文件

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113103213.png)

## 3. 部署接口项目

- 配置 `Tomcat`

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113103534.png)

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113103657.png)



- 选择文件部署

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113104301.png)

- 配置端口号 `9090` JMX port `1100`

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113104354.png)

- 运行

  ![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113104506.png)

## 4. 部署后台项目

部署方式与接口项目一致，只需要改端口号和 JMX port 即可

![](https://yuu-blog.oss-cn-shenzhen.aliyuncs.com/Yuu_20191113104946.png)

## 5.  启动前台 vue 项目

- 确保已安装 `node.js`
- `npm install` 安装依赖
- `npm run dev` 运行项目



