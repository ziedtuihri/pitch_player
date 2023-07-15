/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Reclamation;
import edu.pitchplayer.services.ReclamationCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Access
 */
public class AffichageReclamationController implements Initializable {

    @FXML
    private Label comptel;
    @FXML
    private Label datel;
    @FXML
    private Label sujetl;
    @FXML
    private Label objetl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
     
     

    }    

    @FXML
    private void retourmenu(ActionEvent event) {
    }
    
}
