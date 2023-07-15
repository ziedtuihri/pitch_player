package edu.connection1cinfo1.gui;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainCRUD;
import edu.pitchplayer.utils.MyConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class ViewReservationController implements Initializable {

    @FXML
    private ComboBox<String> combobox;
    @FXML
    private DatePicker dateR;
    @FXML
    private Button idbutton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadVilles();
    }

    @FXML
    private void recherche(ActionEvent event) {
        loadTerrains();
    }

    private void loadVilles() {
        List<String> villes = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT ville FROM terrain";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                String ville = resultSet.getString("ville");
                villes.add(ville);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(villes);
        combobox.setItems(observableList);
    }

   private void loadTerrains() {
    String selectedVille = combobox.getSelectionModel().getSelectedItem();
    LocalDate selectedDate = dateR.getValue();

    List<Terrain> terrains = new ArrayList<>();

    try {
        String query;
        PreparedStatement pst;
        ResultSet resultSet;

        if (selectedVille != null && selectedDate != null) {
            // Recherche des terrains disponibles dans la ville et à la date sélectionnées
            query = "SELECT * FROM terrain WHERE ville = ? AND date_disponibilite = ?";
            pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, selectedVille);
            pst.setDate(2, Date.valueOf(selectedDate));
            resultSet = pst.executeQuery();
        } else if (selectedVille != null) {
            // Recherche des terrains disponibles dans la ville sélectionnée
            query = "SELECT * FROM terrain WHERE ville = ?";
            pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, selectedVille);
            resultSet = pst.executeQuery();
        } else {
            // Aucun critère de recherche sélectionné, récupération de tous les terrains disponibles
            query = "SELECT * FROM terrain";
            pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            resultSet = pst.executeQuery();
        }

        while (resultSet.next()) {
            // Récupérer les informations du terrain depuis la base de données
            int terrainId = resultSet.getInt("id");
            String terrainNom = resultSet.getString("nom");
            // ...

            // Créer un objet Terrain
            Terrain terrain = new Terrain(terrainId, terrainNom);
            terrains.add(terrain);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Faites quelque chose avec la liste de terrains chargée
   }  // ...
}
