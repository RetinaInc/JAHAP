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
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Csc;

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Rates;


/**
 *
 * @author russ
 */
public class ratesbean  extends DatabaseOperations  implements rates_i{

     JahapDatabaseConnector dbhook;
    private static List<Rates> allrecordlist; 
    public ratesbean(){
       long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rates t ORDER BY t.id");
            List<Rates>allroomslist= query_AllDbRecords.getResultList();
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
    
    public List<Rates>getCurrentRate(){
        List<Rates>hh=new ArrayList<Rates>();
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
        Rates emptyroom = new Rates();
        
       
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
            dbhook.getEntity().find(Rates.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
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
     
   public List<Rates>SearchForRate(String searchstring){
    
        return allrecordlist;
    }  
     
/* Depricated - not used
     public Rates SearchForRatewithId(long id){
             
         for(Rates kj:allrecordlist){
                 if(kj.getId()==id){
                     return kj;
                 }
         }
         
         
         return null;
                 
     }
     
  */   
     
    public Rates  getDataRecord(Long id){
        int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
            System.out.println(currentRecordNumber);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return allrecordlist.get(currentRecordNumber);
   } 
     
     
     
    public void quitDBaccess() {
        dbhook.getEntity().close();
    }

    
    // ---------------------- Getters & Setters   ------------------
    
    
    public Collection<AccountPosition> getAccountPositionCollection() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getAccountPositionCollection();
        }
        return null;
    }

    public String getCode() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCode();
        return "";
    }

    public Long getId() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

    public String getName() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return null;
    }

    public double getPrice() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getPrice();
        return 0;
    }

    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        
    }

    public void setCode(String code) {
         if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setCode(code);
    }

  

    public void setName(String name) {
         if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setName(name);
    }

    public void setPrice(double price) {
         if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setPrice(price);
    }

    public void setId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getRevaccount() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevaccount();
        return 0;
    }

    

    public Collection<Csc> getCscCollection() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getCscCollection();
        }
        return null;   
    }

    public boolean getOvernight() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getOvernight();
        }
        return false;
    }

    public void setCscCollection(Collection<Csc> cscCollection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setOvernight(boolean overnight) {
        if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setOvernight(overnight);
    }

    public void setRevaccount(long revaccount) {
        if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setRevaccount(revaccount);
    }
    
}
