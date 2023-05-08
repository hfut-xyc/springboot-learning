CREATE DATABASE IF NOT EXISTS `db_seckill`;
USE `db_seckill`;

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`
(
    `id`          BIGINT(11)   NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    VARCHAR(255) NOT NULL COMMENT '用户名',
    `password`    VARCHAR(255) NOT NULL COMMENT '密码',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY pk (`id`),
    UNIQUE KEY uk_username (`username`)
) ENGINE = InnoDB
  CHARSET = utf8;

INSERT INTO tb_user(username, password) VALUES ('admin', 'admin');

CREATE TABLE `tb_voucher`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '优惠券id',
    `stock`       int(8)     NOT NULL COMMENT '库存',
    `create_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARSET = utf8 AUTO_INCREMENT = 10;

CREATE TABLE `tb_voucher_order`
(
    `id`          bigint(20) NOT NULL COMMENT '订单id',
    `userId`      bigint(20) NOT NULL COMMENT '用户id',
    `voucherId`   bigint(20) NOT NULL COMMENT '优惠券id',
    `create_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARSET = utf8;
