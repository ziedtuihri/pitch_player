/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Equipe;
import edu.pitchplayer.services.EquipeDetailsService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

/**
 * FXML Controller class
 *
 * @author WIJDEN
 */
public class MainEquipeViewController implements Initializable {

    @FXML
    private TextField captainNameField;

    @FXML
    private TextField captainAddressField;

    @FXML
    private FlowPane equipeFlowPane;

    @FXML
    private Pagination pagination;

    @FXML
    private Button searchButton;

    private Popup suggestionsPopup;
    private ListView<String> suggestionsListView;
    private ObservableList<String> suggestions;

    private EquipeDetailsService equipeDetailsService;

    public void setCaptainName(String captainName) {
        captainNameField.setText(captainName);
    }

    public void setCaptainAddress(String captainAddress) {
        captainAddressField.setText(captainAddress);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        equipeDetailsService = new EquipeDetailsService();

        // initialize suggestions popup
        suggestionsPopup = new Popup();
        suggestionsPopup.setAutoHide(true);
        suggestionsListView = new ListView<>();
       // suggestionsListView.getStyleClass().add("suggestions-list"); to be worked on later
        suggestionsPopup.getContent().add(suggestionsListView);

        // initialize suggestions list
        suggestions = FXCollections.observableArrayList();
        suggestionsListView.setItems(suggestions);

        // setting up the suggestion list cell factory
        suggestionsListView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
            }
        });

        // set up the suggestion list selection behavior
        suggestionsListView.setOnMouseClicked(event -> {
            String selectedSuggestion = suggestionsListView.getSelectionModel().getSelectedItem(); //selects name of captain from the list
            if (selectedSuggestion != null) { 
                captainNameField.setText(selectedSuggestion); //insert the value selected to the textfield
                handleSearch(); //call search
                suggestionsPopup.hide(); // hide that list
            }
        });

        // add event listeners to the text fields
        captainNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            handleCaptainNameFieldTextChanged(newValue);
            showSuggestionsPopup();
        });
        captainAddressField.textProperty().addListener((observable, oldValue, newValue) -> handleSearch());

        // Add event handler to the search button
        searchButton.setOnAction(event -> handleSearch());

    }

    @FXML
    public void handleSearch() {
        String captainName = captainNameField.getText();
        String captainAddress = captainAddressField.getText();

        try {
            List<Equipe> equipeList = equipeDetailsService.searchEquipeByCaptain(captainName, captainAddress);
            updateEquipeFlowPane(equipeList);
        } catch (SQLException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        }
    }

    private void updateEquipeFlowPane(List<Equipe> equipeList) {
        equipeFlowPane.getChildren().clear(); //to clear any previous search from the new FlowPane

        if (equipeList == null || equipeList.isEmpty()) {
            System.out.println("No equipe found.");
            return;
        }

        for (Equipe equipe : equipeList) {

            try {
                // Container for each equipe
                VBox equipeContainer = new VBox();

                // Add labels or other controls to display equipe information
                Label nomLabel = new Label("Nom: " + equipe.getNom());
                Label anneeFondationLabel = new Label("Année de Fondation: " + equipe.getAnneeFondation());

                equipeContainer.getChildren().addAll(nomLabel, anneeFondationLabel);

                equipeFlowPane.getChildren().add(equipeContainer);
            } catch (Exception e) {
                System.out.println("Error displaying equipe: " + e.getMessage());
            }
        }

    }

    private void handleCaptainNameFieldTextChanged(String newValue) {
        try {
            List<String> suggestionsList = equipeDetailsService.getSuggestionsForCaptainName(newValue);
            suggestions.setAll(suggestionsList);

            if (suggestionsList.isEmpty()) {
                suggestionsPopup.hide();
            } else {
                suggestionsPopup.show(captainNameField.getScene().getWindow());
            }
        } catch (SQLException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        }
    }

    private void showSuggestionsPopup() {
        if (!suggestions.isEmpty()) {
            suggestionsPopup.show(captainNameField.getScene().getWindow());
        } else {
            suggestionsPopup.hide();
        }
    }

}
