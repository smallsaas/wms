/*
Navicat MySQL Data Transfer

Source Server         : location
Source Server Version : 50718
Source Host           : 127.0.0.1:3306
Source Database       : softto

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-06-30 15:01:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_category_id` bigint(20) not NULL COMMENT '产品类别',
  `product_code` varchar(255) not NULL COMMENT '编码',
  `name` varchar(26) not NULL COMMENT '名称',
  `english_name` varchar(255) DEFAULT NULL COMMENT '外文名称',
  `weight` varchar(26) DEFAULT NULL COMMENT '重量',
  `readjust_cost_price` decimal(12,4) DEFAULT NULL COMMENT '入库成本调整',
  `product_standard` varchar(50) DEFAULT NULL COMMENT '产品标准',
  `price` decimal(12,4) DEFAULT NULL COMMENT '价格(该场景下是零售价格)',
  `suggested_price` decimal(12,4) DEFAULT NULL COMMENT '建议零售价',
  `cost_price` decimal(12,4) DEFAULT NULL COMMENT '成本价格',
  `specification` varchar(26) DEFAULT NULL COMMENT '规格',
  `quantities` int(11) DEFAULT NULL COMMENT '数量',
  `stock_cost` decimal(12,4) DEFAULT NULL COMMENT '库存成本',
  `purchase_advance` int(11) DEFAULT NULL COMMENT '预购数量',
  `status` varchar(26) not NULL COMMENT '状态',
  `sort_value` int(11) not NULL COMMENT '排序值',
  `search_key_word` varchar(26) DEFAULT NULL COMMENT '搜索关键字',
  `bar_code` varchar(255) DEFAULT NULL COMMENT '条形码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `description` text COMMENT '描述',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `volume` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `spec` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field4` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field5` varchar(255) DEFAULT NULL COMMENT '保留字段',
  UNIQUE KEY (`product_code`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product_category
-- ----------------------------
DROP TABLE IF EXISTS `t_product_category`;
CREATE TABLE `t_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(26) NOT NULL COMMENT '类型名称',
  `category_code` varchar(26) NOT NULL COMMENT '类型名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `category_description` text COMMENT '类型说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`category_name`,`pid`),
  unique key (`category_code`),
  foreign key (`pid`) references `t_product_category`(`id`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
