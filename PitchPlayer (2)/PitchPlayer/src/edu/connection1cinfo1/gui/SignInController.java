/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class SignInController implements Initializable {

    @FXML
    private VBox VBox;
    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXTextField textepassword;
    @FXML
    private JFXButton btnpasswordforget;
    @FXML
    private JFXButton btnseconnecter;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    private void openHome() {
        String nom = txtusername.getText();
        String pass = textepassword.getText();
        if (nom.equals("oussama") && pass.equals("oussama")) {
            System.out.println("bien");
            VBox.getScene().getWindow().hide();
            Stage home = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../gui/Home.fxml"));
//                VBox V= new VBox();
                Scene scene = new Scene(root);
                home.setScene(scene);
                home.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("error!");
        }
    }

    @FXML
    private void send() {
    }

}
