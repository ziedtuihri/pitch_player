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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class EquipeCRUD implements ICRUD<Equipe> {

    @Override
    public void addEntity(Equipe equipe) {

        try {
            String query = "INSERT INTO Equipe (nom, annee_fondation) VALUES (?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, equipe.getNom());
            pst.setInt(2, equipe.getAnneeFondation());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Equipe> displayEntities() {
        List<Equipe> equipes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Equipe";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                int anneeFondation = rs.getInt("annee_fondation");
                Equipe equipe = new Equipe(id, nom, anneeFondation);
                equipes.add(equipe);
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return equipes;
    }

    @Override
    public Equipe getById(int id) {
        Equipe equipe = null;

        try {
            String query = "SELECT * FROM Equipe WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                int anneeFondation = rs.getInt("annee_fondation");

                equipe = new Equipe(id, nom, anneeFondation);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return equipe;
    }

    @Override
    public void update(Equipe entity) {
        try {
            String query = "UPDATE Equipe SET nom = ?, annee_fondation = ? WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setString(1, entity.getNom());
            pst.setInt(2, entity.getAnneeFondation());
            pst.setInt(3, entity.getId());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Equipe entity) {
        try {
            String query = "DELETE FROM Equipe WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, entity.getId());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
