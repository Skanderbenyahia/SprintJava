/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Session;
import Entity.User;
import Services.UserService;
import Technique.DataSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private Button deconnexion;
    @FXML
    private Label bienvenue;
    
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
    private void afficherAcceuil(ActionEvent event) {     
    }

    @FXML
    private void afficherAdoption(ActionEvent event) {
    }

    @FXML
    private void afficherVentes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Ventes.fxml"));
        Parent root=loader.load();
        bienvenue.getScene().setRoot(root);
        
    }

    @FXML
    private void afficherServices(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Service.fxml"));
        Parent root=loader.load();
        bienvenue.getScene().setRoot(root);
        
    }

    @FXML
    private void afficherSoins(ActionEvent event) {
    }

    @FXML
    private void afficherEvents(ActionEvent event) {
    }


    @FXML
    private void deconnexion(ActionEvent event) throws IOException {
        UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root=loader.load();
        bienvenue.getScene().setRoot(root);
    }
    
    @FXML
    private void affichePanier(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Panier.fxml"));
        Parent root=loader.load();
        bienvenue.getScene().setRoot(root);
    }
    
}
