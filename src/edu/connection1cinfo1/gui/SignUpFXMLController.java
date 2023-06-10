/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class SignUpFXMLController implements Initializable {

    @FXML
    private TextField idEmail;
    @FXML
    private TextField idNom;
    @FXML
    private TextField idPrenom;
    @FXML
    private TextField idPassword;
    @FXML
    private TextField idAdresse;
    @FXML
    private TextField idTelephone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Save(ActionEvent event) {
    }
    
}
