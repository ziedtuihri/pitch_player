/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author WIJDEN
 */
public class Reservation {

    private int id;
    private LocalDate date;
    private boolean statut;
    private String message;
    private String ownerConfirmationStatus;
    private String playerNotificationStatus;
    private User user; //User(role=joueur) can make a reservation of a Terrain and User(role=owner) can accept or decline the reservation
    private Terrain terrain; //A reservation belongs to 1 Terrain, but Terrain can be booked many times
    // private Equipe equipe; //When User(role=joueur) makes a Terrain Reservation, his whole Equipe will be notified

    public Reservation() {
    }


    public Reservation(LocalDate date, boolean statut, String message, String ownerConfirmationStatus, String playerNotificationStatus, User user, Terrain terrain) {
        this.date = date;
        this.statut = statut;
        this.message = message;
        this.ownerConfirmationStatus = ownerConfirmationStatus;
        this.playerNotificationStatus = playerNotificationStatus;
        this.user = user;
        this.terrain = terrain;
    }
    

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isStatut() {
        return statut;
    }

    public String getMessage() {
        return message;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public void setMessage(String message) {
        this.message = message;
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
        return "Reservation{" + "id=" + id + ", date=" + date + ", statut=" + statut + ", ownerConfirmationStatus=" + ownerConfirmationStatus + ", playerNotificationStatus=" + playerNotificationStatus + ", user=" + user + ", terrain=" + terrain + '}';
    }

   

}
