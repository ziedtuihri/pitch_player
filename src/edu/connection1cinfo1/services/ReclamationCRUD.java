/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Reclamation;
import edu.connection1cinfo1.entities.User;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class ReclamationCRUD implements ICRUD<Reclamation> {

    @Override
    public void addEntity(Reclamation reclamation) {
        try {
            String query = "INSERT INTO Reclamation (date, sujet, objet, statut, message_traitement, user_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setDate(1, java.sql.Date.valueOf(reclamation.getDate()));
            pst.setString(2, reclamation.getSujet());
            pst.setString(3, reclamation.getObjet());
            pst.setString(4, reclamation.getStatut());
            pst.setString(5, reclamation.getMessageTraitement());

            if (reclamation.getUser() != null) {
                pst.setInt(6, reclamation.getUser().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> displayEntities() {
        List<Reclamation> reclamations = new ArrayList<>();

        try {
            String query = "SELECT * FROM Reclamation";
            Statement stmt = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            UserCRUD userCRUD = new UserCRUD();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                String sujet = rs.getString("sujet");
                String objet = rs.getString("objet");
                String statut = rs.getString("statut");
                String messageTraitement = rs.getString("message_traitement");
                int userId = rs.getInt("user_id");

                User user = null;

                if (!rs.wasNull()) {
                    user = userCRUD.getById(userId);
                }

                Reclamation reclamation = new Reclamation(date, sujet, objet, statut, user, messageTraitement);
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
        UserCRUD userCRUD = new UserCRUD();

        try {
            String query = "SELECT * FROM Reclamation WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                String sujet = rs.getString("sujet");
                String objet = rs.getString("objet");
                String statut = rs.getString("statut");
                String messageTraitement = rs.getString("message_traitement");
                int userId = rs.getInt("user_id");

                User user = null;

                if (!rs.wasNull()) {
                    user = userCRUD.getById(userId);
                }

                reclamation = new Reclamation(date, sujet, objet, statut, user, messageTraitement);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving reclamation: " + ex.getMessage());
        }

        return reclamation;
    }

    @Override
    public void update(Reclamation reclamation) {
        try {
            String query = "UPDATE Reclamation SET date = ?, sujet = ?, objet = ?, statut = ?, message_traitement = ?, user_id = ? WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setDate(1, java.sql.Date.valueOf(reclamation.getDate()));
            pst.setString(2, reclamation.getSujet());
            pst.setString(3, reclamation.getObjet());
            pst.setString(4, reclamation.getStatut());
            pst.setString(5, reclamation.getMessageTraitement());

            if (reclamation.getUser() != null) {
                pst.setInt(6, reclamation.getUser().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            pst.setInt(7, reclamation.getId());

            int rows = pst.executeUpdate();

            if (rows == 1) {
                System.out.println("Reclamation with ID: " + reclamation.getId() + " updated successfully.");
            } else {
                System.out.println("Failed to update Reclamation with ID: " + reclamation.getId() + " !");
            }

            pst.close();
        } catch (SQLException ex) {
            System.err.println("Failed to update Reclamation with ID: " + reclamation.getId() + " !");
            ex.printStackTrace();
            // Throw the exception to the calling method or handle it accordingly
        }
    }

    @Override
    public void delete(Reclamation reclamation) {
        try {
            String query = "DELETE FROM Reclamation WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, reclamation.getId());
            pst.executeUpdate();

            System.out.println("Reclamation with ID: " + reclamation.getId() + " deleted successfully.");

            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error deleting Reclamation: " + ex.getMessage());
        }
    }

}
