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


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.Rates;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class EditPositionFx implements Initializable {
    @FXML
    private TextField amount_fxtextfield;
    @FXML
    private TextField Price_fxtextfield;
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
        private Button ok;
    @FXML
    private Button cancel;
    private InterAccSearchResult accm;
    @FXML
    private TextField PositionName_fxtextfield;
    private ratesbean rbean;
    @FXML
    private TextField rate_fxtextbox;
    @FXML
    private Button search_fxbutton;
    private viewAccountPositionsProperty viewPosition;
   
    private InterAccSearchResult AccSearchresult;
     private String guisource;
    @FXML
    private Button OK_editposition_fxbutton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void init(InterAccSearchResult AccSearchresults,SimpelAccountingController zi,String guisource, viewAccountPositionsProperty jm){
         
         
         this.guisource=guisource;
         this.AccSearchresult=AccSearchresults;
         rbean=new ratesbean();
         viewPosition=jm;
         if(jm.isDebit()!=true){
         Price_fxtextfield.setText(jm.getCpricestring());
         amount_fxtextfield.setText(jm.getCamountstring());
         PositionName_fxtextfield.setText(jm.getCpositionname());
         rate_fxtextbox.setText(jm.getCpositionname());
         }
         
         if(jm.isDebit()!=false){
         Price_fxtextfield.setText(jm.getDpricestring());
         amount_fxtextfield.setText(jm.getDamountstring());
         PositionName_fxtextfield.setText(jm.getDpositionname());
         rate_fxtextbox.setText(jm.getDpositionname());
         }
         
         
    }
    

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
    }

      

    @FXML
    private void Cancel(ActionEvent event) {
    }

    @FXML
    private void searchforrate(MouseEvent event) {
    }

    @FXML
    private void detailsofrate(MouseEvent event) {
    }

    @FXML
    private void OK_editposition(ActionEvent event) {
         if(viewPosition.isDebit()==false){
            viewPosition.setCpositionname(PositionName_fxtextfield.getText());
            viewPosition.setcAmount(Integer.parseInt(amount_fxtextfield.getText()));
            viewPosition.setcPrice(Double.parseDouble(Price_fxtextfield.getText()));
        }if(viewPosition.isDebit()==true){
             viewPosition.setDpositionname(PositionName_fxtextfield.getText());
            viewPosition.setdAmount(Integer.parseInt(amount_fxtextfield.getText()));
            viewPosition.setdPrice(Double.parseDouble(Price_fxtextfield.getText()));
        }
        
        AccSearchresult.setEventObj(viewPosition);
        try {
            AccSearchresult.setDbRecordId(1, guisource,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
       // EditPositionEvent hl=new EditPositionEvent(viewPosition);
        
        
        
        
       
         Stage jimbo= (Stage) OK_editposition_fxbutton.getScene().getWindow();
         
        jimbo.close();
    }
}
