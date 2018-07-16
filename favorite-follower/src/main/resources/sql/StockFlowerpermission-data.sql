INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1018750859749736456', 'StockFlower模块', 'StockFlower.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1018750859749736453', '1018750859749736456', '查看StockFlower', 'StockFlower.view'),
('1018750859749736454', '1018750859749736456', '编辑StockFlower', 'StockFlower.edit');
