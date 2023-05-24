/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.Reclamation;
import edu.connection1cinfo1.entities.Reservation;
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
public class ReservationCRUD implements ICRUD<Reservation>{

    public void addEntity(Reservation t) {
        try{
            String request = "INSERT INTO `reservation` (date_reservation, statut_reservation, montant_reservation," +
                    " joueur_id," +
                    " terrain_id) VALUES"+"(?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(request);

            pst.setDate(1, t.getDate());
            pst.setString(2, t.getStatut());
            pst.setFloat(3, t.getMontant());
            pst.setInt(4, t.getJoueurID());
            pst.setInt(5, t.getTerrainID());

            pst.executeUpdate();
            System.out.println("Reservation ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Reservation> displayEntities() {
        List<Reservation> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM `reservation`";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                Reservation p = new Reservation();
                p.setId(rs.getInt(1));
                p.setDate(rs.getDate("date_reservation"));
                p.setStatut(rs.getString("statut_reservation"));
                p.setMontant(rs.getFloat("montant_reservation"));
                p.setJoueurID(rs.getInt("joueur_id"));
                p.setTerrainID(rs.getInt("terrain_id"));

                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }

    @Override
    public Reservation getById(int id) {
        return null;
    }

    public void update(Reservation entity) {
        String requet = "UPDATE `reservation` SET statut_reservation = ?, montant_reservation = ? WHERE  id_reservation = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getStatut());
            pst.setFloat(2, entity.getMontant());
            pst.setInt(3, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Reservation modifié");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Reservation entity) {
        String requet = "DELETE FROM `reservation` WHERE id_reservation = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Reservation supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
