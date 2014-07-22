/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.print.DocFlavor;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.acc.accountspositionbean;
import org.jahap.business.acc.billbean;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Accounts;
import org.jahap.entities.Bill;
import org.jahap.entities.Rates;
import org.jahap.gui.BillTabs;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class SimpelAccountingController implements Initializable, InterAccSearchResultListener{

    static Logger log = Logger.getLogger(SimpelAccountingController.class.getName());
    
    
    @FXML
    private TableView Account_tablefx;
    
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
    
    
    @FXML
    private TextField balance_fxtextbox;
    @FXML
    private Button addArticle;
    @FXML
    private Button cancleArticle;
    @FXML
    private Button editArticle;
    @FXML
    private Button editRates;
    @FXML
    private Button printOverview;
    @FXML
    private Button closeAccount;
    @FXML
    private Button AdvancedChargeRates;
    //private List<viewAccountPositions> accview;
    private InterAccSearchResult accsearchresult;
    private  accountsbean acc;
    private ratesbean rates;
    private List<AccountPosition> accpos;
    private long rateid=0;
    //private ObservableList<viewAccountPositions> data;
    final ObservableList<viewAccountPositionsProperty> datam=FXCollections.observableArrayList();
    @FXML
    private Tooltip balance_textbox_fxtooltip;
  
    private ObservableList<String> tempBills=FXCollections.observableArrayList();
    
    @FXML
    private Tab Account;
    
    
    
    private List<AccountViewer> accViewList;
  
    @FXML
    private Button createInvoiceButton;
    @FXML
    private ChoiceBox<String> movePositionToBillChoiceBox;
    @FXML
    private Button removePosfromBillbutton;
    @FXML
    private TabPane AccountTab;
    @FXML
    private Button PrintAndCloseBill_FxButton;
    @FXML
    private TitledPane x1;
    @FXML
    private TitledPane x2;
    @FXML
    private TitledPane x3;
    @FXML
    private TitledPane x4;
    
   
    

    
    
    
    
    private List<BillTabs> billtablist;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        log.debug("Function entry");
        log.debug("Function exit");
    }    
    
    void init(long id){
        log.debug("Function entry: init" );
            
                
       
          
        acc=new accountsbean();
        rates= new ratesbean();
        accsearchresult = new InterAccSearchResult();
        accsearchresult.addIDListener(this);
        acc.getDataRecord(id);
        //initTable();
        testinittable();
        double t=acc.getBalance();
        balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
       
              
        
        log.debug("Function exit: init");
    }
    



               
     
