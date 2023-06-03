/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.entities;

import java.time.LocalDate;

/**
 *
 * @author WIJDEN
 */
public class Match {

    private int id;
    private String nom;
    private LocalDate date;
    private String statut;
    private String matchType; //Match can be a simple match or can be related to a tournoi
    private String commentaireMatch; //Players can put comments on a Match
    private Tournoi tournoi; //this is the Tournoi a Match belongs to if its matchType is related to a tournoi
    //Relationship between them is Match belongs to 1 and only Tournoi, but Tournoi can have 1 or many Match
    private User joueur; //This is the User of role joueur who will be creating the Match, he needs to make a reservation of a Terrain in order to create a Match
    //Relationship is Match can have many joueurs (Users) and joueur can create one or many matches
    private Terrain terrain; //This is the Terrain in which the Match will take place, Match can have 1 Terrain, but Terrain can host 1 or many Match

    public Match() {
    }

    public Match(int id) {
        this.id = id;
    }

    public Match(String nom, LocalDate date, String statut, String matchType, String commentaireMatch, Tournoi tournoi, User joueur, Terrain terrain) {
        this.nom = nom;
        this.date = date;
        this.statut = statut;
        this.matchType = matchType;
        this.commentaireMatch = commentaireMatch;
        this.tournoi = tournoi;
        this.joueur = joueur;
        this.terrain = terrain;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatut() {
        return statut;
    }

    public String getMatchType() {
        return matchType;
    }

    public String getCommentaireMatch() {
        return commentaireMatch;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public User getJoueur() {
        return joueur;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public void setCommentaireMatch(String commentaireMatch) {
        this.commentaireMatch = commentaireMatch;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public void setJoueur(User joueur) {
        this.joueur = joueur;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", statut=" + statut + ", matchType=" + matchType + ", commentaireMatch=" + commentaireMatch + ", tournoi=" + tournoi + ", joueur=" + joueur + ", terrain=" + terrain + '}';
    }

}
