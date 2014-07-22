/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.util.EventObject;
import javax.swing.table.TableModel;

/**
 *
 * @author russ
 */
public class RateSearchResultEvent extends EventObject{
    
    private long DbRecordId;

   
    private String TableNameofSource;
    
    
     public long getDbRecordId() {
        return DbRecordId;
    }

    public Object getTableNameofSource() {
        return TableNameofSource;
    }
    
    
    public RateSearchResultEvent(Object source, long DataRecordId, String TableNameofSource ){
        super(source);
        this.DbRecordId=DataRecordId;
        this.TableNameofSource=TableNameofSource;
    }
    
    
    
    
}
