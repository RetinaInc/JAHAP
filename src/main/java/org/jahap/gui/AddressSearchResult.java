/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import javax.swing.event.EventListenerList;
import javax.swing.table.TableModel;

/**
 *
 * @author russ
 */
public class AddressSearchResult {
    private long DbRecordId;
    private String TableNameofSource;

   
    public long getDbRecordId() {
        return DbRecordId;
    }
    

    public void setDbRecordId(long DataRecordId, String TableNameofSource) {
        //
        this.TableNameofSource=TableNameofSource;
        this.DbRecordId=DataRecordId;
        notifyId(new AddressSearchResultEvent(this,DataRecordId,TableNameofSource));
    }
    
    private EventListenerList listeners = new  EventListenerList();
    
    
    public void addIDListener( AddressSearchResultListener listener )
  {
    listeners.add( AddressSearchResultListener.class, listener );
  }

  public void removeIDListener( AddressSearchResultListener listener )
  {
    listeners.remove( AddressSearchResultListener.class, listener );
  }

  

    
    protected synchronized void notifyId(AddressSearchResultEvent event){
        for ( AddressSearchResultListener l : listeners.getListeners( AddressSearchResultListener.class ) )
      l.idinfo( event );

    }
}


