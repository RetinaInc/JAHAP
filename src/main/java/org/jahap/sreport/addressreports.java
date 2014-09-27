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

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.jahap.entities.Address;


/**
 *
 * @author russ
 */
public class addressreports {
    
    public void singleAdressReport(List<Address>adlist) throws JRException{
          addressDataSource adSource=new addressDataSource(adlist);
        
        
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
			  reportsbean rbean=new reportsbean();
			       ByteArrayInputStream bis = new ByteArrayInputStream(rbean.SearchForReport("Address").getReport());              
                    
                         
                        try {
            jp = JasperFillManager.fillReport(bis, new HashMap(), adSource);
            
        } catch (JRException jRException) {
        }
                        
                        
                         
                
                          
                          
               
			
                    
			
    
      
    
      //jhjh.getContentPane().add(viewer);
       
       System.out.println("Hallo");
              
                        
               JasperViewer.viewReport(jp,false);
        
        
    } 
    
     public void multiAdressReport(List<Address>adlist) throws JRException{
          addressDataSource adSource=new addressDataSource(adlist);
        
        
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
			  reportsbean rbean=new reportsbean();
			       ByteArrayInputStream bis = new ByteArrayInputStream(rbean.SearchForReport("Address List").getReport());               
                    
                         
                        try {
            jp = JasperFillManager.fillReport(bis, new HashMap(), adSource);
            
        } catch (JRException jRException) {
        }
                        
                        
                         
                
                          
                          
               
			
                    
			
    
      
    
      //jhjh.getContentPane().add(viewer);
       
       System.out.println("Hallo");
              
                        
               JasperViewer.viewReport(jp,false);
        
        
    } 
    
    
    
 }
    
    
    
    
    

