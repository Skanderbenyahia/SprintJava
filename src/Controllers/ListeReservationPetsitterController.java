/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.CentreDressage;
import Entity.ReservationPetsitter;
import Entity.Session;
import Services.CentreDressageService;
import Services.ReservationPetsitterService;
import Services.UserService;
import Technique.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class ListeReservationPetsitterController implements Initializable {

    @FXML
    private TableView<ReservationPetsitter> reservation;
    @FXML
    private TableColumn<ReservationPetsitter, Date> Column_dated;
    @FXML
    private TableColumn<ReservationPetsitter, Date> Column_datef;
    @FXML
    private TableColumn<ReservationPetsitter, Float> Column_prix;
    @FXML
    private TableColumn<ReservationPetsitter, Float> Column_apport;
    @FXML
    private TableColumn<ReservationPetsitter, Integer> Column_user;
    private ObservableList data= FXCollections.observableArrayList();

    private int id_user;
    private Connection con= DataSource.getInstance().getConnexion();
    @FXML
    private Button AnnulerReser;
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
        
        Column_dated.setCellValueFactory(new PropertyValueFactory<> ("dateD"));
        Column_datef.setCellValueFactory(new PropertyValueFactory<> ("dateF"));
        Column_prix.setCellValueFactory(new PropertyValueFactory<> ("prix"));
        Column_apport.setCellValueFactory(new PropertyValueFactory<> ("encaisser"));
        Column_user.setCellValueFactory(new PropertyValueFactory<> ("idUser"));
        id_user=Session.getCurrentSession();
        try {
           ReservationPetsitterService rs = new ReservationPetsitterService();
                data=rs.getReservation(id_user);
                reservation.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(Back_ServicePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   catch (SQLException ex) {    
            Logger.getLogger(ListeReservationPetsitterController.class.getName()).log(Level.SEVERE, null, ex);
        }    
     AnnulerReser.setDisable(true);
     bienvenue.setText("Connecté en tant que : " + currentUser.get(0) + " " + currentUser.get(1));
}

    @FXML
    private void selected(MouseEvent event) {
            AnnulerReser.setDisable(false);

    }

    @FXML
    private void AnnulerRes(ActionEvent event) throws IOException, SQLException {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir annuler la réservation ?");
        Optional<ButtonType> ok =alert.showAndWait();
        if(ok.get()==ButtonType.OK)
        {
           ReservationPetsitter r=this.reservation.getSelectionModel().getSelectedItem();
           ReservationPetsitterService cd= new ReservationPetsitterService();
           cd.SupprimerReservationPetsitter(r.getId());
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ListeReservationPetsitter.fxml"));
           Parent root =loader.load();
           reservation.getScene().setRoot(root);
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void panierPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Panier.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void homePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/home.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void adoptionPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Adoption.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void servicePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Service.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void soinPage(ActionEvent event) {
     
    }

    @FXML
    private void eventPage(ActionEvent event) {
    }

    @FXML
    private void ventePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Ventes.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }
}