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


package org.jahap.gui;

import java.util.HashMap;
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
    private HashMap datamap;
    
    public long getDbRecordId() {
        return DbRecordId;
    }
    

    public void setDbRecordId(long DataRecordId, String TableNameofSource, HashMap datamap) {
        //
        this.TableNameofSource=TableNameofSource;
        this.DbRecordId=DataRecordId;
        this.datamap=datamap;
        notifyId(new InterAccSearchResultEvent(this,DataRecordId,TableNameofSource,datamap,EventObj));
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


