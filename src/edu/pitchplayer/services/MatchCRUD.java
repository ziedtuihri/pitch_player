/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Match;
import edu.pitchplayer.interfaces.ICRUD;
import edu.pitchplayer.utils.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class MatchCRUD implements ICRUD<Match> {

    @Override
    public void addEntity(Match match) {

        /*tournoi_id, "
                    + "equipe1_id, equipe2_id, joueur_id, terrain_id, social_media_share_status, "
                    + "social_media_share_link
         */
        try {
            String query = "INSERT INTO `Match`"
                    + "(nom, date, statut, score, type, commentaire_match) VALUES "
                    + "(?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(query);

            pst.setString(1, match.getNom());
            pst.setDate(2, Date.valueOf(match.getDate()));
            pst.setString(3, match.getStatut());
            pst.setString(4, match.getScore());
            pst.setString(5, match.getMatchType().toString());
            pst.setString(6, match.getCommentaireMatch());
            /* pst.setInt(7, match.getTournoi().getId());
            pst.setInt(8, match.getEquipe1Id());
            pst.setInt(9, match.getEquipe2Id());
            pst.setInt(10, match.getJoueur().getId());
            pst.setInt(11, match.getTerrain().getId());
            pst.setString(12, match.getSocialMediaShareStatus().toString());
            pst.setString(13, match.getSocialMediaShareLink());
             */
            pst.executeUpdate();
            System.out.println("Match ajoute");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Match> displayEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Match getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Match entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Match entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
