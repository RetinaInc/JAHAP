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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import org.eclipse.persistence.internal.helper.DatabaseTable;
import org.jahap.business.base.addressbean;
import org.jahap.entities.Address;
import org.jahap.sreport.addressreports;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class ListDialogAddressController implements Initializable{
    private addressbean addresses;
    private List searchlistAddresses; // All Records of 
    @FXML
    private Button PrintButton;
     @FXML
     private TableView dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    private long id=0;
    private boolean isOverviewDialog=false;
   
    
    private AddressSearchResult searchresult;
    private InterResSearchResult ResSearchresult;
    private String guisource;
    
    
    
    private void initTable(){
        addresses = new addressbean();
        searchlistAddresses=addresses.SearchForAddress("*");
        ObservableList<Address> data= FXCollections.observableList(searchlistAddresses);
        
        // -----------------  id
        TableColumn<Address,String> IdCol = new TableColumn<Address,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- Christian Name  ----------------------- 
    
        TableColumn<Address,String> firstNameCol = new TableColumn<Address,String>("First Name");
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getChristianname());
     }
     
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      dataTable.getColumns().add(firstNameCol);
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- Name --------------------------------
      
       TableColumn<Address,String> NameCol = new TableColumn<Address,String>("Name");
      NameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });
       dataTable.getColumns().add(NameCol);
       
       
       //---------------------------------- Street --------------------------------
        
         TableColumn<Address,String> StreetCol = new TableColumn<Address,String>("Street");
      StreetCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(TableColumn.CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getStreet());
     }
     
             
      });
       dataTable.getColumns().add(StreetCol);
       
        //---------------------------------- City --------------------------------
        
         TableColumn<Address,String>CityCol = new TableColumn<Address,String>("City");
      CityCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCity());
     }
     
             
      });
       dataTable.getColumns().add(CityCol);
       
           //---------------------------------- ZipCode --------------------------------
        
         TableColumn<Address,String>ZipCol = new TableColumn<Address,String>("Zip Code");
      ZipCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getZipcode());
     }
     
             
      });
       dataTable.getColumns().add(ZipCol);
       
       
         //---------------------------------- Phone --------------------------------
        
         TableColumn<Address,String>PhoneCol = new TableColumn<Address,String>("Phone Col");
      PhoneCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getPhone());
     }
     
             
      });
       dataTable.getColumns().add(PhoneCol);
        
    dataTable.setItems(data);
    }
    
    
    
    /**
     * Initializes the controller class. Call from a Address Dialog,
     * Sets selected Address ID in Oberver
     */
    public void init(AddressSearchResult searchresults,AdressGuiFx zi){
         isOverviewDialog=true;
         searchresult= new AddressSearchResult();
         searchresult=searchresults;
        initTable();
    }
    
    public void init(InterResSearchResult ResSearchresults,ResguiController zi,String guisource){
         isOverviewDialog=true;
         
         this.ResSearchresult=ResSearchresults;
         this.guisource=guisource;
        initTable();
    }
    
    public void init(InterResSearchResult ResSearchresults,HotelSetupController zi,String guisource){
         isOverviewDialog=true;
         
         this.ResSearchresult=ResSearchresults;
         this.guisource=guisource;
        initTable();
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
       searchresult= new AddressSearchResult();
       initTable();
       
    }    

    @FXML
    private void PrintReport(ActionEvent event) {
        
         List<Address> adl= new ArrayList<Address>();
        adl=searchlistAddresses;
        
        addressreports ARP = new addressreports();
        try {
            ARP.multiAdressReport(adl);
        } catch (JRException ex) {
            Logger.getLogger(ListDialogAddressController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OkAction(ActionEvent event) throws IOException {
        if(isOverviewDialog==false){
        Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
        }
        if(isOverviewDialog==true){
             Address ad=(Address) dataTable.getSelectionModel().getSelectedItem();
                id=ad.getId();
            try {
                ResSearchresult.setDbRecordId(id, guisource);
            } catch (Exception e) {
            }
            Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
        }
        
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }

    @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
        
        
    
        
    Address ad=(Address) dataTable.getSelectionModel().getSelectedItem();
    id=ad.getId();
    if (isOverviewDialog==false){ searchresult.setDbRecordId(id, "Address");}
    
    if (event.getClickCount()==2){
        Stage stage = new Stage();
        String fxmlFile = "/fxml/AdressGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
        AdressGuiFx controller= loader.<AdressGuiFx>getController();
       controller.init(id);
       
        
        stage.showAndWait();
        
        
    }
    
       }
    }

   


    
    

