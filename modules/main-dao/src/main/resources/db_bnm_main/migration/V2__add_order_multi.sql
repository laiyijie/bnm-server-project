CREATE TABLE `order_multi_info` (
  `id`          BIGINT         NOT NULL AUTO_INCREMENT,
  `order_id`    VARCHAR(45)    NOT NULL,
  `create_time` BIGINT         NOT NULL,
  `update_time` BIGINT         NOT NULL,
  `state`       VARCHAR(45)    NOT NULL,
  `type`        VARCHAR(45)    NOT NULL,
  `content`     VARCHAR(450)   NULL,
  `extra_info`  VARCHAR(20480) NULL,
  PRIMARY KEY (`id`),
  INDEX (`order_id`),
  INDEX (`state`)
)
  ENGINE = MyISAM
  CHARSET = utf8
  COLLATE utf8_general_ci;