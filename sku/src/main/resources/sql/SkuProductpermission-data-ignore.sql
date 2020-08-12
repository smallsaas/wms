INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1019396048600018957', 'SkuProduct模块', 'SkuProduct.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1019396048600018954', '1019396048600018957', '查看SkuProduct', 'SkuProduct.view'),
('1019396048600018955', '1019396048600018957', '编辑SkuProduct', 'SkuProduct.edit');
