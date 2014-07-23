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
import java.util.Locale;
import javafx.beans.property.*;
import org.jahap.business.acc.billbean;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.AccountPosition;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author russ
 */
public  class viewAccountPositionsProperty {

   
    
     private boolean billed=false;
     private boolean debit=false;
     private long id;
     private final SimpleStringProperty idx= new SimpleStringProperty();
     private long rateId; 

     private Date ratedate;
     private final SimpleStringProperty rateDateString= new SimpleStringProperty() ;
     private boolean canceled=false;
     private long  canceledposition;
     private double cPrice;
     private double dPrice;
     
     private final SimpleStringProperty cpricestring = new SimpleStringProperty();
     private final SimpleStringProperty dpricestring = new SimpleStringProperty();
     private final SimpleStringProperty billnamestring = new SimpleStringProperty();
     private long cRateid;
     private long dRateid;
     private final SimpleStringProperty cpositionname=new SimpleStringProperty();
     private final SimpleStringProperty dpositionname= new SimpleStringProperty();
     private final SimpleStringProperty ctotal = new SimpleStringProperty();
     private final SimpleStringProperty dtotal = new SimpleStringProperty();
   
     private final SimpleStringProperty camountstring = new SimpleStringProperty();
     private final SimpleStringProperty damountstring = new SimpleStringProperty();
     private final SimpleStringProperty billnostring = new SimpleStringProperty();
     private boolean isTempBill; 
     
     private long billno;
     private int cAmount;
     private int dAmount;
    private  DateFormat df ;
     
      public viewAccountPositionsProperty() {
    df = new SimpleDateFormat("MM.dd.yyyy");
    }
    
    public boolean isBilled() {
        return billed;
    }

     public String getRateDateString() {
        return rateDateString.get();
    }

    public long getRateId() {
        return rateId;
    }

    public void setRateId(long rateId) {
        this.rateId = rateId;
    }
     
   
     
    public SimpleStringProperty getRateDateStringProperty(){
        return rateDateString;
    }

    public void setRateDateString(String rateDateString) {
        this.rateDateString.set(rateDateString);
        
    }

    public void setIdx(String idx){
        this.idx.set(idx);
    }
    
    public SimpleStringProperty getIdxProperty(){
        return idx;
    }
    
    public String getIdx(){
        return idx.get();
    }
    
    public void setBilled(boolean billed) {
        this.billed = billed;
    }

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    public long getId() {
       
        return id;
    }

    public void setId(long id) {
        idx.set(String.valueOf(id));
        this.id = id;
    }

