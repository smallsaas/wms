INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917594964212506628', 'LoginLog模块', 'LoginLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917594964212506625', '917594964212506628', '查看LoginLog', 'LoginLog.view'),
('917594964212506626', '917594964212506628', '更新LoginLog', 'LoginLog.update'),
('917594964212506627', '917594964212506628', '删除LoginLog', 'LoginLog.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('917594964212506632', 'OperationLog模块', 'OperationLog.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('917594964212506629', '917594964212506632', '查看OperationLog', 'OperationLog.view'),
('917594964212506630', '917594964212506632', '更新OperationLog', 'OperationLog.update'),
('917594964212506631', '917594964212506632', '删除OperationLog', 'OperationLog.delete');
