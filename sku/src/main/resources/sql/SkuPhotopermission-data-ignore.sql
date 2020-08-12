INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1019396048600018953', 'SkuPhoto模块', 'SkuPhoto.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1019396048600018950', '1019396048600018953', '查看SkuPhoto', 'SkuPhoto.view'),
('1019396048600018951', '1019396048600018953', '编辑SkuPhoto', 'SkuPhoto.edit');
