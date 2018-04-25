
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
`id` bigint NOT NULL,
`code` varchar(26) NOT NULL COMMENT '部门编号',
`is_org` int(11) DEFAULT NULL COMMENT '是否机构',
`pid` bigint DEFAULT NULL ,
`name` varchar(26) NOT NULL COMMENT '简称',
`full_name` varchar(26) DEFAULT NULL COMMENT '全称',
`location` text DEFAULT NULL COMMENT '位置',
`note` text DEFAULT NULL COMMENT '备注',
`sort` int(11) DEFAULT NULL COMMENT '排序号',
`version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
UNIQUE (`code`),PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position` (
`id` bigint NOT NULL,
`name` varchar(26) NOT NULL  COMMENT '名称',
`note` text DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff` (
`id` bigint NOT NULL,
`dept_id` bigint NOT NULL COMMENT '所属部门',
`work_age` int(11) DEFAULT NULL COMMENT '工龄',
`hire_date` datetime DEFAULT NULL COMMENT '入职时间',
`leave_date` datetime DEFAULT NULL COMMENT '离职时间',
`status` varchar(26) DEFAULT NULL COMMENT '在职状态',
`profile_id` bigint DEFAULT NULL ,
`position_id` bigint DEFAULT NULL COMMENT '所属职位',
`user_id` bigint DEFAULT NULL COMMENT '用户ID, 如果没有关联则为NULL',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`note` text DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_department_staff`;
CREATE TABLE `t_department_staff` (
`id` bigint NOT NULL,
`department_id` bigint NOT NULL COMMENT '部门ID',
`staff_id` bigint NOT NULL COMMENT '员工ID',
`is_manager` varchar(26) DEFAULT NULL COMMENT '是否主管',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




