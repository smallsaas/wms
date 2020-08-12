INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008979851924541453', 'ProductCondition模块', 'ProductCondition.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008979851924541450', '1008979851924541453', '查看ProductCondition', 'ProductCondition.view'),
('1008979851924541451', '1008979851924541453', '编辑ProductCondition', 'ProductCondition.edit');
