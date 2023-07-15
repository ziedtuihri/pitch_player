/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class MainController implements Initializable {

    @FXML
    private VBox VBox;
    @FXML
    private JFXButton btnseconnecter;
    @FXML
    private JFXButton bntinscrire;
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), VBox);
        t.setToX(VBox.getLayoutX() * 5.5);
        t.play();
         try {
                Parent root = FXMLLoader.load(getClass().getResource("../gui/SignIn.fxml"));
                VBox.getChildren().removeAll();
                VBox.getChildren().setAll(root);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

    }

    @FXML
    private void open() {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), VBox);
        t.setToX(VBox.getLayoutX() * 5.5);
        t.play();
         try {
                Parent root = FXMLLoader.load(getClass().getResource("../gui/SignIn.fxml"));
                VBox.getChildren().removeAll();
                VBox.getChildren().setAll(root);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    @FXML
    private void openSignUp() {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), VBox);
        t.setToX(5);
        t.play();
         try {
                Parent root = FXMLLoader.load(getClass().getResource("../gui/SignUp.fxml"));
                VBox.getChildren().removeAll();
                VBox.getChildren().setAll(root);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

}
