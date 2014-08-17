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
 import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import org.jahap.business.acc.DatabaseOperations;
import org.jahap.business.base.ratesbean;
import org.jahap.business.base.vattypesbean;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Accounts;
import org.jahap.entities.Address;
import org.jahap.entities.Bill;
import org.jahap.entities.Csc;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Occ;
import org.jahap.entities.Rates;
import org.jahap.entities.Res;
import org.jahap.entities.Revenue;

import org.jahap.entities.Vat;

/**
 *
 * @author russ
 */
public class accountsbean extends DatabaseOperations implements accounts_i{
    static Logger log = Logger.getLogger(accountsbean.class.getName());
    
    private ObservableList<AccountInfo> k;
    JahapDatabaseConnector dbhook;
    private static List<Accounts> allrecordlist;
  private cscbean cscServices;
    
    /**
     * Constructor
     */
    public accountsbean(){
        // cscServices=new cscbean();
        long testg;
        dbhook = new JahapDatabaseConnector();
        cscServices= new cscbean();
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Accounts t ORDER BY t.id");
            List<Accounts>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Accounts t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          System.out.println("=========>dbconnection Accounts");
           // If the table is yet empty, init List 
        
         
        
    }
    
          /**
     *
     * @param searchstring
     * @return
     */
    public List<Accounts>SearchForAcc(String searchstring){
    
        return allrecordlist;
    }
    
    

    
    public List getAccOverview(){
        
        List<AccountInfo> k=new ArrayList<AccountInfo>();
          
   
        
        query_AllDbRecords = dbhook.getEntity().createQuery("Select t.id, r.arrivaldate, r.departuredate, a.name , t.balance from Accounts t JOIN t.reservation r JOIN t.address a ");
        List result=query_AllDbRecords.getResultList();
        
       
        
        
        
         int count = 0;  
            try {
            for (Iterator i = result.iterator(); i.hasNext();) {  
                
                Object[] values = (Object[]) i.next();                
               System.out.println(++count + ": " + values[0] + ", " + values[1] + values[2] + values[3] + "<br />");                
               AccountInfo jk= new AccountInfo(values[0].toString(),values[1].toString(),values[2].toString(),values[3].toString(), values[4].toString());
                
                k.add(jk);
            
            }    
             
        } catch (Exception e) {
                System.err.println("*** ACCBEAN");            
             System.out.printf(e.toString());
        }


        
       return k;
    }
    
    
    /**
     *
     */
    public void createNewEmptyRecord() {
         if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Accounts>();
            numberOfLastRecord++;
        }
        
        if(tabelIsEmpty==false){
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
        Accounts emptyacc = new Accounts();
        
       
        allrecordlist.add(emptyacc);
        this.currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
    }

    /**
     *
     */
    public void nextRecordBackward() {
         if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
    }

