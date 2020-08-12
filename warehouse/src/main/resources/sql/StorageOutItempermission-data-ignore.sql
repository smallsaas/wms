INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009381436970262533', 'StorageOutItem模块', 'StorageOutItem.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009381436970262530', '1009381436970262533', '查看StorageOutItem', 'StorageOutItem.view'),
('1009381436970262531', '1009381436970262533', '编辑StorageOutItem', 'StorageOutItem.edit');
