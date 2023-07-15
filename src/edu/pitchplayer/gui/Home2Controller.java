/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Reclamation;
import edu.pitchplayer.services.ReclamationCRUD;
import edu.pitchplayer.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Access
 */
public class Home2Controller implements Initializable {

    @FXML
    private AnchorPane menu_form;

    @FXML
    private Button home_btn;

    @FXML
    private Button add_recbtn;

    @FXML
    private Button stat_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button view_recbtn;

    @FXML
    private AnchorPane statrec_form;

    @FXML
    private Label nb_totale_rec;

    @FXML
    private Label unread_rec;

    @FXML
    private BarChart<?, ?> stat_chart;

    @FXML
    private AnchorPane showrec_form;

    @FXML
    private TableView<Reclamation> reclam_table_view;

    @FXML
    private TableColumn<Reclamation, Integer> rec_id;

    @FXML
    private TableColumn<Reclamation, String> rec_date;

    @FXML
    private TableColumn<Reclamation, String> rec_sujet;

    @FXML
    private TableColumn<Reclamation, String> rec_notif;

    @FXML
    private TableColumn<Reclamation, String> rec_mess;

    @FXML
    private TableColumn<Reclamation, String> rec_type;

    @FXML
    private TextField searchtf;

    @FXML
    private TextField date_show;

    @FXML
    private TextField tempo_show;

    @FXML
    private TextField notification_show;

    @FXML
    private TextField message_show;

    @FXML
    private Button rec_update;

    @FXML
    private Button rec_delete;

    @FXML
    private Button rec_clear;
    @FXML
    private AnchorPane add_Form;
    @FXML
    private ComboBox TopicCb;
    @FXML
    private TextField datetf;
    @FXML
    private TextArea commenttf;
    @FXML
    private TextField topictf;
    @FXML
    private Button nextbt;
    private AnchorPane view_reclam;

    /**
     * Initializes the controller class.
     */
    private ResultSet result;
    @FXML
    private CheckBox mychecklist;
    private AnchorPane home_form;

    public void addReclamationReset() {
        commenttf.setText("");
        topictf.setText("");

    }

