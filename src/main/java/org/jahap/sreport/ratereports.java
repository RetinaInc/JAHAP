/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.sreport;

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

import org.jahap.entities.Rates;


/**
 *
 * @author russ
 */
public class ratereports {
    
    public void singleRateReport(List<Rates>adlist) throws JRException{
          ratesDataSource adSource=new ratesDataSource(adlist);
        
        
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
			              
                    
                         
                        try {
            jp = JasperFillManager.fillReport("C:\\Users\\russ\\Documents\\NetBeansProjects\\jahap\\src\\main\\resources\\reports\\rate.jasper", new HashMap(), adSource);
            
        } catch (JRException jRException) {
        }
                        
                        
                         
                
                          
                          
               
			
                    
			
    
      
    
      //jhjh.getContentPane().add(viewer);
       
       System.out.println("Hallo");
              
                        
               JasperViewer.viewReport(jp,false);
        
        
    } 
    
     public void multiRateReport(List<Rates>adlist) throws JRException{
          ratesDataSource adSource=new ratesDataSource(adlist);
        
        
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
			              
                    
                         
                        try {
            jp = JasperFillManager.fillReport("C:\\Users\\russ\\Documents\\NetBeansProjects\\jahap\\src\\main\\resources\\reports\\ratesList.jasper", new HashMap(), adSource);
            
        } catch (JRException jRException) {
        }
                        
                        
                         
                
                          
                          
               
			
                    
			
    
      
    
      //jhjh.getContentPane().add(viewer);
       
       System.out.println("Hallo");
              
                        
               JasperViewer.viewReport(jp,false);
        
        
    } 
    
    
    
 }
    
    
    
    
    

