/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.gui;

import edu.connection1cinfo1.entities.Tournoi;
import edu.connection1cinfo1.services.TournoiCRUD;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class AjouterTournoiFXMLController implements Initializable {

    
    @FXML
    private DatePicker fxDateF;
    @FXML
    private TextField fxNomTournoi;
    @FXML
    private TextField fxNombreP;
    @FXML
    private TextField fxLieu;
    @FXML
    private DatePicker fxDateD;
    @FXML
    private Label stMessage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterTournoiAction(ActionEvent event) {
        String nom = fxNomTournoi.getText().trim();
        String nbrParticipant = fxNombreP.getText().trim();
        String lieu = fxLieu.getText();
        LocalDate dateD = fxDateD.getValue();
        
       // System.out.println(dateD);
                
                
        LocalDate dateF = fxDateF.getValue();
        //System.out.println(dateF);
        
      

        
        if (nom.isEmpty()) {
            stMessage.setText("Please input name.");
            return;
        }

        Tournoi T = new Tournoi(nom, nbrParticipant, dateD, dateF, lieu);

        TournoiCRUD tc = new TournoiCRUD();
        tc.addEntity(T);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "tournoi  Ajouter avec succes", ButtonType.OK);
        a.show();
        fxNomTournoi.setText("");
        fxNombreP.setText("");
        fxLieu.setText("");
        

    }

}
