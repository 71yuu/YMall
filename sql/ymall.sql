/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.60 : Database - ymall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ymall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ymall`;

/*Table structure for table `tb_address` */

DROP TABLE IF EXISTS `tb_address`;

CREATE TABLE `tb_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '地址编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '会员编号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `tel` varchar(255) DEFAULT NULL COMMENT '电话',
  `street_name` varchar(255) DEFAULT NULL COMMENT '地址详细信息',
  `is_default` tinyint(1) DEFAULT NULL COMMENT '是否为默认地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `tb_address` */

insert  into `tb_address`(`id`,`user_id`,`user_name`,`tel`,`street_name`,`is_default`) values 
(14,78,'Yuu','13055206361','福建省厦门市集美区集美大学诚毅学院',0),
(15,78,'杨雨衡','13055206361','福建省厦门市集美区集美大学诚毅学院',1);

/*Table structure for table `tb_express` */

DROP TABLE IF EXISTS `tb_express`;

CREATE TABLE `tb_express` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '快递编号',
  `express_name` varchar(255) DEFAULT NULL COMMENT '快递名称',
  `sort_order` int(11) DEFAULT NULL COMMENT '排序值',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='商品描述表';

/*Data for the table `tb_express` */

insert  into `tb_express`(`id`,`express_name`,`sort_order`,`created`,`updated`) values 
(4,'顺丰快递',1,'2019-07-10 11:05:50','2019-07-10 11:05:50'),
(5,'中通快递',2,'2019-07-10 11:05:58','2019-07-10 11:05:58'),
(6,'韵达快递',3,'2019-07-10 11:06:05','2019-07-10 11:06:05'),
(7,'申通快递',4,'2019-07-10 11:06:15','2019-07-10 11:06:15'),
(8,'Y速运',0,'2019-07-10 11:06:26','2019-07-10 11:06:26');

/*Table structure for table `tb_item` */

DROP TABLE IF EXISTS `tb_item`;

