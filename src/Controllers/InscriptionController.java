/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bn-sk
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXButton inscrire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       type.getItems().add("Membre");
       type.getItems().add("PetSitter");
       type.getItems().add("Veterinaire");   
    }    

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        UserService us = new UserService();
        User p = new User();
        p.setNom(nom.getText());
        p.setPrenom(prenom.getText());
        p.setAdresse(adresse.getText());
        p.setEmail(email.getText());
        p.setPassword(password.getText());
        p.setUsername(username.getText());
        p.setTel(Integer.parseInt(tel.getText()));
        if(type.getValue()=="Membre")
        {
            p.setRoles("ROLE_CLIENT");
        }
        if(type.getValue()=="PetSitter")
        {
            p.setRoles("ROLE_PETSITTER");
        }
        if(type.getValue()=="Veterinaire")
        {
            p.setRoles("ROLE_VETERINAIRE");
        }
        
        
        us.signIn(p);
        Stage stage;

        Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/start.fxml"));
        Scene scene = new Scene(page2);
        stage = (Stage) inscrire.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
}
