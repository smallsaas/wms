INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('876708082437145784', '日志管理', 'contract.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('876708082437854441', '876708082437145784', '查看日志', 'operation_log.view'),
('876708082437854442', '876708082437145784', '编辑日志', 'operation_log.edit');
