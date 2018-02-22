INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('966566209108811781', 'SystemConfig模块', 'SystemConfig.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('966566209108811778', '966566209108811781', '查看SystemConfig', 'SystemConfig.view'),
('966566209108811779', '966566209108811781', '更新SystemConfig', 'SystemConfig.update'),
('966566209108811780', '966566209108811781', '删除SystemConfig', 'SystemConfig.delete');