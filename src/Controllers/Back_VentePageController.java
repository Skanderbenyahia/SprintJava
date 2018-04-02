/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.CentreDressage;
import Services.CategorieService;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class Back_VentePageController implements Initializable {

    @FXML
    private TableView<Categorie> CategorieView;
    @FXML
    private TableColumn<Categorie, String> Column_libelle;
    @FXML
    private Button AjouterC;
    @FXML
    private Button ModifierC;
    @FXML
    private Button SupprimerC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ModifierC.setDisable(true);
        SupprimerC.setDisable(true);
        Column_libelle.setCellValueFactory(new PropertyValueFactory<> ("libelle"));
        
        try {
                 CategorieService cs=new CategorieService();
                 CategorieView.setItems(cs.getObservableCategorie());
        } 
        catch (SQLException ex) {
            Logger.getLogger(Back_VentePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void Back_AdminLayout(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/adminLayout.fxml"));
        Parent root =loader.load();
        CategorieView.getScene().setRoot(root);
    }

    @FXML
    private void selected(MouseEvent event) {
        SupprimerC.setDisable(false);
        ModifierC.setDisable(false);
    }

    @FXML
    private void AjouterCategorie(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AjouterCategoriePage.fxml"));
           Parent root =loader.load();
           CategorieView.getScene().setRoot(root);
    }

    
    public void changeScene(ActionEvent event, String viewName,String title,Categorie c,ControllerClass controllerC) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent=loader.load();
        
        controllerC=loader.getController();
        controllerC.preloadData(c);
        
         Scene CentreDPage= new Scene (parent);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setTitle(title);
         window.setScene(CentreDPage);
         window.show();
   }
    @FXML
    private void ModifierCategorie(ActionEvent event) throws IOException
    {
         Categorie c=this.CategorieView.getSelectionModel().getSelectedItem();
        Back_AjouterCDPageController controllerC=new Back_AjouterCDPageController();
        changeScene(event, "../GUI/Back_AjouterCategoriePage.fxml", "Modifier la catégorie", c, controllerC);
    }
    
    
    
}
