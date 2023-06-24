/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.connection1cinfo1.gui;


import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.connection1cinfo1.entities.User;
import edu.connection1cinfo1.services.UserCRUD;
import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent;
import org.controlsfx.control.textfield.TextFields;

import org.mindrot.jbcrypt.BCrypt;


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
    private Text stMessage;
    @FXML
    private PasswordField idPassword;
    @FXML
    private Hyperlink idRuternToLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Parent signUpRoot = null;
        try {
            signUpRoot = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SignUpFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Scene signUpScene = new Scene(signUpRoot);
           // Set the action to be performed when the link is clicked
        idRuternToLogin.setOnAction(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("Login");
            stage.show();
            System.out.println("Returning to Login page...");
        });
        
        
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
                Image image3 = new Image("https://cdn.fifacm.com/content/media/imgs/fifa23/players/p213007.png", 100, 100, true, true);
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

        String email = idEmail.getText();
        String nom = idNom.getText().trim();
        String prenom = idPrenom.getText().trim();
        String telephone = idTelephone.getText().trim();
        String adress = idAdresse.getText();
        String password = idPassword.getText();

        if (nom.isEmpty() && nom.length() < 2) {
            stMessage.setText("Please correct First name");
            return;
        }

        if (prenom.isEmpty() && prenom.length() < 2) {
            stMessage.setText("Please correct Last name.");
            return;
        }

        if (email.isEmpty()) {
            stMessage.setText("Please input email");
            return;
        }
        
        if (adress.isEmpty()) {
            stMessage.setText("Please input adress");
            return;
        }

        if (telephone.isEmpty()) {
            stMessage.setText("Please input phone number");
            return;
        }
        try{
            System.out.println("num:  " +Integer.parseInt(telephone));
        }catch(Exception e){
            stMessage.setText("Please input a valid phone without caracters.");
            return;
        }  
        
         String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
         System.out.println("Hashed Password: " + hashedPassword);

        // Extract the first 3 characters of the first name and last name
        String firstThreeCharsFirstName = nom.substring(0, 3).toLowerCase();
        String firstThreeCharsLastName = prenom.substring(0, 3).toLowerCase();

        // Generate a random number between 1 and 10
        Random random = new Random();
        int randomNumber = random.nextInt(10) + 1;

        // Create the initial username
        String username = firstThreeCharsFirstName + firstThreeCharsLastName + randomNumber;

        UserCRUD userCRUD = new UserCRUD();
        // Query the database to check if the username exists
        boolean existsInDatabase = userCRUD.checkUsernameExists(username);

        // Handle conflicts by modifying the username
        while (existsInDatabase) {
            // Modify the username by appending 4 characters of the first name

            firstThreeCharsFirstName = nom.substring(0, 3 + 1).toLowerCase();
            firstThreeCharsLastName = prenom.substring(0, 3 + 1).toLowerCase();

            randomNumber = random.nextInt(10 + 10) + 1 + 10;

            username = firstThreeCharsFirstName + firstThreeCharsLastName + randomNumber;


            // Check if the modified username exists
            existsInDatabase = userCRUD.checkUsernameExists(username);
        }

        // At this point, you have a unique username
        System.out.println("Generated Username: " + username);


        User user = new User(username, nom, prenom, email, hashedPassword, adress, "joueur");
        if(userCRUD.addEntitySignUp(user)) {
            stMessage.setText("User added successfully your user name: "+username);
        } else {
            stMessage.setText("There is an error adding this User");
        }
    }


    private void selectPicture(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\ztouahri2\\OneDrive - DXC Production\\Desktop\\pitch_player\\src\\img"));
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null) {

        }
    }
    
}
