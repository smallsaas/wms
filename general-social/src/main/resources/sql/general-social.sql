/*
Navicat MySQL Data Transfer

Source Server         : jacob
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : sbinfrastructure

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-11-14 11:59:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for so_member_black
-- ----------------------------
DROP TABLE IF EXISTS `so_member_black`;
CREATE TABLE `so_member_black` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `member_id` bigint(20) NOT NULL COMMENT '拉黑用户',
  `member_black_id` bigint(20) NOT NULL COMMENT '被拉黑用户',
  `create_time` datetime NOT NULL COMMENT '拉黑时间',
  `cause` text NOT NULL COMMENT '通用字段'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for so_member_block
-- ----------------------------
DROP TABLE IF EXISTS `so_member_block`;
CREATE TABLE `so_member_block` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `member_id` bigint(20) NOT NULL COMMENT '屏蔽用户',
  `reference_id` bigint(20) NOT NULL COMMENT '被屏蔽的ID',
  `create_time` datetime NOT NULL COMMENT '拉黑时间',
  `cause` text NOT NULL COMMENT '通用字段'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for so_member_care
-- ----------------------------
DROP TABLE IF EXISTS `so_member_care`;
CREATE TABLE `so_member_care` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `member_id` bigint(20) NOT NULL COMMENT '外键/用户',
  `follower_id` bigint(20) NOT NULL COMMENT '外键/用户/粉丝ID',
  `follow_time` datetime NOT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for so_member_report
-- ----------------------------
DROP TABLE IF EXISTS `so_member_report`;
CREATE TABLE `so_member_report` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `member_id` bigint(20) NOT NULL COMMENT '举报用户',
  `reference_id` bigint(20) NOT NULL COMMENT '被举报资源ID(用户/动态/活动)',
  `cause_id` text NOT NULL COMMENT '举报原因 [独立表]',
  `desc` text NOT NULL COMMENT '举报内容',
  `create_time` datetime NOT NULL COMMENT '举报时间',
  `deal_result` varchar(255) NOT NULL COMMENT '处理结果',
  `deal_time` datetime NOT NULL COMMENT '处理时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for so_member_report_type
-- ----------------------------
DROP TABLE IF EXISTS `so_member_report_type`;
CREATE TABLE `so_member_report_type` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '类别/唯一/unique',
  `pid` bigint(20) NOT NULL COMMENT '父级ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for so_member_trends
-- ----------------------------
DROP TABLE IF EXISTS `so_member_trends`;
CREATE TABLE `so_member_trends` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `member_id` bigint(20) NOT NULL COMMENT '外键/用户ID',
  `trends` varchar(255) NOT NULL COMMENT '动态内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `field1` varchar(255) NOT NULL COMMENT '保留字段',
  `field2` varchar(255) NOT NULL COMMENT '保留字段',
  `field3` varchar(255) NOT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for so_stock_collect
-- ----------------------------
DROP TABLE IF EXISTS `so_stock_collect`;
CREATE TABLE `so_stock_collect` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `member_id` bigint(20) NOT NULL COMMENT '用户ID',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `create_time` datetime NOT NULL COMMENT '收藏时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for so_stock_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `so_stock_evaluation`;
CREATE TABLE `so_stock_evaluation` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `stock_id` bigint(20) NOT NULL COMMENT '外键',
  `member_id` bigint(20) NOT NULL COMMENT '外键',
  `note` text NOT NULL COMMENT '评价信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for so_stock_evaluation_addition
-- ----------------------------
DROP TABLE IF EXISTS `so_stock_evaluation_addition`;
CREATE TABLE `so_stock_evaluation_addition` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `evaluate_id` bigint(20) NOT NULL COMMENT '外键',
  `note` text NOT NULL COMMENT '评价信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
