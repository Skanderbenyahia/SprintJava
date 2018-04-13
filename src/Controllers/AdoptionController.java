/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Animal;
import Entity.Refuge;
import Entity.Session;
import Entity.User;
import Entity.WishList;
import Services.AnimalService;
import Services.RefugeService;
import Services.UserService;
import Technique.DataSource;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import javafx.stage.Stage;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import java.sql.Connection;
import java.util.Calendar;
import java.sql.PreparedStatement;

import static java.time.LocalDate.now;
import java.util.ArrayList;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author Skeez
 */
public class AdoptionController implements Initializable {

    public String pathImage = "C:\\Users\\Skeez\\Desktop\\GitRogue\\SprintJava\\src\\Ressources\\Images\\";
    public String pathButton = "C:\\Users\\Skeez\\Desktop\\GitRogue\\SprintJava\\src\\Ressources\\Images\\add-square-button.png";
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane tabanimal;
    @FXML
    private AnchorPane tabrefuge;
    @FXML
    private Button facebookbutton;
    @FXML
    private JFXTextArea facebookcomment;
    @FXML
    private Label bienvenue;
    private Connection con = DataSource.getInstance().getConnexion();
    @FXML
    private ScrollPane scrollR;

    

    /**
     * Initializes the controller class.
     */
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
            AffichageAnimal();
            afficherRefuge();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AffichageAnimal() throws SQLException {

        FlowPane f = new FlowPane();
        AnimalService as = new AnimalService();
        List<Animal> rs = as.selectAnimals();

        for (Animal r : rs) {
            if (r.getEtat().equals("nonadopte")) {
                AnchorPane anchorpane1 = new AnchorPane();

                anchorpane1.setPrefHeight(250.0);
                anchorpane1.setPrefWidth(504.0);
                Separator separtor = new Separator();
                VBox vbox = new VBox();
                System.out.println(r.getImage());

                File fileimage = new File(pathImage + r.getImage());
                Image preimage = new Image(fileimage.toURI().toString());
                ImageView image = new ImageView(preimage);
                image.setLayoutX(120);
                image.setLayoutY(37);
                image.setFitWidth(150);
                image.setFitHeight(180);

                Label libelle = new Label(r.getNom());
                Font font = new Font("Arial", 26);
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
                re.setText("Adopter");
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
                Animal p = new Animal(r.getId(), r.getNom(), r.getEspece(), r.getRace(), r.getAge(), r.getSexe(), r.getTaille(), r.getRegion(), r.getDescription(), r.getEtat(), r.getImage(), r.getDemande());
                ajoutButton.setOnAction(e -> {
                    try {
                        as.increment(p);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Formulaire_Adoption.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException ex) {
                            Logger.getLogger(AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        as.AjouterWishlistAnimal(Session.getCurrentSession(), r.getId());
                        tabanimal.getScene().setRoot(root);

                    } catch (SQLException ex) {
                        Logger.getLogger(AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            }
        }
        scroll.setContent(f);
        tabanimal.getChildren().add(scroll);
        

    }

    public void changeScene3(ActionEvent event, String viewName, String title, Refuge a, ControllerClassAdoption controllerC) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();

        controllerC = loader.getController();
        controllerC.preloadData(a);

        Scene CentreDPage = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle(title);
        window.setScene(CentreDPage);
        window.show();
    }

    public void afficherRefuge() throws SQLException {
        FlowPane f = new FlowPane();
        RefugeService rf = new RefugeService();
        List<Refuge> lr = rf.selectRefuges();
        for (Refuge r : lr) {

            AnchorPane anchorpane1 = new AnchorPane();

            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();

            File fileimage = new File(pathImage + r.getImage());
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(120);
            image.setLayoutY(37);
            image.setFitWidth(150);
            image.setFitHeight(180);

            Label libelle = new Label(r.getLibelle());
            Font font = new Font("Arial", 26);
            libelle.setStyle("-fx-font-weight: bold");
            libelle.setFont(font);
            libelle.setTextFill(Color.web("cfbfa6"));
            libelle.setLayoutX(290);
            libelle.setLayoutY(48);

            Label description = new Label("Description :" + r.getDescription());
            Font font2 = new Font("Arial", 18);
            description.setFont(font2);
            description.setLayoutX(290);
            description.setLayoutY(90);

            Label demande = new Label(String.valueOf(r.getEmail()));
            demande.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 16);
            demande.setStyle("-fx-font-weight: bold");
            demande.setFont(font3);
            demande.setLayoutX(290);
            demande.setLayoutY(190);

            File file = new File(pathButton);
            Image preimagebutton = new Image(file.toURI().toString());
            ImageView imagebutton = new ImageView(preimagebutton);

            Button ajoutButton = new Button();
            ajoutButton.setPrefHeight(40);
            ajoutButton.setPrefWidth(150);
            ajoutButton.setLayoutX(650);
            Label re = new Label();
            re.setText("Contacter");
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

            Refuge rr = new Refuge(r.getId(), r.getLibelle(), r.getNum(), r.getEmail(), r.getRegion(), r.getAdresse(), r.getDescription(), r.getImage());
            ajoutButton.setOnAction(e -> {

                Formulaire_RefugeController controllerR = new Formulaire_RefugeController();
                try {
                    changeScene3(e, "../GUI/Formulaire_Refuge.fxml", "Modifier le refuge", rr, controllerR);
                } catch (IOException ex) {
                    Logger.getLogger(AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        }
        tabrefuge.getChildren().add(f);
    }

    @FXML
    private void facebookComent(ActionEvent event) throws FacebookException, SQLException, IOException {
        String nom = "";
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("140659596773881", "b883646ec52c8b0acfd8e205487dd065");
        String accessTokenString = "EAACEdEose0cBAD16duKPyjZCbxBjRnmhy4kvJVekviH4LfZBgChnwSzjFOLb8nKVZA9tZAuQclbshZCIRrddf25NOELImOd6zoVZB30S5SZCvbQbZCAYPbbniSDLoYB4VyIJYWQfCUmfPkkj1BdVa3SEQLiTdKPUH6YELtSuan6j0SkYULvYpBCHPFAK5PyqYkL8E9VQC6whVwZDZD";
        AccessToken at = new AccessToken(accessTokenString);
        facebook.setOAuthAccessToken(at);
        UserService us = new UserService();
        int id = Session.getCurrentSession();
        ResultSet rs = us.userNom(id);
        while (rs.next()) {
            nom = rs.getString(1);
        }
        facebook.postStatusMessage(nom + ": " + facebookcomment.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Adoption.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void panierPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Panier.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void homePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/home.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void adoptionPagee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Adoption.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void ventePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Ventes.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void servicePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Service.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void soinPage(ActionEvent event) {
    }

    @FXML
    private void eventPage(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

}
