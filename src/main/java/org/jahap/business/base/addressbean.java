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
import org.jahap.entities.*;
        

/**
 *
 * @author russ
 */

public class addressbean extends DatabaseOperations implements address_i {

  

    JahapDatabaseConnector dbhook;
    private static List<Address> allrecordlist;
    // Has there been an Tabel buffer (List) created?



    
    /**
     * Constructor
     * establishes a connection to the Database an picks all adresses
     */
    public addressbean(){
        long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("SELECT a FROM Address a ORDER BY a.id");
            List<Address>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("SELECT a FROM Address a ORDER BY a.id ");
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
    //--------------------------------- Search Function ----------------------
    /**
     *
     * @param searchstring
     * @return List<Adress>
     * gets all Addresses of the Database
     */
    public List<Address>SearchForAddress(String searchstring){
    
        return allrecordlist;
    }
    
    public List<Address>getCurrentAddress(){
        List<Address>hh=new ArrayList<Address>();
        hh.add(allrecordlist.get(currentRecordNumber));
        return hh;
    
    }
    
    
    //---------------------------------
    
     /**
     * Creates an empty Record - has to be filled in oder get written in to the db
     */
    @Override
    public void createNewEmptyRecord(){
         
         if(numberOfLastRecord==-1){
            allrecordlist = new ArrayList();
            numberOfLastRecord++;
            
        }
        
         if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         }
        Address emptyaddress = new Address();
        
       
        allrecordlist.add(emptyaddress);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
       
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
      
    private void saveOldRecord(){
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Address.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
    }   
    
    /**
     * Saves the record which is currently worked on
     */
    @Override      
  public void saveRecord(){
      if (newEmptyRecordCreated==true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          
      }
      if (newEmptyRecordCreated==false){
          saveOldRecord();
      }
      
  }        

       
    /**
     * moves the selctor to the next address record
     */
    @Override
    public void nextRecordForeward(){
        if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
        
    }
    
    /**
     * moves the selctor one record back
     */
    @Override
    public void nextRecordBackward(){
        
         if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
    }
    
    
    /**
     * closes database connection
     */
    public void quitDBaccess(){
       dbhook.getEntity().close();
   }
    
   

    /**
     * gets the record id of the Dbrecord in focus 
     * @return long 
     */
    public Long getId(){
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return (long) 0;
   }
   
    /**
     * 
     * @param id
     */
    public void  setDataRecordId(Long id){
       int inl;
        
      
            
            for(inl=0; inl<=allrecordlist.size()-1;inl++)
           {
                
                if((long)allrecordlist.get(inl).getId() == id){
                    currentRecordNumber=inl;
                   
                }
                
              
            } 
            
        
       
   }
   
    /**
     *
     * @param id
     * @return address
     */
    public Address getDataRecord(long id){
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
     * @return
     */
    @Override
    public String getName() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return "";
    }
    
   

    /**
     *
     * @return
     */
    @Override
    public String getChristianname() {
       if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getChristianname();
       return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getStreet() {
       if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getStreet();
       return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getZipcode() {
      if( tabelIsEmpty!=true)   
       return allrecordlist.get(currentRecordNumber).getZipcode();
      return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getCity() {
     if( tabelIsEmpty!=true)      
       return allrecordlist.get(currentRecordNumber).getCity();
     return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getPhone() {
       if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getPhone();
       return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getEmail() {
       if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getEmail();
       return "";
    }

    /**
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setName(name);
    }

    /**
     *
     * @param christianname
     */
    @Override
    public void setChristianname(String christianname) {
        if (tabelIsInit==false || tabelIsEmpty!=true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setChristianname(christianname);
    }

    /**
     *
     * @param street
     */
    @Override
    public void setStreet(String street) {
        if (tabelIsInit==false || tabelIsEmpty!=true) 
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        allrecordlist.get(currentRecordNumber).setStreet(street);
    }

    /**
     *
     * @param zipcode
     */
    @Override
    public void setZipcode(String zipcode) {
        if (tabelIsInit==false || tabelIsEmpty!=true) 
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        allrecordlist.get(currentRecordNumber).setZipcode(zipcode);
    }
    

    /**
     *
     * @param city
     */
    @Override
    public void setCity(String city) {
        if (tabelIsInit==false || tabelIsEmpty!=true) 
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        allrecordlist.get(currentRecordNumber).setCity(city);
    }

    /**
     *
     * @param phone
     */
    @Override
    public void setPhone(String phone) {
        if (tabelIsInit==false || tabelIsEmpty!=true) 
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        allrecordlist.get(currentRecordNumber).setPhone(phone);
        
    }

    /**
     *
     * @param email
     */
    @Override
    public void setEmail(String email) {
        if (tabelIsInit==false || tabelIsEmpty!=true) 
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        allrecordlist.get(currentRecordNumber).setEmail(email);
        
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Collection<Bill> getBillCollection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<Res> getResCollection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setResCollection(Collection<Res> resCollection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}
