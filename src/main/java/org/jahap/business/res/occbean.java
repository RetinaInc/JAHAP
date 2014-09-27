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



package org.jahap.business.res;


import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.jahap.business.res.DatabaseOperations;
import org.jahap.entities.Accounts;
import org.jahap.entities.Address;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Occ;
import org.jahap.entities.Res;
import org.jahap.entities.Rooms;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author russ
 */
public class occbean extends  DatabaseOperations implements occ_i{

    JahapDatabaseConnector dbhook;
    private static List<Occ> allrecordlist;
    private boolean createt=newEmptyRecordCreated;
    static Logger log = Logger.getLogger(occbean.class.getName());
    /**
     *
     */
    public occbean(){
        log.debug("Function entry Contructor");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Ooc t ORDER BY t.id");
            List<Occ>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occ t ORDER BY t.id");
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
        log.debug("Function exit Contructor");
    }
    
    private void RefreshAllRecords(){
        log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occ t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        log.debug("Function exit RefreshAllRecords");
    }
    
    
    //----------------------- Search Function  ------------
        /**
     *
     * @param searchstring
     * @return
     */
    public List<Occ>SearchForOcc(String searchstring){
        log.debug("Function entry SearchForOcc");
                 
        return allrecordlist;
    }
    
    public List<Occ>SearchForOcc(Date fromDate, Date toDate){
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
        log.debug("Function entry" + fromDate.toString() + "   // " + toDate.toString());
       List<Occ> date;
        query_AllDbRecords=dbhook.getEntity().createQuery("select t from Occ t where t.arrivaldate>='" + dt.format(fromDate) + "' AND t.departuredate<='"+ dt.format(toDate) + "' ORDER BY t.id");
        date=query_AllDbRecords.getResultList();
        date.forEach(i -> System.out.println(i.getArrivaldate().toString()+ " // " + i.getDeparturedate().toString() ));
                
                
        return date;
    }
    
     public List<Occ>SearchForOccforRes(Res res){
         log.debug("Function entry SearchForOccforRes");
        List<Occ>hl=new ArrayList<Occ>();
    int ind=-1;
      if(allrecordlist.size()>0) {
          do{
              ind++;
              if(allrecordlist.get(ind).getRes().getId()==res.getId()){
                    hl.add(allrecordlist.get(ind));
              }
              
          }while(allrecordlist.size()!=ind+1);
      }
           
        
        
      
         log.debug("Function exit SearchForOccforRes");
        return hl;
    }
    
        //-----------------------------------------------
    
    /**
     *
     */
    public void createNewEmptyRecord() {
        
        log.debug("Function entry createNewEmptyRecord");
        if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Occ>();
            //numberOfLastRecord++;
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
        Occ emptyocc = new Occ();
        
       
        allrecordlist.add(emptyocc);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
        log.debug("Function exit createNewEmptyRecord");
        
    }
    
   // ####################### Validation ######################     
        private boolean validateOverlappAtEnd(int depdate){
            return true;
        }
    
        private boolean validateOverlappAtBegin(int depdate){
            return true;
        }
        
        private boolean validateOverlappInMid(int depdate){
            return true;
        }
        
