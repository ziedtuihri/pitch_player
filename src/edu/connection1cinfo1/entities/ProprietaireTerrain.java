/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

/**
 *
 * @author WIJDEN
 */
public class ProprietaireTerrain {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String adresse;
    private String telephone;
    private String informationsTerrain;
    private Terrain terrain; //this is the table which Prop is related to  Terrain has 1 Owner

    public ProprietaireTerrain() {
    }

    //generate constuctor with parameters here
    
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

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getInformationsTerrain() {
        return informationsTerrain;
    }

    public Terrain getTerrain() {
        return terrain;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setInformationsTerrain(String informationsTerrain) {
        this.informationsTerrain = informationsTerrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return "ProprietaireTerrain{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", adresse=" + adresse + ", telephone=" + telephone + ", informationsTerrain=" + informationsTerrain + ", terrain=" + terrain + '}';
    }

}
