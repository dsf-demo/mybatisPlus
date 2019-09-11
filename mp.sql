
USE `mp`;

-- 用户表：user
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(30) DEFAULT NULL COMMENT '姓名',
  `age` INT(11) DEFAULT NULL COMMENT '年龄',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` BIGINT(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` DATETIME(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` INT(11) DEFAULT '1' COMMENT '版本',
  `deleted` INT(1) DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)' 
) ENGINE=INNODB CHARSET=UTF8;
INSERT INTO `user`(`id`,`name`,`age`,`email`,`manager_id`,`create_time`) VALUES
(1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20'),
(1088248166370832385, '王天风', 25, 'wtf@baomidou.com', 1087982257332887553, '2019-02-05 11:12:22'),
(1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385, '2019-02-14 08:31:16'),
(1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15'),
(1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16');


-- 用户表，用于mp-annotation项目，实验实体类注解
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user`  (
  `user_id` BIGINT(20) AUTO_INCREMENT COMMENT '主键',
  `user_name` VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
  `age` INT(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
  `manager_id` BIGINT(20) NULL DEFAULT NULL COMMENT '直属上级id',
  `create_time` DATETIME(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;
INSERT INTO `mp_user` VALUES
(1, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20'),
(2, '王天风', 25, 'wtf@baomidou.com', 1, '2019-02-05 11:12:22'),
(3, '李艺伟', 28, 'lyw@baomidou.com', 2, '2019-02-14 08:31:16'),
(4, '张雨琪', 31, 'zjq@baomidou.com', 2, '2019-01-14 09:15:15'),
(5, '刘红雨', 32, 'lhm@baomidou.com', 2, '2019-01-14 09:48:16');



-- 用户爱好表，用于mp-pagination项目，实验多表联查
DROP TABLE IF EXISTS `user_hobby`;
CREATE TABLE `user_hobby` (
  `id` BIGINT(20) NOT NULL,
  `user_id` BIGINT(20) DEFAULT NULL,
  `hobby` VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `user_hobby` VALUES
-- boss
(1,1087982257332887553,'吃饭'),
(2,1087982257332887553,'睡觉'),
(3,1087982257332887553,'打豆豆'),
-- 王天风(经理)
(4,1088248166370832385,'工作'),
(5,1088248166370832385,'学习'),
(6,1088248166370832385,'喝酒'),
-- 李艺伟
(7,1088250446457389058,'弹琴'),
(8,1088250446457389058,'下棋'),
(9,1088250446457389058,'书法'),
-- 张雨琪
(10,1094590409767661570,'编程'),
(11,1094590409767661570,'学习'),
(12,1094590409767661570,'下棋'),
-- 刘红雨
(13,1094592041087729666,'工作'),
(14,1094592041087729666,'学习'),
(15,1094592041087729666,'画画');


-- 用户表2：用于测试动态表名 SQL 解析器
DROP TABLE IF EXISTS `user_2019`;
CREATE TABLE `user_2019`  (
  `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(30) DEFAULT NULL COMMENT '姓名',
  `age` INT(11) DEFAULT NULL COMMENT '年龄',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` BIGINT(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` DATETIME(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` INT(11) DEFAULT '1' COMMENT '版本',
  `deleted` INT(1) DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)'
) ENGINE=INNODB CHARSET=UTF8;
INSERT INTO `user_2019`(`id`,`name`,`age`,`email`,`manager_id`,`create_time`) VALUES
(1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20'),
(1088248166370832385, '王天风', 25, 'wtf@baomidou.com', 1087982257332887553, '2019-02-05 11:12:22'),
(1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385, '2019-02-14 08:31:16'),
(1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15'),
(1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16');




