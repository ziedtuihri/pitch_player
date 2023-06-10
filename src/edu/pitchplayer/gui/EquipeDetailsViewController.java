/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Equipe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author WIJDEN
 */
public class EquipeDetailsViewController implements Initializable {

    @FXML
    private Label nomLabel;
    @FXML
    private Label anneeFondationLabel;
    @FXML
    private Label nomCapitaineLabel;
    @FXML
    private Button joinButton;
    private Equipe equipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
        updateView();
    }

    private void updateView() {
        if (equipe != null) {
            nomLabel.setText("Nom: " + equipe.getNom());
            anneeFondationLabel.setText("Année de Fondation: " + equipe.getAnneeFondation());
            nomCapitaineLabel.setText("Nom du Capitaine: " + equipe.getNom());
        }
    }

}
