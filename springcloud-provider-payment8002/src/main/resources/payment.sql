CREATE TABLE `payment` (
  `pkid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `serial_number` varchar(200) DEFAULT NULL COMMENT '流水号',
  PRIMARY KEY (`pkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

