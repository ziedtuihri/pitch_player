/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import edu.pitchplayer.entities.Reclamation;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.services.UserCRUD;
import edu.pitchplayer.utils.EmailSender;
import edu.pitchplayer.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Access
 */
public class MaillingController implements Initializable {

    @FXML
    private ComboBox<String> adressemail;
    @FXML
    private TextField sujetmail;
    @FXML
    private TextField objetmailtf;
    @FXML
    private Button envoibtn;
    @FXML
    private TextField mailchoisi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<String> emailList = getEmailListFromDataSource();

            ObservableList<String> observableEmailList = FXCollections.observableArrayList(emailList);

            adressemail.setItems(observableEmailList);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des adresses e-mail : " + e.getMessage());
        }// TODO

    }

    @FXML
    public void Remplircombobox() {

        String s = adressemail.getSelectionModel().getSelectedItem().toString();
        mailchoisi.setText(s);

    }

    private List<String> getEmailListFromDataSource() throws SQLException {
        List<String> emailList = new ArrayList<>();

        Connection connection = MyConnection.getInstance().getCnx();
        String query = "SELECT email FROM user";
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String email = rs.getString("email");
            emailList.add(email);
        }

        rs.close();
        pst.close();

        return emailList;
    }

    @FXML
    private void envoimail(ActionEvent event) {

        Alert alert;

        EmailSender em = new EmailSender();

        em.sendEmail(mailchoisi.getText(), sujetmail.getText(), objetmailtf.getText());

        alert = new Alert(AlertType.INFORMATION);

        alert.setTitle(
                "Information Message");
        alert.setHeaderText(
                null);
        alert.setContentText(
                "email sent successfully!");
        alert.showAndWait();
    }

}
