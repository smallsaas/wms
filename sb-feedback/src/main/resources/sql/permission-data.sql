INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('935340980606734344', 'TFeedbackImage模块', 'TFeedbackImage.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('935340980606734341', '935340980606734344', '查看TFeedbackImage', 'TFeedbackImage.view'),
('935340980606734342', '935340980606734344', '更新TFeedbackImage', 'TFeedbackImage.update'),
('935340980606734343', '935340980606734344', '删除TFeedbackImage', 'TFeedbackImage.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('935340980606734340', 'TFeedback模块', 'TFeedback.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('935340980606734337', '935340980606734340', '查看TFeedback', 'TFeedback.view'),
('935340980606734338', '935340980606734340', '更新TFeedback', 'TFeedback.update'),
('935340980606734339', '935340980606734340', '删除TFeedback', 'TFeedback.delete');
