INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917675637375991813', 'Dept模块', 'Dept.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917675637375991810', '917675637375991813', '查看Dept', 'Dept.view'),
('917675637375991811', '917675637375991813', '更新Dept', 'Dept.update'),
('917675637375991812', '917675637375991813', '删除Dept', 'Dept.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917675637375991817', 'OperationLog模块', 'OperationLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917675637375991814', '917675637375991817', '查看OperationLog', 'OperationLog.view'),
('917675637375991815', '917675637375991817', '更新OperationLog', 'OperationLog.update'),
('917675637375991816', '917675637375991817', '删除OperationLog', 'OperationLog.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917675637375991821', 'Staff模块', 'Staff.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917675637375991818', '917675637375991821', '查看Staff', 'Staff.view'),
('917675637375991819', '917675637375991821', '更新Staff', 'Staff.update'),
('917675637375991820', '917675637375991821', '删除Staff', 'Staff.delete');
