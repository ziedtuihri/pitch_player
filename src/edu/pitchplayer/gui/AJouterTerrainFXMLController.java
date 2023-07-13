package edu.pitchplayer.gui;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainCRUD;
import edu.pitchplayer.utils.MyConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.applet.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.me.JSONException;
import org.json.me.JSONObject;


/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class AJouterTerrainFXMLController implements Initializable {

    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxAdresse;
    @FXML
    private TextField fxLongeur;
    @FXML
    private TextField fxLargeur;
    private Label stMessage;
    private Label stMessage1;
    @FXML
    private Label stMessageLongeur;
    @FXML
    private Label stMessageLargeur;
    @FXML
    private Label stMessageNom;
    @FXML
    private Label stMessageAdresse;
    @FXML
    private Button browseButton;
    WebEngine webEngine = null;
    @FXML
    private ImageView fxImage;
     String imagePath;
     
     private MapsFXMLController mapsController;
    @FXML
    private Label fxLibNom;
     
     private MyMemoryTranslator translator;
    @FXML
    private Label fxLibAfresse;
    @FXML
    private Label fxLibLongueur;
    @FXML
    private Label fxLibImage;
    @FXML
    private Button fxAjouter;
    @FXML
    private Button fxAnnuler;
    @FXML
    private Button fxRetour;
    @FXML
    private Label fxLibLargeur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       translator = new MyMemoryTranslator();
    
    }    

    @FXML
   private void AjoutTerrainAction(ActionEvent event) {
    String nom = fxNom.getText();
    String adresse = fxAdresse.getText();
    String longeurText = fxLongeur.getText();
    String largeurText = fxLargeur.getText();
    boolean isError = false;
    
    

    if (adresse.isEmpty() && nom.isEmpty() && longeurText.isEmpty() && largeurText.isEmpty()) {
        stMessageAdresse.setText("Veuillez saisir une adresse.");
        stMessageNom.setText("Veuillez saisir un nom.");
        stMessageLongeur.setText("Veuillez saisir une longueur.");
        stMessageLargeur.setText("Veuillez saisir une largeur.");
        isError = true;
    } else {
        stMessageAdresse.setText("");
        stMessageNom.setText("");
        stMessageLongeur.setText("");
        stMessageLargeur.setText("");
    }

    if (nom.isEmpty()) {
        stMessageNom.setText("Veuillez saisir un nom.");
        isError = true;
    }else if (!nom.matches("[a-zA-Z]+")) {
        stMessageNom.setText("Le nom ne doit contenir que des lettres.");
        isError = true;
    }

    if (longeurText.isEmpty()) {
        stMessageLongeur.setText("Veuillez saisir une longueur.");
        isError = true;
    }

    if (largeurText.isEmpty()) {
        stMessageLargeur.setText("Veuillez saisir une largeur.");
        isError = true;
    }

    float longeur = 0;
    float largeur = 0;

    try {
        longeur = Float.parseFloat(longeurText);
    } catch (NumberFormatException e) {
        if (!longeurText.isEmpty()) {
            stMessageLongeur.setText("La longueur doit être un nombre valide.");
            isError = true;
        }
    }

    try {
        largeur = Float.parseFloat(largeurText);
    } catch (NumberFormatException e) {
        if (!largeurText.isEmpty()) {
            stMessageLargeur.setText("La largeur doit être un nombre valide.");
            isError = true;
        }
    }

    if (!isError) {
        if (longeur == 0) {
            stMessageLongeur.setText("La longueur ne peut pas être égale à 0.");
            isError = true;
        }

        if (largeur == 0) {
            stMessageLargeur.setText("La largeur ne peut pas être égale à 0.");
            isError = true;
        }

        if (longeur < 0) {
            stMessageLongeur.setText("La longueur ne peut pas être négative.");
            isError = true;
        }

        if (largeur < 0) {
            stMessageLargeur.setText("La largeur ne peut pas être négative.");
            isError = true;
        }
    }

    if (isError) {
        return;
    }
   Terrain terrain = new Terrain(nom, adresse, longeur, largeur);
        terrain.setImage(imagePath);

        TerrainCRUD tc = new TerrainCRUD();
        if (tc.existsNom(nom)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nom du terrain existe déjà dans la base de données.");
            alert.showAndWait();
            return;
        }

        tc.addEntity(terrain);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Terrain ajouté avec succès.");
        alert.showAndWait();

        fxNom.setText("");
        fxAdresse.setText("");
        fxLongeur.setText("");
        fxLargeur.setText("");
        fxImage.setImage(null);
        imagePath = null;
}

    @FXML
    private void AnnulerAction(ActionEvent event) {
         fxNom.setText("");
         fxAdresse.setText("");
         fxLongeur.setText("");
         fxLargeur.setText("");
         stMessageLongeur.setText("");
          stMessageLargeur.setText("");
           stMessageNom.setText("");
            stMessageAdresse.setText("");
    }

    @FXML
    private void RetourAction(ActionEvent event) {
        
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AccueilTerrainFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
          
    }

