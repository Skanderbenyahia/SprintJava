/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.CentreDressage;
import Services.CentreDressageService;
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
 * @author jabou
 */
public class Back_AjouterCDPageController implements Initializable,ControllerClass {

    @FXML
    private Label titre;
    @FXML
    private JFXTextField libelle;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField lan;
    @FXML
    private JFXTextField lat;
    @FXML
    private JFXTextField image;
    
    private CentreDressage d;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCentreDressage(ActionEvent event) throws SQLException, IOException {
        
        CentreDressageService cd= new CentreDressageService();
        if(d!=null)
        {
            updateCentre();
            cd.ModifierCentreDressage(d);
        }
        else
        {
         CentreDressage d=new CentreDressage();
        d.setLibelle(libelle.getText());
        d.setAdresse(adresse.getText());
        d.setDescription(description.getText());
        d.setImage(image.getText());
        d.setTel(Integer.parseInt(tel.getText()));
        d.setLat(Integer.parseInt(lat.getText()));
        d.setLng(Integer.parseInt(lan.getText()));
        cd.AjouterCentreDressage(d);
        }
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_ServicePage.fxml"));
        Parent root =loader.load();
        tel.getScene().setRoot(root);
    }

    @Override
    public void preloadData(CentreDressage d) {
        this.d=d;
        this.libelle.setText(d.getLibelle());
        this.adresse.setText(d.getAdresse());
        this.tel.setText(Integer.toString(d.getTel()));
        this.lat.setText(Double.toString(d.getLat()));
        this.lan.setText(Double.toString(d.getLng()));
        this.description.setText(d.getDescription());
        this.image.setText(d.getImage());
        this.titre.setText("Modifier Un Centre de dressage");
    }
    
     public void updateCentre()
    {
    d.setLibelle(libelle.getText());
    d.setAdresse(adresse.getText());
    d.setTel(Integer.parseInt(tel.getText()));
    d.setLat(Integer.parseInt(lat.getText()));
    d.setLng(Integer.parseInt(lan.getText()));
    d.setDescription(description.getText());
    d.setImage(image.getText());
    }

    @Override
    public void preloadData(Categorie c) {
    }
    
}
