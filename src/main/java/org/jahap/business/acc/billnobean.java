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
import java.util.List;
import org.apache.log4j.Logger;
import org.jahap.entities.Bill;
import org.jahap.entities.BillNo;
import org.jahap.entities.JahapDatabaseConnector;

/**
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class billnobean extends DatabaseOperations implements BILLNO_i{
    
    JahapDatabaseConnector dbhook;
    private static List<BillNo> allrecordlist;
     static Logger log = Logger.getLogger(billnobean.class.getName());
    
     public billnobean(){
         log.debug("Function entry billnobean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from BillNo t ORDER BY t.id");
            List<BillNo>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  BillNo t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getBillno();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          
        log.debug("Function entry billnobean");    
        
         
     }
     
     
     public List<BillNo>SearchForBill(String searchstring){
        
         log.debug("Function entry SearchForBillbean");
         List<BillNo>cleanedList=new ArrayList<BillNo>();
         for(BillNo k:allrecordlist){
         
             // Remove ZeroBill
          
              cleanedList.add(k);
        
               
       }
        
        log.debug("Function exit SearchForBillbean ");
        return cleanedList;
    }
     
     
    public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<BillNo>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
                BillNo emptyacc = new BillNo();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
      
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
    } 
    
    

     

    public void nextRecordBackward() {
  log.debug("Function entry nextRecordBackward");
        
       if (currentRecordNumber>0) {
            currentRecordNumber--;
        }  
        log.debug("Function exit nextRecordBackward");
    
    }

    public void nextRecordForeward() {
      log.debug("Function entry nextRecordForeward");
        
       if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }    
       
        log.debug("Function exit nextRecordForeward ");    
    }

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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from BillNo t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }
    
     
      public BillNo getDataRecord(long id){
        if(id==0)return null;
        log.debug("Function entry getDataRecord");
       int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getBillno() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
            return null;
        }
        
        log.debug("Function exit getDataRecord " + String.valueOf(currentRecordNumber) );
        return allrecordlist.get(currentRecordNumber);
        
   }
    
      
    public BillNo getLastPosition(){
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from BillNo t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
     } 
      
     public void quitDBaccess(){
           log.debug("Function entry quitDBaccess");
       dbhook.getEntity().close();
       
           log.debug("Function exit quitDBaccess");
   } 
    
    
       private void saveOldRecord(){
           log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().refresh(dbhook.getEntity().find(BillNo.class,allrecordlist.get(currentRecordNumber).getBillno()));
            
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    } 
     
       
       
     
    public Bill getBill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getBillno() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBill(Bill bill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBillno(Long billno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