@FXML
private void browseFile(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Sélectionner un fichier");

    // Définir le répertoire initial
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

    // Définir les filtres d'extension de fichier (optionnel)
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Image", "*.png", "*.jpg", "*.jpeg"));

    // Afficher la boîte de dialogue de sélection de fichier
    Stage stage = (Stage) browseButton.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(stage);

    if (selectedFile != null) {
        // Fichier sélectionné par l'utilisateur
        System.out.println("Fichier sélectionné : " + selectedFile.getAbsolutePath());

        // Charger l'image dans ImageView
        Image image = new Image(selectedFile.toURI().toString());
        fxImage.setImage(image);

        // Enregistrer le chemin d'accès de l'image sélectionnée
        imagePath = selectedFile.getAbsolutePath();
    } else {
        // L'utilisateur a annulé la sélection du fichier
        System.out.println("Sélection de fichier annulée.");
    }
}


    @FXML
    private void MapsAction(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MapsFXML.fxml"));
Parent root = loader.load();
Scene scene = new Scene(root);
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
stage.setScene(scene);
stage.show();

MapsFXMLController mapsController = loader.getController();
//mapsController.setAJouterTerrainController(this);
setMapsController(mapsController);


//MapsFXMLController mapsController = loader.getController();
//mapsController.setAJouterTerrainController(this);

           
    }
    
    public void setCoordinates(double latitude, double longitude) {
    // Utilisez les coordonnées comme vous le souhaitez
    fxAdresse.setText("Latitude: " + latitude + ", Longitude: " + longitude);
    
}
     public void setCordonne(String cordonne) {
    fxNom.setText(cordonne);
     }
        public void setMapsController(MapsFXMLController controller) {
        mapsController = controller;
    }

 @FXML
