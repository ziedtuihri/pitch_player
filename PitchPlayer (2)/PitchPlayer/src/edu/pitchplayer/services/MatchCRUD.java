/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Match;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.Tournoi;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.interfaces.ICRUD;
import edu.pitchplayer.utils.MyConnection;
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
public class MatchCRUD implements ICRUD<Match> {

    @Override
    public void addEntity(Match match) {
        try {
            String query = "INSERT INTO `Match` (nom, date, statut, match_type, commentaire_match, tournoi_id, joueur_id, terrain_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, match.getNom());
            pst.setDate(2, java.sql.Date.valueOf(match.getDate()));
            pst.setString(3, match.getStatut());
            pst.setString(4, match.getMatchType());
            pst.setString(5, match.getCommentaireMatch());

            if (match.getTournoi() != null) {
                pst.setInt(6, match.getTournoi().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            if (match.getJoueur() != null) {
                pst.setInt(7, match.getJoueur().getId());
            } else {
                pst.setNull(7, java.sql.Types.INTEGER);
            }

            if (match.getTerrain() != null) {
                pst.setInt(8, match.getTerrain().getId());
            } else {
                pst.setNull(8, java.sql.Types.INTEGER);
            }

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    match.setId(generatedId);
                    System.out.println("Match added successfully. Generated ID: " + generatedId);
                }
            }

            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error adding match: " + ex.getMessage());
        }
    }

    @Override
    public List<Match> displayEntities() {
        List<Match> matchList = new ArrayList<>();

        try {
            String query = "SELECT m.id, m.nom, m.date, m.statut, m.match_type, m.commentaire_match, "
                    + "t.id AS terrain_id, t.nom AS terrain_nom, "
                    + "tu.id AS tournoi_id, tu.nom AS tournoi_nom, "
                    + "u.id AS joueur_id, u.username AS joueur_username, u.nom AS joueur_nom "
                    + "FROM `Match` m "
                    + "JOIN Terrain t ON m.terrain_id = t.id "
                    + "LEFT JOIN Tournoi tu ON m.tournoi_id = tu.id "
                    + "JOIN User u ON m.joueur_id = u.id";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                LocalDate date = rs.getDate("date").toLocalDate();
                String statut = rs.getString("statut");
                String matchType = rs.getString("match_type"); 
                String commentaireMatch = rs.getString("commentaire_match");

                // Terrain details
                int terrainId = rs.getInt("terrain_id");
                String terrainNom = rs.getString("terrain_nom");
                Terrain terrain = new Terrain();
                terrain.setId(terrainId);
                terrain.setNom(terrainNom);

                // Tournoi details
                int tournoiId = rs.getInt("tournoi_id");
                String tournoiNom = rs.getString("tournoi_nom");
                Tournoi tournoi = null;
                if (tournoiId != 0) {
                    tournoi = new Tournoi();
                    tournoi.setId(tournoiId);
                    tournoi.setNom(tournoiNom);
                }

                // Joueur details
                int joueurId = rs.getInt("joueur_id");
                String joueurUsername = rs.getString("joueur_username");
                String joueurNom = rs.getString("joueur_nom");
                User joueur = new User();
                joueur.setId(joueurId);
                joueur.setUsername(joueurUsername);
                joueur.setNom(joueurNom);

                // Create the Match object with related entities
                Match match = new Match();
                match.setId(id);
                match.setNom(nom);
                match.setDate(date);
                match.setStatut(statut);
                match.setMatchType(matchType);
                match.setCommentaireMatch(commentaireMatch);
                match.setTerrain(terrain);
                match.setTournoi(tournoi);
                match.setJoueur(joueur);

                matchList.add(match);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving matches: " + ex.getMessage());
        }

        return matchList;
    }

    @Override
    public Match getById(int id) {
        Match match = null;

        try {
            String query = "SELECT * FROM `Match` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                LocalDate date = rs.getDate("date").toLocalDate();
                String statut = rs.getString("statut");
                String matchType = rs.getString("match_type"); // 
                String commentaireMatch = rs.getString("commentaire_match");

                // Retrieve tournoi, joueur, and terrain IDs
                int tournoiId = rs.getInt("tournoi_id");
                int joueurId = rs.getInt("joueur_id");
                int terrainId = rs.getInt("terrain_id");

                // Retrieve the corresponding tournoi, joueur, and terrain entities
                TournoiCRUD tournoiCRUD = new TournoiCRUD();
                Tournoi tournoi = tournoiCRUD.getById(tournoiId);

                UserCRUD userCRUD = new UserCRUD();
                User joueur = userCRUD.getById(joueurId);

                TerrainCRUD terrainCRUD = new TerrainCRUD();
                Terrain terrain = terrainCRUD.getById(terrainId);

                // Create the Match object
                match = new Match(nom, date, statut, matchType, commentaireMatch, tournoi, joueur, terrain);
                match.setId(id);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving match: " + ex.getMessage());
        }

        return match;
    }

    @Override
    public void update(Match match) {
        try {
            String query = "UPDATE `Match` SET nom = ?, date = ?, statut = ?, match_type = ?, commentaire_match = ?, "
                    + "tournoi_id = ?, joueur_id = ?, terrain_id = ? WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setString(1, match.getNom());
            pst.setDate(2, java.sql.Date.valueOf(match.getDate()));
            pst.setString(3, match.getStatut());
            pst.setString(4, match.getMatchType().toString());
            pst.setString(5, match.getCommentaireMatch());

            if (match.getTournoi() != null) {
                pst.setInt(6, match.getTournoi().getId());
            } else {
                pst.setNull(6, java.sql.Types.INTEGER);
            }

            if (match.getJoueur() != null) {
                pst.setInt(7, match.getJoueur().getId());
            } else {
                pst.setNull(7, java.sql.Types.INTEGER);
            }

            if (match.getTerrain() != null) {
                pst.setInt(8, match.getTerrain().getId());
            } else {
                pst.setNull(8, java.sql.Types.INTEGER);
            }

            pst.setInt(9, match.getId());

            pst.executeUpdate();
            pst.close();

            System.out.println("Match updated successfully.");
        } catch (SQLException ex) {
            System.out.println("Error updating match: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Match match) {
        try {
            String query = "DELETE FROM `Match` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, match.getId());

            // Delete associated records in the Tournoi table
            if (match.getTournoi() != null) {
                String deleteTournoiQuery = "DELETE FROM Tournoi WHERE id = ?";
                PreparedStatement deleteTournoiPst = MyConnection.getInstance().getCnx().prepareStatement(deleteTournoiQuery);
                deleteTournoiPst.setInt(1, match.getTournoi().getId());
                deleteTournoiPst.executeUpdate();
                deleteTournoiPst.close();
            }

            // Delete associated records in the Terrain table
            if (match.getTerrain() != null) {
                String deleteTerrainQuery = "DELETE FROM Terrain WHERE id = ?";
                PreparedStatement deleteTerrainPst = MyConnection.getInstance().getCnx().prepareStatement(deleteTerrainQuery);
                deleteTerrainPst.setInt(1, match.getTerrain().getId());
                deleteTerrainPst.executeUpdate();
                deleteTerrainPst.close();
            }

            pst.executeUpdate();

            System.out.println("Match with ID: " + match.getId() + " deleted successfully.");
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error deleting Match: " + ex.getMessage());
        }
    }

}
