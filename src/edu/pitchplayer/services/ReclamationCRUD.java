/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Reclamation;
import javafx.collections.ObservableList;
import edu.pitchplayer.entities.Reservation;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.interfaces.ICRUD;
import edu.pitchplayer.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WIJDEN
 */
public class ReclamationCRUD implements ICRUD<Reclamation> {

    @Override
    public void addEntity(Reclamation t) {
        try {
            String query = "INSERT INTO Reclamation (date, sujet, objet, statut, message_traitement, user_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()));
            pst.setString(2, t.getSujet());
            pst.setString(3, t.getObjet());
            pst.setString(4, t.getStatut());
            pst.setString(5, t.getMessageTraitement());

            if (t.getUser() != null) {
                pst.setInt(6, t.getUser().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            pst.executeUpdate();
            System.out.println("reclamation ajoutée");
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> displayEntities() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM Reclamation";
            Statement stmt = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date");
                String sujet = rs.getString("sujet");
                String objet = rs.getString("objet");
                String statut = rs.getString("statut");
                String messageTraitement = rs.getString("message_traitement");
                int userId = rs.getInt("user_id");

                User user = null;

                Reclamation reclamation = new Reclamation(id, date, sujet, objet, statut, user, messageTraitement);
                reclamations.add(reclamation);
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error displaying reclamations: " + ex.getMessage());
        }

        return reclamations;
    }

    @Override
    public Reclamation getById(int id) {
        
        Reclamation reclamation = null;
        //UserCRUD usercr = new UserCRUD();

        try {
            String query = "SELECT * FROM reclamation WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String date = rs.getString("date");
                String sujet = rs.getString("sujet");
                String objet = rs.getString("objet");
                String statut = rs.getString("statut");
                String messagetraitement = rs.getString("message_traitement");
                
                int userId = rs.getInt("user_id");

                /*User user = UserCRUD.getById(user);*/

                reclamation = new Reclamation();
                reclamation.setId(id);
                reclamation.setDate(date);
                reclamation.setSujet(sujet);
                reclamation.setObjet(objet);
                reclamation.setStatut(statut);
                /*reclamation.setUser(user);*/
                reclamation.setMessageTraitement(messagetraitement);
                
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reclamation;
    }
    


    @Override
    public void update(Reclamation r) {
        try {
            String query = "UPDATE Reclamation SET date = ?, sujet = ?, objet = ?, statut = ?, message_traitement = ?, user_id = ? WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, r.getDate());
            pst.setString(2, r.getSujet());
            pst.setString(3, r.getObjet());
            pst.setString(4, r.getStatut());
            pst.setString(5, r.getMessageTraitement());

            if (r.getUser() != null) {
                pst.setInt(6, r.getUser().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            pst.setInt(7, r.getId());

            int rows = pst.executeUpdate();

            if (rows == 1) {
                System.out.println("Reclamation with ID: " + r.getId() + " updated successfully.");
            } else {
                System.out.println("Failed to update Reclamation with ID: " + r.getId() + " !");
            }

            pst.close();
        } catch (SQLException ex) {
            System.err.println("Failed to update Reclamation with ID: " + r.getId() + " !");
            ex.printStackTrace();
            // Throw the exception to the calling method or handle it accordingly
        }
    }

    @Override
    public void delete(int id) {
        Reclamation r = new Reclamation();
        try {
            String query = "DELETE FROM Reclamation WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reclamation with ID: " + id + " deleted successfully.");
            } else {
                System.out.println("No reclamation found with ID: " + id);
            }

            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error deleting Reclamation: " + ex.getMessage());
        }
    }
}
