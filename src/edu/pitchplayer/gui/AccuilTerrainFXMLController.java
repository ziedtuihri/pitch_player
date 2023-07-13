package edu.pitchplayer.gui;

import edu.pitchplayer.entities.TerrainCount;
import edu.pitchplayer.services.TerrainService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class AccuilTerrainFXMLController implements Initializable {

    @FXML
    private TableView<TerrainCount> fxVilleNbr;
    @FXML
    private TableColumn<TerrainCount, String> fxVille;
    @FXML
    private TableColumn<TerrainCount, Integer> fxNombre;

    private TerrainService terrainService;
    private TableView<TerrainCount> fxTerrainTournoi;
    private TableColumn<TerrainCount, String> fxNomTerrain;
    private TableColumn<TerrainCount, Integer> fxNbrTournoi;
    private ObservableList<TerrainCount> terrainCountList;
    @FXML
    private BarChart<String, Integer> fxBarChart;
    @FXML
    private TextField fxRecherche;
    @FXML
    private Button fxAjout;
    @FXML
    private Button fxGestion;
    @FXML
    private Button fxDetail;
    @FXML
    private Button fxQuitter;
    
   private MyMemoryTranslator translator;
    @FXML
    private Button fxRechercheStade;
    @FXML
    private Button fxRechercheV;
    @FXML
    private Button fxTrie;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        terrainService = new TerrainService();
        displayTerrainCountByVille();
          translator = new MyMemoryTranslator();
   
    }

  private void displayTerrainCountByVille() {
        List<TerrainCount> terrainCounts = terrainService.getTerrainCountByVille();
        terrainCountList = FXCollections.observableArrayList(terrainCounts);

        fxVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        fxNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        fxVilleNbr.setItems(terrainCountList);

        createBarChart();
    }

    private void createBarChart() {
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis) {
        @Override
        public String toString(Number object) {
            return String.format("%.0f", object.doubleValue());
        }
    });

    fxBarChart.setAnimated(false); // Disable animation for a smoother display
    fxBarChart.getXAxis().setLabel("ville");
    fxBarChart.getYAxis().setLabel("");

    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    series.setName("Nombre de Terrain par ville");

    for (TerrainCount terrainCount : terrainCountList) {
        series.getData().add(new XYChart.Data<>(terrainCount.getVille(), terrainCount.getNombre()));
    }

    fxBarChart.getData().add(series);
}


    

    @FXML
    private void AjouterTerrainAction(ActionEvent event) {
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

    @FXML
    private void AfficherTerrainAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherTerrainFXML.fxml"));
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
    private void RechercheTerainAction(ActionEvent event) {
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
    private void RechercheAction(ActionEvent event) {
         String recherche = fxRecherche.getText(); // Récupérer le texte saisi dans le champ de recherche

    List<TerrainCount> terrainCounts = terrainService.getTerrainCountByVille(); // Récupérer la liste complète des villes
    ObservableList<TerrainCount> terrainCountList = FXCollections.observableArrayList();

    for (TerrainCount terrainCount : terrainCounts) {
        String ville = terrainCount.getVille().toLowerCase(); // Convertir le nom de la ville en minuscules

        if (ville.contains(recherche.toLowerCase())) { // Vérifier si la ville contient la recherche (ignorant la casse)
            terrainCountList.add(terrainCount); // Ajouter la ville à la liste des villes filtrées
        } else if (String.valueOf(terrainCount.getNombre()).contains(recherche)) { // Vérifier si le nombre de terrains correspond à la recherche
            terrainCountList.add(terrainCount); // Ajouter la ville à la liste des villes filtrées
        }
    }

    fxVilleNbr.setItems(terrainCountList);

    // Mettre à jour les données du graphique avec les villes filtrées
    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    series.setName("Terrain Count");

    for (TerrainCount terrainCount : terrainCountList) {
        series.getData().add(new XYChart.Data<>(terrainCount.getVille(), terrainCount.getNombre()));
    }

//    fxBarChart.getData().setAll(series);
    }

    @FXML
    private void DetailAction(ActionEvent event) {
        TerrainCount selectedTerrain = fxVilleNbr.getSelectionModel().getSelectedItem();
    if (selectedTerrain != null) {
        String selectedVille = selectedTerrain.getVille();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/DetailAcceuilFXML.fxml"));
            Parent root = loader.load();
            DetailAcceuilFXMLController detailController = loader.getController();
            detailController.setTerrainService(terrainService);
            detailController.setSelectedVille(selectedVille);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

  @FXML
private void TraduireAction(ActionEvent event) throws UnsupportedEncodingException, IOException {
    String sourceLang = "fr"; // Langue source
    String targetLang = "en"; // Langue cible

     String villeText = fxVille.getText();
    String translatedville = translator.traduireTexte(villeText, sourceLang, targetLang);
    if (translatedville != null) {
        fxVille.setText(translatedville);
    } else {
        // Gérer les erreurs de traduction pour fxLibAdresse
    }
    
    // Traduire fxLibAdresse
    String nombreText = fxNombre.getText();
    String translatednombre = translator.traduireTexte(nombreText, sourceLang, targetLang);
    if (translatednombre != null) {
        fxNombre.setText(translatednombre);
    } else {
        // Gérer les erreurs de traduction pour fxLibAdresse
    }

    // Traduire fxLibLongueur
    String rechercheText = fxRechercheV.getText();
    String translatedrecherche = translator.traduireTexte(rechercheText, sourceLang, targetLang);
    if (translatedrecherche != null) {
        fxRechercheV.setText(translatedrecherche);
    } else {
        // Gérer les erreurs de traduction pour fxLibLongueur
    }
     // Traduire fxLibLongueur
    String ajoutText = fxAjout.getText();
    String translatedajout= translator.traduireTexte(ajoutText, sourceLang, targetLang);
    if (translatedajout != null) {
        fxAjout.setText(translatedajout);
    } else {
        // Gérer les erreurs de traduction pour fxLibLongueur
    }

    // Traduire fxLibImage
    String gestionText = fxGestion.getText();
    String translatedgestion = translator.traduireTexte(gestionText, sourceLang, targetLang);
    if (translatedgestion != null) {
        fxGestion.setText(translatedgestion);
    } else {
        // Gérer les erreurs de traduction pour fxLibImage
    }

    // Traduire fxAjouter
    String quitterText = fxQuitter.getText();
    String translatedquitter = translator.traduireTexte(quitterText, sourceLang, targetLang);
    if (translatedquitter != null) {
        fxQuitter.setText(translatedquitter);
    } else {
        // Gérer les erreurs de traduction pour fxAjouter
    }

    // Traduire fxAnnuler
    String RechercheStadeText = fxRechercheStade.getText();
    String translatedRechercheStade= translator.traduireTexte(RechercheStadeText, sourceLang, targetLang);
    if (translatedRechercheStade != null) {
        fxRechercheStade.setText(translatedRechercheStade);
    } else {
        // Gérer les erreurs de traduction pour fxAnnuler
    }

    // Traduire fxRetour
    String detailText = fxDetail.getText();
    String translateddetail = translator.traduireTexte(detailText, sourceLang, targetLang);
    if (translateddetail != null) {
        fxDetail.setText(translateddetail);
    } else {
        // Gérer les erreurs de traduction pour fxRetour
    }
   
    String trieText = fxTrie.getText();
    String translatedtrie = translator.traduireTexte(trieText, sourceLang, targetLang);
    if (translatedtrie != null) {
        fxTrie.setText(translatedtrie);
    } else {
        // Gérer les erreurs de traduction pour fxRetour
    }
}

    @FXML
    private void TrieAction(ActionEvent event) {
         ObservableList<TerrainCount> items = fxVilleNbr.getItems();
    if (!items.isEmpty()) {
        // Tri par nom de ville en ordre alphabétique croissant
        items.sort(Comparator.comparing(TerrainCount::getVille, Comparator.nullsLast(String::compareToIgnoreCase)));

        // Mettre à jour la TableView avec les données triées
        fxVilleNbr.refresh();
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
    }

    @FXML
    private void QuitterAction(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Quitter l'application");
    alert.setContentText("Êtes-vous sûr de vouloir quitter l'application ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Fermez l'application ou effectuez les actions de sortie nécessaires
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    }
}


