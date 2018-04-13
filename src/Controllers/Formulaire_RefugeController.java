/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Animal;
import Entity.Refuge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Skeez
 */
public class Formulaire_RefugeController implements Initializable,ControllerClassAdoption {

    @FXML
    private Pane motivation;
    private JFXTextField nom;
    private JFXTextField prenom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXButton enoyer;
    @FXML
    private Label titre;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField espece;
    @FXML
    private JFXTextField region;
    @FXML
    private JFXTextField etat;
    @FXML
    private JFXTextField num;
    
    private Refuge r;
    @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     @Override
    public void preloadData(Refuge r) {
        this.email.setText(r.getEmail());
    }

    @FXML
    private void Envoyez(ActionEvent event) throws IOException {
         try{
            String host ="smtp.gmail.com" ;
            String user = "motaz.pro@gmail.com";
            String pass = "motaz25671527";
            String to = "";
            String from = "motaz.pro@gmail.com";
            String subject = "Animal trouvé !";
            String messageText = "";
            boolean sessionDebug = false;
            
             messageText = "Espece de l'animal"+espece.getText()+" Region "+region.getText()+" Est il blessé?: "+etat.getText()+" l'adresse : "+adresse.getText()+" Plus d'information: "+description.getText();
             to = email.getText();
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
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Service.fxml"));
        Parent root = loader.load();
        adresse.getScene().setRoot(root);
        }catch(MessagingException ex)
        {
            System.out.println(ex);
        }
         
    }

    @Override
    public void preloadData(Animal a) {
        
    }

   
    }
    

