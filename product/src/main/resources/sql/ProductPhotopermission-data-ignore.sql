INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008975658165821448', 'ProductPhoto模块', 'ProductPhoto.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008975658165821445', '1008975658165821448', '查看ProductPhoto', 'ProductPhoto.view'),
('1008975658165821446', '1008975658165821448', '编辑ProductPhoto', 'ProductPhoto.edit');
