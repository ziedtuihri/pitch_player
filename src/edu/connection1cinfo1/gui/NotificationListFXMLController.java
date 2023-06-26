/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;

import java.awt.Color;
import static java.awt.Color.BLUE;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class NotificationListFXMLController implements Initializable {

    @FXML
    private VBox idReturnNotification;
    @FXML
    private VBox idmessageContainer;
    @FXML
    private VBox idMessageNotifications;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idReturnNotification.setOnMouseClicked(e -> {
        Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("JoueurFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(VerificationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene signUpScene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(signUpScene);
        stage.setTitle("Joueur");
        stage.show();
        });
        
        
        Circle circle = new Circle(20);
        circle.setStyle("-fx-fill: blue;");
        circle.setStrokeWidth(2);
        
        Text text = new Text("JJ");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        text.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-fill: white;");    
        
        
        StackPane stackPane = new StackPane(circle, text);
        
        Hyperlink titleLabel = new Hyperlink("Message Title");
        
        titleLabel.setStyle("-fx-text-fill: #5185c5;"
                + "-fx-font-weight: bold;"
                + " -fx-font-size: 16px;"
                + "-fx-font-family: \"System\";"
                + "-fx-font-style: normal;"
                + "-fx-border-color: transparent;");

        titleLabel.setOnAction(e -> {
            Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("ConfirmNotificationFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(VerificationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Scene signUpScene = new Scene(root);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("Confirmation");
            stage.show();
        });
        Label titleLabe2 = new Label("description ... of this notification");
        
        idmessageContainer.getChildren().addAll(stackPane);
        idMessageNotifications.getChildren().addAll(titleLabel, titleLabe2);
        
        //////////////////////////////////////////
        
        Circle circle2 = new Circle(20);
        circle2.setStyle("-fx-fill: green;");
        circle2.setStrokeWidth(2);
        
        Text text2 = new Text("MT");
        text2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        text2.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-fill: white;");    
        
        
        StackPane stackPane2 = new StackPane(circle2, text2);
        
        Hyperlink titleLabel2 = new Hyperlink("Message Title");
        titleLabel2.setStyle("-fx-text-fill: #5185c5;"
                + "-fx-font-weight: bold;"
                + " -fx-font-size: 16px;"
                + "-fx-font-family: \"System\";"
                + "-fx-font-style: normal;"
                + "-fx-border-color: transparent;");
        
        titleLabel2.setOnAction(e -> {
            Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("ConfirmNotificationFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(VerificationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Scene signUpScene = new Scene(root);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("Confirmation");
            stage.show();
        });
        Label titleLabe22 = new Label("description ... of this notification");
        
        idmessageContainer.getChildren().addAll(stackPane2);
        idMessageNotifications.getChildren().addAll(titleLabel2, titleLabe22);
        
        
    }    
    
}
