/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
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
 * @author WIJDEN
 */
public class TerrainCRUD implements ICRUD<Terrain> {

    public void addEntity(Terrain t) {
        try {
            String requete = "INSERT INTO terrain (nom_terrain,adresse_terrain,ville_terrain,"
                    + "dimensions_terrain,"
                    + "equipements_terrain,disponibilite_terrain) VALUES" + "(?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getAdresse());
            pst.setString(3, t.getVille());
            pst.setString(4, t.getDimensions());
            pst.setString(5, t.getEquipements());
            pst.setString(6, t.getDisponibilite());
            pst.executeUpdate();
            System.out.println("Terrain Ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("ex.getMessage");
        }

    }

    @Override
    public List<Terrain> displayEntities() {
        List<Terrain> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM terrain";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Terrain t = new Terrain();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString("nom_terrain"));
                t.setAdresse(rs.getString("adresse_terrain"));
                t.setVille(rs.getString("ville_terrain"));
                t.setDimensions(rs.getString("dimensions_terrain"));
                t.setEquipements(rs.getString("equipements_terrain"));
                t.setDisponibilite(rs.getString("disponibilite_terrain"));
                myList.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("ex.getMessage");
        }
        return myList;
    }

    @Override
    public Terrain getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void update(Terrain t) {
        String requet = "UPDATE terrain SET nom_terrain= ?, adresse_terrain= ?, ville_terrain= ?,dimensions_terrain= ?,"
                + "equipements_terrain= ?, disponibilite_terrain= ? WHERE id_terrain = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, t.getNom());
            pst.setString(2, t.getAdresse());
            pst.setString(3, t.getVille());
            pst.setString(4, t.getDimensions());
            pst.setString(5, t.getEquipements());
            pst.setString(6, t.getDisponibilite());
            pst.setInt(7, t.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Terrain modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(Terrain t) {
        String requet = "DELETE FROM terrain WHERE id_terrain = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, t.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Terrain supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

}
