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
