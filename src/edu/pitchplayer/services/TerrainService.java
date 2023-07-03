/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.TerrainCount;

import edu.pitchplayer.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Raouf
 */
public class TerrainService {
    public List<Terrain> RechercheTerrainNom(String nom) throws SQLException {
        List<Terrain> equipeList = new ArrayList<>();

        try {

            String query = "SELECT DISTINCT nom, adresse,longueur,largeur"
                    + "FROM terrain "
               
                    + "WHERE nom like ? ";

            //here is modified to search for a string that is like ? and the following entered characters are like % (partial name matching)
            if (nom != null && !nom.isEmpty()) {
                query += "AND nom LIKE ? ";
            }

            
            //////////////
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            int paramIndex = 1;

            if (nom != null && !nom.isEmpty()) {
                pst.setString(paramIndex, nom + "%");
                paramIndex++;
            }

           
            //////////////
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Terrain terrain = new Terrain();
                // equipe.setId(rs.getInt("id"));
                terrain.setNom(rs.getString("nom"));
                terrain.setAdresse(rs.getString("adresse"));
                terrain.setLongueur(rs.getFloat("longueur"));
terrain.setLargeur(rs.getFloat("largeur"));

                equipeList.add(terrain);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return equipeList;
    }
    public List<String> getSuggestionsForNom(String partialName) throws SQLException {
        List<String> suggestions = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT nom FROM terrain  WHERE  nom LIKE ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, partialName + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String suggestion = rs.getString("nom");
                suggestions.add(suggestion);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return suggestions;
    }
//    public void displayTerrainCountByVille() {
//    try {
//        String query = "SELECT ville, COUNT(*) AS terrain_count FROM Terrain GROUP BY ville ORDER BY terrain_count DESC";
//        Statement st = MyConnection.getInstance().getCnx().createStatement();
//        ResultSet rs = st.executeQuery(query);
//
//        while (rs.next()) {
//            String ville = rs.getString("ville");
//            int terrainCount = rs.getInt("terrain_count");
//            System.out.println("Ville: " + ville + ", Nombre de terrains: " + terrainCount);
//        }
//
//        rs.close();
//        st.close();
//    } catch (SQLException ex) {
//        System.out.println("Erreur lors de l'affichage du nombre de terrains par ville : " + ex.getMessage());
//    }
//    }
    public ObservableList<TerrainCount> getTerrainCountByVille() {
    ObservableList<TerrainCount> terrainCounts = FXCollections.observableArrayList();

    try {
        String query = "SELECT adresse, COUNT(*) AS terrain_count FROM Terrain GROUP BY adresse ORDER BY terrain_count DESC";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String ville = rs.getString("adresse");
            int terrainCount = rs.getInt("terrain_count");
            terrainCounts.add(new TerrainCount(ville, terrainCount));
        }

        rs.close();
        st.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération du nombre de terrains par ville : " + ex.getMessage());
    }

    return terrainCounts;
}
    public List<Terrain> getTerrainsByLettre(String lettre) throws SQLException {
    List<Terrain> terrains = new ArrayList<>();

    try {
        String query = "SELECT * FROM terrain WHERE nom LIKE ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
        pst.setString(1, "%" + lettre + "%");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Terrain terrain = new Terrain();
            terrain.setNom(rs.getString("nom"));
            terrain.setAdresse(rs.getString("adresse"));
            terrain.setLongueur(rs.getFloat("longueur"));
terrain.setLargeur(rs.getFloat("largeur"));

            terrains.add(terrain);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des terrains par lettre : " + ex.getMessage());
    }

    return terrains;
}
     public List<Terrain> getTerrainsByNom(String nom) throws SQLException {
        List<Terrain> terrains = new ArrayList<>();

        try {
            String query = "SELECT * FROM terrain WHERE nom LIKE ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, "%" + nom + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Terrain terrain = new Terrain();
                terrain.setNom(rs.getString("nom"));
                terrain.setAdresse(rs.getString("adresse"));
                terrain.setLongueur(rs.getFloat("longueur"));
terrain.setLargeur(rs.getFloat("largeur"));

                terrains.add(terrain);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des terrains par nom : " + ex.getMessage());
        }

        return terrains;
    }

    public List<Terrain> getTerrainsByAdresse(String adresse) throws SQLException {
        List<Terrain> terrains = new ArrayList<>();

        try {
            String query = "SELECT * FROM terrain WHERE adresse LIKE ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, "%" + adresse + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Terrain terrain = new Terrain();
                terrain.setNom(rs.getString("nom"));
                terrain.setAdresse(rs.getString("adresse"));
                terrain.setLongueur(rs.getFloat("longueur"));
terrain.setLargeur(rs.getFloat("largeur"));

                terrains.add(terrain);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des terrains par adresse : " + ex.getMessage());
        }

        return terrains;
    }

     public List<TerrainCount> getTerrainCountByTournoi() {
        List<TerrainCount> terrainCounts = new ArrayList<>();

        try {
            String query = "SELECT t.nom, COUNT(*) AS tournoi_count " +
                    "FROM Terrain t LEFT JOIN Tournoi tn ON t.id = tn.terrain_id " +
                    "GROUP BY t.id " +
                    "ORDER BY COUNT(*) DESC";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String terrainNom = rs.getString("nom");
                int tournoiCount = rs.getInt("tournoi_count");
                terrainCounts.add(new TerrainCount(terrainNom, tournoiCount));
            }

            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération du nombre de tournois par terrain : " + ex.getMessage());
        }

        return terrainCounts;
    }
    
    
    
  
    }



    
