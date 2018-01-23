DROP TABLE IF EXISTS `t_view_history`;
CREATE TABLE `t_view_history` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `member_id` bigint(20) NOT NULL COMMENT '外键/用户',
  `roles_id` bigint(20) NOT NULL COMMENT '商店或者料理店的ID',
  `roles` VARCHAR(26) DEFAULT NULL comment '角色 与 ID 组合 KEY ',
  `view_time` datetime NOT NULL COMMENT '浏览时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浏览记录表';