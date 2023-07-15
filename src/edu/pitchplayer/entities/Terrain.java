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
    private String disponibilite;
    private User owner;

    public Terrain(int id, String nom, String adresse, String ville, float longueur, float largeur, String disponibilite, User owner) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.longueur = longueur;
        this.largeur = largeur;
        this.disponibilite = disponibilite;
        this.owner = owner;
    }

    //Getters

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

    public String getDisponibilite() {
        return disponibilite;
    }

    public User getOwner() {
        return owner;
    }
    //Setters

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

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
}
