/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.Terrain;
import edu.connection1cinfo1.entities.Tournoi;
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
public class TournoiCRUD implements ICRUD<Tournoi>{

    public void addEntity(Tournoi t) {
        try{
            String request = "INSERT INTO `tournoi` (nom_tournoi, nbr_participant, date_debut," +
                    " date_fin," +
                    " lieu) VALUES"+"(?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(request);

            pst.setString(1, t.getNom());
            pst.setInt(2, t.getNbrParticipant());
            pst.setDate(3, t.getDateDebut());
            pst.setDate(4, t.getDateFin());
            pst.setString(5, t.getLieu());

            pst.executeUpdate();
            System.out.println("Tournoi ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Tournoi> displayEntities() {
        List<Tournoi> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM `tournoi`";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                Tournoi p = new Tournoi();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom_tournoi"));
                p.setNbrParticipant(rs.getInt("nbr_participant"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setLieu(rs.getString("lieu"));

                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }

    @Override
    public Tournoi getById(int id) {
        return null;
    }

    public void update(Tournoi entity) {
        String requet = "UPDATE `tournoi` SET nom_tournoi = ?, nbr_participant = ?, date_debut = ? WHERE  id_tournoi = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getNom());
            pst.setInt(2, entity.getNbrParticipant());
            pst.setDate(3, entity.getDateDebut());
            pst.setInt(4, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Terrain modifié");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Tournoi entity) {
        String requet = "DELETE FROM `tournoi` WHERE id_tournoi = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Tournoi supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
