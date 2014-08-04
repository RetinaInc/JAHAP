
CREATE TABLE Paymenttypes (
                ID BIGINT ,
                Name VARCHAR(50) ,
                receivable BOOLEAN 
                
);





CREATE TABLE payed (
                ID BIGINT,
                debit BOOLEAN,
                paymenttype BIGINT,
                total DECIMAL(8,2),
                openpos BOOLEAN
                
);

CREATE TABLE SEQ_STORE (
               TABLE_NAME VARCHAR(255),
               VALUE  BIGINT
);

CREATE TABLE bill_no (
               Billno BIGINT
);

CREATE TABLE rates (
                ID BIGINT NOT NULL,
                Name VARCHAR(255),
                price DECIMAL(8,2),
                Code VARCHAR(50),
                REVACCOUNT BIGINT,
                overnight BOOLEAN,
                vattype BIGINT,
                net BOOLEAN
                
);



CREATE TABLE log_accounting (
                ID BIGINT ,
                amount DECIMAL(8,2),
                Date VARCHAR(50),
                time VARCHAR(50),
                account VARCHAR(50),
                account_position BIGINT ,
                positionname VARCHAR(255)
                
);


CREATE TABLE accounts (
                ID BIGINT ,
                balance DECIMAL(8,2),
                checkout BOOLEAN,
                checkinDate VARCHAR(50),
                checkoutDate VARCHAR(50) ,
                CSCservice BIGINT,
                address BIGINT,
                reservation BIGINT                
                
);

            
CREATE TABLE Vattype (
                ID BIGINT,
                name VARCHAR(100),
                datevat DATE,
                percentage DECIMAL(8,2),
                accountposition BIGINT
                
);


CREATE TABLE VAT (
                ID BIGINT,
                Amount DECIMAL(8,2),
                debit BOOLEAN,
                date DATE,
                vattype BIGINT,
                Accountposition BIGINT
                
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
                positionname VARCHAR(255)
                
);


CREATE TABLE revenue (
                ID BIGINT ,
                amount DECIMAL(8,2),
                debit BOOLEAN ,
                accountposition BIGINT ,
                revaccount BIGINT,
                revdate DATE
               
);


CREATE TABLE ROOMS (
                ID BIGINT ,
                CATEGORY VARCHAR(255),
                CODE VARCHAR(255),
                NAME VARCHAR(255)
                
);




CREATE TABLE ADDRESS (
                ID BIGINT ,
                CHRISTIANNAME VARCHAR(255),
                CITY VARCHAR(255),
                EMAIL VARCHAR(255),
                NAME VARCHAR(255),
                PHONE VARCHAR(255),
                STREET VARCHAR(255),
                ZIPCODE VARCHAR(255)
                
);

CREATE TABLE bill (
                ID BIGINT,
                billno BIGINT,
                address BIGINT,
                billnostring VARCHAR(200),
                uuid VARCHAR(200),
                billdate DATE,
                billname VARCHAR(100),
                billchange TIMESTAMP,
                canceled BOOLEAN,
                temp_bill BOOLEAN,
                CanceledBill BIGINT,
                Total DECIMAL(8,2)
                
);



CREATE TABLE csc (
                ID BIGINT ,
                rate BIGINT ,
                fromDate DATE ,
                toDate DATE,
                sevice VARCHAR(100),
                account BIGINT 
                price DECIMAL(10,2),
                amount SMALLINT,
                
);


CREATE TABLE RES (
                ID BIGINT ,
                arrivaltime VARCHAR(50),
                arrivaldate VARCHAR(50),
                departuretime VARCHAR(50),
                resno VARCHAR(50) ,                
                addressid BIGINT ,
                departuredate VARCHAR(50)
                
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
                account BIGINT
                
);


CREATE TABLE revaccounts (
                ID BIGINT NOT NULL,
                revaccountnumber BIGINT,
                name VARCHAR(255)                               
                
);




-- CSC
 


-- ACCOUNTPOSITION Constrains
 














