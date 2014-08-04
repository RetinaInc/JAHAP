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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Revaccounts;
import org.jahap.entities.Revenue;

/**
 *
 * @author russ
 */
public class revenuebean extends DatabaseOperations  implements revenue_i {

    JahapDatabaseConnector dbhook;
    private static List<Revenue> allrecordlist; 
    
    public revenuebean(){
       long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revenue t ORDER BY t.id");
            List<Revenue>allroomslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allroomslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revenue t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
        
        
        
       System.out.println("=========>dbconnection");
           // If the table is yet empty, init List 
        

       
       
   }
    
    
     private void RefreshAllRecords(){
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revenue t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }  public List<Revenue>SearchForRev(String searchstring){
    
        return allrecordlist;
    }
    
    public boolean selectRevPos(long id){
        int i=-1;
        do {
             i++;
            try {
                if (allrecordlist.get(i).getId() == id) {
                    currentRecordNumber = i;                    
                    return true;
                }                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
          }while(allrecordlist.get(i).getId()!=id);
        return false;
    }
    
    
    public void createNewEmptyRecord() {
        Calendar cal  = Calendar.getInstance();
        if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Revenue>();
            numberOfLastRecord++;
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
                Revenue emptyacc = new Revenue();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
        allrecordlist.get(currentRecordNumber).setRevdate(cal.getTime());
        setNewEmptyRecordCreadted();
        tabelIsInit=true; //
    }

    public void nextRecordBackward() {
         if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
    }

    public void nextRecordForeward() {
        if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
    }
    
    private void saveNewRecord(){
        revaccountsbean revAcc=new revaccountsbean(); 
        
        if (revAcc.SearchForRevAccount(allrecordlist.get(currentRecordNumber).getRevaccount().getId())!=null){
                     allrecordlist.get(currentRecordNumber).setRevaccount(revAcc.SearchForRevAccount(allrecordlist.get(currentRecordNumber).getRevaccount().getId()));            
        }
        
        
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
           
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revenue t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
     } 

    public void saveRecord() {
       if (newEmptyRecordCreated=true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          
      }
      if (newEmptyRecordCreated=false){
          saveOldRecord();
      }
    }
    
     private void saveOldRecord(){
         
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Revaccounts.class,allrecordlist.get(currentRecordNumber).getId() );
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 

    public void quitDBaccess() {
        dbhook.getEntity().close();
    }

    
       // ####################### Getter and Setters ############   
    
    public AccountPosition getAccountposition() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAccountposition();
        return null;
    }

    public boolean getDebit() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getDebit();
        return false;
    }

    public Long getId() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

    public Revaccounts getRevaccount() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevaccount();
        return null;
    }

    public void setAccountposition(AccountPosition accountposition) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAccountposition(accountposition);
    }

    public void setDebit(boolean debit) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setDebit(debit);
    }

    public void setRevaccount(Revaccounts revaccount) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRevaccount(revaccount);
    }
    
    public void setRevaccount(long revaccid){
           revaccountsbean rac=new revaccountsbean();
            if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRevaccount(rac.getRevAccount(revaccid));
           
           
    }

    public double getAmount() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAmount();
        return 0;
    }

    public void setAmount(double amount) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAmount(amount);
    }

    public Date getRevdate() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevdate();
        return null;
    }

    public void setRevdate(Date revdate) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRevdate(revdate);
    }
    
}
