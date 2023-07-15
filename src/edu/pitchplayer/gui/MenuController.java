/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Access
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoadd(ActionEvent event) {
        
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../gui/Ajoutreclamation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch( Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void viewreclam(ActionEvent event) {
        
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../gui/AffichageReclamation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch( Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void backtomenu(ActionEvent event) {
        
        
        
    }
    
}
