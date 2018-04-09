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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class StripeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static User client = null;
    @FXML
    private JFXTextField name, cc, cvv, exp_y, exp_m;
    

    public void initialize(URL url, ResourceBundle rb) {
        
    }

    

    @FXML
    private void createPayment(ActionEvent event) {
        
        PaymentOrder payment = new PaymentOrder(cc.getText(), cvv.getText(), exp_m.getText(), exp_y.getText(),total);
            try {
                Charge charge = payment.createCharge("sk_test_iPtaJ5udtTjyX6WXdtUjomIp", payment.getAmmount(), payment.getName(), payment.getCardnumber(), payment.getExp_month(), payment.getExp_year(), payment.getCvv());
                System.out.println("charge : " + charge.getStatus());
                if (charge.getStatus().equalsIgnoreCase("succeeded")) {
                    System.out.println("success");
                    ProduitService ps=new ProduitService();
                    ps.CommanderPanier(Session.getCurrentSession());
                    
                }

            } catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException | APIException ex) {
                if (ex.getMessage() != null || !(ex.getMessage().equals(""))) {
                    System.out.println("error");

                }
            } catch (SQLException ex) {
                Logger.getLogger(StripeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
