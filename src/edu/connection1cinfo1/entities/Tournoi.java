/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

import java.time.LocalDate;

/**
 *
 * @author WIJDEN
 */
public class Tournoi {

    private int id;
    private String nom;
    private int nbrParticipant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String lieu;
    private User owner; //This is the table Tournoi, it can only be created by User(role=owner) and Tournoi can have many participating Equipe 

    public Tournoi(int id, String nom, int nbrParticipant, LocalDate dateDebut, LocalDate dateFin, String lieu, User owner) {
        this.id = id;
        this.nom = nom;
        this.nbrParticipant = nbrParticipant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.owner = owner;
    }

    public Tournoi() {
    }

    public Tournoi(int id) {
        this.id = id;
    }

    public Tournoi(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Tournoi(String nom, int nbrParticipant, LocalDate dateDebut, LocalDate dateFin, String lieu, User owner) {
        this.nom = nom;
        this.nbrParticipant = nbrParticipant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.owner = owner;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNbrParticipant() {
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

    public void setNbrParticipant(int nbrParticipant) {
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

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nom=" + nom + ", nbrParticipant=" + nbrParticipant + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", lieu=" + lieu + ", owner=" + owner + '}';
    }
    
    
}
