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
public class Reclamation {

    private int id;
    private LocalDate date;
    private String sujet;
    private String objet;
    private String statut; //This is the status of the complaint, it can be pending, or resolved
                            //When a User(role=joueur) makes a Reclamation, the User(role=owner) will be notified, he can read the details of the Reclamation and take action
                             //Once the Reclamation is resolved, the User(role=owner) sends a message to User(role=joueur) and the status of Reclamation becomes resolved
    private User user; //The is the User who will be making the Reclamation (role=joueur) and who will be treating the Reclamation (role=owner)
    private String messageTraitement;//This is notified when the User(role=joueur) receives the Reclamation response from User(role=owner)
    //private String treatmentMessage; //This is the message that User(role=owner) sends to User(role=joueur) once the Reclamation status is resolved

    public Reclamation(LocalDate date, String sujet, String objet, String statut, User user, String messageTraitement) {
        this.date = date;
        this.sujet = sujet;
        this.objet = objet;
        this.statut = statut;
        this.user = user;
        this.messageTraitement = messageTraitement;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSujet() {
        return sujet;
    }

    public String getObjet() {
        return objet;
    }

    public String getStatut() {
        return statut;
    }

    public User getUser() {
        return user;
    }

    public String getMessageTraitement() {
        return messageTraitement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessageTraitement(String messageTraitement) {
        this.messageTraitement = messageTraitement;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", date=" + date + ", sujet=" + sujet + ", objet=" + objet + ", statut=" + statut + ", user=" + user + ", messageTraitement=" + messageTraitement + '}';
    }

    
}
