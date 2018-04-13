/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

;
import Entity.Session;
import Entity.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bn-sk
 */


public class StartController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton login;
    @FXML
    private Hyperlink forgotpass;
    @FXML
    private JFXButton inscription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/inscription.fxml"));
        Parent root = loader.load();
        username.getScene().setRoot(root);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        int roles = 0;
        UserService us = new UserService();
        User u = new User();
        u.setUsername(username.getText());
        u.setPassword(password.getText());
        roles = us.VerificationUtilisateur(u,password.getText());
        Stage stage;

        if (roles == 1) {

            //  Session.start(u.getId());
            System.out.println("user id is : " + u.getId());

            Session.start(u.getId());
            System.out.println(Session.getCurrentSession());
            us.loggin(u, Session.getCurrentSession());
            System.out.println("Role from login! : member");

            Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
            Scene scene = new Scene(page2);
            stage = (Stage) username.getScene().getWindow();
            stage.setScene(scene);
            stage.setUserData(u);
            stage.show();

            /* */
        }
        if (roles == 0) {
            System.out.println("user id is : " + u.getId());

            Session.start(u.getId());
            System.out.println(Session.getCurrentSession());
            us.loggin(u, Session.getCurrentSession());
            System.out.println("Role from login! : admin");

            Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/adminLayout.fxml"));
            Scene scene = new Scene(page2);
            stage = (Stage) username.getScene().getWindow();
            stage.setScene(scene);
            stage.setUserData(u);
            stage.show();

        }

        if (roles != 0 && roles != 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not login");
            alert.setContentText("please check your credentials!");
            alert.showAndWait();
        }
    }

}
