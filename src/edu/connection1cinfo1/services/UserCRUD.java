/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.User;

import edu.connection1cinfo1.gui.JoueurFXMLController;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;
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


    public static boolean checkUsernameExists(String username) {
        try {
            String query = "SELECT COUNT(*) FROM `user` WHERE username = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            pst.close();
            return count > 0;
        } catch (SQLException ex) {
            System.out.println("Error checking username existence: " + ex.getMessage());
            return false;
        }
    }
    
        public static String checkAccountExists(String email, String username) {
        try {
            
            String hashedPassword = "null";
            String query = "SELECT pwd FROM `user` WHERE (email = ? OR username = ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
              hashedPassword  = rs.getString("pwd");   
            }
            System.out.println("hashed Password: " +hashedPassword);
            rs.close();
            pst.close();
            return hashedPassword;
            
        } catch (SQLException ex) {
            System.out.println("Error checking Account existence: " + ex.getMessage());
            return "null";
        }
    }
        
        public static boolean checkEmailExists(String email) {
        try {
            String query = "SELECT COUNT(*) FROM `user` WHERE email = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            pst.close();
            return count > 0;
        } catch (SQLException ex) {
            System.out.println("Error checking email existence: " + ex.getMessage());
            return false;
        }
    }
        
    public static String getEmailWithUsername(String username) {
        try {
            String emailWithUsername = "null";
            String query = "SELECT email FROM `user` WHERE username = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
             if(rs.next()){
              emailWithUsername  = rs.getString("email");   
            }
            
            rs.close();
            pst.close();
            return emailWithUsername;
        } catch (SQLException ex) {
            System.out.println("Error checking email existence: " + ex.getMessage());
            return "null";
        }
    }

    public boolean addEntitySignUp(User user) {

        try {
            String query = "INSERT INTO User "
                    + "(username, nom, prenom, email, pwd, adresse, role) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getNom());
            pst.setString(3, user.getPrenom());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPwd());
            pst.setString(6, user.getAdresse());
            pst.setString(7, user.getPhone());

            pst.executeUpdate();
            System.out.println("User added successfully.");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int getId(String username){
        try {

            int idUser = 0;
            String query = "SELECT id FROM `user` WHERE (email = ? OR username = ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, username);

            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                idUser  = rs.getInt("id");
            }

            rs.close();
            pst.close();
            return idUser;

        } catch (SQLException ex) {
            System.out.println("Error checking Account existence: " + ex.getMessage());
            return 0;
        }
    }



    public String getNameById(int id_user){

        String nom = null, prenom = null;
        try {


            String query = "SELECT nom, prenom FROM `user` WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id_user);

            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                nom  = rs.getString("nom");
                prenom  = rs.getString("prenom");
            }

            rs.close();
            pst.close();

            return nom+"_"+prenom;

        } catch (SQLException ex) {
            System.out.println("Error checking Account existence: " + ex.getMessage());
            return "null";
        }

    }

}
