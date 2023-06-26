/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;  
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author Raouf
 */
public class DisponibiliteTerrainFXMLController implements Initializable {

    @FXML
    private DatePicker fxDate;
    @FXML
    private TableView<String> fxTable;
    @FXML
    private TableColumn<String, String> fxHeures;

    private List<String> heuresList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 heuresList = new ArrayList<>();
    configureTableColumn();

    fxDate.setOnAction(event -> {
        LocalDate selectedDate = fxDate.getValue();
        if (selectedDate != null) {
            populateHeuresList();
        }
    });
    }    
    private void configureTableColumn() {
    fxHeures.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
}
    private void populateHeuresList() {
    heuresList.clear();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    LocalTime startTime = LocalTime.of(0, 0);
    while (startTime.isBefore(LocalTime.of(23, 0))) {
        LocalTime endTime = startTime.plusHours(2);
        String heureRange = startTime.format(formatter) + "-" + endTime.format(formatter);
        heuresList.add(heureRange);
        startTime = endTime;
    }

    ObservableList<String> observableHeuresList = FXCollections.observableArrayList(heuresList);
    fxTable.setItems(observableHeuresList);
}
}
