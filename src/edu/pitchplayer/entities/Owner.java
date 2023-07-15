/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.entities;

/**
 *
 * @author WIJDEN
 */
public class Owner extends User {
     private Terrain terain;
     private Tournoi tourrnoi ;

    public Owner(Terrain terain, Tournoi tourrnoi, int id, String username, String nom, String prenom, String email, String pwd, Role role, String adresse, String phone) {
        super(id, username, nom, prenom, email, pwd, role, adresse, phone);
        this.terain = terain;
        this.tourrnoi = tourrnoi;
    }

    public void setTerain(Terrain terain) {
        this.terain = terain;
    }

    public void setTourrnoi(Tournoi tourrnoi) {
        this.tourrnoi = tourrnoi;
    }

  

    public Tournoi getTourrnoi() {
        return tourrnoi;
    }

    public Terrain getTerrain() {
         return terain;
    }
}
