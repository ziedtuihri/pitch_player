/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.tests;

import edu.pitchplayer.entities.Reclamation;
import edu.pitchplayer.services.ReclamationCRUD;
import edu.pitchplayer.utils.MyConnection;
import edu.pitchplayer.utils.EmailSender;
import java.time.LocalDate;

/**
 *
 * @author Access
 */
public class MainClassR {

    public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
        ReclamationCRUD rcd = new ReclamationCRUD();
        //rcd.addEntity(r);
        // rcd.displayEntities();
        //rcd.delete(2);
        //rcd.getById(3);
        //em.send("pitchplayer780@gmail.com", "pitchplayeresprit", "grakrem23@gmail.com", "testmail", "c'est un mail de test");
        //rcd.addEntity(r);
        // rcd.displayEntities();

        //EmailSender em = new EmailSender();
        //em.sendEmail("mohamedgrami350@gmail.com", "sujet", "message");
        rcd.delete(16);
    }
}
