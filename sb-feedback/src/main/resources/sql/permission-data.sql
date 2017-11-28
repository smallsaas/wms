INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('935348998345981956', 'TFeedbackImage模块', 'TFeedbackImage.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('935348998345981953', '935348998345981956', '查看TFeedbackImage', 'TFeedbackImage.view'),
('935348998345981954', '935348998345981956', '更新TFeedbackImage', 'TFeedbackImage.update'),
('935348998345981955', '935348998345981956', '删除TFeedbackImage', 'TFeedbackImage.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('935348998329204741', 'TFeedback模块', 'TFeedback.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('935348998329204738', '935348998329204741', '查看TFeedback', 'TFeedback.view'),
('935348998329204739', '935348998329204741', '更新TFeedback', 'TFeedback.update'),
('935348998329204740', '935348998329204741', '删除TFeedback', 'TFeedback.delete');
