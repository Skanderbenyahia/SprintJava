/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author Skeez
 */
public class Formulaire_AdoptionController implements Initializable {

    @FXML
    private Pane motivation;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField adresse;
    @FXML
    private Label titre;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton enoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EnvoyerMail(ActionEvent event) throws IOException {
         try{
            String host ="smtp.gmail.com" ;
            String user = "motaz.pro@gmail.com";
            String pass = "motaz25671527";
            String to = "moatez.souid@esprit.tn";
            String from = "motaz.pro@gmail.com";
            String subject = "";
            
            
            String messageText = "Nom: "+nom.getText()+" Prenom: "+prenom.getText()+" Motivation: "+description.getText();
            boolean sessionDebug = false;
             subject = "Demande d'adoption";
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message sent successfully");
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Wishlist.fxml"));
        Parent root = loader.load();
        adresse.getScene().setRoot(root);
        }catch(MessagingException ex)
        {
            System.out.println(ex);
        }
         
    }
    }

   
