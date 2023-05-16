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
public class Reclamation {

    private int id;
    private Date date;
    private String objet;
    private String contenu;
    private String statut;
    private Joueur joueur;
    private ProprietaireTerrain proprietaireTerrain;

    public Reclamation() {
    }

    //generate constructo with parameters here
    
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getObjet() {
        return objet;
    }

    public String getContenu() {
        return contenu;
    }

    public String getStatut() {
        return statut;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ProprietaireTerrain getProprietaireTerrain() {
        return proprietaireTerrain;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setProprietaireTerrain(ProprietaireTerrain proprietaireTerrain) {
        this.proprietaireTerrain = proprietaireTerrain;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", date=" + date + ", objet=" + objet + ", contenu=" + contenu + ", statut=" + statut + ", joueur=" + joueur + ", proprietaireTerrain=" + proprietaireTerrain + '}';
    }
    
    
    
    

}
