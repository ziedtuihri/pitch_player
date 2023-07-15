/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Reclamation;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.services.ReclamationCRUD;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Access
 */
public class AddReclamController implements Initializable {

    @FXML
    private TextField datef;
    @FXML
    private ComboBox Topich;
    @FXML
    private TextArea objecttf;
    @FXML
    private Label choixla;

    /**
     * Initializes the controller class.
     */
     @FXML
    private void selectchoix(ActionEvent event) {
        
        String s = Topich.getSelectionModel().getSelectedItem().toString();
        choixla.setText(s);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        datef.setText(new Date().toString());
        ObservableList<String> list = FXCollections.observableArrayList("Disponibilité Terrain", "Materiel", "deroulement de tournoi");
        Topich.setItems(list);
        // TODO
    }    

    @FXML
    private void addreclam(ActionEvent event) {
        
        String sujet = choixla.getText();
        String date = datef.getText();
        String objet = objecttf.getText();
        
        Reclamation r = new Reclamation(date, sujet, objet, Reclamation.ReclamationStatut.Closed, User, Reclamation.NotificationStatut.Notified, date);

        ReclamationCRUD rcd = new ReclamationCRUD();

        if (choix.getText().isEmpty()) {
            Alert b = new Alert(Alert.AlertType.INFORMATION, "le choix est vide !", ButtonType.OK);
            b.show();
        }

        if (datetf.getText().isEmpty()) {
            Alert b = new Alert(Alert.AlertType.INFORMATION, "le champ date est vide");
            b.show();
        }
        if (objettf.getText().isEmpty()) {
            Alert b = new Alert(Alert.AlertType.INFORMATION, "le champ objet est vide");
            b.show();
        } else {

            rcd.addEntity(r);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("../gui/Confirmation.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        choix.setText("");
        datetf.setText("");
        objettf.setText("");

    }
    }

    @FXML
    private void backtomenu(ActionEvent event) {
    }
    
    

   
    
}
