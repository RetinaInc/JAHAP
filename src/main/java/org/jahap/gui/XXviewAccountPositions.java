/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.AccountPosition;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author russ
 */
public class XXviewAccountPositions {

   
    
     private boolean billed=false;
     private boolean debit=false;
     private long id;
     private Date ratedate;
     private String rateDateString;
     private boolean canceled=false;
     private long  canceledposition;
     private double cPrice;
     private double dPrice;
     
     private String cPriceString;
     private String dPriceString;
     
     private long cRateid;
     private long dRateid;
     private String cPositionname;
     private String dPositionname;
     private String cTotal;
     private String dTotal;
   
     private String cAmountString;
     private String dAmountString;
     private int cAmount;
     private int dAmount;
    private  DateFormat df ;
     
      public XXviewAccountPositions() {
    df = new SimpleDateFormat("MM.dd.yyyy");
    }
    
    public boolean isBilled() {
        return billed;
    }

     public String getRateDateString() {
        return rateDateString;
    }

    public void setRateDateString(String rateDateString) {
        this.rateDateString = rateDateString;
        
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
        this.id = id;
    }

    public Date getRatedate() {
        return ratedate;
     
    }

    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
        if(ratedate!=null){
        this.rateDateString=df.format(ratedate);
        }
    }

    public long getcRateid() {
        return cRateid;
    }

    public void setcRateid(long cRateid) {
        this.cRateid = cRateid;
        this.id=cRateid;
        
        
        
        
    }

    public String getcTotal() {
        if(this.cAmount!=0 && this.cPrice!=0){
           this.cTotal=String.valueOf(this.cAmount*this.cPrice);
        }          
        return cTotal;
    }

   

    public String getdTotal() {
        
        if(this.dAmount!=0 && this.dPrice!=0){
        this.dTotal=String.valueOf(this.dAmount*this.dPrice);
        }
        return dTotal;
    }

   

    public long getdRateid() {
        return dRateid;
    }

    public void setdRateid(long dRateid) {
        this.dRateid = dRateid;
        this.id=dRateid;
    }

    public String getcPositionname() {
        return cPositionname;
    }

    public void setcPositionname(String cPositionname) {
        this.cPositionname = cPositionname;
    }

    public String getdPositionname() {
        return dPositionname;
    }

    public void setdPositionname(String dPositionname) {
        this.dPositionname = dPositionname;
    }

    public int getcAmount() {
        return cAmount;
        
    }

    public void setcAmount(int cAmount) {
        this.cAmount = cAmount;
        if(cAmount!=0){
            this.cAmountString=String.valueOf(cAmount);
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
            this.cPriceString=String.valueOf(cPrice);
        }
    }

    public double getdPrice() {
        return dPrice;
    }

    public void setdPrice(double dPrice) {
        this.dPrice = dPrice;
        if(dPrice!=0){
            this.dPriceString=String.valueOf(dPrice);
        }
    }

    public String getcPriceString() {
        return cPriceString;
    }

    public void setcPriceString(String cPriceString) {
        this.cPriceString = cPriceString;
    }

    public String getdPriceString() {
        return dPriceString;
    }

    public void setdPriceString(String dPriceString) {
        this.dPriceString = dPriceString;
    }
   
    public void setdAmount(int dAmount) {
        this.dAmount = dAmount;
        if(dAmount!=0){
            this.dAmountString=String.valueOf(dAmount);
        }
        
    }
    
     public String getcAmountString() {
        return cAmountString;
    }

    public void setcAmountString(String cAmountString) {
        this.cAmountString = cAmountString;
    }

    public String getdAmountString() {
        return dAmountString;
    }

    public void setdAmountString(String dAmountString) {
        this.dAmountString = dAmountString;
    }
    
    public AccountPosition getAccountPosition(){
        AccountPosition pos=new AccountPosition();
        pos.setBilled(this.billed);
        pos.setDebit(this.debit);
       ratesbean jj=new ratesbean();
        
        if(this.debit==false){
             pos.setAmount(this.cAmount);
             pos.setPositionname(this.cPositionname);
             pos.setPrice(this.cPrice);
             pos.setRate(jj.getDataRecord(this.cRateid));
        }if(this.debit==true){pos.setAmount(this.dAmount);
             pos.setPositionname(this.dPositionname);
             pos.setPrice(this.dPrice);
             pos.setRate(jj.getDataRecord(this.dRateid));
        }
        return pos;
    }
    
}
