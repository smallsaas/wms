SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_operation_log`;
CREATE TABLE `t_operation_log` (
`id` bigint(20) NOT NULL,
`log_type` varchar(50) DEFAULT NULL COMMENT '日志类型',
`log_name` varchar(200) DEFAULT NULL COMMENT '日志名称',
`user_id` bigint(20) DEFAULT NULL ,
`user_name` varchar(200) DEFAULT NULL,
`class_name` varchar(200) DEFAULT NULL COMMENT '类名称',
`method` varchar(200) DEFAULT NULL COMMENT '方法名称',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`succeed` varchar(200) DEFAULT NULL COMMENT '成功与否',
`message` text DEFAULT NULL COMMENT '附带信息',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

