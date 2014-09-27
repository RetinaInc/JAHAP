/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
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

package org.jahap.business.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.jahap.entities.Currency;
import org.jahap.entities.JahapDatabaseConnector;

/**
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class currencybean extends DatabaseOperations implements currency_i  {
      JahapDatabaseConnector dbhook;
    private static List<Currency> allrecordlist;
     static Logger log = Logger.getLogger(languagebean.class.getName());

    /**
     *
     */
    public currencybean(){
       
         log.debug("Function entry billbean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Currency t ORDER BY t.id");
            List<Currency>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Currency t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          
        log.debug("Function entry billbean");    
        
    }

    
     public List<Currency>SearchForCurrency(String searchstring){
        
         log.debug("Function entry SearchForBill");
      
         
        
        log.debug("Function exit SearchForBill ");
        return allrecordlist;
    }
    
    
      public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Currency>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               Currency emptyacc = new Currency();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
       
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
    }
     
     
      public List<String>SearchForCountry(currency dd){
        List<String>hh=new ArrayList<String>();
         log.debug("Function entry SearchForCountry" + String.valueOf(dd));
        for(Currency u:allrecordlist){
            if(dd==dd.code){
                hh.add(u.getCurrencyCode());
            }
            
            if(dd==dd.name){
                hh.add(u.getCurrencyName());
            }
            
            if(dd==dd.symbol){
                hh.add(String.valueOf(u.getCurrencySymbol()));
            }
        }
        return hh;
     }
     
    

    @Override
    public void nextRecordBackward() {
         log.debug("Function entry nextRecordBackward");
        
       if (currentRecordNumber>0) {
            currentRecordNumber--;
        }  
        log.debug("Function exit nextRecordBackward");
    }

    @Override
    public void nextRecordForeward() {
        log.debug("Function entry nextRecordForeward");
        
       if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }    
       
        log.debug("Function exit nextRecordForeward ");
    }

    @Override
    public void saveRecord() {
       log.debug("Function entry saveRecord");
         
         if (newEmptyRecordCreated=true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          RefreshAllRecords();
        
         }
      if (newEmptyRecordCreated=false){
          saveOldRecord();
      }
        log.debug("Function exit saveRecord ");
    }

    private void RefreshAllRecords(){
         
         log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Currency t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }
    
    
     public Currency getDataRecord(long id){
        if(id==0)return null;
        log.debug("Function entry getDataRecord");
       int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
            return null;
        }
        
        log.debug("Function exit getDataRecord " + String.valueOf(currentRecordNumber) );
        return allrecordlist.get(currentRecordNumber);
        
   }
    
    public Currency getLastPosition(){
          log.debug("Function entry getLastPosition(");
             if( tabelIsEmpty!=true){ 
                 log.debug("Function exit getLastPosition");   
              return allrecordlist.get(currentRecordNumber);
             }
             log.debug("Function exit getLastPosition with Null");
        return null;
    }
     
    
     private void saveNewRecord(){
          log.debug("Function entry saveNewRecord");
          
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Currency t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
     }
    
     
     
    @Override
    public void quitDBaccess() {
       log.debug("Function entry quitDBaccess");
       dbhook.getEntity().close();
       
           log.debug("Function exit quitDBaccess");
    }

     private void saveOldRecord(){
           log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().refresh(dbhook.getEntity().find(Currency.class,allrecordlist.get(currentRecordNumber).getId() ));
            
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    }

    @Override
    public String getCurrencyCode() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCurrencyCode();
        return null;
    }

    @Override
    public String getCurrencyName() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCurrencyName();
        return null;
    }

    @Override
    public Character getCurrencySymbol() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCurrencySymbol();
        return null;
    }

    @Override
    public Integer getId() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

    @Override
    public void setCurrencyCode(String currencyCode) {
       if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCurrencyCode(currencyCode);
    }

    @Override
    public void setCurrencyName(String currencyName) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCurrencyName(currencyName);
    }

    @Override
    public void setCurrencySymbol(Character currencySymbol) {
       if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCurrencySymbol(currencySymbol);
        
    }
}
