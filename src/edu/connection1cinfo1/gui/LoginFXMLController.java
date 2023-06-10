/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;

import edu.connection1cinfo1.services.FileOperations;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import javafx.util.Pair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField idUsername;
    @FXML
    private TextField idPassword;
    @FXML
    private Text idMessage;
    @FXML
    private Button idLogin;
    @FXML
    private Button idSignUp;
    @FXML
    private Text idForgotPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    


    @FXML
    private void login(ActionEvent event) {
        String username = idUsername.getText().trim();
        String password = idPassword.getText().trim();
        
        
    }

    @FXML
    private void signUp(ActionEvent event) {
        
    }
    
}
