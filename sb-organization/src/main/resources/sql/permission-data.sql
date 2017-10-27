INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045032', 'Department模块', 'Department.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045029', '923723633401045032', '查看Department', 'Department.view'),
('923723633401045030', '923723633401045032', '更新Department', 'Department.update'),
('923723633401045031', '923723633401045032', '删除Department', 'Department.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401044996', 'Dept模块', 'Dept.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633363296257', '923723633401044996', '查看Dept', 'Dept.view'),
('923723633401044994', '923723633401044996', '更新Dept', 'Dept.update'),
('923723633401044995', '923723633401044996', '删除Dept', 'Dept.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045000', 'Dict模块', 'Dict.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401044997', '923723633401045000', '查看Dict', 'Dict.view'),
('923723633401044998', '923723633401045000', '更新Dict', 'Dict.update'),
('923723633401044999', '923723633401045000', '删除Dict', 'Dict.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045020', 'Group模块', 'Group.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045017', '923723633401045020', '查看Group', 'Group.view'),
('923723633401045018', '923723633401045020', '更新Group', 'Group.update'),
('923723633401045019', '923723633401045020', '删除Group', 'Group.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045004', 'Log模块', 'Log.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045001', '923723633401045004', '查看Log', 'Log.view'),
('923723633401045002', '923723633401045004', '更新Log', 'Log.update'),
('923723633401045003', '923723633401045004', '删除Log', 'Log.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045008', 'Notice模块', 'Notice.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045005', '923723633401045008', '查看Notice', 'Notice.view'),
('923723633401045006', '923723633401045008', '更新Notice', 'Notice.update'),
('923723633401045007', '923723633401045008', '删除Notice', 'Notice.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045036', 'Organization模块', 'Organization.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045033', '923723633401045036', '查看Organization', 'Organization.view'),
('923723633401045034', '923723633401045036', '更新Organization', 'Organization.update'),
('923723633401045035', '923723633401045036', '删除Organization', 'Organization.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045012', 'Pcd模块', 'Pcd.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045009', '923723633401045012', '查看Pcd', 'Pcd.view'),
('923723633401045010', '923723633401045012', '更新Pcd', 'Pcd.update'),
('923723633401045011', '923723633401045012', '删除Pcd', 'Pcd.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045028', 'Perm模块', 'Perm.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045025', '923723633401045028', '查看Perm', 'Perm.view'),
('923723633401045026', '923723633401045028', '更新Perm', 'Perm.update'),
('923723633401045027', '923723633401045028', '删除Perm', 'Perm.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045040', 'Position模块', 'Position.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045037', '923723633401045040', '查看Position', 'Position.view'),
('923723633401045038', '923723633401045040', '更新Position', 'Position.update'),
('923723633401045039', '923723633401045040', '删除Position', 'Position.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045056', 'Role模块', 'Role.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045053', '923723633401045056', '查看Role', 'Role.view'),
('923723633401045054', '923723633401045056', '更新Role', 'Role.update'),
('923723633401045055', '923723633401045056', '删除Role', 'Role.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045044', 'Staff模块', 'Staff.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045041', '923723633401045044', '查看Staff', 'Staff.view'),
('923723633401045042', '923723633401045044', '更新Staff', 'Staff.update'),
('923723633401045043', '923723633401045044', '删除Staff', 'Staff.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045048', 'Tenant模块', 'Tenant.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045045', '923723633401045048', '查看Tenant', 'Tenant.view'),
('923723633401045046', '923723633401045048', '更新Tenant', 'Tenant.update'),
('923723633401045047', '923723633401045048', '删除Tenant', 'Tenant.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045052', 'User模块', 'User.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045049', '923723633401045052', '查看User', 'User.view'),
('923723633401045050', '923723633401045052', '更新User', 'User.update'),
('923723633401045051', '923723633401045052', '删除User', 'User.delete');
