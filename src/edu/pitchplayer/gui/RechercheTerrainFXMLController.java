/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class RechercheTerrainFXMLController implements Initializable {

    @FXML
    private TableColumn<Terrain, String> fxNom;
    @FXML
    private TableView<Terrain> fxRecherche;
    private TerrainService terrainService;
    @FXML
    private ComboBox<String> FXNomSuggestion;
    @FXML
    private TableColumn<Terrain, String> fxAdresse;
    @FXML
    private TextField fxRechercheNom;
    
    private ObservableList<Terrain> terrainsList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        terrainService = new TerrainService();
        initTableView();
    }
    
    private void initTableView() {
        fxNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        fxAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        fxRecherche.setItems(terrainsList);
    }

    @FXML
    private void RechercheAction(ActionEvent event) throws SQLException {
        String lettreRecherchee = fxRechercheNom.getText();
        if (!lettreRecherchee.isEmpty()) {
            List<Terrain> terrains = terrainService.getTerrainsByLettre(lettreRecherchee);
            terrainsList.setAll(terrains);
        } else {
            terrainsList.clear();
        }
    }
}
   

    
    

