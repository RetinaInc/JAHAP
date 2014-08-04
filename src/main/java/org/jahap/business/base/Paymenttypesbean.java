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
package org.jahap.business.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Payed;
import org.jahap.entities.Paymenttypes;


/**
 *
 * @author russ
 */
public class Paymenttypesbean extends DatabaseOperations  implements Paymenttypes_i {

    JahapDatabaseConnector dbhook;
    private static List<Paymenttypes> allrecordlist; 

    public Paymenttypesbean() {
               long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Paymenttypes t ORDER BY t.id");
            List<Paymenttypes>allroomslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allroomslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rates t ORDER BY t.id");
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
    
    public List<Paymenttypes>getCurrentRate(){
        List<Paymenttypes>hh=new ArrayList<Paymenttypes>();
        hh.add(allrecordlist.get(currentRecordNumber));
        return hh;
    
    }
    
     public void createNewEmptyRecord() {
        if(numberOfLastRecord==-1){
            allrecordlist = new ArrayList();
            numberOfLastRecord++;
        }
        
         if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         }
        Paymenttypes emptyroom = new Paymenttypes();
        
       
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
            dbhook.getEntity().find(Paymenttypes.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
    
     private void saveNewRecord(){
        if ( newEmptyRecordCreated==true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().persist(allrecordlist.get(currentRecordNumber));
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
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
     
       
        public List<Paymenttypes>SearchForRate(String searchstring){
                
        return allrecordlist;
    }
        
        
       
     
     public Paymenttypes  getDataRecord(Long id){
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
    public Long getId() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getId();
        }
        return (long) 0;
    }

    public String getName() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getName();
        }
        return null;
    }

    public Collection<Payed> getPayedCollection() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getPayedCollection();
        }
        return null;
    }

    public Boolean getReceivable() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getReceivable();
        }
        return null;
    }

   

   

    public void setName(String name) {
         if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setName(name);
    }

    public void setPayedCollection(Collection<Payed> payedCollection) {
        if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setPayedCollection(payedCollection);
    }

    public void setReceivable(Boolean receivable) {
         if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setReceivable(receivable);
    }

   
    
}
