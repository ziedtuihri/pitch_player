package edu.connection1cinfo1.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.pitchplayer.entities.Reservation;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.utils.MyConnection;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class HistoriqueController implements Initializable {

    @FXML
    private JFXTextField txt_ville;
    @FXML
    private ImageView imagelog;
    @FXML
    private JFXTextField txt_statut;
    @FXML
    private JFXTextField txt_locataire;
    @FXML
    private JFXTextField txt_terrain;
    @FXML
    private JFXTextField txt_adresse;

    @FXML
    private JFXTextField txt_montant;
    @FXML
    private JFXTextField txt_datedebut;
    @FXML
    private JFXTextField txt_datefin;
    @FXML
    private TableView<Reservation> TableHistorique;
    @FXML
    private TableColumn<Reservation, LocalDate> datedebut;
    @FXML
    private TableColumn<Reservation, LocalDate> datefin;
    @FXML
    private TableColumn<Reservation, String> statut;
    @FXML
    private TableColumn<Reservation, String> mesag;
    @FXML
    private TableColumn<Reservation, Integer> paiement;
    
    @FXML
    private JFXComboBox<Date> search_historique;

    private ObservableList<Reservation> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showReservation();
        initTable();
        TableHistorique.setItems(data);
          startTimer();
    }
     private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkReservationDates();
            }
        }, 0, 1000);
    }

    private void checkReservationDates() {
    LocalDate currentDate = LocalDate.now();
    for (Reservation reservation : data) {
        if (reservation.getDate_fin().isBefore(currentDate)) {
            reservation.setMessage("Réservation expirée");
            Platform.runLater(() -> {
                TableHistorique.refresh();
            });
        } else if (currentDate.isBefore(reservation.getDate_debut())) {
            long daysUntilReservation = ChronoUnit.DAYS.between(currentDate, reservation.getDate_debut());
            reservation.setMessage("Jours restants : " + daysUntilReservation);
            Platform.runLater(() -> {
                TableHistorique.refresh();
            });
        } else {
            reservation.setMessage("En cours");
            Platform.runLater(() -> {
                TableHistorique.refresh();
            });
        }
    }
}



    @FXML
    private void cherche() {
        data.clear();
        String sql = "SELECT * "
                + "FROM reservation "
                + "JOIN User ON reservation.user_id = User.id "
                + "JOIN terrain ON reservation.terrain_id = terrain.id "
                + "WHERE reservation.date_debut = ?";
        String terrain = null;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setString(1, search_historique.getValue().toString());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                terrain = rs.getString("nom");
                data.add(new Reservation(rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate(), true, "ok", 55.5f, terrain, rs.getString("user.nom"), new User(), new Terrain()));
                txt_terrain.setText(rs.getString("terrain.nom"));
                txt_locataire.setText(rs.getString("user.nom"));
                txt_ville.setText(rs.getString("terrain.ville"));
                txt_adresse.setText(rs.getString("terrain.adresse"));
                txt_montant.setText(rs.getString("paiement"));
                int statut = rs.getInt("statut");
                if (statut == 1) {
                    txt_statut.setText("Confirmé");
                } else {
                    txt_statut.setText("Non confirmé");
                }
                Date dated = rs.getDate("reservation.date_debut");
                txt_datedebut.setText(String.valueOf(dated));
                Date datef = rs.getDate("reservation.date_fin");
                txt_datefin.setText(String.valueOf(datef));
                Blob blob = rs.getBlob("imag");
                if (blob != null) {
                    byte[] byteImag = blob.getBytes(1, (int) blob.length());
                    Image img = new Image(new ByteArrayInputStream(byteImag), imagelog.getFitWidth(), imagelog.getFitHeight(), true, true);
                    imagelog.setImage(img);
                }
                search_historique.getSelectionModel().clearSelection();
            }
            TableHistorique.setItems(data);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void TableHistoriqueEvent() {
        Reservation reservation = TableHistorique.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM reservation WHERE date_debut = ?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setDate(1, java.sql.Date.valueOf(reservation.getDate_debut()));

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
//                terranN.setText(rs.getString("nom_terrain"));
//                locataireN.setText(rs.getString("nom_utilisateur"));
                statut.setText(rs.getString("statut"));
                mesag.setText(rs.getString("message"));
                paiement.setText(rs.getString("paiement"));
                datedebut.setText(rs.getDate("date_debut").toString());
                datefin.setText(rs.getDate("date_fin").toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void remplir() {
        String sql1 = "SELECT terrain.nom, reservation.date_debut "
                + "FROM terrain "
                + "JOIN reservation ON terrain.id = reservation.terrain_id";
        List<Date> date = new ArrayList<>();

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql1);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                date.add(rs.getDate("date_debut"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        ObservableList<Date> observableList = FXCollections.observableArrayList(date);
        search_historique.setItems(observableList);
    }

    public void showReservation() {
        TableHistorique.getItems().clear();
        String sql = "SELECT * FROM reservation ";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Reservation(rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate(), true, "ok", 55.5f, "ok", "ok", new User(), new Terrain()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        datedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        mesag.setCellValueFactory(new PropertyValueFactory<>("message"));
        paiement.setCellValueFactory(new PropertyValueFactory<>("paiement"));
//
//        terranN.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTerrain().getNom()));
//        locataireN.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getNom()));

        TableHistorique.setItems(data);

    }

    private void initTable() {
        datedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        mesag.setCellValueFactory(new PropertyValueFactory<>("message"));
        paiement.setCellValueFactory(new PropertyValueFactory<>("paiement"));
//
//        terranN.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTerrain().getNom()));
//        locataireN.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getNom()));
    }
     

     
    }
