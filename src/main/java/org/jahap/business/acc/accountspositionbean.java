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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.text.html.HTMLDocument;
import org.apache.log4j.Logger;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Accounts;
import org.jahap.entities.Address;
import org.jahap.entities.Bill;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Payed;
import org.jahap.entities.Rates;
import org.jahap.entities.Revenue;
import org.jahap.entities.Vat;
import org.jahap.entities.accountsposition_ie;

/**
 *
 * @author russ
 */
public class accountspositionbean extends DatabaseOperations implements accountsposition_i{
    static Logger log = Logger.getLogger(accountspositionbean.class.getName()); 
    JahapDatabaseConnector dbhook;
    private static List<AccountPosition> allrecordlist;
    
    /**
     * Constructor
     */
    public accountspositionbean(){
        long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from AccountPosition t ORDER BY t.id");
            List<AccountPosition>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  AccountPosition t ORDER BY t.id");
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from AccountPosition t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
      public AccountPosition getDataRecord(long id){
       int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return allrecordlist.get(currentRecordNumber);
        
   }
    
     /**
     *
     * @param searchstring
     * @return
     */
    public List<AccountPosition>SearchForAccPos(String searchstring){
    
        return allrecordlist;
    }
    
    /**
     *
     */
    public void createNewEmptyRecord() {
        log.debug("Function entry -createNewEmptyRecord -");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<AccountPosition>();
            numberOfLastRecord++;
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
                AccountPosition emptyacc = new AccountPosition();
        
       
        allrecordlist.add(emptyacc);
       
        currentRecordNumber=numberOfLastRecord;
         saveRecord(); // save the last added Record
        log.debug("New Record Number " + String.valueOf(allrecordlist.get(currentRecordNumber).getId()) );
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
        log.debug("Function exit -createNewEmptyRecord - ");
    }

    /**
     *
     */
    public void nextRecordBackward() {
        if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
    }

    /**
     *
     */
    public void nextRecordForeward() {
        if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
    }

    /**
     *
     */
    public void saveRecord() {
        if (newEmptyRecordCreated=true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          RefreshAllRecords();
                
      }
      if (newEmptyRecordCreated=false){
          
          saveOldRecord();
      }
    }
    
   
        
       
    /**
     *
     * @return
     */
    public AccountPosition getLastPosition(){
             if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber);
        return null;
    }
    
    
    
    /**
     *
     * @param accpos for search
     * @return Accountposition 
     * 
     * If the accpos includes a record id  which exits in the DB table this record
     * will be returned.
     * 
     * If the ID is unknown, this function searches for Rate,Posname,Ratedate and Amount,Price.
     * If there is no record which can be exactly detected by this combination - null will be returned
     * 
     * Sets focus of bean on the found record.
     */
    public AccountPosition searchForAccPosition(AccountPosition accpos){
          int iAmount=0;
          int iPrice=0;
          int iRate=0;
          int iPosname=0;
          int iRateDate=0;
          ArrayList<AccountPosition> erma=new ArrayList<AccountPosition>();
          AccountPosition ola=new AccountPosition();
          int number=0;
          for(AccountPosition ikk:allrecordlist){
                if (accpos.getId()==ikk.getId()){
                    currentRecordNumber=number;
                    return ikk;
                  }
                   number++;                 
                }
          
          // if the Positon can be identify by one of this attributes
          
          // Price
          number=0;
          for(AccountPosition ikk:allrecordlist){
                         if(ikk.getPrice()==accpos.getPrice()){
                           iPrice++;
                           erma.add(ikk);
                           ola=ikk;
                           number++;
                         }      
                    }
            
                if(iPrice==1){
                    currentRecordNumber=number;
                    return ola;
                }
            
                
                // Amount
                number=0;
                 for(AccountPosition ikx:erma){
                              
                              
                              if(ikx.getAmount()==accpos.getAmount()){
                                   erma.remove(ikx);
                               }
                             
                             if(ikx.getAmount()==accpos.getAmount()){
                                 iAmount++;
                                 ola=ikx;
                               }
                            
                      number++;
                  }
                
                 if(iAmount==1){
                                 currentRecordNumber=number;
                                 return ola;
                              }  
                
                // RAte
                number=0;
                 for(AccountPosition ikc:erma){
                              
                              
                              if(ikc.getRate()==accpos.getRate()){
                                   erma.remove(ikc);
                               }
                             
                             if(ikc.getRate()==accpos.getRate()){
                                 iRate++;
                                 ola=ikc;
                               }
                            
                      number++;
                  }
                            
                      if(iRate==1){
                                 currentRecordNumber=number;
                                 return ola;
                              }  
                         
                 // Posname        
                         number=0;      
                        for(AccountPosition iku:erma){
                                    
                                  if(iku.getPositionname()==accpos.getPositionname()){
                                   erma.remove(iku);
                               }
                             
                             if(iku.getPositionname()==accpos.getPositionname()){
                                 iPosname++;
                                 ola=iku;
                               }
                            
                                }
                                    
                         
                         
                         if (iPosname==1){
                                currentRecordNumber=number;
                               return ola;   
                         }
                         
                         
                    // ratedate     
                         
                         number++;
                       for(AccountPosition iku:erma){
                                    
                                  if(iku.getRatedate()==accpos.getRatedate()){
                                   erma.remove(iku);
                               }
                             
                             if(iku.getRatedate()==accpos.getRatedate()){
                                 iRateDate++;
                                 ola=iku;
                               }
                            
                             }
                                    
                         
                         
                         if (iRateDate==1){
                              int ij=0;
                                for(AccountPosition ikk:allrecordlist){
                                      if(ola.getId()==allrecordlist.get(ij).getId()){
                                          currentRecordNumber=ij;
                                        return ola;     
                                      }
                                    ij++;
                                }
                               
                               
                               
                         }
                         
                    
            
            
            
                
            
            
       return null; 
    }
    
    
    
