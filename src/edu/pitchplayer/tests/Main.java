/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author WIJDEN /edu/pitchplayer/styles/main.css
 */
public class Main extends Application {

     @Override
    public void start(Stage primaryStage) throws Exception {
        // load the fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pitchplayer/gui/MainEquipeView.fxml"));
        Parent root = loader.load();

        // set up the stage and scene
        //primaryStage.setTitle("Equipe Details");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
