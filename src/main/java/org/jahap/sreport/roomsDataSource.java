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
import org.jahap.entities.Rooms;

/**
 *
 * @author russ
 */
public class roomsDataSource implements JRDataSource{

    private List<Rooms>roomsSource;

    public roomsDataSource(List<Rooms>ratesSource) {
        this.roomsSource=ratesSource;
    }
   
    
   private int counter=-1;
    
   private HashMap<String,Integer> fieldNumber= new HashMap<String, Integer>();
    
   
   
   
    public boolean next() throws JRException {
        if(counter<roomsSource.size()-1){
            counter++;
             return true;   
                }
        return false;
    }

    public Object getFieldValue(JRField jrf) throws JRException {
        if(jrf.getName().equals("name"))return roomsSource.get(counter).getName();
        else if(jrf.getName().equals("category"))return roomsSource.get(counter).getCategory();
        else if(jrf.getName().equals("code"))return roomsSource.get(counter).getCode();
        
        return "";
       
        
    }
    /*
    public static JRDataSource getDataSource(){
        return new addressDataSource();
    }
    */
}
