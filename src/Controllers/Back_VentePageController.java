/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.CentreDressage;
import Entity.Produit;
import Entity.ReservationPetsitter;
import Services.CategorieService;
import Services.CentreDressageService;
import Services.ProduitService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    @FXML
    private TableView<Produit> ProduitView;
    @FXML
    private TableColumn<Produit, Integer> Column_Categorie;
    @FXML
    private TableColumn<Produit, String> Column_libelleP;
    @FXML
    private TableColumn<Produit, String> Column_description;
    @FXML
    private TableColumn<Produit, Integer> Column_prix;
    @FXML
    private TableColumn<Produit, String> Column_animal;
    @FXML
    private TableColumn<Produit, String> Column_image;
    @FXML
    private TableColumn<Produit, Integer> Column_quantite;
    @FXML
    private Button AjouterP;
    @FXML
    private Button ModifierP;
    @FXML
    private Button SupprimerP;
    @FXML
    private JFXTextField search;
private ObservableList data= FXCollections.observableArrayList();
private ObservableList dataR= FXCollections.observableArrayList();
    @FXML
    private JFXTextField searchR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                                        /*Categorie*/
                //**********************************************************************************//
        ModifierC.setDisable(true);
        SupprimerC.setDisable(true);
        Column_libelle.setCellValueFactory(new PropertyValueFactory<> ("libelle"));
        
        try {
                 CategorieService cs=new CategorieService();
                 data=cs.getObservableCategorie();
                 CategorieView.setItems(data);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Back_VentePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FilteredList<Categorie> filteredList= new FilteredList<>(data, e -> true);
            search.setOnKeyReleased(e-> {
                search.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredList.setPredicate((Predicate<? super Categorie>) categorie-> {
                        if((newValue==null)|(newValue.isEmpty()))
                    {
                    return true;
                    }
                        if(categorie.getLibelle().contains(newValue))
                            {
                             return true; 
                            }
                       
                      
                        return false;
                    });
                });
                SortedList<Categorie> sortedDate= new SortedList<>(filteredList);
                sortedDate.comparatorProperty().bind(CategorieView.comparatorProperty());
                CategorieView.setItems(sortedDate);
            });
        
                                        /*Produit*/
                //**********************************************************************************//
           ModifierP.setDisable(true);
           SupprimerP.setDisable(true);
           Column_Categorie.setCellValueFactory(new PropertyValueFactory<>("categorie") );
           Column_libelleP.setCellValueFactory(new PropertyValueFactory<>("libelle"));
           Column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
           Column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
           Column_animal.setCellValueFactory(new PropertyValueFactory<>("animal"));
           Column_image.setCellValueFactory(new PropertyValueFactory<>("Image"));
           Column_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
           
        try {
            ProduitService ps=new ProduitService();
            dataR=ps.getObservableProduit();
           ProduitView.setItems(dataR);

        } catch (SQLException ex) {
            Logger.getLogger(Back_VentePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FilteredList<Produit> filteredListR= new FilteredList<>(dataR, e -> true);
            searchR.setOnKeyReleased(e-> {
                searchR.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredListR.setPredicate((Predicate<? super Produit>) produit-> {
                        if((newValue==null)|(newValue.isEmpty()))
                    {
                    return true;
                    }
                        if(produit.getLibelle().contains(newValue))
                            {
                             return true; 
                            }
                        else if(produit.getDescription().contains(newValue))
                            {
                             return true; 
                            }
                         else if(produit.getAnimal().contains(newValue))
                            {
                             return true; 
                            }
  
                        return false;
                    });
                });
                SortedList<Produit> sortedDateR= new SortedList<>(filteredListR);
                sortedDateR.comparatorProperty().bind(ProduitView.comparatorProperty());
                ProduitView.setItems(sortedDateR);
            });
        
        
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

   public void changeSceneC(ActionEvent event, String viewName,String title,Categorie c,VenteControllerClass controllerC) throws IOException
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
    private void ModifierCategorie(ActionEvent event) throws IOException {
        Categorie c=this.CategorieView.getSelectionModel().getSelectedItem();
        Back_AjouterCategoriePageController controllerC=new Back_AjouterCategoriePageController();
        changeSceneC(event, "../GUI/Back_AjouterCategoriePage.fxml", "Modifier le produit", c, controllerC);
    }

    @FXML
    private void SupprimerCategorie(ActionEvent event) throws SQLException, IOException {
          Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> ok =alert.showAndWait();
        if(ok.get()==ButtonType.OK)
        {
           Categorie c=this.CategorieView.getSelectionModel().getSelectedItem();
           CategorieService cs=new CategorieService();
           cs.SupprimerCategorie(c.getId());
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_VentePage.fxml"));
           Parent root =loader.load();
           CategorieView.getScene().setRoot(root);
        }
    }
    
                                   /*Produit*/
         //***************************************************************************************//
    @FXML
    private void AjouterProduit(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AjouterProduitPage.fxml"));
         Parent root =loader.load();
         ProduitView.getScene().setRoot(root);
    }
    
  public void changeScene(ActionEvent event, String viewName,String title,Produit p,VenteControllerClass controllerC) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent=loader.load();
        
        controllerC=loader.getController();
        controllerC.preloadData(p);
        
         Scene CentreDPage= new Scene (parent);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setTitle(title);
         window.setScene(CentreDPage);
         window.show();
   }
    @FXML
    private void ModifierProduit(ActionEvent event) throws IOException {
        Produit p=this.ProduitView.getSelectionModel().getSelectedItem();
        Back_AjouterProduitPageController controllerC=new Back_AjouterProduitPageController();
        changeScene(event, "../GUI/Back_AjouterProduitPage.fxml", "Modifier le produit", p, controllerC);
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) throws IOException, SQLException {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> ok =alert.showAndWait();
        if(ok.get()==ButtonType.OK)
        {
           Produit p=this.ProduitView.getSelectionModel().getSelectedItem();
            ProduitService ps=new ProduitService();
           ps.SupprimerProduit(p.getId());
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_VentePage.fxml"));
           Parent root =loader.load();
           ProduitView.getScene().setRoot(root);
        }
    }
    

    @FXML
    private void SelectedP(MouseEvent event) {
         ModifierP.setDisable(false);
         SupprimerP.setDisable(false);
    }

  
    
    
}
