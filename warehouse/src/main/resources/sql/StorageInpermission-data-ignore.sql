INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009644892365529093', 'StorageIn模块', 'StorageIn.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009644892365529090', '1009644892365529093', '查看StorageIn', 'StorageIn.view'),
('1009644892365529091', '1009644892365529093', '编辑StorageIn', 'StorageIn.edit');
