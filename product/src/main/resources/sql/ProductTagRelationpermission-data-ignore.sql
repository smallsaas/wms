INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008979415721099276', 'ProductTagRelation模块', 'ProductTagRelation.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008979415721099273', '1008979415721099276', '查看ProductTagRelation', 'ProductTagRelation.view'),
('1008979415721099274', '1008979415721099276', '编辑ProductTagRelation', 'ProductTagRelation.edit');
