INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('935348998329204741', '反馈模块', 'feedback.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('935348998329204738', '935348998329204741', '查看反馈', 'feedback.view'),
('935348998329204739', '935348998329204741', '更新反馈', 'feedback.update'),
('935348998329204740', '935348998329204741', '删除反馈', 'feedback.delete');
