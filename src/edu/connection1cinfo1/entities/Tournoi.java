/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author WIJDEN
 */
public class Tournoi {

    private int id;
    private String nom;
    private String nbrParticipant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
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

    public String getNbrParticipant() {
        return nbrParticipant;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
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

    public void setNbrParticipant(String nbrParticipant) {
        this.nbrParticipant = nbrParticipant;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

  

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nom=" + nom + ", nbrParticipant=" + nbrParticipant + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", lieu=" + lieu + '}';
    }

    public Tournoi(int id, String nom, String nbrParticipant, LocalDate dateDebut, LocalDate dateFin, String lieu) {
        this.id = id;
        this.nom = nom;
        this.nbrParticipant = nbrParticipant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
    }

  

    public Tournoi(int id) {
        this.id = id;
    }

    public Tournoi(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Tournoi(String nom, String nbrParticipant, LocalDate dateDebut, LocalDate dateFin, String lieu) {
        this.nom = nom;
        this.nbrParticipant = nbrParticipant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
    }

  
    public Tournoi(String nom, String nbrParticipant, String lieu) {
        this.nom = nom;
        this.nbrParticipant = nbrParticipant;
        this.lieu = lieu;
    }
    
    
    

    
}
