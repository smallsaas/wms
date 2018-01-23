INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('955633883372433412', 'ViewHistory模块', 'ViewHistory.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('955633882520989697', '955633883372433412', '查看ViewHistory', 'ViewHistory.view'),
('955633883372433410', '955633883372433412', '更新ViewHistory', 'ViewHistory.update'),
('955633883372433411', '955633883372433412', '删除ViewHistory', 'ViewHistory.delete');