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
import java.util.List;
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
public final class JahapDatabaseConnector {

     private static  String PERSISTENCE_UNIT_NAME;
    // private static final String PERSISTENCE_UNIT_NAME = "JAHAP";
  public static EntityManagerFactory factory;
    private static EntityManager EntManager;
    private static JahapDatabaseConnector databaseConnector;
     
    
    public JahapDatabaseConnector() {
        ReadConfig config = new ReadConfig();
        List<ConfigItem> result = config.readConfig("config.xml");
        PERSISTENCE_UNIT_NAME = result.get(0).getPersitence_unit();
        
        factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntManager=factory.createEntityManager();
    }

    public static EntityManagerFactory getFactory() {
        return factory;
    }

    public static void setFactory(EntityManagerFactory factory) {
        JahapDatabaseConnector.factory = factory;
    }
    
    
  
    
    
   
    public EntityManager getEntity(){
        
        return EntManager;
       
    }
    
}