private void TraduireAction(ActionEvent event) throws UnsupportedEncodingException, IOException {
    String sourceLang = "fr"; // Langue source
    String targetLang = "en"; // Langue cible

     String NomText = fxLibNom.getText();
    String translatedNom = translator.traduireTexte(NomText, sourceLang, targetLang);
    if (translatedNom != null) {
        fxLibNom.setText(translatedNom);
    } else {
        // Gérer les erreurs de traduction pour fxLibAdresse
    }
    
    // Traduire fxLibAdresse
    String adresseText = fxLibAfresse.getText();
    String translatedAdresse = translator.traduireTexte(adresseText, sourceLang, targetLang);
    if (translatedAdresse != null) {
        fxLibAfresse.setText(translatedAdresse);
    } else {
        // Gérer les erreurs de traduction pour fxLibAdresse
    }

    // Traduire fxLibLongueur
    String longueurText = fxLibLongueur.getText();
    String translatedLongueur = translator.traduireTexte(longueurText, sourceLang, targetLang);
    if (translatedLongueur != null) {
        fxLibLongueur.setText(translatedLongueur);
    } else {
        // Gérer les erreurs de traduction pour fxLibLongueur
    }
     // Traduire fxLibLongueur
    String largeurText = fxLibLargeur.getText();
    String translatedLargeur= translator.traduireTexte(largeurText, sourceLang, targetLang);
    if (translatedLongueur != null) {
        fxLibLargeur.setText(translatedLargeur);
    } else {
        // Gérer les erreurs de traduction pour fxLibLongueur
    }

    // Traduire fxLibImage
    String imageText = fxLibImage.getText();
    String translatedImage = translator.traduireTexte(imageText, sourceLang, targetLang);
    if (translatedImage != null) {
        fxLibImage.setText(translatedImage);
    } else {
        // Gérer les erreurs de traduction pour fxLibImage
    }

    // Traduire fxAjouter
    String ajouterText = fxAjouter.getText();
    String translatedAjouter = translator.traduireTexte(ajouterText, sourceLang, targetLang);
    if (translatedAjouter != null) {
        fxAjouter.setText(translatedAjouter);
    } else {
        // Gérer les erreurs de traduction pour fxAjouter
    }

    // Traduire fxAnnuler
    String annulerText = fxAnnuler.getText();
    String translatedAnnuler = translator.traduireTexte(annulerText, sourceLang, targetLang);
    if (translatedAnnuler != null) {
        fxAnnuler.setText(translatedAnnuler);
    } else {
        // Gérer les erreurs de traduction pour fxAnnuler
    }

    // Traduire fxRetour
    String retourText = fxRetour.getText();
    String translatedRetour = translator.traduireTexte(retourText, sourceLang, targetLang);
    if (translatedRetour != null) {
        fxRetour.setText(translatedRetour);
    } else {
        // Gérer les erreurs de traduction pour fxRetour
    }
    // Traduire browseButton
    String browseButtonText = browseButton.getText();
    String translatedBrowseButton = translator.traduireTexte(browseButtonText, sourceLang, targetLang);
    if (translatedBrowseButton != null) {
        browseButton.setText(translatedBrowseButton);
    } else {
        // Gérer les erreurs de traduction pour browseButton
    }
}

public class MyMemoryTranslator {

    private static final String API_URL = "https://api.mymemory.translated.net/get";

    public String traduireTexte(String texte, String sourceLang, String targetLang) throws IOException {
        String encodedText = URLEncoder.encode(texte, "UTF-8");
        String encodedLangPair = URLEncoder.encode(sourceLang + "|" + targetLang, "UTF-8");
        String url = API_URL + "?q=" + encodedText + "&langpair=" + encodedLangPair;

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String jsonResponse = EntityUtils.toString(entity);

        // Analyser la réponse JSON pour obtenir la traduction
        String translatedText = parseTranslationFromJSON(jsonResponse);

        return translatedText;
    }

    private String parseTranslationFromJSON(String jsonResponse) {
       String translatedText = null;

    try {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        if (jsonObject.has("responseData")) {
            JSONObject responseData = jsonObject.getJSONObject("responseData");
            if (responseData.has("translatedText")) {
                translatedText = responseData.getString("translatedText");
            }
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }


        return translatedText;
    }
    }}


