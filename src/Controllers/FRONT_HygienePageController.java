/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Commentaire;
import Entity.Session;
import Entity.User;
import Entity.centreToilettage;
import Entity.jaime;
import Entity.reservation_veterinaire;
import Entity.signaler;
import Services.CommentaireService;
import Services.centreToilettageServices;
import Services.reservation_veterinaire_Service;
import Services.signalerService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.JaimeService;

/**
 * FXML Controller class
 *
 * @author Mimouna
 */
public class FRONT_HygienePageController implements Initializable {

    Stage mainStage;
    JaimeService jaimeService = new JaimeService();
    public Commentaire selectedCom;

    @FXML
    private Hyperlink deconnexion;
    @FXML
    private Hyperlink profil;
    @FXML
    private Button panier;
    @FXML
    private Button location;
    @FXML
    private AnchorPane an;
    private AnchorPane centreScrollPane;

    private AnchorPane veterinaireScrollPane;
    private JFXListView<VBox> veterinaireListe;
    @FXML
    private JFXListView<VBox> centreListe;
    @FXML
    private Button id_like;
    @FXML
    private Button id_dislike1;
    public centreToilettage selectedcentre;
    private List<centreToilettage> listeCentreT;

    centreToilettageServices centreSRV = new centreToilettageServices();
    private String pathImage = "C:\\Users\\jabou\\Desktop\\emna\\SprintJava\\src\\Ressources\\Images\\15.jpeg";
    @FXML
    private JFXListView<Label> ListView_Commentaire;
    @FXML
    private JFXTextArea textCommentaire;
    @FXML
    private JFXButton CommenterPB;
    @FXML
    private JFXButton ValiderPB;
    @FXML
    private JFXButton SupprimerPB;
    @FXML
    private JFXButton ModifierComPB;
    @FXML
    private JFXButton updateComPB;
    @FXML
    private JFXButton SignalerPB;
    @FXML
    private RadioButton religio;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton gros;

    @FXML
    private ToggleGroup gender1;
    final ToggleGroup group = new ToggleGroup();

    @FXML
    private RadioButton autre;
    @FXML
    private ToggleGroup gender11;
    @FXML
    private JFXButton envSignalPB;
    @FXML
    private TextArea txtautre;

    CommentaireService CommentaireSer = new CommentaireService();
    private List<Commentaire> listComment;
    @FXML
    private Label TestSignal;
    private String username;
    //  signalerService signalSR = new signalerService();
    private int id_user = Session.getCurrentSession();
    signalerService signalSR = new signalerService();
    @FXML
    private RadioButton ha;
    private DatePicker date_debut;
    private DatePicker date_fin;
    private JFXComboBox<Label> type;
    @FXML
    private AnchorPane ANVet;
    public String choixType;
    @FXML
    private Button back;
    @FXML
    private ImageView like;
    @FXML
    private ImageView deslike;
    LocalDate testtest;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label bienvenue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        envSignalPB.setVisible(false);
        CommenterPB.setVisible(false);
        SupprimerPB.setVisible(false);
        ValiderPB.setVisible(false);
        textCommentaire.setVisible(false);
        ModifierComPB.setVisible(false);
        updateComPB.setVisible(false);
        SignalerPB.setVisible(false);
        TestSignal.setVisible(false);
        ListView_Commentaire.setVisible(false);
        id_dislike1.setVisible(false);
        id_like.setVisible(false);
        txtautre.setVisible(false);

