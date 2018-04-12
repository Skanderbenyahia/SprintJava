/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Concour;
import Entity.Conseils;
import Services.ServiceConcour;
import Services.ServiceConseils;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class Back_eventPageController implements Initializable {

    @FXML
    private TableView<Concour> concourTable;
    @FXML
    private TableColumn<Concour, String> Column_description;
    @FXML
    private TableColumn<Concour, Integer> Column_nombreP;
    @FXML
    private TableColumn<Concour, Date> dateConcour;
    @FXML
    private Button ajoutConcour;
    @FXML
    private Button modifierConcour;
    @FXML
    private Button supprimerConcour;
    private ObservableList data= FXCollections.observableArrayList();
    
    @FXML
    private TableView<Conseils> conseilTable;
    @FXML
    private TableColumn<Conseils, String> Column_titre;
    @FXML
    private TableColumn<Conseils, String> Column_text;
    @FXML
    private TableColumn<Conseils, String> Column_type;
    @FXML
    private Button ajoutConseil;
    @FXML
    private Button modifierConseil;
    @FXML
    private Button SupprimerConseilButton;
    private ObservableList data2= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            modifierConcour.setDisable(true);
            supprimerConcour.setDisable(true);
            
            Column_description.setCellValueFactory(new PropertyValueFactory<> ("description"));
            Column_nombreP.setCellValueFactory(new PropertyValueFactory<> ("nbredeplaces"));
            dateConcour.setCellValueFactory(new PropertyValueFactory<> ("date"));
            
            ServiceConcour cd = new ServiceConcour();
            data=cd.getObservableConcour();
            concourTable.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(Back_eventPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ////////////////////////////////////Conseil///////////////////////////////////
        modifierConseil.setDisable(true);
        SupprimerConseilButton.setDisable(true);
        
        Column_titre.setCellValueFactory(new PropertyValueFactory<> ("titre"));
        Column_text.setCellValueFactory(new PropertyValueFactory<> ("text"));
        Column_type.setCellValueFactory(new PropertyValueFactory<> ("type"));
        
        try {
            ServiceConseils c= new ServiceConseils();
            data2=c.getObservableConseils();
            conseilTable.setItems(data2);
        } catch (SQLException ex) {
            Logger.getLogger(Back_eventPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void selected(MouseEvent event) {
        modifierConcour.setDisable(false);
        supprimerConcour.setDisable(false);
    }

    @FXML
    private void ajouterConcourPage(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AjoutConcour.fxml"));
         Parent root =loader.load();
         concourTable.getScene().setRoot(root);
    }

    public void changeScene(ActionEvent event, String viewName,String title,Concour c,event_ControllorClass controllerCon) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent=loader.load();
        
        controllerCon=loader.getController();
        controllerCon.preloadData(c);
        
        Scene CentreDPage= new Scene (parent);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setTitle(title);
        window.setScene(CentreDPage);
        window.show();
   }
    @FXML
    private void ModifierConcour(ActionEvent event) throws IOException {
        Concour c=this.concourTable.getSelectionModel().getSelectedItem();
        Back_AjouterCDPageController controllerC=new Back_AjouterCDPageController();
        event_ControllorClass controllerCon = null;
        changeScene(event, "../GUI/Back_AjoutConcour.fxml", "Modifier le Concour", c,controllerCon);
    }

    @FXML
    private void SUpprimerConcour(ActionEvent event) throws IOException, SQLException {
         Alert alert= new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> ok =alert.showAndWait();
        if(ok.get()==ButtonType.OK)
        {
           Concour d=this.concourTable.getSelectionModel().getSelectedItem();
            ServiceConcour cd= new ServiceConcour();
           cd.supprimerConcour(d.getId());
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_eventPage.fxml"));
           Parent root =loader.load();
           concourTable.getScene().setRoot(root);
        }
    }

    @FXML
    private void AjouterConseilPage(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AjoutConseil.fxml"));
         Parent root =loader.load();
         conseilTable.getScene().setRoot(root);
    }
    
    
    public void changeScene2(ActionEvent event, String viewName,String title,Conseils c,event_ControllorClass controllerCon) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent=loader.load();
        
        controllerCon=loader.getController();
        controllerCon.preloadData(c);
        
        Scene CentreDPage= new Scene (parent);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setTitle(title);
        window.setScene(CentreDPage);
        window.show();
   }

    @FXML
    private void ModifierConseil(ActionEvent event) throws IOException {
         Conseils c=this.conseilTable.getSelectionModel().getSelectedItem();
        Back_AjouterCDPageController controllerC=new Back_AjouterCDPageController();
        event_ControllorClass controllerCon = null;
        changeScene2(event, "../GUI/Back_AjoutConseil.fxml", "Modifier le Conseil", c,controllerCon);
    }

    @FXML
    private void SupprimerConseil(ActionEvent event) throws SQLException, IOException {
         Alert alert= new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> ok =alert.showAndWait();
        if(ok.get()==ButtonType.OK)
        {
           Conseils d=this.conseilTable.getSelectionModel().getSelectedItem();
            ServiceConseils cd= new ServiceConseils();
           cd.supprimerConseils(d.getId());
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_eventPage.fxml"));
           Parent root =loader.load();
           concourTable.getScene().setRoot(root);
        }
    }

    @FXML
    private void selectedConseil(MouseEvent event) {
        modifierConseil.setDisable(false);
        SupprimerConseilButton.setDisable(false);
    }
    
}