//package edu.pitchplayer.gui;
//
//import edu.pitchplayer.entities.Terrain;
//import edu.pitchplayer.services.TerrainCRUD;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.embed.swing.JFXPanel;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import sun.applet.Main;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//
//public class AJouterTerrainFXMLController implements Initializable {
//
//    @FXML
//    private TextField fxNom;
//    @FXML
//    private TextField fxAdresse;
//    @FXML
//    private TextField fxLongeur;
//    @FXML
//    private TextField fxLargeur;
//    @FXML
//    private Label stMessageLongeur;
//    @FXML
//    private Label stMessageLargeur;
//    @FXML
//    private Label stMessageNom;
//    @FXML
//    private Label stMessageAdresse;
//    @FXML
//    private Button browseButton;
//    @FXML
//    private ImageView fxImage;
//    String imagePath;
//
//    private MapsFXMLController mapsController;
//    private AJouterTerrainFXMLController ajouterTerrainController;
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
//
//    @FXML
//    private void AjoutTerrainAction(ActionEvent event) {
//        String nom = fxNom.getText();
//        String adresse = fxAdresse.getText();
//        String longeurText = fxLongeur.getText();
//        String largeurText = fxLargeur.getText();
//
//        if (adresse.isEmpty() || nom.isEmpty() || longeurText.isEmpty() || largeurText.isEmpty()) {
//            stMessageAdresse.setText("Veuillez saisir une adresse.");
//            stMessageNom.setText("Veuillez saisir un nom.");
//            stMessageLongeur.setText("Veuillez saisir une longueur.");
//            stMessageLargeur.setText("Veuillez saisir une largeur.");
//            return;
//        }
//
//        float longeur;
//        float largeur;
//
//        try {
//            longeur = Float.parseFloat(longeurText);
//            largeur = Float.parseFloat(largeurText);
//        } catch (NumberFormatException e) {
//            stMessageLongeur.setText("La longueur doit être un nombre valide.");
//            stMessageLargeur.setText("La largeur doit être un nombre valide.");
//            return;
//        }
//
//        if (longeur == 0) {
//            stMessageLongeur.setText("La longueur ne peut pas être égale à 0.");
//            return;
//        }
//
//        if (largeur == 0) {
//            stMessageLargeur.setText("La largeur ne peut pas être égale à 0.");
//            return;
//        }
//
//        if (longeur < 0 || largeur < 0) {
//            stMessageLongeur.setText("La longueur ne peut pas être négative.");
//            stMessageLargeur.setText("La largeur ne peut pas être négative.");
//            return;
//        }
//
//        Terrain terrain = new Terrain(nom, adresse, longeur, largeur);
//        terrain.setImage(imagePath);
//
//        TerrainCRUD tc = new TerrainCRUD();
//        if (tc.existsNom(nom)) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Le nom du terrain existe déjà dans la base de données.");
//            alert.showAndWait();
//            return;
//        }
//
//        tc.addEntity(terrain);
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Succès");
//        alert.setHeaderText(null);
//        alert.setContentText("Terrain ajouté avec succès.");
//        alert.showAndWait();
//
//        fxNom.setText("");
//        fxAdresse.setText("");
//        fxLongeur.setText("");
//        fxLargeur.setText("");
//        fxImage.setImage(null);
//        imagePath = null;
//    }
//
//    @FXML
//    private void AnnulerAction(ActionEvent event) {
//        fxNom.setText("");
//        fxAdresse.setText("");
//        fxLongeur.setText("");
//        fxLargeur.setText("");
//        stMessageLongeur.setText("");
//        stMessageLargeur.setText("");
//        stMessageNom.setText("");
//        stMessageAdresse.setText("");
//    }
//
//    @FXML
//    private void RetourAction(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AccueilTerrainFXML.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void browseFile(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Sélectionner un fichier");
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Image", "*.png", "*.jpg", "*.jpeg"));
//
//        Stage stage = (Stage) browseButton.getScene().getWindow();
//        File selectedFile = fileChooser.showOpenDialog(stage);
//
//        if (selectedFile != null) {
//            System.out.println("Fichier sélectionné : " + selectedFile.getAbsolutePath());
//            Image image = new Image(selectedFile.toURI().toString());
//            fxImage.setImage(image);
//            imagePath = selectedFile.getAbsolutePath();
//        } else {
//            System.out.println("Sélection de fichier annulée.");
//        }
//    }
//    
//    @FXML
//    private void MapsAction(ActionEvent event) throws IOException {
//      FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MapsFXML.fxml"));
//Parent root = loader.load();
//Scene scene = new Scene(root);
//Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//stage.setScene(scene);
//stage.show();
//
//MapsFXMLController mapsController = loader.getController();
//mapsController.setAJouterTerrainController(this);
//
//           
//    }
//
//    public void setCoordinates(double latitude, double longitude) {
//        String address = "Latitude: " + latitude + ", Longitude: " + longitude;
//        fxAdresse.setText(address);
//    }
//
//    public void setMapsController(MapsFXMLController controller) {
//        mapsController = controller;
//    }
//    public void setAJouterTerrainController(AJouterTerrainFXMLController controller) {
//    ajouterTerrainController = controller;
//}
//    public void setCordonne(String cordonne) {
//    fxNom.setText(cordonne);
//}
//}
