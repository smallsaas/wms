
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
-- Table structure for t_condition
-- ----------------------------
DROP TABLE IF EXISTS `t_sku_condition`;
CREATE TABLE `t_sku_condition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `condition_name` varchar(26) NOT NULL COMMENT '状态名称',
  `condition_description` text COMMENT '名称说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`condition_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_sku_product`;
CREATE TABLE `t_sku_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) not NULL COMMENT '产品名称',
  `sku_code` varchar(255) NOT NULL COMMENT 'sku编号',
  `sku_name` varchar(255) NOT NULL COMMENT 'sku名称',
  `status` varchar(26) not NULL COMMENT '状态',
  `sort_value` int(11) not NULL COMMENT '排序值',
  `search_key_word` varchar(26) DEFAULT NULL COMMENT '搜索关键字',
  `bar_code` varchar(255) DEFAULT NULL COMMENT '条形码',
  `description` text COMMENT '描述',

  `sku_price` decimal(12,4) NOT NULL COMMENT '产品价格',
  `readjust_cost_price` decimal(12,4) DEFAULT NULL COMMENT '入库成本调整',
  `suggested_price` decimal(12,4) DEFAULT NULL COMMENT '建议零售价',
  `cost_price` decimal(12,4) DEFAULT NULL COMMENT '成本价格',
  `stock_cost` decimal(12,4) DEFAULT NULL COMMENT '库存成本',

  `create_time`  timestamp not null default current_timestamp COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field3` varchar(255) DEFAULT NULL COMMENT '保留字段',
  UNIQUE(`sku_code`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for t_product_condition
-- ----------------------------
DROP TABLE IF EXISTS `t_sku_condition`;
CREATE TABLE `t_sku_condition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) NOT NULL COMMENT '产品Id',
  `condition_id` bigint(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`sku_id`,`condition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product_photo
-- ----------------------------
DROP TABLE IF EXISTS `t_sku_photo`;
CREATE TABLE `t_sku_photo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `photo_url` varchar(255) NOT NULL COMMENT '图片地址',
  `is_primary` int(11) DEFAULT 0 COMMENT '是否为主要',
  `sku_id` bigint(20) NOT NULL COMMENT '产品Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`sku_id`,`photo_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_sku_tag`;
CREATE TABLE `t_sku_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(26) NOT NULL COMMENT '标签名称',
  `tag_description` text COMMENT '标签说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_sku_tag_relation`;
CREATE TABLE `t_sku_tag_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) NOT NULL COMMENT '产品Id',
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`sku_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product_unit
-- ----------------------------
DROP TABLE IF EXISTS `t_sku_unit`;
CREATE TABLE `t_sku_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(26) NOT NULL COMMENT '类型名称',
  `is_primary` int(11) DEFAULT 0 COMMENT '是否为主要计量单位',
  `sku_id` bigint(20) NOT NULL COMMENT '产品Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`sku_id`,`unit_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_specification
-- ----------------------------
DROP TABLE IF EXISTS `t_sku_specification`;
CREATE TABLE `t_sku_specification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) DEFAULT NULL COMMENT '组ID',
  `group_id` bigint(20) DEFAULT NULL COMMENT '产品ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`sku_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_specification_group
-- ----------------------------
DROP TABLE IF EXISTS `t_specification_group`;
CREATE TABLE `t_specification_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(26) NOT NULL COMMENT '规格名称',
  `pid` bigint(20) default null comment '父级ID',
  `type` varchar(26) default null comment '类型',
  PRIMARY KEY (`id`),
  foreign key (`pid`) references `t_specification_group` (`id`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_sku_price_history`;
CREATE TABLE `wms_sku_price_history` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`sku_id` bigint(20) NOT NULL COMMENT '单位Id',
`origin_price` decimal(12,4) NOT NULL COMMENT '原来价格',
`after_price` decimal(12,4) DEFAULT NULL COMMENT '更新后价格',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


