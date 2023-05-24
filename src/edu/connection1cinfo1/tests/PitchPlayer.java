/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.tests;

import edu.connection1cinfo1.entities.*;
import edu.connection1cinfo1.services.*;

import java.sql.Date;

import edu.connection1cinfo1.utils.MyConnection;

/**
 *
 * @author ZIED
 */
public class PitchPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // time stamp new Date(1684349175000l)
        // il y a un'autre methode aussi Date.valueOf("2000-10-10")
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("utilDate:" + utilDate);
        System.out.println("sqlDate:" + sqlDate);


        Equipe equipe = new Equipe(7, "equipe5", 1999);

        Admin admin = new Admin("name1", "prenom3", "emial@@", "password",
                "1234567", "adress");

        Joueur joueur = new Joueur(
                "zied3355", "th3355", "email@gmail.com",
                "12345", sqlDate, "00000000006",
                "position jeu",
                "pied front", 12, 80, 1,
                6,
                equipe.getId()
        );

        Match match = new Match("name10", sqlDate,
                                "status10", "score10",
                                "type", "commentaire",
                                1, 1, 25);

        ProprietaireTerrain propTerrain = new ProprietaireTerrain(
                "namePropTerrain","prenomPrepTerrain","email@mail","pass123",
                "adress1", "1234567", "infoTerrain", 1);

        Reclamation reclamation = new Reclamation(
                sqlDate,
                "object ...",
                "contenue",
                "status",
                9,
                2
        );

        Reservation reservation = new Reservation(
                sqlDate,
                "statusss",
                20.22F,
                9,
                1
        );

        Terrain terrain = new Terrain(
                "Terrain1",
                "Adress2",
                "Ville3",
                "Dimention77",
                "Equipement99",
                "Dispo99"
        );


        //*** ALL  UPDATE ***//

        Joueur joueurUpdate = new Joueur(
                9, "zied33", "th33"
        );

        Match matchUpdate = new Match(
                2, "name99", sqlDate);

        ProprietaireTerrain propTerrainUpdate = new ProprietaireTerrain(
                3, "nome55", "prenom77");

        Reclamation reclamationUpdate = new Reclamation(
                2,
                sqlDate,
                "contenuuuuu"
        );

        Reservation reservationUpdate = new Reservation(
                2,
                "statussUPDATED",
                99.99F
        );

        Terrain terrainUpdate = new Terrain(
                1,
                "nomTerrainUpdated",
                "adress 09 __"
        );

        // CRUD Equipe
        EquipeCRUD equipeCRUD = new EquipeCRUD();

        // equipeCRUD.addEntity(equipe);
        // equipeCRUD.update(equipe);
        // equipeCRUD.delete(equipe);
        System.out.println(equipeCRUD.displayEntities());

        // CRUD Joueur
        JoueurCRUD joueurCRUD = new JoueurCRUD();
         // joueurCRUD.addEntity(joueur);
         // joueurCRUD.update(joueurUpdate);
         // joueurCRUD.delete(joueurUpdate);
        System.out.println(joueurCRUD.displayEntities());

        // CRUD Admin
        AdminCRUD adminCRUD = new AdminCRUD();

        // adminCRUD.addEntity(admin);
        // adminCRUD.update(equipe);
        // adminCRUD.delete(equipe);
        System.out.println(adminCRUD.displayEntities());

        // CRUD Match
        MatchCRUD matchCRUD = new MatchCRUD();

         // matchCRUD.addEntity(match);
         // matchCRUD.update(matchUpdate);
        //  matchCRUD.delete(matchUpdate);
         System.out.println(matchCRUD.displayEntities());

        // CRUD proprietaireTerrain
        ProprietaireTerrainCRUD proprietaireTerrainCRUD = new ProprietaireTerrainCRUD();

        // proprietaireTerrainCRUD.addEntity(propTerrain);
        // proprietaireTerrainCRUD.update(propTerrainUpdate);
        // proprietaireTerrainCRUD.delete(propTerrainUpdate);
         System.out.println(proprietaireTerrainCRUD.displayEntities());

        // CRUD Reclamation
        ReclamationCRUD reclamationCRUD = new ReclamationCRUD();

        //reclamationCRUD.addEntity(reclamation);
        //reclamationCRUD.update(reclamationUpdate);
        //reclamationCRUD.delete(reclamationUpdate);
        System.out.println(reclamationCRUD.displayEntities());

        // CRUD Reservation
        ReservationCRUD reservationCRUD = new ReservationCRUD();

        //reservationCRUD.addEntity(reservation);
        //reservationCRUD.update(reservationUpdate);
        //reservationCRUD.delete(reservationUpdate);
        System.out.println(reservationCRUD.displayEntities());

        // CRUD Terrain
        TerrainCRUD terrainCRUD = new TerrainCRUD();

        //terrainCRUD.addEntity(terrain);
        //terrainCRUD.update(terrainUpdate);
        //terrainCRUD.delete(terrainUpdate);
        System.out.println(reservationCRUD.displayEntities());
    }

}
