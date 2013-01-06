SET SESSION FOREIGN_KEY_CHECKS=0;

/* Create Tables */

CREATE TABLE BTS.ADDRESS
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	STREET VARCHAR(100) NOT NULL,
	NUMBER VARCHAR(50) NOT NULL,
	ZIP_CODE VARCHAR(10) NOT NULL,
	BOX VARCHAR(50),
	CITY VARCHAR(100) NOT NULL,
	COUNTRY VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.AGENT
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	FUNCTION VARCHAR(100),
	FK_CIVILITY BIGINT,
	FK_ADDRESS BIGINT NOT NULL,
	FK_CONTACT_INFORMATION BIGINT NOT NULL,
	FK_SOCIETY BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.BILL
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CREATION_DATE DATE NOT NULL,
	REMARK VARCHAR(100),
	DISCOUNT_RATE REAL(5,2),
	DUE_DATE DATE NOT NULL,
	PAYMENT_DATE DATE,
	DATE_FIRST_REMINDER DATE NOT NULL,
	DATE_SECOND_REMINDER DATE NOT NULL,
	DATE_FORMAL_NOTICE DATE NOT NULL,
	CLOSED BOOLEAN,
	FK_PAYMENT_MODE BIGINT NOT NULL,
	FK_CUSTOMER BIGINT NOT NULL,
	FK_ADDRESS_BILLING BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.BILL_DETAIL
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	QUANTITY INT NOT NULL,
	PRICE REAL(12,2) NOT NULL,
	VAT_APPLICABLE REAL(5,2),
	DISCOUNT_RATE REAL(5,2),
	TRANSFERRED BOOLEAN NOT NULL,
	FK_BILL BIGINT NOT NULL,
	FK_PRODUCT BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.CIVILITY
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	LABEL VARCHAR(50) NOT NULL,
	LABEL_FR VARCHAR(50) NOT NULL,
	LABEL_NL VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.COMMAND
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CREATION_DATE DATE NOT NULL,
	REMARK VARCHAR(100),
	DISCOUNT_RATE REAL(5,2),
	TRANSFERRED BOOLEAN NOT NULL,
	FK_AGENT BIGINT,
	FK_CUSTOMER BIGINT NOT NULL,
	FK_ADDRESS_DELIVERY BIGINT NOT NULL,
	FK_ADDRESS_BILLING BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.COMMAND_DETAIL
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	QUANTITY BIGINT NOT NULL,
	PRICE REAL(12,2) NOT NULL,
	VAT_APPLICABLE REAL(5,2),
	DISCOUNT_RATE REAL(5,2),
	TRANSFERRED BOOLEAN NOT NULL,
	FK_COMMAND BIGINT NOT NULL,
	FK_PRODUCT BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.CONTACT_INFORMATION
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	PHONE VARCHAR(50) NOT NULL,
	FAX VARCHAR(50),
	MOBILE VARCHAR(50),
	EMAIL VARCHAR(100),
	WEBSITE VARCHAR(100),
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.CREDIT_NOTE
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CREATION_DATE DATE NOT NULL,
	REMARK VARCHAR(100),
	FK_CUSTOMER BIGINT NOT NULL,
	FK_ADDRESS_BILLING BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.CREDIT_NOTE_DETAIL
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	QUANTITY INT NOT NULL,
	PRICE REAL(12,2) NOT NULL,
	VAT_APPLICABLE REAL(5,2),
	DISCOUNT_RATE REAL(5,2),
	FK_CREDIT_NOTE BIGINT NOT NULL,
	FK_PRODUCT BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.CUSTOMER
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	FIRST_NAME VARCHAR(100),
	VAT_NUMBER VARCHAR(50),
	DEFAULT_DISCOUNT_RATE REAL(5,2),
	BANK_1 VARCHAR(100),
	BANK_2 VARCHAR(100),
	CREDIT_LIMIT REAL(12,2),
	VAT_APPLICABLE REAL(5,2),
	DESCRIPTION VARCHAR(100),
	FK_ADDRESS_DELIVERY BIGINT NOT NULL,
	FK_ADDRESS_BILLING BIGINT NOT NULL,
	FK_PAYMENT_MODE BIGINT NOT NULL,
	FK_CONTACT_INFORMATION BIGINT NOT NULL,
	FK_CIVILITY BIGINT,
	FK_LEGAL_FORM BIGINT,
	FK_AGENT BIGINT,
	FK_SOCIETY BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.DELIVERY_ORDER
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CREATION_DATE DATE NOT NULL,
	REMARK VARCHAR(100),
	DISCOUNT_RATE REAL(5,2),
	TRANSFERRED BOOLEAN NOT NULL,
	FK_CUSTOMER BIGINT NOT NULL,
	FK_ADDRESS_DELIVERY BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.DELIVERY_ORDER_DETAIL
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	QUANTITY INT NOT NULL,
	PRICE REAL(12,2) NOT NULL,
	VAT_APPLICABLE REAL(5,2),
	DISCOUNT_RATE REAL(5,2),
	TRANSFERRED BOOLEAN NOT NULL,
	FK_DELIVERY_ORDER BIGINT NOT NULL,
	FK_PRODUCT BIGINT NOT NULL,
	FK_COMMAND_DETAIL BIGINT,
	FK_BILL_DETAIL BIGINT,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.ESTIMATE
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CREATION_DATE DATE NOT NULL,
	REMARK VARCHAR(100),
	DISCOUNT_RATE REAL(5,2),
	TRANSFERRED BOOLEAN NOT NULL,
	FK_CUSTOMER BIGINT NOT NULL,
	FK_ADDRESS_BILLING BIGINT NOT NULL,
	FK_AGENT BIGINT,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.ESTIMATE_DETAIL
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	QUANTITY BIGINT NOT NULL,
	PRICE REAL(12,2) NOT NULL,
	VAT_APPLICABLE REAL(5,2),
	DISCOUNT_RATE REAL(5,2),
	TRANSFERRED BOOLEAN NOT NULL,
	FK_ESTIMATE BIGINT NOT NULL,
	FK_PRODUCT BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.LEGAL_FORM
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	LABEL VARCHAR(100) NOT NULL,
	LABEL_FR VARCHAR(100) NOT NULL,
	LABEL_NL VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.PAYMENT_MODE
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	LABEL VARCHAR(100) NOT NULL,
	LABEL_FR VARCHAR(100) NOT NULL,
	LABEL_NL VARCHAR(100) NOT NULL,
	DAYS_NUMBER BIGINT NOT NULL,
	MONTH_END BOOLEAN NOT NULL,
	ADDITIONAL_DAYS BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.PRODUCT
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	PRICE REAL(12,2) NOT NULL,
	QUANTITY_STOCK BIGINT NOT NULL,
	REORDER_LEVEL BIGINT,
	VAT_APPLICABLE REAL(5,2) NOT NULL,
	FK_CATEGORY_PRODUCT BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.PRODUCT_CATEGORY
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	LABEL VARCHAR(100) NOT NULL,
	LABEL_FR VARCHAR(100) NOT NULL,
	LABEL_NL VARCHAR(100) NOT NULL,
	FK_SOCIETY BIGINT NOT NULL,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;


