/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.ratesbean;
import org.jahap.business.base.roomsbean;
import org.jahap.entities.Rates;
import org.jahap.entities.Rooms;
import static org.jahap.gui.AdressGuiFx.addresses;
import org.jahap.sreport.roomreports;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class RoomGuiFx implements Initializable, RoomSearchResultListener {
    @FXML
    private TextField roomcode_fxtextfield;
    @FXML
    private TextField roomname_fxtextfield;
    @FXML
    private TextField roomcategory_fxtextfield;
    
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    @FXML
    private Button search;
    @FXML
    private Button printAdress;

    private static roomsbean rooms;
    private List<TextField> textfields;
    private RoomSearchResult searchresults;
    private long ratesid=0;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textfields=new ArrayList<TextField>();
         textfields.add(roomcategory_fxtextfield);
         textfields.add(roomcode_fxtextfield);
         textfields.add(roomname_fxtextfield);
       
         rooms= new roomsbean();
         searchresults = new RoomSearchResult();
         searchresults.addIDListener(this);
         
    }    

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        rooms.nextRecordBackward();
        FillWithSelectedData();
        
        
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        rooms.nextRecordForeward();
        FillWithSelectedData();
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
    }
       @FXML
        private void printRoom(ActionEvent event) throws JRException {
         List<Rooms> adl= new ArrayList<Rooms>();
       adl= rooms.getCurrentRoom();
        
        roomreports ARP = new roomreports();
        ARP.singleRoomReport(adl);
        
    }
    
    
    public void init(long id){
        rooms = new roomsbean();
        rooms.setDataRecordId(id);
                      
              FillWithSelectedData();
        
        
        
    }

    

    private void FillWithSelectedData() {
        roomname_fxtextfield.setText(rooms.getName());
        roomcode_fxtextfield.setText(rooms.getCode());
        roomcategory_fxtextfield.setText(rooms.getCategory());
        
    }

    public void idinfo(RoomSearchResultEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void searchRoom(ActionEvent event) {
    }

  
    
}
