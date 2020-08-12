INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008975085785845768', 'SpecificationGroup模块', 'SpecificationGroup.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008975085785845765', '1008975085785845768', '查看SpecificationGroup', 'SpecificationGroup.view'),
('1008975085785845766', '1008975085785845768', '编辑SpecificationGroup', 'SpecificationGroup.edit');
