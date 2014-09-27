/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jahap.business.base.Hotelbean;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.country;
import org.jahap.business.base.countrybean;
import org.jahap.business.base.currency;
import org.jahap.business.base.currencybean;
import org.jahap.business.base.language;
import org.jahap.business.base.languagebean;

/**
 * FXML Controller class
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class HotelSetupController implements Initializable, InterResSearchResultListener {
    @FXML
    private TextField hotelStreet;
    @FXML
    private TextField hotelZipCode;
    @FXML
    private TextField hotelCity;
    @FXML
    private ChoiceBox<String> hotelCountryChoice;
    @FXML
    private TextField hotelPhone;
    @FXML
    private TextField hotelEmail;
    @FXML
    private TextField hotelInternet;
    @FXML
    private TextField hotelFax;
    @FXML
    private TextField hotelCode;
    @FXML
    private TextField hotelName;
    @FXML
    private ChoiceBox<String> hotelLanguageChoice;
    @FXML
    private ChoiceBox<String> hotelCurrencyChoice;
    @FXML
    private TextArea footerText;
    @FXML
    private TextArea bankAccountData1;
    @FXML
    private TextArea BankAccountData2;
    @FXML
    private Button search;
    @FXML
    private Button create;
    @FXML
    private Button save;
    private addressbean addresses;
    private InterResSearchResult ressearchresult;
    private int HotelAdressId;
    private Hotelbean hbean;
    private countrybean counBean;
     private currencybean currBean;
     private languagebean  langBean;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addresses= new addressbean();
        hbean =  new Hotelbean();
         ressearchresult=new InterResSearchResult();
            ressearchresult.addIDListener(this);
        
       
         counBean = new countrybean();
        langBean = new languagebean();
        currBean = new currencybean();
         ObservableList<String> data= FXCollections.observableList(counBean.SearchForCountry(country.name));
     ObservableList<String> datal= FXCollections.observableList(langBean.SearchForCountry(language.name));   
     ObservableList<String> datac= FXCollections.observableList(currBean.SearchForCountry(currency.name));  
        hotelCountryChoice.setItems(data);
        hotelCurrencyChoice.setItems(datac);
        hotelLanguageChoice.setItems(datal);
        hbean.getDataRecord(1);
        fillDialog();
    }    

    @FXML
    private void searchAddress(ActionEvent event) throws IOException {
                Stage stage = new Stage();
        String fxmlFile = "/fxml/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(ressearchresult,this,"hoteladdress");
       
        
        stage.showAndWait();
        
    }

    @FXML
    private void createAddress(ActionEvent event) {
    }

    private void fillDialog(){
        HotelAdressId=hbean.getId();
        hotelCity.setText(hbean.getHotelAdress().getCity());
        hotelZipCode.setText(hbean.getHotelAdress().getZipcode());
        hotelName.setText(hbean.getHotelName());
        hotelStreet.setText(hbean.getHotelAdress().getStreet());
        hotelInternet.setText(hbean.getHotelAdress().getHomepage());
        hotelCode.setText(hbean.getHotelCode());
        hotelPhone.setText(hbean.getHotelAdress().getPhone());
        BankAccountData2.setText(hbean.getHotelBankaccountdata2());
        bankAccountData1.setText(hbean.getHotelBankaccountdata1());
        footerText.setText(hbean.getHotelFootertext());
        hotelCountryChoice.setValue(hbean.getHotelCountry().getCountryName());
        hotelCurrencyChoice.setValue(hbean.getHotelCurrency().getCurrencyName());
        hotelLanguageChoice.setValue(hbean.getHotelLanguage().getLanguageName());
    }
    
    @FXML
    private void saveHotelData(ActionEvent event) {
         hbean.setHotelAdress(addresses.getDataRecord(HotelAdressId));
         hbean.setHotelBankaccountdata1(bankAccountData1.getText());
         hbean.setHotelBankaccountdata2(BankAccountData2.getText());
         hbean.setHotelCode(hotelCode.getText());
         hbean.setHotelFootertext(footerText.getText());
         hbean.setHotelCountry(hotelCountryChoice.getSelectionModel().getSelectedIndex()+1);
         hbean.setHotelLanguage(hotelLanguageChoice.getSelectionModel().getSelectedIndex()+1);
         hbean.setHotelCurrency(hotelCurrencyChoice.getSelectionModel().getSelectedIndex()+1);
         hbean.setHotelName(hotelName.getText());
         hbean.saveRecord();
    }

    @Override
    public void idinfo(InterResSearchResultEvent e) {
        if(e.getTableNameofSource()=="hoteladdress"){
                 hotelName.setText(addresses.getDataRecord(e.getDbRecordId()).getName());
                 hotelCity.setText(addresses.getDataRecord(e.getDbRecordId()).getCity());
                 hotelEmail.setText(addresses.getDataRecord(e.getDbRecordId()).getEmail());
                 hotelInternet.setText(addresses.getDataRecord(e.getDbRecordId()).getHomepage());
                 hotelPhone.setText(addresses.getDataRecord(e.getDbRecordId()).getPhone());
                 hotelStreet.setText(addresses.getDataRecord(e.getDbRecordId()).getStreet());
                 hotelZipCode.setText(addresses.getDataRecord(e.getDbRecordId()).getZipcode());
                 hotelCountryChoice.setValue(addresses.getDataRecord(e.getDbRecordId()).getCountry().getCountryName());
                 hotelCurrencyChoice.setValue(addresses.getDataRecord(e.getDbRecordId()).getCurrency().getCurrencyName());
                  hotelLanguageChoice.setValue(addresses.getDataRecord(e.getDbRecordId()).getLanguage().getLanguageName());
                  
                  HotelAdressId=(int) e.getDbRecordId();
        }  
        
        
        
    }
    
}
