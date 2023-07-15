/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane root;
    private Parent fxml;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("../gui/Acceuille.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void Acceuil() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../gui/Acceuille.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void facture() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../gui/Facture.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void hestorique() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../gui/Historique.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Reservation() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../gui/Reservation.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void locataire() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../gui/locataire.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void contracts() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../gui/Contrat.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
}


