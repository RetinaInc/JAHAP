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
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;

import org.jahap.business.base.roomsbean;

import org.jahap.entities.Rooms;
import org.jahap.sreport.ratereports;
import org.jahap.sreport.roomreports;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class RoomListController implements Initializable {
    @FXML
    private Button PrintButton;
    @FXML
    private TableView  dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    private roomsbean rooms;
    private List RoomsSearchResult;
    private long id=0;
    private boolean isOverviewDialog=false;
    /**
     * Initializes the controller class.
     */
    
    private RoomSearchResult searchresult;
    private InterResSearchResult ResSearchresult;
    private String guisource;
    
    
    private void initTable(){
        rooms= new roomsbean();
        RoomsSearchResult= rooms.SearchForRooms("*");
        ObservableList<Rooms> data= FXCollections.observableList(RoomsSearchResult);
        
        // -----------------  id
        TableColumn<Rooms,String> IdCol = new TableColumn<Rooms,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
      
      //dataTable.getColumns().add(IdCol);
      
      // -----------------  Code
        TableColumn<Rooms,String> CodeCol = new TableColumn<Rooms,String>("Code");
      CodeCol.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCode());
     }
     
             
      });  
      
      dataTable.getColumns().add(CodeCol);
      
       // -----------------  Name
        TableColumn<Rooms,String> NameCol = new TableColumn<Rooms,String>("Name");
      NameCol.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
      
      dataTable.getColumns().add(NameCol);
      
       // -----------------  Category
        TableColumn<Rooms,String> CatCol = new TableColumn<Rooms,String>("Category");
      CatCol.setCellValueFactory(new Callback<CellDataFeatures<Rooms, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rooms, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCategory());
     }
     
      
             
      });  
      
      dataTable.getColumns().add(CatCol);
        
       dataTable.setItems(data);
            
    
    
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchresult= new RoomSearchResult();
        initTable();
   }    
    
    public void init(InterResSearchResult ResSearchresults,ResguiController zi,String guisource){
        //RoomSearchResult searchresult
          searchresult= new RoomSearchResult();
        isOverviewDialog=true;
         this.ResSearchresult=ResSearchresults;
         this.guisource=guisource;
        initTable();
    }
 
    @FXML
    private void PrintReport(ActionEvent event) throws JRException {
        List<Rooms> jj=new ArrayList<Rooms>();
        jj=rooms.SearchForRooms("*");
        roomreports hh=new roomreports() ;
       hh.multiRoomReport(jj);
        
    }
    
    
    
    @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
               
     
                Rooms ad=(Rooms) dataTable.getSelectionModel().getSelectedItem();
                id=ad.getId();
       if(isOverviewDialog==false){searchresult.setDbRecordId(id, "Rooms");}
       
       if(event.getClickCount()==2){
             Stage stage = new Stage();
             String fxmlFile = "/fxml/RoomsGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
        RoomGuiFx controller= loader.<RoomGuiFx>getController();
       controller.init(id);
       
        
        stage.showAndWait();
      
      
       }
       
        
    } 

    @FXML
    private void OkAction(ActionEvent event) throws IOException {
        if(isOverviewDialog==false){
            Stage stage = (Stage) Ok.getScene().getWindow();
            stage.close();
        }
        
       if(isOverviewDialog==true){
           Rooms ad=(Rooms) dataTable.getSelectionModel().getSelectedItem();
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
}
