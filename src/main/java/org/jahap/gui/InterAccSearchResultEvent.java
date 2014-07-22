/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.util.EventObject;


/**
 *
 * @author russ
 */
public class InterAccSearchResultEvent extends EventObject{
    
    private long DbRecordId;

    private Object EvObj;
    private String TableNameofSource;
    
    
     public long getDbRecordId() {
        return DbRecordId;
    }

    public Object getTableNameofSource() {
        return TableNameofSource;
    }
    
    
    public void setEventObject(Object EvObj){
          this.EvObj=EvObj;
    }
    
    public Object getEventObject(){
        return this.EvObj;
    }
    
    public InterAccSearchResultEvent(Object source, long DataRecordId, String TableNameofSource, Object EvObj ){
        super(source);
        this.DbRecordId=DataRecordId;
        this.TableNameofSource=TableNameofSource;
        this.EvObj=EvObj;
    }
    
     
        
    
    
}