CREATE TABLE `tb_item` (
  `id` bigint(20) NOT NULL COMMENT '商品编号',
  `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(100) DEFAULT NULL COMMENT '商品卖点',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
  `num` int(11) DEFAULT NULL COMMENT '库存数量',
  `limit_num` int(11) DEFAULT NULL COMMENT '售卖数量限制',
  `image` varchar(2000) DEFAULT NULL COMMENT '商品图片',
  `cid` bigint(11) DEFAULT NULL COMMENT '所属分类',
  `status` int(1) DEFAULT '1' COMMENT '商品状态 1正常 0下架',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `status` (`status`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

/*Data for the table `tb_item` */

insert  into `tb_item`(`id`,`title`,`sell_point`,`price`,`num`,`limit_num`,`image`,`cid`,`status`,`created`,`updated`) values 
(156268231822963,'HUAWEI P30 Pro 麒麟980 超感光徕卡四摄 屏内指纹 双景录像 ','8GB+128GB 全网通版（天空之境）',5488.00,999,10,'http://pub7lsomw.bkt.clouddn.com/FpKQOpEUgDUuda0qObUovy454Ubd,http://pub7lsomw.bkt.clouddn.com/FkdP5QRm_0H3abio19Aie39RRF9j,http://pub7lsomw.bkt.clouddn.com/Fux2wRIMI1HDFm42BKro3J5V1-8p,http://pub7lsomw.bkt.clouddn.com/Fqg2ASg782ZQOlwZKC2z_-ukbNYD,http://pub7lsomw.bkt.clouddn.com/FptgIlZ-ukR4_XAxl5Pg5iUaDw6T',1235,1,'2019-07-09 22:25:18','2019-07-09 22:48:06'),
(156268365230771,'HUAWEI nova 5 8GB+128GB 全网通版','【nova 5新品将于7.13日开始预订，7.20日首销，敬请期待】',2799.00,999,5,'http://pub7lsomw.bkt.clouddn.com/FnK_JTCL57pocGQVz-Zp20HGNvqW,http://pub7lsomw.bkt.clouddn.com/Fr_aVXduBnaInGL7s4f-i43vgAiM,http://pub7lsomw.bkt.clouddn.com/Fjt_DKhvAo4Xwj3yNXT7k5LIHOYi,http://pub7lsomw.bkt.clouddn.com/FsHn1FemvV1hGiHyFhPRtcqcSEWa,http://pub7lsomw.bkt.clouddn.com/Fs2_dvgllnhG-4Vr2-dpFO8x371_',1247,1,'2019-07-09 22:47:32','2019-07-09 23:02:50'),
(156269705828174,'荣耀Note10 全网通 6GB+64GB 幻影蓝 AMOLED全面屏手机 AI智能 GT游戏加速 双卡双待 长续航','【到手价1899】①限时优惠900元②享3期/6期免息； 麒麟970AI芯片|液冷双Turbo|6.95英寸全面屏',1899.00,999,20,'http://pub7lsomw.bkt.clouddn.com/FvQQ7z7CmNs_RCAW0cundgE6Ho9S,http://pub7lsomw.bkt.clouddn.com/Fl5RdjBWrM2HYgCClA8IMZo9HXq0,http://pub7lsomw.bkt.clouddn.com/Ft9LWvfh6c-VTQe2VX2iDzW_Wno7,http://pub7lsomw.bkt.clouddn.com/FmZY-Du13VkJIC-ul_JHtXRGtVFJ,http://pub7lsomw.bkt.clouddn.com/FmTwXP4qzFIMqzwYYU2XTPGqJdS2',1234,1,'2019-07-10 02:30:58','2019-07-10 02:57:49'),
(156269945407026,'荣耀8X Max 骁龙660 7.12英寸高屏占比珍珠屏 5000mAh大电池 全网通 6GB+64GB（魅海蓝）',' 7.12英寸90%屏占比珍珠屏 随身影院 5000mAh大电池',1399.00,998,20,'http://pub7lsomw.bkt.clouddn.com/FtUbjDFl4PJtNwALeVNgve9QcnIv,http://pub7lsomw.bkt.clouddn.com/FhyzRQUYRw6PaV-wE1Cz0vlmkR8s,http://pub7lsomw.bkt.clouddn.com/FsgkuYsYjhVFQ4wyi8DzZSHln27f,http://pub7lsomw.bkt.clouddn.com/FkkXEzlxkGgCPhQnlDEpCQDulQgJ,http://pub7lsomw.bkt.clouddn.com/FnHh-OAB9C9IxjWl75SlhQm3-VTE',1234,1,'2019-07-10 03:10:54','2019-07-10 03:20:31'),
(156271863486747,'荣耀10青春版 幻彩渐变 2400万AI自拍 6.21英寸高屏占比珍珠屏 全网通 4GB+64GB（渐变蓝）','优惠300，成交价1099！',1099.00,999,10,'http://pub7lsomw.bkt.clouddn.com/FsD8plGUl9aSSTEobuQsewLd4l8e,http://pub7lsomw.bkt.clouddn.com/FpjsmsV6g0c2h08Azl6-MujgtTFX,http://pub7lsomw.bkt.clouddn.com/FmideoIREC0_50lxsxI7umVYVag6,http://pub7lsomw.bkt.clouddn.com/Fm87xVbflJUQw7c33EiDsfLmjcFD,http://pub7lsomw.bkt.clouddn.com/FgzUwHgH46jz7fxPJKLtpEHvNipZ',1234,1,'2019-07-10 08:30:34','2019-07-10 08:30:34'),
(156271954394859,'HUAWEI Mate 20 X 6GB+128GB 全网通版（宝石蓝）','麒麟980新一代人工智能芯片，4000万超大广角徕卡三摄，5000mAh大电池，石墨烯液冷散热技术加持旗舰手机',4099.00,999,10,'http://pub7lsomw.bkt.clouddn.com/FujL3yUs0vkqKYiz70Kewx5Wm6G-,http://pub7lsomw.bkt.clouddn.com/Fj6xJ3l-WqDh-EM6mEBXw9PKJNu-,http://pub7lsomw.bkt.clouddn.com/Ft0mfQuRx8-OK3ON2MFi18XzSP_E,http://pub7lsomw.bkt.clouddn.com/FoKCzHlOBv3PhUqBg5y03ZyEZuT4,http://pub7lsomw.bkt.clouddn.com/FoJpbDfzSNjxJtIBD347z6V0vYXn',1248,1,'2019-07-10 08:45:43','2019-07-10 08:45:43'),
(156272085015807,'荣耀Magic2 魔法全视屏 麒麟980AI芯片 屏内指纹 超广角AI三摄 全网通 6GB+128GB 渐变黑','麒麟980 AI处理器，后置2400万 AI三摄，40W安全超级快充。',2499.00,999,10,'http://pub7lsomw.bkt.clouddn.com/FvaioHuROssa58-WubEN6RzsLkMX,http://pub7lsomw.bkt.clouddn.com/Fl2yKYN91x7MYP-1-mMS-zU5ihQK,http://pub7lsomw.bkt.clouddn.com/FjrY2auIZ6F2W3SUBXcKXnd57YAf,http://pub7lsomw.bkt.clouddn.com/Fob962hE38xjhuGuT90OkzNGSkVi,http://pub7lsomw.bkt.clouddn.com/FvnIfbbBJBCTTjEjWljJKUHh-_Dh',1234,1,'2019-07-10 09:07:30','2019-07-10 09:07:30'),
(156272096620228,'HUAWEI nova 4 4800万超广角三摄 自拍极点全面屏 高配 8GB+128GB 全网通版（蜜语红·星耀版）',' 6.4英寸极点全面屏，4800万超广角三摄，2500万海报级自拍，AI微塑美颜，来电视频铃声，AI视频专家自动剪辑主角故事。',2799.00,998,10,'http://pub7lsomw.bkt.clouddn.com/Fo2lgi24IsyTcUy8Vzx_6-PHNI33,http://pub7lsomw.bkt.clouddn.com/Fi6ZLAkm362MZvqug0WKpODNvr1d,http://pub7lsomw.bkt.clouddn.com/FviVJduugrDkGigsVP89taEFpKvP,http://pub7lsomw.bkt.clouddn.com/FmHIIRxJnBXkz-EYUM_iYcEjuDig,http://pub7lsomw.bkt.clouddn.com/FjE__GdtP-aEWYKFkrjkttdO0ccY',1247,1,'2019-07-10 09:09:26','2019-07-10 09:09:26'),
(156272121662666,'荣耀畅玩8A 6.09英寸珍珠全面屏 震撼大音量 标配版 全网通 3GB+32GB（幻夜黑）','【到手价：749元】①下单立减50！②送原厂手机壳 ③晒单抽奖赢好礼！④送超值券包',799.00,998,10,'http://pub7lsomw.bkt.clouddn.com/FjkGpyu7wzBeRD0Sb3ePgNk3y9sl,http://pub7lsomw.bkt.clouddn.com/FgMuNXTHEhhzgvoUx0e9M_Sxqvlx,http://pub7lsomw.bkt.clouddn.com/FnX606l3BbD1I7zHYSrGIglfT68U,http://pub7lsomw.bkt.clouddn.com/FpowJ1Pd24F2Qv45QSmy4Nabb0Vw,http://pub7lsomw.bkt.clouddn.com/FtRWHvpISqVRUFM3YptPzYCXRYyz',1249,1,'2019-07-10 09:13:36','2019-07-10 09:13:36'),
(156272133543147,'HUAWEI nova 4e 3200万立体美颜 AI超广角三摄 4GB+128GB 全网通版（雀翎蓝）',' 3200万立体美颜，AI超广角三摄，3D曲面玻璃，128GB大内存',1799.00,999,10,'http://pub7lsomw.bkt.clouddn.com/FtExSRPJRGsDXEDLFjly_ZMjSljI,http://pub7lsomw.bkt.clouddn.com/FtB2HlS25f9-9jPu0AYQIkPYboyM,http://pub7lsomw.bkt.clouddn.com/Fp_aY2PfiNtscRLclwKXcr75KldO,http://pub7lsomw.bkt.clouddn.com/FoL7ZyfaY7WjM9tUMDUjUePikz-Y',1247,1,'2019-07-10 09:15:35','2019-07-10 09:15:35');

/*Table structure for table `tb_item_cat` */

DROP TABLE IF EXISTS `tb_item_cat`;

CREATE TABLE `tb_item_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID=0时代表一级根分类',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `status` int(1) DEFAULT '1' COMMENT '状态 1启用 0禁用',
  `sort_order` int(4) DEFAULT NULL COMMENT '排列序号',
  `is_parent` tinyint(1) DEFAULT '1' COMMENT '是否为父分类 1为true 0为false',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`,`status`) USING BTREE,
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB AUTO_INCREMENT=1250 DEFAULT CHARSET=utf8 COMMENT='商品类目';

/*Data for the table `tb_item_cat` */

insert  into `tb_item_cat`(`id`,`parent_id`,`name`,`status`,`sort_order`,`is_parent`,`created`,`updated`) values 
(-1,0,'所有商品',1,0,1,'2019-07-09 17:08:00','2019-07-09 17:08:03'),
(1214,0,'手机',1,1,1,'2019-07-09 17:08:56','2019-07-09 17:08:56'),
(1221,0,'笔记本&平板',1,2,1,NULL,'2019-07-09 22:05:38'),
(1234,1214,'荣耀系列',1,1,0,NULL,'2019-07-10 08:42:54'),
(1235,1214,'HUAWEI P系列',1,2,0,'2019-07-09 22:06:15','2019-07-09 22:06:15'),
(1236,1221,'平板电脑',1,1,0,'2019-07-09 22:07:10','2019-07-09 22:07:10'),
(1237,1221,'笔记本电脑',1,2,0,'2019-07-09 22:07:21','2019-07-09 22:07:21'),
(1238,1221,'笔记本配件',1,3,0,'2019-07-09 22:07:30','2019-07-09 22:07:30'),
(1239,0,'智能穿戴',1,3,1,'2019-07-09 22:07:43','2019-07-09 22:07:43'),
(1240,1239,'手环',1,1,0,'2019-07-09 22:07:52','2019-07-09 22:07:52'),
(1241,1239,'电视盒子',1,2,0,'2019-07-09 22:07:58','2019-07-09 22:07:58'),
(1242,1239,'照明',1,3,0,'2019-07-09 22:08:06','2019-07-09 22:08:06'),
(1243,0,'热销配件',1,4,1,'2019-07-09 22:08:13','2019-07-09 22:08:13'),
(1244,1243,'保护壳',1,1,0,'2019-07-09 22:08:20','2019-07-09 22:08:20'),
(1245,1243,'移动电源',1,2,0,'2019-07-09 22:08:26','2019-07-09 22:08:26'),
(1246,1243,'耳机',1,3,0,NULL,'2019-07-09 22:08:39'),
(1247,1214,'HUAWEI nova系列',1,3,0,NULL,'2019-07-10 09:17:22'),
(1248,1214,'Mate 系列',1,4,0,'2019-07-10 08:42:50','2019-07-10 08:42:50'),
(1249,1214,'畅玩系列',1,5,0,'2019-07-10 09:10:33','2019-07-10 09:10:33');

/*Table structure for table `tb_item_desc` */

DROP TABLE IF EXISTS `tb_item_desc`;

CREATE TABLE `tb_item_desc` (
  `item_id` bigint(20) NOT NULL COMMENT '商品编号',
  `item_desc` text COMMENT '商品描述',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';

/*Data for the table `tb_item_desc` */

insert  into `tb_item_desc`(`item_id`,`item_desc`,`created`,`updated`) values 
(156266413524759,'<p><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/FgFzGQ1ErxI8TVxfo4cAZu1RcWOR\"><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/FpNugyQTtHpVDW3O1xhCVCzl9RHx\"></p><p><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/FnkZDuiG2tnliVWV3SwFw2PmGqGQ\"><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/Fv0NQMvATI6YQ1DFx4B7I7W2bXtd\"><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/FjwOrCdiGXcmDRhWwat2qNa_ugse\"><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/Fn6LkE_1EfkofRvMqydkCUKooFIu\"><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/Fn0U9WORRsWsoNf_1Ij014BX9CZD\"><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/FqUcMZB5bZJtYhtc0WLFN42gRtG2\"><img style=\"max-width:100%;\" src=\"http://pub7lsomw.bkt.clouddn.com/FqdPmUqxC_z60aDCqzrzrrBV7uW6\"><b></b><i></i><u></u><sub></sub><sup></sup><strike></strike><br></p>','2019-07-09 17:22:15','2019-07-09 17:25:47'),
(156267056226257,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FsQ0_KGIHunwcub4kUmg-qZBNbrg\" style=\"max-width:100%;\"><br></p>','2019-07-09 19:09:22','2019-07-09 20:51:29'),
(156267834403843,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FsU-MGJ1Rn-_DyDR-igw3dqROkaj\" style=\"max-width:100%;\"><br></p>','2019-07-09 21:19:04','2019-07-09 21:24:27'),
(156267929355630,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FnXJRoKBdVPqpLh0N6xl25OmW7Uq\" style=\"max-width:100%;\"><br></p>','2019-07-09 21:34:55','2019-07-09 21:39:00'),
(156268007938096,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FpbBVoRbk0C3V-yRzlivAb4hAcaI\" style=\"max-width:100%;\"><br></p>','2019-07-09 21:47:59','2019-07-09 22:04:24'),
(156268231822963,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FsU-MGJ1Rn-_DyDR-igw3dqROkaj\" style=\"max-width:100%;\"><br></p>','2019-07-09 22:25:18','2019-07-09 22:48:06'),
(156268365230771,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/lgepvbcpxvKr47jm-vpKEklJfLwJ\" style=\"max-width:100%;\"><br></p>','2019-07-09 22:47:32','2019-07-09 23:02:50'),
(156269705828174,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/ljWozpWDNgoQxU7Rr_Dx9EA8jows\" style=\"max-width:100%;\"><br></p>','2019-07-10 02:30:58','2019-07-10 02:57:49'),
(156269945407026,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/lt48nkiYdkXFMG65rWkW1JMqf9Uw\" style=\"max-width:100%;\"><br></p>','2019-07-10 03:10:54','2019-07-10 03:20:31'),
(156271863486747,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/lvtRbvNgr22sNouj9Hyidp6WGnJG\" style=\"max-width:100%;\"><br></p>','2019-07-10 08:30:34','2019-07-10 08:30:34'),
(156271954394859,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FujL3yUs0vkqKYiz70Kewx5Wm6G-\" style=\"max-width:100%;\"><br></p>','2019-07-10 08:45:43','2019-07-10 08:45:43'),
(156272085015807,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FvaioHuROssa58-WubEN6RzsLkMX\" style=\"max-width:100%;\"><br></p>','2019-07-10 09:07:30','2019-07-10 09:07:30'),
(156272096620228,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/Fo2lgi24IsyTcUy8Vzx_6-PHNI33\" style=\"max-width:100%;\"><br></p>','2019-07-10 09:09:26','2019-07-10 09:09:26'),
(156272121662666,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FjkGpyu7wzBeRD0Sb3ePgNk3y9sl\" style=\"max-width:100%;\"><br></p>','2019-07-10 09:13:36','2019-07-10 09:13:36'),
(156272133543147,'<p><img src=\"http://pub7lsomw.bkt.clouddn.com/FtExSRPJRGsDXEDLFjly_ZMjSljI\" style=\"max-width:100%;\"><br></p>','2019-07-10 09:15:35','2019-07-10 09:15:35');

/*Table structure for table `tb_member` */

DROP TABLE IF EXISTS `tb_member`;

CREATE TABLE `tb_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员编号',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `state` int(1) DEFAULT '0' COMMENT '会员状态 1正常 2封禁',
  `file` varchar(255) DEFAULT 'https://yuu-1257159061.cos.ap-guangzhou.myqcloud.com/ymall/default-user-avatar.png' COMMENT '头像',
  `description` varchar(500) DEFAULT NULL COMMENT '会员描述',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE,
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `tb_member` */

insert  into `tb_member`(`id`,`username`,`password`,`phone`,`email`,`sex`,`state`,`file`,`description`,`created`,`updated`) values 
(78,NULL,'93bb7f2ba5d3d4be1af978726b97b4be','13055206361',NULL,NULL,1,'http://pub7lsomw.bkt.clouddn.com/1562726657635.png',NULL,'2019-07-10 10:38:34','2019-07-10 10:38:34');

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单编号',
  `payment` decimal(10,2) DEFAULT NULL COMMENT '实付金额',
  `payment_type` int(1) DEFAULT NULL COMMENT '支付类型 1在线支付 2货到付款',
  `post_fee` decimal(10,2) DEFAULT NULL COMMENT '邮费',
  `status` int(1) DEFAULT NULL COMMENT '状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `shipping_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '会员编号',
  `buyer_message` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '买家留言',
  `buyer_nick` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '买家昵称',
  `buyer_comment` tinyint(1) DEFAULT NULL COMMENT '买家是否已经评价',
  `created` datetime DEFAULT NULL COMMENT '订单创建时间',
  `updated` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`id`),
  KEY `buyer_nick` (`buyer_nick`),
  KEY `status` (`status`),
  KEY `payment_type` (`payment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tb_order` */

insert  into `tb_order`(`id`,`payment`,`payment_type`,`post_fee`,`status`,`payment_time`,`consign_time`,`end_time`,`close_time`,`shipping_name`,`shipping_code`,`user_id`,`buyer_message`,`buyer_nick`,`buyer_comment`,`created`,`updated`) values 
('156272657441294',9195.00,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL,78,NULL,'13055206361',NULL,'2019-07-10 10:42:54','2019-07-10 10:42:54');

/*Table structure for table `tb_order_item` */

DROP TABLE IF EXISTS `tb_order_item`;

CREATE TABLE `tb_order_item` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int(10) DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `total_fee` decimal(10,2) DEFAULT NULL COMMENT '商品总金额',
  `pic_path` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址',
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `tb_order_item` */

insert  into `tb_order_item`(`id`,`item_id`,`order_id`,`num`,`title`,`price`,`total_fee`,`pic_path`) values 
('156272657442857','156272121662666','156272657441294',1,'荣耀畅玩8A 6.09英寸珍珠全面屏 震撼大音量 标配版 全网通 3GB+32GB（幻夜黑）',799.00,799.00,'http://pub7lsomw.bkt.clouddn.com/FjkGpyu7wzBeRD0Sb3ePgNk3y9sl'),
('156272657444263','156272096620228','156272657441294',1,'HUAWEI nova 4 4800万超广角三摄 自拍极点全面屏 高配 8GB+128GB 全网通版（蜜语红·星耀版）',2799.00,2799.00,'http://pub7lsomw.bkt.clouddn.com/Fo2lgi24IsyTcUy8Vzx_6-PHNI33'),
('156272657444438','156269945407026','156272657441294',2,'荣耀8X Max 骁龙660 7.12英寸高屏占比珍珠屏 5000mAh大电池 全网通 6GB+64GB（魅海蓝）',1399.00,2798.00,'http://pub7lsomw.bkt.clouddn.com/FtUbjDFl4PJtNwALeVNgve9QcnIv'),
('156272657444678','156268365230771','156272657441294',1,'HUAWEI nova 5 8GB+128GB 全网通版',2799.00,2799.00,'http://pub7lsomw.bkt.clouddn.com/FnK_JTCL57pocGQVz-Zp20HGNvqW');

/*Table structure for table `tb_order_shipping` */

DROP TABLE IF EXISTS `tb_order_shipping`;

CREATE TABLE `tb_order_shipping` (
  `order_id` varchar(50) NOT NULL COMMENT '订单ID',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货人全名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '固定电话',
  `receiver_mobile` varchar(30) DEFAULT NULL COMMENT '移动电话',
  `receiver_province` varchar(10) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(10) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮政编码,如：310001',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_order_shipping` */

insert  into `tb_order_shipping`(`order_id`,`receiver_name`,`receiver_phone`,`receiver_mobile`,`receiver_province`,`receiver_city`,`receiver_district`,`receiver_address`,`receiver_zip`,`created`,`updated`) values 
('156272657441294','杨雨衡','13055206361',NULL,NULL,NULL,NULL,'福建省厦门市集美区集美大学诚毅学院',NULL,'2019-07-10 10:42:54','2019-07-10 10:42:54');

/*Table structure for table `tb_panel` */

DROP TABLE IF EXISTS `tb_panel`;

CREATE TABLE `tb_panel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `name` varchar(50) DEFAULT NULL COMMENT '板块名称',
  `type` int(1) DEFAULT NULL COMMENT '类型 0轮播图 1板块种类一 2板块种类二 3板块种类三 ',
  `sort_order` int(4) DEFAULT NULL COMMENT '排列序号',
  `position` int(1) DEFAULT NULL COMMENT '所属位置 0首页 1商品推荐 2我要捐赠',
  `limit_num` int(4) DEFAULT NULL COMMENT '板块限制商品数量',
  `status` int(1) DEFAULT '1' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`status`) USING BTREE,
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='内容分类';

/*Data for the table `tb_panel` */

insert  into `tb_panel`(`id`,`name`,`type`,`sort_order`,`position`,`limit_num`,`status`,`created`,`updated`) values 
(11,'轮播图',0,0,0,5,1,'2019-07-09 17:00:00','2019-07-09 17:00:00'),
(12,'活动板块1',1,1,0,4,1,'2019-07-09 18:56:18','2019-07-09 18:56:18'),
(13,'热销单品',2,2,0,4,1,NULL,'2019-07-09 23:28:48'),
(14,'精品推荐',3,3,0,8,1,NULL,'2019-07-10 00:10:52'),
(15,'手机',3,4,0,8,1,NULL,'2019-07-10 00:10:59'),
(16,'精品平板',3,6,0,8,1,NULL,'2019-07-10 00:37:49'),
(17,'笔记本电脑',3,5,0,8,1,NULL,'2019-07-10 00:47:24'),
(18,'智能穿戴',3,7,0,7,1,NULL,'2019-07-10 00:53:54'),
(19,'智能家居',3,8,0,7,1,NULL,'2019-07-10 00:54:14'),
(20,'热销配件',3,9,0,7,1,NULL,'2019-07-10 00:54:19'),
(21,'生态精品',3,10,0,7,1,NULL,'2019-07-10 00:54:21'),
(22,'精选配件',3,11,0,7,1,NULL,'2019-07-10 00:54:24');

/*Table structure for table `tb_panel_content` */

DROP TABLE IF EXISTS `tb_panel_content`;

CREATE TABLE `tb_panel_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `panel_id` int(11) NOT NULL COMMENT '所属板块id',
  `type` int(1) DEFAULT NULL COMMENT '类型 0关联商品 1其他链接 2关联商品（封面）3关联商品（封面）',
  `product_id` bigint(20) DEFAULT NULL COMMENT '关联商品id',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序值',
  `full_url` varchar(500) DEFAULT NULL COMMENT '其他链接',
  `pic_url` varchar(500) DEFAULT NULL COMMENT '图片',
  `pic_url2` varchar(500) DEFAULT NULL COMMENT '3d轮播图备用',
  `pic_url3` varchar(500) DEFAULT NULL COMMENT '3d轮播图备用',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `category_id` (`panel_id`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;

/*Data for the table `tb_panel_content` */

insert  into `tb_panel_content`(`id`,`panel_id`,`type`,`product_id`,`sort_order`,`full_url`,`pic_url`,`pic_url2`,`pic_url3`,`created`,`updated`) values 
(78,11,0,156268231822963,1,'','http://pub7lsomw.bkt.clouddn.com/FgboEB3GoUaOhhVVrV2iUs_0j4rd',NULL,NULL,NULL,'2019-07-09 22:30:02'),
(79,11,0,156268365230771,2,'','http://pub7lsomw.bkt.clouddn.com/FmRYkuEFYde6xI-dDcXqsOfSKK3o',NULL,NULL,'2019-07-09 22:48:34','2019-07-09 22:48:34'),
(80,12,0,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/FnK_JTCL57pocGQVz-Zp20HGNvqW',NULL,NULL,'2019-07-09 23:07:15','2019-07-09 23:07:15'),
(81,12,0,156268231822963,2,'','http://pub7lsomw.bkt.clouddn.com/FmyoVNNUwRlMq9L65u6rsJnX-_jD',NULL,NULL,'2019-07-09 23:09:56','2019-07-09 23:09:56'),
(82,12,0,156268365230771,3,'','http://pub7lsomw.bkt.clouddn.com/FtvjyajKpyy6xteuuDeUmVpxkq2z',NULL,NULL,'2019-07-09 23:10:19','2019-07-09 23:10:19'),
(83,12,0,156268231822963,4,'','http://pub7lsomw.bkt.clouddn.com/FmYF8urf5luGTp7EzETexBMTvAz5',NULL,NULL,'2019-07-09 23:10:31','2019-07-09 23:10:31'),
(84,13,0,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/FurHro4g_v6KrvZVVumIdtD3VRVf',NULL,NULL,'2019-07-09 23:26:27','2019-07-09 23:26:27'),
(85,13,0,156268365230771,0,'','http://pub7lsomw.bkt.clouddn.com/FmyoVNNUwRlMq9L65u6rsJnX-_jD',NULL,NULL,'2019-07-09 23:27:26','2019-07-09 23:27:26'),
(86,14,0,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/FrekC0IfRuSfFdZMh9pxVdfEWzFI',NULL,NULL,NULL,'2019-07-09 23:51:14'),
(87,14,0,156268231822963,2,'','http://pub7lsomw.bkt.clouddn.com/FhVtfCRfReyM37ELaa7canqnSDJy',NULL,NULL,'2019-07-09 23:39:49','2019-07-09 23:39:49'),
(88,14,0,156268231822963,3,'','http://pub7lsomw.bkt.clouddn.com/FuxZbjWZL3g0ZltDwhPGEjju7ynv',NULL,NULL,'2019-07-09 23:40:31','2019-07-09 23:40:31'),
(89,14,0,156268365230771,4,'','http://pub7lsomw.bkt.clouddn.com/FncB80rXZ6K-549TYrLom-VOQPKb',NULL,NULL,'2019-07-09 23:40:54','2019-07-09 23:40:54'),
(91,14,0,156268231822963,6,'','http://pub7lsomw.bkt.clouddn.com/FnEmbcH6sDuaBZW47d6hXOFqeUx5',NULL,NULL,'2019-07-09 23:47:20','2019-07-09 23:47:20'),
(92,14,0,156268365230771,7,'','http://pub7lsomw.bkt.clouddn.com/FtXF9v_EbPcinP4sIQvaAWZq5NxC',NULL,NULL,'2019-07-09 23:47:37','2019-07-09 23:47:37'),
(93,14,0,156268231822963,0,'','http://pub7lsomw.bkt.clouddn.com/FsTOANA4z23QZDbOnlWijWyeTWOZ',NULL,NULL,NULL,'2019-07-10 00:11:31'),
(94,15,0,156269705828174,1,'','http://pub7lsomw.bkt.clouddn.com/FiAl6TJLp__NvL8_3f0vbLxUhO03',NULL,NULL,NULL,'2019-07-10 02:58:49'),
(95,15,0,156271863486747,2,'','http://pub7lsomw.bkt.clouddn.com/FiNn5h9bT1Wg0EDT2bs4ak5wn0G7',NULL,NULL,NULL,'2019-07-10 08:30:46'),
(96,15,0,156271954394859,3,'','http://pub7lsomw.bkt.clouddn.com/FhmzWEQ5aJxi00lS90199e34YLy7',NULL,NULL,NULL,'2019-07-10 08:46:01'),
(97,14,0,156268231822963,8,'','http://pub7lsomw.bkt.clouddn.com/FnKW_5yeMQJ7W2plr6Yk7TE-6A5k',NULL,NULL,NULL,'2019-07-10 00:12:27'),
(98,15,0,156272085015807,4,'','http://pub7lsomw.bkt.clouddn.com/FvaioHuROssa58-WubEN6RzsLkMX',NULL,NULL,NULL,'2019-07-10 09:08:01'),
(99,15,0,156272096620228,5,'','http://pub7lsomw.bkt.clouddn.com/Fo2lgi24IsyTcUy8Vzx_6-PHNI33',NULL,NULL,NULL,'2019-07-10 09:09:45'),
(100,15,0,156272121662666,6,'','http://pub7lsomw.bkt.clouddn.com/Fi2cL3eRANv3iMysg3t0DCsqFpH5',NULL,NULL,NULL,'2019-07-10 09:15:47'),
(101,15,0,156272133543147,7,'','http://pub7lsomw.bkt.clouddn.com/FnEmbcH6sDuaBZW47d6hXOFqeUx5',NULL,NULL,NULL,'2019-07-10 09:15:53'),
(102,15,0,156269945407026,1,'','http://pub7lsomw.bkt.clouddn.com/FtUbjDFl4PJtNwALeVNgve9QcnIv',NULL,NULL,NULL,'2019-07-10 03:11:13'),
(103,11,0,156268365230771,3,'','http://pub7lsomw.bkt.clouddn.com/Fn5aIOj1sPy9_HPAyy3Us_CRwmLb',NULL,NULL,'2019-07-10 00:21:52','2019-07-10 00:21:52'),
(104,11,0,156268365230771,4,'','http://pub7lsomw.bkt.clouddn.com/FpkqZw5MHFGD0iOaP6DwfbCfkiof',NULL,NULL,'2019-07-10 00:22:10','2019-07-10 00:22:10'),
(105,11,1,NULL,5,'https://www.71yuu.com/','http://pub7lsomw.bkt.clouddn.com/Fka1NhCLE19wPupRlGSSR-UxtD12',NULL,NULL,'2019-07-10 00:22:36','2019-07-10 00:22:36'),
(106,16,0,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/Fkb_AeosFyYNFe_giwHo-Gr2IMv2',NULL,NULL,'2019-07-10 00:27:44','2019-07-10 00:27:44'),
(107,16,0,156268231822963,2,'','http://pub7lsomw.bkt.clouddn.com/Fh41PYzAklpyqqApDFfCTfYljcab',NULL,NULL,'2019-07-10 00:29:12','2019-07-10 00:29:12'),
(108,16,0,156268365230771,3,'','http://pub7lsomw.bkt.clouddn.com/Fm4gchBeRRVHvzik8PlHxVk02p5H',NULL,NULL,'2019-07-10 00:31:19','2019-07-10 00:31:19'),
(109,16,0,156268231822963,4,'','http://pub7lsomw.bkt.clouddn.com/FpA6Nge5X8hMsC4tGz8TkVMrpnQI',NULL,NULL,'2019-07-10 00:32:27','2019-07-10 00:32:27'),
(110,16,0,156268231822963,5,'','http://pub7lsomw.bkt.clouddn.com/FnGy7u1Mtw9xpin9nbBgxFDT8GsZ',NULL,NULL,'2019-07-10 00:33:54','2019-07-10 00:33:54'),
(111,16,0,156268365230771,6,'','http://pub7lsomw.bkt.clouddn.com/FlD1YI-YO4n8Sm502c9WeGBbV7sS',NULL,NULL,'2019-07-10 00:34:58','2019-07-10 00:34:58'),
(112,16,0,156268365230771,7,'','http://pub7lsomw.bkt.clouddn.com/FjsM5dug05E_vzFox7Nh24vxItVI',NULL,NULL,'2019-07-10 00:35:40','2019-07-10 00:35:40'),
(113,16,0,156268365230771,8,'','http://pub7lsomw.bkt.clouddn.com/FvkKDWnd_dNViTlDYkNEW9qUDDQ7',NULL,NULL,'2019-07-10 00:36:00','2019-07-10 00:36:00'),
(114,17,0,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/FsTUuP-MY2p8_Rtu75yshgLc9HAA',NULL,NULL,'2019-07-10 00:41:47','2019-07-10 00:41:47'),
(115,17,0,156268365230771,2,'','http://pub7lsomw.bkt.clouddn.com/FsHMvN6Sh5bYfS1hUwDSSUovV1Iz',NULL,NULL,'2019-07-10 00:42:34','2019-07-10 00:42:34'),
(116,17,0,156268231822963,3,'','http://pub7lsomw.bkt.clouddn.com/Fhq53yhpC8sRp5sYCDCuKMdsxR5x',NULL,NULL,'2019-07-10 00:43:07','2019-07-10 00:43:07'),
(117,17,0,156268365230771,4,'','http://pub7lsomw.bkt.clouddn.com/FpX5ofiu2JRNceYT1KKfhZEwk5hV',NULL,NULL,'2019-07-10 00:45:14','2019-07-10 00:45:14'),
(118,17,0,156268365230771,5,'','http://pub7lsomw.bkt.clouddn.com/Fmg1g_m9jvOkI_C0_oZxrEYnjbMk',NULL,NULL,'2019-07-10 00:45:33','2019-07-10 00:45:33'),
(119,17,0,156268365230771,6,'','http://pub7lsomw.bkt.clouddn.com/FkOP4JouTXsE3XxRcl4Qs3cD70Sa',NULL,NULL,'2019-07-10 00:46:21','2019-07-10 00:46:21'),
(120,17,0,156268365230771,7,'','http://pub7lsomw.bkt.clouddn.com/FnOZnlOUbVJPZwdDWYyJOGDz5Wao',NULL,NULL,'2019-07-10 00:46:38','2019-07-10 00:46:38'),
(121,17,0,156268365230771,8,'','http://pub7lsomw.bkt.clouddn.com/FuuLFcc1xLCcOwj5QeyWCA2dfxTl',NULL,NULL,'2019-07-10 00:47:09','2019-07-10 00:47:09'),
(122,18,2,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/FmeWoAGMO4arsC-mswaCRP6Cf2Om',NULL,NULL,NULL,'2019-07-10 00:53:23'),
(123,18,0,156268231822963,2,'','http://pub7lsomw.bkt.clouddn.com/FjHoRljhhq9JQBcVbH7878v-H7_s',NULL,NULL,'2019-07-10 00:49:51','2019-07-10 00:49:51'),
(124,18,0,156268365230771,3,'','http://pub7lsomw.bkt.clouddn.com/FmnICU_SGBZTSGCf2ofNz56onMJI',NULL,NULL,'2019-07-10 00:50:34','2019-07-10 00:50:34'),
(125,18,0,156268365230771,4,'','http://pub7lsomw.bkt.clouddn.com/FhCPEwQhyqSYziYqMekP4EO-9Kfk',NULL,NULL,'2019-07-10 00:51:04','2019-07-10 00:51:04'),
(126,18,0,156268365230771,5,'','http://pub7lsomw.bkt.clouddn.com/FgelPcSEB2lkd-4t620GUgaAlf4h',NULL,NULL,'2019-07-10 00:51:38','2019-07-10 00:51:38'),
(127,18,0,156268231822963,6,'','http://pub7lsomw.bkt.clouddn.com/FjAI4QZEHoyvE7jNepqhx3h1zZnJ',NULL,NULL,'2019-07-10 00:52:12','2019-07-10 00:52:12'),
(128,18,0,156268365230771,7,'','http://pub7lsomw.bkt.clouddn.com/Fg5atYssfQJFLtDJmQzpXByxfWER',NULL,NULL,'2019-07-10 00:52:38','2019-07-10 00:52:38'),
(130,19,2,156268231822963,1,'','http://pub7lsomw.bkt.clouddn.com/FmPsY2qDUIdQ5mUOyWNz_LDWVLFc',NULL,NULL,'2019-07-10 00:54:59','2019-07-10 00:54:59'),
(131,19,0,156268365230771,2,'','http://pub7lsomw.bkt.clouddn.com/FrpvBCfcQNauK6z8ntxayEbThtbn',NULL,NULL,'2019-07-10 00:55:15','2019-07-10 00:55:15'),
(132,19,0,156268365230771,3,'','http://pub7lsomw.bkt.clouddn.com/FhEGelnrvmfRP7LHxYuf3CK7QBAZ',NULL,NULL,'2019-07-10 00:55:31','2019-07-10 00:55:31'),
(133,19,0,156268365230771,4,'','http://pub7lsomw.bkt.clouddn.com/FgS8KX5kQ43ze3Jrmxqb-OzcHb12',NULL,NULL,'2019-07-10 00:55:45','2019-07-10 00:55:45'),
(134,19,0,156268365230771,6,'','http://pub7lsomw.bkt.clouddn.com/FjmtIGK_CbH1lWkz1F2IqDROWNrQ',NULL,NULL,'2019-07-10 00:56:07','2019-07-10 00:56:07'),
(135,19,0,156268365230771,5,'','http://pub7lsomw.bkt.clouddn.com/FgS8KX5kQ43ze3Jrmxqb-OzcHb12',NULL,NULL,'2019-07-10 00:56:25','2019-07-10 00:56:25'),
(136,19,0,156268365230771,7,'','http://pub7lsomw.bkt.clouddn.com/FkxA9knjUc29Vw-XNYWHnxHzmf6v',NULL,NULL,'2019-07-10 00:56:38','2019-07-10 00:56:38'),
(137,20,2,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/FoYaHbVA1_6fln3Zht2F0SoaMTOj',NULL,NULL,NULL,'2019-07-10 00:58:47'),
(138,20,0,156268365230771,2,'','http://pub7lsomw.bkt.clouddn.com/Fv9DBHm1MEHEMJhRnmMrW58p45w4',NULL,NULL,'2019-07-10 00:57:22','2019-07-10 00:57:22'),
(139,20,0,156268365230771,3,'','http://pub7lsomw.bkt.clouddn.com/FlEvT-WvZBjuiHtAwL4QCDWRMVgs',NULL,NULL,'2019-07-10 00:57:33','2019-07-10 00:57:33'),
(140,20,0,156268365230771,4,'','http://pub7lsomw.bkt.clouddn.com/FrlETSAFEVnIWUvjpUgJcPu_a7AL',NULL,NULL,'2019-07-10 00:57:47','2019-07-10 00:57:47'),
(141,20,0,156268365230771,5,'','http://pub7lsomw.bkt.clouddn.com/Flrx2EBC2lobY2eJmAeNALZF_BNI',NULL,NULL,'2019-07-10 00:58:01','2019-07-10 00:58:01'),
(142,20,0,156268365230771,6,'','http://pub7lsomw.bkt.clouddn.com/Ftq4x8X8oSzjNq8c9JTfh_kwUKiY',NULL,NULL,'2019-07-10 00:58:12','2019-07-10 00:58:12'),
(143,20,0,156268365230771,7,'','http://pub7lsomw.bkt.clouddn.com/Fkd9mlW2K23UooLUsSgL05wggcxN',NULL,NULL,'2019-07-10 00:58:28','2019-07-10 00:58:28'),
(144,21,2,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/Fi8CiUEL5N57JoTFOc4i4mm9AEjO',NULL,NULL,'2019-07-10 00:59:07','2019-07-10 00:59:07'),
(145,21,0,156268365230771,2,'','http://pub7lsomw.bkt.clouddn.com/FivVNRyn8aNjFpwXbD4nzh_-3qZu',NULL,NULL,'2019-07-10 00:59:29','2019-07-10 00:59:29'),
(146,21,0,156268365230771,3,'','http://pub7lsomw.bkt.clouddn.com/Foox3uBjTPgWIOjVRDLqWTLRx3bp',NULL,NULL,NULL,'2019-07-10 01:00:11'),
(147,21,0,156268231822963,4,'','http://pub7lsomw.bkt.clouddn.com/Fttk_HMnMpUy4in9PRxOc7uZjMg5',NULL,NULL,'2019-07-10 01:00:00','2019-07-10 01:00:00'),
(148,21,0,156268365230771,5,'','http://pub7lsomw.bkt.clouddn.com/FrzmdkpUdoHe32DRog7jafiLff9c',NULL,NULL,'2019-07-10 01:00:28','2019-07-10 01:00:28'),
(149,21,0,156268365230771,6,'','http://pub7lsomw.bkt.clouddn.com/FrTiUKMvry8N2as8DN6TQfbQUfUW',NULL,NULL,'2019-07-10 01:00:45','2019-07-10 01:00:45'),
(150,21,0,156268365230771,7,'','http://pub7lsomw.bkt.clouddn.com/Fj8scPW_HBIo4MMJbXQA6ajs5LFF',NULL,NULL,'2019-07-10 01:00:58','2019-07-10 01:00:58'),
(151,22,2,156268365230771,1,'','http://pub7lsomw.bkt.clouddn.com/FoZIcvdU6vkX2HDAshrG7np_07wY',NULL,NULL,NULL,'2019-07-10 01:02:34'),
(152,22,0,156268231822963,2,'','http://pub7lsomw.bkt.clouddn.com/FjY39dK5GGIekyCMggPuM1W7LCEA',NULL,NULL,NULL,'2019-07-10 01:02:27'),
(153,22,0,156268365230771,3,'','http://pub7lsomw.bkt.clouddn.com/Fq9IcTpReMj9SgjEqZX4YSf5HoBO',NULL,NULL,'2019-07-10 01:02:46','2019-07-10 01:02:46'),
(154,22,0,156268365230771,4,'','http://pub7lsomw.bkt.clouddn.com/FrgJHhcQNf5UyufKhyyBoFDMWeQg',NULL,NULL,'2019-07-10 01:03:00','2019-07-10 01:03:00'),
(155,22,0,156268231822963,5,'','http://pub7lsomw.bkt.clouddn.com/FurE0pcGxMisGkmxCTrzb8uyAAP3',NULL,NULL,'2019-07-10 01:03:10','2019-07-10 01:03:10'),
(156,22,0,156268231822963,6,'','http://pub7lsomw.bkt.clouddn.com/Fg02vwvb86oQGOwQgGoxz6DAIEWV',NULL,NULL,'2019-07-10 01:03:19','2019-07-10 01:03:19'),
(157,22,0,156268365230771,7,'','http://pub7lsomw.bkt.clouddn.com/Fm7d4f9IAx1vb3cPklDh704LvsD1',NULL,NULL,'2019-07-10 01:03:30','2019-07-10 01:03:30');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码 md5加密存储',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`password`,`created`,`updated`) values 
(1,'admin','e10adc3949ba59abbe56e057f20f883e','2017-09-05 21:27:54','2017-10-18 22:57:08');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
