/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Ligne;
import Entity.Produit;
import Entity.Session;
import Services.ProduitService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
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
 * @author bn-sk
 */
public class PanierController implements Initializable {

    static String lib = "";
    static String desc = "";
    public String pathImage = "C:\\Users\\bn-sk\\Desktop\\Git\\SprintJava\\src\\Ressources\\Images\\";
    public String pathButton = "C:\\Users\\bn-sk\\Desktop\\Git\\SprintJava\\src\\Ressources\\Images\\minus.png";
    @FXML
    private AnchorPane anp;
    @FXML
    private ScrollPane srolle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProduitService ps = new ProduitService();
        try {
            AffichagePanier();
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AffichagePanier() throws SQLException {

        FlowPane f = new FlowPane();
        ProduitService ps = new ProduitService();
        List<Ligne> lignes = ps.AfficherPanier();

        for (Ligne ligne : lignes) {

            ArrayList<String> detail = new ArrayList<String>();
            ResultSet details = ps.selectProduitsdistinct(ligne.getId_produit());
            while (details.next()) {

                String libelle = details.getString(3);
                String description = details.getString(4);
                detail.add(libelle);
                detail.add(description);

            }

            AnchorPane anchorpane1 = new AnchorPane();
            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();

            File fileimage = new File(pathImage + ligne.getImage());
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(240);
            image.setLayoutY(37);
            image.setFitWidth(150);
            image.setFitHeight(180);

            Label libelle = new Label(detail.get(0));
            Font font = new Font("Arial", 24);
            libelle.setStyle("-fx-font-weight: bold");
            libelle.setFont(font);
            libelle.setTextFill(Color.web("cfbfa6"));
            libelle.setLayoutX(410);
            libelle.setLayoutY(48);

            Label description = new Label("Ceci est un produit " + detail.get(1));
            Font font2 = new Font("Arial", 18);
            description.setFont(font2);
            description.setLayoutX(410);
            description.setLayoutY(90);

            Label prix = new Label(String.valueOf(ligne.getPrix()) + " DT");
            prix.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 16);
            prix.setStyle("-fx-font-weight: bold");
            prix.setFont(font3);
            prix.setLayoutX(410);
            prix.setLayoutY(190);

            File file = new File(pathButton);
            Image preimagebutton = new Image(file.toURI().toString());
            ImageView imagebutton = new ImageView(preimagebutton);

            Button suppressonButton = new Button();
            suppressonButton.setLayoutX(480);
            suppressonButton.setLayoutY(170);
            suppressonButton.setPrefWidth(30);
            suppressonButton.setPrefHeight(20);
            suppressonButton.setGraphic(imagebutton);

            vbox.setSpacing(30.0);
            separtor.setLayoutX(3.0);
            separtor.setLayoutY(55.0);
            separtor.setPrefHeight(4.0);
            separtor.setPrefWidth(1213.0);

            anchorpane1.getChildren().addAll(image, libelle, description, prix, suppressonButton);
            vbox.getChildren().add(anchorpane1);
            f.getChildren().addAll(vbox);

            suppressonButton.setOnAction(e -> {

                try {
                    
                    try {
                        ps.SupprimerProduitPanier(ligne.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Panier.fxml"));
                    Parent root = loader.load();
                    suppressonButton.getScene().setRoot(root);
                    
                } catch (IOException ex) {
                    Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        }
        Button valider=new Button("Commande le panier");
        valider.setContentDisplay(ContentDisplay.RIGHT);
        valider.setStyle("-fx-background-color: f67777");
        
        valider.setOnAction((c) -> {
            try {
                System.out.println(Session.getCurrentSession());
                ps.CommanderPanier(Session.getCurrentSession());
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        f.getChildren().add(valider);
        srolle.setContent(f);

    }

}
