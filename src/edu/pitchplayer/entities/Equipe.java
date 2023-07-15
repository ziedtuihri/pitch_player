/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.entities;

/**
 *
 * @author WIJDEN
 */
public class Equipe extends User{

    private int id;
    private String nom;
    private int anneeFondation;

    public Equipe(int id, String nom, int anneeFondation) {
        this.id = id;
        this.nom = nom;
        this.anneeFondation = anneeFondation;
    }

    public Equipe(String nom, int anneeFondation) {
        this.nom = nom;
        this.anneeFondation = anneeFondation;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAnneeFondation() {
        return anneeFondation;
    }
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAnneeFondation(int anneeFondation) {
        this.anneeFondation = anneeFondation;
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nom=" + nom + ", anneeFondation=" + anneeFondation + '}';
    }
    
}
