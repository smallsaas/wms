/*
Navicat MySQL Data Transfer

Source Server         : Silent-Y
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : sbstatement

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-11-06 10:02:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_statement
-- ----------------------------
DROP TABLE IF EXISTS `t_statement`;
CREATE TABLE `t_statement` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `mothod` varchar(255) NOT NULL COMMENT '方法',
  `sql` text NOT NULL COMMENT 'sql语句',
  `identifier` varchar(255) NOT NULL COMMENT '标识符',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_statement
-- ----------------------------

