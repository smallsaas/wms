INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1018750859749736452', 'StockFavorite模块', 'StockFavorite.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1018750859749736449', '1018750859749736452', '查看StockFavorite', 'StockFavorite.view'),
('1018750859749736450', '1018750859749736452', '编辑StockFavorite', 'StockFavorite.edit');
