/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.services;

import edu.pitchplayer.entities.Owner;
import edu.pitchplayer.interfaces.ICRUD;
import edu.pitchplayer.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class OwnerCRUD implements ICRUD<Owner> {

    @Override
    public void addEntity(Owner user) {
    try {
            String query = "INSERT INTO Owner "
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
            
            pst.setString(9,(new Integer(user.getTerrain().getId())).toString()) ;
         
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Owner> displayEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Owner getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Owner entity) {
     try {
            String query = "UPDATE Owner SET username = ?, nom = ?, prenom = ?, "
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
        
            pst.setInt(9, entity.getTerrain().getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @Override
    public void delete(Owner entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
