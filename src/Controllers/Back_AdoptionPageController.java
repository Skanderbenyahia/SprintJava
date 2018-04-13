/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Animal;
import Entity.Refuge;
import Entity.Session;
import Services.AnimalService;
import Services.RefugeService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
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
 * @author Skeez
 */
public class Back_AdoptionPageController implements Initializable {

    @FXML
    private TableView<Animal> AnimalView;
    @FXML
    private TableColumn<Animal, String> Column_description;
    @FXML
    private Button AjouterAnimal;
    @FXML
    private TableColumn<Animal, String> Column_nom;
    @FXML
    private TableColumn<Animal, String> Column_race;
    @FXML
    private TableColumn<Animal, String> Column_espece;
    @FXML
    private TableColumn<Animal, String> Column_age;
    @FXML
    private TableColumn<Animal, String> Column_taille;
    @FXML
    private TableColumn<Animal, String> Column_etat;
    @FXML
    private Button ModifierAnimal;
    @FXML
    private TableView<Refuge> RefugeView;
    @FXML
    private Button AjouterRefuge;
    @FXML
    private TableColumn<Refuge, String> Column_libelle_refuge;
    @FXML
    private TableColumn<Refuge, String> Column_email_refuge;
    @FXML
    private TableColumn<Refuge, String> Column_adresse_refuge;
    @FXML
    private TableColumn<Refuge, Integer> Column_num_refuge;
    @FXML
    private TableColumn<Refuge, String> Column_region_refuge;
    @FXML
    private TableColumn<Refuge, String> Column_description_refuge;
    @FXML
    private TableColumn<Refuge, String> Column_image_refuge;
    @FXML
    private Button ModifierRefuge;
    @FXML
    private Button SupprimerRefuge;
    @FXML
    private Button SupprimerAnimal1;
    @FXML
    private Button changerEtatt;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*ModifierAnimal.setDisable(true);
      SupprimerAnimal.setDisable(true);
      // ChangerEtat.setDisable(true);*/
        Column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Column_race.setCellValueFactory(new PropertyValueFactory<>("race"));
        Column_espece.setCellValueFactory(new PropertyValueFactory<>("espece"));
        Column_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        Column_taille.setCellValueFactory(new PropertyValueFactory<>("taille"));
        Column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Column_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        try {
            AnimalService as = new AnimalService();
            AnimalView.setItems(as.getObservableAnimal());
        } catch (SQLException ex) {
            Logger.getLogger(Back_ServicePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*  ModifierRefuge.setDisable(true);
       SupprimerRefuge.setDisable(true);*/
        Column_libelle_refuge.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        Column_email_refuge.setCellValueFactory(new PropertyValueFactory<>("email"));
        Column_adresse_refuge.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Column_num_refuge.setCellValueFactory(new PropertyValueFactory<>("num"));
        Column_region_refuge.setCellValueFactory(new PropertyValueFactory<>("region"));
        Column_description_refuge.setCellValueFactory(new PropertyValueFactory<>("description"));
        Column_image_refuge.setCellValueFactory(new PropertyValueFactory<>("image"));

        try {
            RefugeService as = new RefugeService();
            RefugeView.setItems(as.getObservableRefuge());
        } catch (SQLException ex) {
            Logger.getLogger(Back_ServicePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selected(MouseEvent event) {
        /*  ModifierAnimal.setDisable(false);
       SupprimerAnimal.setDisable(false);
       
     // ChangerEtat.setDisable(false);*/
    }

    @FXML
    private void AjouterAnimal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Back_AjoutAnimalPage.fxml"));
        Parent root = loader.load();
        AjouterAnimal.getScene().setRoot(root);
    }

    public void changeScene(ActionEvent event, String viewName, String title, Animal a, ControllerClassAdoption controllerC) throws IOException {
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

    @FXML
    private void ModifierAnimal(ActionEvent event) throws IOException {
        Animal d = this.AnimalView.getSelectionModel().getSelectedItem();
        Back_AjoutAnimalPageController controllerC = new Back_AjoutAnimalPageController();
        changeScene(event, "../GUI/Back_AjoutAnimalPage.fxml", "Modifier l'animal", d, controllerC);
    }

    @FXML
    private void SupprimerAnimal(ActionEvent event) throws SQLException, IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de supprimer l'animal ?");
        Optional<ButtonType> ok = alert.showAndWait();
        if (ok.get() == ButtonType.OK) {
            Animal d = this.AnimalView.getSelectionModel().getSelectedItem();
            AnimalService cd = new AnimalService();
            cd.SupprimerAnimal(d.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Back_AdoptionPage.fxml"));
            Parent root = loader.load();
            AnimalView.getScene().setRoot(root);
        }
    }

    @FXML
    private void AjouterRefuge(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Back_AjoutRefugePage.fxml"));
        Parent root = loader.load();
        AjouterRefuge.getScene().setRoot(root);
    }

    public void changeScene2(ActionEvent event, String viewName, String title, Refuge a, ControllerClassAdoption controllerC) throws IOException {
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

    @FXML
    private void ModifierRefuge(ActionEvent event) throws IOException {
        Refuge d = this.RefugeView.getSelectionModel().getSelectedItem();
        Back_AjoutRefugePageController controllerR = new Back_AjoutRefugePageController();
        changeScene2(event, "../GUI/Back_AjoutRefugePage.fxml", "Modifier le refuge", d, controllerR);
    }

    @FXML
    private void SupprimerRefuge(ActionEvent event) throws SQLException, IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de supprimer le refuge ?");
        Optional<ButtonType> ok = alert.showAndWait();
        if (ok.get() == ButtonType.OK) {
            Refuge d = this.RefugeView.getSelectionModel().getSelectedItem();
            RefugeService cd = new RefugeService();
            cd.SupprimerRefuge(d.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Back_AdoptionPage.fxml"));
            Parent root = loader.load();
            AnimalView.getScene().setRoot(root);
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root = loader.load();
        AnimalView.getScene().setRoot(root);
    }

    @FXML
    private void Back_admin(ActionEvent event) throws IOException {
        Parent adoption = FXMLLoader.load((getClass().getResource("/GUI/adminLayout.fxml")));
        Scene CentreDPage = new Scene(adoption);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(CentreDPage);
        window.show();
    }

    @FXML
    private void ChangerEtat(ActionEvent event) throws SQLException, IOException {
        Animal a = AnimalView.getSelectionModel().getSelectedItem();
        AnimalService as = new AnimalService();
        as.ChangerEtat(a.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Back_AdoptionPage.fxml"));
        Parent root = loader.load();
        AnimalView.getScene().setRoot(root);

    }

}
