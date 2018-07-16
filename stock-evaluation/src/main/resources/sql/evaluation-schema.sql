SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for so_stock_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_evaluation`;
CREATE TABLE `t_stock_evaluation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_id` bigint(20) NOT NULL COMMENT '外键',
  `member_id` bigint(20) NOT NULL COMMENT '外键',
  `type` varchar(26) default null COMMENT '类型 评价为 商品/订单/其他',
  `note` text default NULL COMMENT '评价信息',
  `is_display` smallint default '1' COMMENT '默认显示',
  `is_stick` smallint default '0' COMMENT '是否置顶 默认不置顶',
  `status` varchar(26) default null COMMENT '状态',
  `create_time` timestamp not null default current_timestamp COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_stock_evaluation_image`;
CREATE TABLE `t_stock_evaluation_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `evaluate_id` bigint(20) default NULL COMMENT '外键',
  `image` varchar(255) default null COMMENT '图片',
  `status` varchar(26) default null COMMENT '状态',
  `create_time` timestamp not null default current_timestamp COMMENT '创建时间',
  `evaluation_addition_id` bigint(20) default NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_stock_evaluation_star`;
CREATE TABLE `t_stock_evaluation_star` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `evaluate_id` bigint(20) NOT NULL COMMENT '外键',
  `star_name` varchar(255) default null COMMENT '星级名称',
  `star_value` smallint default null COMMENT '星级值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_stock_evaluation_addition
-- ----------------------------
DROP TABLE IF EXISTS `t_stock_evaluation_addition`;
CREATE TABLE `t_stock_evaluation_addition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `evaluate_id` bigint(20) NOT NULL COMMENT '外键',
  `note` text NOT NULL COMMENT '评价信息',
  `status` varchar(26) default null COMMENT '状态',
  `is_display` smallint default '1' COMMENT '默认显示',
  `create_time` timestamp not null default current_timestamp COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






