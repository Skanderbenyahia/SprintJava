/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Concour;
import Entity.Session;
import Services.ServiceConcour;
import Services.ServiceConseils;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import com.teknikindustries.bulksms.SMS;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class Front_eventsController implements Initializable {

    @FXML
    private Tab concours;
    @FXML
    private Tab conseils;
    int idConcour;
    int idUser;
    int nbPlaces;

    private JFXTextField RATE;
    @FXML
    private ScrollPane scroll;
    public String pathImage = "C:\\Users\\bn-sk\\Desktop\\GitFinal\\SprintJava\\src\\Ressources\\Images\\";
    @FXML
    private AnchorPane anC;
    @FXML
    private AnchorPane ancon;
    @FXML
    private ScrollPane scrollConc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            afficherConcour();
            afficherConseil();
        } catch (SQLException ex) {
            Logger.getLogger(Front_eventsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherConseil() throws SQLException {
        FlowPane f = new FlowPane();

        ServiceConseils sc = new ServiceConseils();
        ResultSet rs = sc.selectConseil();

        while (rs.next()) {

            AnchorPane anchorpane1 = new AnchorPane();
            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();

            File fileimage = new File(pathImage + "conseils.jpg");
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(140);
            image.setLayoutY(37);
            image.setFitWidth(150);
            image.setFitHeight(180);

            Label titre = new Label(rs.getString(2));
            Font font = new Font("Arial", 24);
            titre.setStyle("-fx-font-weight: bold");
            titre.setFont(font);
            titre.setTextFill(Color.web("cfbfa6"));
            titre.setLayoutX(310);
            titre.setLayoutY(48);

            Label text = new Label("Ce conseil porte sur : " + rs.getString(3));
            Font font2 = new Font("Arial", 18);
            text.setFont(font2);
            text.setLayoutX(310);
            text.setLayoutY(90);

            Label type = new Label("Type: " + rs.getString(4));
            type.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 16);
            type.setStyle("-fx-font-weight: bold");
            type.setFont(font3);
            type.setLayoutX(310);
            type.setLayoutY(190);

            vbox.setSpacing(30.0);
            separtor.setLayoutX(3.0);
            separtor.setLayoutY(55.0);
            separtor.setPrefHeight(4.0);
            separtor.setPrefWidth(1213.0);

            anchorpane1.getChildren().addAll(image, titre, text, type);
            vbox.getChildren().add(anchorpane1);
            f.getChildren().addAll(vbox);

        }
        scroll.setContent(f);
    }

    public void afficherConcour() throws SQLException {
        idUser = Session.getCurrentSession();
        FlowPane f = new FlowPane();
                    ServiceConcour serviceC = new ServiceConcour();

        ServiceConcour sc = new ServiceConcour();
        List<Concour> concour = sc.AfficherListeConcours();

        for (Concour rs : concour) {

            AnchorPane anchorpane1 = new AnchorPane();
            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();

            File fileimage = new File(pathImage + "concour.jpg");
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(140);
            image.setLayoutY(37);
            image.setFitWidth(150);
            image.setFitHeight(180);

            Label description = new Label(rs.getDescription());
            Font font = new Font("Arial", 24);
            description.setStyle("-fx-font-weight: bold");
            description.setFont(font);
            description.setTextFill(Color.web("cfbfa6"));
            description.setLayoutX(310);
            description.setLayoutY(48);

            Label nbPlace = new Label("Nombre de place disponible: " + String.valueOf(rs.getnbredeplaces()));
            Font font2 = new Font("Arial", 18);
            nbPlace.setFont(font2);
            nbPlace.setLayoutX(310);
            nbPlace.setLayoutY(90);

            Label DATE = new Label("Date du concour: " + String.valueOf(rs.getdate()));
            DATE.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 16);
            DATE.setStyle("-fx-font-weight: bold");
            DATE.setFont(font3);
            DATE.setLayoutX(310);
            DATE.setLayoutY(130);

            Button participer = new Button();
            participer.setPrefHeight(40);
            participer.setPrefWidth(150);
            participer.setLayoutX(850);
            Label re = new Label();
            re.setText("Participer");
            re.setTextFill(Color.web("ffffff"));
            participer.setLayoutY(150);
            participer.setGraphic(re);

            TextField note = new TextField();
            note.setLayoutX(300);
            note.setLayoutY(170);
            note.setPrefHeight(30);
            note.setPrefWidth(50);
            Label rd = new Label();
            rd.setText("/5");
            rd.setLayoutX(360);
            rd.setLayoutY(170);

            Label notes = new Label();
            Font font4 = new Font("Arial", 26);
            notes.setStyle("-fx-font-weight: bold");
            description.setFont(font4);
            notes.setTextFill(Color.web("cfbfa6"));
            notes.setLayoutX(420);
            notes.setLayoutY(170);
            float r = serviceC.ratingcount(rs.getId());
            notes.setText("Notes : " + r);

            Button noter = new Button();
            noter.setPrefHeight(40);
            noter.setPrefWidth(150);
            noter.setLayoutX(550);
            Label rr = new Label();
            rr.setText("Noter");
            rr.setTextFill(Color.web("ffffff"));
            noter.setLayoutY(170);
            noter.setGraphic(rr);

            participer.setOnAction(e -> {
                try {
                    int nbparticipants = serviceC.concourPlaces(rs.getId());
                    if (nbparticipants <= (rs.getnbredeplaces())) {
                        serviceC.participation(rs.getId(), idUser);
                        SMS smstut = new SMS();

                        smstut.SendSMS("salysaly", "00000000", "welcome to concours ", "21652639490", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                        System.out.println("added participation");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText("Ce Concours est plein ");
                        alert.setContentText("Nous sommes dsl vous pouvez consulter les autres concours");
                        alert.showAndWait();
                    }

                } catch (SQLException ex) {
                    System.out.println("error");
                }

            });

            noter.setOnAction(e -> {
                try {
                    float NOTE = Integer.parseInt(note.getText());

                    serviceC.rating(rs.getId(), NOTE);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Rated ");
                    alert.setContentText("Merci d'avoir noté ce concour !!");
                    alert.showAndWait();
                    
                } catch (SQLException ex) {
                    System.out.println("error rate");
                }

            });

            vbox.setSpacing(30.0);
            separtor.setLayoutX(3.0);
            separtor.setLayoutY(55.0);
            separtor.setPrefHeight(4.0);
            separtor.setPrefWidth(1213.0);

            anchorpane1.getChildren().addAll(image, description, nbPlace, DATE, participer, note, noter, rd,notes);
            vbox.getChildren().add(anchorpane1);
            f.getChildren().addAll(vbox);
        }
        scrollConc.setContent(f);
    }
}