        try {
            afficherCentres();
            afficherVeterinaire();

        } catch (SQLException ex) {
            Logger.getLogger(FRONT_HygienePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id_dislike1.setVisible(false);
        id_like.setVisible(false);
        try {
            listeCentreT = centreSRV.afficherCentreT();
        } catch (SQLException ex) {
            Logger.getLogger(FRONT_HygienePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherCentres() throws SQLException {
        FlowPane f = new FlowPane();
        centreToilettageServices cs = new centreToilettageServices();
        List<centreToilettage> rs = cs.afficherCentreT();
        rs.forEach(e -> {
            AnchorPane anchorpane1 = new AnchorPane();
            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();
            String img = e.getImage();
            Image image = new Image(img, 150, 100, true, true);
            ImageView imageView = new ImageView();
            imageView.imageProperty().unbind();
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(180);
            imageView.setLayoutX(50);
            imageView.setLayoutY(37);

            Label libelle = new Label("Libelle: " + e.getLibelle());
            Font font = new Font("Arial", 24);
            libelle.setStyle("-fx-font-weight: bold");
            libelle.setFont(font);
            libelle.setTextFill(Color.web("cfbfa6"));
            libelle.setLayoutX(240);
            libelle.setLayoutY(40);

            Label addresse = new Label("Addresse :  " + e.getAdresse());
            Font font2 = new Font("Arial", 18);
            addresse.setFont(font2);
            addresse.setLayoutX(240);
            addresse.setLayoutY(70);

            Label tel = new Label("Telephone:" + e.getTel());
            tel.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 16);
            tel.setStyle("-fx-font-weight: bold");
            tel.setFont(font3);
            tel.setLayoutX(240);
            tel.setLayoutY(100);

            Label description = new Label("Description:" + e.getDescription());
            tel.setTextFill(Color.web("f67777"));
            Font font4 = new Font("Arial", 16);
            description.setStyle("-fx-font-weight: bold");
            description.setFont(font4);
            description.setLayoutX(240);
            description.setLayoutY(130);

            Label nombrejaime = new Label("nombre jaime:" + jaimeService.GetNbrJaime(e.getId()));
            nombrejaime.setTextFill(Color.web("f67777"));
            Font font5 = new Font("Arial", 16);
            tel.setStyle("-fx-font-weight: bold");
            nombrejaime.setFont(font3);
            nombrejaime.setLayoutX(240);
            nombrejaime.setLayoutY(160);

            Label nombrejaimepas = new Label("nombre j'aime pas:" + jaimeService.GetNbrJaime(e.getId()));
            tel.setTextFill(Color.web("f67777"));
            Font font6 = new Font("Arial", 16);
            nombrejaimepas.setStyle("-fx-font-weight: bold");
            nombrejaimepas.setFont(font3);
            nombrejaimepas.setLayoutX(240);
            nombrejaimepas.setLayoutY(190);

            anchorpane1.setOnMouseClicked(a -> {

            });
            vbox.setSpacing(30.0);
            separtor.setLayoutX(3.0);
            separtor.setLayoutY(55.0);
            separtor.setPrefHeight(4.0);
            separtor.setPrefWidth(1213.0);

            anchorpane1.getChildren().addAll(imageView, libelle, addresse, tel, description, nombrejaime, nombrejaimepas);
            vbox.getChildren().add(anchorpane1);
            f.getChildren().addAll(vbox);
            centreListe.getItems().add(vbox);

        });

    }

    /**
     * ** affichage veterinaires ****
     */
    public void afficherVeterinaire() throws SQLException {
        FlowPane f = new FlowPane();
        reservation_veterinaire_Service RVS = new reservation_veterinaire_Service();
        List<User> ListeV = RVS.listeVeterinaire();
        for (User e : ListeV) {

            AnchorPane anchorpane1 = new AnchorPane();
            anchorpane1.setPrefHeight(250.0);
            anchorpane1.setPrefWidth(504.0);
            Separator separtor = new Separator();
            VBox vbox = new VBox();

            Label nom = new Label(e.getNom());
            Font font = new Font("Arial", 24);
            nom.setStyle("-fx-font-weight: bold");
            nom.setFont(font);
            nom.setTextFill(Color.web("cfbfa6"));
            nom.setLayoutX(290);
            nom.setLayoutY(48);

            Label prenom = new Label(e.getPrenom());
            Font font2 = new Font("Arial", 18);
            prenom.setFont(font2);
            prenom.setLayoutX(450);
            prenom.setLayoutY(48);

            Label tel = new Label("Telephone:" + e.getTel());
            tel.setTextFill(Color.web("f67777"));
            Font font3 = new Font("Arial", 16);
            tel.setStyle("-fx-font-weight: bold");
            tel.setFont(font3);
            tel.setLayoutX(290);
            tel.setLayoutY(130);

            Label adresse = new Label("Adresse:" + e.getAdresse());
            tel.setTextFill(Color.web("f67777"));
            Font font4 = new Font("Arial", 16);
            adresse.setStyle("-fx-font-weight: bold");
            adresse.setFont(font4);
            adresse.setLayoutX(290);
            adresse.setLayoutY(90);

            File fileimage = new File(pathImage + e.getId() + ".jpeg");
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(120);
            image.setLayoutY(37);
            image.setFitHeight(150);
            image.setFitWidth(180);

            Button prendre_rdv = new Button();
            prendre_rdv.setPrefHeight(40);
            prendre_rdv.setPrefWidth(200);
            prendre_rdv.setLayoutX(850);
            prendre_rdv.setLayoutY(120);
            Label RE = new Label();
            RE.setText(" prendre un rendez-vous");
            RE.setTextFill(Color.web("ffffff"));
            prendre_rdv.setGraphic(RE);

            ComboBox type = new ComboBox();
            type.getItems().addAll("operation", "consultation");
            type.setPromptText("type");
            type.setLayoutX(850);
            type.setLayoutY(170);
            type.setPrefHeight(4.0);
            type.setPrefWidth(213.0);
            type.setEditable(true);

            JFXDatePicker time_debut = new JFXDatePicker();
            time_debut.setShowTime(true);
            time_debut.setLayoutX(290);
            time_debut.setLayoutY(170);

            JFXDatePicker date_debut = new JFXDatePicker();
            date_debut.setLayoutX(550);
            date_debut.setLayoutY(170);

            prendre_rdv.setOnAction((ActionEvent X) -> {
                choixType = type.getValue().toString();
                LocalDate DD1 = date_debut.getValue();
                LocalTime DT1 = time_debut.getTime();
                LocalDateTime fulldate_debut = LocalDateTime.of(DD1, DT1);
                if (fulldate_debut.isAfter(LocalDateTime.now())) {
                    if (type.getValue().equals("consultation")) {
                        try {
                            LocalTime DT2 = DT1.plusMinutes(30);
                            LocalDateTime fulldate_fin = LocalDateTime.of(DD1, DT2);
                            reservation_veterinaire RV = new reservation_veterinaire(id_user, e.getId(), fulldate_debut, fulldate_fin, choixType);

                            System.out.println(RV.toString());
                            boolean test = RVS.ReserverVeterinaireExiste(RV);
                            if (test == true) {
                                RVS.ReserverVeterinaire(RV);
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                alert1.setTitle("Reservation Dialog");
                                alert1.setHeaderText(null);
                                alert1.setContentText("Réservation passée avec succée !");
                                alert1.show();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText("Date déja prise !");
                                alert.show();
                            }
                        } catch (SQLException | ParseException ex) {
                            Logger.getLogger(FRONT_HygienePageController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        LocalTime DT2 = DT1.plusMinutes(90);
                        LocalDateTime fulldate_fin = LocalDateTime.of(DD1, DT2);
                        reservation_veterinaire RV = new reservation_veterinaire(id_user, e.getId(), fulldate_debut, fulldate_fin, choixType);
                        try {
                            boolean test = RVS.ReserverVeterinaireExiste(RV);
                            if (test == true) {
                                RVS.ReserverVeterinaire(RV);
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                alert1.setTitle("Reservation Dialog");
                                alert1.setHeaderText(null);
                                alert1.setContentText("Réservation passée avec succée !");
                                alert1.show();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText("Date déja prise !");
                                alert.show();
                            }
                        } catch (SQLException | ParseException ex) {
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("La date doit etre supérieur a la date courante !");
                    alert.show();
                }

            });

            vbox.setSpacing(30.0);
            separtor.setLayoutX(3.0);
            separtor.setLayoutY(55.0);
            separtor.setPrefHeight(4.0);
            separtor.setPrefWidth(1213.0);

            anchorpane1.getChildren().addAll(image, nom, prenom, adresse, tel, date_debut, prendre_rdv, type, time_debut);
            vbox.getChildren().add(anchorpane1);
            f.getChildren().addAll(vbox);

        }
        scroll.setContent(f);
        ANVet.getChildren().addAll(scroll);
    }
    ;

    User selectedUser;

    @FXML
    private void likeAction(ActionEvent event) throws IOException {
        int a = centreListe.getSelectionModel().getSelectedIndex();
        selectedcentre = listeCentreT.get(a);
        System.out.println("hello " + selectedcentre.getId());
        jaime j = new jaime(Session.getCurrentSession(), selectedcentre.getId(), 1, 0);
        jaimeService.ajouterJaime(j);
        jaimeService.supprimerJaimePas(Session.getCurrentSession(), selectedcentre.getId());

        id_like.setVisible(false);
        id_dislike1.setVisible(true);
        like.setVisible(false);
        Parent homePage = FXMLLoader.load(getClass().getResource("/GUI/ListeCentreToilettage_front.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();

    }

    @FXML
    private void dislikeAction1(ActionEvent event) throws IOException {
        int a = centreListe.getSelectionModel().getSelectedIndex();
        selectedcentre = listeCentreT.get(a);
        System.out.println("hello " + selectedcentre.getId());
        jaime j = new jaime(Session.getCurrentSession(), selectedcentre.getId(), 0, 1);
        jaimeService.ajouterJaime(j);
        jaimeService.supprimerJaime(Session.getCurrentSession(), selectedcentre.getId());
        id_like.setVisible(true);
        id_dislike1.setVisible(false);
        deslike.setVisible(false);

        Parent homePage = FXMLLoader.load(getClass().getResource("/GUI/ListeCentreToilettage_front.fxml"));
        Scene homePage_scene = new Scene(homePage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePage_scene);
        app_stage.show();
    }

    @FXML
    private void GetIndexComment(MouseEvent event) {
        int index = centreListe.getSelectionModel().getSelectedIndex();
        selectedcentre = listeCentreT.get(index);

        SupprimerPB.setVisible(true);
        int index2 = ListView_Commentaire.getSelectionModel().getSelectedIndex();
        listComment = CommentaireSer.findAll(selectedcentre.getId());
        selectedCom = listComment.get(index2);
        CommenterPB.setVisible(false);
        ModifierComPB.setVisible(true);
        ValiderPB.setVisible(false);
        textCommentaire.setVisible(false);
        updateComPB.setVisible(false);
        SignalerPB.setVisible(true);
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        envSignalPB.setVisible(false);
        TestSignal.setVisible(false);
        txtautre.setVisible(false);

    }

    private void remplirListViewCommentaire() {
        ListView_Commentaire.getItems().clear();
        for (Commentaire commentaire : listComment) {
            int year = commentaire.getDate_com().getYear();
            Month month = commentaire.getDate_com().getMonth();
            int day = commentaire.getDate_com().getDayOfMonth();

            int nbrj = (LocalDateTime.now().getDayOfYear() - commentaire.getDate_com().getDayOfYear());
            int nbrheur = (LocalDateTime.now().getHour() - commentaire.getDate_com().getHour());
            int nbrmin = (LocalDateTime.now().getMinute() - commentaire.getDate_com().getMinute());
            username = CommentaireSer.Username(commentaire.getIdUser(), commentaire.getId());
            if (nbrj > 0 && nbrheur > 0 && nbrmin > 0) {
                Label lbl = new Label(username + "  :  " + day + "/" + month + "/" + year + "\n il y a " + nbrj + "Jour  " + nbrheur + "Heure  " + nbrmin + "Minute\n"
                        + commentaire.getContenu() + "\n"
                );
                ListView_Commentaire.getItems().add(lbl);
            }
            if (nbrj == 0 && nbrheur == 0 && nbrmin == 0) {
                Label lbl = new Label(username + "  :  " + day + "/" + month + "/" + year + "\n  Maintenant\n"
                        + commentaire.getContenu() + "\n"
                );
                ListView_Commentaire.getItems().add(lbl);
            }
            if (nbrj == 0 && nbrheur == 0 && nbrmin > 0) {
                Label lbl = new Label(username + "  :  " + day + "/" + month + "/" + year + "\n il y a " + nbrmin + "  Minute\n"
                        + commentaire.getContenu() + "\n"
                );
                ListView_Commentaire.getItems().add(lbl);
            }
            if (nbrj == 0 && nbrheur > 0 && nbrmin == 0) {
                Label lbl = new Label(username + "  :  " + day + "/" + month + "/" + year + "\n il y a " + nbrheur + "  Heure  \n"
                        + commentaire.getContenu() + "\n"
                );
                ListView_Commentaire.getItems().add(lbl);
            }
            if (nbrj > 0 && nbrheur == 0 && nbrmin == 0) {
                Label lbl = new Label(username + "  :  " + day + "/" + month + "/" + year + "\n il y a " + nbrj + "  Jour  \n"
                        + commentaire.getContenu() + "\n"
                );
                ListView_Commentaire.getItems().add(lbl);
            }
            if (nbrj == 0 && nbrheur > 0 && nbrmin > 0) {
                Label lbl = new Label(username + "  :  " + day + "/" + month + "/" + year + "\n il y a  " + nbrheur + "  Heure  " + nbrmin + "  Minute\n"
                        + commentaire.getContenu() + "\n"
                );
                ListView_Commentaire.getItems().add(lbl);
            }
            if (nbrj > 0 && nbrheur > 0 && nbrmin == 0) {
                Label lbl = new Label(username + "  :  " + day + "/" + month + "/" + year + "\n il y a  " + nbrj + "  Jour  " + nbrheur + "  Heure\n"
                        + commentaire.getContenu() + "\n"
                );
                ListView_Commentaire.getItems().add(lbl);
            }
            if (nbrj > 0 && nbrheur == 0 && nbrmin > 0) {
                Label lbl = new Label(username + "  :  " + day + "/" + month + "/" + year + "\n il y a  " + nbrj + "  Jour  " + nbrmin + "  Minute\n"
                        + commentaire.getContenu() + "\n"
                );
                ListView_Commentaire.getItems().add(lbl);
            }

        }

    }

    @FXML
    private void ValiderCommentaire(ActionEvent event) {
        int index = centreListe.getSelectionModel().getSelectedIndex();
        selectedcentre = listeCentreT.get(index);

        Commentaire c = new Commentaire(textCommentaire.getText(), 0, Session.getCurrentSession(), selectedcentre.getId(), LocalDateTime.now());
        CommentaireSer.ajouter_commentaire(c);
        listComment = CommentaireSer.AfficherCommentaireCentre(selectedcentre.getId());
        remplirListViewCommentaire();
        textCommentaire.setText("");
        textCommentaire.setVisible(false);
        ValiderPB.setVisible(false);
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        envSignalPB.setVisible(false);
        TestSignal.setVisible(false);
        txtautre.setVisible(false);

    }

    @FXML
    private void supprimerComment(ActionEvent event) throws IOException {
        int index = centreListe.getSelectionModel().getSelectedIndex();
        selectedcentre = listeCentreT.get(index);

        int b = ListView_Commentaire.getSelectionModel().getSelectedIndex();
        selectedCom = listComment.get(b);
        if (id_user != selectedCom.getIdUser()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainStage);
            alert.setTitle("Suppression ");
            alert.setHeaderText("Vous  pouver supprimer seulement vos commentaires");
            alert.setContentText("Veuillez supprimer vos commentaire si vous voulez ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Confirmation de la suppression");
            alert.setHeaderText("Supprimer commentaire");
            alert.setContentText("Veuillez Confirmer la suppression");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.err.println(selectedCom.getId());
                    CommentaireSer.supprimer_commentaire(selectedCom.getId(), id_user);

                }
            });
        }
        listComment = CommentaireSer.AfficherCommentaireCentre(selectedcentre.getId());
        remplirListViewCommentaire();
        ModifierComPB.setVisible(false);
        SupprimerPB.setVisible(false);
        SignalerPB.setVisible(false);
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        envSignalPB.setVisible(false);
        TestSignal.setVisible(false);
        txtautre.setVisible(false);

        Parent homePage = FXMLLoader.load(getClass().getResource("/GUI/ListeCentreToilettage_front.fxml"));
        Scene homePage_scene = new Scene(homePage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePage_scene);
        app_stage.show();

    }

    @FXML
    private void modifierComment(ActionEvent event) throws IOException {
        textCommentaire.setVisible(true);
        textCommentaire.setText(selectedCom.getContenu());
        updateComPB.setVisible(true);
        SupprimerPB.setVisible(false);
        ModifierComPB.setVisible(false);
        SignalerPB.setVisible(false);
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        envSignalPB.setVisible(false);
        TestSignal.setVisible(false);
        txtautre.setVisible(false);
    }

    @FXML
    private void updateComment(ActionEvent event) {
        int index = centreListe.getSelectionModel().getSelectedIndex();
        selectedcentre = listeCentreT.get(index);

        int b = ListView_Commentaire.getSelectionModel().getSelectedIndex();
        selectedCom = listComment.get(b);
        Commentaire c1 = new Commentaire();

        c1.setContenu(textCommentaire.getText());
        c1.setDate_com(LocalDateTime.now());
        if (id_user != selectedCom.getIdUser()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainStage);
            alert.setTitle("Modification ");
            alert.setHeaderText("Vous  pouver modifier seulement vos commentaires");
            alert.setContentText("Veuillez modifier vos commentaire si vous voulez ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Confirmation de la modification");
            alert.setHeaderText("Modifier commentaire");
            alert.setContentText("Veuillez Confirmer la Modification");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    CommentaireSer.modifier_commentaire(c1, selectedCom.getId(), Session.getCurrentSession());

                }
            });
        }
        listComment = CommentaireSer.AfficherCommentaireCentre(selectedcentre.getId());
        remplirListViewCommentaire();
        textCommentaire.setText("");
        textCommentaire.setVisible(false);
        updateComPB.setVisible(false);
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        envSignalPB.setVisible(false);
        TestSignal.setVisible(false);
        txtautre.setVisible(false);

    }

    @FXML
    private void SignalerComment(ActionEvent event) {
        if (signalSR.GetSignaltest(Session.getCurrentSession(), selectedCom.getId())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Signaler ");
            alert.setHeaderText("Vous  avez deja signaler ce commentaire");
            alert.setContentText("Merci .");
            alert.showAndWait();

        } else {
            religio.setVisible(true);
            gros.setVisible(true);
            ha.setVisible(true);
            autre.setVisible(true);
            ModifierComPB.setVisible(false);
            SupprimerPB.setVisible(false);
            SignalerPB.setVisible(false);
            religio.setToggleGroup(group);
            religio.setSelected(true);
            gros.setToggleGroup(group);
            ha.setToggleGroup(group);
            autre.setToggleGroup(group);
            envSignalPB.setVisible(true);
            TestSignal.setVisible(false);
        }
    }

    @FXML
    private void envSignalComment(ActionEvent event) throws IOException {
        String a = "";
        if (religio.isSelected()) {
            a = "religion";
        }
        if (gros.isSelected()) {
            a = "grosmot";
        }
        if (ha.isSelected()) {
            a = "harcelemenet";
        }
        if (autre.isSelected()) {

            a = txtautre.getText();
        }
        System.out.println(a);

        //commentaire
        int b = ListView_Commentaire.getSelectionModel().getSelectedIndex();
        selectedCom = listComment.get(b);
        signaler s = new signaler(a, Session.getCurrentSession(), selectedCom.getId());
        signalSR.ajouter_signal(s);
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        txtautre.setVisible(false);
        envSignalPB.setVisible(false);
        TestSignal.setVisible(false);
        if (signalSR.GetNbrSignal(selectedCom.getId()) == 2) {
            CommentaireSer.misajour(selectedCom.getId());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainStage);
            alert.setTitle("Suppression automatique du commentaire ");
            alert.setHeaderText("A cause du nombre de signal ");
            alert.setContentText("Merci .");
            alert.showAndWait();

            Parent homePage = FXMLLoader.load(getClass().getResource("/GUI/ListeCentreToilettage_front.fxml"));
            Scene homePage_scene = new Scene(homePage);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(homePage_scene);
            app_stage.show();
        }

    }

