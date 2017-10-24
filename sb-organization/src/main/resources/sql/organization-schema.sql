SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
`id` bigint NOT NULL AUTO_INCREMENT,
`code` varchar(26) DEFAULT NULL COMMENT '部门编号'
`is_org` int(11) DEFAULT NULL COMMENT '是否机构',
`pid` bigint DEFAULT NULL ,
`name` varchar(26) NOT NULL COMMENT '简称',
`full_name` varchar(26) DEFAULT NULL COMMENT '全称',
`location` text DEFAULT NULL COMMENT '位置',
`note` text DEFAULT NULL COMMENT '备注',
`sort` int(11) DEFAULT NULL COMMENT '排序号',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position` (
`id` bigint NOT NULL AUTO_INCREMENT,
`name` varchar(26) NOT NULL  COMMENT '备注',
`note` text DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff` (
`id` bigint NOT NULL AUTO_INCREMENT,
`dept_id` bigint NOT NULL COMMENT '所属部门',
`work_age` int(11) DEFAULT NULL COMMENT '工龄',
`profile_id` bigint DEFAULT NULL ,
`position_id` bigint DEFAULT NULL COMMENT '所属职位',
`note` text DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

