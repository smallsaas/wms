INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1010043805954310148', 'Transfer模块', 'Transfer.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1010043805954310145', '1010043805954310148', '查看Transfer', 'Transfer.view'),
('1010043805954310146', '1010043805954310148', '编辑Transfer', 'Transfer.edit');
