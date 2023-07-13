//package edu.pitchplayer.gui;
//
//import edu.pitchplayer.entities.Terrain;
//import edu.pitchplayer.services.TerrainService;
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//public class DetailAcceuilFXMLController implements Initializable {
//
//    @FXML
//    private TableView<Terrain> fxRecherche;
//    @FXML
//    private TableColumn<Terrain, String> fxNomTerrain;
//    @FXML
//    private TableColumn<Terrain, String> fxImage;
//    @FXML
//    private TextField fxNomVille;
//private String selectedVille;
//private TerrainService terrainService;
// 
//    @Override
//   public void initialize(URL url, ResourceBundle rb) {
//       fxNomVille.setText(selectedVille);
//    
//}
//  public void setSelectedVille(String ville) {
//    selectedVille = ville;
//    fxNomVille.setText(selectedVille);
//    loadTerrainsByVille(selectedVille);
//}
//private void loadTerrainsByVille(String ville) {
//    List<Terrain> terrains = terrainService.getTerrainsByVille(ville);
//    ObservableList<Terrain> terrainList = FXCollections.observableArrayList(terrains);
//
//    fxNomTerrain.setCellValueFactory(new PropertyValueFactory<>("nom"));
//    fxImage.setCellValueFactory(new PropertyValueFactory<>("image"));
//
//    fxImage.setCellFactory(column -> new TableCell<>() {
//        private final ImageView imageView = new ImageView();
//        
//        protected void updateItem(String imagePath, boolean empty) {
//            super.updateItem(imagePath, empty);
//            if (empty || imagePath == null || imagePath.isEmpty()) {
//                setGraphic(null);
//            } else {
//                try {
//                    // Utiliser une URL pour charger l'image à partir du chemin local
//                    URL imageURL = new File(imagePath).toURI().toURL();
//                    Image image = new Image(imageURL.toString());
//                    imageView.setImage(image);
//                    setGraphic(imageView);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                    // Gérer l'erreur de chargement de l'image
//                    // Afficher un message d'erreur ou une image par défaut
//                    Image defaultImage = new Image(new File("C:/Users/Raouf/Desktop/3437278_5c27f.jpg").toURI().toURL().toString());
//                    imageView.setImage(defaultImage);
//                    setGraphic(imageView);
//                }
//            }
//        }
//    });
//
//    fxRecherche.setItems(terrainList);
//}
//public void setTerrainService(TerrainService terrainService) {
//    this.terrainService = terrainService;
//}
//
//}
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainService;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class DetailAcceuilFXMLController implements Initializable {

    @FXML
    private TableView<Terrain> fxRecherche;
    @FXML
    private TableColumn<Terrain, String> fxNomTerrain;
    @FXML
    private TableColumn<Terrain, String> fxImage;
    @FXML
    private TextField fxNomVille;
    private String selectedVille;
    private TerrainService terrainService;
    @FXML
    private Label fxText;
    @FXML
    private Button fxRetour;
      private MyMemoryTranslator translator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxNomVille.setText(selectedVille);
        translator = new MyMemoryTranslator();
    }

    public void setSelectedVille(String ville) {
        selectedVille = ville;
        fxNomVille.setText(selectedVille);
        loadTerrainsByVille(selectedVille);
    }

    private void loadTerrainsByVille(String ville) {
        List<Terrain> terrains = terrainService.getTerrainsByVille(ville);
        ObservableList<Terrain> terrainList = FXCollections.observableArrayList(terrains);

        fxNomTerrain.setCellValueFactory(new PropertyValueFactory<>("nom"));
        fxImage.setCellValueFactory(new PropertyValueFactory<>("image"));

        fxImage.setCellFactory(column -> new TableCell<Terrain, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);
                if (empty || imagePath == null || imagePath.isEmpty()) {
                    setGraphic(null);
                } else {
                    try {
                        // Utiliser une URL pour charger l'image à partir du chemin local
                        URL imageURL = new File(imagePath).toURI().toURL();
                        Image image = new Image(imageURL.toString());
                        imageView.setImage(image);
                        imageView.setFitWidth(100); // Réglez la largeur souhaitée de l'image
                        imageView.setPreserveRatio(true);
                        setGraphic(imageView);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        // Gérer l'erreur de chargement de l'image
                        // Afficher un message d'erreur ou une image par défaut
                        Image defaultImage = new Image(new File("C:/Users/Raouf/Desktop/3437278_5c27f.jpg").toURI().toString());
                        imageView.setImage(defaultImage);
                        imageView.setFitWidth(50); // Réglez la largeur souhaitée de l'image
                        imageView.setPreserveRatio(true);
                        setGraphic(imageView);
                    }
                }
            }
        });

        fxRecherche.setItems(terrainList);
    }

    public void setTerrainService(TerrainService terrainService) {
        this.terrainService = terrainService;
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
    private void TraduireAction(ActionEvent event) throws UnsupportedEncodingException, IOException {
        String sourceLang = "fr"; // Langue source
        String targetLang = "en"; // Langue cible

        String nomText = fxNomTerrain.getText();
        String translatednom = translator.traduireTexte(nomText, sourceLang, targetLang);
        if (translatednom != null) {
            fxNomTerrain.setText(translatednom);
        } else {
            // Gérer les erreurs de traduction pour fxLibAdresse
        }

        // Traduire fxLibNom
        String imageText = fxImage.getText();
        String translatedimage = translator.traduireTexte(imageText, sourceLang, targetLang);
        if (translatedimage != null) {
            fxImage.setText(translatedimage);
        } else {
            // Gérer les erreurs de traduction pour fxLibNom
        }

        // Traduire fxLibVille
        String textText = fxText.getText();
        String translatedtext = translator.traduireTexte(textText, sourceLang, targetLang);
        if (translatedtext != null) {
            fxText.setText(translatedtext);
        } else {
            // Gérer les erreurs de traduction pour fxLibVille
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

