/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.ProduitService;
import Technique.DataSource;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author bn-sk
 */
public class VentesController implements Initializable {
private Connection con= DataSource.getInstance().getConnexion();
    @FXML
    private Hyperlink deconnexion;
    @FXML
    private Hyperlink profil;
    @FXML
    private Label bienvenue;
    @FXML
    private Button panier;
    @FXML
    private Button location;
    
    public String pathImage="C:\\Users\\bn-sk\\Documents\\NetBeansProjects\\Java\\SprintJava\\src\\Ressources\\Images\\";
    public String pathButton="C:\\Users\\bn-sk\\Documents\\NetBeansProjects\\Java\\SprintJava\\src\\Ressources\\Images\\add-square-button.png";
    @FXML
    private AnchorPane an;
    
    /**
     * Initializes the controller class.
     */
    enum animal{chien};
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        AffichageProduit();
    } catch (SQLException ex) {
        Logger.getLogger(VentesController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    

    public void AffichageProduit() throws SQLException
    {
              
              FlowPane f = new FlowPane();
              ProduitService ps = new ProduitService();
              ResultSet rs= ps.selectProduits();
              
              
              while(rs.next())
              {
                  
                  AnchorPane anchorpane1 = new AnchorPane();
                  anchorpane1.setPrefHeight(250.0);
                  anchorpane1.setPrefWidth(504.0);
                  Separator separtor = new Separator();
                  VBox vbox = new VBox();
                  
                  
                 File fileimage=new File(pathImage+rs.getString(7));
                 Image preimage=new Image(fileimage.toURI().toString());
                 ImageView image=new ImageView(preimage);
                 image.setLayoutX(0);
                 image.setLayoutY(0);
                 image.setFitWidth(182);
                 image.setFitHeight(203);
                 
                 Label libelle=new Label(rs.getString(3));
                 libelle.setLayoutX(208);
                 libelle.setLayoutY(58);
                 
                 
                 Label description=new Label(rs.getString(4));
                 libelle.setLayoutX(193);
                 libelle.setLayoutY(90);
                 
                 
                 Label prix=new Label(String.valueOf(rs.getInt(5))+" DT");
                 libelle.setLayoutX(508);
                 libelle.setLayoutY(51);
                 
                 
                 File file=new File(pathButton);
                 Image preimagebutton=new Image(file.toURI().toString());
                 ImageView imagebutton=new ImageView(preimagebutton);
                 
                 Button ajoutButton=new Button();
                 ajoutButton.setLayoutX(493);
                 ajoutButton.setLayoutY(85);
                 ajoutButton.setPrefWidth(44);
                 ajoutButton.setPrefHeight(31);
                 ajoutButton.setGraphic(imagebutton);
                 
                 vbox.setSpacing(30.0);
                  separtor.setLayoutX(3.0);
                  separtor.setLayoutY(55.0);
                  separtor.setPrefHeight(4.0);
                  separtor.setPrefWidth(1213.0);
                 
                  anchorpane1.getChildren().addAll(image,libelle,description,prix,ajoutButton);
                  vbox.getChildren().add(anchorpane1);
                  f.getChildren().addAll(vbox);
  
              }
             an.getChildren().addAll(f);

            
    }
}
