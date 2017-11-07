DROP TABLE IF EXISTS `equipment_template`;
DROP TABLE IF EXISTS `equipment`;

CREATE TABLE IF NOT EXISTS `equipment_template` ( /* 设备模板，字段和设备表一样，
    原因：1.比如仓库ID，库区ID，储位等这些字段，可能想批量增加的时候，凡是此模板的设备，都想放到某个仓库的某个库区的某个储位，而不想每次生成都手动指定
          2.比如规格型号，如果只在schema表中定义此字段，假设某个模板是IPHONE7，规格假设第1批生成的设备虽然是IPHONE7 32G，但在第一次批量生成设备的时候
          这批设备是中国制造的，第二次批量生成设备的时候这批设备是日本制造的，此时让用户可以输入规格型号，这就需要equipment表也有规格型号字段 */
`id` BIGINT(20) NOT NULL COMMENT '主键',
`number` VARCHAR(50) NOT NULL COMMENT '模板编码，此编码是生成设备编码的其中一部分',
`code` VARCHAR(50)DEFAULT NULL COMMENT '设备编码'	,
`name` VARCHAR(50) DEFAULT NULL COMMENT '设备名称',
`spec` VARCHAR(20) DEFAULT NULL COMMENT '规格型号',
`material` VARCHAR(250) DEFAULT NULL COMMENT '材质',
`department_id`	BIGINT(20) DEFAULT NULL COMMENT  ' 归属部门 外键',
`department_name` VARCHAR(50) DEFAULT NULL COMMENT  '部门名称',
`unit` VARCHAR(20) DEFAULT NULL COMMENT '计量单位',
`note` text DEFAULT NULL COMMENT '备注	',
`brand` VARCHAR(50) DEFAULT NULL COMMENT '品牌名称',
`supplier` VARCHAR(250) DEFAULT NULL COMMENT '供应商名称',
`warehouse_id` BIGINT(20) DEFAULT NULL COMMENT '仓库ID',
`warehouse_area_id` BIGINT(20) DEFAULT NULL COMMENT '库区ID',
`warehouse_slot_id` BIGINT(20) DEFAULT NULL COMMENT '储位ID',
`machine_code` VARCHAR(50) DEFAULT NULL COMMENT'主机编码',
`system` VARCHAR(50) DEFAULT NULL COMMENT '设备系统',
`status` VARCHAR(20) DEFAULT NULL COMMENT '当前状态',
`factory` VARCHAR(250)DEFAULT NULL COMMENT ' 制造厂',
`installation_site`	VARCHAR(250) DEFAULT NULL COMMENT '安装位置',
`start_time` date DEFAULT NULL COMMENT '启用时间',
`type` int DEFAULT NULL COMMENT 'ABC分类',
`produce_time` date DEFAULT NULL COMMENT '出厂时间',
`main` int(2) DEFAULT NULL COMMENT '设备主次',
`category_id` BIGINT(20) DEFAULT NULL COMMENT '类别',
`category_name` varchar(250) DEFAULT NULL COMMENT '类别名称，显示类别名称的时候不需要去category表查询，无论category相应的类别怎么变化，是删除还是改名，这里显示的都是添加设备模板时的类别名',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `equipment` ( /* 设备 */
`id` BIGINT(20) NOT NULL COMMENT '主键',
`template_id` BIGINT(20) COMMENT '模板ID，外键',
`number` VARCHAR(50) NOT NULL COMMENT '模板编码，此编码是生成设备编码的其中一部分',
`number_of_code` INT COMMENT '设备流水号的数字形式，当批量生成的时候，需找出表中最大的number_of_code，从number_of_code+1开始生成',
`batch_number` INT COMMENT '批次,用于指定该设备是由第几次批量生成来创建的，方便按批更新',
`code` VARCHAR(50)DEFAULT NULL COMMENT '设备编码，格式：IT M I 00021（IT 电子设备类别 M 手机类别 I 苹果类别 00021 设备流水号）'	,
`name` VARCHAR(50) DEFAULT NULL COMMENT  '设备名称',
`spec` VARCHAR(20) DEFAULT NULL COMMENT  '规格型号',
`material` VARCHAR(250) DEFAULT NULL COMMENT  '材质',
`department_id`	BIGINT(20) DEFAULT NULL COMMENT  ' 归属部门 外键',
`department_name` VARCHAR(50) DEFAULT NULL COMMENT  '部门名称',
`unit` VARCHAR(20) DEFAULT NULL COMMENT '计量单位',
`note` text DEFAULT NULL COMMENT '备注	',
`brand` VARCHAR(50) DEFAULT NULL COMMENT '品牌名称',
`supplier` VARCHAR(250) DEFAULT NULL COMMENT '供应商名称',
`warehouse_id` BIGINT(20) DEFAULT NULL COMMENT '仓库ID',
`warehouse_area_id` BIGINT(20) DEFAULT NULL COMMENT '库区ID',
`warehouse_slot_id` BIGINT(20) DEFAULT NULL COMMENT '储位ID',
`machine_code` VARCHAR(50) DEFAULT NULL COMMENT '主机编码',
`system` VARCHAR(50) DEFAULT NULL COMMENT '设备系统',
`status` VARCHAR(20) DEFAULT NULL COMMENT '当前状态',
-- 要加
-- `fault_status` VARCHAR(20) DEFAULT NULL COMMENT '故障状态',
`factory` VARCHAR(250)DEFAULT NULL COMMENT '制造厂',
`installation_site`	VARCHAR(250) DEFAULT NULL COMMENT '安装位置',
`start_time` date DEFAULT NULL COMMENT '启用时间',
`type` int DEFAULT NULL COMMENT 'ABC分类',
`produce_time` date DEFAULT NULL COMMENT '出厂时间',
`main` int(2) DEFAULT NULL COMMENT '设备主次',
`category_id` BIGINT(20) DEFAULT NULL COMMENT '类别',
`category_name` varchar(250) DEFAULT NULL COMMENT '类别名称，显示类别名称的时候不需要去category表查询，无论category相应的类别怎么变化，是删除还是改名，这里显示的都是生成设备时的类别名',
 PRIMARY KEY (`id`),
 FOREIGN KEY (`template_id`) REFERENCES equipment_template (`id`) on delete set null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
