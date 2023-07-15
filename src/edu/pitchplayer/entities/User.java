/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.entities;

import edu.pitchplayer.entities.Equipe;

/**
 *
 * @author WIJDEN
 */
public class User {

    private int id;
    private String username;
    private String nom;
    private String prenom;
    private String email;
    private String pwd;
    private String adresse;
    private String phone;
    private Role role;



    public User(int id, String username, String nom, String prenom, String email, String pwd, Role role, String adresse, String phone, Equipe equipe, int height, int weight, int scores, String profile, Terrain terain) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.role = role;
        this.adresse = adresse;
        this.phone = phone;
    
    }
    public User() {
    }

    public User(String username, String nom, String prenom, String email, String pwd, Role role, String adresse, String phone) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.adresse = adresse;
        this.phone = phone;
     
    }

    public User(int id) {
        this.id = id;
    }

    public User(String username, String nom, String prenom, String email, String pwd, Role role, String adresse, String phone, Equipe equipe, int height, int weight, String profile) {
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.role = role;
        this.adresse = adresse;
        this.phone = phone;
       
    }

    public User(String username, String nom, String prenom, String email, String pwd, Role role, String adresse, String phone, Equipe equipe, int height, int weight, int scores, String profile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public Role getRole() {
        return role;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pwd=" + pwd + ", role=" + role + ", adresse=" + adresse + ", phone=" + phone + ", equipe="  +  ", profile="  + '}';
    }

    
    

}
