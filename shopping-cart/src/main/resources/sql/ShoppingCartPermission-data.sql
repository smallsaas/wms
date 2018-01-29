SET FOREIGN_KEY_CHECKS=0;

INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('957169355110281219', 'ShoppingCart模块', 'ShoppingCart.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('957169354976063489', '957169355110281219', '查看ShoppingCart', 'ShoppingCart.view'),
('957169355110281217', '957169355110281219', '更新ShoppingCart', 'ShoppingCart.update');
