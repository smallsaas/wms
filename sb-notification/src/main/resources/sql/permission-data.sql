INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('985002103461306372', 'Notify模块', 'Notify.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('985002103461306369', '985002103461306372', '查看Notify', 'Notify.view'),
('985002103461306370', '985002103461306372', '更新Notify', 'Notify.update'),
('985002103461306371', '985002103461306372', '删除Notify', 'Notify.delete');
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('985002103461306376', 'UserNotify模块', 'UserNotify.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('985002103461306373', '985002103461306376', '查看UserNotify', 'UserNotify.view'),
('985002103461306374', '985002103461306376', '更新UserNotify', 'UserNotify.update'),
('985002103461306375', '985002103461306376', '删除UserNotify', 'UserNotify.delete');
