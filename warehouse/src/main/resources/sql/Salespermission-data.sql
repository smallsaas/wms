INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1062889478554804229', 'Sales模块', 'Sales.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1062889478554804226', '1062889478554804229', '查看Sales', 'Sales.view'),
('1062889478554804227', '1062889478554804229', '编辑Sales', 'Sales.edit');
