/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.services.EquipeCRUD;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;

import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class AjouterJoueurFXMLController implements Initializable {
    @FXML
    private Label stMessage;
    @FXML
    private TextField tfNomJoueur;
    @FXML
    private TextField tfPrenomJoueur;
    @FXML
    private TextField tfEmailJoueur;
    @FXML
    private TextField tfNumeroTeleJoueur;
    @FXML
    private ChoiceBox<?> tfChoiceBoxJoueur;
    @FXML
    private DatePicker tfDateJoueur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EquipeCRUD equipeCRUD = new EquipeCRUD();
        List<Equipe> listEquipe = equipeCRUD.displayEntities();

        ChoiceBox<Equipe> cb = (ChoiceBox<Equipe>) tfChoiceBoxJoueur;
        
        cb.setConverter(new StringConverter<Equipe>() {
            @Override
            public String toString(Equipe equipe) {
                return  equipe.getNom(); // Return the name of the Equipe
            }
            @Override
            public Equipe fromString(String string) {
                // This method is not needed for ChoiceBox
                return null;
            }
        });
        cb.getItems().addAll(listEquipe);
    }    

    @FXML
    private void AjouterJoueur(ActionEvent event) {
        
        String nomJoueur = tfNomJoueur.getText();
        String prenomJoueur = tfPrenomJoueur.getText().trim();
        String numTeleJoueur = tfNumeroTeleJoueur.getText().trim();
        String emailJoueur = tfEmailJoueur.getText().trim();

        LocalDate dateJoueur = tfDateJoueur.getValue();
        ChoiceBox<Equipe> cb = (ChoiceBox<Equipe>) tfChoiceBoxJoueur;
        stMessage.setText("");

        if (nomJoueur.isEmpty()) {
            stMessage.setText("Please input name.");
            return;
        }

        if (prenomJoueur.isEmpty()) {
            stMessage.setText("Please input last name.");
            return;
        }

        if (emailJoueur.isEmpty()) {
            stMessage.setText("Please input email");
            return;
        }

        if (dateJoueur == null) {
            stMessage.setText("Please input Date");
            return;
        }

        if (numTeleJoueur.isEmpty()) {
            stMessage.setText("Please input phone number");
            return;
        }
        try{
            System.out.println("num:  " +Integer.parseInt(numTeleJoueur));
        }catch(Exception e){
            stMessage.setText("Please input a valid phone without caracters.");
            return;
        }

        if (cb.getItems().isEmpty()) {
            stMessage.setText("Please input phone number");
            return;
        }

        Date sqlDate = Date.valueOf(dateJoueur);

        System.out.println("date:: " + dateJoueur+"\n select "+cb.getValue().getId());
        /*
        Joueur j = new Joueur(nomJoueur, prenomJoueur, emailJoueur,
                sqlDate,
                numTeleJoueur,
                cb.getValue().getId()
                );
        JoueurCRUD joueurCRUD = new JoueurCRUD();

       
        joueurCRUD.addEntity(j);
        */
        Alert a = new Alert(Alert.AlertType.INFORMATION,"Joueur added", ButtonType.OK);
        a.show();

        
    }
    
}
