INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1009976804657405956', 'Procurement模块', 'Procurement.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1009976804657405953', '1009976804657405956', '查看Procurement', 'Procurement.view'),
('1009976804657405954', '1009976804657405956', '编辑Procurement', 'Procurement.edit');
