/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mimouna
 */
public class AjouterCentreToilettageController implements Initializable {

    centreToilettageServices centreIS = new centreToilettageServices();

    @FXML
    private JFXButton AjouterCentreTBT;
    @FXML
    private JFXTextField libelleTXT;
    @FXML
    private JFXTextField adresseTXT;
    @FXML
    private JFXTextField telephoneTXT;
    @FXML
    private JFXTextField descriptionTXT;
    @FXML
    private JFXButton loadBTN;
    @FXML
    private ImageView imageviewcentre;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    
     String path="";
     Stage mainStage;

    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        telephoneTXT.setText("0");
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        
    }
           
           
           
    
    @FXML
    private void AjouterCentreToilettage(ActionEvent event) throws SQLException, IOException 
    {
        centreToilettage centre = new centreToilettage(libelleTXT.getText(), adresseTXT.getText(), Integer.parseInt(telephoneTXT.getText()), descriptionTXT.getText(),path);
      
        /* ken les champs ferghyn , naffichi message  */ 

        boolean a=true;
     if (libelleTXT.getText().length()==0 )
     {
         label1.setVisible(true);
         a=false;
     }
        if (adresseTXT.getText().length()==0 )
     {
         label2.setVisible(true);
          a=false;
     }   
       if (telephoneTXT.getText().length()==0 || telephoneTXT.getText().equals("0") )
     {
         label3.setVisible(true);
          a=false;
     }
        if (descriptionTXT.getText().length()==0 )
     {
         label4.setVisible(true);
          a=false;
     }  
        if (path=="" )
     {
         label5.setVisible(true);
          a=false;
     } 
        
        
        /** besh na7iwhom */ 
        
        int b=0,c=0,d=0,e=0,f=0,som=0;
      if (libelleTXT.getText().length()!=0 )
     {
         label1.setVisible(false);
         b=1;
     }
        if (adresseTXT.getText().length()!=0 )
     {
         label2.setVisible(false);
      c=1;
     }   
       if (telephoneTXT.getText().length()!=0  )
     {
         label3.setVisible(false);
        d=1;
     }
        if (descriptionTXT.getText().length()!=0 )
     {
         label4.setVisible(false);
         e=1;
     }  
        if (path!="" )
     {
         label5.setVisible(false);
      f=1;
     }
        som=b+c+d+e+f;
        
   
           if (a==true && som==5) 
           {
           centreIS.AjouterCentreT(centre);
                        
          Parent homePage = FXMLLoader.load(getClass().getResource("../GUI/Back_HygienePage.fxml"));
        
          Scene homePage_scene=new Scene(homePage);
        
          Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
          app_stage.setScene(homePage_scene);
        
          app_stage.show();
          
           }
    }

    @FXML
    /* charger une image */ 
    private void load(ActionEvent event) throws MalformedURLException 
    {
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
    
    
    
}
    

