/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Access
 */
public class MenuReclamationController implements Initializable {

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
    private TableView<?> reclam_table_view;
    @FXML
    private TableColumn<?, ?> rec_id;
    @FXML
    private TableColumn<?, ?> rec_date;
    @FXML
    private TableColumn<?, ?> rec_sujet;
    @FXML
    private TableColumn<?, ?> rec_notif;
    @FXML
    private TableColumn<?, ?> rec_mess;
    @FXML
    private TableColumn<?, ?> rec_type;
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
    private AnchorPane Homeanch;
    @FXML
    private Label unread_rec1;
    @FXML
    private Label nb_totale_rec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switch_form(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void ReclamationSelect(MouseEvent event) {
    }

    @FXML
    private void update_rec(ActionEvent event) {
    }

    @FXML
    private void delete_rec(ActionEvent event) {
    }

    @FXML
    private void clear_champ(ActionEvent event) {
    }
    
}
