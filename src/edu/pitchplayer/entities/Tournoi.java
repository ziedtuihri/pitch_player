/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.entities;

import java.time.LocalDate;
import java.util.HashSet;

/**
 *
 * @author WIJDEN
 */
public class Tournoi {

    private int id;
    private String nom;
    private HashSet<Equipe> LesEquipes;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    
 

    public Tournoi(int id, String nom, int nbrParticipant, LocalDate dateDebut, LocalDate dateFin, String lieu, User owner) {
        this.id = id;
        this.nom = nom;
        this.LesEquipes= new HashSet<Equipe>() ;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

    }
    //Getters
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }



    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

 
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    
    
}
