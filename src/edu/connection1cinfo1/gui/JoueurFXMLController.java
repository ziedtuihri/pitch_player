package edu.connection1cinfo1.gui;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class JoueurFXMLController implements Initializable {

    @FXML
    private ImageView idReturnJoueur;
    @FXML
    private HBox idAjoutEquipe;
    @FXML
    private HBox idRechercheEquipe;
    @FXML
    private HBox idUpdateProfile;
    @FXML
    private HBox idShowMatch;
    @FXML
    private ImageView idImageProfile;
    @FXML
    private VBox idRtnJoueur;
    
    private ImageView idNotificationRed;
    @FXML
    private ImageView idNotificationNull;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
     
        
        
        
         // Load the new image
        Image newImage = new Image(getClass().getResourceAsStream("../../../img/notificationJJJ.png"));

        // Set the new image to the image view
        idNotificationNull.setImage(newImage);
        
        
        idRtnJoueur.setOnMouseClicked(e -> {
        Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(VerificationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene signUpScene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(signUpScene);
        stage.setTitle("Login");
        stage.show();
        });
        
        idAjoutEquipe.setOnMouseClicked(e -> {
            
        });
          
        idRechercheEquipe.setOnMouseClicked(e -> {

        });
            
        idUpdateProfile.setOnMouseClicked(e -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("JoueurUpdateFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(VerificationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene signUpScene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(signUpScene);
        stage.setTitle("Update");
        stage.show();
        });
              
        idShowMatch.setOnMouseClicked(e -> {

        });
       
    }   
    
    
    public void checkNotification(){
        
    }
    
 
    
}
