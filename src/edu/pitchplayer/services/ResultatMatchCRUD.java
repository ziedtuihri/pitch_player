/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Equipe;
import edu.pitchplayer.entities.Match;
import edu.pitchplayer.entities.ResultatMatch;
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
public class ResultatMatchCRUD implements ICRUD<ResultatMatch> {

    @Override
    public void addEntity(ResultatMatch resultatMatch) {
        try {
            String query = "INSERT INTO ResultatMatch (match_id, equipe1_id, equipe2_id, equipe1_score, equipe2_score) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, resultatMatch.getMatch().getId());
            pst.setInt(2, resultatMatch.getEquipe1().getId());
            pst.setInt(3, resultatMatch.getEquipe2().getId());
            pst.setInt(4, resultatMatch.getEquipe1Score());
            pst.setInt(5, resultatMatch.getEquipe2Score());
            pst.executeUpdate();
            pst.close();

            System.out.println("ResultatMatch added successfully.");
        } catch (SQLException ex) {
            System.out.println("Error adding ResultatMatch: " + ex.getMessage());
        }
    }

    @Override
    public List<ResultatMatch> displayEntities() {
        List<ResultatMatch> resultatMatchList = new ArrayList<>();

        try {
            String query = "SELECT * FROM ResultatMatch";
            Statement stmt = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                int matchId = rs.getInt("match_id");
                int equipe1Id = rs.getInt("equipe1_id");
                int equipe2Id = rs.getInt("equipe2_id");
                int equipe1Score = rs.getInt("equipe1_score");
                int equipe2Score = rs.getInt("equipe2_score");

                // Retrieve the corresponding Match object
                MatchCRUD matchCRUD = new MatchCRUD();
                Match match = matchCRUD.getById(matchId);

                // Retrieve the corresponding Equipe objects
                EquipeCRUD equipeCRUD = new EquipeCRUD();
                Equipe equipe1 = equipeCRUD.getById(equipe1Id);
                Equipe equipe2 = equipeCRUD.getById(equipe2Id);

                // Create the ResultatMatch object
                ResultatMatch resultatMatch = new ResultatMatch();
                resultatMatch.setId(id);
                resultatMatch.setMatch(match);
                resultatMatch.setEquipe1(equipe1);
                resultatMatch.setEquipe2(equipe2);
                resultatMatch.setEquipe1Score(equipe1Score);
                resultatMatch.setEquipe2Score(equipe2Score);

                resultatMatchList.add(resultatMatch);
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving ResultatMatch entities: " + ex.getMessage());
        }

        return resultatMatchList;
    }

    @Override
    public ResultatMatch getById(int id) {
        ResultatMatch resultatMatch = null;

        try {
            String query = "SELECT * FROM ResultatMatch WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int matchId = rs.getInt("match_id");
                int equipe1Id = rs.getInt("equipe1_id");
                int equipe2Id = rs.getInt("equipe2_id");
                int equipe1Score = rs.getInt("equipe1_score");
                int equipe2Score = rs.getInt("equipe2_score");

                // Retrieve the corresponding Match object
                MatchCRUD matchCRUD = new MatchCRUD();
                Match match = matchCRUD.getById(matchId);

                // Retrieve the corresponding Equipe objects
                EquipeCRUD equipeCRUD = new EquipeCRUD();
                Equipe equipe1 = equipeCRUD.getById(equipe1Id);
                Equipe equipe2 = equipeCRUD.getById(equipe2Id);

                // Create the ResultatMatch object
                resultatMatch = new ResultatMatch();
                resultatMatch.setId(id);
                resultatMatch.setMatch(match);
                resultatMatch.setEquipe1(equipe1);
                resultatMatch.setEquipe2(equipe2);
                resultatMatch.setEquipe1Score(equipe1Score);
                resultatMatch.setEquipe2Score(equipe2Score);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving ResultatMatch: " + ex.getMessage());
        }

        return resultatMatch;
    }

    @Override
    public void update(ResultatMatch resultatMatch) {
        try {
            String query = "UPDATE ResultatMatch SET match_id = ?, equipe1_id = ?, equipe2_id = ?, equipe1_score = ?, equipe2_score = ? WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setInt(1, resultatMatch.getMatch().getId());
            pst.setInt(2, resultatMatch.getEquipe1().getId());
            pst.setInt(3, resultatMatch.getEquipe2().getId());
            pst.setInt(4, resultatMatch.getEquipe1Score());
            pst.setInt(5, resultatMatch.getEquipe2Score());
            pst.setInt(6, resultatMatch.getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error updating ResultatMatch: " + ex.getMessage());
        }
    }

    @Override
    public void delete(ResultatMatch resultatMatch) {
        try {
            String query = "DELETE FROM ResultatMatch WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, resultatMatch.getId());
            pst.executeUpdate();

            System.out.println("ResultatMatch with ID: " + resultatMatch.getId() + " deleted successfully.");
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error deleting ResultatMatch: " + ex.getMessage());
        }
    }

}
