/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Produit;
import Entity.Session;
import Entity.User;
import Services.ProduitService;
import Services.UserService;
import Technique.DataSource;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bn-sk
 */
public class VentesController implements Initializable {

    private Connection con = DataSource.getInstance().getConnexion();
    @FXML
    private Label bienvenue;
    @FXML
    private Button panier;

    public String pathImage = "C:\\Users\\bn-sk\\Desktop\\Git\\SprintJava\\src\\Ressources\\Images\\";
    public String pathButton = "C:\\Users\\bn-sk\\Desktop\\Git\\SprintJava\\src\\Ressources\\Images\\add-square-button.png";
    @FXML
    private AnchorPane an;
    @FXML
    private ScrollPane scroll;

    @FXML
    private void Afficherpanier(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Panier.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void EventPage(ActionEvent event) {
    }

    @FXML
    private void SoinPage(ActionEvent event) {
    }

    @FXML
    private void ServicePage(ActionEvent event) throws IOException {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Service.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void HomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/home.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void AdoptionPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Adoption.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException {
         UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    /**
     * Initializes the controller class.
     */
    enum animal {
        chien
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List currentUser = new ArrayList();
        String req = "select nom,prenom from user where id=(?)";
        try {
            PreparedStatement prepared = con.prepareStatement(req);
            prepared.setInt(1, Session.getCurrentSession());
            ResultSet resultat = prepared.executeQuery();

            while (resultat.next()) {
                String nom = resultat.getString(1);
                String prenom = resultat.getString(2);
                currentUser.add(nom);
                currentUser.add(prenom);
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        bienvenue.setText("Connecté en tant que : " + currentUser.get(0) + " " + currentUser.get(1));
        try {
            AffichageProduit();
        } catch (SQLException ex) {
            Logger.getLogger(VentesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AffichageProduit() throws SQLException {

        FlowPane f = new FlowPane();
        ProduitService ps = new ProduitService();
        ResultSet rs = ps.selectProduits();

        while (rs.next()) {

            AnchorPane anchorpane1 = new AnchorPane();
            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();

            File fileimage = new File(pathImage + rs.getString(7));
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(120);
            image.setLayoutY(37);
            image.setFitWidth(150);
            image.setFitHeight(180);

            Label libelle = new Label(rs.getString(3));
            Font font = new Font("Arial", 24);
            libelle.setStyle("-fx-font-weight: bold");
            libelle.setFont(font);
            libelle.setTextFill(Color.web("cfbfa6"));
            libelle.setLayoutX(290);
            libelle.setLayoutY(48);

            Label description = new Label("Ce produit est" + rs.getString(4));
            Font font2 = new Font("Arial", 20);
            description.setFont(font2);
            description.setLayoutX(290);
            description.setLayoutY(90);

            Label prix = new Label(String.valueOf(rs.getInt(5)) + " DT");
            prix.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 18);
            prix.setStyle("-fx-font-weight: bold");
            prix.setFont(font3);
            prix.setLayoutX(290);
            prix.setLayoutY(130);

            File file = new File(pathButton);
            Image preimagebutton = new Image(file.toURI().toString());
            ImageView imagebutton = new ImageView(preimagebutton);

            Button ajoutButton = new Button();
            ajoutButton.setLayoutX(480);
            ajoutButton.setLayoutY(130);
            ajoutButton.setPrefWidth(30);
            ajoutButton.setPrefHeight(20);
            ajoutButton.setGraphic(imagebutton);

            vbox.setSpacing(30.0);
            separtor.setLayoutX(3.0);
            separtor.setLayoutY(55.0);
            separtor.setPrefHeight(4.0);
            separtor.setPrefWidth(1213.0);

            anchorpane1.getChildren().addAll(image, libelle, description, prix, ajoutButton);
            vbox.getChildren().add(anchorpane1);
            f.getChildren().addAll(vbox);

            ResultSet r = ps.selectProduitsdistinct(rs.getInt(1));
            while (r.next()) {
                Produit p = new Produit(r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getString(6), r.getString(7), 1);
                ajoutButton.setOnAction(e -> {
                    
                    try {
                        System.out.println(p.toString());
                        ps.ajoutpanier(p, Session.getCurrentSession());

                    } catch (SQLException ex) {
                        Logger.getLogger(VentesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Notifications notif = Notifications.create()
                .title("Ajout Produit avec succées !")
                .text("Produit : "+p.getLibelle())
                .graphic(new ImageView(preimage))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("test");
            }

        }
        );
        notif.showInformation();

                });
            }

        }
        scroll.setContent(f);

    }
}
