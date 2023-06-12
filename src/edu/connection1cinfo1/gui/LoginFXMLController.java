/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;

import edu.connection1cinfo1.entities.Joueur;
import edu.connection1cinfo1.services.FileOperations;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
    
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    


    @FXML
    private void login(ActionEvent event) {
        String username = idUsername.getText().trim();
        String password = idPassword.getText().trim();
                String requet = "SELECT pwd FROM user WHERE username = " +username;
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {

            ResultSet rs = pst.executeQuery();
        if (rs.next()) {

            //System.out.println("Joueur modifié");
        }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
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
}
