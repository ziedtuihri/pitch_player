package edu.pitchplayer.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.services.TerrainService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class RechercheFXMLController implements Initializable {

    @FXML
    private ListView<Terrain> fxView;
    @FXML
    private TextField fxRecherche;
    @FXML
    private VBox fxVbox;
    @FXML
    private RadioButton fxNom;
    @FXML
    private RadioButton fxAdresse;

    private TerrainService terrainService = new TerrainService();
    private ObservableList<Terrain> terrainList = FXCollections.observableArrayList();

    private ToggleGroup searchToggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configure ListView
        fxView.setItems(terrainList);
        fxView.setCellFactory(param -> new TerrainListCell());

        // Configure TextField
        fxRecherche.setPromptText("Saisissez une lettre");
        fxRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            String selectedToggleId = ((RadioButton) searchToggleGroup.getSelectedToggle()).getId();
            if (!newValue.isEmpty()) {
                try {
                    List<Terrain> terrains;
                    if (selectedToggleId.equals("fxNom")) {
                        terrains = terrainService.getTerrainsByNom(newValue);
                    } else {
                        terrains = terrainService.getTerrainsByAdresse(newValue);
                    }
                    terrainList.setAll(terrains);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                terrainList.clear();
            }
        });

        // Configure Radio Buttons
        fxNom.setToggleGroup(searchToggleGroup);
        fxAdresse.setToggleGroup(searchToggleGroup);
        fxNom.setSelected(true);
    }

    @FXML
    private void EffacerAction(ActionEvent event) {
        fxRecherche.setText("");
    }

    @FXML
    private void RetourAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AccueilTerrainFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void DetailAction(ActionEvent event) {
        Terrain terrainSelectionne = fxView.getSelectionModel().getSelectedItem();
        if (terrainSelectionne != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/DetailTerrainFXML.fxml"));
                Parent root = loader.load();
                DetailTerrainFXMLController detailController = loader.getController();
                detailController.setTerrain(terrainSelectionne);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void QRCodeAction(ActionEvent event) {
        Terrain terrainSelectionne = fxView.getSelectionModel().getSelectedItem();
        if (terrainSelectionne != null) {
            try {
                int qrWidth = 300;
                int qrHeight = 300;
                BitMatrix bitMatrix = generateQRCode(terrainSelectionne, qrWidth, qrHeight);

                // Créer une nouvelle fenêtre pour afficher le code QR
                Stage qrCodeStage = new Stage();
                qrCodeStage.setTitle("QR Code");

                // Charger le fichier FXML de la vue QRCodeFXML.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/QRCodeFXML.fxml"));
                Parent root = loader.load();

                // Obtenir le contrôleur QRCodeFXMLController
                QRCodeFXMLController qrCodeController = loader.getController();

                // Appeler la méthode setQRCodeImage pour définir l'image du code QR dans le contrôleur
                qrCodeController.setQRCodeImage(matrixToImage(bitMatrix));

                // Créer une scène et l'associer à la fenêtre
                Scene scene = new Scene(root);
                qrCodeStage.setScene(scene);

                // Afficher la fenêtre
                qrCodeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }

    private class TerrainListCell extends javafx.scene.control.ListCell<Terrain> {
        @Override
        protected void updateItem(Terrain terrain, boolean empty) {
            super.updateItem(terrain, empty);
            if (empty || terrain == null) {
                setText(null);
            } else {
                setText(terrain.getNom() + " - " + terrain.getAdresse() + " - " + terrain.getVille());
            }
        }
    }

    private BitMatrix generateQRCode(Terrain terrain, int width, int height) throws WriterException {
        String qrText = terrain.getNom() + " " + terrain.getAdresse() + " " + terrain.getVille() + " " + terrain.getLongueur() + " " + terrain.getLargeur();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        return qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, width, height);
    }

    private Image matrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = matrix.get(x, y) ? 0x000000 : 0xFFFFFF;
                bufferedImage.setRGB(x, y, color);
            }
        }

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
}
