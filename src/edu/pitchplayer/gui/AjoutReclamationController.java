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
public class AjoutReclamationController implements Initializable {

    @FXML
    private TextField datetf;
    @FXML
    private ComboBox sujetcb;
    @FXML
    private TextArea objettf;
    @FXML
    private Label choix;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void selectChoix(ActionEvent event) {

        String s = sujetcb.getSelectionModel().getSelectedItem().toString();
        choix.setText(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        datetf.setText(new Date().toString());
        ObservableList<String> list = FXCollections.observableArrayList("Disponibilité Terrain", "Materiel", "deroulement de tournoi");
        sujetcb.setItems(list);

    }

    @FXML
    private void ajoutreclamation(ActionEvent event) {

        String sujet = choix.getText();
        String date = datetf.getText();
        String objet = objettf.getText();
        
        Reclamation r = new Reclamation(date, sujet, objet, Reclamation.ReclamationStatut.Closed, User, Reclamation.NotificationStatut.Notified, date)

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

    @FXML
    private void backtomenu(ActionEvent event) {
        
        try {
                Parent root = FXMLLoader.load(getClass().getResource("../gui/Menu.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

}