        private  List<Occ>validateRoom(List<Occ>vrooms){
            log.debug("Function entry validateRoom");
            
            int cont=0;
            
            do{
              // Remove every record which is after resbooking
              if(vrooms.size()!=0){  
                        if(allrecordlist.get(currentRecordNumber).getArrivaldate().before(vrooms.get(cont).getArrivaldate())){
                                  if(allrecordlist.get(currentRecordNumber).getDeparturedate().before(vrooms.get(cont).getArrivaldate())){
                                         vrooms.remove(cont);
                                         cont=0;
                                  }
                             }
              }
            
            if(vrooms.size()!=0){    
              
             if(allrecordlist.get(currentRecordNumber).getArrivaldate().after(vrooms.get(cont).getDeparturedate())){
                         if(allrecordlist.get(currentRecordNumber).getDeparturedate().after(vrooms.get(cont).getDeparturedate())){
                                vrooms.remove(cont);
                                cont=0;
                         }
                    }   
            }
            if(vrooms.size()!=0){   
             if(allrecordlist.get(currentRecordNumber).getArrivaldate().equals(vrooms.get(cont).getDeparturedate())){
                            if(allrecordlist.get(currentRecordNumber).getArrivaltime().after(vrooms.get(cont).getDeparturetime())){
                                 vrooms.remove(cont);
                                cont=0; 
                            }
                 }
            }
            if(vrooms.size()!=0){   
             if(allrecordlist.get(currentRecordNumber).getDeparturedate().equals(vrooms.get(cont).getArrivaldate())){
                            if(allrecordlist.get(currentRecordNumber).getDeparturetime().after(vrooms.get(cont).getArrivaltime())){
                                 vrooms.remove(cont);
                                cont=0; 
                            }
                 }
             
            }
             
               cont++;
              
               
              }while(cont<vrooms.size());
             log.debug("Function exit validateRoom");
            return vrooms;
              
        }
            
       
        /**
     *
     * @return
     */
    public List<String>CheckForOverlappingReservations(){
           log.debug("Function entry checkForOverlappingReservations");
             List<Occ>vrooms=new ArrayList<Occ>();
            List<String>overlapping=new ArrayList<String>();
            int count=0;
            
            if(!tabelIsEmpty && allrecordlist.size()>2){ // at least 2 occ record should be in the db in order to check for overlapings
                    do{
                        if(allrecordlist.get(count).getId()!=null){
                                if(allrecordlist.get(count).getRoom().getCode()==allrecordlist.get(currentRecordNumber).getRoom().getCode()){
                                      vrooms.add(allrecordlist.get(count));
                                }
                        }
                        count++;
                    }while(count<allrecordlist.size());
                 if(!vrooms.isEmpty())
                    validateRoom(vrooms);
                 
            }
               
            
               
            
             if(!vrooms.isEmpty() ){
                 
               if((vrooms.size()==1) ||(tabelIsEmpty) ){
                  return null;   
                }
                 
             int col=0;
             do{
                
                overlapping.add(vrooms.get(col).getRes().getResno());
                col++;  
             }while(col<vrooms.size()-1);
             RefreshAllRecords();
            return overlapping;
        }
             log.debug("Function exit checkForOverlappingReservations");
             return null;
            
        }
        
 //  ###########################   Rec Ops ##############       
        
        private List<String>saveNewRecord(){
            log.debug("Function entry saveNewRecord");
            List<Occ>vrooms=new ArrayList<Occ>();
            List<String>overlapping=new ArrayList<String>();
            int count=0;
            if(!tabelIsEmpty){
                    do{
                       if(allrecordlist.get(count).getId()!=null){ 
                                if(allrecordlist.get(count).getRoom().getCode()==allrecordlist.get(currentRecordNumber).getRoom().getCode()){
                                      vrooms.add(allrecordlist.get(count));
                                }
                       }
                        count++;
                    }while(count<allrecordlist.size());

                     if(!vrooms.isEmpty())
                    validateRoom(vrooms);
            }
            
        if((vrooms.size()==0 || vrooms.size()==1) ||(tabelIsEmpty) ){
           
               //------------------------------

                if ( newEmptyRecordCreated==true){
                    try{
                    dbhook.getEntity().getTransaction().begin();
                    dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
                    dbhook.getEntity().getTransaction().commit();
                    
                    newEmptyRecordCreated=false;
                    tabelIsEmpty=false;
                    }
                    catch (Exception e){
                          newEmptyRecordCreated=false;
                          allrecordlist.remove(currentRecordNumber);
                          RefreshAllRecords(); // Reloads List with all Records

                    }
                }
                 return null;
           }
        
        if(vrooms.size()!=0){
             int col=0;
             do{
                overlapping.add(vrooms.get(col).getRes().getResno());
                col++;
             }while(col<vrooms.size());
              RefreshAllRecords();
            return overlapping;
        }
           RefreshAllRecords();
            log.debug("Function exit saveNewRecord");
        return overlapping;
        }
    
