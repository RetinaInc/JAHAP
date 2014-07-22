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
public class InterResSearchResult {
    private long DbRecordId;
    private String TableNameofSource;

   
    public long getDbRecordId() {
        return DbRecordId;
    }
    

    public void setDbRecordId(long DataRecordId, String TableNameofSource) {
        //
        this.TableNameofSource=TableNameofSource;
        this.DbRecordId=DataRecordId;
        notifyId(new InterResSearchResultEvent(this,DataRecordId,TableNameofSource));
    }
    
    private EventListenerList listeners = new  EventListenerList();
    
    
    public void addIDListener( InterResSearchResultListener listener )
  {
    listeners.add( InterResSearchResultListener.class, listener );
  }

  public void removeIDListener( InterResSearchResultListener listener )
  {
    listeners.remove( InterResSearchResultListener.class, listener );
  }

  

    
    protected synchronized void notifyId(InterResSearchResultEvent event){
        for ( InterResSearchResultListener l : listeners.getListeners( InterResSearchResultListener.class ) )
      l.idinfo( event );

    }
}


