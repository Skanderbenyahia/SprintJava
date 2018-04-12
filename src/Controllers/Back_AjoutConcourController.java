/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Concour;
import Entity.Conseils;
import Services.ServiceConcour;
import Services.ServiceConseils;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class Back_AjoutConcourController implements Initializable,event_ControllorClass {

    @FXML
    private Label titre;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField nbParticipants;
    @FXML
    private DatePicker dateConcour;
    Concour c;
    LocalDate dateC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void addConcour(ActionEvent event) throws SQLException, IOException {
        ServiceConcour serviceC = new ServiceConcour();
       if( ValidateFieldsconcours())
        {
        if(c!=null)
        {
            updateConcouur();
            serviceC.modifierConcour(c);
        }
        else 
        {
        String descrp = description.getText();
        int nbPar = Integer.parseInt(nbParticipants.getText());
        Date dateC = java.sql.Date.valueOf(dateConcour.getValue());
        Concour con = new Concour(descrp, nbPar, dateC);
        serviceC.AjouterConcour(con);     
        }
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_eventPage.fxml"));
         Parent root =loader.load();
         description.getScene().setRoot(root);  
       }
    }
    
    private boolean ValidateFieldsconcours()
     {
         if(description.getText().isEmpty() | nbParticipants.getText().isEmpty())
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tout les champs !");
                alert.showAndWait();
         return false;
         }
         
          if((Integer.parseInt(nbParticipants.getText()))<0)
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez Vérifier le numéro des participants !");
                alert.showAndWait();
         return false;
         }
        return true;
     
     }

    @Override
    public void preloadData(Concour c) 
    {
        this.c=c;
        this.description.setText(c.getDescription());
        this.nbParticipants.setText(Integer.toString(c.getnbredeplaces()));
        this.dateConcour.setValue(c.getDate().toLocalDate());
        this.titre.setText("Modifier Un Concour");
    }
    
     public void updateConcouur()
    {
      c.setDescription(description.getText());
      c.setnbredeplaces(Integer.parseInt(nbParticipants.getText()));
      //c.setdate(dateConcour.getValue());
    }

    @Override
    public void preloadData(Conseils c) {
    }
}
