/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Session;
import Entity.reservation_veterinaire;
import Services.UserService;
import Services.reservation_veterinaire_Service;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mimouna
 */
public class Reservation_userController implements Initializable {

    private ObservableList data = FXCollections.observableArrayList();
    @FXML
    private Hyperlink deconnexion;
    @FXML
    private Hyperlink profil;
    @FXML
    private Button panier;
    @FXML
    private Button location;
    @FXML
    private Button Backk;
    @FXML
    private TableView<Label> liste_rdv_user;
    @FXML
    private TableColumn<reservation_veterinaire, Date> date_debut;
    @FXML
    private TableColumn<reservation_veterinaire, Date> date_fin;
    @FXML
    private TableColumn<reservation_veterinaire, String> description;
    int id_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
          id_user=  Session.getCurrentSession();
        try {
            
            reservation_veterinaire_Service RVS = new reservation_veterinaire_Service();
            data = RVS.getObservableReservationUser(id_user);
            liste_rdv_user.setItems(data);
           
        } catch (SQLException ex) {
            Logger.getLogger(Reservation_userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backkk(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("/GUI/FRONT_HygienePage.fxml"));
        Scene homePage_scene = new Scene(homePage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePage_scene);
        app_stage.show();
    }

}
