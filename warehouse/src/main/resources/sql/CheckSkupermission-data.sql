INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1010407576199507977', 'CheckSku模块', 'CheckSku.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1010407576199507974', '1010407576199507977', '查看CheckSku', 'CheckSku.view'),
('1010407576199507975', '1010407576199507977', '编辑CheckSku', 'CheckSku.edit');
