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
public class InterResSearchResultEvent extends EventObject{
    
    private long DbRecordId;

   
    private String TableNameofSource;
    
    
     public long getDbRecordId() {
        return DbRecordId;
    }

    public Object getTableNameofSource() {
        return TableNameofSource;
    }
    
    
    public InterResSearchResultEvent(Object source, long DataRecordId, String TableNameofSource ){
        super(source);
        this.DbRecordId=DataRecordId;
        this.TableNameofSource=TableNameofSource;
    }
    
    
    
    
}
