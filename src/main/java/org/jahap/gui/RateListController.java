/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import org.jahap.business.base.ratesbean;
import org.jahap.entities.Address;
import org.jahap.entities.Rates;
import org.jahap.sreport.ratereports;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class RateListController implements Initializable {
    @FXML
    private Button PrintButton;
    @FXML
    private TableView  dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    private ratesbean rates;
    private List RatesSearchResult;
    private long id=0;
    private InterResSearchResult ResSearchresult;
    private InterAccSearchResult AccSearchresult;
    private String guisource;
    /**
     * Initializes the controller class.
     */
    private boolean isOverviewDialog=false;
    RateSearchResult searchresult;
    
    private void initTable(){
        rates= new ratesbean();
        RatesSearchResult= rates.SearchForRate("*");
        ObservableList<Rates> data= FXCollections.observableList(RatesSearchResult);
        
        // -----------------  id
        TableColumn<Rates,String> IdCol = new TableColumn<Rates,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Rates, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rates, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
      
      //dataTable.getColumns().add(IdCol);
      
      // -----------------  Code
        TableColumn<Rates,String> CodeCol = new TableColumn<Rates,String>("Code");
      CodeCol.setCellValueFactory(new Callback<CellDataFeatures<Rates, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rates, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCode());
     }
     
             
      });  
      
      dataTable.getColumns().add(CodeCol);
      
       // -----------------  Name
        TableColumn<Rates,String> NameCol = new TableColumn<Rates,String>("Name");
      NameCol.setCellValueFactory(new Callback<CellDataFeatures<Rates, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rates, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
      
      dataTable.getColumns().add(NameCol);
      
       // -----------------  Price
        TableColumn<Rates,String> PriceCol = new TableColumn<Rates,String>("Price");
      PriceCol.setCellValueFactory(new Callback<CellDataFeatures<Rates, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rates, String> p) {
         return new ReadOnlyObjectWrapper(String.valueOf(p.getValue().getPrice()));
     }
     
      
             
      });  
      
      dataTable.getColumns().add(PriceCol);
      
       // -----------------  RevAccount
        TableColumn<Rates,String> RevAccCol = new TableColumn<Rates,String>("Account");
      RevAccCol.setCellValueFactory(new Callback<CellDataFeatures<Rates, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Rates, String> p) {
         return new ReadOnlyObjectWrapper(String.valueOf(p.getValue().getRevaccount()));
     }
     
             
      });  
      
      dataTable.getColumns().add(RevAccCol);
        
       dataTable.setItems(data);
            
    
    
    }
    
    public void init(InterResSearchResult ResSearchresults,ResguiController zi,String guisource){
        //RoomSearchResult searchresult
          searchresult= new RateSearchResult();
        isOverviewDialog=true;
         this.ResSearchresult=ResSearchresults;
         this.guisource=guisource;
        initTable();
    }
    
    public void init(InterAccSearchResult AccSearchresults,SimpelAccountingController zi,String guisource){
        //RoomSearchResult searchresult
          searchresult= new RateSearchResult();
        isOverviewDialog=true;
         this.AccSearchresult=AccSearchresults;
         this.guisource=guisource;
        initTable();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchresult= new RateSearchResult();
        initTable();
        
   }    

    @FXML
    private void PrintReport(ActionEvent event) throws JRException {
        List<Rates> jj=new ArrayList<Rates>();
        jj=rates.SearchForRate("*");
        ratereports hh=new ratereports() ;
        hh.multiRateReport(jj);
        
    }

    @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
        Rates ad=(Rates) dataTable.getSelectionModel().getSelectedItem();
    id=ad.getId();
      searchresult.setDbRecordId(id, "Rates");
        if(event.getClickCount()==2){
       Stage stage = new Stage();
        String fxmlFile = "/fxml/RateGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
        RateGuiFx controller= loader.<RateGuiFx>getController();
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
           Rates ad=(Rates) dataTable.getSelectionModel().getSelectedItem();
    id=ad.getId();
        if(ResSearchresult!=null){
          try {
                ResSearchresult.setDbRecordId(id, guisource);
            } catch (Exception e) {
            }
        }if(AccSearchresult!=null){
            try {
                AccSearchresult.setDbRecordId(id, guisource);
            } catch (Exception e) {
            }
        }
        
            Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
       }
        
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }
}