    public void addReclamationShowListData() {

        ReclamationCRUD rcd = new ReclamationCRUD();
        rec_id.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
        rec_date.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("date"));
        rec_sujet.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("sujet"));
        rec_notif.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("statut"));
        rec_mess.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("objet"));

        List<Reclamation> reclamationList = rcd.displayEntities();
        ObservableList<Reclamation> dataList = FXCollections.observableArrayList(reclamationList);
        reclam_table_view.setItems(dataList);

    }

    public void Nb_Totale_Reclamation() {
        String sql = "SELECT COUNT(id) FROM reclamation";
        int countData = 0;

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                countData = rs.getInt("COUNT(id)");
            }

            nb_totale_rec.setText(String.valueOf(countData));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void Nb_Totale_Reclamation_nonlu() {
        String sql = "SELECT COUNT(id) FROM reclamation";
        int countData = 0;

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                countData = rs.getInt("COUNT(id)");
            }

            unread_rec.setText(String.valueOf(countData));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private List<String> motList;

    public List<String> getMotIndiserable() throws SQLException {
        motList = new ArrayList<>();

        Connection connection = MyConnection.getInstance().getCnx();
        String query = "SELECT mot FROM mot_indesirable";
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String mot = rs.getString("most");
            motList.add(mot);
        }

        rs.close();
        pst.close();

        return motList;
    }

    public boolean estMotIndesirable(String mot) {
        // Vérifie si le mot est présent dans la liste des mots indésirables
        return motList.contains(mot);
    }

    public boolean effectuerControleSaisie(String saisie) {
        // Divise la saisie en mots individuels
        String[] mots = saisie.split("\\s+");

        // Vérifie chaque mot dans la saisie
        for (String mot : mots) {
            if (estMotIndesirable(mot)) {
                return false;
            }
        }
        return true;

    }

    public void addReclamationAdd() {

        ReclamationCRUD rcd = new ReclamationCRUD();

        Alert alert;

        if (datetf.getText().isEmpty()
                || commenttf.getText().isEmpty()
                || topictf.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            String date = datetf.getText();
            String sujet = topictf.getText();
            String objet = commenttf.getText();
            String statut = "not notified";
            String messagetraitement = null;

            if (mychecklist.isSelected()) {
                statut = "notified";
                messagetraitement = "Une nouvelle réclamation a été ajouté Vous pouvez la consulter via votre compte";
            }

            Reclamation r = new Reclamation(date, sujet, objet, statut, null, messagetraitement);
            rcd.addEntity(r);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("../gui/Mailling.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        alert = new Alert(AlertType.INFORMATION);

        alert.setTitle(
                "Information Message");
        alert.setHeaderText(
                null);
        alert.setContentText(
                "Successfully Added!");
        alert.showAndWait();

        addReclamationShowListData();

        addReclamationReset();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        datetf.setText(new Date().toString());
        ObservableList<String> list = FXCollections.observableArrayList("Disponibilité Terrain", "Materiel", "deroulement de tournoi");
        TopicCb.setItems(list);

        Nb_Totale_Reclamation();
        Nb_Totale_Reclamation_nonlu();
        addReclamationReset();
        addReclamationShowListData();

        // TODO
    }

    @FXML
    private void update_rec(ActionEvent event) {

    }

    @FXML
    private void delete_rec(ActionEvent event) {

        Reclamation selectedReclamation = reclam_table_view.getSelectionModel().getSelectedItem();
        ReclamationCRUD rcd = new ReclamationCRUD();

        if (selectedReclamation != null) {
            // Afficher une boîte de dialogue de confirmation
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Suppression de la réclamation");
            confirmationDialog.setHeaderText("Confirmer la suppression");
            confirmationDialog.setContentText("Voulez-vous vraiment supprimer cette réclamation ?");
            rcd.delete(selectedReclamation.getId());

            // Attendre la réponse de l'utilisateur
            Optional<ButtonType> response = confirmationDialog.showAndWait();

            if (response.isPresent() && response.get() == ButtonType.OK) {
                // Supprimer la réclamation de la liste des données ou de la base de données
                // par exemple : reclamationDAO.delete(selectedReclamation);

                // Rafraîchir la TableView
                reclam_table_view.getItems().remove(selectedReclamation);

            }
        } else {
            // Aucune réclamation sélectionnée, afficher un message d'erreur
            Alert errorDialog = new Alert(Alert.AlertType.ERROR);
            errorDialog.setTitle("Erreur de suppression");
            errorDialog.setHeaderText("Sélectionnez une réclamation");
            errorDialog.setContentText("Veuillez sélectionner une réclamation à supprimer.");
            errorDialog.showAndWait();
        }
    }

    @FXML
    private void clear_champ(ActionEvent event) {
    }

    private void go_addrec(ActionEvent event) {

        add_Form.setVisible(true);

    }

    @FXML
    private void logout(ActionEvent event) {

        Stage primaryStage = (Stage) logout_btn.getScene().getWindow();

        // Fermer la fenêtre
        primaryStage.close();
    }

    @FXML
    private void go_to_next_step(ActionEvent event) {

        addReclamationAdd();

    }

    @FXML
    private void selectchoix(ActionEvent event) {

        String s = TopicCb.getSelectionModel().getSelectedItem().toString();
        topictf.setText(s);
    }

    public void ReclamationSearch(String searchTerm) {
        FilteredList<Reclamation> filteredList = new FilteredList<>(reclam_table_view.getItems());

        searchtf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredList.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    // If the search term is empty, show all Reclamations
                    return true;
                } else {
                    // Check if the search term matches any property of the Reclamation
                    String lowerCaseSearchTerm = newValue.toLowerCase();
                    return String.valueOf(reclamation.getId()).contains(lowerCaseSearchTerm)
                            || reclamation.getDate().toString().toLowerCase().contains(lowerCaseSearchTerm)
                            || reclamation.getSujet().toLowerCase().contains(lowerCaseSearchTerm)
                            || reclamation.getStatut().toLowerCase().contains(lowerCaseSearchTerm)
                            || reclamation.getObjet().toLowerCase().contains(lowerCaseSearchTerm);
                    // Add more conditions for other properties as needed
                }
            });
        });

        SortedList<Reclamation> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(reclam_table_view.comparatorProperty());
        reclam_table_view.setItems(sortedList);
    }

    @FXML
    private void switch_form(ActionEvent event) {
        if (event.getSource() == stat_btn) {
            statrec_form.setVisible(true);
            showrec_form.setVisible(false);
            add_Form.setVisible(false);
        } else if (event.getSource() == add_recbtn) {
            statrec_form.setVisible(false);
            showrec_form.setVisible(false);
            add_Form.setVisible(true);

        } else if (event.getSource() == view_recbtn) {
            statrec_form.setVisible(false);
            showrec_form.setVisible(true);
            add_Form.setVisible(false);

        } else if (event.getSource() == view_recbtn) {
            statrec_form.setVisible(false);
            showrec_form.setVisible(false);
            add_Form.setVisible(false);

        }
    }

    @FXML
    public void ReclamationSelect() {
        // Get the selected Reclamation from the TableView
        Reclamation selectedReclamation = reclam_table_view.getSelectionModel().getSelectedItem();

        int num = reclam_table_view.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        {
            // Set the values of the TextFields with the selected Reclamation data
            date_show.setText(selectedReclamation.getDate().toString());
            tempo_show.setText(selectedReclamation.getSujet());
            notification_show.setText(selectedReclamation.getStatut());
            message_show.setText(selectedReclamation.getObjet());
            // ...
        }
    }

}
