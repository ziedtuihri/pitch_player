/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Equipe;
import edu.pitchplayer.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class EquipeDetailsService {

    public List<Equipe> searchEquipeByCaptain(String captainName, String captainAddress) throws SQLException {
        List<Equipe> equipeList = new ArrayList<>();

        try {

            String query = "SELECT DISTINCT e.id, e.nom, e.annee_fondation "
                    + "FROM Equipe e "
                    + "INNER JOIN User u ON e.id = u.equipe_id "
                    + "WHERE u.role = 'captain' ";

            //here is modified to search for a string that is like ? and the following entered characters are like % (partial name matching)
            if (captainName != null && !captainName.isEmpty()) {
                query += "AND u.nom LIKE ? ";
            }

            if (captainAddress != null && !captainAddress.isEmpty()) {
                query += "AND u.adresse LIKE ? ";
            }
            //////////////
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            int paramIndex = 1;

            if (captainName != null && !captainName.isEmpty()) {
                pst.setString(paramIndex, captainName + "%");
                paramIndex++;
            }

            if (captainAddress != null && !captainAddress.isEmpty()) {
                pst.setString(paramIndex, captainAddress + "%");
                paramIndex++;
            }
            //////////////
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Equipe equipe = new Equipe();
                // equipe.setId(rs.getInt("id"));
                equipe.setNom(rs.getString("nom"));
                equipe.setAnneeFondation(rs.getInt("annee_fondation"));
                equipeList.add(equipe);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return equipeList;
    }

    public List<String> getSuggestionsForCaptainName(String partialName) throws SQLException {
        List<String> suggestions = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT u.nom FROM User u WHERE u.role = 'captain' AND u.nom LIKE ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, partialName + "%"); //to replace characters after partialName (whatever user types)
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

}
