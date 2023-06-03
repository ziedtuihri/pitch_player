/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.gui;

import edu.connection1cinfo1.entities.Terrain;
import edu.connection1cinfo1.services.TerrainCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class AjouterTerrainFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfVille;
    @FXML
    private TextField tfDimensions;
    @FXML
    private TextField tfEquipements;
    @FXML
    private TextField tfDisponibilite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterTerrainAction(ActionEvent event) {
        String nom = tfNom.getText();
         String adresse = tfAdresse.getText();
         String ville = tfVille.getText();
         String dimensions = tfDimensions.getText();
         String equipement = tfEquipements.getText();
         String disponibilite = tfDisponibilite.getText();
         
         Terrain T = new Terrain(nom, adresse,ville,dimensions,equipement,disponibilite);
         TerrainCRUD tc = new TerrainCRUD();
         tc.addEntity(T);
         Alert a = new Alert(Alert.AlertType.INFORMATION, "terrain Ajouter avec succes",ButtonType.OK);
         a.show();
             tfNom.setText("");
             tfAdresse.setText("");
              tfVille.setText("");
             tfDimensions.setText("");
              tfEquipements.setText("");
             tfDisponibilite.setText("");
             
         
         
        
    }
    
}
