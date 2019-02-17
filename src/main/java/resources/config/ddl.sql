CREATE TABLE `member` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `phone` varchar(100) NOT NULL COMMENT '电话',
  `create_date` varchar(100) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
);
CREATE TABLE `aluminum_alloy` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `height` double NOT NULL COMMENT '高',
  `width` double NOT NULL COMMENT '宽',
  `price` double NOT NULL COMMENT '单价',
  PRIMARY KEY (`id`)
);