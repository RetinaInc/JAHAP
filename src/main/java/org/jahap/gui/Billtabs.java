/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jahap.gui;

/**
 *
 * @author russ
 */

import com.google.common.eventbus.EventBus;
import com.lowagie.text.pdf.hyphenation.TernaryTree;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 *
 * @author russ
 */
public class BillTabs{
        
        @FXML
         private TableColumn<viewAccountPositionsProperty, String>id_Account_tablefx;                                                   
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> date_Account_tablefx;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cService_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cAmount_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dService_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dAmount_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cPrice_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dPrice_Account_tablefxColumn;
        private EventBus eventbus;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cTotal_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dTotal_Account_tablefxColumn;
        
        
         private ObservableList<viewAccountPositionsProperty> datamk=FXCollections.observableArrayList();
         private long billno;
         private Tab billtab;
        private TableView ggk;
         
          
         
        public BillTabs(long billno) {
           ggk = new TableView();
             ggk.setPrefHeight(501);
             ggk.setPrefWidth(829);
             this.setBillno(billno);
             
             billtab = new Tab();
            billtab.setText(String.valueOf(billno));
            billtab.setContent(ggk);
        }     
        
        
        public String getBillname(){
            return this.billtab.getText();
        }
        
        public List<viewAccountPositionsProperty> removeSelectedPosiions(){
            ObservableList<viewAccountPositionsProperty>  gg = FXCollections.observableArrayList();
            gg=ggk.getSelectionModel().getSelectedItems();
            boolean jk=false;
            // checks for billed position which are not temp bills
            for(Iterator<viewAccountPositionsProperty>jjk=gg.iterator();jjk.hasNext();){
                  if(jjk.next().getBillno()!=0){
                      jk=true;
                  }
            }
            if(jk==false){
             ggk.getItems().remove(gg);
            }
            return gg;
        }
        
        
        
        public BillTabs(String billname) {
           ggk = new TableView();
             ggk.setPrefHeight(501);
             ggk.setPrefWidth(829);
             this.setBillno(billno);
             
             billtab = new Tab();
            billtab.setText(billname);
            billtab.setContent(ggk);
        }     

        public TableView getGgk() {
            return ggk;
        }

        public void setGgk(TableView ggk) {
            this.ggk = ggk;
        }
             
        
        
        public long getBillno() {
            return billno;
        }

        public void setBillno(long billno) {
            this.billno = billno;
        }

        public Tab getBilltab() {
            return billtab;
        }

        public void setBilltab(Tab billtab) {
            this.billtab = billtab;
        }
        
        public void addPosition(viewAccountPositionsProperty jj){
            try {
                this.datamk.add(jj);
            } catch (Exception e) {
                System.out.println("---<BillTabs>---");
                System.out.println(e.toString());               
            }
            
            
        }
        
        public void addPositions(ObservableList<viewAccountPositionsProperty> jj){
            
            
            boolean addAll = this.datamk.addAll(jj);
//            ggk.getItems().removeAll();
//            
//            ggk.getItems().add(datamk);
//            billtab.setContent(ggk);
            
            this.ggk.setVisible(false);
            this.ggk.setVisible(true);
            
            
        }
        
        
        public ObservableList<viewAccountPositionsProperty>getList(){
            return this.datamk;
        }
        
        public void createTabel(){
            buildTable(this.ggk);
        }
        
        public void refreshTab(){
             
        }
        
        
          private void buildTable(TableView ko){
              // #################  ID       
            this.id_Account_tablefx  = new TableColumn<viewAccountPositionsProperty, String>("idx");
             this.id_Account_tablefx.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("idx"));
                     
           
            
             
             
              // ############### Date
                
