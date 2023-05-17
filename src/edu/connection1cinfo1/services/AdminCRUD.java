/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Admin;
import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.Match;
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
public class AdminCRUD implements ICRUD<Admin> {
    public void addEntity(Admin t) {
        try{
            String request = "INSERT INTO admin (nom_admin, prenom_admin, email_admin, password_admin," +
                    " telephone_admin, adresse_admin) VALUES"+"(?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(request);

            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getPassword());
            pst.setString(5, t.getTelephone());
            pst.setString(6, t.getAdresse());
            pst.executeUpdate();  //to modify sth in the table type retour int nbr ligne executed
            System.out.println("Admin ajoute");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Admin> displayEntities() {
        List<Admin> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM admin";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                Admin p = new Admin();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom_admin"));
                p.setPrenom(rs.getString("prenom_admin"));
                p.setEmail(rs.getString("email_admin"));
                p.setPassword(rs.getString("password_admin"));
                p.setTelephone(rs.getString("telephone_admin"));
                p.setAdresse(rs.getString("adresse_admin"));

                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }

    @Override
    public Admin getById(int id) {
        return null;
    }

    public void update(Admin entity) {
        String requet = "UPDATE admin SET nom_admin= ?, prenom_admin= ? WHERE id_admin = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getNom());
            pst.setString(2, entity.getPrenom());
            pst.setInt(3, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Admin modifié");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void delete(Admin entity) {
        String requet = "DELETE FROM admin WHERE id_equipe = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Admin supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
