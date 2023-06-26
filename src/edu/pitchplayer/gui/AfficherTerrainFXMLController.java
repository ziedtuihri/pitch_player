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
import static javafx.collections.FXCollections.observableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class AfficherTerrainFXMLController implements Initializable {

    @FXML
    private TableView<Terrain> fxTerrain;
//    private TableColumn<Terrain, Integer> fxId;
    @FXML
    private TableColumn<Terrain, String> fxNom;
    @FXML
    private TableColumn<Terrain, String> fxAdresse;
    @FXML
    private TableColumn<Terrain, String> fxVille;
    @FXML
    private TableColumn<Terrain, Float> fxLongeur;
    @FXML
    private TableColumn<Terrain, Float> fxLargeur;
    @FXML
    private TableColumn<Terrain, Boolean> fxDisponibilite;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TerrainCRUD terrain = new TerrainCRUD();
        fxTerrain.setItems(terrain.getTerrain());
        fxNom.setCellValueFactory(new PropertyValueFactory<Terrain,String>("nom"));
        fxAdresse.setCellValueFactory(new PropertyValueFactory<Terrain,String>("adresse"));
        fxVille.setCellValueFactory(new PropertyValueFactory<Terrain,String>("ville"));
        fxLongeur.setCellValueFactory(new PropertyValueFactory<Terrain,Float>("longueur"));
        fxLargeur.setCellValueFactory(new PropertyValueFactory<Terrain,Float>("largeur"));
        fxDisponibilite.setCellValueFactory(new PropertyValueFactory<Terrain,Boolean>("disponible"));

//        displayEntities();
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

//    @FXML
//    private void ModifierAction(ActionEvent event) {
////        Terrain terrain = fxTerrain.getSelectionModel().getSelectedItem();
////    
////    if (terrain != null) {
////        // Ouvrir une nouvelle fenêtre pour la modification
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierTerrainFXML.fxml"));
////        Parent root;
////        try {
////            root = loader.load();
////            ModifierTerrainFXMLController controller = loader.getController();
////            controller.setTerrain(terrain);
////            Scene scene = new Scene(root);
////            Stage stage = new Stage();
////            stage.setScene(scene);
////            stage.show();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//}
//
//    
//
//    @FXML
//    private void SupprimerAction(ActionEvent event) {
//    }
//   

    @FXML
    private void ModifierAction(ActionEvent event) {
          Terrain terrain = fxTerrain.getSelectionModel().getSelectedItem();
    
    if (terrain != null) {
        // Ouvrir une nouvelle fenêtre pour la modification
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierTerrainFXML.fxml"));
        Parent root;
        try {
            root = loader.load();
            ModifierTerrainFXMLController controller = loader.getController();
            controller.setTerrain(terrain);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
           Terrain terrain = fxTerrain.getSelectionModel().getSelectedItem();

    if (terrain != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce terrain ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            TerrainCRUD terrainCRUD = new TerrainCRUD();
            terrainCRUD.delete(terrain);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Suppression réussie");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Terrain supprimé avec succès");
            successAlert.showAndWait();

            // Redirect to the previous scene or close the window
            fxTerrain.getItems().clear();
            fxTerrain.getItems().addAll(terrainCRUD.getTerrain());
        }
    }
    }
}
