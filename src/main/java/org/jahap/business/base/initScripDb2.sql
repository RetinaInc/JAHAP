
CREATE TABLE Paymenttypes (
                ID BIGINT ,
                Name VARCHAR(50) ,
                receivable BOOLEAN ,
                CONSTRAINT Paymenttypes_pk PRIMARY KEY (ID)
);

CREATE TABLE CHOICE (
                ID BIGINT NOT NULL, 
                GROUPID INTEGER, 
                GROUPCODE VARCHAR(5), 
                GROUPNAME VARCHAR(50), 
                CHOICECODE VARCHAR(5), 
                CHOICETEXT VARCHAR(100), 
                CHOICEINT INTEGER, 
                CHOICEFLOAT DECIMAL(8, 2), 
                "LANGUAGE" INTEGER, 
                PRIMARY KEY (ID)
);


CREATE TABLE hotel (
                ID BIGINT ,
                hotel_code VARCHAR(10) ,
                hotel_name VARCHAR(100) ,
                hotel_adress BIGINT,
                hotel_bankaccountdata1 VARCHAR(200) ,
                hotel_bankaccountdata2 VARCHAR(200) ,
                hotel_language BIGINT,
                hotel_country BIGINT,
                hotel_currency BIGINT,
                hotel_footertext VARCHAR(200) ,
             
);

create table reports( id integer not null primary key, 
                      name varchar(50),
                      language varchar(5),
                      description varchar(200),
                      report_group varchar(50),
                      report blob(16M)),
                      report_layout blob(16M));

