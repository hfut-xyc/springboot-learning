CREATE DATABASE IF NOT EXISTS `db_jpa`;
USE `db_jpa`;

DROP TABLE IF EXISTS `tb_author`;
CREATE TABLE `tb_author` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
  `author_name` VARCHAR(255) NOT NULL COMMENT '作者名',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`author_name`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id`    INT(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` VARCHAR(255) NOT NULL COMMENT '书名',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`title`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id`           INT(11)      NOT NULL AUTO_INCREMENT COMMENT '学号',
  `student_name` VARCHAR(255) NOT NULL COMMENT '学生姓名',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_name` VARCHAR(255) NOT NULL COMMENT '课程名',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4;