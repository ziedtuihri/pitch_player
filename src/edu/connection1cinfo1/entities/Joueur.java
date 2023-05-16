/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

import java.sql.Date;

/**
 *
 * @author WIJDEN
 */
public class Joueur {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Date dateNaissance;
    private String numeroTelephone;
    private String positionJeu;
    private String piedFortDominant;
    private int taille;
    private int poids;
    private int experienceJeu;
    private int nbrButs;
    private Equipe equipe; //represents relationship between Equipe and Joueur joueur belongs to 1 and only equipe

    public Joueur() {
    }
    //here insert other constructos if needed

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getPositionJeu() {
        return positionJeu;
    }

    public String getPiedFortDominant() {
        return piedFortDominant;
    }

    public int getTaille() {
        return taille;
    }

    public int getPoids() {
        return poids;
    }

    public int getExperienceJeu() {
        return experienceJeu;
    }

    public int getNbrButs() {
        return nbrButs;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public void setPositionJeu(String positionJeu) {
        this.positionJeu = positionJeu;
    }

    public void setPiedFortDominant(String piedFortDominant) {
        this.piedFortDominant = piedFortDominant;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public void setExperienceJeu(int experienceJeu) {
        this.experienceJeu = experienceJeu;
    }

    public void setNbrButs(int nbrButs) {
        this.nbrButs = nbrButs;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    
    
    

}