     private void RefreshAllRecords(){
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Accounts t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
   
      public Accounts getLastRecord(){
             return  allrecordlist.get(allrecordlist.size()-1);
        
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
          
      }
      if (newEmptyRecordCreated=false){
          saveOldRecord();
      }
      RefreshAllRecords();
    }

    
     private void saveOldRecord(){
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Accounts.class,allrecordlist.get(this.currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
    
    
     private void saveNewRecord(){
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().persist(allrecordlist.get(this.currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Accounts t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
     } 
     
     
     
     
      /**
     *
     */
    public void quitDBaccess(){
       dbhook.getEntity().close();
   } 
     
     
     public Accounts getDataRecord(long id){
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
    
    
     
     
    // ########################## Position handling ###############
     
     public  void adjustPosition(Long idofPosition,AccountPosition AdjustLikePosition ){
        accountspositionbean test=new accountspositionbean();
        revenuebean rev=new revenuebean();
        
         Collection<Revenue> btrw=new ArrayList<Revenue>(); 
        revaccountsbean jj=new revaccountsbean();
        
        
        
        // VAT CounterBooking
         Collection<Vat> vatList=new ArrayList<Vat>(); 
         vattypesbean vattyp=new vattypesbean();
        vatbean vat=new vatbean();
        
        double vatamount=0.0;
      double revWihoutVat=0.0;
        
        // ------------------ ACCPosition -------------------
      
         if (test.getDataRecord(idofPosition)!=null){
          
              if (AdjustLikePosition.getAccount()!=null) {
                   test.setAccount(AdjustLikePosition.getAccount());
              }
              
              if (AdjustLikePosition.getAmount()!=0) {
              test.setAmount(AdjustLikePosition.getAmount());
               }
              
               if (AdjustLikePosition.getPrice()!=0) {
              test.setPrice(AdjustLikePosition.getPrice());
               }
              
            
              test.setDebit(AdjustLikePosition.getDebit());
              
             if (AdjustLikePosition.getPositionname()!="") {
              test.setPositionname(AdjustLikePosition.getPositionname());
              }
              if (AdjustLikePosition.getRate()!=null) {
              test.setRate(AdjustLikePosition.getRate());
               }
              
              if (AdjustLikePosition.getRatedate()!=null) {
                  test.setRatedate(AdjustLikePosition.getRatedate());
              }
              
              vatamount=(test.getPrice()*test.getAmount())/(AdjustLikePosition.getRate().getVattype().getPercentage()+100);
              vatamount=vatamount*AdjustLikePosition.getRate().getVattype().getPercentage();
              revWihoutVat=(test.getPrice()*test.getAmount())-vatamount;
              
              //searches in Rev collection of accpos 
              for(Iterator<Revenue> i=test.getRevenueCollection().iterator();i.hasNext();){
                  
                  if(rev.selectRevPos(i.next().getId())==true){
                      
                  
                   if (AdjustLikePosition.getAmount()!=0) {
                      
                      rev.setAmount(revWihoutVat);
                  }
                   
                   try {
                    
                      rev.setDebit(AdjustLikePosition.getDebit());
                  } catch (Exception e) {
                  }
                  
                   if((AdjustLikePosition.getRate()!=null)){
                           if (AdjustLikePosition.getRate().getRevaccount()!=0){
                             
                              rev.setRevaccount(jj.SearchForRevAccount(AdjustLikePosition.getRate().getRevaccount()));
                           }
                   }
                   
                  } 
              }
              
              // Search for  VAT 
              for(Iterator<Vat> i=test.getVatCollection().iterator();i.hasNext();){
                  
                  if(vat.selectVatPos(i.next().getId())==true){
                      
                  
                   if (AdjustLikePosition.getAmount()!=0) {
                      
                      vat.setAmount(vatamount);
                  }
                   
                   try {
                    
                      vat.setDebit(AdjustLikePosition.getDebit());
                  } catch (Exception e) {
                  }
                  
                   if((AdjustLikePosition.getRate()!=null)){
                           if (AdjustLikePosition.getRate().getVattype().getId()!=0){
                              vat.setVattype(null);
                              vat.setVattype(vattyp.SearchForVatType(AdjustLikePosition.getRate().getVattype().getId()));
                           }
                   }
                   
                  } 
              }
              
              rev.saveRecord();
              test.saveRecord();
              vat.saveRecord();
             
      }
        
        
        
   }
     
    
    public void  addPosition(Rates r,int amount, double price,String positioname){
        log.debug("Function entry addposition " + String.valueOf(r.getId()) + "/ amount: " + String.valueOf(amount)  + "/ price: " +  String.valueOf(price) + "/Posname: " + String.valueOf(positioname) );
            
       Calendar cal  = Calendar.getInstance();
       
        accountspositionbean test = null;
        double vatamount = 0;
        double revWihoutVat = 0;
        vatbean vat = null;
        revenuebean rev = null;
        try {
// ACC Mainbooking
            test = new accountspositionbean();
            // REV CounterBooking
            rev = new revenuebean();
            // VAT CounterBooking
            vat = new vatbean();
            vatamount = 0.0;
            revWihoutVat = 0.0;
        } catch (Exception e) {
            log.debug("addposition");
            e.printStackTrace();
        }
      
      
      
      
     
      // in Revenues is only the total amount registered
     
      
      
      test.createNewEmptyRecord();
      test.setAccount(allrecordlist.get(this.currentRecordNumber));
      test.setAmount(amount);
      test.setPrice(price);
      test.setRatedate(cal.getTime());
      
      
      if (price==0){
            test.setPrice(r.getPrice());
            if(amount==0){
                       
                       test.setAmount(1);
               }
      }
      
      if(price!=0){
             if(amount==0){
                  
                  test.setAmount(1);
             }
      }
      
      test.setPositionname(positioname);
      if (positioname==""){
            test.setPositionname(r.getName());      
            
      }
      
      
      vatamount=(test.getPrice()*test.getAmount())/(r.getVattype().getPercentage()+100);
      vatamount=vatamount*r.getVattype().getPercentage();
      revWihoutVat=(test.getPrice()*test.getAmount())-vatamount;
      
       
      
      vat.createNewEmptyRecord();
      vat.setVattype(r.getVattype());
      vat.setAmount(vatamount);
      vat.setDate(cal.getTime());
        log.debug("LastPositionOf AccPos used as Key for VAT Table  " + String.valueOf(test.getLastPosition()));
      vat.setAccountposition(test.getLastPosition());
      
     
      
      test.setRate(r);
      test.saveRecord();
      
       rev.createNewEmptyRecord(); 
       rev.setAmount(revWihoutVat);
      rev.setRevaccount(r.getRevaccount());
      log.debug("LastPositionOf AccPos used as Key for REV Table  " + String.valueOf(test.getLastPosition()));
      rev.setAccountposition(test.getLastPosition());
      rev.saveRecord();
      
      vat.setAccountposition(test.getLastPosition());
      vat.saveRecord();
     
        log.debug("Function exit addPosition ");
    }
    
     public void  cancelPosition(Rates r,int amount, long canceledposition, double price,String positioname){
      Calendar cal  = Calendar.getInstance();
         accountspositionbean test=new accountspositionbean();
           revenuebean rev=new revenuebean();
      vatbean vat=new vatbean();
      
      double vatamount=0.0;
      double revWihoutVat=0.0;
      
      rev.createNewEmptyRecord(); 
      vat.createNewEmptyRecord();
      test.createNewEmptyRecord();
      
       if (price!=0){
           test.setPrice(price);
      
           if(amount!=0){
                    vatamount=(amount*price)/(r.getVattype().getPercentage()+100);
                    vatamount=vatamount*r.getVattype().getPercentage();
                    revWihoutVat=(amount*price)-vatamount;
                    rev.setAmount(revWihoutVat);
                    test.setAmount(amount);
                    vat.setAmount(vatamount);
            
           }
       }
      
      
       if (price==0){
           test.setPrice(r.getPrice());
      
           if(amount==0){
           vatamount=(1*r.getPrice())/(r.getVattype().getPercentage()+100);
           vatamount=vatamount*r.getVattype().getPercentage();
            revWihoutVat=(1*r.getPrice())-vatamount;
             rev.setAmount(revWihoutVat);
                    test.setAmount(1);
                    vat.setAmount(vatamount);
            
           }
       }
       
       if (price!=0){
            test.setPrice(price);
      
           if(amount==0){
           vatamount=(1*price)/(r.getVattype().getPercentage()+100);
           vatamount=vatamount*r.getVattype().getPercentage();
            revWihoutVat=(1*price)-vatamount;
            rev.setAmount(revWihoutVat);
                    test.setAmount(1);
                    vat.setAmount(vatamount);
            
           }
       }
       
       
      
      vat.setVattype(r.getVattype());
      vat.setDebit(true);
      vat.setDate(cal.getTime());
      vat.setAccountposition(test.getDataRecord(canceledposition));
      
     
      rev.setRevdate(cal.getTime());
      rev.setAccountposition(test.getDataRecord(canceledposition));
      
      rev.setRevaccount(r.getRevaccount());
      // in Revenues is only the total amount registered
    
      rev.setDebit(true);
      test.setCanceled(true);
      
      
      
      
      
      test.setAccount(allrecordlist.get(this.currentRecordNumber));
      test.setPrice(price);
      test.setDebit(true);
      test.setCanceledposition(canceledposition);
      test.setRatedate(cal.getTime());
      
      
      
      test.setPositionname(positioname);
      if (positioname==""){
            test.setPositionname(r.getName());      
            
      }
      
      
      test.setRate(r);
      test.saveRecord();
      
      
      rev.setAccountposition(test.getLastPosition());
      rev.saveRecord();
      
      vat.setAccountposition(test.getLastPosition());
      vat.saveRecord();
     
      
    }
    
    
    
    @Deprecated
    // currently not used
    public void  adjustPosition(AccountPosition ToAdjustPosition,AccountPosition AdjustLikePosition ){
         accountspositionbean test=new accountspositionbean();
      revenuebean rev=new revenuebean();
         
       Collection<Revenue> btrw=new ArrayList<Revenue>(); 
        revaccountsbean jj=new revaccountsbean();
             
      
      
      if (test.searchForAccPosition(ToAdjustPosition)!=null){
          
              if (AdjustLikePosition.getAccount()!=null) {
                   test.setAccount(AdjustLikePosition.getAccount());
              }
              
              if (AdjustLikePosition.getAmount()!=0) {
              test.setAmount(AdjustLikePosition.getAmount());
               }
              
              if (AdjustLikePosition.getPrice()!=0) {
              test.setPrice(AdjustLikePosition.getPrice());
               }
              
              
               if (AdjustLikePosition.getPrice()!=0) {
              test.setPrice(AdjustLikePosition.getPrice());
               }
            
              test.setDebit(AdjustLikePosition.getDebit());
              
             if (AdjustLikePosition.getPositionname()!="") {
              test.setPositionname(AdjustLikePosition.getPositionname());
              }
              if (AdjustLikePosition.getRate()!=null) {
              test.setRate(AdjustLikePosition.getRate());
               }
              
              if (AdjustLikePosition.getRatedate()!=null) {
                  test.setRatedate(AdjustLikePosition.getRatedate());
              }
              
              //searches in Rev collection of accpos 
              for(Iterator<Revenue> i=test.getRevenueCollection().iterator();i.hasNext();){
                  
                  if(rev.selectRevPos(i.next().getId())==true){
                      
                  
                   if (AdjustLikePosition.getAmount()!=0) {
                      
                      rev.setAmount(AdjustLikePosition.getAmount()*AdjustLikePosition.getPrice());
                  }
                   
                   try {
                    
                      rev.setDebit(AdjustLikePosition.getDebit());
                  } catch (Exception e) {
                  }
                  
                   if((AdjustLikePosition.getRate()!=null)){
                           if (AdjustLikePosition.getRate().getRevaccount()!=0){
                             
                              rev.setRevaccount(jj.SearchForRevAccount(AdjustLikePosition.getRate().getRevaccount()));
                           }
                   }
                   
                  } 
              }
              rev.saveRecord();
              test.saveRecord();
              
             
      }
      
    
      
    }
    
    
  
       
   
    
    /**
     *
     * @param fromAccountNr
     * @param accountPosNr
     */
    public void copyPositionsIntoThisAccount(long fromAccountNr,long accountPosNr[]) {
           Calendar cal  = Calendar.getInstance();
         
           for(int k=0;k<allrecordlist.size()-1;){  //search for the Account
                  if(allrecordlist.get(k).getId()==fromAccountNr){
                      
                       AccountPosition kj[]=allrecordlist.get(k).getAccountPositionCollection().toArray(new AccountPosition[allrecordlist.get(k).getAccountPositionCollection().size()]);
                           for(int l=0;l<accountPosNr.length;){ // go through all pos to replicate
                                   for(int ul=0;ul<kj.length;){
                                       if(kj[ul].getId()==accountPosNr[l]){
                                             kj[ul].setAccount(allrecordlist.get(currentRecordNumber));
                                              copyPos(kj[ul]);
                                       }
                                       ul++;
                                   }
                               
                               
                               
                             l++;  
                           }
                                   
                          break; // If the account has been found     
                      
                        } 
                  k++;   
           }
        
        saveRecord();
        
    }
    
    private AccountPosition copyPos(AccountPosition getit){
        Calendar cal  = Calendar.getInstance();
          revenuebean rev=new revenuebean();

      accountspositionbean ulo= new accountspositionbean();
        ulo.createNewEmptyRecord();
           
           ulo.setAccount(getit.getAccount());
           ulo.setAmount(getit.getAmount());
           ulo.setBilled(getit.getBilled());
           ulo.setDebit(getit.getDebit());
           ulo.setPositionname(getit.getPositionname());
           ulo.setRate(getit.getRate());
           ulo.setRatedate(cal.getTime());
           ulo.setPrice(getit.getPrice());
                  
          
          // Create new REVEntree for this
          rev.createNewEmptyRecord();    
          rev.setRevaccount(getit.getRate().getRevaccount());
          rev.setAmount(ulo.getAmount()*getit.getPrice());
          rev.setAccountposition(ulo.getLastPosition());
          rev.setDebit(ulo.getDebit());
          rev.saveRecord();
          ulo.saveRecord();
          return ulo.getLastPosition();
    }
    
    
     private AccountPosition movePos(AccountPosition getit){
          revenuebean rev=new revenuebean();

      accountspositionbean ulo= new accountspositionbean();
        ulo.createNewEmptyRecord();
           
           ulo.setAccount(getit.getAccount());
           ulo.setAmount(getit.getAmount());
           ulo.setBilled(getit.getBilled());
           ulo.setDebit(getit.getDebit());
           ulo.setPositionname(getit.getPositionname());
           ulo.setRate(getit.getRate());
           ulo.setRatedate(getit.getRatedate());
           ulo.setPrice(getit.getPrice());
        
          
          // Create new REVEntree for this
          rev.createNewEmptyRecord();    
          rev.setRevaccount(getit.getRate().getRevaccount());
          rev.setAmount(ulo.getAmount()* getit.getPrice());
          rev.setAccountposition(ulo.getLastPosition());
          if(ulo.getDebit()==true)
                  rev.setDebit(false);               
          else
                 rev.setDebit(true);
          
          
          rev.saveRecord();
          // Debit or Credit Position
          
          
          
          
          
          
          ulo.saveRecord();
          AccountPosition cambio=ulo.searchForAccPosition(getit);
          if(ulo.getDebit()==true)
                  ulo.setDebit(false);               
          else
              ulo.setDebit(true);
          ulo.saveRecord();
          
          return ulo.getLastPosition();
    }
    
    
    /**
     *
     * @param numberOfPos
     */
    public void replicatePosition(long numberOfPos[]){
        
        
        AccountPosition uiu[]= allrecordlist.get(currentRecordNumber).getAccountPositionCollection().toArray(new AccountPosition[allrecordlist.get(currentRecordNumber).getAccountPositionCollection().size()]);
        for (int i = 0; i < numberOfPos.length; ) {
            long l = numberOfPos[i];
                for(int o=0;o<uiu.length; ){
                        if(numberOfPos[i]==uiu[o].getId()){
                            //allrecordlist.get(currentRecordNumber).getAccountPositionCollection().add(
                            copyPos(uiu[o]);
                                                   
                        }  
                    
                    o++;
                }
            i++;
        }
        
        //saveRecord();
    }
    
    /**
     *
     * @param fromAccountNr
     * @param positionsToMove
     */
    public void movePositionsIntoThisAccount(long fromAccountNr, long positionsToMove[] ){
        for(int k=0;k<allrecordlist.size()-1;){  //search for the Account
                  if(allrecordlist.get(k).getId()==fromAccountNr){
                      
                       AccountPosition kj[]=allrecordlist.get(k).getAccountPositionCollection().toArray(new AccountPosition[allrecordlist.get(k).getAccountPositionCollection().size()]);
                           
                           for(int l=0;l<positionsToMove.length;){ // go through all pos to replicate
                                   for(int ul=0;ul<kj.length;){
                                       if(kj[ul].getId()==positionsToMove[l]){
                                           
                                             movePos(kj[ul]);   // Copy Pos, and inver debit property of source                                        
                                             
                                       }
                                       ul++;
                                   }
                               
                               
                               
                             l++;  
                           }
                                   
                          break; // If the account has been found     
                      
                        } 
                  k++;   
           }
        
        saveRecord();
           
        
        
    }
    
    //###################### RES Search ##########
    
    public void moveToRecordwithRes(Res res){
          int index=-1; 
        
          for(index=0;index<=allrecordlist.size()-1;index++){
               if(allrecordlist.get(index).getReservation()==res){
               currentRecordNumber=index;
           }
            
        
    }
    }
    
    
    
    
    // ###################### misc ##############
    
    /**
     *
     * @return
     */
    public double calcBalance(){
        Collection pos; 
       
   
        //hl[]=new Object[allrecordlist.get(currentRecordNumber).getAccountPositionCollection().size()];
        AccountPosition hl[]=allrecordlist.get(currentRecordNumber).getAccountPositionCollection().toArray(new AccountPosition[allrecordlist.get(currentRecordNumber).getAccountPositionCollection().size()]);
        
        
        double balance=0;
        for(int i=0;i<hl.length; ){
            if(hl[i].getDebit()==false){    
                       balance+= hl[i].getAmount();
                       
                         
            }
            if(hl[i].getDebit()==true){    
                       balance-= hl[i].getAmount();
                       
                         
            }
            i++;
        }
        
        
        return balance;
    }
    
  
    /**
     *
     */
    public void moveToFirstRecord(){
      currentRecordNumber=0;
      
  }
  
 
   
    
  
  
    // ############### Billing   #################
    
    
    void createBill(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    void copyPositonToBill(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    void markPositionAsBilled(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //################ Reporting ###############
    
    
    void getPrintedAccountReport(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    void getReportDataAccount(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //######################## CSC Handling #######################
    
   
    
    
    
    // #########################  Getters and Setter   ########################
    /**
     *
     * @return
     */
    public Collection<AccountPosition> getAccountPositionCollection() {
        
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAccountPositionCollection();
        return null;
        
    }
    
    public Accounts getAccount(){
        return allrecordlist.get(currentRecordNumber);
    }
    

    /**
     *
     * @return
     */
    public double getBalance() {
         double h=0;   
         AccountPosition acP=new AccountPosition();
        if( tabelIsEmpty!=true){
             
                      for(Iterator<AccountPosition> acci=allrecordlist.get(currentRecordNumber).getAccountPositionCollection().iterator();acci.hasNext();){
                              acP=acci.next();
                              if(acP.getDebit()==false){
                                  h=h-acP.getAmount()*acP.getPrice();
                              }
                              if(acP.getDebit()==true){
                                  h=h+acP.getAmount()*acP.getPrice();
                              }
                      }
                    allrecordlist.get(currentRecordNumber).setBalance(h);
             
            
              return allrecordlist.get(currentRecordNumber).getBalance();
        }
        return 0;
    }

    
    public double getSumofCreditsPos(){
         double h=0;   
         AccountPosition acP=new AccountPosition();
        if( tabelIsEmpty!=true){
             
                      for(Iterator<AccountPosition> acci=allrecordlist.get(currentRecordNumber).getAccountPositionCollection().iterator();acci.hasNext();){
                              acP=acci.next();
                              if(acP.getDebit()==false){
                                  h=h+acP.getAmount()*acP.getPrice();
                              }
                             
                      
                    
              }
            
              return h;
        }
        return 0;
        
        
    }

    public double getSumofDebitsPos(){
         double h=0;   
         AccountPosition acP=new AccountPosition();
        if( tabelIsEmpty!=true){
              
                      for(Iterator<AccountPosition> acci=allrecordlist.get(currentRecordNumber).getAccountPositionCollection().iterator();acci.hasNext();){
                              acP=acci.next();
                              if(acP.getDebit()==true){
                                  h=h+acP.getPrice()*acP.getAmount();
                              }
                             
                     
                    
              }
            
              return h;
        }
        return 0;
        
        
    }
    
    /**
     *
     * @return
     */
    public Serializable getCheckin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public String getCheckindate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCheckindate();
        return null;
    }

    /**
     *
     * @return
     */
    public boolean getCheckout() {
        if( tabelIsEmpty!=true) {
              return allrecordlist.get(currentRecordNumber).getCheckout();
        }
        return false;
    }

    /**
     *
     * @return
     */
    public String getCheckoutdate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCheckoutdate();
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
    public Res getReservation() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getReservation();
        return null;
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
     * @param balance
     */
    public void setBalance(double balance) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBalance(balance);
    }

    /**
     *
     * @param bill
     */
    public void setBill(Bill bill) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param checkin
     */
    public void setCheckin(Serializable checkin) {
        
    }

    /**
     *
     * @param checkindate
     */
    public void setCheckindate(String checkindate) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCheckindate(checkindate);
    }

   
    /**
     *
     * @param checkoutdate
     */
    public void setCheckoutdate(String checkoutdate) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCheckindate(checkoutdate);
    }

    /**
     *
     * @param reservation
     */
    public void setReservation(Res reservation) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setReservation(reservation);
    }

    /**
     *
     * @param checkout
     */
    public void setCheckout(boolean checkout) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCheckout(checkout);
    }

  
   

    public Address getAddress() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAddress();
        return null;
    }

    public void setAddress(Address address) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAddress(address);
    
    }

    public Collection<Csc> getCscCollection() {
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCscCollection();
        return null;
    }

    public void setCscCollection(Collection<Csc> cscCollection) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCscCollection(cscCollection);
    }

    

   
    
}
