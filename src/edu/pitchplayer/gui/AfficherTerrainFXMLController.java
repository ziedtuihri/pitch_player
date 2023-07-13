/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainCRUD;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
public class AfficherTerrainFXMLController implements Initializable {

    @FXML
    private TableView<Terrain> fxTerrain;
//    private TableColumn<Terrain, Integer> fxId;
    @FXML
    private TableColumn<Terrain, String> fxNom;
    @FXML
    private TableColumn<Terrain, String> fxAdresse;
//    private TableColumn<Terrain, String> fxVille;
    @FXML
    private TableColumn<Terrain, Float> fxLongeur;
    @FXML
    private TableColumn<Terrain, Float> fxLargeur;
//    private TableColumn<Terrain, Boolean> fxDisponibilite;
    @FXML
    private Button fxRetour;
    @FXML
    private Button fxModifer;
    @FXML
    private Button fxSupprimer;
    
   private MyMemoryTranslator translator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TerrainCRUD terrain = new TerrainCRUD();
        fxTerrain.setItems(terrain.getTerrain());
        fxNom.setCellValueFactory(new PropertyValueFactory<Terrain,String>("nom"));
        fxAdresse.setCellValueFactory(new PropertyValueFactory<Terrain,String>("adresse"));
//        fxVille.setCellValueFactory(new PropertyValueFactory<Terrain,String>("ville"));
        fxLongeur.setCellValueFactory(new PropertyValueFactory<Terrain,Float>("longueur"));
        fxLargeur.setCellValueFactory(new PropertyValueFactory<Terrain,Float>("largeur"));
//        fxDisponibilite.setCellValueFactory(new PropertyValueFactory<Terrain,Boolean>("disponible"));

//        displayEntities();
translator = new MyMemoryTranslator();

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

//    @FXML
//    private void ModifierAction(ActionEvent event) {
////        Terrain terrain = fxTerrain.getSelectionModel().getSelectedItem();
////    
////    if (terrain != null) {
////        // Ouvrir une nouvelle fenêtre pour la modification
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierTerrainFXML.fxml"));
////        Parent root;
////        try {
////            root = loader.load();
////            ModifierTerrainFXMLController controller = loader.getController();
////            controller.setTerrain(terrain);
////            Scene scene = new Scene(root);
////            Stage stage = new Stage();
////            stage.setScene(scene);
////            stage.show();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//}
//
//    
//
//    @FXML
//    private void SupprimerAction(ActionEvent event) {
//    }
//   

    @FXML
    private void ModifierAction(ActionEvent event) {
          Terrain terrain = fxTerrain.getSelectionModel().getSelectedItem();
    
    if (terrain != null) {
        // Ouvrir une nouvelle fenêtre pour la modification
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierTerrainFXML.fxml"));
        Parent root;
        try {
            root = loader.load();
            ModifierTerrainFXMLController controller = loader.getController();
            controller.setTerrain(terrain);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
             Stage currentStage = (Stage) fxTerrain.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
           Terrain terrain = fxTerrain.getSelectionModel().getSelectedItem();

    if (terrain != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce terrain ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            TerrainCRUD terrainCRUD = new TerrainCRUD();
            terrainCRUD.delete(terrain);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Suppression réussie");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Terrain supprimé avec succès");
            successAlert.showAndWait();

            // Redirect to the previous scene or close the window
            fxTerrain.getItems().clear();
            fxTerrain.getItems().addAll(terrainCRUD.getTerrain());
        }
    }
    }

    @FXML
private void TraduireAction(ActionEvent event) throws UnsupportedEncodingException, IOException {
    String sourceLang = "fr"; // Langue source
    String targetLang = "en"; // Langue cible

     String adresseText = fxAdresse.getText();
    String translatedAdresse = translator.traduireTexte(adresseText, sourceLang, targetLang);
    if (translatedAdresse != null) {
        fxAdresse.setText(translatedAdresse);
    } else {
        // Gérer les erreurs de traduction pour fxLibAdresse
    }
    
    // Traduire fxLibAdresse
    String nomText = fxNom.getText();
    String translatedNom = translator.traduireTexte(nomText, sourceLang, targetLang);
    if (translatedNom != null) {
        fxNom.setText(translatedNom);
    } else {
        // Gérer les erreurs de traduction pour fxLibAdresse
    }

    // Traduire fxLibLongueur
//    String villeText = fxVille.getText();
//    String translatedVille = translator.traduireTexte(villeText, sourceLang, targetLang);
//    if (translatedVille != null) {
//        fxVille.setText(translatedVille);
//    } else {
//        // Gérer les erreurs de traduction pour fxLibLongueur
//    }
     // Traduire fxLibLongueur
    String largeurText = fxLargeur.getText();
    String translatedLargeur= translator.traduireTexte(largeurText, sourceLang, targetLang);
    if (translatedLargeur != null) {
        fxLargeur.setText(translatedLargeur);
    } else {
        // Gérer les erreurs de traduction pour fxLibLongueur
    }

    // Traduire fxLibImage
    String longeurText = fxLongeur.getText();
    String translatedLongeur = translator.traduireTexte(longeurText, sourceLang, targetLang);
    if (translatedLongeur != null) {
        fxLongeur.setText(translatedLongeur );
    } else {
        // Gérer les erreurs de traduction pour fxLibImage
    }

    // Traduire fxAjouter
    String modifierText = fxModifer.getText();
    String translatedmodifier = translator.traduireTexte(modifierText, sourceLang, targetLang);
    if (translatedmodifier != null) {
        fxModifer.setText(translatedmodifier);
    } else {
        // Gérer les erreurs de traduction pour fxAjouter
    }

    // Traduire fxAnnuler
    String supprimerText = fxSupprimer.getText();
    String translatedsupprimer = translator.traduireTexte(supprimerText, sourceLang, targetLang);
    if (translatedsupprimer != null) {
        fxSupprimer.setText(translatedsupprimer);
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
