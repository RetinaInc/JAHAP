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
public class InterAccSearchResult {
    private long DbRecordId;
    private String TableNameofSource;
    private Object EventObj;
   
    public long getDbRecordId() {
        return DbRecordId;
    }
    

    public void setDbRecordId(long DataRecordId, String TableNameofSource) {
        //
        this.TableNameofSource=TableNameofSource;
        this.DbRecordId=DataRecordId;
        notifyId(new InterAccSearchResultEvent(this,DataRecordId,TableNameofSource,EventObj));
    }

    public Object getEventObj() {
        return EventObj;
    }

    public void setEventObj(Object EventObj) {
        this.EventObj = EventObj;
    }
    
    
    
    private EventListenerList listeners = new  EventListenerList();
    
    
    public void addIDListener( InterAccSearchResultListener listener )
  {
    listeners.add( InterAccSearchResultListener.class, listener );
  }

  public void removeIDListener( InterAccSearchResultListener listener )
  {
    listeners.remove( InterAccSearchResultListener.class, listener );
  }

  

    
    protected synchronized void notifyId(InterAccSearchResultEvent event){
        for ( InterAccSearchResultListener l : listeners.getListeners( InterAccSearchResultListener.class ) )
      l.idinfo( event );

    }
}