    @FXML
    private void religioncheckbox(MouseEvent event) {
        txtautre.setVisible(false);
    }

    @FXML
    private void grosmotcheckbox(MouseEvent event) {
        txtautre.setVisible(false);
    }

    @FXML
    private void harcelementchcheckbox(MouseEvent event) {
        txtautre.setVisible(false);

    }

    @FXML
    private void autrecheckbox(MouseEvent event) {
        txtautre.setVisible(true);
    }

    @FXML
    private void CommenterCentre(ActionEvent event) {
        textCommentaire.setVisible(true);
        ValiderPB.setVisible(true);
        textCommentaire.setVisible(true);
        CommenterPB.setVisible(false);
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        envSignalPB.setVisible(false);
        TestSignal.setVisible(false);
        txtautre.setVisible(false);
    }

    @FXML
    private void selectionnerCentre(MouseEvent event) {
        id_like.setVisible(true);
        id_dislike1.setVisible(true);
        int index = centreListe.getSelectionModel().getSelectedIndex();
        selectedcentre = listeCentreT.get(index);
        if (jaimeService.GetJaimeByIdCentre(selectedcentre.getId(), Session.getCurrentSession())) {
            id_like.setVisible(false);
            id_dislike1.setVisible(true);
        } else {
            id_dislike1.setVisible(false);
            id_like.setVisible(true);
        }
        ListView_Commentaire.setVisible(true);
        listComment = CommentaireSer.AfficherCommentaireCentre(selectedcentre.getId());
        remplirListViewCommentaire();
        textCommentaire.setVisible(false);
        ValiderPB.setVisible(false);
        SupprimerPB.setVisible(false);
        CommenterPB.setVisible(true);
        updateComPB.setVisible(false);
        ModifierComPB.setVisible(false);
        SignalerPB.setVisible(false);
        religio.setVisible(false);
        gros.setVisible(false);
        ha.setVisible(false);
        autre.setVisible(false);
        envSignalPB.setVisible(false);
        TestSignal.setVisible(false);
        txtautre.setVisible(false);
    }

    private void selectedVeterinaire(MouseEvent event) {

    }

    @FXML
    private void back(ActionEvent event) throws IOException {

        Parent homePage = FXMLLoader.load(getClass().getResource("/GUI/home.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    private void afficher_reservation_user(ActionEvent event) throws IOException 
    {
            Parent homePage = FXMLLoader.load(getClass().getResource("/GUI/reservation_user.fxml"));
            Scene homePage_scene = new Scene(homePage);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(homePage_scene);
            app_stage.show();
        
    }

    

}
