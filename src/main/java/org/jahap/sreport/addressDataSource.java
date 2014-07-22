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
import org.jahap.entities.Address;


/**
 *
 * @author russ
 */
public class addressDataSource implements JRDataSource{

    private List<Address>addressSource;

    public addressDataSource(List<Address>addressSource) {
        this.addressSource=addressSource;
    }
   
    
   private int counter=-1;
    
   private HashMap<String,Integer> fieldNumber= new HashMap<String, Integer>();

   
    
   
   
   
    public boolean next() throws JRException {
        if(counter<addressSource.size()-1){
            counter++;
             return true;   
                }
        return false;
    }

    public Object getFieldValue(JRField jrf) throws JRException {
        if(jrf.getName().equals("name"))return addressSource.get(counter).getName();
        else if(jrf.getName().equals("christianname"))return addressSource.get(counter).getChristianname();
        else if(jrf.getName().equals("city"))return addressSource.get(counter).getCity();
        else if(jrf.getName().equals("street"))return addressSource.get(counter).getStreet();
        else if(jrf.getName().equals("zipcode"))return addressSource.get(counter).getZipcode();
        else if(jrf.getName().equals("email"))return addressSource.get(counter).getEmail();
        else if(jrf.getName().equals("phone"))return addressSource.get(counter).getPhone();
        return "";
       
        
    }
    /*
    public static JRDataSource getDataSource(){
        return new addressDataSource();
    }
    */
}