             this.date_Account_tablefx=new TableColumn<viewAccountPositionsProperty, String>("rateDateString");    
             this.date_Account_tablefx.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("rateDateString"));
             this.date_Account_tablefx .setCellFactory(new Callback<TableColumn<viewAccountPositionsProperty, String>, TableCell<viewAccountPositionsProperty, String>>() {
                  @Override
                  public TableCell<viewAccountPositionsProperty, String> call(TableColumn<viewAccountPositionsProperty, String> param) {
                      return new TableCell<viewAccountPositionsProperty, String>() {
                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             Tooltip tol=new Tooltip("Info");
                             
                              super.updateItem(item, empty);
                              
                               int tl=getIndex();

                               if(tl<=datamk.size()-1){
                                 if(datamk.get(tl).isDebit()==true){
                                 // DEV: Stylshert implement
                                 // setStyle("-fx-font-style: italic;");
                                 
                                 }
                                 if(datamk.get(tl).isCanceled()==true){
                                  setTextFill(Color.RED);
                                   tol.setText("This position is canceled");
                                    Tooltip.install(this, tol);
                                 }
                                 if(datamk.get(tl).isBilled()==true || datamk.get(tl).getBillnamestring()!="" && !datamk.get(tl).getBillnamestring().contentEquals("ZEROBILL")){
                                    setTextFill(Color.GREY);
                                    String texttip=new String();
                                    texttip="This position is billed";
                                      if(datamk.get(tl).isCanceled()==true){
                                         texttip= texttip + " and canceled";
                                      }
                                    tol.setText(texttip);
                                    Tooltip.install(this, tol);
                                 }
                                 setText(item);
                               }
                               
                                setText(item);
//                               }
                          }
                         
                         
                          
                         
                      };
                  }
                });  
             ko.getColumns().add(this.date_Account_tablefx);
                          
              // #############cAmount
             this.cAmount_Account_tablefxColumn=new TableColumn<viewAccountPositionsProperty, String>("camountstring"); 
             this.cAmount_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("camountstring"));
             this.cAmount_Account_tablefxColumn.setCellFactory(new Callback<TableColumn<viewAccountPositionsProperty, String>, TableCell<viewAccountPositionsProperty, String>>() {
                  @Override
              public TableCell<viewAccountPositionsProperty, String> call(TableColumn<viewAccountPositionsProperty, String> param) {
                      return new TableCell<viewAccountPositionsProperty, String>() {
                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                               int tl=getIndex();

                                if(tl<=datamk.size()-1){
                                 if(datamk.get(tl).isBilled()==true || datamk.get(tl).getBillnamestring()!="" && !datamk.get(tl).getBillnamestring().contentEquals("ZEROBILL")){
                                 setTextFill(Color.GREY);
                                    String texttip=new String();
                                    texttip="This position is billed";
                                     
                                 }
                                 setText(item);
                             
                         } 
                          
                         }
                      };
                  }
                });  
             ko.getColumns().add(this.cAmount_Account_tablefxColumn);   
        
             // ################ cPosname
             
           this.cService_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("cPosition");
             this.cService_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("cpositionname"));
           ko.getColumns().add(this.cService_Account_tablefxColumn);   
             
             
              // #############cPrice
             this.cPrice_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("cpricestring");
             this.cPrice_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("cpricestring"));
             ko.getColumns().add(this.cPrice_Account_tablefxColumn);   
              // ################ cTotal
              this.cTotal_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("ctotal");
             this.cTotal_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("ctotal"));
              ko.getColumns().add(this.cTotal_Account_tablefxColumn);   
             
             
          
             
            
             
             // ##############dPosname
               this.dService_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("dpositionname");
              this.dService_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dpositionname"));
              
             
              this.dService_Account_tablefxColumn.setStyle("-fx-table-cell-border-color: grey");
              ko.getColumns().add(this.dService_Account_tablefxColumn);   
          
             // #################dAmount
              this.dAmount_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("damountstring");
              this.dAmount_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("damountstring"));
            ko.getColumns().add(this.dAmount_Account_tablefxColumn);   
              
                // #############dPrice
             this.dPrice_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("dpricestring");
             this.dPrice_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dpricestring"));
             ko.getColumns().add(this.dPrice_Account_tablefxColumn);   
              // ################ dTotal
             this.dTotal_Account_tablefxColumn = new TableColumn<viewAccountPositionsProperty, String>("dtotal");          
            this.dTotal_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dtotal"));
           ko.getColumns().add(this.dTotal_Account_tablefxColumn);   
              
             
           
              
             
              
              
  
             
             
          // List<viewAccountPositionsProperty> accview=new ArrayList<viewAccountPositionsProperty>();
   
    
   
   
   
             
  
       ko.setItems(this.datamk);
    }
        
    }