    public Date getRatedate() {
        return ratedate;
     
    }

    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
        if(ratedate!=null){
        this.rateDateString.set(df.format(ratedate));
        }
    }

    public long getcRateid() {
        return cRateid;
    }

    public void setcRateid(long cRateid) {
        this.cRateid = cRateid;
        this.rateId=cRateid;
        
        
        
        
    }

    public String getCtotal() {
        if(this.cAmount!=0 && this.cPrice!=0){
           this.ctotal.set(String.valueOf(this.cAmount*this.cPrice));
        }          
        return ctotal.get();
    }

    public SimpleStringProperty ctotalProperty(){
        return ctotal;
    }
    
   

    public String getDtotal() {
        
        if(this.dAmount!=0 && this.dPrice!=0){
        this.dtotal.set(String.valueOf(this.dAmount*this.dPrice));
        }
        return dtotal.get();
    }

   public SimpleStringProperty dtotalProperty() {
        
        
        return dtotal;
    }
    

    public long getdRateid() {
        return dRateid;
    }

    public void setdRateid(long dRateid) {
        this.dRateid = dRateid;
        this.rateId=dRateid;
    }

    public String getCpositionname() {
        return cpositionname.get();
    }
    
    public SimpleStringProperty cpositionnameProperty() {
        return cpositionname;
    
    }
    
    public void setBillnamestring(String billname){
         this.billnamestring.set(billname);
    }

    public SimpleStringProperty getbillnamestringProperty(){
        return billnamestring;
    }
    
    public String getBillnamestring(){
        return billnamestring.get();
    }
    
    public void setCpositionname(String cPositionname) {
        this.cpositionname.set(cPositionname);
    }

    public String getDpositionname() {
        return dpositionname.get();
    }

    public SimpleStringProperty dpositionnameProperty() {
        return dpositionname;
    }
    
    public void setDpositionname(String dPositionname) {
        this.dpositionname.set(dPositionname);
    }

    public int getcAmount() {
        return cAmount;
        
    }

    public void setcAmount(int cAmount) {
        this.cAmount = cAmount;
        if(cAmount!=0){
            this.camountstring.set(String.valueOf(cAmount));
            this.ctotal.set(String.valueOf(cAmount*cPrice));
        }
    }

    public int getdAmount() {
        return dAmount;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public long getCanceledposition() {
        return canceledposition;
    }

    public void setCanceledposition(long canceledposition) {
        this.canceledposition = canceledposition;
    }

    public double getcPrice() {
        return cPrice;
    }

    public void setcPrice(double cPrice) {
        this.cPrice = cPrice;
        if(cPrice!=0){
            this.cpricestring.set(String.valueOf(cPrice));
        }
    }

    public double getdPrice() {
        return dPrice;
    }

    public void setdPrice(double dPrice) {
        this.dPrice = dPrice;
        if(dPrice!=0){
            this.dpricestring.set(String.valueOf(dPrice));
        }
    }

    public String getCpricestring() {
        return cpricestring.get();
    }
    
    public SimpleStringProperty cpricestringProperty() {
        return cpricestring;
    }

    public void setCpricestring(String cPriceString) {
        this.cpricestring.set(cPriceString);
    }

    public String getDpricestring() {
        return dpricestring.get();
    }

    public SimpleStringProperty dpricestringProperty() {
        return dpricestring;
    }
    
    public void setDpricestring(String dPriceString) {
        this.dpricestring.set(dPriceString);
    }
   
    public void setdAmount(int dAmount) {
        this.dAmount = dAmount;
        if(dAmount!=0){
            this.damountstring.set(String.valueOf(dAmount));
            this.dtotal.set(String.valueOf(dAmount*dPrice));
        }
        
    }
    
     public String getCamountstring() {
        return camountstring.get();
    }

      public SimpleStringProperty camountstringProperty() {
        return camountstring;
    }
     
    public void setCamountstring(String cAmountString) {
        this.camountstring.set(cAmountString);
    }

    public String getDamountstring() {
        return damountstring.get();
    }
    
    public SimpleStringProperty damountstringProprty() {
        return damountstring;
    }

    public void setDamountstring(String dAmountString) {
        this.damountstring.set(dAmountString);
    }

    public long getBillno() {
        return billno;
        
    }

    public void setBillno(long billno) {
        this.billno = billno;
        this.billnostring.set(String.valueOf(billno));
    }
    
      public SimpleStringProperty billnostringProprty() {
        return damountstring;
    }

    public void setBillnostring(String billnoString) {
        this.damountstring.set(billnoString);
    }

    public boolean isIsTempBill() {
        return isTempBill;
    }

    public void setIsTempBill(boolean isTempBill) {
        this.isTempBill = isTempBill;
    }
    
    
    
    
    public AccountPosition getAccountPosition(){
        AccountPosition pos=new AccountPosition();
        billbean bb=new billbean();
        pos.setBilled(this.billed);
        pos.setDebit(this.debit);
        
        pos.setBill(this.billno);
       ratesbean jj=new ratesbean();
        
        if(this.debit==false){
             pos.setAmount(this.cAmount);
             pos.setPositionname(this.cpositionname.get());
             pos.setPrice(this.cPrice);
             pos.setRate(jj.getDataRecord(this.cRateid));
        }if(this.debit==true){pos.setAmount(this.dAmount);
             pos.setPositionname(this.dpositionname.get());
             pos.setPrice(this.dPrice);
             pos.setRate(jj.getDataRecord(this.dRateid));
        }
        return pos;
    }
    
}
