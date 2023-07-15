/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon.USER;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.utils.MyConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class LocataireController implements Initializable {

    @FXML
    private JFXTextField texte_search;
    @FXML
    private JFXTextField texte_prenom;
    @FXML
    private JFXTextField texte_adresse;
    @FXML
    private JFXTextField texte_nom;
    @FXML
    private JFXTextField texte_numtph;
    @FXML
    private JFXTextField texte_email;
    @FXML
    private TableView<User> tablelocataire;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btnremove;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUser();
    }
    public ObservableList<User> data = FXCollections.observableArrayList();

    public void showUser() {
        tablelocataire.getItems().clear();
        String sql = "SELECT * FROM User";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("phone"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<User, String>("adresse"));
        phone.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        tablelocataire.setItems(data);
    }

    @FXML
    private void addlocataire() {
        String id = texte_search.getText();
        String nom = texte_nom.getText();
        String prenom = texte_prenom.getText();
        String adresse = texte_adresse.getText();
        String phone = texte_numtph.getText();
        String email = texte_email.getText();
        String sql = "insert into User(id, nom, prenom, adresse, phone, email) values(?, ?, ?, ?, ?, ?)";

        if (!id.equals("") && !nom.equals("") && !prenom.equals("") && !adresse.equals("") && !phone.equals("") && !email.equals("")) {
            try {
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, nom);
                pst.setString(3, prenom);
                pst.setString(4, adresse);
                pst.setString(5, phone);
                pst.setString(6, email);

                pst.execute();

                texte_search.setText("");
                texte_nom.setText("");
                texte_prenom.setText("");
                texte_adresse.setText("");
                texte_numtph.setText("");
                texte_email.setText("");

                Alert alert = new Alert(AlertType.CONFIRMATION, "Locataire ajouté avec succès", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();

                showUser();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void updatelocataire() {
        String id = texte_search.getText();
        String nom = texte_nom.getText();
        String prenom = texte_prenom.getText();
        String adresse = texte_adresse.getText();
        String phone = texte_numtph.getText();
        String email = texte_email.getText();
        String sql = "update User set id=?, nom=?, prenom=?, adresse=?, phone=?, email=? where id='" + texte_search.getText() + "'";
        if (!id.equals("") && !nom.equals("") && !prenom.equals("") && !adresse.equals("") && !phone.equals("") && !email.equals("")) {

            try {
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, nom);
                pst.setString(3, prenom);
                pst.setString(4, adresse);
                pst.setString(5, phone);
                pst.setString(6, email);

                pst.executeUpdate();

                texte_search.setText("");
                texte_nom.setText("");
                texte_prenom.setText("");
                texte_adresse.setText("");
                texte_numtph.setText("");
                texte_email.setText("");

                Alert alert = new Alert(AlertType.CONFIRMATION, "Locataire modifier avec succès", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();

                showUser();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void removelocataire() {
        String sql = "DELETE FROM User WHERE id = ?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setString(1, texte_search.getText());
            pst.executeUpdate();
            texte_search.setText("");
            texte_nom.setText("");
            texte_prenom.setText("");
            texte_adresse.setText("");
            texte_numtph.setText("");
            texte_email.setText("");
            Alert alert = new Alert(AlertType.CONFIRMATION, "Locataire supprimé avec succès", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            showUser();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void chercherlocataire() {
        String sql = "select nom, prenom, adresse, phone, email from User where nom='" + texte_search.getText() + "'";
        int m = 0;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                texte_nom.setText(rs.getString("nom"));
                texte_prenom.setText(rs.getString("prenom"));
                texte_adresse.setText(rs.getString("adresse"));
                texte_numtph.setText(rs.getString("phone"));
                texte_email.setText(rs.getString("email"));
                m = 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        if (m == 0) {
            Alert alert = new Alert(AlertType.ERROR, "Aucun locataire trouvé avec le nom=" + texte_search.getText(), javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void tablelocataireEvent() {
        User user = tablelocataire.getSelectionModel().getSelectedItem();
        String sql = "select*from User where id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
               texte_nom.setText(rs.getString("nom"));
                texte_prenom.setText(rs.getString("prenom"));
                texte_adresse.setText(rs.getString("adresse"));
                texte_numtph.setText(rs.getString("phone"));
                texte_email.setText(rs.getString("email")); 
                texte_search.setText(rs.getString("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }

    }

}
