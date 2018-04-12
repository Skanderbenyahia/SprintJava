/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.ReservationPetsitter;
import Entity.Session;
import Entity.User;
import Services.CentreDressageService;
import Services.ProduitService;
import Services.ReservationPetsitterService;
import Services.UserService;
import Technique.DataSource;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class ServiceController implements Initializable {
    
    @FXML
    private Label bienvenue;
    public String pathImage = "C:\\Users\\jabou\\Desktop\\SprintJava\\src\\Ressources\\Images\\";
    private Connection con = DataSource.getInstance().getConnexion();
    @FXML
    private AnchorPane an;
    @FXML
    private AnchorPane anP;
    
    int idUser;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ScrollPane scrollP;
    static int id;
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
            AffichageCentreD();
            AffichageRservationP();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void afficherEvents(ActionEvent event) {
    }
    
    @FXML
    private void afficherAcceuil(ActionEvent event) {
    }
    
    @FXML
    private void afficherAdoption(ActionEvent event) {
    }
    
    @FXML
    private void afficherVentes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Ventes.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }
    
    @FXML
    private void afficherServices(ActionEvent event) {
    }
    
    @FXML
    private void afficherSoins(ActionEvent event) {
    }
    
    @FXML
    private void logout(ActionEvent event) throws IOException {
        UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }
    
    public void AffichageCentreD() throws SQLException {
        FlowPane f = new FlowPane();
        
        CentreDressageService cs = new CentreDressageService();
        ResultSet rs = cs.selectCentreD();
        
        while (rs.next()) {
            id=rs.getInt(1);
            JFXButton buttonmap = new JFXButton();
            Label location=new Label("Localisation");
            location.setTextFill(Color.web("ffffff"));
            buttonmap.setGraphic(location);
            buttonmap.setPrefHeight(40);
            buttonmap.setPrefWidth(150);
            buttonmap.setLayoutX(850);
            buttonmap.setLayoutY(150);
           
            buttonmap.setOnAction((e) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/MapCentreD.fxml"));
                    Parent root = loader.load();
                    bienvenue.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            AnchorPane anchorpane1 = new AnchorPane();
            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();
            
            File fileimage = new File(pathImage + rs.getString(8));
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(140);
            image.setLayoutY(37);
            image.setFitWidth(150);
            image.setFitHeight(180);
            
            Label libelle = new Label(rs.getString(2));
            Font font = new Font("Arial", 24);
            libelle.setStyle("-fx-font-weight: bold");
            libelle.setFont(font);
            libelle.setTextFill(Color.web("cfbfa6"));
            libelle.setLayoutX(310);
            libelle.setLayoutY(48);
            
            Label description = new Label("Ceci est un centre de dressage " + rs.getString(7));
            Font font2 = new Font("Arial", 18);
            description.setFont(font2);
            description.setLayoutX(310);
            description.setLayoutY(90);
            
            Label tel = new Label("Numero: " + String.valueOf(rs.getInt(4)));
            tel.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 16);
            tel.setStyle("-fx-font-weight: bold");
            tel.setFont(font3);
            tel.setLayoutX(310);
            tel.setLayoutY(190);
            
            Label adresse = new Label("Situé  : " + rs.getString(3));
            Font font5 = new Font("Arial", 18);
            adresse.setFont(font2);
            adresse.setLayoutX(310);
            adresse.setLayoutY(130);
            
            vbox.setSpacing(30.0);
            separtor.setLayoutX(3.0);
            separtor.setLayoutY(55.0);
            separtor.setPrefHeight(4.0);
            separtor.setPrefWidth(1213.0);
            
            anchorpane1.getChildren().addAll(image, libelle, description, adresse, tel,buttonmap);
            vbox.getChildren().add(anchorpane1);
            f.getChildren().addAll(vbox);
            
        }
        scroll.setContent(f);
        an.getChildren().addAll(scroll);
    }
    
    public void AffichageRservationP() throws SQLException {
        FlowPane f = new FlowPane();
        
        ReservationPetsitterService ps = new ReservationPetsitterService();
        List<User> petsitter = ps.ListPetsitter();
        
        for (User rs : petsitter) {
            
            AnchorPane anchorpane1 = new AnchorPane();
            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();
            
            File fileimage = new File(pathImage + rs.getId() + ".jpeg");
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(120);
            image.setLayoutY(37);
            image.setFitWidth(150);
            image.setFitHeight(180);
            
            Label nom = new Label(rs.getNom());
            Font font = new Font("Arial", 24);
            nom.setStyle("-fx-font-weight: bold");
            nom.setFont(font);
            nom.setTextFill(Color.web("cfbfa6"));
            nom.setLayoutX(290);
            nom.setLayoutY(48);
            
            Label prenom = new Label(rs.getPrenom());
            Font font6 = new Font("Arial", 24);
            prenom.setStyle("-fx-font-weight: bold");
            prenom.setFont(font6);
            prenom.setTextFill(Color.web("cfbfa6"));
            prenom.setLayoutX(450);
            prenom.setLayoutY(48);
            
            Label adresse = new Label("Ce est un petsitter habite à " + rs.getAdresse());
            Font font2 = new Font("Arial", 18);
            adresse.setFont(font2);
            adresse.setLayoutX(290);
            adresse.setLayoutY(90);
            
            Label tel = new Label("Numero: " + String.valueOf(rs.getTel()));
            tel.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 16);
            tel.setStyle("-fx-font-weight: bold");
            tel.setFont(font3);
            tel.setLayoutX(290);
            tel.setLayoutY(130);
            
            DatePicker dateD = new DatePicker();
            dateD.setLayoutX(290);
            dateD.setLayoutY(170);
            
            DatePicker dateF = new DatePicker();
            dateF.setLayoutX(550);
            dateF.setLayoutY(170);
            
            Button reserver = new Button();
            reserver.setPrefHeight(40);
            reserver.setPrefWidth(150);
            reserver.setLayoutX(850);
            Label re = new Label();
            re.setText("Réserver");
            re.setTextFill(Color.web("ffffff"));
            reserver.setLayoutY(150);
            reserver.setGraphic(re);
            
            reserver.setOnAction((e) -> {
                try {
                    float prix = 0;
                    float encaisser = 0;
                    prix = rs.getId() * 10;
                    encaisser = (float) (prix * 0.2);
                    idUser = Session.getCurrentSession();
                    
                    LocalDate Dated = dateD.getValue();
                    Instant i = Instant.from(Dated.atStartOfDay(ZoneId.systemDefault()));
                    java.util.Date date = Date.from(i);
                    java.sql.Date dsql = new java.sql.Date(date.getTime());
                    
                    LocalDate Datef = dateF.getValue();
                    Instant i2 = Instant.from(Datef.atStartOfDay(ZoneId.systemDefault()));
                    java.util.Date date2 = Date.from(i2);
                    java.sql.Date fsql = new java.sql.Date(date2.getTime());
                    
                    ReservationPetsitter r = new ReservationPetsitter(dsql, fsql, prix, encaisser, rs.getId(), idUser);
                    
                    List<ReservationPetsitter> existe = ps.existance(dsql, rs.getId());
                    if (fsql.before(dsql)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Date Debut doit etre supérieur à la date fin ");
                        alert.showAndWait();
                    } else if (existe.isEmpty() == true) {
                        ps.ReserverPetsitter(r);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Votre réservation à été faites");
                        alert.showAndWait();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Service.fxml"));
                        Parent root = loader.load();
                        bienvenue.getScene().setRoot(root);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("La date est déja prise choisir une autre date");
                        alert.showAndWait();
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            
            vbox.setSpacing(30.0);
            separtor.setLayoutX(3.0);
            separtor.setLayoutY(55.0);
            separtor.setPrefHeight(4.0);
            separtor.setPrefWidth(1213.0);
            
            anchorpane1.getChildren().addAll(image, nom, prenom, adresse, tel, dateD, dateF, reserver);
            vbox.getChildren().add(anchorpane1);
            f.getChildren().addAll(vbox);
        }
        //scrollP.setContent(f);
        anP.getChildren().addAll(f);
    }
    
}
