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
import org.jahap.business.base.vattypesbean;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Vat;
import org.jahap.entities.Vattype;

/**
 *
 * @author russ
 */
public class vatbean extends DatabaseOperations  implements vat_i{

  

      JahapDatabaseConnector dbhook;
    private static List<Vat> allrecordlist; 
    
    
    public vatbean() {
       long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Vat t ORDER BY t.id");
            List<Vat>allroomslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allroomslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Vat t ORDER BY t.id");
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
    
    public List<Vat>getCurrentVat(){
        List<Vat>hh=new ArrayList<Vat>();
        hh.add(allrecordlist.get(currentRecordNumber));
        return hh;
    
    }
        
     private void saveNewRecord(){
         
          vattypesbean vattype=new vattypesbean(); 
         if (vattype.SearchForVatType(allrecordlist.get(currentRecordNumber).getVattype().getId())!=null){
                     allrecordlist.get(currentRecordNumber).setVattype(vattype.SearchForVatType(allrecordlist.get(currentRecordNumber).getVattype().getId()));            
        }
         
        if ( newEmptyRecordCreated==true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
           
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
             allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Vat t GROUP BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
        }
    
    public void createNewEmptyRecord() {
         if(numberOfLastRecord==-1){
            allrecordlist = new ArrayList();
            numberOfLastRecord++;
        }
        
         if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         }
        Vat emptyroom = new Vat();
        
       
        allrecordlist.add(emptyroom);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected    
        
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

    public void saveRecord() {
        if (newEmptyRecordCreated==true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          
      }
      if (newEmptyRecordCreated==false){
          saveOldRecord();
      }
    }
    
    
     private void saveOldRecord(){
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Vattype.class,allrecordlist.get(currentRecordNumber).getId() );
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
    
     public void  setDataRecordId(Long id){
        int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber=inl;
        } catch (Exception e) {
            e.printStackTrace();  
            
        }
       
   }
     
      public boolean selectVatPos(long id){
        int i=-1;
        do {
            i++; 
            try {
                if (allrecordlist.get(i).getId() == id) {
                    currentRecordNumber = i;                    
                    return true;
                }                
            } catch (Exception e) {
            }
            
          }while(allrecordlist.get(i).getId()!=id);
        return false;
    }
     
     
    public List<Vat>SearchForRate(String searchstring){
    
        return allrecordlist;
    }   
     
     
       public Vat  getDataRecord(Long id){
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
    
    
    public void quitDBaccess() {
           dbhook.getEntity().close();
    }

  // ---------------------- Getters & Setters   ------------------
    
    public AccountPosition getAccountposition() {
          if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getAccountposition();
        }
        return null;
    }

    public double getAmount() {
           if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getAmount();
        }
        return 0.0;
    }

    public Date getDate() {
           if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getDate();
        }
        return null;
    }

    public Boolean getDebit() {
           if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getDebit();
        }
        return null;
    }

    public Long getId() {
          if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getId();
        }
        return null;
    }

    public Vattype getVattype() {
          if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getVattype();
        }
        return null;
    }

    public void setAccountposition(AccountPosition accountposition) {
         if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setAccountposition(accountposition);
    }

    public void setAmount(double amount) {
         if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setAmount(amount);
    }

    public void setDate(Date date) {
         if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setDate(date);
    }

    public void setDebit(Boolean debit) {
         if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setDebit(debit);
    }

    

    public void setVattype(Vattype vattype) {
          if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setVattype(vattype);
    }

    
    
}
