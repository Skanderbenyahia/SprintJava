/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Animal;
import Entity.Session;
import Entity.WishList;
import Services.AnimalService;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Skeez
 */
public class WishlistController implements Initializable {

    public String pathImage = "C:\\Users\\Skeez\\Desktop\\GitRogue\\SprintJava\\src\\Ressources\\Images\\";

    @FXML
    private ScrollPane scroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }
    
    public void afficher()
    {
        
        try {
            FlowPane f = new FlowPane();
            
            AnimalService as = new AnimalService();
            List<WishList> l = as.AfficherWishList(Session.getCurrentSession());
            for (WishList w : l) {
                System.out.println(w.getId_animal());
                List<Animal> a = as.AfficherAnimalDistinct(w.getId_animal());
                
                for (Animal r : a) {
                    
                        AnchorPane anchorpane1 = new AnchorPane();

                        anchorpane1.setPrefHeight(250.0);
                        anchorpane1.setPrefWidth(504.0);
                        Separator separtor = new Separator();
                        VBox vbox = new VBox();
                        System.out.println(r.getNom());

                        File fileimage = new File(pathImage + r.getImage());
                        Image preimage = new Image(fileimage.toURI().toString());
                        ImageView image = new ImageView(preimage);
                        image.setLayoutX(120);
                        image.setLayoutY(37);
                        image.setFitWidth(150);
                        image.setFitHeight(180);

                        Label libelle = new Label(r.getNom());
                        Font font = new Font("Arial", 32);
                        libelle.setStyle("-fx-font-weight: bold");
                        libelle.setFont(font);
                        libelle.setTextFill(Color.web("cfbfa6"));
                        libelle.setLayoutX(290);
                        libelle.setLayoutY(48);

                        Label description = new Label("cet animal est :" + r.getDescription());
                        Font font2 = new Font("Arial", 18);
                        description.setFont(font2);
                        description.setLayoutX(290);
                        description.setLayoutY(90);

                        Label demande = new Label(String.valueOf(r.getDemande()) + " Demandes");
                        demande.setTextFill(Color.web("f67777"));
                        Font font3 = new Font("Arial", 16);
                        demande.setStyle("-fx-font-weight: bold");
                        demande.setFont(font3);
                        demande.setLayoutX(290);
                        demande.setLayoutY(190);

                        Button ajoutButton = new Button();
                        ajoutButton.setPrefHeight(40);
                        ajoutButton.setPrefWidth(150);
                        ajoutButton.setLayoutX(650);
                        Label re = new Label();
                        re.setText("Supprimer");
                        re.setTextFill(Color.web("ffffff"));
                        ajoutButton.setLayoutY(150);
                        ajoutButton.setGraphic(re);

                        vbox.setSpacing(30.0);
                        separtor.setLayoutX(3.0);
                        separtor.setLayoutY(55.0);
                        separtor.setPrefHeight(4.0);
                        separtor.setPrefWidth(1213.0);

                        anchorpane1.getChildren().addAll(image, libelle, description, demande, ajoutButton);
                        vbox.getChildren().add(anchorpane1);
                        f.getChildren().addAll(vbox);

                    }
                }
                scroll.setContent(f);
            } catch (SQLException ex) {
            Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
