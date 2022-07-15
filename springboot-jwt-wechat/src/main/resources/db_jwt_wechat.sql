CREATE DATABASE IF NOT EXISTS `db_jwt_wechat`;
USE `db_jwt_wechat`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `open_id`     VARCHAR(255) NOT NULL COMMENT '微信账号在该小程序下的唯一标识',
  `nickname`    VARCHAR(255) NOT NULL COMMENT '微信昵称',
  `avatar`      VARCHAR(255) NOT NULL COMMENT '微信头像',
  `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY pk(`id`),
  UNIQUE KEY uk_openid(`open_id`)
) ENGINE = InnoDB CHARSET = utf8;