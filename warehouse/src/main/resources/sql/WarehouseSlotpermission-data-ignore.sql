INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009975534420865029', 'WarehouseSlot模块', 'WarehouseSlot.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009975534420865026', '1009975534420865029', '查看WarehouseSlot', 'WarehouseSlot.view'),
('1009975534420865027', '1009975534420865029', '编辑WarehouseSlot', 'WarehouseSlot.edit');
