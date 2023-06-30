/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class ConfirmNotificationFXMLController implements Initializable {

    @FXML
    private VBox idReturnCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        idReturnCode.setOnMouseClicked(e -> {
        Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("NotificationListFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(VerificationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene signUpScene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(signUpScene);
        stage.setTitle("Confirmation");
        stage.show();
        });
        
        
        
        
        
    }    
    
}