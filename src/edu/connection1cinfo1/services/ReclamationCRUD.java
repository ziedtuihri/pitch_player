/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.Match;
import edu.connection1cinfo1.entities.Reclamation;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZIED
 */
public class ReclamationCRUD implements ICRUD<Reclamation>{

    public void addEntity(Reclamation t) {
        try{
            String request = "INSERT INTO `reclamation` (date_reclamation, objet_reclamation, contenu_reclamation," +
                    " statut_reclamation," +
                    " joueur_id, pt_id ) VALUES"+"(?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(request);

            pst.setDate(1, t.getDate());
            pst.setString(2, t.getObjet());
            pst.setString(3, t.getContenu());
            pst.setString(4, t.getStatut());
            pst.setInt(5, t.getJoueurID());
            pst.setInt(6, t.getProprietaireTerrainID());

            pst.executeUpdate();
            System.out.println("reclamation ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Reclamation> displayEntities() {
        List<Reclamation> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM `reclamation`";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                Reclamation p = new Reclamation();
                p.setId(rs.getInt(1));
                p.setDate(rs.getDate("date_reclamation"));
                p.setObjet(rs.getString("objet_reclamation"));
                p.setContenu(rs.getString("contenu_reclamation"));
                p.setStatut(rs.getString("statut_reclamation"));
                p.setJoueurID(rs.getInt("joueur_id"));
                p.setProprietaireTerrainID(rs.getInt("pt_id"));


                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }

    @Override
    public Reclamation getById(int id) {
        return null;
    }

    public void update(Reclamation entity) {
        String requet = "UPDATE `reclamation` SET contenu_reclamation = ?, date_reclamation = ? WHERE  id_reclamation = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getContenu());
            pst.setDate(2, entity.getDate());
            pst.setInt(3, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("reclamation modifié");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void delete(Reclamation entity) {
        String requet = "DELETE FROM `reclamation` WHERE id_reclamation = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("reclamation supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }


}
