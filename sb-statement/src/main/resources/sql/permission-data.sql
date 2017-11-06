INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('927354755810029572', 'Statement模块', 'Statement.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('927354755810029569', '927354755810029572', '查看Statement', 'Statement.view'),
('927354755810029570', '927354755810029572', '更新Statement', 'Statement.update'),
('927354755810029571', '927354755810029572', '删除Statement', 'Statement.delete');
