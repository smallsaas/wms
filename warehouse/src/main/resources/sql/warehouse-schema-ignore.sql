SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `wms_storage_in`;
CREATE TABLE `wms_storage_in` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_code` varchar(255) NOT NULL COMMENT '操作编号',
  `transaction_type` varchar(26) NOT NULL COMMENT '操作类型',
  `storage_in_time` datetime not null comment 'in time',
  `transaction_time` timestamp not null default current_timestamp COMMENT '操作时间',
  `batch_no` varchar(255) DEFAULT NULL COMMENT '批次',
  `warehouse_id` bigint(20) NOT NULL COMMENT '仓库',
  `slot_id` bigint(20) DEFAULT NULL COMMENT '储位',
  `note` text COMMENT '备注',
  `status` varchar(26) NOT NULL COMMENT '状态',
  `transaction_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  `originator_id` bigint(20) NOT NULL COMMENT '制单人ID',
  `originator_name` varchar(255) NOT NULL COMMENT '制单人',
  `readjust_cost_price` decimal(12,4) DEFAULT NULL COMMENT '入库成本调整',
  `procurement_id` bigint(20) DEFAULT NULL COMMENT '采购订单信息，采购与入库是一对多的关系，非采购入库时，采购的ID为null',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `out_order_num` varchar(32) DEFAULT null COMMENT '订单号信息',
  `distributor_customer` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分销商客户',
  PRIMARY KEY (`id`),
  UNIQUE (`transaction_code`),
  FOREIGN KEY (`warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `wms_storage_in_item`;
CREATE TABLE `wms_storage_in_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `storage_in_id` bigint(20) NOT NULL COMMENT '入库ID',
  `sku_id` bigint(20) NOT NULL COMMENT 'SKUID',
  `transaction_sku_price` decimal(12,4) DEFAULT NULL COMMENT '出入库价格',
  `transaction_quantities` int(11) NOT NULL COMMENT '操作数量',
  `after_transaction_quantities` int(11) NOT NULL default 0 COMMENT '操作后数量',
  `transaction_time` datetime DEFAULT NULL COMMENT '操作时间',
  `type` varchar(26) DEFAULT 'Others' COMMENT '操作类型',
  `relation_code` varchar(255) NOT NULL COMMENT '关联的操作编号',
   `demand_quantities` int(11) NOT NULL COMMENT '需求数量',
   `sku_tax_price` decimal(12,4) DEFAULT NULL COMMENT '含税价格',
   `sku_total_tax_price` decimal(12,4) DEFAULT NULL COMMENT '含税总价',
   `before_transaction_quantities`  int(11) NOT NULL DEFAULT '0' COMMENT '操作前数量',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`sku_id`) REFERENCES `wms_sku_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `wms_storage_out`;
CREATE TABLE `wms_storage_out` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_code` varchar(255) NOT NULL COMMENT '操作编号',
  `transaction_type` varchar(26) NOT NULL COMMENT '操作类型',
  `transaction_time` timestamp not null default current_timestamp COMMENT '操作时间',
  `storage_out_time` datetime not null comment 'out time',
  `batch_no` varchar(255) DEFAULT NULL COMMENT '批次',
  `note` text COMMENT '备注',
  `status` varchar(26) NOT NULL COMMENT '状态',
  `transaction_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  `originator_id` bigint(20) NOT NULL COMMENT '制单人ID',
  `originator_name` varchar(255) NOT NULL COMMENT '制单人',
  `warehouse_id` bigint(20) NOT NULL COMMENT '仓库',
  `slot_id` bigint(20) DEFAULT NULL COMMENT '储位',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `sales_id` bigint(20) DEFAULT NULL COMMENT 'sales订单信息，非sales out库时，sales的ID为null',
  `out_order_num` varchar(32) DEFAULT null COMMENT '订单号信息',
  `distributor_customer` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分销商客户',
  PRIMARY KEY (`id`),
  UNIQUE (`transaction_code`),
  FOREIGN KEY (`warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_storage_out_item`;
CREATE TABLE `wms_storage_out_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `storage_out_id` bigint(20) NOT NULL COMMENT '入库ID',
  `sku_id` bigint(20) NOT NULL COMMENT 'SKUID',
  `transaction_sku_price` decimal(12,4) DEFAULT NULL COMMENT '出入库价格',
  `transaction_quantities` int(11) NOT NULL COMMENT '操作数量',
  `after_transaction_quantities` int(11) NOT NULL default 0 COMMENT '操作后数量',
  `transaction_time` datetime DEFAULT NULL COMMENT '操作时间',
  `relation_code` varchar(255) NOT NULL COMMENT '关联的操作编号',
  `type` varchar(26) DEFAULT 'Others' COMMENT '操作类型',
  `demand_quantities` int(11) NOT NULL COMMENT '需求数量',
  `before_transaction_quantities`  int(11) NOT NULL DEFAULT '0' COMMENT '操作前数量',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`storage_out_id`) REFERENCES `wms_storage_out` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_procurement`;
CREATE TABLE `wms_procurement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `procurement_code` varchar(255) NOT NULL COMMENT '采购表编号',
  `supplier_id` bigint(20) NOT NULL COMMENT '供应商ID',
  `procurement_others_payment` decimal(12,4) DEFAULT NULL COMMENT '采购其他支出',
  `procurement_discount` int(11) DEFAULT NULL COMMENT '采购折扣',
  `procurement_total` decimal(12,4) NOT NULL COMMENT '总花费',
  `procurement_time` datetime NOT NULL COMMENT '采购时间',
  `procurement_note` text COMMENT '采购备注',
  `procure_status` varchar(26) DEFAULT NULL COMMENT '状态',
  `transaction_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  `originator_id` bigint(20) NOT NULL COMMENT '制单人ID',
  `originator_name` varchar(255) NOT NULL COMMENT '制单人',
  `buyer` varchar(255) DEFAULT NULL COMMENT '采购员',
  `transaction_time` timestamp not null default current_timestamp COMMENT '操作时间',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`),
  UNIQUE (`procurement_code`),
  FOREIGN KEY (`supplier_id`) REFERENCES `wms_suppliers` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `wms_transfer`;
