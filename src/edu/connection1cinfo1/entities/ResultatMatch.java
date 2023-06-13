/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection1cinfo1.entities;

/**
 *
 * @author WIJDEN
 */
public class ResultatMatch {

    private int id;
    private Match match;
    private Equipe equipe1;
    private Equipe equipe2;
    private int equipe1Score;
    private int equipe2Score;

    public ResultatMatch(Match match, Equipe equipe1, Equipe equipe2, int equipe1Score, int equipe2Score) {
        this.match = match;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.equipe1Score = equipe1Score;
        this.equipe2Score = equipe2Score;
    }

    public ResultatMatch() {
    }

    public int getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public int getEquipe1Score() {
        return equipe1Score;
    }

    public int getEquipe2Score() {
        return equipe2Score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public void setEquipe1Score(int equipe1Score) {
        this.equipe1Score = equipe1Score;
    }

    public void setEquipe2Score(int equipe2Score) {
        this.equipe2Score = equipe2Score;
    }

    @Override
    public String toString() {
        return "ResultatMatch{" + "id=" + id + ", match=" + match + ", equipe1=" + equipe1 + ", equipe2=" + equipe2 + ", equipe1Score=" + equipe1Score + ", equipe2Score=" + equipe2Score + '}';
    }
    
    
}
