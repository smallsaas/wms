INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009000436406784005', 'ProductCategory模块', 'ProductCategory.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009000436406784002', '1009000436406784005', '查看ProductCategory', 'ProductCategory.view'),
('1009000436406784003', '1009000436406784005', '编辑ProductCategory', 'ProductCategory.edit');
