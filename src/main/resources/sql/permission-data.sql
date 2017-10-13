INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('918713495914246148', 'Department模块', 'Department.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('918713495914246145', '918713495914246148', '查看Department', 'Department.view'),
('918713495914246146', '918713495914246148', '更新Department', 'Department.update'),
('918713495914246147', '918713495914246148', '删除Department', 'Department.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('918713495914246152', 'OperationLog模块', 'OperationLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('918713495914246149', '918713495914246152', '查看OperationLog', 'OperationLog.view'),
('918713495914246150', '918713495914246152', '更新OperationLog', 'OperationLog.update'),
('918713495914246151', '918713495914246152', '删除OperationLog', 'OperationLog.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('918713495914246156', 'Staff模块', 'Staff.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('918713495914246153', '918713495914246156', '查看Staff', 'Staff.view'),
('918713495914246154', '918713495914246156', '更新Staff', 'Staff.update'),
('918713495914246155', '918713495914246156', '删除Staff', 'Staff.delete');
