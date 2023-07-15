package edu.pitchplayer.utils;

import javax.mail.internet.*; 
import java.util.Properties;  
import javax.mail.*; 


public class EmailSender {

  /*private void sendMatchCreationEmail(String toEmail) {
        String recipientEmail = "grakrem23@gmail.com"; // Replace with the actual recipient's email address
        String subject = "jeynbzpfrbugnrwn";
        String message = "nouvelle reclamation recu !";

        sendEmail(recipientEmail, subject, message);
    }*/

    public void sendEmail(String recipientEmail, String subject, String message) {
        // Set up the SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Add this line to enable TLSv1.2
        properties.put("mail.smtp.ssl.ciphers", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256"); // Add this line to specify the cipher suite

        // Set up the sender's credentials
        final String senderEmail = "grakrem23@gmail.com";
        final String password = "jeynbzpfrbugnrwn";
        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            // Create a new message
            Message emailMessage = new MimeMessage(session);

            // Set the sender and recipient addresses
            emailMessage.setFrom(new InternetAddress(senderEmail));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the email subject and content
            emailMessage.setSubject(subject);
            emailMessage.setText(message);

            // Send the email
            Transport.send(emailMessage);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
