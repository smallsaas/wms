INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1019411476453863428', 'SkuPriceHistory模块', 'SkuPriceHistory.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1019411476453863425', '1019411476453863428', '查看SkuPriceHistory', 'SkuPriceHistory.view'),
('1019411476453863426', '1019411476453863428', '编辑SkuPriceHistory', 'SkuPriceHistory.edit');
