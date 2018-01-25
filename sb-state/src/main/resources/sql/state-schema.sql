
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id` bigint(20) NOT NULL,
  `code` varchar(26) NOT NULL COMMENT 'Country Code',
  `lang` varchar(5) NOT NULL COMMENT 'Of Language(zh,en)',
  `name` varchar(30) NOT NULL COMMENT 'Country Name',
  `full_name` varchar(50) DEFAULT NULL COMMENT 'Full Country Name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
