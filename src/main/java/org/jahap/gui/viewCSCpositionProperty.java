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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
//import static org.jahap.entities.LogAccounting_.date;

/**
 *
 * @author russ
 */
public class viewCSCpositionProperty {

    
    
     private  boolean overnight=false;
     private Date from;
     private Date to;
     private int amount;
     private String service;
     private double price;
     private long rateId;
     
     
     private final SimpleStringProperty fromDate = new SimpleStringProperty();
     private final SimpleStringProperty toDate = new SimpleStringProperty();
     private final SimpleStringProperty amountCsC = new SimpleStringProperty();
     private final SimpleStringProperty serviceCsC = new SimpleStringProperty();
     private final SimpleStringProperty priceCsC = new SimpleStringProperty();
     private final SimpleStringProperty overnightCsC = new SimpleStringProperty();
     private final SimpleStringProperty rateidCsC = new SimpleStringProperty();
     private final SimpleStringProperty totalCsC = new SimpleStringProperty();
       private  DateFormat df ;
       
     public viewCSCpositionProperty() {
        this.df = new SimpleDateFormat("MM.dd.yyyy");;
    }  
     
    public boolean isOvernight() {
        return overnight;
    }

    public void setOvernight(boolean overnight) {
        this.overnight = overnight;
        Boolean ts=this.overnight;
        this.overnightCsC.set(ts.toString());
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
        if(from!=null){
            this.fromDate.set(df.format(from));
        }
        
    }

    public long getRateId() {
        return rateId;
    }

    public void setRateId(long rateId) {
        this.rateId = rateId;
        this.rateidCsC.set(String.valueOf(rateId));
    }

    
    
    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
       
        if(to!=null){
            this.toDate.set(df.format(to));
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.amountCsC.set(String.valueOf(amount));
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
        this.serviceCsC.set(service);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.priceCsC.set(String.valueOf(price));
    }
     
    
    public final  String getOvernightCsC(){return overnightCsC.get();}
    public final void setOvernightCsC(String value){overnightCsC.set(value);}
    public SimpleStringProperty overnightCsCProperty() {return overnightCsC;}

    public final  String getAmountCsC(){return amountCsC.get();}
    public final void setAmountCsC(String value){amountCsC.set(value);}
    public SimpleStringProperty amountCsCProperty() {return overnightCsC;}
    
    public final  String getFromDate(){return fromDate.get();}
    public final void setFromDate(String value){fromDate.set(value);}
    public SimpleStringProperty fromDateProperty() {return fromDate;}
    
    public final  String getToDate(){return toDate.get();}
    public final void setToDate(String value){toDate.set(value);}
    public SimpleStringProperty toDateProperty() {return toDate;}
    
    public final  String getPriceCsC(){return priceCsC.get();}
    public final void setPriceCsC(String value){priceCsC.set(value);}
    public SimpleStringProperty priceCsCProperty() {return priceCsC;}
    
    public final  String getServiceCsC(){return serviceCsC.get();}
    public final void setServiceCsC(String value){serviceCsC.set(value);}
    public SimpleStringProperty serviceCsCProperty() {return serviceCsC;}
    
     public final  String getRateidCsC(){return rateidCsC.get();}
    public final void setRateidCsC(String value){rateidCsC.set(value);}
    public SimpleStringProperty rateidCsCProperty() {return rateidCsC;}
    
    public final  String getTotalCsC(){
        totalCsC.set(String.valueOf(amount*price));        
        return rateidCsC.get();}
    public final void setTotalCsC(String value){rateidCsC.set(value);}
    public SimpleStringProperty totalCsCProperty() {return rateidCsC;}
    
}
