INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('1015164732824195077', 'SkuPriceHistory模块', 'SkuPriceHistory.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('1015164732824195074', '1015164732824195077', '查看SkuPriceHistory', 'SkuPriceHistory.view'),
('1015164732824195075', '1015164732824195077', '编辑SkuPriceHistory', 'SkuPriceHistory.edit');
