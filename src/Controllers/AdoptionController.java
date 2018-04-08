/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Animal;
import Entity.Session;
import Services.AnimalService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Skeez
 */
public class AdoptionController implements Initializable {

    public String pathImage="C:\\Users\\Skeez\\Desktop\\Git\\SprintJava\\src\\Ressources\\Images";
    public String pathButton="C:\\Users\\Skeez\\Desktop\\Git\\SprintJava\\src\\Ressources\\Images\\add-square-button.png";
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane tabanimal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AffichageAdoption();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void AffichageAdoption() throws SQLException
    {
              
              FlowPane f = new FlowPane();
              AnimalService as = new AnimalService();
              List<Animal> rs= as.selectAnimals();
              
              
                        
                    
              for (Animal r : rs)
              {
                  
                  AnchorPane anchorpane1 = new AnchorPane();
                  
                  anchorpane1.setPrefHeight(250.0);
                  anchorpane1.setPrefWidth(504.0);
                  Separator separtor = new Separator();
                  VBox vbox = new VBox();
                  
                  
                 File fileimage=new File(pathImage+r.getImage());
                 Image preimage=new Image(fileimage.toURI().toString());
                 ImageView image=new ImageView(preimage);
                 image.setLayoutX(240);
                 image.setLayoutY(37);
                 image.setFitWidth(150);
                 image.setFitHeight(180);
                 
                 Label libelle=new Label(r.getNom());
                 Font font = new Font("Arial",26);
                 libelle.setStyle("-fx-font-weight: bold");
                 libelle.setFont(font);
                 libelle.setTextFill(Color.web("cfbfa6"));
                 libelle.setLayoutX(410);
                 libelle.setLayoutY(48);
                 
                 Label description=new Label("Description :"+r.getDescription());
                 Font font2 = new Font("Arial",18);
                 description.setFont(font2);
                 description.setLayoutX(410);
                 description.setLayoutY(90);
                 
                 Label demande=new Label(String.valueOf(r.getDemande())+" Demandes");
                 demande.setTextFill(Color.web("f67777"));
                 Font font3 = new Font("Arial",16);
                 demande.setStyle("-fx-font-weight: bold");
                 demande.setFont(font3);
                 demande.setLayoutX(410);
                 demande.setLayoutY(190);
                 
                 File file=new File(pathButton);
                 Image preimagebutton=new Image(file.toURI().toString());
                 ImageView imagebutton=new ImageView(preimagebutton);
                 
                 Button ajoutButton=new Button("Adopter");
                 ajoutButton.setLayoutX(520);
                 ajoutButton.setLayoutY(170);
                 ajoutButton.setPrefWidth(30);
                 ajoutButton.setPrefHeight(20);
                 ajoutButton.setGraphic(imagebutton);
                 
                  vbox.setSpacing(30.0);
                  separtor.setLayoutX(3.0);
                  separtor.setLayoutY(55.0);
                  separtor.setPrefHeight(4.0);
                  separtor.setPrefWidth(1213.0);
                 
                  anchorpane1.getChildren().addAll(image,libelle,description,demande,ajoutButton);
                  vbox.getChildren().add(anchorpane1);
                  f.getChildren().addAll(vbox);
                  
                  
                  Animal p = new Animal(r.getId(), r.getNom(), r.getEspece(), r.getRace(), r.getAge(), r.getSexe(), r.getTaille(), r.getRegion(), r.getDescription(), r.getEtat(), r.getImage(),r.getDemande());
                  ajoutButton.setOnAction(e->{
                          try {
                              as.increment(p);
                              FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Formulaire_Adoption.fxml"));
                              Parent root = null;
                              try {
                                  root = loader.load();
                              } catch (IOException ex) {
                                  Logger.getLogger(AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              tabanimal.getScene().setRoot(root);
                              
                          } catch (SQLException ex) {
                              Logger.getLogger(AdoptionController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                  
                  });
                  }
                                
                
              
   
             tabanimal.getChildren().add(f);
             
             

            
    }
}
