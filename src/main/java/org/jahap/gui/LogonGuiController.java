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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jahap.CurrentUser;
import org.jahap.MainEventResult;
import org.jahap.entities.JahapDatabaseConnector;

/**
 * FXML Controller class
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class LogonGuiController implements Initializable {
    @FXML
    private Button LoginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField loginName;
    @FXML
    private PasswordField password;
    
    private MainEventResult mEv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void init(MainEventResult mEv){
        
        this.mEv=mEv;
        
    }
    
    @FXML
    private void loginEvent(ActionEvent event) {
        
        JahapDatabaseConnector hhh=JahapDatabaseConnector.getConnector(loginName.getText(), password.getText());
           
         Stage jimbo= (Stage) LoginButton.getScene().getWindow();
         boolean admin=false;
         if(loginName.getText().equals("root")) admin=true;
         CurrentUser hh = CurrentUser.getCurrentUser(loginName.getText(), admin);
         
         this.mEv.setDbRecordId(true, "WW");
        jimbo.close();
        
    }

    @FXML
    private void cancelAction(ActionEvent event) {
    }
    
}