     private void saveNewRecord(){
         log.debug("Function entry savenewrecord");
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
               AccountPosition merge = dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
                log.debug("Function entry " +  String.valueOf(currentRecordNumber) + "  --  " + String.valueOf(allrecordlist.get(currentRecordNumber).getId()) ); 
            //dbhook.getEntity().persist(allrecordlist.get(currentRecordNumber));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from AccountPosition t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
            //currentRecordNumber++;
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
         log.debug("Function exit saveNewRecord");
     } 
    
    
     private void saveOldRecord(){
         log.debug("Function entry saveoldRecord");
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().refresh(dbhook.getEntity().find(AccountPosition.class,allrecordlist.get(currentRecordNumber).getId() ));
            
            
            dbhook.getEntity().getTransaction().commit();
        }
         log.debug("Function exit saveOldRecord");
    } 
    
    
      /**
     *
     */
    public void quitDBaccess(){
       dbhook.getEntity().close();
   } 
     
       // ########################## Position handling ############### 
      
      
    /**
     *
     * @return
     */
    public Accounts getAccount() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAccount();
        return null;
    }

    /**
     *
     * @return
     */
  

    /**
     *
     * @return
     */
    public BigInteger getArticle() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getArticle();
        return null;
    }

    

    /**
     *
     * @return
     */
    public boolean getBilled() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getBilled();
        return false;
    }

    /**
     *
     * @return
     */
    public boolean getDebit() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getDebit();
        return false;
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
    public String getPositionname() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getPositionname();
        return null;
    }

    /**
     *
     * @return
     */
    public Rates getRate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRate();
        return null;
    }

    /**
     *
     * @return
     */
    public Date getRatedate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRatedate();
        return null;
    }

    /**
     *
     * @return
     */
    public Collection<Revenue> getRevenueCollection() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevenueCollection();
        return null;
    }

    /**
     *
     * @param account
     */
    public void setAccount(Accounts account) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAccount(account);
    }

   

    

    /**
     *
     * @param billed
     */
    public void setBilled(boolean billed) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBilled(billed);
    }

    /**
     *
     * @param debit
     */
    public void setDebit(boolean debit) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setDebit(debit);
    }

    /**
     *
     * @param positionname
     */
    public void setPositionname(String positionname) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setPositionname(positionname);
    }

    /**
     *
     * @param rate
     */
    public void setRate(Rates rate) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRate(rate);
    }

    /**
     *
     * @param ratedate
     */
    public void setRatedate(Date ratedate) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRatedate(ratedate);
    }

    public int getAmount() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAmount();
        return 0;
    }

    public double getPrice() {
     if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getPrice();
        return 0;
    }

    public void setAmount(int amount) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAmount(amount);
    }

    public void setPrice(double price) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setPrice(price);
    }

    public boolean isCanceled() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).isCanceled();
        return false;
    }

    public long getCanceledposition() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCanceledposition();
        return 0;
    }
    
    public void setCanceledposition(long canceled) {
       if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCanceledposition(canceled);
    }

    public void setCanceled(boolean canceled) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCanceled(canceled);
    }
    
    

    public long getBill() {
         if( tabelIsEmpty!=true) {
             
                   return allrecordlist.get(currentRecordNumber).getBill();
              
         }
        return 0;
    }

    public Payed getPayed() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getPayed();
        return null;
    }

    public Collection<Vat> getVatCollection() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getVatCollection();
        return null;
    }

    public void setBill(long bill) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBill(bill);
    }

    public void setId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPayed(Payed payed) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setPayed(payed);
    }

    public void setRevenueCollection(Collection<Revenue> revenueCollection) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).getRevenueCollection();
    }

    public void setVatCollection(Collection<Vat> vatCollection) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setVatCollection(vatCollection);
    }

    
    
}