CREATE TABLE BTS.SOCIETY
(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	CAPITAL REAL(12,2) NOT NULL,
	ACTIVITY VARCHAR(100) NOT NULL,
	RESPONSIBLE VARCHAR(100),
	VAT_NUMBER VARCHAR(100) NOT NULL,
	LOGO LONGBLOB,
	FK_CONTACT_INFORMATION BIGINT NOT NULL,
	FK_ADDRESS BIGINT NOT NULL,
	FK_LEGAL_FORM BIGINT,
	PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8;



/* Create Foreign Keys */

ALTER TABLE BTS.AGENT
	ADD FOREIGN KEY (FK_ADDRESS)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.BILL
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
	ADD FOREIGN KEY (FK_ADDRESS_BILLING)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CREDIT_NOTE
	ADD FOREIGN KEY (FK_ADDRESS_BILLING)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CUSTOMER
	ADD FOREIGN KEY (FK_ADDRESS_DELIVERY)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CUSTOMER
	ADD FOREIGN KEY (FK_ADDRESS_BILLING)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.DELIVERY_ORDER
	ADD FOREIGN KEY (FK_ADDRESS_DELIVERY)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.ESTIMATE
	ADD FOREIGN KEY (FK_ADDRESS_BILLING)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.SOCIETY
	ADD FOREIGN KEY (FK_ADDRESS)
	REFERENCES BTS.ADDRESS (ID)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;


ALTER TABLE BTS.COMMAND
	ADD FOREIGN KEY (FK_AGENT)
	REFERENCES BTS.AGENT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CUSTOMER
	ADD FOREIGN KEY (FK_AGENT)
	REFERENCES BTS.AGENT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.ESTIMATE
	ADD FOREIGN KEY (FK_AGENT)
	REFERENCES BTS.AGENT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.BILL_DETAIL
	ADD FOREIGN KEY (FK_BILL)
	REFERENCES BTS.BILL (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.DELIVERY_ORDER_DETAIL
	ADD FOREIGN KEY (FK_BILL_DETAIL)
	REFERENCES BTS.BILL_DETAIL (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.AGENT
	ADD FOREIGN KEY (FK_CIVILITY)
	REFERENCES BTS.CIVILITY (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CUSTOMER
	ADD FOREIGN KEY (FK_CIVILITY)
	REFERENCES BTS.CIVILITY (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.COMMAND_DETAIL
	ADD FOREIGN KEY (FK_COMMAND)
	REFERENCES BTS.COMMAND (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.DELIVERY_ORDER_DETAIL
	ADD FOREIGN KEY (FK_COMMAND_DETAIL)
	REFERENCES BTS.COMMAND_DETAIL (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.AGENT
	ADD FOREIGN KEY (FK_CONTACT_INFORMATION)
	REFERENCES BTS.CONTACT_INFORMATION (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CUSTOMER
	ADD FOREIGN KEY (FK_CONTACT_INFORMATION)
	REFERENCES BTS.CONTACT_INFORMATION (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.SOCIETY
	ADD FOREIGN KEY (FK_CONTACT_INFORMATION)
	REFERENCES BTS.CONTACT_INFORMATION (ID)
	ON UPDATE CASCADE
	ON DELETE CASCADE
;


ALTER TABLE BTS.CREDIT_NOTE_DETAIL
	ADD FOREIGN KEY (FK_CREDIT_NOTE)
	REFERENCES BTS.CREDIT_NOTE (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.BILL
	ADD FOREIGN KEY (FK_CUSTOMER)
	REFERENCES BTS.CUSTOMER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.COMMAND
	ADD FOREIGN KEY (FK_CUSTOMER)
	REFERENCES BTS.CUSTOMER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CREDIT_NOTE
	ADD FOREIGN KEY (FK_CUSTOMER)
	REFERENCES BTS.CUSTOMER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.DELIVERY_ORDER
	ADD FOREIGN KEY (FK_CUSTOMER)
	REFERENCES BTS.CUSTOMER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.ESTIMATE
	ADD FOREIGN KEY (FK_CUSTOMER)
	REFERENCES BTS.CUSTOMER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.DELIVERY_ORDER_DETAIL
	ADD FOREIGN KEY (FK_DELIVERY_ORDER)
	REFERENCES BTS.DELIVERY_ORDER (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.ESTIMATE_DETAIL
	ADD FOREIGN KEY (FK_ESTIMATE)
	REFERENCES BTS.ESTIMATE (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CUSTOMER
	ADD FOREIGN KEY (FK_LEGAL_FORM)
	REFERENCES BTS.LEGAL_FORM (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.SOCIETY
	ADD FOREIGN KEY (FK_LEGAL_FORM)
	REFERENCES BTS.LEGAL_FORM (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.BILL
	ADD FOREIGN KEY (FK_PAYMENT_MODE)
	REFERENCES BTS.PAYMENT_MODE (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CUSTOMER
	ADD FOREIGN KEY (FK_PAYMENT_MODE)
	REFERENCES BTS.PAYMENT_MODE (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.BILL_DETAIL
	ADD FOREIGN KEY (FK_PRODUCT)
	REFERENCES BTS.PRODUCT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.COMMAND_DETAIL
	ADD FOREIGN KEY (FK_PRODUCT)
	REFERENCES BTS.PRODUCT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CREDIT_NOTE_DETAIL
	ADD FOREIGN KEY (FK_PRODUCT)
	REFERENCES BTS.PRODUCT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.DELIVERY_ORDER_DETAIL
	ADD FOREIGN KEY (FK_PRODUCT)
	REFERENCES BTS.PRODUCT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.ESTIMATE_DETAIL
	ADD FOREIGN KEY (FK_PRODUCT)
	REFERENCES BTS.PRODUCT (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.PRODUCT
	ADD FOREIGN KEY (FK_CATEGORY_PRODUCT)
	REFERENCES BTS.PRODUCT_CATEGORY (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.AGENT
	ADD FOREIGN KEY (FK_SOCIETY)
	REFERENCES BTS.SOCIETY (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.CUSTOMER
	ADD FOREIGN KEY (FK_SOCIETY)
	REFERENCES BTS.SOCIETY (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE BTS.PRODUCT_CATEGORY
	ADD FOREIGN KEY (FK_SOCIETY)
	REFERENCES BTS.SOCIETY (ID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;



INSERT INTO BTS.CIVILITY (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Mr.", "M.", "Dhr.");
INSERT INTO BTS.CIVILITY (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Ms.", "Mlle.", "Mej.");
INSERT INTO BTS.CIVILITY (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Mrs.", "Mme.", "Mw.");
INSERT INTO BTS.CIVILITY (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Dr.", "Dr.", "Dr.");
INSERT INTO BTS.CIVILITY (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Att.", "Me.", "Mr.");
INSERT INTO BTS.CIVILITY (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Prof.", "Pr.", "Prof.");

INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Public Limited Company", "Entreprise individuelle", "Eenmanszaak");
INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Limited Company", "Société Privée à Responsabilité Limitée (Unipersonnelle) (SPRL)", "Besloten Vennootschap met Veperkte Aansprakelijkheid (BVBA)");
INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Limited Company Starter", "SPRL Starter (SPRL-S)", "Personenvennootschap met Beperkte Aansprakelijkheid (PVBA Starter)");
INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Private Limited Company (LTD)", "Société Anonyme (SA)", "Naamloze vennootschap (NV)");
INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Partnership Limited By Shares", "Société en Commandité par Actions (SCA)", "Commanditaire Vennootschap op Aandelen (CVA)");
INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Limited Liability Partnership (LLP)", "Société Coopérative à Responsabilité Limitée (SCRL)", "Coöperatieve Vennootschap met Beperkte Aansprakelijkheid (CVBA)");
INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Illimited Liability Partnership", "Société Coopérative à Responsabilité Illimitée (SCRI)", "Coöperatieve Vennootschap met Onbeperkte en Hoofdelijke Aansprakelijkheid (CVOA)");
INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("General Partnership", "Société en Nom Collectif (SNC)", "Vennootschap onder Firma (VOF)");
INSERT INTO BTS.LEGAL_FORM (LABEL, LABEL_FR, LABEL_NL)
VALUES ("Limited Partnership", "Société en Commandité Simple (SCS)", "Commanditaire Vennootschap (CV)");

INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("CASH", "COMPTANT", "CONTANT", 0, 0, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("TRANSFER", "VIREMENT", "OVERSCHRIJVING", 0, 0, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("CHECK", "CHEQUE", "BANKCHEQUE", 0, 0, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("30 days", "30 jours", "30 dagen", 30, 0, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("60 days", "60 jours", "60 dagen", 60, 0, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("90 days", "90 jours", "90 dagen", 90, 0, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("30 days end of month", "30 jours fin du mois", "30 dagen einde of mand", 30, 1, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("60 days end of month", "60 jours fin du mois", "60 dagen einde of mand", 60, 1, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("90 days end of month", "90 jours fin du mois", "90 dagen einde of mand", 90, 1, 0);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("30 days end of month the 10", "30 jours fin du mois le 10", "30 dagen einde of mand de 10", 30, 1, 10);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("60 days end of month the 10", "60 jours fin du mois le 10", "60 dagen einde of mand de 10", 60, 1, 10);
INSERT INTO BTS.PAYMENT_MODE (LABEL, LABEL_FR, LABEL_NL, DAYS_NUMBER, MONTH_END, ADDITIONAL_DAYS)
VALUES ("90 days end of month the 10", "90 jours fin du mois le 10", "90 dagen einde of mand de 10", 70, 1, 10);

