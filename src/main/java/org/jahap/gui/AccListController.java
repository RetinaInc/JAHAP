/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jahap.gui;

import com.google.common.eventbus.EventBus;
import com.lowagie.text.pdf.hyphenation.TernaryTree;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.acc.AccountInfo;
import org.jahap.entities.Accounts;


/**
 * FXML Controller class
 *
 * @author russ
 */
public class AccListController implements Initializable {
    @FXML
    private Button PrintButton;
    @FXML
    private TableView  dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
     private  List ll;
    
  
     
 
    
    private  accountsbean accbean;

    
       private void initTable(){
       
        
        accbean  = new accountsbean();
      
        ll=accbean.getAccOverview();
       ObservableList<AccountInfo> cc=FXCollections.observableList(ll);
   
        
//         -----------------  id
        TableColumn<AccountInfo,String> IdCol = new TableColumn<AccountInfo,String>("id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
      
//     dataTable.getColumns().add(IdCol);
    
      TableColumn<AccountInfo,String> arrival = new TableColumn<AccountInfo,String>("Arrivaldate");
      arrival.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getArrivaldate());
     }
     
             
      });  
      
      dataTable.getColumns().add(arrival);
      
       TableColumn<AccountInfo,String> departure = new TableColumn<AccountInfo,String>("departuredate");
      departure.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getDeparturedate());
     }
     
             
      });  
      
      dataTable.getColumns().add(departure);
      
       TableColumn<AccountInfo,String> name = new TableColumn<AccountInfo,String>("name");
      name.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
      
      dataTable.getColumns().add(name);
      
      
        TableColumn<AccountInfo,String> balance = new TableColumn<AccountInfo,String>("Balance");
      balance.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getBalance());
     }
     
             
      });  
      
      dataTable.getColumns().add(balance);
      
      
      dataTable.setItems(cc);
  }  
    
       
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         
        
        
        
       
        
        initTable();
    }    

    @FXML
    private void PrintReport(ActionEvent event) {
    }

    @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
        AccountInfo Ai=(AccountInfo) dataTable.getSelectionModel().getSelectedItem();
        int id;
        id= Integer.valueOf(Ai.getId());
        Stage stage = new Stage();
        String fxmlFile = "/fxml/simpelAccounting.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        SimpelAccountingController controller= loader.<SimpelAccountingController>getController();
       controller.init(id);
       
      
        stage.showAndWait();
        
    }

    @FXML
    private void OkAction(ActionEvent event) {
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }
    
}
