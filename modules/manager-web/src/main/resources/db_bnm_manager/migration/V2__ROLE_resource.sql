CREATE TABLE `resc_role` (
  `resc_id` INT(11)     NOT NULL,
  `role_id` INT(11)     NOT NULL,
  `method`  VARCHAR(45) NOT NULL,
  PRIMARY KEY (`resc_id`, `role_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `resc` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `resource`    VARCHAR(50)      DEFAULT NULL,
  `name`        VARCHAR(45)      DEFAULT NULL,
  `description` VARCHAR(150)     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `role` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(20) NOT NULL,
  `description` VARCHAR(50)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `user_role` (
  `user_id` VARCHAR(45) NOT NULL,
  `role_id` INT(11)     NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

ALTER TABLE `resc_role`
  ADD INDEX `role_id_idx` (`role_id` ASC);
ALTER TABLE `resc_role`
  ADD CONSTRAINT `resc_id`
FOREIGN KEY (`resc_id`)
REFERENCES `resc` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  ADD CONSTRAINT `role_id`
FOREIGN KEY (`role_id`)
REFERENCES `role` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_name` FOREIGN KEY (`user_id`) REFERENCES `admin_account` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

INSERT INTO `resc` (`id`, `resource`, `name`, `description`) VALUES ('1', '/**', 'ALL', '全部权限');
INSERT INTO `role` (`id`, `name`, `description`) VALUES ('1', '超级管理员', '拥有所有权限');
INSERT INTO `resc_role` (`resc_id`, `role_id`, `method`) VALUES ('1', '1', 'ALL');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('admin', '1');