/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import com.google.common.eventbus.EventBus;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.ratesbean;
import org.jahap.business.base.roomsbean;
import org.jahap.business.res.occbean;
import org.jahap.business.res.resbean;
import org.jahap.entities.Occ;
import extfx.scene.control.*;
import extfx.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.acc.cscbean;
import org.jahap.entities.Accounts;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class ResguiController implements Initializable, InterResSearchResultListener {
    private CalendarView clview;
    @FXML
    private DatePicker datapickerFrom;
    @FXML
    private DatePicker datapickerTo;
    @FXML
    private Label DASH_ResOrderer_txt;
    @FXML
    private Font x1;
    @FXML
    private Label DASH_ResNo_fxtxt;
    @FXML
    private Label DASH_ResArrival_fxtxt;
    @FXML
    private Font x2;
    @FXML
    private Label DASH_ResState_fxtxt;
    @FXML
    private Label DASH_ResPax_fxtxt;
    @FXML
    private Label DASH_ResDeparture_fxtxt;
    @FXML
    private TextField Orderer_Name_fxtxtfield;
    @FXML
    private TextField Orderer_FirstName_fxtxtfield;
    @FXML
    private TextField Orderer_Street_fxtxtfield;
    @FXML
    private TextField Orderer_ZipCode_fxtxtfield;
    @FXML
    private TextField Orderer_City_fxtxtfield;
    @FXML
    private TextField Orderer_Country_fxtxtfield;
    @FXML
    private Button Orderer_Seach_fxbuton;
    @FXML
    private Button Orderer_Details_fxbutton;
    @FXML
    private TextField Guest_firstName_fxtxtfield;
    @FXML
    private TextField Guest_Country_fxtxtfield;
    @FXML
    private TextField Guest_City_fxtxtfield;
    @FXML
    private TextField Guest_Street_fxtxtfield;
    @FXML
    private TextField Guest_ZipCode_fxtxtfield;
    @FXML
    private TextField Guest_Name_fxtxtfield;
    @FXML
    private Button Guest_Search_fxbutton;
    @FXML
    private Button Guest_Details_fxbutton;
    @FXML
    private TextField Room_Code_fxtxtfield;
    @FXML
    private Button Room_Search_fxbutton;
    @FXML
    private TextField ACC_No_fxtxtfield;
    @FXML
    private TextField ACC_Balance_fxtxtfield;
    @FXML
    private Button ACC_OpenAcc_fxbutton;
    @FXML
    private Button Save_fxbutton;
    @FXML
    private Button Exit_fxbutton;
    @FXML
    private Button Print_fxbutton;
    @FXML
    private Button Search_fxbutton;
    @FXML
    private Button Toolbox_FirstRecord_fxButton;
    @FXML
    private Button Toolbox_BackwardRecord_fxbutton;
    @FXML
    private ChoiceBox<?> Toolbox_Sorting_fxchoicebox;
    @FXML
    private Button Toolbox_ForewardRecord_fxbutton;
    @FXML
    private Button Toolbox_LastRecord_fxbutton;
    
    private InterResSearchResult ressearchresult;
    private resbean res;
    private ratesbean rate;
    private occbean occ;
    private roomsbean room;
    private addressbean address;
    private accountsbean accs;
    private cscbean cscs;
    @FXML
    private Font x3;
    @FXML
    private Button New_fxbutton;
    @FXML
    private TextField RATE_Name_fxtxtfield;
    @FXML
    private Button RATE_Search_fxbutton;
    @FXML
    private Button RATE_Details_fxtxtfield;
    @FXML
    private Label DASH_ResNewCreated_fxtxt;
    private EventBus eventbus;
    /**
     * Initializes the controller class.
     */
     private long roomid=0;
     private long ordererid=0;
     private long guestid=0;
     private long rateid=0;
     private long accountid=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Search_Orderer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(ressearchresult,this,"orderaddress");
       
        
        stage.showAndWait();
        
        
    }

    @FXML
    private void Details_Orderer(ActionEvent event) throws IOException {
           Stage stage = new Stage();
        String fxmlFile = "/fxml/AdressGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        AdressGuiFx controller= loader.<AdressGuiFx>getController();
       controller.init(ordererid);
       
        
        stage.showAndWait();
        
        
        
        
    }

    @FXML
    private void Search_Guest(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(ressearchresult,this,"guestaddress");
       
        
        stage.showAndWait();
        
    }

    @FXML
    private void Details_guest(ActionEvent event) throws IOException {
        
         Stage stage = new Stage();
        String fxmlFile = "/fxml/AdressGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        AdressGuiFx controller= loader.<AdressGuiFx>getController();
       controller.init(guestid);
       
        
        stage.showAndWait();
        
    }

    @FXML
    private void Search_room(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        String fxmlFile = "/fxml/RoomList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        RoomListController controller= loader.<RoomListController>getController();
       controller.init(ressearchresult,this,"rooms");
       
        
        stage.showAndWait();
    }

    @FXML
    private void Open_Account(ActionEvent event) throws IOException {
        

        
         Stage stage = new Stage();
        String fxmlFile = "/fxml/simpelAccounting.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        SimpelAccountingController controller= loader.<SimpelAccountingController>getController();
       controller.init(accountid);
       
       
        stage.showAndWait();
        
        
    }

    @FXML
    private void Save(ActionEvent event) {
       // only for changing Res
        Date SaveFromDate=occ.getArrivaldate();
        Date SaveToDate=occ.getDeparturedate();
        long SaveRoomId=occ.getRoom().getId();
        long SaveAddressId=occ.getGuest().getId();
      
         res.setAddresses(address.getDataRecord(ordererid));  // Set Addressrecord
                      

                     

                      
                      
                    
        
        
        
        if(datapickerFrom.getValue()!=occ.getArrivaldate()){
           
            occ.setArrivaldate(datapickerFrom.getValue());
        
        }
        if(datapickerTo.getValue()!=occ.getDeparturedate()){
          
            occ.setDeparturedate(datapickerTo.getValue());
        }
        if(roomid!=occ.getRoom().getId()){
            
            occ.setRoom(room.getDataRecord(roomid));
        
        }
        if(guestid!=occ.getGuest().getId()){
            
            occ.setGuest(address.getDataRecord(guestid));
        
        }
        
        if(rateid!=cscs.getRate().getId()){
             cscs.setRate(rate.getDataRecord(rateid));
        }
        
        
         List<String>overlaps=new ArrayList<String>();  
         overlaps=occ.CheckForOverlappingReservations();  
         if(overlaps==null){
             overlaps=occ.saveRecord(true);
             res.saveRecord();
             cscs.saveRecord();
             System.out.println("Ok");
         }
         
          if(overlaps!=null){
                        if(overlaps.size()>=1){
                            String Test=overlaps.get(1);
                            int i;
                            for (i=0;i==overlaps.size();i++){
                               Test=Test+ ", " +overlaps.get(i);
                            }
                            
                           int iol=  JOptionPane.showOptionDialog(null, Test, "Rooms is Occupied by",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,null,new String[]{"create Reservation","drop Reservation","Cancel"},"Cancel");
                            
                               switch(iol ){
                                   case 0:{
                                      
                                        res.setArrivaldate(occ.getArrivaldate().toString());
                                        res.setDeparturedate(occ.getDeparturedate().toString());
                                        res.saveRecord();
                                        cscs.saveRecord();
                                       System.out.println("res adjusted");
                                       
                                   }
                                   case 1:{
                                       System.out.println("res removed");
                                       occ.setArrivaldate(SaveFromDate);
                                       occ.setDeparturedate(SaveToDate);
                                       occ.setRoom(room.getDataRecord(SaveRoomId));
                                       occ.setGuest(address.getDataRecord(SaveAddressId));
                                       res.setArrivaldate(occ.getArrivaldate().toString());
                                        res.setDeparturedate(occ.getDeparturedate().toString());
                                         overlaps=occ.saveRecord(true);
                                         res.saveRecord();
                                         cscs.saveRecord();
                                   }
                                   case 2:{
                                        System.out.println("res");
                                        
                                   }
                               }
                        }
               }
    }

    @FXML
    private void Exit(ActionEvent event) {
    }

    @FXML
    private void Print(ActionEvent event) {
    }

    @FXML
    private void Search(ActionEvent event) {
    }

    @FXML
    private void Toolbox_FirstRecord(ActionEvent event) {
    }

    @FXML
    private void Toolbox_BackwardRecord(ActionEvent event) {
        res.nextRecordBackward();
        FillWithSelectedData();
    }

    @FXML
    private void Toolbox_ForewardRecord(ActionEvent event) {
        res.nextRecordForeward();
        FillWithSelectedData();
        
    }

    @FXML
    private void Toolbox_LastRecord(ActionEvent event) {
    }
    
    public void init(long id){

        
        
        res = new resbean();
        address=new addressbean();
        occ=new occbean();
        room=new roomsbean();
        rate=new ratesbean();
        accs=new accountsbean();
        cscs=new cscbean();
        ressearchresult=new InterResSearchResult();
        res.setDataRecordId(id);
                      ressearchresult.addIDListener(this);
              FillWithSelectedData();
        
        
        
    }

    private void FillWithSelectedData() {
        // init DASH Board
        DASH_ResArrival_fxtxt.setText(res.getArrivaldate());
        DASH_ResDeparture_fxtxt.setText(res.getDeparturedate());
        DASH_ResNo_fxtxt.setText(res.getResno());
        DASH_ResOrderer_txt.setText(res.getAddresses().getName());
        DASH_ResPax_fxtxt.setText("1"); //DEV : Paximplenetion
        DASH_ResState_fxtxt.setText("Definitv"); //DEV : State implemnetation
        
        // init Order infos
        fillOrderer();
        
        //DEV :Init Guest infos
       
        fillGuest();
        //Res
        Occ gh=new Occ();
        gh=occ.SearchForOccforRes(res.GetCurrentRes()).get(0);
        roomid=occ.getRoom().getId();
        Room_Code_fxtxtfield.setText(gh.getRoom().getCode()+" "+gh.getRoom().getName());
        
        //RATE_Name_fxtxtfield.setText(;
        fillDates();
        
       
        // ACC
        
        
        ACC_Balance_fxtxtfield.setText("33");
        accs.moveToRecordwithRes(res.GetCurrentRes());
        Accounts hhf=new Accounts();
        hhf=accs.getAccount();
        cscs.moveToRecordwithAccount(accs.getAccount());
        RATE_Name_fxtxtfield.setText(cscs.getRate().getCode() + "  " + cscs.getRate().getName());
        ACC_No_fxtxtfield.setText(accs.getId().toString() + " " + accs.getAddress().getName() );
        this.rateid=cscs.getRate().getId();
        this.accountid=accs.getId();
       
    }

    private void fillOrderer(){
         Orderer_Name_fxtxtfield.setText(res.getAddresses().getName());
        Orderer_FirstName_fxtxtfield.setText(res.getAddresses().getChristianname());
        Orderer_Street_fxtxtfield.setText(res.getAddresses().getStreet());
        Orderer_ZipCode_fxtxtfield.setText(res.getAddresses().getZipcode());
        Orderer_City_fxtxtfield.setText(res.getAddresses().getCity());
        ordererid=res.getAddresses().getId();
    }
    
    private void fillOrderer(long addressid){
        
         Orderer_Name_fxtxtfield.setText(address.getDataRecord(addressid).getName());
        Orderer_FirstName_fxtxtfield.setText(address.getDataRecord(addressid).getChristianname());
        Orderer_Street_fxtxtfield.setText(address.getDataRecord(addressid).getStreet());
        Orderer_ZipCode_fxtxtfield.setText(address.getDataRecord(addressid).getZipcode());
        Orderer_City_fxtxtfield.setText(address.getDataRecord(addressid).getCity());
        ordererid=addressid;
    }
    
    
    private void fillGuest(){
         Guest_Name_fxtxtfield.setText(occ.getGuest().getName());
        Guest_firstName_fxtxtfield.setText(occ.getGuest().getChristianname());
        Guest_Street_fxtxtfield.setText(occ.getGuest().getStreet());
        Guest_ZipCode_fxtxtfield.setText(occ.getGuest().getZipcode());
        Guest_City_fxtxtfield.setText(occ.getGuest().getCity());
        guestid=occ.getGuest().getId();
              
    }
    
    private void fillGuest(long addressid){
         Guest_Name_fxtxtfield.setText(address.getDataRecord(addressid).getName());
        Guest_firstName_fxtxtfield.setText(address.getDataRecord(addressid).getChristianname());
        Guest_Street_fxtxtfield.setText(address.getDataRecord(addressid).getStreet());
        Guest_ZipCode_fxtxtfield.setText(address.getDataRecord(addressid).getZipcode());
        Guest_City_fxtxtfield.setText(address.getDataRecord(addressid).getCity());
       guestid=addressid;
    }
    
    private void fillRoom(long roomid){
        
        Room_Code_fxtxtfield.setText(room.getDataRecord(roomid).getCode()+ " " + room.getDataRecord(roomid).getName());
        this.roomid=roomid;
    }
    
     private void fillRate(long rateid){
        RATE_Name_fxtxtfield.setText(rate.getDataRecord(rateid).getCode()+ " " + rate.getDataRecord(rateid).getName());
        this.rateid=rateid;
     }
     
     
     
     private void fillDates(){
         datapickerFrom.setValue(occ.getArrivaldate());
         datapickerTo.setValue(occ.getDeparturedate());
     }
    
    public void idinfo(InterResSearchResultEvent e) {
     if(e.getTableNameofSource()=="orderaddress"){
         fillOrderer(e.getDbRecordId());
     }   
     
     if(e.getTableNameofSource()=="guestaddress"){
           fillGuest(e.getDbRecordId());
     }
     if(e.getTableNameofSource()=="rooms"){
           fillRoom(e.getDbRecordId());
     }
     
     if(e.getTableNameofSource()=="rate"){
           fillRate(e.getDbRecordId());
     }
     
     
    }

    @FXML
    private void New(ActionEvent event) {
    }

    @FXML
    private void SearchForRate(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/RatesList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        RateListController controller= loader.<RateListController>getController();
       controller.init(ressearchresult,this,"rate");
       
        
        stage.showAndWait();
    }

    @FXML
    private void RateDetails(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/RateGuiFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        RateGuiFx controller= loader.<RateGuiFx>getController();
       controller.init(rateid);
       
        
        stage.showAndWait();
        
        
    }

    @FXML
    private void Action_From_Date(ActionEvent event) {
       
    }

    @FXML
    private void Action_To_Date(ActionEvent event) {
    }
    
}
