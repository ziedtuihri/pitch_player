/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitchplayer.tests;

import edu.pitchplayer.entities.Equipe;
import edu.pitchplayer.entities.Reservation;
import edu.pitchplayer.entities.Terrain;
import edu.pitchplayer.entities.User;
import edu.pitchplayer.services.ReservationCRUD;
import edu.pitchplayer.services.UserCRUD;
import edu.pitchplayer.utils.MyConnection;
import java.time.LocalDate;
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
        MyConnection mc = MyConnection.getInstance();
// Creating an instance of UserCRUD
        UserCRUD userCRUD = new UserCRUD();

        /* // Testing the addEntity method
        User newUser = new User();
        newUser.setUsername("johnDoe");
        newUser.setNom("Doe");
        newUser.setPrenom("John");
        newUser.setEmail("johndoe@example.com");
        newUser.setPwd("password");
        newUser.setAdresse("123 Street");
        newUser.setPhone("123456789");
        newUser.setHeight(180);
        newUser.setWeight(70);
        newUser.setProfile("profile");
        newUser.setCode("ABC123");
        newUser.setRole("user");

        userCRUD.addEntity(newUser);
        System.out.println("User added successfully.");
         */
        // Testing the displayEntities method
        List<User> userList = userCRUD.displayEntities();
        for (User user : userList) {
            System.out.println(user);
        }

        // Testing the getById method
        int idToRetrieve = 3;
        User retrievedUser = userCRUD.getById(idToRetrieve);
        /*
        if (retrievedUser != null) {
            System.out.println("Retrieved User: " + retrievedUser);
        } else {
            System.out.println("User with ID " + idToRetrieve + " not found.");
        }*//*
        // Testing the update method
        int idToRetrieve = 3;
        User retrievedUser = userCRUD.getById(idToRetrieve);

// Testing the update method
        retrievedUser.setUsername("User*(");
        retrievedUser.setNom("Smith");
        retrievedUser.setPrenom("Jane");

        Equipe newEquipe = new Equipe();
        newEquipe.setId(1); // Set the ID of the new equipe
        newEquipe.setNom("New Equipe");
        newEquipe.setAnneeFondation(1997);

        retrievedUser.setEquipe(newEquipe);

        userCRUD.update(retrievedUser);
         */
        // Testing the delete method
        userCRUD.delete(retrievedUser);
        System.out.println("User deleted successfully.");

    }

}
