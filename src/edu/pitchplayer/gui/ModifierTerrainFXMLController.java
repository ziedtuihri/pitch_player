/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainCRUD;
import java.io.IOException;
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
    @FXML
    private TextField fxVille;
    @FXML
    private TextField fxLongueur;
    @FXML
    private TextField fxLargeur;
    @FXML
    private ToggleButton fxDisponibilite;

     private Terrain terrain;
    @FXML
    private Button fxModifier;
    /**
     * Initializes the controller class.
     */
      public void setTerrain(Terrain terrain) {
        this.terrain = terrain;

        // Afficher les valeurs actuelles du terrain dans les champs de texte et la case à cocher
        fxNom.setText(terrain.getNom());
        fxAdresse.setText(terrain.getAdresse());
        fxVille.setText(terrain.getVille());
        fxLongueur.setText(Float.toString(terrain.getLongueur()));
        fxLargeur.setText(Float.toString(terrain.getLargeur()));
        fxDisponibilite.setSelected(terrain.isDisponible());
    }
    @FXML
       private void ModifierAction() {
        // Mettre à jour les valeurs du terrain avec les nouvelles valeurs entrées dans les champs de texte et la case à cocher
        terrain.setNom(fxNom.getText());
        terrain.setAdresse(fxAdresse.getText());
        terrain.setVille(fxVille.getText());
        terrain.setLongueur(Float.parseFloat(fxLongueur.getText()));
        terrain.setLargeur(Float.parseFloat(fxLargeur.getText()));
        terrain.setDisponible(fxDisponibilite.isSelected());

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

    
    
    
}
