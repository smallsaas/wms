INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1062889478554804233', 'Trader模块', 'Trader.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1062889478554804230', '1062889478554804233', '查看Trader', 'Trader.view'),
('1062889478554804231', '1062889478554804233', '编辑Trader', 'Trader.edit');
