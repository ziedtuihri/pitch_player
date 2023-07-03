/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class DetailTerrainFXMLController implements Initializable {

    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxVille;
    @FXML
    private TextField fxAdresse;
    @FXML
    private TextField fxLongeur;
    @FXML
    private TextField fxLargeur;
 private Terrain terrain;
    /**
     * Initializes the controller class.
     */
 
  public void setTerrain(Terrain terrain) {
 this.terrain = terrain;


     fxNom.setText(terrain.getNom());
        fxAdresse.setText(terrain.getAdresse());
        fxVille.setText(terrain.getVille());
        fxLongeur.setText(String.valueOf(terrain.getLongueur()));
    fxLargeur.setText(String.valueOf(terrain.getLargeur()));
       
    }
        
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
    }    

    @FXML
    private void RetourAction(ActionEvent event) {
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
