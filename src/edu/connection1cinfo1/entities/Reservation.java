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
 * @author WIJDEN
 */
public class Reservation {
    private int id;
    private Date date;
    private String statut;
    private BigDecimal montant;
    private Joueur joueur; //Joueur can make one or more reservations based on the Terrain availability
    private Terrain terrain; //Reservation belongs to a Terrain

    public Reservation() {
    }
    
    //here insert a parametred constructor

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getStatut() {
        return statut;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Terrain getTerrain() {
        return terrain;
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

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date=" + date + ", statut=" + statut + ", montant=" + montant + ", joueur=" + joueur + ", terrain=" + terrain + '}';
    }
    
    
    
    
}
