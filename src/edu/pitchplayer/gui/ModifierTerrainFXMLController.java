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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class ModifierTerrainFXMLController implements Initializable {

    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxAdresse;
//    private TextField fxVille;
    @FXML
    private TextField fxLongueur;
    @FXML
    private TextField fxLargeur;
//    private ToggleButton fxDisponibilite;

     private Terrain terrain;
    @FXML
    private Button fxModifier;
    @FXML
    private ImageView fxImage;
    @FXML
    private Button browseButton;
    /**
     * Initializes the controller class.
     */
      public void setTerrain(Terrain terrain) throws MalformedURLException {
        this.terrain = terrain;

        // Afficher les valeurs actuelles du terrain dans les champs de texte et la case à cocher
        fxNom.setText(terrain.getNom());
        fxAdresse.setText(terrain.getAdresse());
//        fxVille.setText(terrain.getVille());
        fxLongueur.setText(Float.toString(terrain.getLongueur()));
        fxLargeur.setText(Float.toString(terrain.getLargeur()));
//        fxDisponibilite.setSelected(terrain.isDisponible());
         String imagePath = terrain.getImagePath();
    // Vérifier si le chemin d'image est non nul ou vide avant de charger l'image
    if (imagePath != null && !imagePath.isEmpty()) {
        try {
            // Utiliser une URL pour charger l'image à partir du chemin local
            URL imageURL = new File(imagePath).toURI().toURL();
            Image image = new Image(imageURL.toString());
            fxImage.setImage(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Gérer l'erreur de chargement de l'image
            // Afficher un message d'erreur ou une image par défaut
        }
    } else {
        // Charger une image par défaut si le chemin d'image est vide ou non spécifié
        Image defaultImage = new Image(new File("C:/Users/Raouf/Desktop/3437278_5c27f.jpg").toURI().toURL().toString());
        fxImage.setImage(defaultImage);
    }
}
    @FXML
       private void ModifierAction() {
        // Mettre à jour les valeurs du terrain avec les nouvelles valeurs entrées dans les champs de texte et la case à cocher
        terrain.setNom(fxNom.getText());
        terrain.setAdresse(fxAdresse.getText());
//        terrain.setVille(fxVille.getText());
        terrain.setLongueur((int) Float.parseFloat(fxLongueur.getText()));
        terrain.setLargeur((int) Float.parseFloat(fxLargeur.getText()));
//        terrain.setDisponible(fxDisponibilite.isSelected());

        // Mettre à jour le terrain dans la base de données
//        Terrain T = new Terrain(nom, adresse,ville,longeur,largeur);
//         TerrainCRUD tc = new TerrainCRUD();
//         tc.update(T);
//        
        
        TerrainCRUD terrainCRUD = new TerrainCRUD();
        terrainCRUD.update(terrain);

        // Fermer la fenêtre
      Alert a = new Alert(Alert.AlertType.INFORMATION, "terrain Modifier avec succes",ButtonType.OK);
         a.show();
    }
       
      

    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RetourAction(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherTerrainFXML.fxml"));
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
private void ChoisirAction(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Sélectionner une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
    );

    // Afficher la boîte de dialogue de sélection de fichiers
    File selectedFile = fileChooser.showOpenDialog(fxModifier.getScene().getWindow());

    if (selectedFile != null) {
        try {
            // Charger l'image sélectionnée
            Image image = new Image(selectedFile.toURI().toURL().toString());
            fxImage.setImage(image);

            // Mettre à jour le chemin d'accès de l'image dans le terrain
            terrain.setImagePath(selectedFile.getAbsolutePath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Gérer l'erreur de chargement de l'image
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du chargement de l'image.");
            alert.showAndWait();
        }
    }
}


    
    
    
}
