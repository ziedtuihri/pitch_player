///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.pitchplayer.gui;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//import netscape.javascript.JSObject;
//
///**
// * FXML Controller class
// *
// * @author Raouf
// */
//public class MapsFXMLController implements Initializable {
//
//    @FXML
//    private WebView fxMaps;
//    private WebEngine webEngine;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        WebEngine webEngine = fxMaps.getEngine();
//    webEngine.load("http://www.openstreetmap.org");
//    }    
//    
//     @FXML
//    private void handleSelectAddress(ActionEvent event) {
//        // Exécuter du code JavaScript pour récupérer l'adresse sélectionnée
//        JSObject window = (JSObject) webEngine.executeScript("window");
//        Object result = window.call("getSelectedAddress"); // Supposons que vous avez une fonction JavaScript "getSelectedAddress" qui retourne l'adresse sélectionnée
//
//        // Insérer l'adresse dans la base de données
//        if (result instanceof String) {
//            String selectedAddress = (String) result;
//            // Code pour insérer l'adresse dans votre base de données
//            // Utilisez les fonctionnalités appropriées pour accéder à votre base de données
//        } else {
//            System.out.println("Aucune adresse sélectionnée");
//        }
//    }
//}
//
//


package edu.pitchplayer.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.event.ActionEvent;
import netscape.javascript.JSObject;

public class MapsFXMLController implements Initializable {

    @FXML
    private WebView fxMaps;
    private WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = fxMaps.getEngine();
        webEngine.load("http://www.openstreetmap.org");
       fxMaps.setOnMouseClicked(event -> {
            // Récupérer les coordonnées de l'emplacement sélectionné
            JSObject window = (JSObject) webEngine.executeScript("window");
            double lat = (double) window.call("getClickLat");
            double lon = (double) window.call("getClickLon");

    // Insérer les coordonnées dans la base de données
    insertLocationToDatabase(lat, lon);
});
    }    
    
    @FXML
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
    private void insertLocationToDatabase(double lat, double lon) {
    // Code pour insérer les coordonnées dans votre base de données
    // Utilisez les fonctionnalités appropriées pour accéder à votre base de données
    // ...
}
}
