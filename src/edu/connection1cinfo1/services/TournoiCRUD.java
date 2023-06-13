/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Tournoi;
import edu.connection1cinfo1.entities.User;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;
import java.sql.Date;
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
public class TournoiCRUD implements ICRUD<Tournoi> {

    @Override
    public void addEntity(Tournoi tournoi) {
        try {
            String query = "INSERT INTO Tournoi "
                    + "(nom, nbr_participant, date_debut, date_fin, lieu, owner_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setString(1, tournoi.getNom());
            pst.setInt(2, tournoi.getNbrParticipant());
            pst.setDate(3, java.sql.Date.valueOf(tournoi.getDateDebut()));
            pst.setDate(4, java.sql.Date.valueOf(tournoi.getDateFin()));
            pst.setString(5, tournoi.getLieu());
            pst.setInt(6, tournoi.getOwner().getId());

            pst.executeUpdate();

            System.out.println("Tournoi added successfully.");
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error adding tournoi: " + ex.getMessage());
        }
    }

    @Override
    public List<Tournoi> displayEntities() {
        List<Tournoi> tournoiList = new ArrayList<>();

        try {
            String query = "SELECT * FROM Tournoi";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);

            UserCRUD userCRUD = new UserCRUD();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                int nbrParticipant = rs.getInt("nbr_participant");
                LocalDate dateDebut = rs.getDate("date_debut").toLocalDate();
                LocalDate dateFin = rs.getDate("date_fin").toLocalDate();
                String lieu = rs.getString("lieu");
                int ownerId = rs.getInt("owner_id");

                User owner = userCRUD.getById(ownerId);

                Tournoi tournoi = new Tournoi(nom, nbrParticipant, dateDebut, dateFin, lieu, owner);
                tournoi.setId(id);

                tournoiList.add(tournoi);
            }

            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error displaying tournois: " + ex.getMessage());
        }

        return tournoiList;
    }

    @Override
    public Tournoi getById(int id) {
        Tournoi tournoi = null;

        try {
            String query = "SELECT * FROM Tournoi WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                int nbrParticipant = rs.getInt("nbr_participant");
                LocalDate dateDebut = rs.getDate("date_debut").toLocalDate();
                LocalDate dateFin = rs.getDate("date_fin").toLocalDate();
                String lieu = rs.getString("lieu");
                int ownerId = rs.getInt("owner_id");

                // Retrieve the User entity for the owner
                UserCRUD userCRUD = new UserCRUD();
                User owner = userCRUD.getById(ownerId);

                tournoi = new Tournoi(id, nom, nbrParticipant, dateDebut, dateFin, lieu, owner);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving tournoi: " + ex.getMessage());
        }

        return tournoi;
    }

    @Override
    public void update(Tournoi tournoi) {
        try {
            String query = "UPDATE Tournoi SET nom = ?, nbr_participant = ?, date_debut = ?, date_fin = ?, lieu = ?, owner_id = ? WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, tournoi.getNom());
            pst.setInt(2, tournoi.getNbrParticipant());
            pst.setDate(3, Date.valueOf(tournoi.getDateDebut()));
            pst.setDate(4, Date.valueOf(tournoi.getDateFin()));
            pst.setString(5, tournoi.getLieu());
            pst.setInt(6, tournoi.getOwner().getId());
            pst.setInt(7, tournoi.getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error updating tournoi with ID: " + tournoi.getId() + ex.getMessage());
        }
    }

    @Override
    public void delete(Tournoi tournoi) {
        try {
            String query = "DELETE FROM Tournoi WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, tournoi.getId());
            pst.executeUpdate();

            System.out.println("Tournoi deleted successfully.");
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error deleting tournoi: " + ex.getMessage());
        }
    }

}
