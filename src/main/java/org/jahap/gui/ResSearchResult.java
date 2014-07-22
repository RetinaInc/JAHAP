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
public class ResSearchResult {
    private long DbRecordId;
    private String TableNameofSource;

   
    public long getDbRecordId() {
        return DbRecordId;
    }
    

    public void setDbRecordId(long DataRecordId, String TableNameofSource) {
        //
        this.TableNameofSource=TableNameofSource;
        this.DbRecordId=DataRecordId;
        notifyId(new ResSearchResultEvent(this,DataRecordId,TableNameofSource));
    }
    
    private EventListenerList listeners = new  EventListenerList();
    
    
    public void addIDListener( ResSearchResultListener listener )
  {
    listeners.add( ResSearchResultListener.class, listener );
  }

  public void removeIDListener( ResSearchResultListener listener )
  {
    listeners.remove( ResSearchResultListener.class, listener );
  }

  

    
    protected synchronized void notifyId(ResSearchResultEvent event){
        for ( ResSearchResultListener l : listeners.getListeners( ResSearchResultListener.class ) )
      l.idinfo( event );

    }
}


