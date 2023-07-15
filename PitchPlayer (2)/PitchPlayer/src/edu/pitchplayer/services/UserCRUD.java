/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Equipe;
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
                    + "(username, nom, prenom, email, pwd, adresse, phone, height, weight, profile, code, role, equipe_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getNom());
            pst.setString(3, user.getPrenom());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPwd());
            pst.setString(6, user.getAdresse());
            pst.setString(7, user.getPhone());
            pst.setInt(8, user.getHeight());
            pst.setInt(9, user.getWeight());
            pst.setString(10, user.getProfile());
            pst.setString(11, user.getCode());
            pst.setString(12, user.getRole());

            if (user.getEquipe() != null) {
                pst.setInt(13, user.getEquipe().getId());
            } else {
                pst.setNull(13, java.sql.Types.INTEGER);
            }

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
                String adresse = rs.getString("adresse");
                String phone = rs.getString("phone");
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                String profile = rs.getString("profile");
                String code = rs.getString("code");
                String role = rs.getString("role");
                int equipeId = rs.getInt("equipe_id");

                Equipe equipe = null;

                if (!rs.wasNull()) {
                    equipe = equipeCRUD.getById(equipeId);
                }

                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setEmail(email);
                user.setPwd(pwd);
                user.setAdresse(adresse);
                user.setPhone(phone);
                user.setHeight(height);
                user.setWeight(weight);
                user.setProfile(profile);
                user.setCode(code);
                user.setRole(role);
                user.setEquipe(equipe);

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
                String adresse = rs.getString("adresse");
                String phone = rs.getString("phone");
                int height = rs.getInt("height");
                int weight = rs.getInt("weight");
                String profile = rs.getString("profile");
                String code = rs.getString("code");
                String role = rs.getString("role");
                int equipeId = rs.getInt("equipe_id");

                Equipe equipe = equipeCRUD.getById(equipeId);

                user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setEmail(email);
                user.setPwd(pwd);
                user.setAdresse(adresse);
                user.setPhone(phone);
                user.setHeight(height);
                user.setWeight(weight);
                user.setProfile(profile);
                user.setCode(code);
                user.setRole(role);
                user.setEquipe(equipe);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    @Override
    public void update(User user) {
        try {
            String query = "UPDATE User SET username = ?, nom = ?, prenom = ?, "
                    + "email = ?, pwd = ?, role = ?, adresse = ?, phone = ?, equipe_id = ?, "
                    + "height = ?, weight = ?, profile = ? WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getNom());
            pst.setString(3, user.getPrenom());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPwd());
            pst.setString(6, user.getRole());
            pst.setString(7, user.getAdresse());
            pst.setString(8, user.getPhone());

            if (user.getEquipe() != null) {
                pst.setInt(9, user.getEquipe().getId());
            } else {
                pst.setNull(9, java.sql.Types.INTEGER);
            }

            pst.setInt(10, user.getHeight());
            pst.setInt(11, user.getWeight());
            pst.setString(12, user.getProfile());
            pst.setInt(13, user.getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try {
            String query = "DELETE FROM User WHERE id = ?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, user.getId());
            pst.executeUpdate();

            System.out.println("User deleted successfully.");
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Error deleting user: " + ex.getMessage());
        }
    }

}
