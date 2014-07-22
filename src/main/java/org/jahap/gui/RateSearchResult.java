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
public class RateSearchResult {
    private long DbRecordId;
    private String TableNameofSource;

   
    public long getDbRecordId() {
        return DbRecordId;
    }
    

    public void setDbRecordId(long DataRecordId, String TableNameofSource) {
        //
        this.TableNameofSource=TableNameofSource;
        this.DbRecordId=DataRecordId;
        notifyId(new RateSearchResultEvent(this,DataRecordId,TableNameofSource));
    }
    
    private EventListenerList listeners = new  EventListenerList();
    
    
    public void addIDListener( RateSearchResultListener listener )
  {
    listeners.add( RateSearchResultListener.class, listener );
  }

  public void removeIDListener( RateSearchResultListener listener )
  {
    listeners.remove( RateSearchResultListener.class, listener );
  }

  

    
    protected synchronized void notifyId(RateSearchResultEvent event){
        for ( RateSearchResultListener l : listeners.getListeners( RateSearchResultListener.class ) )
      l.idinfo( event );

    }
}


