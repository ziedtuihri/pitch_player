/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.applet.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class AJouterTerrainFXMLController implements Initializable {

    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxAdresse;
    @FXML
    private TextField fxLongeur;
    @FXML
    private TextField fxLargeur;
    private Label stMessage;
    private Label stMessage1;
    @FXML
    private Label stMessageLongeur;
    @FXML
    private Label stMessageLargeur;
    @FXML
    private Label stMessageNom;
    @FXML
    private Label stMessageAdresse;
    @FXML
    private Button browseButton;
    WebEngine webEngine = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    
    }    

    @FXML
    private void AjoutTerrainAction(ActionEvent event) {
         String nom = fxNom.getText();
         String adresse = fxAdresse.getText();
//         float longeur = Float.parseFloat(fxLongeur.getText());
//         float largeur = Float.parseFloat(fxLargeur.getText());
        String longeurText = fxLongeur.getText();
        String largeurText = fxLargeur.getText();
         if (adresse.isEmpty()&&nom.isEmpty()&&longeurText.isEmpty()&&largeurText.isEmpty() ) {
            stMessageAdresse.setText("Veuillez saisir une adresse.");
             stMessageNom.setText("Veuillez saisir un nom.");
             stMessageLongeur.setText("Veuillez saisir une longueur.");
              stMessageLargeur.setText("Veuillez saisir une largeur.");
            return;
        }
        if (nom.isEmpty()) {
            
            stMessageNom.setText("Veuillez saisir un nom.");
            return;
        }

       
        if (longeurText.isEmpty()) {
            stMessageLongeur.setText("Veuillez saisir une longueur.");
            return;
        }

        if (largeurText.isEmpty()) {
            stMessageLargeur.setText("Veuillez saisir une largeur.");
            return;
        }

        float longeur;
        float largeur;

        try {
            longeur = Float.parseFloat(longeurText);
            largeur = Float.parseFloat(largeurText);
        } catch (NumberFormatException e) {
            stMessageLongeur.setText("La longueur doit être un nombre valide.");
             stMessageLargeur.setText("La largeur doit être un nombre valide.");
             
            return;
        }
        
    

//        try {
//            largeur = Float.parseFloat(largeurText);
//        } catch (NumberFormatException e) {
//            stMessage1.setText("La largeur doit être un nombre valide.");
//            return;
//        }


         Terrain T = new Terrain(nom, adresse,longeur,largeur);
         TerrainCRUD tc = new TerrainCRUD();
         if (tc.existsNom(nom)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le nom du terrain existe déjà dans la base de données.");
        alert.showAndWait();
        return;
    }
         tc.addEntity(T);
         Alert a = new Alert(Alert.AlertType.INFORMATION, "terrain Ajouter avec succes",ButtonType.OK);
         a.show();
            fxNom.setText("");
         fxAdresse.setText("");
         fxLongeur.setText("");
         fxLargeur.setText("");
         stMessageLargeur.setText("");
          stMessageLargeur.setText("");
           stMessageNom.setText("");
            stMessageAdresse.setText("");

    }

    @FXML
    private void AnnulerAction(ActionEvent event) {
         fxNom.setText("");
         fxAdresse.setText("");
         fxLongeur.setText("");
         fxLargeur.setText("");
         stMessageLongeur.setText("");
          stMessageLargeur.setText("");
           stMessageNom.setText("");
            stMessageAdresse.setText("");
    }

    @FXML
    private void RetourAction(ActionEvent event) {
        
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AccueilTerrainFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
          
    }

    @FXML
    private void browseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        // Set the initial directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Set the file extension filters (optional)
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // Show the dialog and wait for user input
        Stage stage = (Stage) browseButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // File selected by the user
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            // User canceled the file selection
            System.out.println("File selection canceled.");
        }
    }

    @FXML
    private void MapsAction(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MapsFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
           
    }
    
    
}
