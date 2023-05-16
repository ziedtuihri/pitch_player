/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class EquipeCRUD implements ICRUD<Equipe>{

    @Override
    public void addEntity(Equipe t) {
        try{
        String request = "INSERT INTO equipe(nom_equipe, annee_fondation) VALUES"+"(?,?)";
            
            PreparedStatement pst =MyConnection.getInstance().getCnx()
                               .prepareStatement(request);
            
            pst.setString(1, t.getNom());
            pst.setInt(2, t.getAnneeFondation());
            pst.executeUpdate();  //to modify sth in the table type retour int nbr ligne executed
            System.out.println("Equipe ajoute facon prepared statement");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Equipe> displayEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Equipe getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Equipe entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Equipe entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
