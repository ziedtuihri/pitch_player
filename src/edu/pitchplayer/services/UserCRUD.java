/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Equipe;
import edu.pitchplayer.entities.Role;
import edu.pitchplayer.entities.User;

import edu.pitchplayer.interfaces.ICRUD;
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
public class UserCRUD implements ICRUD<User> {

    @Override
    public void addEntity(User user) {

        try {
            String query = "INSERT INTO User "
                    + "(username, nom, prenom, email, pwd, role, adresse, phone, equipe_id, height, weight, scores, profile) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(query);

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getNom());
            pst.setString(3, user.getPrenom());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPwd());
            pst.setString(6, user.getRole().toString());
            pst.setString(7, user.getAdresse());
            pst.setString(8, user.getPhone());
            pst.setInt(9, user.getEquipe().getId());

            if (user.getEquipe() != null) {
                pst.setInt(9, user.getEquipe().getId());
            } else {
                pst.setNull(9, java.sql.Types.INTEGER);
            }
            pst.setInt(10, user.getHeight());
            pst.setInt(11, user.getWeight());
            pst.setInt(12, user.getScores());
            pst.setString(13, user.getProfile());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> displayEntities() {
        List<User> userList = new ArrayList<>();

        try {
            String query = "SELECT * FROM User";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            EquipeCRUD equipeCRUD = new EquipeCRUD();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String pwd = rs.getString("pwd");
                Role role = Role.valueOf(rs.getString("role"));
                String adresse = rs.getString("adresse");
                String phone = rs.getString("phone");
                int equipeId = rs.getInt("equipe_id");

                Equipe equipe = null;

                if (!rs.wasNull()) {
                    equipe = equipeCRUD.getById(equipeId);
                }

                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                int scores = rs.getInt("scores");
                String profile = rs.getString("profile");

                User user = new User(username, nom, prenom, email, pwd, role, adresse,
                        phone, equipe, height, weight, scores, profile);
                user.setId(id);

                userList.add(user);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return userList;
    }

    @Override
    public User getById(int id) {
        User user = null;
        EquipeCRUD equipeCRUD = new EquipeCRUD();

        try {
            String query = "SELECT * FROM User WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String pwd = rs.getString("pwd");
                Role role = Role.valueOf(rs.getString("role"));
                String adresse = rs.getString("adresse");
                String phone = rs.getString("phone");
                int equipeId = rs.getInt("equipe_id");

                Equipe equipe = null;

                if (!rs.wasNull()) {
                    equipe = equipeCRUD.getById(equipeId);
                }

                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                int scores = rs.getInt("scores");
                String profile = rs.getString("profile");

                user = new User(username, nom, prenom, email, pwd, role, adresse,
                        phone, equipe, height, weight, scores, profile);
                user.setId(id);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    @Override
    public void update(User entity) {
        try {
            String query = "UPDATE User SET username = ?, nom = ?, prenom = ?, "
                    + "email = ?, pwd = ?, role = ?, adresse = ?, phone = ?, equipe_id = ?, "
                    + "height = ?, weight = ?, scores = ?, profile = ? WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setString(1, entity.getUsername());
            pst.setString(2, entity.getNom());
            pst.setString(3, entity.getPrenom());
            pst.setString(4, entity.getEmail());
            pst.setString(5, entity.getPwd());
            pst.setString(6, entity.getRole().toString());
            pst.setString(7, entity.getAdresse());
            pst.setString(8, entity.getPhone());
            if (entity.getEquipe() != null) {
                pst.setInt(9, entity.getEquipe().getId());
            } else {
                pst.setNull(9, java.sql.Types.INTEGER);
            }
            pst.setInt(10, entity.getHeight());
            pst.setInt(11, entity.getWeight());
            pst.setInt(12, entity.getScores());
            pst.setString(13, entity.getProfile());
            pst.setInt(14, entity.getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(User entity) {
        try {
            String query = "DELETE FROM User WHERE id = ?";
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, entity.getId());
            pst.executeUpdate();
            
            System.out.println("User deleted successfully.");
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error deleting user: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
