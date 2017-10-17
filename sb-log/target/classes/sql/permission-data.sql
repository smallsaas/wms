INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('919737530935427077', 'TDepartment模块', 'TDepartment.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('919737530935427074', '919737530935427077', '查看TDepartment', 'TDepartment.view'),
('919737530935427075', '919737530935427077', '更新TDepartment', 'TDepartment.update'),
('919737530935427076', '919737530935427077', '删除TDepartment', 'TDepartment.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('919737530935427081', 'TOrganization模块', 'TOrganization.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('919737530935427078', '919737530935427081', '查看TOrganization', 'TOrganization.view'),
('919737530935427079', '919737530935427081', '更新TOrganization', 'TOrganization.update'),
('919737530935427080', '919737530935427081', '删除TOrganization', 'TOrganization.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('919737530935427085', 'TPosition模块', 'TPosition.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('919737530935427082', '919737530935427085', '查看TPosition', 'TPosition.view'),
('919737530935427083', '919737530935427085', '更新TPosition', 'TPosition.update'),
('919737530935427084', '919737530935427085', '删除TPosition', 'TPosition.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('919737530952204290', 'TStaff模块', 'TStaff.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('919737530935427086', '919737530952204290', '查看TStaff', 'TStaff.view'),
('919737530935427087', '919737530952204290', '更新TStaff', 'TStaff.update'),
('919737530935427088', '919737530952204290', '删除TStaff', 'TStaff.delete');
