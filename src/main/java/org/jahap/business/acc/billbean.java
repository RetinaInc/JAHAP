/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jahap.business.acc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Address;
import org.jahap.entities.Bill;
import org.jahap.entities.JahapDatabaseConnector;

/**
 *
 * @author russ
 */
public class billbean extends DatabaseOperations implements bill_i{
    
    JahapDatabaseConnector dbhook;
    private static List<Bill> allrecordlist;
     static Logger log = Logger.getLogger(billbean.class.getName());

    /**
     *
     */
    public billbean(){
       
         log.debug("Function entry billbean");
        long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Bill t ORDER BY t.id");
            List<AccountPosition>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Bill t ORDER BY t.id");
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

    /**
     *
     * @param searchstring
     * @return
     */
    public List<Bill>SearchForBill(String searchstring){
        
         log.debug("Function entry SearchForBill");
         List<Bill>cleanedList=new ArrayList<Bill>();
         for(Bill k:allrecordlist){
         
             // Remove ZeroBill
          if(k.getBillname()!="ZEROBILL"){
              cleanedList.add(k);
          }
               
       }
        
        log.debug("Function exit SearchForBill ");
        return cleanedList;
    }

    /**
     *
     */
    public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Bill>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            this.setBillchange();
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
                Bill emptyacc = new Bill();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
        this.setBillchange();
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
    }

    /**
     *
     */
    public void nextRecordBackward() {
        log.debug("Function entry nextRecordBackward");
        
       if (currentRecordNumber>0) {
            currentRecordNumber--;
        }  
        log.debug("Function exit nextRecordBackward");
    }

    /**
     *
     */
    public void nextRecordForeward() {
        log.debug("Function entry nextRecordForeward");
        
       if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }    
       
        log.debug("Function exit nextRecordForeward ");
    }

    /**
     *
     */
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Bill t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }

    /**
     *
     * @param id
     * @return
     */
    public Bill getDataRecord(long id){
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

    /**
     *
     * @return
     */
    public Bill getLastPosition(){
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Bill t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
     }

    /**
     *
     */
    public void quitDBaccess(){
           log.debug("Function entry quitDBaccess");
       dbhook.getEntity().close();
       
           log.debug("Function exit quitDBaccess");
   } 
      
       
       private Bill createZeroRecord(){
           
           
           log.debug("Function entry createZeroRecord");
           Bill hhh=new Bill();  
           hhh.setBillname("ZEROBILL");
           
           allrecordlist.add(hhh);
           saveRecord();
           log.debug("Function exit createZeroRecord " + String.valueOf(hhh.getId()) );
           return allrecordlist.get(allrecordlist.size()-1);
           
       }

    /**
     *
     * @return
     */
    public Bill getZeroRecord(){
           log.debug("Function entry getZeroRecord" );
           boolean kl=false;
           for(Iterator<Bill>jj=allrecordlist.iterator();jj.hasNext();){
                 Bill k=jj.next();
                 if("ZEROBILL".equalsIgnoreCase(k.getBillname())){
                     kl=true;
                     saveRecord();
                     log.debug("Function entry getZeroRecord " + String.valueOf(k.getId()));
                     return k;
                 }
           }
           if(kl==false){
                  log.debug("Function entry getZeroRecord ");
                  return createZeroRecord();
           }
           log.debug("Function entry getZeroRecord with Null");
           return null;
       }
       
       
      
       private void saveOldRecord(){
           log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().refresh(dbhook.getEntity().find(Bill.class,allrecordlist.get(currentRecordNumber).getId() ));
            
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    }

    /**
     *
     * @return
     */
    public Collection<AccountPosition> getAccountPositionCollection() {
        log.debug("Function entry getAccountPositionCollection ");
         Collection<AccountPosition> jk=new ArrayList<AccountPosition>();
        if( tabelIsEmpty!=true) {
              if(allrecordlist.get(currentRecordNumber).getBillname()!="ZEROBILL"){
                   log.debug("Function exit getAccountPositionCollection " + String.valueOf(jk));  
                  return allrecordlist.get(currentRecordNumber).getAccountPositionCollection(); 
                    
              }
              
              
        }
        log.debug("Function exit with null");
        return null;
 
    }
    
    /**
     *
     * @return
     */
    public Address getAddress() {
        log.debug("Function entry getAddress");
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getAddress();
                   
       }
        return null;
    }

    /**
     *
     * @return
     */
    public Date getBilldate() {
       
    if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getBilldate();
        return null;
    
    }

    /**
     *
     * @return
     */
    public String getBillname() {
    
       if( tabelIsEmpty!=true){ 
              
              return allrecordlist.get(currentRecordNumber).getBillname();
            }
        return null;    
    
    }

    /**
     *
     * @return
     */
    public long getBillno() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getBillno();
        return 0;
    
    }

    /**
     *
     * @return
     */
    public Boolean getCanceled() {
    if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCanceled();
        return null;
    }

    /**
     *
     * @return
     */
    public long getCanceledbill() {
            if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCanceledbill();
        return 0;
    
    }

    /**
     *
     * @return
     */
    public String getBillNoString() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getBillNoString();
        return null;
    }

    /**
     *
     * @return
     */
    public String getUUID() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getUUID();
        return null;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

    /**
     *
     * @return
     */
    public double getTotal() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getTotal();
        return 0;
    }

    /**
     *
     * @param accountPositionCollection
     */
    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAccountPositionCollection(accountPositionCollection);    
    
    }

    /**
     *
     * @param billno
     */
    public void setBillNoString(String billno) {
       if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBillNoString(billno);
        
    }

    /**
     *
     * @param address
     */
    public void setAddress(Address address) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAddress(address);
    }

    /**
     *
     * @param UUID
     */
    public void setUUID(String UUID) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setUUID(UUID);
        
    }

    /**
     *
     * @param billdate
     */
    public void setBilldate(Date billdate) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBilldate(billdate);
    
    }

    /**
     *
     * @param billname
     */
    public void setBillname(String billname) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBillname(billname);
        
        
    }

    /**
     *
     */
    public void setBillno() {
       
    
    }

    /**
     *
     * @param canceled
     */
    public void setCanceled(Boolean canceled) {
       if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCanceled(canceled);
        
    }

    /**
     *
     * @param canceledbill
     */
    public void setCanceledbill(long canceledbill) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCanceledbill(canceledbill);
        
        
    }

    /**
     *
     * @param total
     */
    public void setTotal(double total) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setTotal(total);
    }

    /**
     *
     * @return
     */
    public Timestamp getBillchange() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getBillchange();
        return null;
    }

    /**
     *
     * @param billchange
     */
    public void setBillchange(Timestamp billchange) {
        log.debug("Function entry setBillchange");
        Calendar cal  = Calendar.getInstance();
        Timestamp ggh = new Timestamp(cal.getTimeInMillis());
        allrecordlist.get(currentRecordNumber).setBillchange(ggh);
        log.debug("Function exit setBillchange");
        
    }

    /**
     *
     */
    public void setBillchange() {
         log.debug("Function entry setBillchange");
        Calendar cal  = Calendar.getInstance();
        Timestamp ggh = new Timestamp(cal.getTimeInMillis());
         try {
             allrecordlist.get(currentRecordNumber).setBillchange(ggh);
         } catch (Exception e) {
             e.printStackTrace();
         }
         log.debug("Function exit setBillchange");
    }

    /**
     *
     * @return
     */
    public Boolean isTemp_bill() {
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).isTemp_bill();
        return null;
    }

    /**
     *
     * @param temp_bill
     */
    public void setTemp_bill(Boolean temp_bill) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setTemp_bill(temp_bill);
    }
}
