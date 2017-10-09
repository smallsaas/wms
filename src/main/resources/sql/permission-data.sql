INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917342513685315589', 'LoginLog模块', 'LoginLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917342513685315586', '917342513685315589', '查看LoginLog', 'LoginLog.view'),
('917342513685315587', '917342513685315589', '更新LoginLog', 'LoginLog.update'),
('917342513685315588', '917342513685315589', '删除LoginLog', 'LoginLog.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917342513685315593', 'OperationLog模块', 'OperationLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917342513685315590', '917342513685315593', '查看OperationLog', 'OperationLog.view'),
('917342513685315591', '917342513685315593', '更新OperationLog', 'OperationLog.update'),
('917342513685315592', '917342513685315593', '删除OperationLog', 'OperationLog.delete');
