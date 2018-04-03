/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.CentreDressage;
import Entity.Produit;
import Services.CategorieService;
import Services.ProduitService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class Back_AjouterProduitPageController implements Initializable,VenteControllerClass {

    @FXML
    private JFXComboBox<Integer> categorie;
    @FXML
    private JFXTextField libelle;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField animal;
    @FXML
    private JFXTextField quantite;
    @FXML
    private JFXTextField image;
    @FXML
    private Button Back_button;
    @FXML
    private Label titre;
    Produit p;
    private Statement ste;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                       CategorieService cs=new CategorieService();

        try {
            categorie.setItems(cs.IDCategorie());
        } catch (SQLException ex) {
            Logger.getLogger(Back_AjouterProduitPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    @FXML
    private void AjouterP(ActionEvent event) throws IOException, SQLException
    {
        ProduitService ps=new ProduitService();
        if(ValidateFields())
        {
        if(p!=null)
            {
                updateProduit();
                ps.ModifierProduit(p);
            }
        else
            {
                Produit p = new Produit();
                p.setCategorie(categorie.getValue());
                p.setLibelle(libelle.getText());
                p.setDescription(description.getText());
                p.setPrix(Integer.parseInt(prix.getText()));
                p.setAnimal(animal.getText());
                p.setQuantite(Integer.parseInt(quantite.getText()));
                p.setImage(image.getText());
                ps.AjouterProduit(p);
            }

        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_VentePage.fxml"));
        Parent root =loader.load();
        animal.getScene().setRoot(root);   
    } 
    }

    @Override
    public void preloadData(Produit p) 
    {
        this.p=p;
        this.categorie.setValue(p.getCategorie());
        this.libelle.setText(p.getLibelle());
        this.description.setText(p.getDescription());
        this.animal.setText(p.getAnimal());
        this.prix.setText(Integer.toString(p.getPrix()));
        this.quantite.setText(Integer.toString(p.getQuantite()));
        this.image.setText(p.getImage());
        this.titre.setText("Modifier Un Produit");
    }

     public void updateProduit()
    {
        p.setCategorie(categorie.getValue());
        p.setLibelle(libelle.getText());
        p.setDescription(description.getText());
        p.setPrix(Integer.parseInt(prix.getText()));
        p.setAnimal(animal.getText());
        p.setQuantite(Integer.parseInt(quantite.getText()));
        p.setImage(image.getText());
    }
     
     
     
       private boolean ValidateFields()
     {
         if(libelle.getText().isEmpty() | description.getText().isEmpty() | animal.getText().isEmpty() 
                 | prix.getText().isEmpty() |quantite.getText().isEmpty()|image.getText().isEmpty())
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tout les champs !");
                alert.showAndWait();
         return false;
         }
         
          if((Integer.parseInt(prix.getText()) | Integer.parseInt(quantite.getText()))<0)
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez Vérifier le prix ou la quantite !");
                alert.showAndWait();
         return false;
         }
        return true;
     
     }
    @Override
    public void preloadData(Categorie c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    }
   
