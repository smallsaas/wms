INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1019264170006237189', 'SpecificationGroup模块', 'SkuSpecificationGroup.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1019264170006237186', '1019264170006237189', '查看SpecificationGroup', 'SkuSpecificationGroup.view'),
('1019264170006237187', '1019264170006237189', '编辑SpecificationGroup', 'SkuSpecificationGroup.edit');
