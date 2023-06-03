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
public class Terrain {

    private int id;
    private String nom;
    private String adresse;
    private String ville;
    private float longueur;
    private float largeur;
    private User owner; //Terrain belongs to one User(role=owner), but User(role=owner) can have 1 or many Terrain
    //Terrain also will have relationship with the Match, since a Match will be held in a givn Terrain (1Match belongs to one Terrain, but Terrain can host many Match)
    //User(role=joueur) can book a Terrain if it is available (disponibilite = true) 
    //By default, the disponibilite is set to false, but when a reservation to a Terrain i made, it will be tru and Users can no longer book it in that given date
    private boolean disponible;
    //default value true, will be false if Terrain has a Reservation in the date User(role=joueur) wants to book it

    public Terrain() {
    }

    public Terrain(String nom, String adresse, String ville, float longueur, float largeur, User owner, boolean disponible) {
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.longueur = longueur;
        this.largeur = largeur;
        this.owner = owner;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public float getLongueur() {
        return longueur;
    }

    public float getLargeur() {
        return largeur;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Terrain{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", ville=" + ville + ", longueur=" + longueur + ", largeur=" + largeur + ", owner=" + owner + ", disponible=" + disponible + '}';
    }

}
