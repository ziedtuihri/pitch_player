/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;

import edu.connection1cinfo1.entities.Equipe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.connection1cinfo1.services.EquipeCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class AjoutEquipeFXMLController implements Initializable {

    @FXML
    private TextField tfNomEquipe;
    @FXML
    private TextField tfanneeFondation;
    @FXML
    private Label statusMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void AjouterEquipe(ActionEvent event) {
        
        String nomEquipe = tfNomEquipe.getText();
        String anneeFondation = tfanneeFondation.getText().trim();
        statusMessage.setText("");

        if (nomEquipe.isEmpty()) {
            statusMessage.setText("Please input Equipe.");
            return;
        }

        if (anneeFondation.isEmpty()) {
            statusMessage.setText("Please input a year.");
            return;
        }
        try{
            System.out.println("num:  " +Integer.parseInt(anneeFondation));
        }catch(Exception e){
            statusMessage.setText("Please input a valid year without caracters.");
            return;
        }


        Equipe e = new Equipe(nomEquipe, Integer.parseInt(anneeFondation));
        EquipeCRUD equipeCRUD = new EquipeCRUD();
        equipeCRUD.addEntity(e);
        Alert a = new Alert(Alert.AlertType.INFORMATION,"Equipe added", ButtonType.OK);
        a.show();




    }
    
}
