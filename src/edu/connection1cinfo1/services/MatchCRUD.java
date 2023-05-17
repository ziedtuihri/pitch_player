/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Match;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;


/**
 *
 * @author Zied
 */
public class MatchCRUD implements ICRUD<Match>{
    public void addEntity(Match t) {
        try{
            String request = "INSERT INTO match (nom_match, date_match, statut_match, score_match," +
                    " type_match, commentaire_match, tournoi_id,equipe_id, joueur_id ) VALUES"+"(?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(request);

            pst.setString(1, t.getNom());
            pst.setDate(2, t.getDate());
            pst.setString(3, t.getStatut());
            pst.setString(4, t.getScore());
            pst.setString(5, t.getType());
            pst.setString(6, t.getCommentaire());
            pst.setInt(7, t.getTournoiID());
            pst.setInt(8, t.getEquipeID());
            pst.setInt(8, t.getJoueurID());
            pst.executeUpdate();
            System.out.println("Match ajoute");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Match> displayEntities() {
        List<Match> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM match";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                Match p = new Match();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom_match"));
                p.setDate(rs.getDate("date_match"));
                p.setStatut(rs.getString("statut_match"));
                p.setScore(rs.getString("score_match"));
                p.setType(rs.getString("type_match"));
                p.setCommentaire(rs.getString("commentaire_match"));
                p.setTournoiID(rs.getInt("tournoi_id"));
                p.setEquipeID(rs.getInt("equipe_id"));
                p.setJoueurID(rs.getInt("joueur_id"));

                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }

    @Override
    public Match getById(int id) {
        return null;
    }

    public void update(Match entity) {
        String requet = "UPDATE match SET nom_match = ?, date_match = ? WHERE  id_match = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getNom());
            pst.setDate(2, entity.getDate());
            pst.setInt(3, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Match modifié");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void delete(Match entity) {
        String requet = "DELETE FROM match WHERE id_equipe = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Match supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
