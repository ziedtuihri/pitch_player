/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.Terrain;
import edu.connection1cinfo1.entities.Tournoi;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class TournoiCRUD implements ICRUD<Tournoi> {

    public void addEntity(Tournoi t) {
        try {
            String requete = "INSERT INTO tournoi (nom_tournoi,nbr_participant,date_debut,"
                    + "date_fin,"
                    + "lieu) VALUES" + "(?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getNbrParticipant());
            pst.setDate(3, Date.valueOf(t.getDateDebut()));
            pst.setDate(4, Date.valueOf(t.getDateFin()));
            pst.setString(5, t.getLieu());

            pst.executeUpdate();
            System.out.println("Tournoi crée");
        } catch (SQLException ex) {
            System.out.println("ex.getMessage");
        }
    }

    public List<Tournoi> displayEntities() {
        List<Tournoi> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM tournoi";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Tournoi t = new Tournoi();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString("nom_tournoi"));
                t.setNbrParticipant(rs.getString("nbr_participant"));
                java.sql.Date sqlDateDebut = rs.getDate("date_debut");
                LocalDate dateDebut = sqlDateDebut.toLocalDate();
                t.setDateDebut(dateDebut);
                java.sql.Date sqlDateFin = rs.getDate("date_fin");
                LocalDate dateFin = sqlDateFin.toLocalDate();
                t.setDateFin(dateFin);

                t.setLieu(rs.getString("lieu"));

                myList.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("ex.getMessage");
        }
        return myList;

    }

    public Tournoi getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public void update(Tournoi t) {
        String requet = "UPDATE terrain SET nom_tournoi= ? WHERE id_tournoi = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setString(1, t.getNom());
            pst.setInt(2, t.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Nom tournoi modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void delete(Tournoi t) {
        String requet = "DELETE FROM tournoi WHERE id_tournoi = ?";
        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.setInt(1, t.getId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Tournoi supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
