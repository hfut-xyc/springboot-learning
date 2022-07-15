CREATE DATABASE IF NOT EXISTS `db_jwt`;
USE `db_jwt`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username`    VARCHAR(255) NOT NULL COMMENT '用户名',
  `password`    VARCHAR(255) NOT NULL COMMENT '密码',
  `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY pk(`id`),
  UNIQUE KEY uk_username(`username`)
) ENGINE = InnoDB CHARSET = utf8;

INSERT INTO user(username, password) VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');