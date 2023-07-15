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
    private String statut;
    private BigDecimal montant;
    private ConfirmationStatut ownerConfirmationStatus;
    private NotificationStatut playerNotificationStatus;
    private NotificationStatut teamNotificationStatus;
    private Equipe equipe2 ;
    private Joueur joueur;
    private Terrain terrain;
    private Equipe equipe;
    private Match match ;


    public Reservation(int id, LocalDate date, String statut, BigDecimal montant, ConfirmationStatut ownerConfirmationStatus, NotificationStatut playerNotificationStatus, NotificationStatut teamNotificationStatus, Equipe equipe2, Joueur joueur, Terrain terrain) {

        this.date = date;
        this.statut = statut;
        this.montant = montant;
        this.ownerConfirmationStatus = ownerConfirmationStatus;
        this.playerNotificationStatus = playerNotificationStatus;
        this.teamNotificationStatus = teamNotificationStatus;
        this.equipe2 = equipe2;
        this.joueur = joueur;
        this.terrain = terrain;
        this.match = new Match(date, terrain, joueur.getEquipe(), equipe2, EtatDeMatch.Non_joué );
    }

    public enum ConfirmationStatut {
        Pending, Confirmed, Rejected
    }

    public enum NotificationStatut {
        NotNotified, Notified
    }

}
