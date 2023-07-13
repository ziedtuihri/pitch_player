package edu.pitchplayer.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class MapsFXMLController implements Initializable {

    @FXML
    private WebView fxMaps;
    private WebEngine webEngine;
    private AJouterTerrainFXMLController ajouterTerrainController;
    private String selectedAddress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = fxMaps.getEngine();
        loadMap();
    }

    private void loadMap() {
        webEngine.load("https://www.google.com/maps");
    }

    private void handleSelectAddress(ActionEvent event) {
        // Exécuter du code JavaScript pour récupérer l'adresse sélectionnée
        JSObject window = (JSObject) webEngine.executeScript("window");
        Object result = window.call("getSelectedAddress"); // Supposons que vous avez une fonction JavaScript "getSelectedAddress" qui retourne l'adresse sélectionnée

        // Insérer l'adresse dans la base de données
        if (result instanceof String) {
            String selectedAddress = (String) result;
            // Code pour insérer l'adresse dans votre base de données
            // Utilisez les fonctionnalités appropriées pour accéder à votre base de données
        } else {
            System.out.println("Aucune adresse sélectionnée");
        }
    }

    @FXML
    private void RetourAction(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterTerrainFXML.fxml"));
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
