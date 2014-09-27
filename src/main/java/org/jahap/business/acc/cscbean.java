/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jahap.business.acc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jahap.entities.Accounts;
import org.jahap.entities.Csc;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Rates;

/**
 *
 * @author russ
 */
public class cscbean extends DatabaseOperations implements csc_i {

    JahapDatabaseConnector dbhook;
    private static List<Csc> allrecordlist;

    public cscbean() {
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();

        try {

            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Csc t ORDER BY t.id");
            List<Csc> alladdresseslist = query_AllDbRecords.getResultList();
            numberOfLastRecord = alladdresseslist.size() - 1;
        } catch (Exception e) {
            numberOfLastRecord = 0;
        }

        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Csc t ORDER BY t.id");
        allrecordlist = query_AllDbRecords.getResultList();

        try {
            testg = allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty = false;
            tabelIsInit = true;
        } catch (Exception e) {
            tabelIsEmpty = true;
        }

        System.out.println("=========>dbconnection Accounts");
           // If the table is yet empty, init List 

    }

    public List<Csc> SearchForOcc(String searchstring) {

        return allrecordlist;
    }

    public void createNewEmptyRecord() {
        if (tabelIsEmpty == true) {
            allrecordlist = new ArrayList<Csc>();
            numberOfLastRecord++;
        }

        if (tabelIsEmpty == false) {
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        /*
         if(numberOfLastRecord==-1){
            
         numberOfLastRecord++;
            
         }
        
         if(numberOfLastRecord>-1){
         numberOfLastRecord++;
         } */
        Csc emptyacc = new Csc();

        allrecordlist.add(emptyacc);
        this.currentRecordNumber = numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit = true; // Set Tabel iniated - List is connected
    }

    public void nextRecordBackward() {
        if (currentRecordNumber > 0) {
            currentRecordNumber--;
        }
    }

    private void RefreshAllRecords() {
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Csc t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord = allrecordlist.size() - 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void nextRecordForeward() {
        if (currentRecordNumber < numberOfLastRecord) {
            currentRecordNumber++;
        }
    }

    public void saveRecord() {
        if (newEmptyRecordCreated == true) {
            saveNewRecord();
            setNewEmptyRecordSaved();

        }
        if (newEmptyRecordCreated == false) {
            saveOldRecord();
        }
    }

    private void saveOldRecord() {
        if (newEmptyRecordCreated == false) {
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Csc.class, allrecordlist.get(this.currentRecordNumber).getId());
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));

            dbhook.getEntity().getTransaction().commit();
        }
    }

    private void saveNewRecord() {
        if (newEmptyRecordCreated = true) {
            try {
                dbhook.getEntity().getTransaction().begin();
                dbhook.getEntity().persist(allrecordlist.get(this.currentRecordNumber));

                dbhook.getEntity().getTransaction().commit();
                newEmptyRecordCreated = false;
                allrecordlist.clear();
                query_AllDbRecords = dbhook.getEntity().createQuery("select t from Csc t ORDER BY t.id"); // Refresh list
                allrecordlist = query_AllDbRecords.getResultList();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void quitDBaccess() {
        dbhook.getEntity().close();
    }

    public void moveToRecordwithAccount(Accounts acc) {

        int index = -1;

        for (index = 0; index <= allrecordlist.size() - 1; index++) {
            if (allrecordlist.get(index).getAccount() == acc) {
                currentRecordNumber = index;
            }

        }
    }

     // ###################################  Getters and Setters
    public Accounts getAccount() {
        if (tabelIsEmpty != true) {
            return allrecordlist.get(currentRecordNumber).getAccount();
        }
        return null;
    }

    public Date getFromdate() {
        if (tabelIsEmpty != true) {
            return allrecordlist.get(currentRecordNumber).getFromdate();
        }
        return null;
    }

    public Long getId() {
        if (tabelIsEmpty != true) {
            return allrecordlist.get(currentRecordNumber).getId();
        }
        return null;
    }

    public Rates getRate() {
        if (tabelIsEmpty != true) {
            return allrecordlist.get(currentRecordNumber).getRate();
        }
        return null;
    }

    public Date getTodate() {
        if (tabelIsEmpty != true) {
            return allrecordlist.get(currentRecordNumber).getTodate();
        }
        return null;
    }

    public void setAccount(Accounts account) {
        if (tabelIsInit == false || tabelIsEmpty == true) {
            createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setAccount(account);
    }

    public void setFromdate(Date fromdate) {
        if (tabelIsInit == false || tabelIsEmpty == true) {
            createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setFromdate(fromdate);
    }

    public void setRate(Rates rate) {
        if (tabelIsInit == false || tabelIsEmpty == true) {
            createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setRate(rate);

    }

    public void setTodate(Date todate) {
        if (tabelIsInit == false || tabelIsEmpty == true) {
            createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setTodate(todate);
    }

    public double getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getAmount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPrice(double price) {
        if (tabelIsInit == false || tabelIsEmpty == true) {
            createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setPrice(price);
    }

    public void setAmount(int amount) {
        if (tabelIsInit == false || tabelIsEmpty == true) {
            createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setAmount(amount);
    }

    public String getService() {
        if (tabelIsEmpty != true) {
            return allrecordlist.get(currentRecordNumber).getService();
        }
        return null;
    }

    public void setService(String service) {
        if (tabelIsInit == false || tabelIsEmpty == true) {
            createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setService(service);
    }

}
