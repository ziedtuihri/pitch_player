///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.pitchplayer.gui;
//
//import edu.pitchplayer.entities.Terrain;
//import edu.pitchplayer.services.TerrainService;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.stage.Stage;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
///**
// * FXML Controller class
// *
// * @author Raouf
// */
//public class DetailTerrainFXMLController implements Initializable {
//
//    @FXML
//    private TextField fxNom;
//    @FXML
//    private TextField fxVille;
//    @FXML
//    private TextField fxAdresse;
//    @FXML
//    private TextField fxLongeur;
//    @FXML
//    private TextField fxLargeur;
// private Terrain terrain;
//    @FXML
//    private ImageView fxImage;
//    /**
//     * Initializes the controller class.
//     */
//    
// 
// 
//  public void setTerrain(Terrain terrain) throws MalformedURLException {
// this.terrain = terrain;
//
//
//     fxNom.setText(terrain.getNom());
//        fxAdresse.setText(terrain.getAdresse());
//        fxVille.setText(terrain.getVille());
//        fxLongeur.setText(String.valueOf(terrain.getLongueur()));
//    fxLargeur.setText(String.valueOf(terrain.getLargeur()));
////String imageUrl = terrain.getImage();
////if (imageUrl != null && !imageUrl.isEmpty()) {
////    try {
////        Image image = new Image(getClass().getResourceAsStream(imageUrl));
////        fxImage.setImage(image);
////    } catch (Exception e) {
////        e.printStackTrace();
////    }
////}
//    String imageUrl = getTerrainImageUrl(terrain);
//    try {
//        Image image = new Image(new File(imageUrl).toURI().toString());
//        fxImage.setImage(image);
//    } catch (Exception e) {
//        e.printStackTrace();
//        // Gérer l'erreur de chargement de l'image
//        // Afficher un message d'erreur ou une image par défaut
//        File errorImageFile = new File("C:/Users/Raouf/Desktop/error.png");
//        Image errorImage = new Image(errorImageFile.toURI().toString());
//        fxImage.setImage(errorImage);
//        // Afficher un message d'erreur
//        System.out.println("Erreur lors du chargement de l'image");
//    }
//}
//
//private String getTerrainImageUrl(String terrainNom) {
//    String imageFileName = "";
//
//    // Sélectionner l'image en fonction des critères de sélection
//    if (terrainNom.equals("raouf")) {
//        imageFileName = "SRB.png";
//    } else if (terrainNom.equals("alexixxx")) {
//        imageFileName = "BRZ.png";
//    } else {
//        // Image par défaut si aucun critère ne correspond
//        imageFileName = "3437278_5c27f.jpg";
//    }
//
//    String imagePath = "C:/Users/Raouf/Desktop/" + imageFileName;
//    return imagePath;
//}
//
//
//        
//   
//  
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//     
//        
//    }    
//
//    @FXML
//    private void RetourAction(ActionEvent event) {
//       try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RechercheFXML.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    }
//    
//}
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Terrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class DetailTerrainFXMLController implements Initializable {

    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxVille;
    @FXML
    private TextField fxAdresse;
    @FXML
    private TextField fxLongeur;
    @FXML
    private TextField fxLargeur;
    @FXML
    private ImageView fxImage;

    private Terrain terrain;
    @FXML
    private Button fxRetour;
  private MyMemoryTranslator translator;
    @FXML
    private Label fxLibNom;
    @FXML
    private Label fxLibVille;
    @FXML
    private Label fxLibAdresse;
    @FXML
    private Label fxLibLongeur;
    @FXML
    private Label fxLibLargeur;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
translator = new MyMemoryTranslator();
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;

        fxNom.setText(terrain.getNom());
        fxAdresse.setText(terrain.getAdresse());
        fxVille.setText(terrain.getVille());
        fxLongeur.setText(String.valueOf(terrain.getLongueur()));
        fxLargeur.setText(String.valueOf(terrain.getLargeur()));

        String imageUrl = getTerrainImageUrl(terrain.getNom());
        try {
            Image image = new Image(new File(imageUrl).toURI().toString());
            fxImage.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur de chargement de l'image
            // Afficher un message d'erreur ou une image par défaut
            File errorImageFile = new File("C:/Users/Raouf/Desktop/error.png");
            Image errorImage = new Image(errorImageFile.toURI().toString());
            fxImage.setImage(errorImage);
            // Afficher un message d'erreur
            System.out.println("Erreur lors du chargement de l'image");
        }
    }

    private String getTerrainImageUrl(String terrainNom) {
        String imageFileName = "";

        // Sélectionner l'image en fonction des critères de sélection
        if (terrainNom.equals("raouf")) {
            imageFileName = "SRB.png";
        } else if (terrainNom.equals("alexixxx")) {
            imageFileName = "BRZ.png";
        } else {
            // Image par défaut si aucun critère ne correspond
            imageFileName = "3437278_5c27f.jpg";
        }

        String imagePath = "C:/Users/Raouf/Desktop/" + imageFileName;
        return imagePath;
    }

    @FXML
    private void RetourAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RechercheFXML.fxml"));
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
    private void TraduireAction(ActionEvent event) throws UnsupportedEncodingException, IOException {
        String sourceLang = "fr"; // Langue source
        String targetLang = "en"; // Langue cible

        String adresseText = fxLibAdresse.getText();
        String translatedAdresse = translator.traduireTexte(adresseText, sourceLang, targetLang);
        if (translatedAdresse != null) {
            fxLibAdresse.setText(translatedAdresse);
        } else {
            // Gérer les erreurs de traduction pour fxLibAdresse
        }

        // Traduire fxLibNom
        String nomText = fxLibNom.getText();
        String translatedNom = translator.traduireTexte(nomText, sourceLang, targetLang);
        if (translatedNom != null) {
            fxLibNom.setText(translatedNom);
        } else {
            // Gérer les erreurs de traduction pour fxLibNom
        }

        // Traduire fxLibVille
        String villeText = fxLibVille.getText();
        String translatedVille = translator.traduireTexte(villeText, sourceLang, targetLang);
        if (translatedVille != null) {
            fxLibVille.setText(translatedVille);
        } else {
            // Gérer les erreurs de traduction pour fxLibVille
        }

        // Traduire fxLibLongeur
        String largeurText = fxLibLargeur.getText();
        String translatedLargeur = translator.traduireTexte(largeurText, sourceLang, targetLang);
        if (translatedLargeur != null) {
            fxLibLargeur.setText(translatedLargeur);
        } else {
            // Gérer les erreurs de traduction pour fxLibLargeur
        }

        // Traduire fxLibImage
        String longeurText = fxLibLongeur.getText();
        String translatedLongeur = translator.traduireTexte(longeurText, sourceLang, targetLang);
        if (translatedLongeur != null) {
            fxLibLongeur.setText(translatedLongeur);
        } else {
            // Gérer les erreurs de traduction pour fxLibLongeur
        }

        // Traduire fxRetour
        String retourText = fxRetour.getText();
        String translatedRetour = translator.traduireTexte(retourText, sourceLang, targetLang);
        if (translatedRetour != null) {
            fxRetour.setText(translatedRetour);
        } else {
            // Gérer les erreurs de traduction pour fxRetour
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


