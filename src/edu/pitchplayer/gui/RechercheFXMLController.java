/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RechercheFXMLController implements Initializable {

    @FXML
    private ListView<Terrain> fxView;
    @FXML
    private TextField fxRecherche;
    @FXML
    private VBox fxVbox;
    @FXML
    private RadioButton fxNom;
    @FXML
    private RadioButton fxAdresse;

    private TerrainService terrainService = new TerrainService();
    private ObservableList<Terrain> terrainList = FXCollections.observableArrayList();

    private ToggleGroup searchToggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configure ListView
        fxView.setItems(terrainList);
        fxView.setCellFactory(param -> new TerrainListCell());

        // Configure TextField
        fxRecherche.setPromptText("Saisissez une lettre");
        fxRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            String selectedToggleId = ((RadioButton) searchToggleGroup.getSelectedToggle()).getId();
            if (!newValue.isEmpty()) {
                try {
                    List<Terrain> terrains;
                    if (selectedToggleId.equals("fxNom")) {
                        terrains = terrainService.getTerrainsByNom(newValue);
                    } else {
                        terrains = terrainService.getTerrainsByAdresse(newValue);
                    }
                    terrainList.setAll(terrains);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                terrainList.clear();
            }
        });

        // Configure Radio Buttons
        fxNom.setToggleGroup(searchToggleGroup);
        fxAdresse.setToggleGroup(searchToggleGroup);
        fxNom.setSelected(true);
    }

    @FXML
    private void EffacerAction(ActionEvent event) {
         fxRecherche.setText("");
        
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
    private void DetailAction(ActionEvent event) {
          Terrain terrainSelectionne = fxView.getSelectionModel().getSelectedItem();
    if (terrainSelectionne != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/DetailTerrainFXML.fxml"));
            Parent root = loader.load();
            DetailTerrainFXMLController detailController = loader.getController();
            detailController.setTerrain(terrainSelectionne);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    private class TerrainListCell extends javafx.scene.control.ListCell<Terrain> {
        @Override
        protected void updateItem(Terrain terrain, boolean empty) {
            super.updateItem(terrain, empty);
            if (empty || terrain == null) {
                setText(null);
            } else {
                setText(terrain.getNom());
            }
        }
    }
}