      private void saveOldRecord(){
          log.debug("Function entry saveOldRecord ");
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Occ.class,allrecordlist.get(currentRecordNumber).getId() );
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
          log.debug("Function exit saveOldRecord");
        
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
        
        log.debug("Function exit nextRecordForeward");
    }
    /**
     *
     */
    
    /**
     *
     * @param test
     * @return
     */
   @Deprecated
   @Override 
   public void saveRecord(){}
    
    public List<String>saveRecord(boolean test){
       
        log.debug("Function entry saveRecord");
         if (newEmptyRecordCreated==false){
          saveOldRecord();    // Validation for old Record is needed
      }
        
         List<String>hh=new ArrayList<String>();
        System.out.println(" ->:" + String.valueOf(this.newEmptyRecordCreated));
        
         if (newEmptyRecordCreated==true){
            if(!tabelIsEmpty){    // Validate Date only if there are Records
                  hh=saveNewRecord();
                  
                 try {
                    if(hh!=null){
                         if (!hh.isEmpty()) {
                            if (hh.size() == 1) {                            

                                setNewEmptyRecordSaved();
                            }
                            if (hh.size() > 2) {                            
                                RefreshAllRecords(); // Reload Data from Database
                                return hh;

                            }
                        }
                        if (hh.isEmpty()) {
                            setNewEmptyRecordSaved();
                            return null;
                        }
                    }
                } catch (Exception e) {
                       e.printStackTrace();  
                }
                  }
                  
                  
                  
            }
            
            if(tabelIsEmpty){    // Just create a Record if Table is empty
                 saveNewRecord();
                        setNewEmptyRecordSaved();
            }
          
     
        log.debug("Function exit saveRecord");
      return null;
    }
    
      /**
     *
     * @return
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
        int inl=-1;
        
           try {
               do {
                   
                   currentRecordNumber = inl;
                   
                   inl++;
               } while (allrecordlist.get(currentRecordNumber).getId() != id && allrecordlist.size() - 1 > inl);
               currentRecordNumber = inl;
           } catch (Exception e) {
               e.printStackTrace();   
           }
   }
       
    /**
     *
     * @param id
     * @return
     */
    public Occ getDataRecord(long id){
       int inl=-1;
        
        try {
           do {
               
               
               
               inl++;
           } while (allrecordlist.get(currentRecordNumber).getId() != id && allrecordlist.size() - 1 > inl);
           currentRecordNumber = inl;
       } catch (Exception e) {
           e.printStackTrace();   
       }
      return  allrecordlist.get(currentRecordNumber);
   }    
       
   // ------------------------------------- Getters --------------------- 


    /**
     *
     * @return
     */
    public Date getArrivaltime() {
         // if( tabelIsEmpty!=true) 
              // return allrecordlist.get(currentRecordNumber).getArrivaltime();
        return null;
    }

 

    /**
     *
     * @return
     */
    public Date getDeparturetime() {
        //if( tabelIsEmpty!=true) 
              //return allrecordlist.get(currentRecordNumber).getDeparturetime();
        return null;
    }

   

    /**
     *
     * @return
     */
    public Res getRes() {
        Res test=null;
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRes();
        return test;
    }

    /**
     *
     * @return
     */
   

    /**
     *
     * @return
     */
    public Rooms getRoom() {
         Rooms test=null; 
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRoom();
        return test;
    }

    
     /**
     *
     * @return
     */
    public Date getArrivaldate() {
      if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getArrivaldate();
       return null;
    }

    /**
     *
     * @return
     */
    public Date getDeparturedate() {
        if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getDeparturedate();
       return null;
    }
    


    
   

 
   
  

    /**
     *
     * @param res
     */
    public void setRes(Res res) {
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
            allrecordlist.get(currentRecordNumber).setRes(res);
           }
        }  
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setRes(res);
        } 
    }

    

    /**
     *
     * @param room
     */
    public void setRoom(Rooms room) {
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setRoom(room);
            }
        }
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setRoom(room);
    }
    }
   
    /**
     *
     * @param arrivaldate type date
     */

    @Override 
    public void setArrivaldate(Date arrivaldate){
         if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                    createNewEmptyRecord();
                    allrecordlist.get(currentRecordNumber).setArrivaldate(arrivaldate);
                     setArrivaltime("12:01");
            }
         }
         if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setArrivaldate(arrivaldate);
            setArrivaltime("12:01");
         }  
    }
    /**
     *
     * @param arrivaldate dd.MM.yyyy
     */
    public void setArrivaldate(String arrivaldate) {
            Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
        try {
            dt=df.parse(arrivaldate);
        } catch (ParseException ex) {
            log.error(ex);
        }
           if(tabelIsInit==false || tabelIsEmpty==true){
                    if(newEmptyRecordCreated!=true){
                        createNewEmptyRecord();
                        allrecordlist.get(currentRecordNumber).setArrivaldate(dt);
                        setArrivaltime("12:01");
                    }
           }
             if(tabelIsInit==true || tabelIsEmpty==false){
                    allrecordlist.get(currentRecordNumber).setArrivaldate(dt);
                        setArrivaltime("12:01");
             }       
                    
    }

    /**
     *
     * @param departuredate
     */
    
    @Override public void setDeparturedate(Date departuredate){
          if (tabelIsInit==false || tabelIsEmpty==true){
                if(newEmptyRecordCreated!=true){
                    createNewEmptyRecord();
                    allrecordlist.get(currentRecordNumber).setDeparturedate(departuredate);
            setDeparturetime("12:00");
                }
          }
            if(tabelIsInit==true || tabelIsEmpty==false){
                 allrecordlist.get(currentRecordNumber).setDeparturedate(departuredate);
            setDeparturetime("12:00");
            }
    
    }
    /**
     *
     * @param departuredate dd.MM.yyyy
     */
    public void setDeparturedate(String departuredate) {
         Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
        try {
            dt=df.parse(departuredate);
        } catch (ParseException ex) {
            log.error(ex);
        }
        
        
         if (tabelIsInit==false || tabelIsEmpty==true){
                if(newEmptyRecordCreated!=true){
                    createNewEmptyRecord();
                    allrecordlist.get(currentRecordNumber).setDeparturedate(dt);
                    setDeparturetime("12:00");
                }
            }
         if(tabelIsInit==true || tabelIsEmpty==false){
             allrecordlist.get(currentRecordNumber).setDeparturedate(dt);
                    setDeparturetime("12:00");
         }
         
    }

    /**
     *
     * @param arrivaltime
     */
    @Override public void setArrivaltime(Date arrivaltime) {}
    /**
     *
     * @param arrivaltime
     */
    public void setArrivaltime(String arrivaltime){
        Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "HH:mm" );
        try {
            dt=df.parse(arrivaltime);
        } catch (ParseException ex) {
            log.error(ex);
        }
        
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setArrivaltime(dt);
            }
           
        }
        
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setArrivaltime(dt);
        }
    }

    /**
     *
     * @param departuretime
     */
    @Override public void setDeparturetime(Date departuretime) {}
    /**
     *
     * @param departuretime
     */
    public void setDeparturetime(String departuretime) {
        Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "HH:mm" );
        try {
            dt=df.parse(departuretime);
        } catch (ParseException ex) {
            log.error(ex);
        }
        
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                    createNewEmptyRecord();
                    allrecordlist.get(currentRecordNumber).setDeparturetime(dt);
            }
        }
         if(tabelIsInit==true || tabelIsEmpty==false){
              allrecordlist.get(currentRecordNumber).setDeparturetime(dt);
         }
    }

    public Address getGuest() {
        Address test=null;
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getGuest();
        return test;
    }

    public void setGuest(Address guest) {
         if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setGuest(guest);
            }
         }
           if(tabelIsInit==true || tabelIsEmpty==false){
                allrecordlist.get(currentRecordNumber).setGuest(guest);
           } 
            
    }

    public Accounts getAccount() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAccount();
        return null;
    }

    public void setAccount(Accounts account) {
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setAccount(account);
            }
         }
           if(tabelIsInit==true || tabelIsEmpty==false){
                allrecordlist.get(currentRecordNumber).setAccount(account);
           } 
    }
    
}
