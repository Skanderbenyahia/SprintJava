/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.CentreDressage;
import Entity.centreToilettage;
import Services.centreToilettageServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class AjoutCentreToiController implements Initializable,SoinControllerClass {

    @FXML
    private JFXTextField lib;
    @FXML
    private JFXTextField adresse_t_field;
    @FXML
    private JFXTextField tel_T_field;
    @FXML
    private JFXTextField des_t_field;
    @FXML
    private JFXButton loadBTN;
    @FXML
    private ImageView imageviewcentre;
    String path="";
    private centreToilettage c;
    @FXML
    private Label titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void load(ActionEvent event) throws MalformedURLException {
         FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(imageviewcentre.getScene().getWindow());
        if (file != null) 
        {
            Image img = new Image(file.toURI().toString(), 388 , 309, true, true);
            imageviewcentre.imageProperty().unbind();
            imageviewcentre.setImage(img);
            
        }
        path = file.toURI().toURL().toString();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_SoinPage.fxml"));
        Parent root =loader.load();
        loadBTN.getScene().setRoot(root);
    }

    @Override
    public void preloadData(centreToilettage c) {
  this.c=c;
        this.lib.setText(c.getLibelle());
        this.adresse_t_field.setText(c.getAdresse());
        this.des_t_field.setText(c.getDescription());
        this.tel_T_field.setText(Integer.toString(c.getTel()));
       
        this.titre.setText("Modifier Un Centre de toilettage"); 
    }
    
     public void updateCentre()
    {
        c.setLibelle(lib.getText());
        c.setAdresse(adresse_t_field.getText());
        c.setDescription(des_t_field.getText());
        c.setTel(Integer.parseInt(tel_T_field.getText()));

    }

    @FXML
    private void AjouterCentreT(ActionEvent event) throws SQLException, IOException {
        centreToilettageServices cs= new centreToilettageServices();
       if(ValidateFields())
       {
        if(c!=null)
        {
        updateCentre();
        cs.modifierCentreT(c);
        }
        else 
        {
            centreToilettage c=new centreToilettage();
            c.setLibelle(lib.getText());
            c.setAdresse(adresse_t_field.getText());
            c.setDescription(des_t_field.getText());
            c.setTel(Integer.parseInt(tel_T_field.getText()));
            c.setImage(path);
            cs.AjouterCentreT(c);
        }
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_SoinPage.fxml"));
        Parent root =loader.load();
        lib.getScene().setRoot(root);
    }
    }
    
     private boolean ValidateFields()
     {
         if(lib.getText().isEmpty() | adresse_t_field.getText().isEmpty() | des_t_field.getText().isEmpty() 
                 | path.isEmpty() |tel_T_field.getText().isEmpty())
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tout les champs !");
                alert.showAndWait();
         return false;
         }
         
          if((Integer.parseInt(tel_T_field.getText()))<0)
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez Vérifier le numéro de téléphone !");
                alert.showAndWait();
         return false;
         }
        return true;
     
     }
}
