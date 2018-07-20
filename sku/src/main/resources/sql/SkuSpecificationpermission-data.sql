INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1019396048608407556', 'SkuSpecification模块', 'SkuSpecification.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1019396048608407553', '1019396048608407556', '查看SkuSpecification', 'SkuSpecification.view'),
('1019396048608407554', '1019396048608407556', '编辑SkuSpecification', 'SkuSpecification.edit');
