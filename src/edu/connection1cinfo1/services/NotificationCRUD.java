/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connection1cinfo1.services;

import edu.connection1cinfo1.entities.Notification;
import edu.connection1cinfo1.interfaces.ICRUD;
import edu.connection1cinfo1.utils.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ztouahri2
 */
public class NotificationCRUD implements ICRUD<Notification> {

    @Override
    public void addEntity(Notification t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Notification> displayEntities() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Notification getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Notification entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Notification entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int checkNotification(int id_user){
        try {
            String query = "SELECT COUNT(*) FROM `notification` WHERE id_user_receive = ? AND vu LIKE 'no'";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id_user);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            pst.close();
            return count;
        } catch (SQLException ex) {
            System.out.println("Error checking username existence: " + ex.getMessage());
            return 0;
        }
    }
    
    public List<Notification> getNotification(int id_user){
            
        List<Notification> notificationList = new ArrayList<>();

        try {
            String query = "SELECT * FROM `notification` WHERE id_user_receive = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.setInt(1, id_user);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int id_user_sent = rs.getInt("id_user_sent");
                int id_user_receive = rs.getInt("id_user_receive");
                String messege = rs.getString("messege");
                String type = rs.getString("type");
                Date date = rs.getDate("date");
                String vu = rs.getString("vu");
        

                Notification notification = new Notification();

                notification.setId(id);
                notification.setId_user_sent(id_user_sent);
                notification.setId_user_receive(id_user_receive);
                notification.setMessege(messege);
                notification.setType(type);
                notification.setDate(date);
                notification.setVu(vu);
                
                notificationList.add(notification);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return notificationList;
    }

    
    
}
