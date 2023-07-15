
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.gui;

import com.jfoenix.controls.JFXTextField;
import edu.pitchplayer.utils.MyConnection;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.text.Position;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class AcceuilleController implements Initializable {

    @FXML
    private Label labelnbr;
    @FXML
    private ImageView imagelog;
    @FXML
    private JFXTextField textelargeur;
    @FXML
    private JFXTextField texteville;
    @FXML
    private JFXTextField textelongeur;
    @FXML
    private JFXTextField adressetexte;
    @FXML
    private Button btnnexte;
    @FXML
    private Button btnprevious;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showterrain();
    }

    public void showterrain() {
        try {
            String sql1 = "SELECT COUNT(*) FROM terrain WHERE id NOT IN (SELECT terrain_id FROM reservation)";
            int i = 0;
        byte byteImag[];
        Blob blob;

            try {
                PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();
                if (rs1.next()) {
                    i = rs1.getInt(1);
                }
                labelnbr.setText(Integer.toString(i));

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            String sql2 = "SELECT imag, largeur, longueur, ville, adresse FROM terrain WHERE id NOT IN (SELECT terrain_id FROM reservation)";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            if (rs2.next()) {
                textelargeur.setText(rs2.getString("largeur"));
                textelongeur.setText(rs2.getString("longueur"));
                texteville.setText(rs2.getString("ville"));
                adressetexte.setText(rs2.getString("adresse"));
            blob = rs2.getBlob("imag");
            byteImag = blob.getBytes(1, (int) blob.length());
            Image img = new Image(new ByteArrayInputStream(byteImag), imagelog.getFitWidth(), imagelog.getFitHeight(), true, true);
            imagelog.setImage(img);

            } else {
                System.out.println("aucun");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void nexte() {

        String adresse = adressetexte.getText();
        String sql3 = "select id from terrain where adresse='" + adresse + "'";
        int position = 0;
        try {

            PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareStatement(sql3);
            ResultSet rs3 = pst3.executeQuery();
            if (rs3.next()) {
                position = rs3.getInt("id");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(position);
        String sql4 = "SELECT imag, largeur, longueur, ville, adresse FROM terrain WHERE id NOT IN (SELECT terrain_id FROM location)and id >'" + position + "'";
        int i = 0;
        byte byteImag[];
        Blob blob;
        try {
            PreparedStatement pst4 = MyConnection.getInstance().getCnx().prepareStatement(sql4);
            ResultSet rs4 = pst4.executeQuery();
            if (rs4.next()) {
                textelargeur.setText(rs4.getString("largeur"));
                textelongeur.setText(rs4.getString("longueur"));
                texteville.setText(rs4.getString("ville"));
                adressetexte.setText(rs4.getString("adresse"));
            blob = rs4.getBlob("imag");
            byteImag = blob.getBytes(1, (int) blob.length());
            Image img = new Image(new ByteArrayInputStream(byteImag), imagelog.getFitWidth(), imagelog.getFitHeight(), true, true);
            imagelog.setImage(img);

            } else {
                System.out.println("aucun");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void previous() {
        String adresse = adressetexte.getText();
        String sql3 = "select id from terrain where adresse='" + adresse + "'";
        int position = 0;
        try {

            PreparedStatement pst3 = MyConnection.getInstance().getCnx().prepareStatement(sql3);
            ResultSet rs3 = pst3.executeQuery();
            if (rs3.next()) {
                position = rs3.getInt("id");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(position);
        String sql4 = "SELECT imag, largeur, longueur, ville, adresse FROM terrain WHERE id NOT IN (SELECT terrain_id FROM reservation)and id <'" + position + "'";
        int i = 0;
        byte byteImag[];
        Blob blob;
        try {
            PreparedStatement pst4 = MyConnection.getInstance().getCnx().prepareStatement(sql4);
            ResultSet rs4 = pst4.executeQuery();
            if (rs4.next()) {
                textelargeur.setText(rs4.getString("largeur"));
                textelongeur.setText(rs4.getString("longueur"));
                texteville.setText(rs4.getString("ville"));
                adressetexte.setText(rs4.getString("adresse"));
            blob = rs4.getBlob("imag");
            byteImag = blob.getBytes(1, (int) blob.length());
            Image img = new Image(new ByteArrayInputStream(byteImag), imagelog.getFitWidth(), imagelog.getFitHeight(), true, true);
            imagelog.setImage(img);

            } else {
                System.out.println("aucun");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
