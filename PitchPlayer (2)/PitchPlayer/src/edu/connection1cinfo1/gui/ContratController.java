package edu.connection1cinfo1.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import edu.pitchplayer.utils.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.awt.*;
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
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import com.itextpdf.text.Font;

import static javafx.scene.text.Font.font;

public class ContratController implements Initializable {

    @FXML
    private JFXTextField txt_locataire;
    @FXML
    private JFXTextField txt_terrain;
    @FXML
    private JFXTextField txt_adresse;
    @FXML
    private JFXComboBox<Date> search_location;
    @FXML
    private JFXTextField txt_montant;
    @FXML
    private JFXTextField txt_ville;
    @FXML
    private ImageView imagelog;
    @FXML
    private JFXTextField txt_datedebut;
    @FXML
    private JFXTextField txt_datefin;
    @FXML
    private JFXTextField txt_statut;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplirCombo();
    }

    @FXML
    private void imprim() {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("contrat.pdf"));
            doc.open();
            String format = "dd/MM/yyyy HH:mm";
            SimpleDateFormat formater = new SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();
            com.itextpdf.text.Image imag = com.itextpdf.text.Image.getInstance("C:\\Users\\Oussama\\OneDrive\\Images\\Logo2.png");
            imag.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            doc.add(imag);
            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("Entre: Pitchplayer"
                    + "\nDemeurant à : ESPRIT"
                    + "\nSous la direction du Professeur GAGROURI KARRAY et du Professeur ABDELAZIZE MEZRI"
                    + "\nD'une part"
                    + "\nEt " + txt_locataire.getText() + ""
                    + "\nDemeurant à : ESPRIT"
                    + "\nD'identier : 221SMT0551,etudiant a 1cinfo1"
                    + "\nD'autre part"
                    + "\nFaire a Esprit le " + formater.format(date) + ""
                    + "\n Signature"
                    , font);
            doc.add(paragraph);

            // Ajouter le code QR
            String qrContent = "Nom du locataire: " + txt_locataire.getText() + "\n" +
                   "Terrain: " + txt_terrain.getText() + "\n" +
                   "Adresse: " + txt_adresse.getText() + "\n" +
                   "Ville: " + txt_ville.getText() + "\n" +
                   "Montant: " + txt_montant.getText() + "\n" +
                   "Date de début: " + txt_datedebut.getText() + "\n" +
                   "Date de fin: " + txt_datefin.getText();; // Remplacez par le contenu réel que vous souhaitez
            int qrSize = 200; // Taille du code QR en pixels

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, qrSize, qrSize,
                    createQRCodeHints());

            BufferedImage qrImage = toBufferedImage(bitMatrix);
            com.itextpdf.text.Image qrPdfImage = com.itextpdf.text.Image.getInstance(qrImage, null);
            doc.add(qrPdfImage);

            doc.close();
            Desktop.getDesktop().open(new File("contrat.pdf"));
        } catch (FileNotFoundException | DocumentException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WriterException ex) {
            ex.printStackTrace();
        }
    }

    private static Map<EncodeHintType, Object> createQRCodeHints() {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        return hints;
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        return image;
    }

    public void remplirCombo() {
        List<Date> reservations = new ArrayList<>();

        try {
            String query = "SELECT date_debut FROM reservation";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Date dateDebut = resultSet.getDate("date_debut");
                reservations.add(dateDebut);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Date> observableList = FXCollections.observableArrayList(reservations);
        search_location.setItems(observableList);
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
            pst.setString(1, search_location.getValue().toString());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                terrain = rs.getString("nom");

                txt_terrain.setText(rs.getString("terrain.nom"));
                txt_locataire.setText(rs.getString("user.nom"));
                txt_adresse.setText(rs.getString("terrain.adresse"));
                txt_ville.setText(rs.getString("terrain.ville"));
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
                search_location.getSelectionModel().clearSelection();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
