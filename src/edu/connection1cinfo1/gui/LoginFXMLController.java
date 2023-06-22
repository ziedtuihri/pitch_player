/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;


import edu.connection1cinfo1.services.UserCRUD;
import edu.connection1cinfo1.tests.FXMain;
import edu.connection1cinfo1.utils.MyConnection;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
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
    private Hyperlink idForgotPass;

    /**
     * Initializes the controller class.
     */
    
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Parent signUpRoot = null;
        try {
            signUpRoot = FXMLLoader.load(getClass().getResource("VerificationFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SignUpFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Scene signUpScene = new Scene(signUpRoot);
           // Set the action to be performed when the link is clicked
        idForgotPass.setOnAction(e -> {

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("Forget Password");
            stage.show();
            System.out.println("Returning to Login page...");
        });
        
    }    


    @FXML
    private void login(ActionEvent event) throws IOException {
        String username = idUsername.getText().trim();
        String password = idPassword.getText().trim();
        String hashedPassword;
        UserCRUD userCRUD = new UserCRUD();
        
        if (username.isEmpty() || username.length() < 2) {
            alertFN("Be carful", "Invalid username !!!");
            return;
        }

        if (password.isEmpty() || password.length() < 2) {
            alertFN("Be carful", "Invalid password !!!");
            return;
        }
        hashedPassword = userCRUD.checkAccountExists(username, username);
        
        if(hashedPassword == "null") {
            alertFN("Be carful", "Invalid Account !!!");
        }else
        if (BCrypt.checkpw(password, hashedPassword)) {
                   Parent signUpRoot = FXMLLoader.load(getClass().getResource("JoueurFXML.fxml"));
                    Scene signUpScene = new Scene(signUpRoot);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(signUpScene);
                    stage.setTitle("Sign Up");
                    stage.show();
            } else {
               alertFN("Be carful", "Invalid username or password !!!");
            }
    }

    @FXML
    private void signUp(ActionEvent event) throws IOException {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("SignUpFXML.fxml"));
        Scene signUpScene = new Scene(signUpRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(signUpScene);
        stage.setTitle("Sign Up");
        stage.show();
    }
    
    public void alertFN(String msg1, String msg2) {
         Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(msg1);//msg1
                alert.setContentText(msg2);//msg2

                alert.showAndWait();
    }
}
