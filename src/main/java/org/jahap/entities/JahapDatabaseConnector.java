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




package org.jahap.entities;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.jahap.config.ConfigItem;
import org.jahap.config.ReadConfig;

/**
 *
 * @author russ
 * 
 * - Hier muss ein Singelton einfügt werden, so dass nur eine Instanz des DB objekt gebildet werden kann.
 */
public  final class JahapDatabaseConnector {

     private static  String PERSISTENCE_UNIT_NAME;
     private static String DB_URL;
    // private static final String PERSISTENCE_UNIT_NAME = "JAHAP";
  private static EntityManagerFactory factory;
    private static EntityManager EntManager;
    private static JahapDatabaseConnector databaseConnector=null;
     private String DBUSER;
     private String DBPASS;
    
//    private JahapDatabaseConnector() {
//        ReadConfig config = new ReadConfig();
//        List<ConfigItem> result = config.readConfig("config.xml");
//        PERSISTENCE_UNIT_NAME = result.get(0).getPersitence_unit();
//        DB_URL= result.get(0).getDatabase_url();
//        Map properties = new HashMap();
//        properties.put("javax.persistence.jdbc.url", DB_URL);
//        properties.put("javax.persistence.jdbc.user", DBUSER);
//        properties.put("javax.persistence.jdbc.password", DBPASS);
//        factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//        
//        EntManager=factory.createEntityManager(properties);
//        
//    }
    
    private JahapDatabaseConnector(String user, String password) {
        ReadConfig config = new ReadConfig();
        List<ConfigItem> result = config.readConfig("config.xml");
      PERSISTENCE_UNIT_NAME = result.get(0).getPersitence_unit();
        DB_URL= result.get(0).getDatabase_url();
        Map properties = new HashMap();
        DBPASS=password;
        DBUSER=user;
        properties.put("javax.persistence.jdbc.url", DB_URL);
        properties.put("javax.persistence.jdbc.user", user);
        properties.put("javax.persistence.jdbc.password", password);
        properties.put("eclipselink.ddl-generation", "create-tables");
        properties.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.ClientDriver");
        
        factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,properties);
        
        EntManager=factory.createEntityManager();
        
    }
    
    
    
    
    public static EntityManagerFactory getFactory() {
        return factory;
    }

    public static void setFactory(EntityManagerFactory factory) {
        JahapDatabaseConnector.factory = factory;
    }
    
    
     public static JahapDatabaseConnector getConnector(String user, String password){
          if(databaseConnector==null){
               databaseConnector=new JahapDatabaseConnector(user, password);
               
        }
          return databaseConnector;
     }
     public static JahapDatabaseConnector getConnector(){
          
          return databaseConnector;
     }
    
   
    public EntityManager getEntity(){
       
        return EntManager;
       
    }
    
}
