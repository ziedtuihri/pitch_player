/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.tests;

import edu.connection1cinfo1.entities.Terrain;
import edu.connection1cinfo1.entities.Tournoi;
import edu.connection1cinfo1.services.TerrainCRUD;
import edu.connection1cinfo1.services.TournoiCRUD;
import edu.connection1cinfo1.utils.MyConnection;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author WIJDEN
 */
public class PitchPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Main class for test
        MyConnection mc = MyConnection.getInstance();
                Terrain t = new Terrain("OLD TRAFFORD", "Rue 14 janvier", "Mannouba", "80*50", "ballons", "disponible");

        //Terrain t2 = new Terrain("ETIHAD", "Avenu Habib Bourguiba", "Ariana", "80*50", "ballons", "disponible");
       // TerrainCRUD tr = new TerrainCRUD();
       // tr.addEntity(t);
      // Terrain t3 = new Terrain("mannouba",1);
      // tr.update(t3);
       //System.out.println(tr.displayEntities());
       //  Terrain t4 = new Terrain(2);
        
     // tr.delete(t4);
        //System.out.println(tr.displayEntities());
     //  Date DateDebut = new Date(1684348380000l);
       //Date DateFin = new Date(1684348380000l);
      // Tournoi tour = new Tournoi("yyyyy", 8, 31/07/2023, 05/08/2023, "mannouba");
       LocalDate dateDebut = LocalDate.of(2023, 8, 31);
        LocalDate dateFin = LocalDate.of(2023, 9, 4);

        Tournoi tour = new Tournoi("mot3a", "4", dateDebut, dateFin, "lac 2");
      
       TournoiCRUD tours = new TournoiCRUD();
       tours.addEntity(tour);
       System.out.println(tours.displayEntities());
        
        
        
    }

}
