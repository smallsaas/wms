INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008979415721099272', 'ProductTag模块', 'ProductTag.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008979415721099269', '1008979415721099272', '查看ProductTag', 'ProductTag.view'),
('1008979415721099270', '1008979415721099272', '编辑ProductTag', 'ProductTag.edit');