//    }
    
    void testinittable(){
              log.debug("Function entry testinittable");    
         billbean jjk= new billbean();
                    AccountPosition zw=new AccountPosition();
                      viewAccountPositionsProperty bz;
               try {
            for (Iterator<AccountPosition> iAccPos = acc.getAccountPositionCollection().iterator(); iAccPos.hasNext();) {
                
                bz = new viewAccountPositionsProperty();
                zw = iAccPos.next();
                bz.setRatedate(zw.getRatedate());                
                bz.setDebit(zw.getDebit());
                bz.setId(zw.getId());
                bz.setBilled(zw.getBilled());
                bz.setCanceled(zw.isCanceled());
                
                try {
                    bz.setIsTempBill(jjk.getDataRecord(zw.getBill()).isTemp_bill());
                    
                } catch (Exception e) {
                    bz.setIsTempBill(false);
                }
                
                
                
                try {
                  
                    if(jjk.getDataRecord(zw.getBill()).isTemp_bill()  ){
                        bz.setBillnamestring(jjk.getDataRecord(zw.getBill()).getBillname());
                    }
                } catch (Exception e) {
                    bz.setBillnamestring("");
                }
              
                   try {
                      if(!jjk.getDataRecord(zw.getBill()).isTemp_bill() ){   //   
                    bz.setBillno(zw.getBill());
                      }
                } catch (Exception e) {
                     bz.setBillno(0); 
                }
                
           
                // ############### Split Credit Row  #####################      
                if (zw.getDebit() == false) {
                    bz.setcAmount(zw.getAmount());
                    bz.setCpositionname(zw.getPositionname());
                    bz.setcRateid(zw.getRate().getId());
                    bz.setcPrice(zw.getPrice());
                    
                }                
                
                if (zw.getDebit() == true) {
                    bz.setdAmount(zw.getAmount());
                    bz.setDpositionname(zw.getPositionname());
                    bz.setdRateid(zw.getRate().getId());
                    bz.setdPrice(zw.getPrice());
                    
                }

                // accview.add(bz);
                datam.add(bz);
            
            }            
        } catch (Exception e) {
            System.err.print("----<property set >----");
            e.printStackTrace();
        }
               
               TabPane gg=Account.getTabPane();
               billtablist= new ArrayList<BillTabs>();
              boolean gh=false;
               // Search for Billed Position
            for(Iterator<viewAccountPositionsProperty> im=datam.iterator(); im.hasNext();  ){
                 gh=false;
                 viewAccountPositionsProperty ggl=im.next();
                 
                if(ggl.getBillno()!=0 && !ggl.isIsTempBill()){
                       // search for exiting tab with bill no
                       for(Iterator<BillTabs> tbas=billtablist.iterator();tbas.hasNext();){
                           BillTabs koller=tbas.next();   
                               if(koller.getBillno()==ggl.getBillno()) {
                                   
                                   gh=true; 
                                      // add position to Tab ObsList
                                      koller.addPosition(ggl);
                                      
                               } 
                              
                         
                        }
                       // Add new tab
                     try {
                        if (gh == false) {
                            
                            
                            BillTabs jj = new BillTabs(ggl.getBillno());

                            // add position to Tab ObsList
                            jj.addPosition(ggl);
                            log.debug("Add Bill Tab" + String.valueOf(jj.getBillname()));
                            billtablist.add(jj);
                        }
                    } catch (Exception e) {
                         log.debug("Error while adding to billtab list");
                         e.printStackTrace();
                    }
                     
                     // TEMP BILLS: Bills that are not closed yet and therefore have non billno
                }else{
                    if(ggl.isIsTempBill() && !ggl.getBillnamestring().equalsIgnoreCase("ZEROBILL") ){
                        
                            
                               for(Iterator<BillTabs> tbas=billtablist.iterator();tbas.hasNext();){
                           BillTabs koller=tbas.next();   
                               if(koller.getBillname()==ggl.getBillnamestring() && ggl.isIsTempBill()) {
                                   
                                   gh=true; 
                                      // add position to Tab ObsList
                                      koller.addPosition(ggl);
                                      
                               } 
                              
                         
                        }
                       // Add new tab
                     try {
                        if (gh == false) {
                            
                            
                             BillTabs jj = new BillTabs(ggl.getBillnamestring());
                              jj.addPosition(ggl);
                              billtablist.add(jj);
                              tempBills.add(ggl.getBillnamestring());
                        }
                    } catch (Exception e) {
                         System.out.println("----<add to billtablist TEMP >-----");
                         e.printStackTrace();
                    }
                            
                            
                            
                            
                             
                        
                    }
                }
                
            } 
               
               
             for(Iterator<BillTabs>kkl=billtablist.iterator();kkl.hasNext();){
                 BillTabs mlk=kkl.next();
                 mlk.createTabel();
                 gg.getTabs().add(mlk.getBilltab());   
                 
             }  
              
             movePositionToBillChoiceBox.setItems(tempBills);
             
             
             
            
             
             
                     
           
                     
                   
        buildTable(Account_tablefx);
        
        log.debug("Function exit testinittable");
    }
    
    private void buildTable(TableView ko){
              // #################  ID  
            log.debug("Function entry buildTable");
            id_Account_tablefx  = new TableColumn<viewAccountPositionsProperty, String>("idx");
             id_Account_tablefx.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("idx"));
                     
           
            
             
             
              // ############### Date
                
                 
             date_Account_tablefx.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("rateDateString"));
             date_Account_tablefx .setCellFactory(new Callback<TableColumn<viewAccountPositionsProperty, String>, TableCell<viewAccountPositionsProperty, String>>() {
                  @Override
                  public TableCell<viewAccountPositionsProperty, String> call(TableColumn<viewAccountPositionsProperty, String> param) {
                      return new TableCell<viewAccountPositionsProperty, String>() {
                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             log.debug("Function entry updateItem Table Format row");
                             try {
                                 Tooltip tol = new Tooltip("Info");
                                 
                                 super.updateItem(item, empty);
                                 
                                 int tl = getIndex();
                                 
                                 if (tl <= datam.size() - 1) {
                                     if (datam.get(tl).isDebit() == true) {
                                 // DEV: Stylshert implement
                                         // setStyle("-fx-font-style: italic;");
                                         
                                     }
                                     if (datam.get(tl).isCanceled() == true) {
                                         setTextFill(Color.RED);
                                         tol.setText("This position is canceled");
                                         Tooltip.install(this, tol);
                                     }
                                     if (datam.get(tl).isBilled() == true || (datam.get(tl).isIsTempBill() &&  !"ZEROBILL".equalsIgnoreCase(datam.get(tl).getBillnamestring()))) {
                                         setTextFill(Color.GREY);
                                         String texttip = new String();
                                         texttip = "This position is billed";
                                         if (datam.get(tl).isCanceled() == true) {
                                             texttip = texttip + " and canceled";
                                         }
                                         tol.setText(texttip);
                                         Tooltip.install(this, tol);
                                     }
                                     setText(item);
                                 }
                                 
                                 setText(item);
//                               }
                             } catch (Exception e) {
                                 e.printStackTrace();
                                   
                             }
                          }
                         
                         
                          
                         
                      };
                  }
                });  
             
                             
              // #############cAmount
             
             cAmount_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("camountstring"));
             cAmount_Account_tablefxColumn.setCellFactory(new Callback<TableColumn<viewAccountPositionsProperty, String>, TableCell<viewAccountPositionsProperty, String>>() {
                  @Override
              public TableCell<viewAccountPositionsProperty, String> call(TableColumn<viewAccountPositionsProperty, String> param) {
                      return new TableCell<viewAccountPositionsProperty, String>() {
                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                               int tl=getIndex();

                               try {
                                 if (tl <= datam.size() - 1) {
                                     if (datam.get(tl).isBilled() == true || (datam.get(tl).isIsTempBill() &&  !"ZEROBILL".equalsIgnoreCase(datam.get(tl).getBillnamestring()))) {
                                         setTextFill(Color.GREY);
                                         String texttip = new String();
                                         texttip = "This position is billed";
                                         
                                     }
                                     setText(item);
                                     
                                 }                                 
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }
                          
                         }
                      };
                  }
                });  
             
        
             // ################ cPosname
             
             //cService_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("cPosition");
             cService_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("cpositionname"));
           
             
             
              // #############cPrice
            
             cPrice_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("cpricestring"));
              // ################ cTotal
             
             cTotal_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("ctotal"));
              
             
             
          
             
            
             
             // ##############dPosname
             
              dService_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dpositionname"));
              
             
              dService_Account_tablefxColumn.setStyle("-fx-table-cell-border-color: grey");
              
          
             // #################dAmount
          
              dAmount_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("damountstring"));
            
                // #############dPrice
            
             dPrice_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dpricestring"));
             
              // ################ dTotal
                       
            dTotal_Account_tablefxColumn.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("dtotal"));
           
              
             
           
              
             
              
              
  
             
             
           List<viewAccountPositionsProperty> accview=new ArrayList<viewAccountPositionsProperty>();
   
    
   
   
   
             
       ko.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       ko.setItems(datam);
       
        log.debug("Function exit");
    }
    
    
    
    @FXML
    private void addArticle(ActionEvent event) throws IOException {
        log.debug("Function entry");
         Stage stage = new Stage();
        String fxmlFile = "/fxml/RatesList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        RateListController controller= loader.<RateListController>getController();
       controller.init(accsearchresult,this,"rate");
       
        
        stage.showAndWait();
        
        log.debug("Function exit buildTable");
    }

    @FXML
    private void cancleArticle(ActionEvent event) {
        log.debug("Function entry cancleArticle");
        Calendar cal  = Calendar.getInstance();
        ObservableList<viewAccountPositionsProperty>  gg = FXCollections.observableArrayList();
        gg=Account_tablefx.getSelectionModel().getSelectedItems();
        if(gg.size()==1){
        Account_tablefx.getSelectionModel().getSelectedItems();
        viewAccountPositionsProperty jh= (viewAccountPositionsProperty) Account_tablefx.getSelectionModel().getSelectedItem();
        
        
        
        viewAccountPositionsProperty ml=new viewAccountPositionsProperty();
        if(jh.isDebit()==false && jh.isCanceled()==false && jh.isBilled()!= true && jh.getBillnamestring()==""){   
           ml.setDebit(true);
           ml.setdRateid(jh.getcRateid());
           ml.setdAmount(jh.getcAmount());
           ml.setCanceledposition(jh.getId());
           ml.setDpositionname(jh.getCpositionname());
           ml.setdPrice(jh.getcPrice());
           
           ml.setRatedate(cal.getTime());
           
           for(int io=0;io<=datam.size()-1;io++){
            if(datam.get(io).getId()==jh.getId()){
                datam.get(io).setCanceled(true);
            }
        }
           
           datam.add(ml);
           
           Rates rate;
           rate = rates.getDataRecord(ml.getcRateid());
           //datam.remove(ml);
           acc.cancelPosition(rate,ml.getcAmount(),ml.getId(),ml.getcPrice(),ml.getDpositionname());
             balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
        }
        }
        log.debug("Function exit cancleArticle");
    }

    @FXML
    private void editArticle(ActionEvent event) throws IOException {
////////////        
        log.debug("Function entry editArticle");
          viewAccountPositionsProperty jh= (viewAccountPositionsProperty) Account_tablefx.getSelectionModel().getSelectedItem();
          
          ObservableList<viewAccountPositionsProperty>  gg = FXCollections.observableArrayList();;
        gg=Account_tablefx.getSelectionModel().getSelectedItems();
        if(gg.size()==1){
          if(jh.isCanceled()==false && jh.getCanceledposition()==0 && !jh.isBilled() && ("".equals(jh.getBillnamestring()) || "ZEROBILL".equals(jh.getBillnamestring()))){
         Stage stage = new Stage();
        String fxmlFile = "/fxml/EditPositionFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        EditPositionFx controller= loader.<EditPositionFx>getController();
       controller.init(accsearchresult,this,"edit",jh);
      
        // accsearchresult,this,"rate"
        stage.showAndWait();
          }
          
        }
        log.debug("Function exit editArticle");
    }
    
    
    
    //@Subscribe
   
    
    
    
    @FXML
    private void editRates(ActionEvent event) throws IOException {
        log.debug("Function entry editRates");
        Stage stage = new Stage();
        String fxmlFile = "/fxml/CscGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        CscGuiFx controller= loader.<CscGuiFx>getController();
       controller.init(acc.getAccount().getId());
      
        // accsearchresult,this,"rate"
        stage.showAndWait();
        log.debug("Function exit editRates");
    }

    @FXML
    private void printOverview(ActionEvent event) {
        log.debug("Function entry printOverview");
        log.debug("Function exit printOverview");
    }

    @FXML
    private void createInvoice(ActionEvent event) {
        log.debug("Function entry createInvoice");
                
        BillTabs jj=new BillTabs("Temp");
        jj.createTabel(); // Create Tabel in TAB 
        billtablist.add(jj);
         TabPane gg=Account.getTabPane();
        gg.getTabs().add(jj.getBilltab());
        tempBills.add(jj.getBilltab().getText());
        log.debug("Function exit createInvoice");
                
                
    }

    @FXML
    private void closeAccount(ActionEvent event) {
        log.debug("Function entry closeAccount");
        log.debug("Function exit closeAccount");
    }

    @FXML
    private void AdvancedChargeRates(ActionEvent event) {
        log.debug("Function entry AdvancedChargeRates");
        log.debug("Function exit AdvancedChargeRates");
    }

    public void idinfo(InterAccSearchResultEvent e) {
        log.debug("Function entry idinfo");
         if(e.getTableNameofSource()=="rate"){
             log.debug("Action reaction - add article -");  
           System.out.println("Rate" + String.valueOf(e.getDbRecordId()));
           
           viewAccountPositionsProperty ml=new viewAccountPositionsProperty();
           
           ml.setDebit(false);
           ml.setcRateid(e.getDbRecordId());
           ml.setcAmount(1);
           ml.setcRateid(e.getDbRecordId());
           Rates rate=rates.getDataRecord(e.getDbRecordId());
           ml.setCpositionname(rate.getName());
           ml.setcPrice(rates.getPrice());
           datam.add(ml);
           datam.add(ml);
           datam.remove(ml);
           acc.addPosition(rate,1,rate.getPrice(),rate.getName());
             balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
        balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                 String.valueOf(acc.getSumofDebitsPos()));
       }
       if(e.getTableNameofSource()=="edit"){
                   int ik=0;
                 viewAccountPositionsProperty view=(viewAccountPositionsProperty)e.getEventObject(); 
              
                for(ik=0;datam.get(ik).getId()!=view.getId();){
                     ik++;
                }



                  datam.add(view);
                  datam.remove(view);

                  System.out.println(view.getCpositionname());
                  acc.adjustPosition(view.getId(),view.getAccountPosition());

                  balance_fxtextbox.setText(String.valueOf(acc.getBalance()));
                balance_textbox_fxtooltip.setText("Total Credits: " + String.valueOf(acc.getSumofCreditsPos()) + "\n" + "Total Debits: " + 
                         String.valueOf(acc.getSumofDebitsPos()));
                  System.err.println("After");
       }
        log.debug("Function exit idinfo");
}

    @FXML
    private void MovePosition(ActionEvent event) {
        log.debug("Function entry MovePosition" );
        for(Iterator<BillTabs> k= billtablist.listIterator();k.hasNext();){
             BillTabs ko=k.next();
                    if(ko.getBillname()==movePositionToBillChoiceBox.getValue()){
                        ObservableList<viewAccountPositionsProperty> jh=  Account_tablefx.getSelectionModel().getSelectedItems();
                        boolean haki=false;
                        
                        // Is a Position allready billed or marked for Billing?
                        for(Iterator<viewAccountPositionsProperty> olk=jh.listIterator();olk.hasNext();){
                            try {
                                if (olk.next().getBillno() == 0 || olk.next().getBillnamestring() != "") {
                                    haki = true;
                                }
                            } catch (Exception e) {
                            }
                        
                        }
                        
                        //Only if the position is not marked (has no Billno / or Billname) move to new bill and mark as be in bill processing
                        if(haki==true){
                        for (viewAccountPositionsProperty kj:jh){
                                 kj.setIsTempBill(true);
                        }
                        ko.addPositions(jh); // add all marked Position
                         for(Iterator<viewAccountPositionsProperty> lop=jh.listIterator();lop.hasNext();){
                               viewAccountPositionsProperty fou=lop.next();
                               fou.setBillnamestring(movePositionToBillChoiceBox.getValue());
                               
                         }
                        }
                        
                    }  
                   
        
        
    }
        
        log.debug("Function exit MovePosition");   
    }

    @FXML
    private void PrintAndCloseBill(ActionEvent event) {
        
        // http://www.developerscrappad.com/408/java/java-ee/ejb3-jpa-3-ways-of-generating-primary-key-through-generatedvalue/
        log.debug("Function entry PrintAndCloseBill");
        log.debug("Function exit PrintAndCloseBill");
                
                
    }

 
    
    class TempBills{
       
        
        String tempbillname;
        Bill tempbill;
        List<AccountPosition>jlk=new ArrayList<>();
        List<viewAccountPositionsProperty> jjhj=new ArrayList<viewAccountPositionsProperty>();
        
        
        public void addPos(viewAccountPositionsProperty dd,accountspositionbean hj){
            log.debug("Function entry addPos");
            jjhj.add(dd);
;            jlk.add(hj.getDataRecord(dd.getId()));
            log.debug("Function exit addPos");
        }
        
        public Iterator<viewAccountPositionsProperty> getIterator(){
            log.debug("Function entry getIterator");
            log.debug("Function exit getIterator " + String.valueOf(jjhj));  
            return jjhj.iterator();
            
        }
        
        public String getTempbillname() {
            log.debug("Function entry getTempbillname");
                    
            log.debug("Function exit getTempbillname" + String.valueOf(tempbillname));
            return tempbillname;
        }

        public void setTempbillname(String tempbillname) {
            log.debug("Function entry setTempbillname");
            this.tempbillname = tempbillname;
            log.debug("Function exit setTempbillname");
        }

        public Bill getTempbill() {
            log.debug("Function entry getTempbill");
            log.debug("Function exit getTempbill" + String.valueOf(tempbill));        
            return tempbill;
        }

        public void setTempbill(Bill tempbill) {
            log.debug("Function entry setTempbill");
            this.tempbill = tempbill;
            log.debug("Function exit setTempbill");
                    
        }

        public List<AccountPosition> getAccountPositions() {
            return jlk;
        }
        
    }
    
    
    @FXML
    private void Save(ActionEvent event) {
         log.debug("Function entry Save");
        // ######### Saves TempBill ##########
        billbean billb=new billbean();
        accountspositionbean hj= new accountspositionbean();
        
        boolean tempbillexits=false;
        List<TempBills> tempbills= new ArrayList<>();
        
        // searching for availabe tempbills
        for (viewAccountPositionsProperty kj : datam) {
            // add all position belonging to tempbilllist
            for (TempBills hhj : tempbills) {
                String jj=hhj.getTempbillname();
                if(jj==kj.getBillnamestring()){
                    hhj.addPos(kj,hj);  
                     // get pos from AccPos Table
                    tempbillexits=true;
                }
            }
            //if tempill does not exists: create one
            if(tempbillexits==false){
                if(kj.getBillno()==0){
                tempbills.add(new TempBills());
                tempbills.get(tempbills.size()-1).setTempbillname(kj.getBillnamestring());
                tempbills.get(tempbills.size()-1).addPos(kj,hj);
               
                }
            }     
        }
        // create TempBill in database, add Tempbill key to accposition
        for (TempBills hugo : tempbills) {
            billb.createNewEmptyRecord();
            billb.getLastPosition().setTemp_bill(true);
            hugo.setTempbill(billb.getLastPosition());
            billb.setBillname(hugo.getTempbillname());
            billb.setAccountPositionCollection(hugo.getAccountPositions()); // add AccPos to billCollection
            for(Iterator<viewAccountPositionsProperty>kkk=hugo.getIterator();kkk.hasNext();) {
                viewAccountPositionsProperty mg=kkk.next();
                hj.getDataRecord(mg.getId());
                hj.setBill(hugo.getTempbill().getBillno());
                
            }
        }
        // persist both tables < bill and accpos>
        billb.saveRecord();
        
        hj.saveRecord();
        log.debug("Function exit Save");
    }
     
    @FXML
    private void removePosfromBill(ActionEvent event) {
        log.debug("Function entry removePosfromBill");
       List<viewAccountPositionsProperty>kjh; 
       billbean jjj= new billbean();
       Bill jkk= new Bill(); // create an empty Bill
       jkk.setId(Long.valueOf(0));
       Tab gg=AccountTab.getSelectionModel().getSelectedItem(); 
       accountspositionbean bbo= new accountspositionbean();
       // Remove from tab
       for(Iterator<BillTabs>jfj=billtablist.iterator();jfj.hasNext();){
              BillTabs fjjf = jfj.next();
              if(fjjf.getBillname()==gg.getText()){
                    kjh=fjjf.removeSelectedPosiions();
                    for( viewAccountPositionsProperty hhh:kjh){
                            hhh.setBillnamestring("");
                            hhh.setIsTempBill(false);
                            hhh.setBillno(0);
                    }
             }
       }
        
       // Remove temp Billtag (Billname) from Acctountstab
       
       for(Iterator<viewAccountPositionsProperty>llo=datam.iterator(); llo.hasNext();){
            viewAccountPositionsProperty kj=llo.next();
            if(kj.getBillnamestring()==gg.getText() &&  kj.isIsTempBill()){
                kj.setBillnamestring("");
                kj.setIsTempBill(false);
                bbo.getDataRecord(kj.getId());
                // set Bill Key to ZeroBill
                bbo.setBill(jjj.getZeroRecord().getId());
            }
            
            
            
       }
       
       
       
       jjj.saveRecord();
       bbo.saveRecord();
       
     
        log.debug("Function exit removePosfromBill");
    }
    
}