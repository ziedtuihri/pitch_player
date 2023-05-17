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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zied
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
        List<Equipe> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM equipe";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                Equipe p = new Equipe();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom_equipe"));
                p.setAnneeFondation(rs.getInt("annee_fondation"));
                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    return myList;
    }

    @Override
    public Equipe getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Equipe entity) {
        String requet = "UPDATE equipe SET nom_equipe= ?, annee_fondation= ? WHERE id_equipe = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getNom());
            pst.setInt(2, entity.getAnneeFondation());
            pst.setInt(3, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Equipe modifié");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Equipe entity) {
        String requet = "DELETE FROM equipe WHERE id_equipe = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Equipe supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
