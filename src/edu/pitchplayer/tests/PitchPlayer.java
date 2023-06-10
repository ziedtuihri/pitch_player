/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.tests;

import edu.pitchplayer.entities.Equipe;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.services.EquipeDetailsService;
import edu.pitchplayer.utils.MyConnection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WIJDEN
 */
public class PitchPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // MyConnection mc = MyConnection.getInstance();

        try {
            MyConnection mc = MyConnection.getInstance();
            EquipeDetailsService equipeDetailsService = new EquipeDetailsService();

            // Test searching for Equipe by captain's name and address
            String captainName = "Doe";
            String captainAddress = "123 Street";

            List<Equipe> equipes = equipeDetailsService.searchEquipeByCaptain(captainName, captainAddress);

            if (!equipes.isEmpty()) {
                System.out.println("Equipe(s) found:");
                for (Equipe equipe : equipes) {
                //    System.out.println("ID: " + equipe.getId());
                    System.out.println("Nom: " + equipe.getNom());
                    System.out.println("Année de Fondation: " + equipe.getAnneeFondation());
                    System.out.println("----------------------");
                }
            } else {
                System.out.println("No matching Equipe found.");
            }

           // mc.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        }
        //layoutX="358.0" layoutY="172.0" prefHeight="200.0" prefWidth="200.0"
    }

}
