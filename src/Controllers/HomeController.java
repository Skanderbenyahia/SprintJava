/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Session;
import Entity.User;
import Technique.DataSource;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author bn-sk
 */
public class HomeController implements Initializable {
private Connection con= DataSource.getInstance().getConnexion();
    private Statement statement;
    @FXML
    private Button panier;
    @FXML
    private Button location;
    @FXML
    private Hyperlink deconnexion;
    @FXML
    private Hyperlink profil;
    @FXML
    private Label bienvenue;
    @FXML
    private AnchorPane acceuil;
    @FXML
    private AnchorPane adoption;
    @FXML
    private AnchorPane ventes;
    @FXML
    private AnchorPane typevente;
    @FXML
    private JFXToggleButton toggle;
    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane services;
    @FXML
    private AnchorPane soins;
    @FXML
    private AnchorPane events;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List currentUser = new ArrayList();
        String req="select nom,prenom from user where id=(?)";
        try {
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setInt(1,Session.getCurrentSession());
            ResultSet resultat=prepared.executeQuery();
        
            while (resultat.next()) {
                String nom=resultat.getString(1);
                String prenom=resultat.getString(2);
               currentUser.add(nom);
               currentUser.add(prenom);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        bienvenue.setText("Connecté en tant que : "+currentUser.get(0)+" "+currentUser.get(1));
    }    

    @FXML
    private void afficherAccessoires(MouseEvent event) {
    }
    
}
