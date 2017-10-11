INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917931119030259717', 'Deptment模块', 'Deptment.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917931119030259714', '917931119030259717', '查看Deptment', 'Deptment.view'),
('917931119030259715', '917931119030259717', '更新Deptment', 'Deptment.update'),
('917931119030259716', '917931119030259717', '删除Deptment', 'Deptment.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917931119030259721', 'OperationLog模块', 'OperationLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917931119030259718', '917931119030259721', '查看OperationLog', 'OperationLog.view'),
('917931119030259719', '917931119030259721', '更新OperationLog', 'OperationLog.update'),
('917931119030259720', '917931119030259721', '删除OperationLog', 'OperationLog.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917931119030259725', 'Staff模块', 'Staff.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917931119030259722', '917931119030259725', '查看Staff', 'Staff.view'),
('917931119030259723', '917931119030259725', '更新Staff', 'Staff.update'),
('917931119030259724', '917931119030259725', '删除Staff', 'Staff.delete');
