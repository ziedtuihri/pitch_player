/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Reservation;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.interfaces.ICRUD;
import edu.pitchplayer.utils.MyConnection;
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
public class ReservationCRUD implements ICRUD<Reservation> {

    @Override
    public void addEntity(Reservation reservation) {
        try {
            String query = "INSERT INTO Reservation"
                    + "(date, statut, message, owner_confirmation_status, player_notification_status, user_id, terrain_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setDate(1, java.sql.Date.valueOf(reservation.getDate()));
            pst.setBoolean(2, reservation.isStatut());
            pst.setString(3, reservation.getMessage()); // Set the message field

            pst.setString(4, reservation.getOwnerConfirmationStatus());
            pst.setString(5, reservation.getPlayerNotificationStatus());

            // Set user_id
            if (reservation.getUser() != null) {
                pst.setInt(6, reservation.getUser().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            // Set terrain_id
            if (reservation.getTerrain() != null) {
                pst.setInt(7, reservation.getTerrain().getId());
            } else {
                pst.setNull(7, java.sql.Types.INTEGER);
            }

            pst.executeUpdate();

            System.out.println("Reservation added successfully.");

            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error adding Reservation: " + ex.getMessage());
        }
    }

    @Override
    public List<Reservation> displayEntities() {
        List<Reservation> reservations = new ArrayList<>();

        try {
            String query = "SELECT * FROM Reservation";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);

            UserCRUD userCRUD = new UserCRUD();
            TerrainCRUD terrainCRUD = new TerrainCRUD();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                boolean statut = rs.getBoolean("statut");
                String message = rs.getString("message");
                String ownerConfirmationStatus = rs.getString("owner_confirmation_status");
                String playerNotificationStatus = rs.getString("player_notification_status");
                int userId = rs.getInt("user_id");
                int terrainId = rs.getInt("terrain_id");

                User user = userCRUD.getById(userId);
                Terrain terrain = terrainCRUD.getById(terrainId);

                Reservation reservation = new Reservation(date, statut, message, ownerConfirmationStatus, playerNotificationStatus, user, terrain);
                reservation.setId(id);

                reservations.add(reservation);
            }

            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error displaying reservations: " + ex.getMessage());
        }

        return reservations;
    }

    @Override
    public Reservation getById(int id) {
        Reservation reservation = null;
        try {
            String query = "SELECT * FROM Reservation WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            UserCRUD userCRUD = new UserCRUD();
            TerrainCRUD terrainCRUD = new TerrainCRUD();

            if (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                boolean statut = rs.getBoolean("statut");
                String message = rs.getString("message");
                String ownerConfirmationStatus = rs.getString("owner_confirmation_status");
                String playerNotificationStatus = rs.getString("player_notification_status");
                int userId = rs.getInt("user_id");
                int terrainId = rs.getInt("terrain_id");

                User user = userCRUD.getById(userId);
                Terrain terrain = terrainCRUD.getById(terrainId);

                reservation = new Reservation(date, statut, message, ownerConfirmationStatus, playerNotificationStatus, user, terrain);
                reservation.setId(id);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving reservation: " + ex.getMessage());
        }

        return reservation;
    }

    @Override
    public void update(Reservation reservation) {
        try {
            String query = "UPDATE Reservation SET date = ?, statut = ?, message = ?, owner_confirmation_status = ?, "
                    + "player_notification_status = ?, user_id = ?, terrain_id = ? WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setDate(1, Date.valueOf(reservation.getDate()));
            pst.setBoolean(2, reservation.isStatut());
            pst.setString(3, reservation.getMessage());

            pst.setString(4, reservation.getOwnerConfirmationStatus());
            pst.setString(5, reservation.getPlayerNotificationStatus());
            pst.setInt(6, reservation.getUser().getId());
            pst.setInt(7, reservation.getTerrain().getId());
            pst.setInt(8, reservation.getId());

            int rows = pst.executeUpdate();

            if (rows == 1) {
                System.out.println("Reservation with ID: " + reservation.getId() + " updated successfully.");
            } else {
                System.out.println("Failed to update Reservation with ID: " + reservation.getId() + " !");
            }

            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error updating reservation: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Reservation reservation) {
        try {
            String query = "DELETE FROM Reservation WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, reservation.getId());
            int rows = pst.executeUpdate();

            if (rows == 1) {
                System.out.println("Reservation with ID: " + reservation.getId() + " deleted successfully.");
            } else {
                System.out.println("Failed to delete Reservation with ID: " + reservation.getId() + " !");
            }

            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error deleting reservation: " + ex.getMessage());
        }
    }

}
