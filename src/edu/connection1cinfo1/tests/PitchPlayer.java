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
        
        /*
        Joueur joueur = new Joueur(
                "zied3355", "th3355", "email@gmail.com",
                "12345", sqlDate, "00000000006",
                "position jeu",
                "pied front", 12, 80, 1,
                6,
                equipe.getId()
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
        System.out.println(joueurCRUD.displayEntities()+"\n"+joueurCRUD.meilleurJoueur());

        */

    }

}
