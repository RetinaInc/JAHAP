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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.base.addressbean;
import org.jahap.entities.Address;
import org.jahap.sreport.addressreports;

 
/*
 * @author russ
 */
public class AdressGuiFx implements Initializable, AddressSearchResultListener {
    @FXML
    private Button Save;
    @FXML
    private TextField name_fxtextfield;
    
    
    @FXML
    private TextField christianname_fxtextfield;
    @FXML
    private TextField street_fxtextfield;
    @FXML
    private TextField zipcode_fxtextfield;
    @FXML
    private TextField city_fxtextfield;
    @FXML
    private TextField phoneno_fxtextfield;
    @FXML
    private TextField email_fxtextfield;
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
 
     public static addressbean addresses;
     private List<TextField> textfields;
     private AddressSearchResult searchresults;
     private long addressid=0;
     
    @FXML
    private Button printAdress;
    @FXML
    private Button newadress;

    public void initialize(URL url, ResourceBundle rb) {
         
          textfields=new ArrayList<TextField>();
        
          textfields.add(city_fxtextfield);
          textfields.add(phoneno_fxtextfield);
          textfields.add(email_fxtextfield);
          textfields.add(christianname_fxtextfield);
          textfields.add(zipcode_fxtextfield);
          textfields.add(street_fxtextfield);
          textfields.add(name_fxtextfield);
          
          
        addresses = new addressbean();
        searchresults= new AddressSearchResult();
        
        
        
       searchresults.addIDListener(this);
        
    }
    
    public void init(long id){
        addresses = new addressbean();
        addresses.setDataRecordId(id);
                      
              FillWithSelectedData();
        
        
        
    }

    @FXML
    private void save(ActionEvent event) {
        addresses.setChristianname(christianname_fxtextfield.getText());
        addresses.setCity(city_fxtextfield.getText());
        addresses.setEmail(email_fxtextfield.getText());
        addresses.setName(name_fxtextfield.getText());
        addresses.setPhone(phoneno_fxtextfield.getText());
        addresses.setStreet(street_fxtextfield.getText());
        addresses.setZipcode(zipcode_fxtextfield.getText());
        addresses.saveRecord();
        
    }

    @FXML
    private void newadress(ActionEvent event) {
        for(int i=0;i<textfields.size();i++){
            textfields.get(i).setText("");
        }
        
       
        
    }

    @FXML
    private void searchAdress(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        String fxmlFile = "/fxml/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(searchresults,this);
       
        
        stage.showAndWait();
    }

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        addresses.nextRecordBackward(); 
        FillWithSelectedData();
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        addresses.nextRecordForeward();
        FillWithSelectedData();
    }

    private void FillWithSelectedData(){
        
        
        city_fxtextfield.setText(addresses.getCity());
        email_fxtextfield.setText(addresses.getEmail()); 
        name_fxtextfield.setText(addresses.getName());
        christianname_fxtextfield.setText(addresses.getChristianname());
        phoneno_fxtextfield.setText(addresses.getPhone());
        street_fxtextfield.setText(addresses.getStreet());
        zipcode_fxtextfield.setText(addresses.getZipcode());
                
     
        
   
      
        
        
    }   

    @FXML
    private void goLastRecord(ActionEvent event) {
    }

    @FXML
    private void printAdress(ActionEvent event) throws JRException {
        List<Address> adl= new ArrayList<Address>();
        adl=addresses.getCurrentAddress();
        
        addressreports ARP = new addressreports();
        ARP.singleAdressReport(adl);
        
        
    }

       public  void idinfo(AddressSearchResultEvent e) {
          
                 //JOptionPane.showMessageDialog(null,e.getDbRecordId()+ e.getTableNameofSource() );
          if (e.getTableNameofSource()=="Address"){
              this.addressid=e.getDbRecordId();
              addresses.setDataRecordId(addressid);
                      
              FillWithSelectedData();
              
              
             
              
          }
              
         
          
        
        
    }

   
   

}
