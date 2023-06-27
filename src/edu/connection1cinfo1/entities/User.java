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
public class User {

    private int id;
    private String username;
    private String nom;
    private String prenom;
    private String email;
    private String pwd;
    private String adresse;
    private String phone;
    private int height;
    private int weight;
    private String profile;
    private String code;
    private String role;
    private Equipe equipe;
    
    private final static User INSTANCE = new User();
    
    private User user;
  
    
    public User() {

    }

    public static User getInstance(){
        return INSTANCE;
    }
      
    public void setUser(User u) {
        this.user = u;
    }
  
    public User getUser() {
        return this.user;
    }
      
    public User(String username, String nom, String prenom, String email, String pwd, String adresse, String role) {
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.adresse = adresse;
        this.role = role;
    }

    public User(String username) {
        this.username = username;
    }
    
    
    
    public User(String username, String nom, String prenom, String email, String pwd, String adresse, String phone, int height, int weight, String profile, String code, String role, Equipe equipe) {
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.adresse = adresse;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.profile = profile;
        this.code = code;
        this.role = role;
        this.equipe = equipe;
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

    public String getAdresse() {
        return adresse;
    }

    public String getPhone() {
        return phone;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getProfile() {
        return profile;
    }

    public String getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    public Equipe getEquipe() {
        return equipe;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pwd=" + pwd + ", adresse=" + adresse + ", phone=" + phone + ", height=" + height + ", weight=" + weight + ", profile=" + profile + ", code=" + code + ", role=" + role + ", equipe=" + equipe + '}';
    }

}
