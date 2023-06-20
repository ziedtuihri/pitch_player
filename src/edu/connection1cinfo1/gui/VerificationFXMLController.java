package edu.connection1cinfo1.gui;





import edu.connection1cinfo1.services.UserCRUD;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import javax.mail.MessagingException;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.ResourceBundle;

import java.util.Properties;
import java.util.Random;
import javax.mail.internet.MimeBodyPart;


/**
 * FXML Controller class
 *
 * @author ztouahri2
 */
public class VerificationFXMLController implements Initializable {

    @FXML
    private TextField idUsernameEmail;
    @FXML
    private TextField idCode;
    @FXML
    private TextField idPasswordUpdate1;
    @FXML
    private TextField idPasswordUpdate2;
    @FXML
    private Button idUpdatePassword;
    @FXML
    private Text idMessageCode;
    @FXML
    private Text idMessageUsername;

    /**
     * Initializes the controller class.
     */
        // Generate a random number between 1000 and 5000
        Random random = new Random();
        private int code = random.nextInt(5000) + 1000;
    
    private String email;
    @FXML
    private Button idSubmitEmail;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idCode.setVisible(false);
        idPasswordUpdate1.setVisible(false);
        idPasswordUpdate2.setVisible(false);
        idUpdatePassword.setVisible(false);
        idMessageCode.setVisible(false);
    }    

    @FXML
    private void SubmitCode(ActionEvent event) throws GeneralSecurityException, IOException, MessagingException {
     
       String usernameEmail = idUsernameEmail.getText().trim();
      
       UserCRUD userCRUD = new UserCRUD();
       

    
        String subject = "Reset your passwor with simole code";
        


        String text = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    <title>Password Reset</title>\n" +
        "    <style>\n" +
        "        /* Add your custom styles here */\n" +
        "        /* Global styles */\n" +
        "        body {\n" +
        "            font-family: Arial, sans-serif;\n" +
        "            margin: 0;\n" +
        "            padding: 0;\n" +
        "        }\n" +
        "        .container {\n" +
        "            max-width: 600px;\n" +
        "            margin: 0 auto;\n" +
        "            padding: 20px;\n" +
        "            background-color: #f5f5f5;\n" +
        "        }\n" +
        "        h2 {\n" +
        "            color: #333;\n" +
        "            margin-bottom: 20px;\n" +
        "        }\n" +
        "        p {\n" +
        "            margin: 10px 0;\n" +
        "        }\n" +
        "        .button {\n" +
        "            display: inline-block;\n" +
        "            background-color: #4CAF50;\n" +
        "            color: #fff;\n" +
        "            text-decoration: none;\n" +
        "            padding: 10px 20px;\n" +
        "            border-radius: 4px;\n" +
        "        }\n" +
        "        .button:hover {\n" +
        "            background-color: #45a049;\n" +
        "        }\n" +
        "    </style>\n" +
        "</head>\n" +
        "<body>\n" +
        "    <div class=\"container\">\n" +
        "        <h2>Password Reset</h2>\n" +
        "        <p>Hello,</p>\n" +
        "        <p>We have received a request to reset your password. To proceed with the password reset, use the code below:</p>\n" +
        "        <p><a class=\"button\" href=\"#\">" + code + "</a></p>\n" +
        "        <p>If you did not request a password reset, please ignore this email.</p>\n" +
        "        <p>Thank you,</p>\n" +
        "        <p>Your App Team</p>\n" +
        "    </div>\n" +
        "</body>\n" +
        "</html>";
       
        boolean check = false;
        String from = "zied.touahri@esprit.tn"; 
       if(userCRUD.checkEmailExists(usernameEmail)){
           idMessageUsername.setVisible(false);
           idUsernameEmail.setVisible(false);
           idSubmitEmail.setVisible(false);
           idUpdatePassword.setVisible(true);
           idCode.setVisible(true);
           idMessageCode.setVisible(true);
           check = sendEmail(usernameEmail, from, subject, text);
       }else if(userCRUD.checkUsernameExists(usernameEmail)){
           idMessageUsername.setVisible(false);
           idUsernameEmail.setVisible(false);
           idSubmitEmail.setVisible(false);
           idUpdatePassword.setVisible(true);
           idCode.setVisible(true);
           idMessageCode.setVisible(true);
           email = userCRUD.getEmailWithUsername(usernameEmail);
           check = sendEmail(email, from, subject, text);
       }else {
           alertFN("Problem account","we cant found this account !!!");
       }
       
        if (check) {
            System.out.println("Email is sent successfully");
        } else {
            System.out.println("There is problem in sending email");
        }

    }

    
    public boolean sendEmail(String to, String from, String subject, String text) {
        boolean flag = false;
             //logic
        //smtp properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "zied.touahri@esprit.tn";
        String password = "221SMT7953";


        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            
    

            message.setContent(text, "text/html");
            Transport.send(message);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @FXML
    private void UpdatePassword(ActionEvent event) {
        String inputCode = idCode.getText().trim();
        if(code == Integer.valueOf(inputCode)){
            System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssss");
        }else {
            alertFN("Wrong code ", "Try to generate another code or check your emails !!");
        }
    }
    
    public void alertFN(String msg1, String msg2) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(msg1);//msg1
                alert.setContentText(msg2);//msg2

                alert.showAndWait();
    }
}
