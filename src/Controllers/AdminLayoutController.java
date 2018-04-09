/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Session;
import Entity.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class AdminLayoutController implements Initializable {

    @FXML
    private Button Adoption_button;
    @FXML
    private Button Ventes_button;
    @FXML
    private Button Services_button;
    @FXML
    private Button hygiene_button;
    @FXML
    private Button event_button;
    @FXML
    private TableView<User> UserView;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, Integer> tel;
    @FXML
    private TableColumn<User, String> role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     nom.setCellValueFactory(new PropertyValueFactory<>("nom") );
     prenom.setCellValueFactory(new PropertyValueFactory<>("prenom") );
     adresse.setCellValueFactory(new PropertyValueFactory<>("adresse") );
     email.setCellValueFactory(new PropertyValueFactory<>("email") );
     tel.setCellValueFactory(new PropertyValueFactory<>("tel") );
     role.setCellValueFactory(new PropertyValueFactory<>("roles") );

        UserService us=new UserService();
        try {
            UserView.setItems(us.getObservableUser());
        } catch (SQLException ex) {
            Logger.getLogger(AdminLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ServicePage(ActionEvent event) throws IOException {
         Parent service= FXMLLoader.load((getClass().getResource("../GUI/Back_ServicePage.fxml")));
         Scene CentreDPage= new Scene (service);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setScene(CentreDPage);
         window.show();
    }

    @FXML
    private void VentePage(ActionEvent event) throws IOException {
         Parent vente= FXMLLoader.load((getClass().getResource("../GUI/Back_VentePage.fxml")));
         Scene ventePage= new Scene (vente);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setScene(ventePage);
         window.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root=loader.load();
        UserView.getScene().setRoot(root);
    }
    
     

    @FXML
    private void adoptionPage(ActionEvent event) throws IOException {
        Parent service= FXMLLoader.load((getClass().getResource("../GUI/Back_AdoptionPage.fxml")));
         Scene CentreDPage= new Scene (service);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setScene(CentreDPage);
         window.show();
    }
    
}
