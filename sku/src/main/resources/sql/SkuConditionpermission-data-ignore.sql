INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1019396047916347396', 'SkuCondition模块', 'SkuCondition.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1019396047916347393', '1019396047916347396', '查看SkuCondition', 'SkuCondition.view'),
('1019396047916347394', '1019396047916347396', '编辑SkuCondition', 'SkuCondition.edit');
