/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

/**
 *
 * @author russ
 */


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


public class MainGuiFx implements Initializable {

    @FXML
    private TitledPane x1;

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private MenuItem newAddress;


 
    @FXML
    private MenuItem AdressSearch;
    @FXML
    private MenuItem Rates;
    @FXML
    private MenuItem Exit;
    @FXML
    private MenuItem Rooms;
    @FXML
    private MenuItem FO_SearchForReservation_fxmenuitem;
    @FXML
    private MenuItem searchForGuestAccount_fxmenuitem;
    @FXML
    private MenuItem DayClose_fxmenuitem;
    @FXML
    private MenuItem occplan_fxmenuitem;
   

    @FXML
    private void newAddress(ActionEvent event) throws IOException,InvocationTargetException {
     
        Stage stage = new Stage();
        String fxmlFile = "/fxml/AdressGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
         
            Scene scene = new Scene(page);
            stage.setTitle("Adresses");
            stage.setScene(scene);
            stage.show();
        
        
    }

    public void initialize(URL url, ResourceBundle rb) {
       
         System.out.print(com.sun.javafx.runtime.VersionInfo.getVersion());
    } 

    @FXML
    private void AdressSearch(ActionEvent event) throws IOException,InvocationTargetException {
     
        Stage stage = new Stage();
        String fxmlFile = "/fxml/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
            
            Scene scene = new Scene(page);
      
            stage.setScene(scene);
            stage.setTitle("Adresses");
            stage.show();
        
        
    }

    @FXML
    private void OpenRatesSearch(ActionEvent event) throws IOException {
        Stage tage = new Stage();
        String fxmlFile = "/fxml/RatesList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
      
            Scene tene = new Scene(page);
            tage.setTitle("Rates");
            tage.setScene(tene);
            tage.show();
        
        
        
    }

    @FXML
    private void RoomsSearch(ActionEvent event) throws IOException {
        
         Stage stage = new Stage();
        String fxmlFile = "/fxml/RoomList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
      
            Scene scene = new Scene(page);
            stage.setTitle("Rooms");
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void FO_SearchForReservation(ActionEvent event) throws IOException {
        
         Stage stage = new Stage();
        String fxmlFile = "/fxml/ResList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
      
            Scene scene = new Scene(page);
            stage.setTitle("Reservation");
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void searchForGuestAccount(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/AccList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
      
            Scene scene = new Scene(page);
            stage.setTitle("Accounts");
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void DayClose(ActionEvent event) {
    }

    @FXML
    private void occplan(ActionEvent event) {
    }
    
}
