/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.CentreDressage;
import Entity.ReservationPetsitter;
import Services.CentreDressageService;
import Services.ReservationPetsitterService;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class Back_ServicePageController implements Initializable {

    @FXML
    private Button BackButton;
    @FXML
    private JFXTabPane ServiceTabPane;
    @FXML
    private Tab CentreDressagePane;
    @FXML
    private Tab PetsitterPan;
    @FXML
    private TableView<CentreDressage> CentreDressageView;
    @FXML
    private TableColumn<CentreDressage, String> Column_libelle;
    @FXML
    private TableColumn<CentreDressage, String> Column_adresse;
    @FXML
    private TableColumn<CentreDressage, Integer> Column_tel;
    @FXML
    private TableColumn<CentreDressage, String> Column_description;
    @FXML
    private TableColumn<CentreDressage, Double> Column_lan;
    @FXML
    private TableColumn<CentreDressage, Double> Column_lat;
    @FXML
    private TableColumn<CentreDressage, String> Column_image;
    @FXML
    private Button ajouterD;
    @FXML
    private Button modifierD;
    @FXML
    private Button supprimerD;
    @FXML
    private TableView<ReservationPetsitter> ReservationPetsitterView;
    @FXML
    private TableColumn<ReservationPetsitter, Date> Column_DateD;
    @FXML
    private TableColumn<ReservationPetsitter, Date> Column_DateF;
    @FXML
    private TableColumn<ReservationPetsitter, Float> Column_Prix;
    @FXML
    private TableColumn<ReservationPetsitter, Float> Column_Encaisser;
    @FXML
    private TableColumn<ReservationPetsitter, Integer> Column_Petsitter;
    @FXML
    private TableColumn<ReservationPetsitter, Integer> Column_Utilisateur;
   
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                                        /* CENTRE DRESSAGE */
                            //********************************************************//
                            
       modifierD.setDisable(true);
       supprimerD.setDisable(true);
        
        Column_libelle.setCellValueFactory(new PropertyValueFactory<> ("libelle"));
        Column_adresse.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
        Column_tel.setCellValueFactory(new PropertyValueFactory<> ("tel"));
        Column_lat.setCellValueFactory(new PropertyValueFactory<> ("lat"));
        Column_lan.setCellValueFactory(new PropertyValueFactory<> ("lng"));
        Column_description.setCellValueFactory(new PropertyValueFactory<> ("description"));
        Column_image.setCellValueFactory(new PropertyValueFactory<> ("image"));
        
        try {
            CentreDressageService cd = new CentreDressageService();
            CentreDressageView.setItems(cd.getObservableCentreDressage());
        } catch (SQLException ex) {
            Logger.getLogger(Back_ServicePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                                                /* ReservationPetsitter*/
                            //********************************************************//
                            
        Column_DateD.setCellValueFactory(new PropertyValueFactory<>("dateD"));
        Column_DateF.setCellValueFactory(new PropertyValueFactory<>("dateF"));
        Column_Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        Column_Encaisser.setCellValueFactory(new PropertyValueFactory<>("encaisser"));
        Column_Petsitter.setCellValueFactory(new PropertyValueFactory<>("idPetsitter"));
        Column_Utilisateur.setCellValueFactory(new PropertyValueFactory<>("idUser"));

            try {
                ReservationPetsitterService rs = new ReservationPetsitterService();
                ReservationPetsitterView.setItems(rs.getObservableReservationPetsitter());
        } catch (SQLException ex) {
            Logger.getLogger(Back_ServicePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }    

                                              /* CENTRE DRESSAGE */
                            //********************************************************//
    @FXML
    private void Back_adminLayout(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/adminLayout.fxml"));
        Parent root =loader.load();
        CentreDressageView.getScene().setRoot(root);
    }

    @FXML
    private void selected(MouseEvent event) {
        modifierD.setDisable(false);
       supprimerD.setDisable(false);
    }

    @FXML
    private void supprimerCentreDressage(ActionEvent event) throws SQLException, IOException {
        Alert alert= new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> ok =alert.showAndWait();
        if(ok.get()==ButtonType.OK)
        {
           CentreDressage d=this.CentreDressageView.getSelectionModel().getSelectedItem();
           CentreDressageService cd= new CentreDressageService();
           cd.SupprimerCentreDressage(d.getId());
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_ServicePage.fxml"));
           Parent root =loader.load();
           CentreDressageView.getScene().setRoot(root);
        }
    }

    @FXML
    private void AjouterCentreDressage(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AjouterCDPage.fxml"));
           Parent root =loader.load();
           CentreDressageView.getScene().setRoot(root);
        
    }

    public void changeScene(ActionEvent event, String viewName,String title,CentreDressage d,ControllerClass controllerC) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent=loader.load();
        
        controllerC=loader.getController();
        controllerC.preloadData(d);
        
         Scene CentreDPage= new Scene (parent);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setTitle(title);
         window.setScene(CentreDPage);
         window.show();
   }
    
    
    @FXML
    private void ModifierCentreDressage(ActionEvent event) throws IOException {
        CentreDressage d=this.CentreDressageView.getSelectionModel().getSelectedItem();
        Back_AjouterCDPageController controllerC=new Back_AjouterCDPageController();
        changeScene(event, "../GUI/Back_AjouterCDPage.fxml", "Modifier le Centre", d, controllerC);
    }
    
    
    
                                            /* ReservationPetsitter*/
                            //********************************************************//

    @FXML
    private void ApportPetSitter(ActionEvent event) throws SQLException 
    {
        ReservationPetsitterService rp=new ReservationPetsitterService();
        int apport=rp.apportPetsitter();
        Alert alert= new Alert(AlertType.INFORMATION);
        alert.setTitle("Apport");
        alert.setHeaderText(null);
        alert.setContentText("Les Petsitters ont apporté : "+ apport +" DT");
        alert.showAndWait();
    }
   
}
