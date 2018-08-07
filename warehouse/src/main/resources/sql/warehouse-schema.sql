SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `wms_storage_in`;
CREATE TABLE `wms_storage_in` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`transaction_code` varchar(255) NOT NULL COMMENT '操作编号',
`transaction_type` varchar(26) NOT NULL COMMENT '操作类型',
`transaction_time` datetime NOT NULL COMMENT '操作时间',
`batch_no` varchar(255) DEFAULT NULL COMMENT '批次',
`warehouse_id` bigint(20) NOT NULL COMMENT '仓库',
`slot_id` bigint(20) DEFAULT NULL COMMENT '储位',
`note`  text  DEFAULT NULL COMMENT '备注',
`status` varchar(26) DEFAULT NULL COMMENT '状态',
`transaction_by` bigint(20) not NULL COMMENT '操作人',
`originator_id` bigint(20) not NULL COMMENT '制单人',
`readjust_cost_price` decimal(12,4) DEFAULT NULL COMMENT '入库成本调整',
`procurement_id` bigint(20) DEFAULT NULL COMMENT '采购订单信息，采购与入库是一对多的关系，非采购入库时，采购的ID为null',
`field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
`field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
UNIQUE(`transaction_code`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_storage_in_item`;
CREATE TABLE `wms_storage_in_item` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`storage_in_id` bigint(20) NOT NULL COMMENT '入库ID',
`sku_id` bigint(20) NOT NULL COMMENT 'SKUID',
`transaction_sku_price` decimal(12,4) default NULL COMMENT '出\入库价格',
`transaction_quantities`  int(11)  NOT NULL COMMENT '操作数量',
`transaction_time` datetime default NULL COMMENT '操作时间',
`type` varchar(26) DEFAULT 'Others' COMMENT '操作类型',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_storage_out`;
CREATE TABLE `wms_storage_out` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`transaction_code` varchar(255) NOT NULL COMMENT '操作编号',
`transaction_type` varchar(26) NOT NULL COMMENT '操作类型',
`transaction_time` datetime NOT NULL COMMENT '操作时间',
`batch_no` varchar(255) DEFAULT NULL COMMENT '批次',
`note`  text  DEFAULT NULL COMMENT '备注',
`status` varchar(26) DEFAULT NULL COMMENT '状态',
`transaction_by` bigint(20) NOT NULL COMMENT '操作人',
`originator_id` bigint(20) NOT NULL COMMENT '制单人',
`warehouse_id` bigint(20) NOT NULL COMMENT '仓库',
`slot_id` bigint(20) DEFAULT NULL COMMENT '储位',
`field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
`field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
UNIQUE(`transaction_code`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_storage_out_item`;
CREATE TABLE `wms_storage_out_item` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`storage_out_id` bigint(20) NOT NULL COMMENT '入库ID',
`sku_id` bigint(20) NOT NULL COMMENT 'SKUID',
`transaction_sku_price` decimal(12,4) DEFAULT NULL COMMENT '出\入库价格',
`transaction_quantities`  int(11)  NOT NULL COMMENT '操作数量',
`transaction_time` datetime DEFAULT NULL COMMENT '操作时间',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wms_procurement`;
CREATE TABLE `wms_procurement` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`procurement_code` varchar(255) NOT NULL COMMENT '采购表编号',
`supplier_id` bigint(20) NOT NULL COMMENT '供应商ID',
`procurement_others_payment` decimal(12,4) DEFAULT NULL COMMENT '采购其他支出',
`procurement_discount`  int  DEFAULT NULL COMMENT '采购折扣',
`procurement_total` decimal(12,4) NOT NULL COMMENT '总花费',
`procurement_time` datetime NOT NULL COMMENT '采购时间',
`procurement_note`  text  DEFAULT NULL COMMENT '采购备注',
`procure_status` varchar(26) DEFAULT NULL COMMENT '状态',
`operator` bigint(20) NOT NULL COMMENT '操作人',
`originator_id` bigint(20) NOT NULL COMMENT '制单人',
`buyer` varchar(255) default null comment '采购员',
`transaction_time` datetime NOT NULL COMMENT '操作时间',
`field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
`field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
UNIQUE(`procurement_code`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_transfer`;
CREATE TABLE `wms_transfer` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`transaction_code` varchar(255) NOT NULL COMMENT '操作编号',
`from_warehouse_id` bigint(20) NOT NULL COMMENT '调出仓库ID',
`to_warehouse_id` bigint(20) NOT NULL COMMENT '调入仓库ID',
`transaction_time` datetime NOT NULL COMMENT '操作时间',
`finish_time` datetime DEFAULT NULL COMMENT '操作时间',
`storage_in_id` bigint(20) DEFAULT NULL COMMENT '入库ID',
`storage_out_id` bigint(20) NOT NULL COMMENT '出库ID',
`note`  text  DEFAULT NULL COMMENT '备注',
`status` varchar(26) DEFAULT NULL COMMENT '状态',
`operator` bigint(20) NOT NULL COMMENT '操作人',
`originator_id` bigint(20) NOT NULL COMMENT '制单人',
`field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
`field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
UNIQUE(`transaction_code`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_suppliers`;
CREATE TABLE `wms_suppliers` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`supplier_name` varchar(255) NOT NULL COMMENT '供名称应商',
`supplier_code` varchar(255) NOT NULL COMMENT '供应商编号',
`supplier_PCD` varchar(255) NOT NULL COMMENT '供应商区域',
`supplier_address` varchar(255) NOT NULL COMMENT '供应商详细地址',
`supplier_postcode` varchar(255) DEFAULT NULL COMMENT '供应商邮编',
`supplier_contact_name` varchar(255) NOT NULL COMMENT '供应商联系人姓名',
`supplier_contact_phone` varchar(26) DEFAULT NULL COMMENT '供应商联系人电话(座机)',
`supplier_contact_fax` varchar(50) DEFAULT NULL COMMENT '供应商联系人传真',
`supplier_contact_email` varchar(50) DEFAULT NULL COMMENT '供应商联系人邮箱',
`supplier_contact_position` varchar(26) DEFAULT NULL COMMENT '供应商联系人职位',
`supplier_contact_cell_phone` varchar(26) DEFAULT NULL COMMENT '供应商联系人手机',
`supplier_account_name` varchar(50) DEFAULT NULL COMMENT '供应商开户名称',
`supplier_account_bank` varchar(50) DEFAULT NULL COMMENT '供应商开户银行',
`supplier_account_bank_no` bigint(20) DEFAULT NULL COMMENT '供应商银行账号',
`supplier_invoice_title` varchar(50) DEFAULT NULL COMMENT '供应商发票抬头',
`supplier_status` varchar(26) DEFAULT NULL COMMENT '供应商状态',
`supplier_note`  text  DEFAULT NULL COMMENT '供应商备注',
`supplier_register_time` datetime DEFAULT NULL COMMENT '供应商注册时间',
`field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
`field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
UNIQUE(`supplier_name`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wms_suppliers_product`;
CREATE TABLE `wms_suppliers_product` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`suppliers_id` bigint(20) NOT NULL  COMMENT '供应商ID',
`product_id` bigint(20) NOT NULL  COMMENT '商品ID',
 unique(`suppliers_id`,`product_id`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `wms_refund`;
CREATE TABLE `wms_refund` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`product_refund_code` varchar(255) NOT NULL COMMENT '退货编号',
`product_procurement_id` bigint(20) NOT NULL COMMENT '采购订单ID',
`storage_out_id` bigint(20) NOT NULL COMMENT '入库ID',
`product_refund_warehouse_id` bigint(20) NOT NULL COMMENT '退货仓库',
`product_refund_quantities`  int(11)  NOT NULL COMMENT '可退数量',
`product_refund_time` datetime NOT NULL COMMENT '退货时间',
`product_refund_status` varchar(26) DEFAULT NULL COMMENT '状态',
`product_refund_note`  text  DEFAULT NULL COMMENT '备注',
`originator_id` bigint(20) NOT NULL COMMENT '制单人',
`operator` bigint(20) NOT NULL COMMENT '操作人',
`transaction_time` datetime NOT NULL COMMENT '操作时间',
`field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
`field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
UNIQUE(`product_refund_code`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_inventory`;
CREATE TABLE `wms_inventory` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`warehouse_id` bigint(20) NOT NULL COMMENT '仓库ID',
`slot_id` bigint(20) DEFAULT NULL COMMENT '储位ID',
`max_inventory`  int(11)  DEFAULT NULL COMMENT '库存上限',
`min_inventory`  int(11)  DEFAULT 0 COMMENT '库存下限',
`sku_id` bigint(20) NOT NULL COMMENT '库存量ID',
`valid_sku`  int(11)  DEFAULT 0 COMMENT '可用库存量',
`advance_quantities`  int(11)  DEFAULT 0 COMMENT '预购量',
`transmit_quantities`  int(11)  DEFAULT 0 COMMENT '在途量',
PRIMARY KEY (`id`),
unique(`warehouse_id`,`sku_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_warehouse`;
CREATE TABLE `wms_warehouse` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`warehouse_code` varchar(255) NOT NULL COMMENT '仓库编号',
`warehouse_name` varchar(255) NOT NULL COMMENT '仓库名称',
`warehouse_PCD` varchar(255) NOT NULL COMMENT '仓库所在地区',
`warehouse_address` varchar(255) NOT NULL COMMENT '仓库详细地址',
`warehouse_charger` bigint(20) DEFAULT NULL COMMENT '仓库负责人',
UNIQUE(`warehouse_code`,`warehouse_name`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_warehouse_slot`;
CREATE TABLE `wms_warehouse_slot` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`slot_code` varchar(255) NOT NULL COMMENT '仓库编号',
`slot_name` varchar(255) NOT NULL COMMENT '仓库名称',
`warehouse_id` bigint(20) NOT NULL COMMENT '仓库ID',
`slot_note`  text  DEFAULT NULL COMMENT '储位说明',
UNIQUE(`slot_code`,`slot_name`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_warehouse_store`;
CREATE TABLE `wms_warehouse_store` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`warehouse_id` bigint(20) NOT NULL COMMENT '产品Id',
`store_id` bigint(20) NOT NULL COMMENT '标签ID',
UNIQUE(`warehouse_id`,`store_id`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_check`;
CREATE TABLE `wms_check` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`check_code` varchar(255) NOT NULL COMMENT '盘点编号',
`check_time` datetime NOT NULL COMMENT '盘点时间',
`profit_lost`  int(11)  NOT NULL COMMENT '盈亏(缺失值)',
`check_note`  text  DEFAULT NULL COMMENT '盘点备注',
`check_by` bigint(20) NOT NULL COMMENT '经手人',
`originator_id` bigint(20) NOT NULL COMMENT '制单人',
`field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
`field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
UNIQUE(`check_code`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_check_sku`;
CREATE TABLE `wms_check_sku` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`check_id` bigint(20) NOT NULL COMMENT '盘点编号',
`sku_id` bigint(20) NOT NULL COMMENT '盘点SKUID',
`warehouse_id` bigint(20) NOT NULL COMMENT '仓库ID',
`fact_quantities`  int(11)  NOT NULL COMMENT '实际存量',
`deserved_quantities`  int(11)  NOT NULL COMMENT '应用存量',
`field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
`field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
UNIQUE(`check_id`,`sku_id`),
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



