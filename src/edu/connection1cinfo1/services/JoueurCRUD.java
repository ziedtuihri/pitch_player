/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Joueur;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;

import java.sql.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zied
 */
public class JoueurCRUD implements ICRUD<Joueur>{

    @Override
    public void addEntity(Joueur t) {
        try{
            String requet = "INSERT INTO joueur (nom_joueur, prenom_joueur, email_joueur, password_joueur, date_naissance, numero_telephone, position_jeu, pied_fort_dominant, taille, poids, experience_jeu, nbr_buts, equipe_id) values "
                    + "('" + t.getNom() + "','" + t.getPrenom() + "','" + t.getEmail() + "','" + t.getPassword() + "','" + t.getDateNaissance() + "','" + t.getNumeroTelephone() + "','" + t.getPositionJeu() + "','" + t.getPiedFortDominant() + "','" + t.getTaille() + "','" + t.getPoids() + "','" + t.getExperienceJeu() + "','" + t.getNbrButs() + "','" + t.getIdequipe() + "')";

            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();

            st.executeUpdate(requet);

            System.out.println("Joueur ajouté ");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public List<Joueur> displayEntities() {

        List<Joueur> myList = new ArrayList<>();
        try{
            String requet = "SELECT * FROM joueur";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);
            while(rs.next()){
                Joueur p = new Joueur();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom_joueur"));
                p.setPrenom(rs.getString("prenom_joueur"));
                p.setEmail(rs.getString("email_joueur"));
                p.setPassword(rs.getString("password_joueur"));
                p.setDateNaissance(rs.getDate("date_naissance"));
                p.setNumeroTelephone(rs.getString("numero_telephone"));
                p.setPositionJeu(rs.getString("position_jeu"));
                p.setPiedFortDominant(rs.getString("pied_fort_dominant"));
                p.setTaille(rs.getInt("taille"));
                p.setPoids(rs.getInt("poids"));
                p.setExperienceJeu(rs.getInt("experience_jeu"));
                p.setNbrButs(rs.getInt("nbr_buts"));
                p.setIdequipe(rs.getInt("equipe_id"));
                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }

    @Override
    public Joueur getById(int id) {
        return null;
    }

    @Override
    public void update(Joueur entity) {
        String requet = "UPDATE joueur SET nom_joueur= ?, prenom_joueur= ? WHERE id_joueur = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, entity.getNom());
            pst.setString(2, entity.getPrenom());
            pst.setInt(3, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Joueur modifié");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Joueur entity) {
        String requet = "DELETE FROM joueur WHERE id_joueur = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, entity.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Joueur supprimé");
            }
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Joueur> meilleurJoueur(){
        List<Joueur> myList = new ArrayList<>();
        try{
            String requet = "SELECT nbr_buts,id_joueur,nom_joueur,prenom_joueur,email_joueur FROM `joueur` order by nbr_buts DESC LIMIT 1;";
            Statement st = MyConnection
                    .getInstance()
                    .getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requet);

            while(rs.next()){
                Joueur p = new Joueur();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom_joueur"));
                p.setPrenom(rs.getString("prenom_joueur"));
                p.setEmail(rs.getString("email_joueur"));
                myList.add(p);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }
}
