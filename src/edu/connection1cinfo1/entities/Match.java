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
    private Tournoi tournoi; //Match can be part of a Tournoi
    private Equipe equipe; //Match can be played by 1 or many Equipes
    private Joueur joueur; //Match can be played by 1 or many Players if it doesn't belong to a Tournoi (match amical)

    public Match() {
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

    public Tournoi getTournoi() {
        return tournoi;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Joueur getJoueur() {
        return joueur;
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

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", statut=" + statut + ", score=" + score + ", type=" + type + ", commentaire=" + commentaire + ", tournoi=" + tournoi + ", equipe=" + equipe + ", joueur=" + joueur + '}';
    }
    
    
    
    
}
