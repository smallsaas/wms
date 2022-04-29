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
  `org_id` bigint(20) default 0 comment 'org_id',
  `org_tag` varchar(50)   DEFAULT NULL COMMENT '隔离字段',
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
  `status` varchar(26) DEFAULT NULL COMMENT '状态',
  `sort_value` int(11) DEFAULT NULL COMMENT '排序值',
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
  `org_id` bigint(20) default 0 comment 'org_id',
  `org_tag` varchar(50)   DEFAULT NULL COMMENT '隔离字段',
  `category_name` varchar(26) NOT NULL COMMENT '类型名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `category_description` text COMMENT '类型说明',
  `category_code` varchar(26) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`category_name`,`pid`),
  unique key (`category_code`),
  foreign key (`pid`) references `t_product_category`(`id`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_condition
-- ----------------------------
DROP TABLE IF EXISTS `t_condition`;
CREATE TABLE `t_condition`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) default 0 comment 'org_id',
  `org_tag` varchar(50)   DEFAULT NULL COMMENT '隔离字段',
  `condition_name` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态名称',
  `pid` bigint NULL DEFAULT NULL COMMENT '父级ID',
  `condition_description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '名称说明',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `condition_name`(`condition_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_product_condition
-- ----------------------------
DROP TABLE IF EXISTS `t_product_condition`;
CREATE TABLE `t_product_condition`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) default 0 comment 'org_id',
  `org_tag` varchar(50)   DEFAULT NULL COMMENT '隔离字段',
  `product_id` bigint NOT NULL COMMENT '产品Id',
  `condition_id` bigint NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `product_id`(`product_id`, `condition_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for t_product_photo
-- ----------------------------
DROP TABLE IF EXISTS `t_product_photo`;
CREATE TABLE `t_product_photo`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) default 0 comment 'org_id',
  `org_tag` varchar(50)   DEFAULT NULL COMMENT '隔离字段',
  `photo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  `is_primary` int NULL DEFAULT 0 COMMENT '是否为主要',
  `product_id` bigint NOT NULL COMMENT '产品Id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `product_id`(`product_id`, `photo_url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;




-- ----------------------------
-- Table structure for t_product_unit
-- ----------------------------
DROP TABLE IF EXISTS `t_product_unit`;
CREATE TABLE `t_product_unit`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) default 0 comment 'org_id',
  `org_tag` varchar(50)   DEFAULT NULL COMMENT '隔离字段',
  `unit_name` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型名称',
  `is_primary` int NULL DEFAULT 0 COMMENT '是否为主要计量单位',
  `product_id` bigint NOT NULL COMMENT '产品Id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `product_id`(`product_id`, `unit_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_specification
-- ----------------------------
DROP TABLE IF EXISTS `t_specification`;
CREATE TABLE `t_specification`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) default 0 comment 'org_id',
  `org_tag` varchar(50)   DEFAULT NULL COMMENT '隔离字段',
  `specification_name` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规格名称',
  `specification_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '规格值',
  `group_id` bigint NULL DEFAULT NULL COMMENT '组ID',
  `product_id` bigint NULL DEFAULT NULL COMMENT '产品ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `product_id`(`product_id`, `group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for t_specification_group
-- ----------------------------
DROP TABLE IF EXISTS `t_specification_group`;
CREATE TABLE `t_specification_group`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_name` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '规格名称',
  `pid` bigint NULL DEFAULT NULL COMMENT '父级ID',
  `type` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;