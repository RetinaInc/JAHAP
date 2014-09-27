package org.jahap.business.base;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jahap.business.base.DatabaseOperations;
import org.jahap.business.base.choice_i;
import org.jahap.entities.Choice;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Language;


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


/**
 *
 * @author russ
 */


public class Choicebean extends DatabaseOperations implements choice_i {

 JahapDatabaseConnector dbhook;
	



    private static List<Choice> allrecordlist;
     static Logger log = Logger.getLogger(Choicebean.class.getName());

    /**
     *
     */
    public Choicebean(){
       
         log.debug("Function entry countrybean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Choice t ORDER BY t.id");
            List<Choicebean>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Choice t ORDER BY t.id");
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
    
    public List<Choice>SearchForChoice(choicegroups group){
        
         log.debug("Function entry SearchForChoice");
      
         
        
        log.debug("Function exit SearchForChoice ");
        return allrecordlist;
    }  
    
    public List<String>SearchForChoiceString(choicegroups group){
        log.debug("Function entry SearchForChoice");
        
        
         query_AllDbRecords = dbhook.getEntity().createQuery("select t.choicetext from Choice t where t.groupname='"+ group.toString() + "' ORDER BY t.id");
          log.debug("Function exit SearchForChoice ");
           return query_AllDbRecords.getResultList();
        
        
         
         
       
        
    }  
   
    
       public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Choice>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               Choice emptyacc = new Choice();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
       
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Choice t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }
    
    
     public Choice getDataRecord(long id){
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
    
    public Choice getLastPosition(){
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Choice t ORDER BY t.id"); // Refresh list
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
            dbhook.getEntity().refresh(dbhook.getEntity().find(Choice.class,allrecordlist.get(currentRecordNumber).getId() ));
            
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    }

    @Override
    public String getChoicecode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getChoicefloat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getChoiceint() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getChoicetext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getGroupcode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getGroupid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getGroupname() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Language getLanguage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChoicecode(String choicecode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChoicefloat(BigDecimal choicefloat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChoiceint(Integer choiceint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChoicetext(String choicetext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGroupcode(String groupcode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGroupid(Integer groupid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGroupname(String groupname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLanguage(Language language) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    




}
