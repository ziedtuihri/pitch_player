package edu.pitchplayer.gui;

import edu.pitchplayer.entities.TerrainCount;
import edu.pitchplayer.services.TerrainService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AccuilTerrainFXMLController implements Initializable {

    @FXML
    private TableView<TerrainCount> fxVilleNbr;
    @FXML
    private TableColumn<TerrainCount, String> fxVille;
    @FXML
    private TableColumn<TerrainCount, Integer> fxNombre;

    private TerrainService terrainService;
    @FXML
    private TableView<TerrainCount> fxTerrainTournoi;
    @FXML
    private TableColumn<TerrainCount, String> fxNomTerrain;
    @FXML
    private TableColumn<TerrainCount, Integer> fxNbrTournoi;
    @FXML
    private BarChart<String, Integer> fxBarChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        terrainService = new TerrainService();
        displayTerrainCountByVille();
        displayTerrainCountByTournoi();
    }

   private void displayTerrainCountByVille() {
    List<TerrainCount> terrainCounts = terrainService.getTerrainCountByVille();
    ObservableList<TerrainCount> terrainCountList = FXCollections.observableArrayList(terrainCounts);

    fxVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
    fxNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    fxVilleNbr.setItems(terrainCountList);

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Terrain Count");

    for (TerrainCount terrainCount : terrainCountList) {
        series.getData().add(new XYChart.Data<>(terrainCount.getVille(), terrainCount.getNombre()));
    }

    barChart.getData().add(series);
    xAxis.setLabel("City");
    yAxis.setLabel("Number of Terrains");

    fxBarChart.setAnimated(false); // Disable animation for a smoother display

    // Create a new XYChart.Series<String, Integer> and copy the data
    XYChart.Series<String, Integer> integerSeries = new XYChart.Series<>();
    integerSeries.setName(series.getName());
    for (XYChart.Data<String, Number> data : series.getData()) {
        integerSeries.getData().add(new XYChart.Data<>(data.getXValue(), data.getYValue().intValue()));
    }

    // Create a new ObservableList of type Series<String, Integer>
    ObservableList<XYChart.Series<String, Integer>> chartData = FXCollections.observableArrayList();
    chartData.add(integerSeries);

    // Set the data to the BarChart using the new ObservableList
    fxBarChart.setData(chartData);
}

    private void displayTerrainCountByTournoi() {
        List<TerrainCount> terrainCounts = terrainService.getTerrainCountByTournoi();
        ObservableList<TerrainCount> terrainCountList = FXCollections.observableArrayList(terrainCounts);

        fxNomTerrain.setCellValueFactory(new PropertyValueFactory<>("nomTerrain"));
        fxNbrTournoi.setCellValueFactory(new PropertyValueFactory<>("nombreTournoi"));

        fxTerrainTournoi.setItems(terrainCountList);
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

}
