
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pcd
-- ----------------------------
DROP TABLE IF EXISTS `pcd`;
CREATE TABLE `pcd` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) not null,
  `type` varchar(50) not null,
  `pid` bigint(20),
  PRIMARY KEY (`id`),
  foreign key (`pid`) references `pcd` (`id`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
