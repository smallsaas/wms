INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('919739618725806084', 'Department模块', 'Department.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('919739618725806081', '919739618725806084', '查看Department', 'Department.view'),
('919739618725806082', '919739618725806084', '更新Department', 'Department.update'),
('919739618725806083', '919739618725806084', '删除Department', 'Department.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('919739618725806088', 'Organization模块', 'Organization.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('919739618725806085', '919739618725806088', '查看Organization', 'Organization.view'),
('919739618725806086', '919739618725806088', '更新Organization', 'Organization.update'),
('919739618725806087', '919739618725806088', '删除Organization', 'Organization.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('919739618725806092', 'Position模块', 'Position.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('919739618725806089', '919739618725806092', '查看Position', 'Position.view'),
('919739618725806090', '919739618725806092', '更新Position', 'Position.update'),
('919739618725806091', '919739618725806092', '删除Position', 'Position.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('919739618725806096', 'Staff模块', 'Staff.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('919739618725806093', '919739618725806096', '查看Staff', 'Staff.view'),
('919739618725806094', '919739618725806096', '更新Staff', 'Staff.update'),
('919739618725806095', '919739618725806096', '删除Staff', 'Staff.delete');
