SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_profile
-- ----------------------------
DROP TABLE IF EXISTS `t_profile`;
CREATE TABLE `t_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(26) DEFAULT NULL COMMENT '姓名',
  `nick` varchar(26) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(26) DEFAULT NULL COMMENT '性别',
  `phone` varchar(26) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(26) DEFAULT NULL COMMENT '手机',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `address` varchar(26) DEFAULT NULL COMMENT '地址',
  `height` varchar DEFAULT NULL comment '身高',
  `age` INT  DEFAULT  NULL  comment '年龄',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `wechat` varchar(26) DEFAULT NULL COMMENT '微信',
  `qq` varchar(26) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(26) DEFAULT NULL COMMENT '邮箱',
  `signature` varchar(26) DEFAULT NULL COMMENT '个性签名',
  `idcard_no` bigint(20) DEFAULT NULL COMMENT '身份证号码',
  `idcard_front` varchar(26) DEFAULT NULL COMMENT '身份证正面',
  `idcard_back` varchar(26) DEFAULT NULL COMMENT '身份证背面',
  `bankcard_no` bigint(20) DEFAULT NULL COMMENT '银行卡帐户',
  `bankcard_snapshot` varchar(26) DEFAULT NULL COMMENT '银行卡图片',
  `field1` varchar(26) DEFAULT NULL COMMENT '预留字段',
  `field2` varchar(26) DEFAULT NULL COMMENT '预留字段',
  `field3` varchar(26) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_profile
-- ----------------------------

-- ----------------------------
-- Table structure for t_resume
-- ----------------------------
DROP TABLE IF EXISTS `t_resume`;
CREATE TABLE `t_resume` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `work_area` varchar(26) DEFAULT NULL COMMENT '工作地点',
  `jb_time` date DEFAULT NULL COMMENT '兼职时间',
  `self_evaluate` varchar(26) DEFAULT NULL COMMENT '个人评价',
  `work_time` varchar(26) DEFAULT NULL COMMENT '工作时间',
  `work_company` varchar(26) DEFAULT NULL COMMENT '工作公司',
  `work_post` varchar(26) DEFAULT NULL COMMENT '工作岗位',
  `school` varchar(26) DEFAULT NULL COMMENT '学校',
  `major` varchar(26) DEFAULT NULL COMMENT '专业',
  `education` varchar(26) DEFAULT NULL COMMENT '学历',
  `school_time` date DEFAULT NULL COMMENT '在校时间',
  `school_exp` varchar(26) DEFAULT NULL COMMENT '在校经历',
  `field1` varchar(26) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(26) DEFAULT NULL COMMENT '保留字段',
  `field3` varchar(26) DEFAULT NULL COMMENT '保留字段',
  `field4` varchar(26) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resume
-- ----------------------------

-- ----------------------------
-- Table structure for t_resume
-- ----------------------------
DROP TABLE IF EXISTS `t_contact`;
CREATE TABLE `t_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact` varchar(26) DEFAULT NULL COMMENT '联系人',
  `mobile` varchar(26) DEFAULT NULL COMMENT '手机号码',
  `phone` varchar(26) DEFAULT NULL COMMENT '电话',
  `qq` varchar(26) DEFAULT NULL COMMENT 'QQ',
  `wechat` varchar(26) DEFAULT NULL COMMENT '微信',
  `email` varchar(50) DEFAULT NULL COMMENT '邮件',
  `field1` varchar(26) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(26) DEFAULT NULL COMMENT '保留字段',
  `field3` varchar(26) DEFAULT NULL COMMENT '保留字段',
  `field4` varchar(26) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resume
-- ----------------------------


-- ----------------------------
-- Table structure for t_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `t_enterprise`;
CREATE TABLE `t_enterprise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `industry` varchar(26) DEFAULT NULL COMMENT '商家行业',
  `site` varchar(26) DEFAULT NULL COMMENT '商家地址',
  `brief` text DEFAULT NULL COMMENT '商家简介',
  `license_code` varchar(26) DEFAULT NULL COMMENT '营业执照',
  `license_image` varchar(26) DEFAULT NULL COMMENT '执照照片',
  `bank` varchar(26) DEFAULT NULL COMMENT '开户银行',
  `bank_account` varchar(26) DEFAULT NULL COMMENT '银行帐号',
  `idcard_no` bigint(20) DEFAULT NULL COMMENT '身份证号码',
  `idcard_image` varchar(26) DEFAULT NULL COMMENT '身份证照片',
  `contact` varchar(26) DEFAULT NULL COMMENT '法人代表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;