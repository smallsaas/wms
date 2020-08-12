INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1010407576199507973', 'Check模块', 'Check.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1010407576199507970', '1010407576199507973', '查看Check', 'Check.view'),
('1010407576199507971', '1010407576199507973', '编辑Check', 'Check.edit');
