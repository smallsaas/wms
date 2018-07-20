INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009644892818513925', 'StorageInItem模块', 'StorageInItem.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009644892818513922', '1009644892818513925', '查看StorageInItem', 'StorageInItem.view'),
('1009644892818513923', '1009644892818513925', '编辑StorageInItem', 'StorageInItem.edit');
