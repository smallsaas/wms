INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('922644451308679221', 'Profile模块', 'Profile.management');
INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('922644451308679218', '922644451308679221', '查看Profile', 'Profile.view'),
('922644451308679219', '922644451308679221', '更新Profile', 'Profile.update'),
('922644451308679220', '922644451308679221', '删除Profile', 'Profile.delete');

INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('922644451308679225', 'Resume模块', 'Resume.management');
INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('922644451308679222', '922644451308679225', '查看Resume', 'Resume.view'),
('922644451308679223', '922644451308679225', '更新Resume', 'Resume.update'),
('922644451308679224', '922644451308679225', '删除Resume', 'Resume.delete');