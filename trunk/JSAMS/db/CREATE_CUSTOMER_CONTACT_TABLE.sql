CREATE TABLE BTS.CUSTOMER_CONTACT
(
	FK_CUSTOMER BIGINT NOT NULL,
	FK_CONTACT BIGINT NOT NULL,
	PRIMARY KEY (FK_CUSTOMER, FK_CONTACT)
) ENGINE = InnoDB;

ALTER TABLE BTS.CUSTOMER_CONTACT
	ADD FOREIGN KEY (FK_CUSTOMER)
	REFERENCES BTS.CUSTOMER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;

ALTER TABLE BTS.CUSTOMER_CONTACT
	ADD FOREIGN KEY (FK_CONTACT)
	REFERENCES BTS.CONTACT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;