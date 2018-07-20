INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009975534022406149', 'Warehouse模块', 'Warehouse.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009975534022406146', '1009975534022406149', '查看Warehouse', 'Warehouse.view'),
('1009975534022406147', '1009975534022406149', '编辑Warehouse', 'Warehouse.edit');
