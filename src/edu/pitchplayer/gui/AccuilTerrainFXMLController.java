/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.TerrainCount;
import edu.pitchplayer.services.TerrainCRUD;
import edu.pitchplayer.services.TerrainService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class AccuilTerrainFXMLController implements Initializable {

   @FXML
    private TableView<TerrainCount> fxVilleNbr;
    @FXML
    private TableColumn<TerrainCount, String> fxVille;
    @FXML
    private TableColumn<TerrainCount, Integer> fxNombre;

    private TerrainService terrainService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          terrainService = new TerrainService();
        displayTerrainCountByVille();
      

    }    

    private void displayTerrainCountByVille() {
        List<TerrainCount> terrainCounts = terrainService.getTerrainCountByVille();

        ObservableList<TerrainCount> terrainCountList = FXCollections.observableArrayList(terrainCounts);

        fxVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        fxNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        fxVilleNbr.setItems(terrainCountList);
    }

    @FXML
    private void AjouterTerrainAction(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterTerrainFXML.fxml"));
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
    private void AfficherTerrainAction(ActionEvent event) {
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
    private void RechercheTerainAction(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RechercheFXML.fxml"));
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
