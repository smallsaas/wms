INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008975658165821452', 'ProductUnit模块', 'ProductUnit.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008975658165821449', '1008975658165821452', '查看ProductUnit', 'ProductUnit.view'),
('1008975658165821450', '1008975658165821452', '编辑ProductUnit', 'ProductUnit.edit');
