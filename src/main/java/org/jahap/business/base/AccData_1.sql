



INSERT INTO ROOT.REVENUE (ID, DEBIT, ACCOUNTPOSITION, REVACCOUNT, AMOUNT,REVDATE) 
       VALUES (1, FALSE, 1, 1, 37.34,'2013-08-05'),
              (2, TRUE, 2, 1, 37.34,'2013-08-05'),
              (3, FALSE, 3, 1, 60.88,'2013-08-05'),   
              (4, FALSE, 4, 1, 19.62,'2013-08-05');



--checked
INSERT INTO ROOT.VAT(ID,AMOUNT,DEBIT,DATE,VATTYPE)
        VALUES(1,7.10,FALSE,'2013-08-05',2),
              (2,7.10,FALSE,'2013-08-05',2),
              (3,11.56,FALSE,'2013-08-05',2),
              (4,3.72,FALSE,'2013-08-05',2);

              



--checked
INSERT INTO ROOT.ACCOUNT_POSITION (ID, BILLED, AMOUNT,PRICE, DEBIT, RATE, RATEDATE, ACCOUNT, POSITIONNAME,CANCELED, CANCELEDPOSITION,BILL,VAT,PAYMENT) 
      VALUES (1, TRUE,1, 44.44, FALSE, 1, '2013-08-05', 1, 'xTEXT2',TRUE,1,1,1,1),
             (2, TRUE,1, 44.44, TRUE, 1, '2013-08-05', 1, 'xTEXT2',FALSE,1,1,2,1),
             (3, FALSE,1, 72.44, FALSE, 1, '2013-08-05', 1, 'xTEXT4',FALSE,0,NULL,3,NULL),  
             (4, FALSE,1, 23.33, FALSE, 1, '2013-08-05', 2, 'xTEXT6',FALSE,0,NULL,4,NULL);
