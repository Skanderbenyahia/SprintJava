/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.ProduitService;
import Technique.DataSource;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author bn-sk
 */
public class VentesController implements Initializable {
private Connection con= DataSource.getInstance().getConnexion();
    @FXML
    private Hyperlink deconnexion;
    @FXML
    private Hyperlink profil;
    @FXML
    private Label bienvenue;
    @FXML
    private Button panier;
    @FXML
    private Button location;
    
    public String pathImage="C:\\Users\\bn-sk\\Documents\\NetBeansProjects\\Java\\SprintJava\\src\\Ressources\\Images\\";
    public String pathButton="C:\\Users\\bn-sk\\Documents\\NetBeansProjects\\Java\\SprintJava\\src\\Ressources\\Images\\add-square-button.png";
    @FXML
    private Pane p1;
    @FXML
    private HBox h1;
    /**
     * Initializes the controller class.
     */
    enum animal{chien};
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        AffichageProduit();
    } catch (SQLException ex) {
        Logger.getLogger(VentesController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    

    public void AffichageProduit() throws SQLException
    {
        
        ProduitService ps=new ProduitService();
        ResultSet rs = ps.selectProduits();
        while (rs.next()) 
        {
            /*image*/
            File file = new File(pathImage+rs.getString(7));
            Image image = new Image(file.toURI().toString());
            ImageView imageProduit = new ImageView(image);
            imageProduit.setLayoutX(38);
            imageProduit.setLayoutY(26);
            imageProduit.setFitWidth(172);
            imageProduit.setFitHeight(167);
            /*libelle*/
            Label libelle=new Label(rs.getString(3));
            libelle.setLayoutX(273);
            libelle.setLayoutY(54);
            libelle.setPrefWidth(110);
            libelle.setPrefHeight(21);
            libelle.setTextFill(Color.web("f67777"));
            /*description*/
            Label description=new Label(rs.getString(4));
            description.setLayoutX(393);
            description.setLayoutY(95);
            description.setPrefWidth(345);
            description.setPrefHeight(21);
            description.setTextFill(Color.web("f67777"));
            /*prix*/
            Label prix=new Label(rs.getInt(5)+"dt");
            prix.setLayoutX(932);
            prix.setLayoutY(99);
            prix.setPrefWidth(44);
            prix.setPrefHeight(21);
            prix.setTextFill(Color.web("f67777"));
            /*button ajouter panier*/
            Button ajouterPanier=new Button();
            File file2 = new File(pathButton);
            Image image2 = new Image(file2.toURI().toString());
            ImageView imageButton = new ImageView(image2);
            imageButton.setLayoutX(1056);
            imageButton.setLayoutY(94);
            ajouterPanier.setLayoutX(1056);
            ajouterPanier.setLayoutY(94);
            ajouterPanier.setOpacity(0);
                
            
            
            
            p1.getChildren().add(imageProduit);
            p1.getChildren().add(libelle);
            p1.getChildren().add(description);
            p1.getChildren().add(ajouterPanier);
            p1.getChildren().add(prix);
            p1.getChildren().add(imageButton);
            
            
           
  
        }
            
    }
}
