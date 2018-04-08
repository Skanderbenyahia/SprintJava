/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Animal;
import Entity.Refuge;
import Services.AnimalService;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Skeez
 */
public class Back_AjoutAnimalPageController implements Initializable,ControllerClassAdoption {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField race;
    @FXML
    private JFXTextField sexe;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField region;
    @FXML
    private JFXTextField espece;
    @FXML
    private JFXTextField image;
    @FXML
    private Label titre;
    @FXML
    private JFXTextField refuge;
    @FXML
    private JFXTextField taille;
    @FXML
    private JFXTextArea description;
    
    private Animal a;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterAnimal(ActionEvent event) throws SQLException, IOException {
        
        AnimalService fs= new AnimalService();
        if(a!=null)
        {
            updateCentre();
            fs.ModifierAnimal(a);
        }
        else
        {
       
          String etat="nonadopte";
        int demande= 0;
        Animal f=new Animal(nom.getText(), espece.getText(), race.getText(), age.getText(), sexe.getText(), taille.getText(), region.getText(), description.getText(),etat, image.getText(), demande );
        fs.AjouterAnimal(f);
        }
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AdoptionPage.fxml"));
        Parent root =loader.load();
        nom.getScene().setRoot(root);
    }
    
    @Override
    public void preloadData(Animal a) {
        this.a=a;
        this.nom.setText(a.getNom());
        this.espece.setText(a.getEspece());
        this.race.setText(a.getRace());
        this.age.setText(a.getAge());
        this.taille.setText(a.getTaille());
        this.image.setText(a.getImage());
        this.region.setText(a.getRegion());
        this.sexe.setText(a.getSexe());
        this.description.setText(a.getDescription());
        this.titre.setText("Modifier Un Animal");
    }
    
     public void updateCentre()
    {
    a.setNom(nom.getText());
    a.setRace(race.getText());
    a.setEspece(espece.getText());
    a.setAge(age.getText());
    a.setTaille(taille.getText());
    a.setImage(image.getText());
    a.setRegion(region.getText());
    a.setSexe(sexe.getText());
    a.setDescription(description.getText());
 
    }

    @Override
    public void preloadData(Refuge c) {
    }
}
