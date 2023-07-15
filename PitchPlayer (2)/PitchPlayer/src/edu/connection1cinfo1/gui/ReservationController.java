/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.pitchplayer.entities.Reservation;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.services.ReservationCRUD;
import edu.pitchplayer.utils.MyConnection;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.ZoneId;
import java.util.Date;
//import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class ReservationController implements Initializable {

    @FXML
    private JFXTextField txt_searchloc;
    @FXML
    private JFXTextField txt_adres;
    @FXML
    private JFXTextField txt_tph;
    @FXML
    private JFXTextField txt_mail;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXTextField txt_periode;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_prenom;
    @FXML
    private JFXTextField tex_nomT;
    @FXML
    private JFXTextField tx_adresseT;
    @FXML
    private ImageView imagelog;
    @FXML
    private JFXTextField txt_searchT;
    @FXML
    private JFXTextField tx_villeT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean isBetween(java.sql.Date my_date, java.sql.Date my_debut, java.sql.Date my_fin) {
        return (my_date.equals(my_debut) || my_date.after(my_debut) && (my_date.equals(my_fin) || my_date.before(my_fin)));

    }

    private boolean isOut(java.sql.Date date_debut, java.sql.Date date_fin, java.sql.Date my_debut, java.sql.Date my_fin) {
        return (date_debut.before(my_debut) && date_fin.after(my_fin));
    }

    @FXML
    private void addResevation() {
    LocalDate selectedDate = datedebut.getValue(); // Récupérer la date sélectionnée par l'utilisateur depuis le DatePicker
    LocalDate selectedDatef = datefin.getValue(); // Récupérer la date sélectionnée par l'utilisateur depuis le DatePicker

    Reservation r = new Reservation(selectedDate, selectedDatef, true, "ok", 55.5f, "ok", "ok", new User(2), new Terrain(7));
    ReservationCRUD rcd = new ReservationCRUD();
    rcd.addEntity(r);

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Réservation ajoutée avec succès", javafx.scene.control.ButtonType.OK);
    alert.showAndWait();
    }

    @FXML
    private void searchterrain() {
        String sql1 = "SELECT nom, adresse, ville, imag FROM terrain WHERE ville = ?";
        int nb = 0;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql1);
            pst.setString(1, txt_searchT.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tex_nomT.setText(rs.getString("nom"));
                tx_adresseT.setText(rs.getString("adresse"));
                tx_villeT.setText(rs.getString("ville"));
                Blob blob = rs.getBlob("imag");
                byte[] byteImag = blob.getBytes(1, (int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byteImag), imagelog.getFitWidth(), imagelog.getFitHeight(), true, true);
                imagelog.setImage(img);
                txt_searchT.setText("");
                nb = 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (nb == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun Terrain trouvé avec la ville = " + txt_searchT.getText(), javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void searchlocataire() {
        String sql = "SELECT nom, prenom, adresse, phone, email FROM User WHERE nom = ?";
        int nbr = 0;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setString(1, txt_searchloc.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txt_nom.setText(rs.getString("nom"));
                txt_prenom.setText(rs.getString("prenom"));
                txt_adres.setText(rs.getString("adresse"));
                txt_tph.setText(rs.getString("phone"));
                txt_mail.setText(rs.getString("email"));
                nbr = 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        if (nbr == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun locataire trouvé avec le nom = " + txt_searchloc.getText(), javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        } else {

        }
    }

    @FXML
    private void periode() {
        java.sql.Date dateDebut = java.sql.Date.valueOf(datedebut.getValue());
        java.sql.Date dateFin = java.sql.Date.valueOf(datefin.getValue());

        if (dateFin.before(dateDebut)) {
            String dated = datedebut.getValue().toString();
            String datef = datefin.getValue().toString();
            String message = "La date de fin " + datef + " ne peut pas être antérieure à la date de début " + dated + ".";
            Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
            alert.showAndWait();
            return;
        }

        long days = ChronoUnit.DAYS.between(dateDebut.toLocalDate(), dateFin.toLocalDate());
        txt_periode.setText(String.valueOf(days));
    }
}
