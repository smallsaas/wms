SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_shopping_cart`;
CREATE TABLE `t_shopping_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL comment '用户ID',
  `product_id` bigint(20) DEFAULT NULL comment '产品ID',
  `product_name` varchar(100) DEFAULT NULL comment '产品名称',
  `cover` varchar(200) DEFAULT NULL comment '产品封面',
  `quantity` int(11) NOT NULL DEFAULT '1' comment '产品数量',
  `total_price` decimal(10,4) NOT NULL DEFAULT '0.00' comment '总价',
  `created_date` datetime DEFAULT NULL comment '创建时间',
  `product_specification_id` bigint(20) DEFAULT NULL comment '产品规格ID',
  `product_specification_name` varchar(200) DEFAULT NULL comment '规格名称',
  `fare_id` bigint(20) DEFAULT NULL comment '商家/经营者ID',
  `weight` DECIMAL(10,4) DEFAULT '0' comment '重量',
  `bulk` int(11) DEFAULT '0' comment '是否批发',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

