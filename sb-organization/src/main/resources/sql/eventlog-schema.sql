SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_operation_log`;
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

