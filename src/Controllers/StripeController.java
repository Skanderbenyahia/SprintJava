/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.PanierController.total;
import Entity.PaymentOrder;
import Entity.Session;
import com.jfoenix.controls.JFXTextField;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import Entity.User;
import Services.ProduitService;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class StripeController implements Initializable {

    public String pathButtonback = "C:\\Users\\bn-sk\\Desktop\\GitGroup\\SprintJava\\src\\Ressources\\Images\\back.png";
    
    public static User client = null;
    @FXML
    private JFXTextField name, cc, cvv, exp_y, exp_m;
    @FXML
    private Button retour;

    public void initialize(URL url, ResourceBundle rb) {
        
            File fileimage = new File(pathButtonback);
            Image preimage = new Image(fileimage.toURI().toString());
            ImageView image = new ImageView(preimage);
            image.setLayoutX(120);
            image.setLayoutY(37);
            image.setFitWidth(150);
            image.setFitHeight(180);
            retour.setGraphic(image);
    }

    @FXML
    private void createPayment(ActionEvent event) {

        PaymentOrder payment = new PaymentOrder(cc.getText(), cvv.getText(), exp_m.getText(), exp_y.getText(), total);
        try {
            Charge charge = payment.createCharge("sk_test_iPtaJ5udtTjyX6WXdtUjomIp", payment.getAmmount(), payment.getName(), payment.getCardnumber(), payment.getExp_month(), payment.getExp_year(), payment.getCvv());
            System.out.println("charge : " + charge.getStatus());
            if (charge.getStatus().equalsIgnoreCase("succeeded")) {
                ProduitService ps = new ProduitService();
                ps.CommanderPanier(Session.getCurrentSession());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Commande éffectué avec succées !");
                alert.showAndWait();

            }

        } catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException | APIException ex) {
            if (ex.getMessage() != null || !(ex.getMessage().equals(""))) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Commande Erroné ! Veuillez vérifier vos coordonées bancaires");
                alert.showAndWait();

            }
        } catch (SQLException ex) {
            Logger.getLogger(StripeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Panier.fxml"));
        Parent root = loader.load();
        retour.getScene().setRoot(root); 
    }
}
