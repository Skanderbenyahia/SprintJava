/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Entity.Animal;
import Entity.Refuge;
import Services.RefugeService;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Integer.parseInt;
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

/**
 * FXML Controller class
 *
 * @author Skeez
 */
public class Back_AjoutRefugePageController implements Initializable,ControllerClassAdoption {

    @FXML
    private JFXTextField refuge_libelle;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField refuge_num;
    @FXML
    private JFXTextField refuge_adresse;
    @FXML
    private JFXTextField refuge_image;
    @FXML
    private Label titre;
    @FXML
    private JFXTextField refuge_region;
    @FXML
    private JFXTextArea refuge_description;
    
    private Refuge a;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterRefuge(ActionEvent event) throws SQLException, IOException {
       // if(ValidateFields()){
           RefugeService fs= new RefugeService();
        if(a!=null)
        {
            ModifierR();
            fs.ModifierRefuge(a);
        }
        else
        {
       
          int num = Integer.parseInt(refuge_num.getText());
        
        Refuge f=new Refuge(refuge_libelle.getText(), num ,email.getText(), refuge_region.getText(), refuge_adresse.getText(), refuge_description.getText(),refuge_image.getText());
        fs.ajouterRefuge(f);
        }
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AdoptionPage.fxml"));
        Parent root =loader.load();
        refuge_libelle.getScene().setRoot(root);
    //}
    } 
    
    @Override
    public void preloadData(Refuge a) {
       
        
        this.a=a;
        this.refuge_libelle.setText(a.getLibelle());
        this.email.setText(a.getEmail());
        this.refuge_adresse.setText(a.getAdresse());
        this.refuge_num.setText(String.valueOf(a.getNum()));
        this.refuge_description.setText(a.getDescription());
        this.refuge_region.setText(a.getRegion());
        this.refuge_image.setText(a.getImage());
        this.titre.setText("Modifier Un Refuge");
    }
    
     public void ModifierR()
    {
    a.setLibelle(refuge_libelle.getText());
    a.setAdresse(refuge_adresse.getText());
    a.setNum(Integer.parseInt(refuge_num.getText()));
    a.setRegion(refuge_region.getText());
    a.setEmail(email.getText());
    a.setDescription(refuge_description.getText());
    a.setImage(refuge_image.getText());
 
    }

    @Override
    public void preloadData(Animal c) {
    }
    
    /* private boolean ValidateFields()
     {
         if(refuge_libelle.getText().isEmpty() | refuge_adresse.getText().isEmpty() | refuge_num.getText().isEmpty() | refuge_region.getText().isEmpty() | email.getText().isEmpty() | refuge_description.getText().isEmpty() | refuge_image.getText().isEmpty())
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tout les champs !");
                alert.showAndWait();
         return false;
         }
        if((Integer.parseInt(refuge_num.getText()))<0)
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez Vérifier le numéro de téléphone !");
                alert.showAndWait();
         return false;
         }
        return true;
     
     } */

    @FXML
    private void back_admin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Back_AdoptionPage.fxml"));
        Parent root=loader.load();
        refuge_num.getScene().setRoot(root);

    }
    }
    

