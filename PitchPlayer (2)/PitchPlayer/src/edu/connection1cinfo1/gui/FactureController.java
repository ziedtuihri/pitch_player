package edu.connection1cinfo1.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.pitchplayer.utils.MyConnection;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FactureController implements Initializable {

    @FXML
    private JFXTextField txt_locataire;
    @FXML
    private JFXTextField txt_adresseT;
    @FXML
    private JFXComboBox<Date> combo_date_debut;
    @FXML
    private JFXTextField txt_montant;
    @FXML
    private JFXTextField txt_ville;
    @FXML
    private ImageView imagelog;
    @FXML
    private JFXComboBox<String> combo_terrain;
    @FXML
    private JFXTextField txt_date_debut;
    @FXML
    private JFXTextField txt_date_fin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplirTerrain();
        combo_terrain.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                recupererDatesReservationTerrain(newValue);
            }
        });
        remplir();
    }

    @FXML
    private void imprim() {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("facture.pdf"));
            doc.open();
            String format = "dd/MM/yyyy HH:mm";
            SimpleDateFormat formater = new SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();

            com.itextpdf.text.Image imag = com.itextpdf.text.Image.getInstance("C:\\Users\\Oussama\\OneDrive\\Images\\Logo2.png");
            imag.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            doc.add(imag);

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("Facture a :" + txt_locataire.getText() + ""
                    + "\nVille de terrain :" + txt_ville.getText() + ""
                    + "\nAdresse de terrain :" + txt_adresseT.getText() + ""
                    + "\nMontant de reservation  :" + txt_montant.getText() + ""
                    + "\n\nFaire a Esprit le " + formater.format(date) + ""
                    + "\n Signature", font);
            doc.add(paragraph);

            // Generate the QR code
            String locataire = txt_locataire.getText();
            String terrain = combo_terrain.getValue();
            String adresse = txt_adresseT.getText();
            String ville = txt_ville.getText();
            String montant = txt_montant.getText();
            String dateDebut = txt_date_debut.getText();
            String dateFin = txt_date_fin.getText();

            if (locataire.isEmpty()) {
                locataire = "N/A";
            }
            if (terrain == null) {
                terrain = "N/A";
            }
            if (adresse.isEmpty()) {
                adresse = "N/A";
            }
            if (ville.isEmpty()) {
                ville = "N/A";
            }
            if (montant.isEmpty()) {
                montant = "N/A";
            }
            if (dateDebut.isEmpty()) {
                dateDebut = "N/A";
            }
            if (dateFin.isEmpty()) {
                dateFin = "N/A";
            }

            String qrContent = "Nom du locataire: " + locataire + "\n" +
                "Terrain: " + terrain + "\n" +
                "Adresse: " + adresse + "\n" +
                "Ville: " + ville + "\n" +
                "Montant: " + montant + "\n" +
                "Date de début: " + dateDebut + "\n" +
                "Date de fin: " + dateFin;

            int qrSize = 200; // Taille de l'image du code QR en pixels

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, qrSize, qrSize);

            BufferedImage qrImage = toBufferedImage(bitMatrix);
            com.itextpdf.text.Image qrPdfImage = com.itextpdf.text.Image.getInstance(qrImage, null);
            doc.add(qrPdfImage);

            doc.close();
            Desktop.getDesktop().open(new File("facture.pdf"));
        } catch (FileNotFoundException | DocumentException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WriterException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void cherche() {
        String sql = "SELECT * "
                + "FROM reservation "
                + "JOIN User ON reservation.user_id = User.id "
                + "JOIN terrain ON reservation.terrain_id = terrain.id "
                + "WHERE reservation.date_debut = ?";
        String terrain = null;

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setString(1, combo_date_debut.getValue().toString());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                terrain = rs.getString("nom");

                txt_ville.setText(rs.getString("terrain.ville"));
                txt_locataire.setText(rs.getString("user.nom"));
                txt_adresseT.setText(rs.getString("terrain.adresse"));
                txt_montant.setText(rs.getString("paiement"));
                txt_date_debut.setText(rs.getString("date_debut"));
                txt_date_fin.setText(rs.getString("date_fin"));

                Date dated = rs.getDate("reservation.date_debut");

                Blob blob = rs.getBlob("imag");
                if (blob != null) {
                    byte[] byteImag = blob.getBytes(1, (int) blob.length());
                    Image img = new Image(new ByteArrayInputStream(byteImag), imagelog.getFitWidth(), imagelog.getFitHeight(), true, true);
                    imagelog.setImage(img);
                }
                combo_date_debut.getSelectionModel().clearSelection();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void remplirTerrain() {
        String sql = "SELECT DISTINCT terrain.nom "
                + "FROM terrain "
                + "JOIN reservation ON terrain.id = reservation.terrain_id";

        List<String> terrains = new ArrayList<>();
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                terrains.add(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(terrains);
        combo_terrain.setItems(observableList);
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
        combo_date_debut.setItems(observableList);
    }

    private void recupererDatesReservationTerrain(String terrain) {
        String sql = "SELECT reservation.date_debut "
                + "FROM terrain "
                + "JOIN reservation ON terrain.id = reservation.terrain_id "
                + "WHERE terrain.nom = ?";

        List<Date> dates = new ArrayList<>();
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst.setString(1, terrain);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dates.add(rs.getDate("date_debut"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        ObservableList<Date> observableList = FXCollections.observableArrayList(dates);
        combo_date_debut.setItems(observableList);
    }

    private BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }
}
