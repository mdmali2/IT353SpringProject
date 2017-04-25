DROP TABLE ACCOUNT;
CREATE TABLE ACCOUNT
(
firstName VARCHAR(255) NOT NULL,
lastName VARCHAR(255) NOT NULL,
username VARCHAR(255) NOT NULL PRIMARY KEY,
password VARCHAR(255) NOT NULL,
admin INTEGER NOT NULL
);

INSERT INTO ACCOUNT (firstname, lastname, username, password, admin)
    VALUES ('itkstu','student','itkstu@ilstu.edu','student',1);

INSERT INTO ACCOUNT (firstname, lastname, username, password, admin)
    VALUES ('Matt','Malinowski','mdmali2@ilstu.edu','abc',0);

INSERT INTO ACCOUNT (firstname, lastname, username, password, admin)
    VALUES ('Will','McKinley','whmckin@ilstu.edu','abc',0);