INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1019396048608407560', 'SkuTag模块', 'SkuTag.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1019396048608407557', '1019396048608407560', '查看SkuTag', 'SkuTag.view'),
('1019396048608407558', '1019396048608407560', '编辑SkuTag', 'SkuTag.edit');
