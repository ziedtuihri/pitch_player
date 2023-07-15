/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Dictionary;


public class Reservation {

    private int id;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private boolean statut;
    private String message;
    private float paiement;
    private String ownerConfirmationStatus;
    private String playerNotificationStatus;
    private User user; //User(role=joueur) can make a reservation of a Terrain and User(role=owner) can accept or decline the reservation
    private Terrain terrain; //A reservation belongs to 1 Terrain, but Terrain can be booked many times
    // private Equipe equipe; //When User(role=joueur) makes a Terrain Reservation, his whole Equipe will be notified

    public Reservation() {
    }

    public Reservation(int id) {
        this.id = id;
    }

    public Reservation(LocalDate date_debut,LocalDate date_fin, boolean statut, String message,float paiement,String ownerConfirmationStatus, String playerNotificationStatus, User user, Terrain terrain) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.statut = statut;
        this.message = message;
        this.paiement = paiement;
        this.ownerConfirmationStatus = ownerConfirmationStatus;
        this.playerNotificationStatus = playerNotificationStatus;
        this.user = user;
        this.terrain = terrain;
    }

    public int getId() {
        return id;
    }

   

    public boolean isStatut() {
        return statut;
    }

    public String getMessage() {
        return message;
    }

    public float getPaiement() {
        return paiement;
    }

    public String getOwnerConfirmationStatus() {
        return ownerConfirmationStatus;
    }

    public String getPlayerNotificationStatus() {
        return playerNotificationStatus;
    }

    public User getUser() {
        return user;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

  

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPaiement(float paiement) {
        this.paiement = paiement;
    }

    public void setOwnerConfirmationStatus(String ownerConfirmationStatus) {
        this.ownerConfirmationStatus = ownerConfirmationStatus;
    }

    public void setPlayerNotificationStatus(String playerNotificationStatus) {
        this.playerNotificationStatus = playerNotificationStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", statut=" + statut + ", message=" + message + ", paiement=" + paiement + ", ownerConfirmationStatus=" + ownerConfirmationStatus + ", playerNotificationStatus=" + playerNotificationStatus + ", user=" + user + ", terrain=" + terrain + '}';
    }

   
}

    

