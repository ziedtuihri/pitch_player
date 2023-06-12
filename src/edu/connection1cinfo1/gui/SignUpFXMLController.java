/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;

import edu.connection1cinfo1.services.FileOperations;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class SignUpFXMLController implements Initializable {

    @FXML
    private TextField idEmail;
    @FXML
    private TextField idNom;
    @FXML
    private TextField idPrenom;
    @FXML
    private TextField idAdresse;
    @FXML
    private TextField idTelephone;
    @FXML
    private Button idPictureProfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        /*       //Reading player data
        HashMap<String, String> loaded = null;
        try {
            loaded = FileOperations.readPlayerDataFromFile("src/Data/PlayerDatabase.txt"); //file name path starts from one step back of src, but others all start from src
        } catch (Exception ex) {
            Logger.getLogger(SignUpFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<String> suggestionList = FXCollections.observableArrayList(loaded.keySet());

        AutoCompletionBinding<String> autoCompleteBinding = TextFields.bindAutoCompletion(idPhotoJoueur, suggestionList);

        // Set the maximum number of visible suggestions

        final String[] URL = {""};

        HashMap<String, String> finalLoaded = loaded;
        autoCompleteBinding.setOnAutoCompleted(event -> {
            String selectedKey = event.getCompletion();
            String selectedValue = finalLoaded.get(selectedKey);
            System.out.println("Selected Key: " + selectedKey);
            System.out.println("Selected Value: " + selectedValue);
            URL[0] = selectedValue;
            // 
            try {
                Platform.runLater(() -> {
                Image image3 = new Image("https://cdn.fifacm.com/content/media/imgs/fifa21/players/p213007.png", 100, 100, true, true);
                idImagePlayer.setImage(image3);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo");

        // Set the initial directory for the file chooser (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Set the file extension filters (optional)
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Show the file chooser dialog
        //File selectedFile = fileChooser.showOpenDialog(primaryStage);

       // if (selectedFile != null) {
            // Process the selected image file
            // You can store the file path or perform further actions with the selected file
         //    String imagePath = selectedFile.getAbsolutePath();
           // System.out.println("Selected Image: " + imagePath);

            // Display the selected image in an ImageView if desired
            // imageView.setImage(new Image("file:" + imagePath));
        //}

    }    

    @FXML
    private void Save(ActionEvent event) {
         
    }

    @FXML
    private void selectPicture(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\ztouahri2\\OneDrive - DXC Production\\Desktop\\pitch_player\\src\\img"));
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null) {

        }
    }
    
}
