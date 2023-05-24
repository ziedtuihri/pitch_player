/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

import java.sql.Date;

/**
 *
 * @author ZIED
 */
public class Reclamation {

    private int id;
    private Date date;
    private String objet;
    private String contenu;
    private String statut;
    private int joueurID;
    private int proprietaireTerrainID;

    public Reclamation() {
    }

    public Reclamation(Date date, String objet, String contenu, String statut, int joueurID, int proprietaireTerrainID) {
        this.date = date;
        this.objet = objet;
        this.contenu = contenu;
        this.statut = statut;
        this.joueurID = joueurID;
        this.proprietaireTerrainID = proprietaireTerrainID;
    }

    public Reclamation(int id, Date date, String contenu) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
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

    public int getJoueurID() {
        return joueurID;
    }

    public int getProprietaireTerrainID() {
        return proprietaireTerrainID;
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

    public void setJoueurID(int joueurID) {
        this.joueurID = joueurID;
    }

    public void setProprietaireTerrainID(int proprietaireTerrainID) {
        this.proprietaireTerrainID = proprietaireTerrainID;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", date=" + date + ", objet=" + objet + ", contenu=" + contenu + ", statut=" + statut + ", joueur=" + joueurID + ", proprietaireTerrain=" + proprietaireTerrainID + '}';
    }
    
    
    
    

}
