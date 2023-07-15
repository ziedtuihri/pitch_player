package edu.pitchplayer.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIJDEN
 */
public class Joueur extends User {
    private int height;
    private int weight;
    private String profile;

    public Joueur(String username, String nom, String prenom, String email, String pwd, Role role, String adresse, String phone, Equipe equipe, int height, int weight, String profile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getWeight() {
        return weight;
    }

    public String getProfile() {
        return profile;
    }
    private Equipe equipe;

 
    public Joueur(int height, int weight, String profile, Reservation reservation, Equipe equipe, String username, String nom, String prenom, String email, String pwd, Role role, String adresse, String phone) {
        super(username, nom, prenom, email, pwd, role, adresse, phone, equipe, height, weight, profile);
        this.height = height;
        this.weight = weight;
        this.profile = profile;
        this.equipe = equipe;
    }

  public  Equipe getEquipe() {
       return this.equipe;
     }

    public int getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
