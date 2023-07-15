/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.tests;

import edu.pitchplayer.utils.mailAPI;
import com.mysql.jdbc.MySQLConnection;
import edu.pitchplayer.entities.Reservation;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.services.ReservationCRUD;
import edu.pitchplayer.services.UserCRUD;

import edu.pitchplayer.utils.MyConnection;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.List;

import edu.pitchplayer.utils.MyConnection;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import javax.mail.MessagingException;

/**
 *
 * @author WIJDEN
 */
public class PitchPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        MyConnection mc = MyConnection.getInstance();
//        Reservation r = new Reservation(LocalDate.now(), LocalDate.now(), true, "ok", 55.5f, "ok", "ok", new User(), new Terrain());
//        ReservationCRUD rcd = new ReservationCRUD();
//        rcd.addEntity(r);
//        Reservation r=new Reservation(1);
//        rcd.delete(r);

//        rcd.update(r);
      
    String senderEmail = "oussama.nefzi@esprit.tn";
    String senderPassword = "xhyrerukfgonkjfk";
    String recipientEmail = "oussamossnefzicho91@gmail.com";
    String subject = "facture de reservation";
    String message = "vous etes les bienvenue";

    mailAPI.send(senderEmail, senderPassword, recipientEmail, subject, message);
    System.out.println("Email envoyé avec succès !");
}

    }

