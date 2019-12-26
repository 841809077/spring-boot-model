CREATE DATABASE spring_boot_study
USE spring_boot_study
CREATE TABLE `novel_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `download` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `novelname` varchar(255) DEFAULT NULL,
  `novelauthor` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `spring_boot_study`.`novel_type`(`id`, `download`, `introduce`, `novelname`, `novelauthor`, `type`) VALUES (1, NULL, NULL, '遮天', '辰东', '已完结');
INSERT INTO `spring_boot_study`.`novel_type`(`id`, `download`, `introduce`, `novelname`, `novelauthor`, `type`) VALUES (2, NULL, '热销作品', '遮天', '辰东', '已完结');
INSERT INTO `spring_boot_study`.`novel_type`(`id`, `download`, `introduce`, `novelname`, `novelauthor`, `type`) VALUES (3, 'true', '热销作品', '天蚕土豆', '辰东', '已完结');
INSERT INTO `spring_boot_study`.`novel_type`(`id`, `download`, `introduce`, `novelname`, `novelauthor`, `type`) VALUES (4, NULL, NULL, NULL, NULL, NULL);

CREATE DATABASE test
USE test
CREATE TABLE `userinfo` (
  `id` int(30) NOT NULL AUTO_INCREMENT COMMENT '主健',
  `person_id` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `department_id` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `test`.`userinfo`(`id`, `person_id`, `department_id`) VALUES (263, '111123', '370112');
INSERT INTO `test`.`userinfo`(`id`, `person_id`, `department_id`) VALUES (271, '12345688', '370112');