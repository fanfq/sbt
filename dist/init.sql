create Database sbt;

DROP TABLE IF EXISTS `t_sbt`;
CREATE TABLE `t_sbt` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