CREATE TABLE language ( id integer not null primary key, 
                        language_code varchar(5),
                        language_name varchar(100);

CREATE TABLE currency ( id integer not null primary key, 
                        currency_code varchar(5),
                        currency_name varchar(100),
                        currency_symbol char(1);
                        
CREATE TABLE country ( id integer not null primary key, 
                        country_code varchar(10),
                        country_name varchar(100),
                        country_currency integer;

CREATE TABLE payed (
                ID BIGINT,
                debit BOOLEAN,
                canceled BOOLEAN,
                canceledpayment BIGINT,
                paymenttype BIGINT,
                
                total DECIMAL(8,2),
                openpos BOOLEAN,
                CONSTRAINT payed_pk PRIMARY KEY (ID)
);



CREATE TABLE rates (
                ID BIGINT NOT NULL,
                Name VARCHAR(255),
                price DECIMAL(8,2),
                Code VARCHAR(50),
                REVACCOUNT BIGINT,
                overnight BOOLEAN,
                vattype BIGINT,
                net BOOLEAN,
                CONSTRAINT rates_pk PRIMARY KEY (ID)
);
/* NO HOLDER */
CREATE TABLE SEQ_STORE (
               TABLE_NAME VARCHAR(255) NOT NULL,
               VALUE  BIGINT,
               PRIMARY KEY (TABLE_NAME)
);
/* BILL NO HoLDER */ 
CREATE TABLE bill_no (
               Billno BIGINT NOT NULL,
               PRIMARY KEY (Billno)
);

CREATE TABLE log_accounting (
                ID BIGINT ,
                amount DECIMAL(8,2),
                Date VARCHAR(50),
                time VARCHAR(50),
                account VARCHAR(50),
                account_position BIGINT ,
                positionname VARCHAR(255),
                CONSTRAINT log_accounting_pk PRIMARY KEY (ID)
);


CREATE TABLE accounts (
                ID BIGINT ,
                balance DECIMAL(8,2),
                checkout BOOLEAN,
                checkinDate VARCHAR(50),
                checkoutDate VARCHAR(50) ,
                CSCservice BIGINT,
                address BIGINT,
                reservation BIGINT,                
                CONSTRAINT accounts_pk PRIMARY KEY (ID)
);

            
CREATE TABLE Vattype (
                ID BIGINT,
                name VARCHAR(100),
                datevat DATE,
                percentage DECIMAL(8,2),
                accountposition BIGINT,
                CONSTRAINT Vattype_pk PRIMARY KEY (ID)
);


CREATE TABLE VAT (
                ID BIGINT,
                Amount DECIMAL(8,2),
                debit BOOLEAN,
                date DATE,
                vattype BIGINT,
                Accountposition BIGINT,
                CONSTRAINT VAT_pk PRIMARY KEY (ID)
);


CREATE TABLE account_position (
                ID BIGINT ,
                bill BIGINT,
                vat BIGINT,
                payment BIGINT,
                billed BOOLEAN ,
                amount INTEGER,
                price DECIMAL(8,2),
                debit BOOLEAN ,
                rate BIGINT ,
                canceledposition BIGINT,
                canceled BOOLEAN, 
                rateDate DATE ,
                account BIGINT ,
                positionname VARCHAR(255),
                CONSTRAINT account_position_pk PRIMARY KEY (ID)
);


CREATE TABLE revenue (
                ID BIGINT ,
                amount DECIMAL(8,2),
                debit BOOLEAN ,
                accountposition BIGINT ,
                revaccount BIGINT,
                revdate DATE,
                CONSTRAINT revenue_pk PRIMARY KEY (ID)
);


CREATE TABLE ROOMS (
                ID BIGINT ,
                CATEGORY VARCHAR(255),
                CODE VARCHAR(255),
                NAME VARCHAR(255),
                CONSTRAINT SQL130111115648290 PRIMARY KEY (ID)
);




CREATE TABLE ADDRESS (
                ID BIGINT ,
                CHRISTIANNAME VARCHAR(255),
                CITY VARCHAR(255),
                EMAIL VARCHAR(255),
                NAME VARCHAR(255),
                PHONE VARCHAR(255),
                STREET VARCHAR(255),
                ZIPCODE VARCHAR(255),
                CONSTRAINT SQL130103114019040 PRIMARY KEY (ID)
);

CREATE TABLE bill (
                ID BIGINT,
                billno BIGINT,
                address BIGINT,
                billdate DATE,
                billname VARCHAR(100),
                billnostring VARCHAR(200),
                uuid VARCHAR(200),
                billchange TIMESTAMP,
                canceled BOOLEAN,
                temp_bill BOLLEAN,
                CanceledBill BIGINT,
                Total DECIMAL(8,2),
                CONSTRAINT bill_pk PRIMARY KEY (ID)
);



CREATE TABLE csc (
                ID BIGINT ,
                rate BIGINT ,
                fromDate DATE ,
                toDate DATE,
                sevice VARCHAR(100),
                account BIGINT ,
                price DECIMAL(10,2),
                amount SMALLINT,
                CONSTRAINT csc_pk PRIMARY KEY (ID)
);


CREATE TABLE RES (
                ID BIGINT ,
                arrivaltime VARCHAR(50),
                arrivaldate VARCHAR(50),
                departuretime VARCHAR(50),
                resno VARCHAR(50) ,                
                addressid BIGINT ,
                departuredate VARCHAR(50),
                CONSTRAINT RES_ID PRIMARY KEY (ID)
);


CREATE TABLE occ (
                ID BIGINT ,
                arrivaltime TIME,
                departuretime TIME,
                arrivaldate DATE,
                guest BIGINT NOT NULL,
                departuredate DATE,
                room BIGINT ,
                res BIGINT ,
                account BIGINT,
                CONSTRAINT occ_ID PRIMARY KEY (ID)
);


CREATE TABLE revaccounts (
                ID BIGINT NOT NULL,
                revaccountnumber BIGINT,
                name VARCHAR(255),                               
                CONSTRAINT revaccount_pk PRIMARY KEY (ID)
);

/* INDEXES */

CREATE INDEX ROOMS_idx
 ON ROOMS
 ( CODE ASC );


CREATE INDEX ADDRESS_idx
 ON ADDRESS
 ( NAME ASC );

CREATE INDEX ADDRESS_idx1
 ON ADDRESS
 ( NAME ASC );






CREATE INDEX RES_idx
 ON RES
 ( arrivaldate ASC, resno ASC );









-- CSC
 
ALTER TABLE csc ADD CONSTRAINT rates_csc_fk
FOREIGN KEY (rate)
REFERENCES rates (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE csc ADD CONSTRAINT accounts_csc_fk
FOREIGN KEY (account)
REFERENCES accounts (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

-- ACCOUNTPOSITION Constrains
 
ALTER TABLE ROOT.account_position ADD CONSTRAINT rates_account_position_fk
FOREIGN KEY (rate)
REFERENCES ROOT.rates (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.account_position ADD CONSTRAINT accounts_account_position_fk
FOREIGN KEY (account)
REFERENCES ROOT.accounts (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;





--  RES
ALTER TABLE ROOT.RES ADD CONSTRAINT ADDRESS_RES_fk
FOREIGN KEY (addressid)
REFERENCES ROOT.ADDRESS (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;




-- OCC

ALTER TABLE ROOT.occ ADD CONSTRAINT RES_occ_fk
FOREIGN KEY (res)
REFERENCES ROOT.RES (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;


ALTER TABLE ROOT.occ ADD CONSTRAINT ADDRESS_occ_fk
FOREIGN KEY (guest)
REFERENCES ADDRESS (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE occ ADD CONSTRAINT accounts_occ_fk
FOREIGN KEY (account)
REFERENCES accounts (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;


ALTER TABLE ROOT.occ ADD CONSTRAINT ROOMS_occ_fk
FOREIGN KEY (room)
REFERENCES ROOT.ROOMS (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

-- REV

ALTER TABLE ROOT.revenue ADD CONSTRAINT revenue_revenueacc_fk
FOREIGN KEY (revaccount)
REFERENCES ROOT.Revaccounts (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;


ALTER TABLE ROOT.revenue ADD CONSTRAINT account_position_revenue_fk
FOREIGN KEY (accountposition)
REFERENCES ROOT.account_position (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;




-- ACC


ALTER TABLE ROOT.accounts ADD CONSTRAINT ADDRESS_accounts_fk
FOREIGN KEY (address)
REFERENCES ADDRESS (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ROOT.accounts ADD CONSTRAINT RES_accounts_fk
FOREIGN KEY (reservation)
REFERENCES RES (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;



ALTER TABLE ROOT.rates ADD CONSTRAINT Vattype_rates_fk
FOREIGN KEY (vattype)
REFERENCES Vattype (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ROOT.VAT ADD CONSTRAINT Vattype_VAT_fk
FOREIGN KEY (vattype)
REFERENCES Vattype (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ROOT.account_position ADD CONSTRAINT VAT_account_position_fk
FOREIGN KEY (VAT)
REFERENCES VAT (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;



ALTER TABLE ROOT.account_position ADD CONSTRAINT payed_account_position_fk
FOREIGN KEY (payment)
REFERENCES payed (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;



-- MISC

ALTER TABLE ROOT.bill ADD CONSTRAINT ADDRESS_bill_fk
FOREIGN KEY (address)
REFERENCES ROOT.ADDRESS (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;


ALTER TABLE ROOT.payed ADD CONSTRAINT Paymenttypes_payed_fk
FOREIGN KEY (paymenttype)
REFERENCES ROOT.Paymenttypes (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ROOT.rates ADD CONSTRAINT revaccounts_rates_fk
FOREIGN KEY (revaccount)
REFERENCES revaccounts (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;