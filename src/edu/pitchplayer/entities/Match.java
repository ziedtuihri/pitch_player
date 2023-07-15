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
    private String score;
    private MatchType matchType;
    private String commentaireMatch;
    private Tournoi tournoi;
    private Equipe equipe1;
    private Equipe equipe2;
    private EtatDeMatch etat_Match ;
   


    public Match(int id, String nom, LocalDate date) {
        this.id = id;
        this.nom = nom;
        this.date = date;
    }
   

    public Match(String nom, LocalDate date, String statut, String score, MatchType matchType, String commentaireMatch, Tournoi tournoi, Equipe equipe1, Equipe equipe2, User joueur, Terrain terrain, SocialMediaShareStatus socialMediaShareStatus, String socialMediaShareLink) {
        this.nom = nom;
        this.date = date;
        this.statut = statut;
        this.score = score;
        this.matchType = matchType;
        this.commentaireMatch = commentaireMatch;
        this.tournoi = tournoi;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
       
     
      
    }

    public Match(String nom, LocalDate date, String statut, String score, MatchType matchType, String commentaireMatch) {
        this.nom = nom;
        this.date = date;
        this.statut = statut;
        this.score = score;
        this.matchType = matchType;
        this.commentaireMatch = commentaireMatch;
    }

    Match(LocalDate date, Terrain terrain, Equipe equipe, Equipe equipe2, EtatDeMatch etatDeMatch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    //Getters
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

    public String getScore() {
        return score;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public String getCommentaireMatch() {
        return commentaireMatch;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }



    
    //Setters

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

    public void setScore(String score) {
        this.score = score;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public void setCommentaireMatch(String commentaireMatch) {
        this.commentaireMatch = commentaireMatch;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }


 

    public int getEquipe1Id() {
        return equipe1.getId();
    }

    public int getEquipe2Id() {
        return equipe2.getId();
    }

}