CREATE TABLE `wms_transfer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_code` varchar(255) NOT NULL COMMENT '操作编号',
  `from_warehouse_id` bigint(20) NOT NULL COMMENT '调出仓库ID',
  `to_warehouse_id` bigint(20) NOT NULL COMMENT '调入仓库ID',
  `transaction_time` timestamp not null default current_timestamp comment'操作时间',
  `finish_time` datetime DEFAULT NULL COMMENT '操作时间--finish',
  `storage_in_id` bigint(20) DEFAULT NULL COMMENT '入库ID',
  `storage_out_id` bigint(20) DEFAULT NULL COMMENT '出库ID',
  `note` text COMMENT '备注',
  `status` varchar(26) NOT NULL COMMENT '状态',
  `transaction_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  `originator_id` bigint(20) NOT NULL COMMENT '制单人ID',
  `originator_name` varchar(255) NOT NULL COMMENT '制单人',
  `transfer_time` datetime not null comment 'transfer time--begin',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`),
  UNIQUE (`transaction_code`),
  FOREIGN KEY (`from_warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`storage_in_id`) REFERENCES `wms_storage_in` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`storage_out_id`) REFERENCES `wms_storage_out` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`to_warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
 UNIQUE (`supplier_code`),
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `wms_refund`;
CREATE TABLE `wms_refund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_refund_code` varchar(255) NOT NULL COMMENT '退货编号',
  `product_procurement_id` bigint(20) default NULL COMMENT '采购订单ID',
  `storage_out_id` bigint(20) DEFAULT NULL COMMENT '入库ID',
  `product_refund_warehouse_id` bigint(20) NOT NULL COMMENT '退货仓库',
  `product_refund_quantities` int(11) NOT NULL COMMENT '可退数量',
  `product_refund_time` datetime NOT NULL COMMENT '退货时间',
  `product_refund_status` varchar(26) NOT NULL COMMENT '状态',
  `product_refund_note` text COMMENT '备注',
  `originator_id` bigint(20) NOT NULL COMMENT '制单人ID',
  `originator_name` varchar(255) NOT NULL COMMENT '制单人',
  `transaction_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  `transaction_time` timestamp not null default current_timestamp COMMENT '操作时间',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `supplier_id` bigint(20) NOT NULL COMMENT '供应商ID',
  PRIMARY KEY (`id`),
  UNIQUE (`product_refund_code`),
  FOREIGN KEY (`storage_out_id`) REFERENCES `wms_storage_out` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`product_procurement_id`) REFERENCES `wms_procurement` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`product_refund_warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_inventory`;
CREATE TABLE `wms_inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `warehouse_id` bigint(20) NOT NULL COMMENT '仓库ID',
  `slot_id` bigint(20) DEFAULT NULL COMMENT '储位ID',
  `max_inventory` int(11) DEFAULT NULL COMMENT '库存上限',
  `min_inventory` int(11) DEFAULT '0' COMMENT '库存下限',
  `sku_id` bigint(20) NOT NULL COMMENT '库存量ID',
  `valid_sku` int(11) DEFAULT '0' COMMENT '可用库存量',
  `advance_quantities` int(11) DEFAULT '0' COMMENT '预购量',
  `transmit_quantities` int(11) DEFAULT '0' COMMENT '在途量',
  `order_count` int(11) NOT NULL DEFAULT '0' COMMENT '占用库存量',
  PRIMARY KEY (`id`),
  UNIQUE(`warehouse_id`,`sku_id`),
  FOREIGN KEY (`sku_id`) REFERENCES `wms_sku_product` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_warehouse`;
CREATE TABLE `wms_warehouse` (
`id` bigint(20) NOT NULL  AUTO_INCREMENT,
`warehouse_code` varchar(255) NOT NULL COMMENT '仓库编号',
`warehouse_name` varchar(255) NOT NULL COMMENT '仓库名称',
`warehouse_PCD` varchar(255) NOT NULL COMMENT '仓库所在地区',
`warehouse_address` varchar(255) NOT NULL COMMENT '仓库详细地址',
`warehouse_charger` bigint(20) DEFAULT NULL COMMENT '仓库负责人',
UNIQUE(`warehouse_code`),
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
PRIMARY KEY (`id`),
FOREIGN KEY (`warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_check`;
CREATE TABLE `wms_check` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `check_code` varchar(255) NOT NULL COMMENT '盘点编号',
  `check_time` timestamp not null default current_timestamp COMMENT '盘点时间',
  `finish_time` datetime DEFAULT NULL COMMENT '操作时间--finish',
  `begin_time` datetime DEFAULT NULL COMMENT '操作时间--begin',
  `warehouse_id` bigint(20) NOT NULL COMMENT '盘点仓库ID',
  `profit_lost` int(11) NOT NULL COMMENT '盈亏(缺失值)',
  `check_note` text COMMENT '盘点备注',
  `transaction_by` varchar(100) DEFAULT NULL COMMENT '经手人',
  `originator_id` bigint(20) NOT NULL COMMENT '制单人ID',
  `originator_name` varchar(255) NOT NULL COMMENT '制单人',
  `status` varchar(20) NOT NULL COMMENT 'check status',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`),
  UNIQUE(`check_code`),
  FOREIGN KEY (`warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `wms_check_sku`;
CREATE TABLE `wms_check_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `check_id` bigint(20) NOT NULL COMMENT '盘点编号',
  `sku_id` bigint(20) NOT NULL COMMENT '盘点SKUID',
  `warehouse_id` bigint(20) NOT NULL COMMENT '仓库ID',
  `fact_quantities` int(11) DEFAULT NULL COMMENT '实际存量',
  `before_proof_quantities` int(11) DEFAULT NULL COMMENT '校对qian的数量，如果缺失值为0的话，该值同应有量相同',
  `deserved_quantities` int(11) NOT NULL COMMENT '应有存量',
  `profit_lost` int(11) DEFAULT NULL COMMENT '盈亏(缺失值)',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`),
  UNIQUE (`check_id`,`sku_id`),
  FOREIGN KEY (`sku_id`) REFERENCES `wms_sku_product` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`check_id`) REFERENCES `wms_check` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wms_trader`;
CREATE TABLE `wms_trader` (
 `id` bigint(20) NOT NULL  AUTO_INCREMENT,
 `trader_name` varchar(255) NOT NULL COMMENT '分销商名称',
 `trader_code` varchar(255) NOT NULL COMMENT '分销商编号',
 `trader_PCD` varchar(255) NOT NULL COMMENT '分销商区域',
 `trader_address` varchar(255) NOT NULL COMMENT '分销商详细地址',
 `trader_postcode` varchar(255) DEFAULT NULL COMMENT '分销商邮编',
 `trader_contact_name` varchar(255) NOT NULL COMMENT '分销商联系人姓名',
 `trader_contact_phone` varchar(26) DEFAULT NULL COMMENT '分销商联系人电话(座机)',
 `trader_contact_fax` varchar(50) DEFAULT NULL COMMENT '分销商联系人传真',
 `trader_contact_email` varchar(50) DEFAULT NULL COMMENT '分销商联系人邮箱',
 `trader_contact_position` varchar(26) DEFAULT NULL COMMENT '分销商联系人职位',
 `trader_contact_cell_phone` varchar(26) DEFAULT NULL COMMENT '分销商联系人手机',
 `trader_account_name` varchar(50) DEFAULT NULL COMMENT '分销商开户名称',
 `trader_account_bank` varchar(50) DEFAULT NULL COMMENT '分销商开户银行',
 `trader_account_bank_no` bigint(20) DEFAULT NULL COMMENT '分销商银行账号',
 `trader_invoice_title` varchar(50) DEFAULT NULL COMMENT '分销商发票抬头',
 `trader_status` varchar(26) DEFAULT NULL COMMENT '分销商状态',
 `trader_note`  text  DEFAULT NULL COMMENT '分销商备注',
 `trader_register_time` timestamp not null default current_timestamp COMMENT '分销商注册时间',
 `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
 `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
 UNIQUE (`trader_code`),
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wms_sales`;
CREATE TABLE `wms_sales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sales_code` varchar(255) NOT NULL COMMENT '销售订单',
  `trader_id` bigint(20) NOT NULL COMMENT '分销商ID',
  `sales_others_payment` decimal(12,4) DEFAULT NULL COMMENT '销售订单其他支出',
  `sales_discount` int(11) DEFAULT NULL COMMENT '销售订单折扣',
  `sales_total` decimal(12,4) NOT NULL COMMENT '销售订单花费',
  `total_count` int NOT NULL COMMENT 'product count',
  `sales_time` datetime NOT NULL default current_timestamp COMMENT '销售订单时间',
  `sales_note` text COMMENT '销售订单备注',
  `sales_status` varchar(26) DEFAULT NULL COMMENT '销售订单状态',
  `transaction_by` varchar(100) DEFAULT NULL COMMENT '销售订单操作人',
  `originator_id` bigint(20) NOT NULL COMMENT '销售订单制单人ID',
  `originator_name` varchar(255) NOT NULL COMMENT '销售订单制单人',
  `transaction_time` timestamp not null default current_timestamp COMMENT '操作时间',
  `delivered_address` varchar(255) not null  COMMENT '收货地址',
  `field1` varchar(255) DEFAULT NULL COMMENT '保留字段',
  `field2` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`),
  UNIQUE (`sales_code`),
  FOREIGN KEY (`trader_id`) REFERENCES `wms_trader` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








