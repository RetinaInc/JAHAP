package org.jahap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jahap.business.base.addressbean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    //private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }
     
    public void start(Stage stage) throws Exception {

        //log.info("Starting Hello JavaFX and Maven demonstration application");
       addressbean addresses=new addressbean();
        String fxmlFile = "/fxml/Maingui.fxml";
        //log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        //log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);
     

        
        stage.setScene(scene);
        stage.show();
    }
}
