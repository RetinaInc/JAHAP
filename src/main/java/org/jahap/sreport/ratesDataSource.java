/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.sreport;

import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.jahap.entities.Rates;

/**
 *
 * @author russ
 */
public class ratesDataSource implements JRDataSource{

    private List<Rates>ratesSource;

    public ratesDataSource(List<Rates>ratesSource) {
        this.ratesSource=ratesSource;
    }
   
    
   private int counter=-1;
    
   private HashMap<String,Integer> fieldNumber= new HashMap<String, Integer>();
    
   
   
   
    public boolean next() throws JRException {
        if(counter<ratesSource.size()-1){
            counter++;
             return true;   
                }
        return false;
    }

    public Object getFieldValue(JRField jrf) throws JRException {
        if(jrf.getName().equals("name"))return ratesSource.get(counter).getName();
        else if(jrf.getName().equals("price"))return ratesSource.get(counter).getPrice();
        else if(jrf.getName().equals("revaccount"))return ratesSource.get(counter).getRevaccount();
        else if(jrf.getName().equals("code"))return ratesSource.get(counter).getCode();
        
        return "";
       
        
    }
    /*
    public static JRDataSource getDataSource(){
        return new addressDataSource();
    }
    */
}
