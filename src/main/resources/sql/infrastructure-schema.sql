SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
`id` bigint NOT NULL AUTO_INCREMENT,
`log_type` varchar(26) DEFAULT NULL COMMENT '日志类型',
`log_name` varchar(26) DEFAULT NULL COMMENT '日志名称',
`user_id` varchar(26) DEFAULT NULL ,
`class_name` varchar(26) DEFAULT NULL COMMENT '类名称',
`method` varchar(26) DEFAULT NULL COMMENT '方法名称',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`succeed` varchar(26) DEFAULT NULL COMMENT '成功与否',
`message` text DEFAULT NULL COMMENT '附带信息',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
`id` bigint NOT NULL AUTO_INCREMENT,
`sort` bigint DEFAULT NULL COMMENT '排序',
`pid` bigint DEFAULT NULL ,
`name` varchar(26) DEFAULT NULL COMMENT '简称',
`full_name` varchar(26) DEFAULT NULL COMMENT '全称',
`desc` text DEFAULT NULL COMMENT '提示',
`version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
`id` bigint NOT NULL AUTO_INCREMENT,
`dept_id` bigint DEFAULT NULL ,
`profile_id` bigint DEFAULT NULL ,
`work_age` int(11) DEFAULT NULL COMMENT '工龄',
`position` varchar(26) DEFAULT NULL COMMENT '职位',
`note` text DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
