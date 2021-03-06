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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.base.Choicebean;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.choicegroups;
import org.jahap.business.base.country;
import org.jahap.business.base.countrybean;
import org.jahap.business.base.currency;
import org.jahap.business.base.currencybean;
import org.jahap.business.base.language;
import org.jahap.business.base.languagebean;
import org.jahap.entities.Address;
import org.jahap.entities.Country;
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
     private countrybean counBean;
     private currencybean currBean;
     private languagebean  langBean;
     private Choicebean choicebean;
     
    @FXML
    private Button printAdress;
    @FXML
    private Button newadress;
    @FXML
    private ChoiceBox<String> CountryChoiceBox;
    @FXML
    private ChoiceBox<String> CurrencyChoiceBox;
    @FXML
    private ChoiceBox<String> LanguageChoiceBox;
    @FXML
    private TextField homepage;
    @FXML
    private ComboBox<String> titel;
    @FXML
    private ComboBox<String> greeting;
    @FXML
    private ComboBox<String> salutation;
    @FXML
    private ChoiceBox<String> addresstype;
    @FXML
    private TextArea remarks;

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
        counBean = new countrybean();
        langBean = new languagebean();
        currBean = new currencybean();
        choicebean= new Choicebean();
        
     ObservableList<String> data= FXCollections.observableList(counBean.SearchForCountry(country.name));
     ObservableList<String> datal= FXCollections.observableList(langBean.SearchForCountry(language.name));   
     ObservableList<String> datac= FXCollections.observableList(currBean.SearchForCountry(currency.name));  
       ObservableList<String> datap= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.title)); 
       ObservableList<String> dataz= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.addresstype)); 
       ObservableList<String> dataui= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.greeting)); 
       ObservableList<String> dataop= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.salutation)); 
       titel.setItems(datap);
       CountryChoiceBox.setItems(data);
        LanguageChoiceBox.setItems(datal);
        CurrencyChoiceBox.setItems(datac);
        addresstype.setItems(dataz);
        greeting.setItems(dataui);
        salutation.setItems(dataop);
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
        addresses.setCountry(CountryChoiceBox.getSelectionModel().getSelectedIndex()-1);
        addresses.setCurrency(CurrencyChoiceBox.getSelectionModel().getSelectedIndex()-1);
        addresses.setLanguage(LanguageChoiceBox.getSelectionModel().getSelectedIndex()-1);
        addresses.setTitel(titel.getValue());
        addresses.setAddresstype(addresstype.getValue());
        addresses.setGreeting(greeting.getValue());
        addresses.setSalutation(salutation.getValue());
        addresses.saveRecord();
        
    }

    @FXML
    private void newadress(ActionEvent event) {
        for(int i=0;i<textfields.size();i++){
            textfields.get(i).setText("");
        }
        addresses.createNewEmptyRecord();
       
        
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
         CountryChoiceBox.setValue(addresses.getCountry().getCountryName());       
       LanguageChoiceBox.setValue(addresses.getLanguage().getLanguageName());
        CurrencyChoiceBox.setValue(addresses.getCurrency().getCurrencyName());
        titel.setValue(addresses.getTitel());
        greeting.setValue(addresses.getGreeting());
        addresstype.setValue(addresses.getAddresstype());
        salutation.setValue(addresses.getSalutation());
        
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
