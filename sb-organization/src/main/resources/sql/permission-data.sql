INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045032', '部门管理', 'Department.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045029', '923723633401045032', '查看部门', 'Department.view'),
('923723633401045030', '923723633401045032', '更新部门', 'Department.update'),
('923723633401045031', '923723633401045032', '删除部门', 'Department.delete');

INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045040', '职位管理', 'Position.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045037', '923723633401045040', '查看职位', 'Position.view'),
('923723633401045038', '923723633401045040', '更新职位', 'Position.update'),
('923723633401045039', '923723633401045040', '删除职位', 'Position.delete');

INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('923723633401045044', '员工管理', 'Staff.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('923723633401045041', '923723633401045044', '查看员工', 'Staff.view'),
('923723633401045042', '923723633401045044', '更新员工', 'Staff.update'),
('923723633401045043', '923723633401045044', '删除员工', 'Staff.delete');

