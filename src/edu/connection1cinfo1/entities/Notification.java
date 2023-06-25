/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connection1cinfo1.entities;

import java.time.LocalDate;

/**
 *
 * @author ztouahri2
 */
public class Notification {
 
    private int id;
    private int id_user_sent;
    private int id_user_receive;
    private String messege;
    private LocalDate date;
    private String type;
    private String vu;

    public Notification() {
    }

    public Notification(int id, int id_user_sent, int id_user_receive, String messege, LocalDate date, String type, String vu) {
        this.id = id;
        this.id_user_sent = id_user_sent;
        this.id_user_receive = id_user_receive;
        this.messege = messege;
        this.date = date;
        this.type = type;
        this.vu = vu;
    }

    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setId_user_sent(int id_user_sent) {
        this.id_user_sent = id_user_sent;
    }

    public void setId_user_receive(int id_user_receive) {
        this.id_user_receive = id_user_receive;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVu(String vu) {
        this.vu = vu;
    }

    public int getId() {
        return id;
    }

    public int getId_user_sent() {
        return id_user_sent;
    }

    public int getId_user_receive() {
        return id_user_receive;
    }

    public String getMessege() {
        return messege;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getVu() {
        return vu;
    }
    
    
    
}
