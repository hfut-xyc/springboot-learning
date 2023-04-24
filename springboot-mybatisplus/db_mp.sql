CREATE DATABASE IF NOT EXISTS `db_mp`;
USE `db_mp`;


DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id`          BIGINT      NOT NULL COMMENT '主键',
  `name`        VARCHAR(255) NOT NULL COMMENT '商品名',
  `category_id` BIGINT      NOT NULL COMMENT '商品类别id',
  `price`       DECIMAL(10, 2) NOT NULL COMMENT '商品价格',
  `create_time` DATETIME       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
) ENGINE = InnoDB
  CHARSET = utf8;

INSERT INTO `dish`(id, name, category_id, price) VALUES (1397850140982161409, '酸菜鱼', 1397844263642378242, '68.00');
INSERT INTO `dish`(id, name, category_id, price) VALUES (1397850392090947585, '鱼香肉丝', 1397844263642378242, '48.00');
INSERT INTO `dish`(id, name, category_id, price) VALUES (1397850851245600769, '椒盐里脊', 1397844263642378242, '128.00');
INSERT INTO `dish`(id, name, category_id, price) VALUES (1397849739276890114, '啤酒', 1413341197421846529, '78.00');
INSERT INTO `dish`(id, name, category_id, price) VALUES (1397849739276890115, '可乐', 1413341197421846529, '78.00');
INSERT INTO `dish`(id, name, category_id, price) VALUES (1397849739276890116, '米饭', 1413384954989060097, '1.00');

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id`          BIGINT      NOT NULL COMMENT '主键',
  `name`        VARCHAR(255) NOT NULL COMMENT '类别名',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
) ENGINE = InnoDB
  CHARSET = utf8;

INSERT INTO `category`(id, name) VALUES (1397844263642378242, '特色菜');
INSERT INTO `category`(id, name) VALUES (1413341197421846529, '饮料');
INSERT INTO `category`(id, name) VALUES (1413384954989060097, '主食');
