SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `sender_id` bigint(20) NOT NULL COMMENT '发送者ID',
  `content` varchar(255) NOT NULL COMMENT '内容提醒',
  `type` varchar(255) NOT NULL COMMENT '类型',
  `target_id` bigint(20) NOT NULL COMMENT '目标ID',
  `target_type` varchar(255) NOT NULL COMMENT '目标类型:topic essay',
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
  `user_id` integer not null,
  `target` integer not null, /* 订阅目标的ID */
  `target_type` varchar(50), /* 目标的类型 */
  `action` varchar(200), /* 订阅动作,如: comment/like/post/update etc. */
  `created_at` datetime null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;