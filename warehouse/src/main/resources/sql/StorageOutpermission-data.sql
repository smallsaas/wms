INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009381435686805509', 'StorageOut模块', 'StorageOut.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009381435686805506', '1009381435686805509', '查看StorageOut', 'StorageOut.view'),
('1009381435686805507', '1009381435686805509', '编辑StorageOut', 'StorageOut.edit');
