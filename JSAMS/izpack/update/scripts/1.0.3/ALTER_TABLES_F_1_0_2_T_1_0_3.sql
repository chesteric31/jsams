ALTER TABLE BTS.BILL DROP COLUMN PAID;
ALTER TABLE BTS.BILL ADD PAYMENT_DATE DATE;

UPDATE BTS.LEGAL_FORM SET LABEL_FR="Société Privée à Responsabilité Limitée (Unipersonnelle) (SPRL)" WHERE ID = 2;
UPDATE BTS.LEGAL_FORM SET LABEL_FR="Société Anonyme (SA)" WHERE ID = 4;
UPDATE BTS.LEGAL_FORM SET LABEL_FR="Société en Commandité par Actions (SCA)" WHERE ID = 5;
UPDATE BTS.LEGAL_FORM SET LABEL_FR="Société Coopérative à Responsabilité Limitée (SCRL)" WHERE ID = 6;
UPDATE BTS.LEGAL_FORM SET LABEL_NL="Coöperatieve Vennootschap met Beperkte Aansprakelijkheid (CVBA)" WHERE ID = 6;
UPDATE BTS.LEGAL_FORM SET LABEL_FR="Société Coopérative à Responsabilité Illimitée (SCRI)" WHERE ID = 7;
UPDATE BTS.LEGAL_FORM SET LABEL_NL="Coöperatieve Vennootschap met Onbeperkte en Hoofdelijke Aansprakelijkheid (CVOA)" WHERE ID = 7;
UPDATE BTS.LEGAL_FORM SET LABEL_FR="Société en Nom Collectif (SNC)" WHERE ID = 8;
UPDATE BTS.LEGAL_FORM SET LABEL_FR="Société en Commandité Simple (SCS)" WHERE ID = 9;
