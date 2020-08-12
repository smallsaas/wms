INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009975122221363205', 'Suppliers模块', 'Suppliers.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009975122221363202', '1009975122221363205', '查看Suppliers', 'Suppliers.view'),
('1009975122221363203', '1009975122221363205', '编辑Suppliers', 'Suppliers.edit');
