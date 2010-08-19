CREATE TABLE ADDRESS
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	STREET VARCHAR(100) NOT NULL,
	NUMBER VARCHAR(50) NOT NULL,
	ZIP_CODE INT NOT NULL,
	BOX VARCHAR(50),
	CITY VARCHAR(100) NOT NULL,
	COUNTRY VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB;