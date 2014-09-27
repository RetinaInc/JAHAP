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

package org.jahap;

import java.io.IOException;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.j2ee.servlets.XmlServlet;
import org.jahap.gui.InterAccSearchResultEvent;
import org.jahap.gui.LogonGuiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp  extends Application implements MainEventListener{

    //private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private MainEventResult mEv;
    public static void main(String[] args)  throws Exception {
        
        launch(args);
        
    }
     
    public void idinfo(MainEvent e) {
        
      if(e.isIsSuccessFull()==true){ 
           Stage stage = new Stage();
        String  fxmlFile = "/fxml/Maingui.fxml";
        //log.debug("Loading FXML for main view from: {}", fxmlFile);
         FXMLLoader loader = new FXMLLoader();
        AnchorPane page = null;

          try {
              page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
              //log.debug("Showing JFX scene");
          } catch (IOException ex) {
              java.util.logging.Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
          }
         
         
         Scene scene = new Scene(page);

        
        stage.setScene(scene);
        stage.show();
      }
    }
    
    public void start(Stage stage) throws Exception {
        mEv = new MainEventResult();
        mEv.addIDListener(this);
        //log.info("Starting Hello JavaFX and Maven demonstration application");
       
       String   fxmlFile = "/fxml/LogonGui.fxml";
        //log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        //log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);
         
         LogonGuiController controller= loader.<LogonGuiController>getController();
       controller.init(mEv);
        
        stage.setScene(scene);
        
     stage.show();
        
       
        
    }
}
