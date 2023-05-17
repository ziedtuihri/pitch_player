/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

import java.sql.Date;

/**
 *
 * @author Zied
 */
public class Match {
    private int id;
    private String nom;
    private Date date;
    private String statut;
    private String score;
    private String type;
    private String commentaire;
    private int tournoiID; //Match can be part of a Tournoi
    private int equipeID; //Match can be played by 1 or many Equipes
    private int joueurID; //Match can be played by 1 or many Players if it doesn't belong to a Tournoi (match amical)

    public Match() {
    }

    public void setTournoiID(int tournoiID) {
        this.tournoiID = tournoiID;
    }

    public void setEquipeID(int equipeID) {
        this.equipeID = equipeID;
    }

    public void setJoueurID(int joueurID) {
        this.joueurID = joueurID;
    }

    public int getTournoiID() {
        return tournoiID;
    }

    public int getEquipeID() {
        return equipeID;
    }

    public int getJoueurID() {
        return joueurID;
    }
    //generate constructor with parameters here

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDate() {
        return date;
    }

    public String getStatut() {
        return statut;
    }

    public String getScore() {
        return score;
    }

    public String getType() {
        return type;
    }

    public String getCommentaire() {
        return commentaire;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", statut=" + statut + ", score=" + score + ", type=" + type + ", commentaire=" + commentaire + ", tournoi=" + tournoiID + ", equipe=" + equipeID + ", joueur=" + joueurID + '}';
    }
    
    
    
    
}
