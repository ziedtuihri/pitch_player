/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author ZIED
 */
public class Reservation {
    private int id;
    private Date date;
    private String statut;
    private float montant;
    private int joueurID; //Joueur can make one or more reservations based on the Terrain availability
    private int terrainID; //Reservation belongs to a Terrain

    public Reservation() {
    }
    
    //here insert a parametred constructor

    public Reservation(int id, String statut, float montant) {
        this.id = id;
        this.statut = statut;
        this.montant = montant;
    }

    public Reservation(Date date, String statut, float montant, int joueurID, int terrainID) {
        this.date = date;
        this.statut = statut;
        this.montant = montant;
        this.joueurID = joueurID;
        this.terrainID = terrainID;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getStatut() {
        return statut;
    }

    public float getMontant() {
        return montant;
    }

    public int getJoueurID() {
        return joueurID;
    }

    public int getTerrainID() {
        return terrainID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setJoueurID(int joueurID) {
        this.joueurID = joueurID;
    }

    public void setTerrainID(int terrainID) {
        this.terrainID = terrainID;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date=" + date + ", statut=" + statut + ", montant=" + montant + ", joueur=" + joueurID + ", terrain=" + terrainID + '}';
    }
    
    
    
    
}
