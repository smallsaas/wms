INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008979851924541445', 'Condition模块', 'Condition.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008979851924541442', '1008979851924541445', '查看Condition', 'Condition.view'),
('1008979851924541443', '1008979851924541445', '编辑Condition', 'Condition.edit');
