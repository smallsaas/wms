INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1012974852803149829', 'SuppliersProduct模块', 'SuppliersProduct.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1012974852803149826', '1012974852803149829', '查看SuppliersProduct', 'SuppliersProduct.view'),
('1012974852803149827', '1012974852803149829', '编辑SuppliersProduct', 'SuppliersProduct.edit');
