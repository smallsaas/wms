INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('929261913433206799', 'stock模块', 'MemberBlack.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('929261913433206816', '929261913433206819', '查看MemberTrends', 'MemberTrends.view'),
('929261913433206817', '929261913433206819', '更新MemberTrends', 'MemberTrends.update'),
('929261913433206818', '929261913433206819', '删除MemberTrends', 'MemberTrends.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('929261913433206787', 'StockCollect模块', 'StockCollect.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('929261913374486529', '929261913433206787', '查看StockCollect', 'StockCollect.view'),
('929261913433206785', '929261913433206787', '更新StockCollect', 'StockCollect.update'),
('929261913433206786', '929261913433206787', '删除StockCollect', 'StockCollect.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('929261913433206795', 'StockEvaluationAddition模块', 'StockEvaluationAddition.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('929261913433206792', '929261913433206795', '查看StockEvaluationAddition', 'StockEvaluationAddition.view'),
('929261913433206793', '929261913433206795', '更新StockEvaluationAddition', 'StockEvaluationAddition.update'),
('929261913433206794', '929261913433206795', '删除StockEvaluationAddition', 'StockEvaluationAddition.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('929261913433206791', 'StockEvaluation模块', 'StockEvaluation.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('929261913433206788', '929261913433206791', '查看StockEvaluation', 'StockEvaluation.view'),
('929261913433206789', '929261913433206791', '更新StockEvaluation', 'StockEvaluation.update'),
('929261913433206790', '929261913433206791', '删除StockEvaluation', 'StockEvaluation.delete');
