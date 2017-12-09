INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('939349783914717189', 'TermConfig模块', 'TermConfig.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('939349783914717186', '939349783914717189', '查看TermConfig', 'TermConfig.view'),
('939349783914717187', '939349783914717189', '更新TermConfig', 'TermConfig.update'),
('939349783914717188', '939349783914717189', '删除TermConfig', 'TermConfig.delete');