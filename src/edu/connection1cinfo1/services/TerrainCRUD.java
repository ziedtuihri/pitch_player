/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.Reservation;
import edu.connection1cinfo1.entities.Terrain;
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
public class TerrainCRUD implements ICRUD<Terrain>{

    public void addEntity(Terrain t) {
        try{
            String request = "INSERT INTO `terrain` (nom_terrain, adresse_terrain, ville_terrain," +
                    " dimensions_terrain," +
                    " equipements_terrain, disponibilite_terrain) VALUES"+"(?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(request);

            pst.setString(1, t.getNom());
            pst.setString(2, t.getAdresse());
            pst.setString(3, t.getVille());
            pst.setString(4, t.getDimensions());
            pst.setString(5, t.getEquipements());
            pst.setString(6, t.getDisponibilite());

            pst.executeUpdate();
            System.out.println("Terrain ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Terrain> displayEntities() {
        List<Terrain> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM `terrain`";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                Terrain p = new Terrain();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom_terrain"));
                p.setAdresse(rs.getString("adresse_terrain"));
                p.setVille(rs.getString("ville_terrain"));
                p.setDimensions(rs.getString("dimensions_terrain"));
                p.setEquipements(rs.getString("equipements_terrain"));
                p.setDisponibilite(rs.getString("disponibilite_terrain"));


                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }

    @Override
    public Terrain getById(int id) {
        return null;
    }

    public void update(Terrain entity) {
        String requet = "UPDATE `terrain` SET nom_terrain = ?, adresse_terrain = ? WHERE  id_terrain = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getNom());
            pst.setString(2, entity.getAdresse());
            pst.setInt(3, entity.getId());

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
    public void delete(Terrain entity) {
        String requet = "DELETE FROM `terrain` WHERE id_terrain = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Terrain supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
