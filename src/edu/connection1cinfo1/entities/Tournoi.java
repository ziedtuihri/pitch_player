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
public class Tournoi {

    private int id;
    private String nom;
    private int nbrParticipant;
    private Date dateDebut;
    private Date dateFin;
    private String lieu;

    public Tournoi() {
    }

    //generate constuctor with parameters here
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNbrParticipant() {
        return nbrParticipant;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNbrParticipant(int nbrParticipant) {
        this.nbrParticipant = nbrParticipant;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nom=" + nom + ", nbrParticipant=" + nbrParticipant + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", lieu=" + lieu + '}';
    }

}
