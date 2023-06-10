/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connection1cinfo1.services;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ztouahri2
 */
public class FileOperations {
    
        public static HashMap<String, String> readPlayerDataFromFile(String FILE_NAME) throws Exception {
         HashMap<String, String> playerList = new HashMap<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "windows-1252"));
        while (true) {
            String line = input.readLine();
            if (line == null)
                break;
            String[] tokens = line.split(";");
            try {
//                String name, String unAccentedName, String clubName, String country, int age, double estimatedValue, int number, String position, double height, double weight, String pFoot, double weeklySalary, String imageSource, int isTransferListed, double transferFee
                // tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Club.decodeSalaryString(tokens[5]), Integer.parseInt(tokens[6]), tokens[7], Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]), tokens[10], Club.decodeSalaryString(tokens[11]), tokens[12], Integer.parseInt(tokens[13]), Club.decodeSalaryString(tokens[14])
               //  playerList.add(p);
               
               String name = tokens[0];
               String imageSource = tokens[12];
                 playerList.put(name, imageSource);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println(line);
            }
        }
        input.close();
        return playerList;
    }
    
        public static ArrayList<Pair<String, String>> readFlagLinkOfCountries(String FILE_NAME) throws IOException {
        ArrayList<Pair<String, String>> countryFlagList = new ArrayList<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME)));
        while (true) {
            String line = input.readLine();
            if (line == null) break;
            try {
                String[] tokens = line.split(";");
                String name = tokens[1];
                String flagLink = tokens[2];
                countryFlagList.add(new Pair<>(name, flagLink));
            } catch (Exception e) {
                System.out.println(e);
                System.out.println(line);
            }
        }
        input.close();
        return countryFlagList;
    }

    public static ArrayList<Pair<String, String>> readInformationOfClubs(String FILE_NAME, HashMap<String, String> unaccented_accented) throws IOException {
        ArrayList<Pair<String, String>> clubLogoList = new ArrayList<>();
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "windows-1252"));
        while (true) {
            String line = input.readLine();
            if (line == null) break;
            try {
                String[] tokens = line.split(";");
                String name = tokens[0];
                String unAccentedName = tokens[1];
                /*double worth = decodeSalaryString(tokens[2]);
                //double budget = decodeSalaryString(tokens[3]);
                String logoLink = tokens[4];
                clubLogoList.add(new Pair<>(name, logoLink));
                //var club = league.FindClub(name);
                if (club != null) {
                    club.setWorth(worth);
                    club.setTransferBudget(budget);
                    club.setLogoLink(logoLink);
                    club.setUnAccentedName(unAccentedName);
                    unaccented_accented.put(unAccentedName, club.getName());
                }
                */
            } catch (Exception e) {
                System.out.println(line);
                e.printStackTrace();
            }
        }
        input.close();
        return clubLogoList;
    }

    
}
