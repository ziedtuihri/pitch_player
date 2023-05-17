/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.tests;

import edu.connection1cinfo1.entities.Admin;
import edu.connection1cinfo1.entities.Equipe;
import edu.connection1cinfo1.entities.Joueur;
import edu.connection1cinfo1.services.AdminCRUD;
import edu.connection1cinfo1.services.EquipeCRUD;
import edu.connection1cinfo1.services.JoueurCRUD;
import java.sql.Date;

import edu.connection1cinfo1.utils.MyConnection;

/**
 *
 * @author WIJDEN
 */
public class PitchPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // ajouté joueur
        Equipe equipe = new Equipe(7, "equipe5", 1999);

        Admin admin = new Admin("name1", "prenom3", "emial@@", "password", "1234567", "adress");

        Joueur joueur = new Joueur(
                "zied3355",
                "th3355",
                "email@gmail.com",
                "12345",
                Date.valueOf("2000-10-10"),
                "00000000006",
                "position jeu",
                "pied front",
                12,
                80,
                1,
                6,
                equipe.getId()
        );

        Joueur joueurUpdate = new Joueur(
                9,
                "zied33",
                "th33"
        );

        // CRUD Entity
        EquipeCRUD equipeCRUD = new EquipeCRUD();

        // equipeCRUD.addEntity(equipe);
        // equipeCRUD.update(equipe);
        // equipeCRUD.delete(equipe);
        // System.out.println(equipeCRUD.displayEntities());

        // CRUD Joueur
        JoueurCRUD joueurCRUD = new JoueurCRUD();
         joueurCRUD.addEntity(joueur);
         //joueurCRUD.update(joueurUpdate);
        // joueurCRUD.delete(equipe);
        // System.out.println(joueurCRUD.displayEntities());

        // CRUD Admin
        AdminCRUD adminCRUD = new AdminCRUD();

        // equipeCRUD.adminCRUD(equipe);
        // equipeCRUD.adminCRUD(equipe);
        // equipeCRUD.adminCRUD(equipe);
        // System.out.println(adminCRUD.displayEntities());

    }

}
