
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `sb_system_config`;
CREATE TABLE `sb_system_config` (
  `id` bigint(20) NOT NULL,
  `data_key` varchar(200) NOT NULL COMMENT 'key',
  `data_value` text NOT NULL COMMENT 'value',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
