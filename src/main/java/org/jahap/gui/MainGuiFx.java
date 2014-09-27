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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.jahap.CurrentUser;


public class MainGuiFx implements Initializable {

    @FXML
    private TitledPane x1;

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
    private MenuItem checkin;
    @FXML
    private MenuItem Checkcout;
    @FXML
    private MenuItem newReservation;
    @FXML
    private Menu PreferencesMenu;
    @FXML
    private MenuItem RoomPrefMenuItem;
    @FXML
    private MenuItem RatesPrefMenuItem;
    @FXML
    private MenuItem HotelPrefMenuItem;
   

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
         CurrentUser cu=CurrentUser.getCurrentUser();
         System.out.print(cu.isIsAdmin());
         PreferencesMenu.visibleProperty().set(cu.isIsAdmin());
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
    private void DoCheckin(ActionEvent event) {
    }

    @FXML
    private void DoCheckout(ActionEvent event) {
    }

    @FXML
    private void createNewReservation(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        String fxmlFile = "/fxml/resgui.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
      
            Scene scene = new Scene(page);
            stage.setTitle("New reservation");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void OpenOccplan(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        String fxmlFile = "/fxml/occplan.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
      
            Scene scene = new Scene(page);
            stage.setTitle("New reservation");
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void RoomPrefMenu(ActionEvent event) {
    }

    @FXML
    private void RatesPrefMenu(ActionEvent event) {
    }

    @FXML
    private void HotelPrefMenu(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/hotelSetup.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
      
            Scene scene = new Scene(page);
            stage.setTitle("Hotel Setup");
            stage.setScene(scene);
            stage.show();
        
    }
    
}
