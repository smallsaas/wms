INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008975658165821444', 'Product模块', 'Product.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008975658165821441', '1008975658165821444', '查看Product', 'Product.view'),
('1008975658165821442', '1008975658165821444', '编辑Product', 'Product.edit');
