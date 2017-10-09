INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917324283461709829', 'Dept模块', 'Dept.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917324283461709826', '917324283461709829', '查看Dept', 'Dept.view'),
('917324283461709827', '917324283461709829', '更新Dept', 'Dept.update'),
('917324283461709828', '917324283461709829', '删除Dept', 'Dept.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917324283461709833', 'LoginLog模块', 'LoginLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917324283461709830', '917324283461709833', '查看LoginLog', 'LoginLog.view'),
('917324283461709831', '917324283461709833', '更新LoginLog', 'LoginLog.update'),
('917324283461709832', '917324283461709833', '删除LoginLog', 'LoginLog.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917324283461709837', 'OperationLog模块', 'OperationLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917324283461709834', '917324283461709837', '查看OperationLog', 'OperationLog.view'),
('917324283461709835', '917324283461709837', '更新OperationLog', 'OperationLog.update'),
('917324283461709836', '917324283461709837', '删除OperationLog', 'OperationLog.delete');
