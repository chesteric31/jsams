DROP TABLE IF EXISTS `bts`.`address`;
CREATE TABLE  `bts`.`address` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `STREET` varchar(100) NOT NULL,
  `NUMBER` varchar(100) NOT NULL,
  `ZIPCODE` int(10) unsigned NOT NULL,
  `CITY` varchar(100) NOT NULL,
  `COUNTRY` varchar(100) NOT NULL,
  `BOX` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB;