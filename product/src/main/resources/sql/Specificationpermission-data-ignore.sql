INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1008975085785845764', 'Specification模块', 'Specification.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1008975085785845761', '1008975085785845764', '查看Specification', 'Specification.view'),
('1008975085785845762', '1008975085785845764', '编辑Specification', 'Specification.edit');
