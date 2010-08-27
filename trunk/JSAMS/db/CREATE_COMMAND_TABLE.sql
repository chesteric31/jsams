CREATE TABLE BTS.COMMAND
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CREATION_DATE DATE NOT NULL,
	REMARK VARCHAR(100),
	DISCOUNT_RATE REAL(5,2),
	FK_CONTACT BIGINT NOT NULL,
	FK_CUSTOMER BIGINT NOT NULL,
	FK_ADDRESS_DELIVERY BIGINT NOT NULL,
	FK_ADDRESS_BILLING BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB;

ALTER TABLE BTS.COMMAND
	ADD FOREIGN KEY (FK_ADDRESS_BILLING)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE BTS.COMMAND
	ADD FOREIGN KEY (FK_ADDRESS_DELIVERY)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE BTS.COMMAND
	ADD FOREIGN KEY (FK_CONTACT)
	REFERENCES BTS.CONTACT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE BTS.COMMAND
	ADD FOREIGN KEY (FK_CUSTOMER)
	REFERENCES BTS.CUSTOMER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;
