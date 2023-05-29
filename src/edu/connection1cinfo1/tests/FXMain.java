/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package edu.connection1cinfo1.tests;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ztouahri2
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        String url = "../gui/AjoutEquipeFXML.fxml";
        URL realUrl = getClass().getResource(url);
        
        FXMLLoader loader = new FXMLLoader(realUrl);
        
        Parent root;        
        try {
            root = loader.load();
                    Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();
        primaryStage.setTitle("gestion utilisateur");
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
