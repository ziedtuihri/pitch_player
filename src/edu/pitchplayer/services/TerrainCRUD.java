/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.interfaces.ICRUD;
import edu.pitchplayer.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class TerrainCRUD implements ICRUD<Terrain> {

    @Override
    public void addEntity(Terrain terrain) {
        try {
            String query = "INSERT INTO Terrain (nom, adresse, ville, longueur, largeur, owner_id, disponibilite) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setString(1, terrain.getNom());
            pst.setString(2, terrain.getAdresse());
            pst.setString(3, terrain.getVille());
            pst.setFloat(4, terrain.getLongueur());
            pst.setFloat(5, terrain.getLargeur());

            if (terrain.getOwner() != null) {
                pst.setInt(6, terrain.getOwner().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            pst.setBoolean(7, terrain.isDisponible());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error adding terrain: " + ex.getMessage());
        }
    }

    @Override
    public List<Terrain> displayEntities() {
        List<Terrain> terrains = new ArrayList<>();
        try {
            String query = "SELECT * FROM Terrain";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            UserCRUD userCRUD = new UserCRUD();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                String ville = rs.getString("ville");
                float longueur = rs.getFloat("longueur");
                float largeur = rs.getFloat("largeur");
                boolean disponibilite = rs.getBoolean("disponibilite");
                int ownerId = rs.getInt("owner_id");

                User owner = userCRUD.getById(ownerId);

                Terrain terrain = new Terrain(nom, adresse, ville, longueur, largeur, owner, disponibilite);

                terrains.add(terrain);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error displaying terrains: " + ex.getMessage());
        }
        return terrains;
    }

    @Override
    public Terrain getById(int id) {
        Terrain terrain = null;
        UserCRUD userCRUD = new UserCRUD();

        try {
            String query = "SELECT * FROM Terrain WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                String ville = rs.getString("ville");
                float longueur = rs.getFloat("longueur");
                float largeur = rs.getFloat("largeur");
                boolean disponibilite = rs.getBoolean("disponibilite");
                int ownerId = rs.getInt("owner_id");

                User owner = userCRUD.getById(ownerId);

                terrain = new Terrain(nom, adresse, ville, longueur, largeur, owner, disponibilite);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving terrain: " + ex.getMessage());
        }

        return terrain;
    }

    @Override
    public void update(Terrain terrain) {
        try {
            String query = "UPDATE Terrain SET nom = ?, adresse = ?, ville = ?, longueur = ?, largeur = ?, owner_id = ?, disponibilite = ? WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setString(1, terrain.getNom());
            pst.setString(2, terrain.getAdresse());
            pst.setString(3, terrain.getVille());
            pst.setFloat(4, terrain.getLongueur());
            pst.setFloat(5, terrain.getLargeur());

            if (terrain.getOwner() != null) {
                pst.setInt(6, terrain.getOwner().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            pst.setBoolean(7, terrain.isDisponible());
            pst.setInt(8, terrain.getId());

            int rows = pst.executeUpdate();

            if (rows == 1) {
                System.out.println("Terrain with ID: " + terrain.getId() + " updated successfully.");
            } else {
                System.out.println("Failed to update Terrain with ID: " + terrain.getId() + " !");
            }

            pst.close();
        } catch (SQLException ex) {
            System.err.println("Failed to update Terrain with ID: " + terrain.getId() + " !");
            ex.printStackTrace();
            // Throw the exception to the calling method or handle it accordingly//
        }

    }

    @Override
    public void delete(Terrain terrain) {
        try {
            String query = "DELETE FROM Terrain WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, terrain.getId());
            int rows = pst.executeUpdate();

            if (rows == 1) {
                System.out.println("Terrain with ID: " + terrain.getId() + " deleted successfully.");
            } else {
                System.out.println("Failed to delete Terrain with ID: " + terrain.getId() + " !");
            }

            pst.close();
        } catch (SQLException ex) {
            System.err.println("Error deleting terrain: " + ex.getMessage());
        }

    }

}
