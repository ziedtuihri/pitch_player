/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

/**
 *
 * @author ZIED
 */
public class Terrain {

    private int id;
    private String nom;
    private String adresse;
    private String ville;
    private String dimensions;
    private String equipements; //can be changed to a SET: Set<String> equipementsTerrain
                               //because the equipments are a list of equipements we can work on it later
    private String disponibilite;

    public Terrain() {
    }
    
    //generate constuctor with parameters here

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

    public String getDimensions() {
        return dimensions;
    }

    public String getEquipements() {
        return equipements;
    }

    public String getDisponibilite() {
        return disponibilite;
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

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Terrain{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", ville=" + ville + ", dimensions=" + dimensions + ", equipements=" + equipements + ", disponibilite=" + disponibilite + '}';
    }

}
