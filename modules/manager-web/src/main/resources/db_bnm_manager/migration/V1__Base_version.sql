SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `account_area` (
  `area_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`area_id`,`username`),
  FULLTEXT KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关联的区域表';

CREATE TABLE `admin_account` (
  `username` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  `login_failed_time` int(11) NOT NULL,
  `last_failed_time` bigint(11) NOT NULL,
  `verify_code` varchar(6) CHARACTER SET utf8 NOT NULL,
  `superior` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '顶头上司',
  `name` varchar(25) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `phone` varchar(25) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `level` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`),
  KEY `superior` (`superior`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `area_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(15) NOT NULL COMMENT '省',
  `city` varchar(15) DEFAULT NULL COMMENT '市',
  `county` varchar(15) DEFAULT NULL COMMENT '县',
  `town` varchar(15) DEFAULT NULL COMMENT '镇',
  `father_id` int(11) DEFAULT NULL COMMENT '上级的ID',
  PRIMARY KEY (`id`),
  KEY `province` (`province`,`city`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='储存位置信息';

CREATE TABLE `offline_charge` (
  `id` varchar(45) CHARACTER SET utf8 NOT NULL,
  `requester` varchar(45) CHARACTER SET utf8 NOT NULL,
  `responser` varchar(45) CHARACTER SET utf8 NOT NULL,
  `num` int(11) NOT NULL,
  `target` varchar(45) CHARACTER SET utf8 NOT NULL,
  `request_time` bigint(20) NOT NULL,
  `response_time` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  `ps` varchar(450) CHARACTER SET utf8 NOT NULL,
  `image` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `admin_account` (`username`, `password`, `login_failed_time`, `last_failed_time`, `verify_code`, `superior`, `name`, `phone`, `level`) VALUES ('admin', '7c4a8d09ca3762af61e59520943dc26494f8941b', '0', '0', '0', NULL, NULL, NULL, '999');
SET FOREIGN_KEY_CHECKS = 1;

