/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Concour;
import Entity.Conseils;
import Services.ServiceConseils;
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
public class Back_AjoutConseilController implements Initializable,event_ControllorClass {

    @FXML
    private Label titre_conseil;
    @FXML
    private JFXTextField titre_field;
    @FXML
    private JFXTextField text_field;
    @FXML
    private JFXTextField type_field;
    Conseils c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void Back_admin(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_eventPage.fxml"));
         Parent root =loader.load();
         titre_conseil.getScene().setRoot(root);
    }

    @FXML
    private void AjouterConseil(ActionEvent event) throws SQLException, IOException {
        ServiceConseils cs= new ServiceConseils();
        if(c!=null)
        {
             updateConseil();
             cs.modifierConseils(c);
        }
        else 
        {
        String titre=titre_field.getText();
        String text=text_field.getText();
        String type=type_field.getText();
        Conseils con=new Conseils(titre,text,type);
        cs.AjouterConseils(con);

        }
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_eventPage.fxml"));
        Parent root =loader.load();
        titre_field.getScene().setRoot(root);
    }

    @Override
    public void preloadData(Concour c) {
    }

    @Override
    public void preloadData(Conseils c) {
        this.c=c;
       this.text_field.setText(c.getTexte());
       this.titre_conseil.setText(c.getTitre());
       this.type_field.setText(c.getType());
       this.titre_conseil.setText("Modifier Un Conseil");
    }
     public void updateConseil()
    {
     c.setTexte(text_field.getText());
     c.setTitre(titre_field.getText());
     c.setType(type_field.getText());
    }
    
}
