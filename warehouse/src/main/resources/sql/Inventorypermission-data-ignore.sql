INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1010414266236137477', 'Inventory模块', 'Inventory.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1010414266236137474', '1010414266236137477', '查看Inventory', 'Inventory.view'),
('1010414266236137475', '1010414266236137477', '编辑Inventory', 'Inventory.edit');
