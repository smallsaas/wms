-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `sender_id` bigint(20) NOT NULL COMMENT '发送者ID',
  `content` varchar(255) NOT NULL COMMENT '内容提醒',
  `type` varchar(255) NOT NULL COMMENT '类型公告-xxx，提醒-remind等等',
  `target_id` bigint(20) NOT NULL COMMENT '目标ID',
  `target_type` varchar(255) NOT NULL COMMENT '目标类型:topic essay prayer memo',
  `action` varchar(255) NOT NULL COMMENT '操作:like comment',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_notify
-- ----------------------------
DROP TABLE IF EXISTS `user_notify`;
CREATE TABLE `user_notify` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `notify_id` bigint(20) NOT NULL COMMENT '提醒ID',
  `is_read` int NOT NULL COMMENT '是否阅读:0-未读,1-已读',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for subscription
-- ----------------------------
DROP TABLE IF EXISTS `subscription`;
CREATE TABLE IF NOT EXISTS subscription  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) not null,
  `target_id` bigint(20) not null, /* 订阅目标的ID */
  `target_type` varchar(50), /* 目标的类型 */
  `action` varchar(200), /* 订阅动作,如: comment/like/post/update etc. */
  `created_at` datetime null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('985010100740796421', 'Notify模块', 'Notify.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('985010100740796418', '985010100740796421', '查看Notify', 'Notify.view'),
('985010100740796419', '985010100740796421', '更新Notify', 'Notify.update'),
('985010100740796420', '985010100740796421', '删除Notify', 'Notify.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('985010100740796425', 'Subscription模块', 'Subscription.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('985010100740796422', '985010100740796425', '查看Subscription', 'Subscription.view'),
('985010100740796423', '985010100740796425', '更新Subscription', 'Subscription.update'),
('985010100740796424', '985010100740796425', '删除Subscription', 'Subscription.delete');
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('985010100740796429', 'UserNotify模块', 'UserNotify.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('985010100740796426', '985010100740796429', '查看UserNotify', 'UserNotify.view'),
('985010100740796427', '985010100740796429', '更新UserNotify', 'UserNotify.update'),
('985010100740796428', '985010100740796429', '删除UserNotify', 'UserNotify.delete');
