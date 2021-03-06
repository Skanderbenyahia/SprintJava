package Controllers;



import Entity.Session;
import Entity.User;
import Services.UserService;
import Technique.DataSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bn-sk
 */
public class HomeController implements Initializable {

    private Connection con = DataSource.getInstance().getConnexion();
    private Statement statement;
    @FXML
    private Button panier;
    @FXML
    private Label bienvenue;
    @FXML
    private Label reservations;
    @FXML
    private Button ReservationPage;
    private boolean petsitter=false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List currentUser = new ArrayList();
        String req = "select nom,prenom,roles from user where id=(?)";
        try {
            PreparedStatement prepared = con.prepareStatement(req);
            prepared.setInt(1, Session.getCurrentSession());
            ResultSet resultat = prepared.executeQuery();

            while (resultat.next()) {
                String nom = resultat.getString(1);
                String prenom = resultat.getString(2);
                String roles=resultat.getString(3);
                currentUser.add(nom);
                currentUser.add(prenom);
                
                if(roles.equals("ROLE_PETSITTER"))
                    {
                         petsitter=true;
                    }
            }
            
               if(petsitter==false)
            {
              reservations.setOpacity(0);
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        bienvenue.setText("Connecté en tant que : " + currentUser.get(0) + " " + currentUser.get(1));
    }

    @FXML
    private void afficherAcceuil(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/home.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void afficherAdoption(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Adoption.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void afficherVentes(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Ventes.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);

    }

    @FXML
    private void afficherServices(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Service.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);

    }

    @FXML
    private void afficherSoins(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/FRONT_HygienePage.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void afficherEvents(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Front_events.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    private void affichePanier(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Panier.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void PanierPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Panier.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void ReservationPage(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListeReservationPetsitter.fxml"));
        Parent root=loader.load();
        bienvenue.getScene().setRoot(root);
    }

    @FXML
    private void afficherWishList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Wishlist.fxml"));
        Parent root = loader.load();
        bienvenue.getScene().setRoot(root);
    }

}
