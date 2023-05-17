/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.Match;
import edu.connection1cinfo1.entities.ProprietaireTerrain;
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
 * @author Zied
 */
public class ProprietaireTerrainCRUD implements ICRUD<ProprietaireTerrain>{
    public void addEntity(ProprietaireTerrain t) {
        try{
            String request = "INSERT INTO proprietaireTerrain (nom_prop, prenom_prop," +
                    " email_prop, password_prop," +
                    " adresse_prop, telephone_prop, informations_terrain,terrain_id," +
                    " joueur_id ) VALUES"+"(?,?,?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(request);

            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getPassword());
            pst.setString(5, t.getAdresse());
            pst.setString(6, t.getTelephone());
            pst.setString(7, t.getInformationsTerrain());
            pst.setInt(8, t.getTerrainID());
            pst.executeUpdate();
            System.out.println("proprietaireTerrain ajoute");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<ProprietaireTerrain> displayEntities() {
        List<ProprietaireTerrain> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM ProprietaireTerrain";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                ProprietaireTerrain p = new ProprietaireTerrain();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom_prop"));
                p.setPrenom(rs.getString("prenom_prop"));
                p.setEmail(rs.getString("email_prop"));
                p.setPassword(rs.getString("password_prop"));
                p.setAdresse(rs.getString("adresse_prop"));
                p.setTelephone(rs.getString("telephone_prop"));
                p.setInformationsTerrain(rs.getString("informations_terrain"));
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
    public ProprietaireTerrain getById(int id) {
        return null;
    }

    public void update(ProprietaireTerrain entity) {
        String requet = "UPDATE proprietaireTerrain SET nom_prop = ?, prenom_prop = ? WHERE  id_prop = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getNom());
            pst.setString(2, entity.getPrenom());
            pst.setInt(3, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("ProprietaireTerrain modifié");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void delete(ProprietaireTerrain entity) {
        String requet = "DELETE FROM proprietaireTerrain WHERE id_equipe = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("ProprietaireTerrain supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
