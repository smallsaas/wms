INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1019396048608407568', 'SkuUnit模块', 'SkuUnit.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1019396048608407565', '1019396048608407568', '查看SkuUnit', 'SkuUnit.view'),
('1019396048608407566', '1019396048608407568', '编辑SkuUnit', 'SkuUnit.edit');
