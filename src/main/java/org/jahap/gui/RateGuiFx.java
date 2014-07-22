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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.Rates;
import static org.jahap.gui.AdressGuiFx.addresses;
import org.jahap.sreport.ratereports;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class RateGuiFx implements Initializable, RateSearchResultListener {
        private TextField ratecode_fxtextfield;
        private TextField ratename_fxtextfield;
        private TextField RateBasePrice_fxtextfield;
        private TextField ratevat_fxtextfield;
        private TextField RateRevAccount_fxtextfield;
    @FXML
    private Button search;
    @FXML
    private Button printAdress;

    private static ratesbean rates;
    private List<TextField> textfields;
    private RateSearchResult searchresults;
    private long ratesid=0;
        private CheckBox OvernightRate_fxCheckBox;
    @FXML
    private TitledPane x1;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textfields=new ArrayList<TextField>();
         textfields.add(ratecode_fxtextfield);
         textfields.add(ratevat_fxtextfield);
         textfields.add(ratename_fxtextfield);
         textfields.add(RateRevAccount_fxtextfield);
         textfields.add(RateBasePrice_fxtextfield);
         
         rates= new ratesbean();
         searchresults = new RateSearchResult();
         searchresults.addIDListener(this);
         
    }    


        private void goOneRecordBackward(ActionEvent event) {
        rates.nextRecordBackward();
        FillWithSelectedData();
        
        
    }

        private void goOneRecordForward(ActionEvent event) {
        rates.nextRecordForeward();
        FillWithSelectedData();
    }


    @FXML
    private void searchAdress(ActionEvent event) {
    }

    @FXML
    private void printAdress(ActionEvent event) throws JRException {
         List<Rates> adl= new ArrayList<Rates>();
       adl= rates.getCurrentRate();
        
        ratereports ARP = new ratereports();
        ARP.singleRateReport(adl);
        
    }
    
    
    public void init(long id){
        rates = new ratesbean();
        rates.setDataRecordId(id);
                      
              FillWithSelectedData();
        
        
        
    }

    public void idinfo(RateSearchResultEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void FillWithSelectedData() {
        ratename_fxtextfield.setText(rates.getName());
        ratecode_fxtextfield.setText(rates.getCode());
        RateRevAccount_fxtextfield.setText( String.valueOf(rates.getRevaccount()));
        RateBasePrice_fxtextfield.setText(String.valueOf(rates.getPrice()));
        OvernightRate_fxCheckBox.setSelected(rates.getOvernight());
        
    }
    
}
