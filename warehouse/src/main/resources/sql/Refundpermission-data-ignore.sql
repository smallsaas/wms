INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1010039400781668356', 'Refund模块', 'Refund.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1010039400781668353', '1010039400781668356', '查看Refund', 'Refund.view'),
('1010039400781668354', '1010039400781668356', '编辑Refund', 'Refund.edit');
