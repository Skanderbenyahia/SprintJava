/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Session;
import Entity.User;
import Entity.centreToilettage;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import Services.centreToilettageServices;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.text.Document;
import sun.plugin2.message.transport.Transport;

/**
 * FXML Controller class
 *
 * @author Mimouna
 */
public class Back_HygienePageController implements Initializable {

    private List<User> ListeV ;
        
    private User selectedUser ; 
	    
    private UserService userSR = new UserService();
    @FXML
    private JFXListView<Label> listeViewVeterinaire;
    @FXML
    private JFXButton mail;
    @FXML
    private TextField destinataire;
    @FXML
    private TextField subject;
    @FXML
    private TextArea body;
    @FXML
    private Label destinataireLabel;
    @FXML
    private Label sujetLabel;
    @FXML
    private Label contenueLabel;
    @FXML
    private JFXButton st;
    @FXML
    private JFXButton modifiercentre;
   @FXML
    private JFXListView<Label> listeViewCentreT;
    @FXML
    private JFXButton supprimercentre;
    @FXML
    private Label adresse_lab;
    @FXML
    private Label descrition_lab;
    @FXML
    private Label libelle_la;
    @FXML
    private Label telephone_lab;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField libelle;
    @FXML
    private JFXTextField telephone;
    @FXML
    private ImageView imageview;
    @FXML
    private JFXButton image;
    
    
    String path="";
    
    private List<centreToilettage> listeCentreT ; 
    
    public centreToilettage selectedcentre;
    centreToilettageServices centreSRV ;
    @FXML
    private JFXButton pdfBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            this.centreSRV = new centreToilettageServices();

        mail.setVisible(false );
           destinataire.setVisible(false);
           body.setVisible(false);
           subject.setVisible(false);
           destinataireLabel.setVisible(false);
           sujetLabel.setVisible(false);
           contenueLabel.setVisible(false);
           
           
           
           modifiercentre.setVisible(false);
           supprimercentre.setVisible(false);
           adresse.setVisible(false);
           description.setVisible(false);
           libelle.setVisible(false);
           telephone.setVisible(false);
           image.setVisible(false);
           adresse_lab.setVisible(false);
           descrition_lab.setVisible(false);
           libelle_la.setVisible(false);
           telephone_lab.setVisible(false);
           imageview.setVisible(false);
          
           

      
<<<<<<< HEAD
        /*try {
=======
        try {
>>>>>>> 0b0018725b00f31ff1dac26b9e2a6c2361723976
            ListeV = userSR.listeVeterinaire();
             listeCentreT = centreSRV.afficherCentreT();
            centreSRV.afficherCentreT().forEach(e -> System.out.println(e));
        } catch (SQLException ex) {
            Logger.getLogger(Back_HygienePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
<<<<<<< HEAD
           */
=======
           
>>>>>>> 0b0018725b00f31ff1dac26b9e2a6c2361723976
        try {
            afficherLaListeDesCentreToilettage();
        } catch (SQLException ex) {
            Logger.getLogger(Back_HygienePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
           liste(); 
    }

 
    public void liste()
    {
               listeViewVeterinaire.getItems().clear();
        for (User c : ListeV) 
           {
            Label lbl = new Label
            (
                    "username :  "+c.getUsername()+ "\n"+
                    "nom :  "+c.getNom()+ "\n"+
                    "prenom :  "+c.getPrenom() + "\n"+
                    "adresse :  "+c.getAdresse()+ "\n"+
                    "telephone :  "+c.getTel()+ "\n" + 
                     "email :  "+c.getEmail()+ "\n"
            );
                lbl.setPrefSize(500, 100);
                listeViewVeterinaire.getItems().add(lbl);
            
            }
     }
       

    @FXML
    private void selectioneer(MouseEvent event) {
         mail.setVisible(true );
           destinataire.setVisible(true);
           body.setVisible(true);
           subject.setVisible(true);
           destinataireLabel.setVisible(true);
           sujetLabel.setVisible(true);
           contenueLabel.setVisible(true);
           
            int index = listeViewVeterinaire.getSelectionModel().getSelectedIndex();
            selectedUser = ListeV.get(index);
            destinataire.setText(selectedUser.getEmail());
           //System.out.println("mimiouna"+selectedUser.getEmail());
           
            
        //System.out.println(id);
    }

    @FXML
    private void envoyer_mail(ActionEvent event)     
    {
                                                               
     /*   Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.prot", "465");
        props.put("mail.smtp.starttls.enable", "true");
	Session session = Session.getDefaultInstance(props,
	new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() 
        {

	return new PasswordAuthentication("emna.mimouna@esprit.tn","emna153");
        //props.put("mail.smtp.starttls.enable", "true");
        }
        }
       );
       try {
        System.err.println(mail.getText());
 
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("safouane.sma@esprit.tn "));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(selectedUser.getEmail()));
        message.setSubject(subject.getText());
        message.setText(body.getText());
         Transport.send(message);

         } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }*/
    }

    @FXML
    private void stat(ActionEvent event) 
   {
        
   
        UserService a =new UserService();
        Scene scene = new Scene(new Group());
        Stage mainStage = new Stage();
        mainStage.setWidth(500);
        mainStage.setHeight(500);
 
        List<User> liste = new ArrayList<User>();
        //a.getStat() hedhi fergha tekhou feha wini requete eli tjib menha..?
<<<<<<< HEAD
        //liste=a.getStat();
=======
        liste=a.getStat();
>>>>>>> 0b0018725b00f31ff1dac26b9e2a6c2361723976
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();
        for (int i = 0; i < liste.size(); i++) 
        {
<<<<<<< HEAD
           // pieChartData.add(new PieChart.Data(liste.get(i).getUsername(),liste.get(i).getStat()));
=======
            pieChartData.add(new PieChart.Data(liste.get(i).getUsername(),liste.get(i).getStat()));
>>>>>>> 0b0018725b00f31ff1dac26b9e2a6c2361723976

        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Vetrinaire et reservation");

        ((Group) scene.getRoot()).getChildren().add(chart);
        mainStage.setScene(scene);
        mainStage.show();
    }

   @FXML
    private void interface_ajout_centreT(ActionEvent event) throws IOException
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("../GUI/ajouterCentreToilettage.fxml"));
        
       Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }
    
    private void afficherLaListeDesCentreToilettage () throws SQLException
    {
                listeViewCentreT.getItems().clear();
        for (centreToilettage c : listeCentreT) 
           {
            Label lbl = new Label
            (
                    "adresse :  "+c.getAdresse() + "\n"+
                    "description :  "+c.getDescription() + "\n"+
                    "libelle :  "+c.getLibelle() + "\n"+
                    "telephone :  "+c.getTel()+ "\n"
            );
            
            String img=c.getImage();
            Image image=new Image(img,150,100,true,true);
            
            lbl.setPrefSize(500, 100);
            
                
                ImageView imageView = new ImageView();
                imageView.imageProperty().unbind();
                imageView.setImage(image);
                imageView.setFitWidth(150);
                imageView.setFitHeight(100);
                listeViewCentreT.getItems().add(lbl);
                lbl.setGraphic(imageView);
            }
    }

 /* gestion centre de toilettages */ 

    @FXML
    private void modifcentre(ActionEvent event) throws SQLException, IOException 
    {
        centreToilettage c = new centreToilettage( libelle.getText(), adresse.getText(),
                Integer.parseInt(telephone.getText()),
                description.getText() ,path);
        centreSRV.modifierCentreT(c, selectedcentre.getId());
        

      Parent homePage = FXMLLoader.load(getClass().getResource("../GUI/Back_HygienePage.fxml"));
        
       Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
        
      
    }

    @FXML
    private void suppcentre(ActionEvent event) throws SQLException, IOException {
        centreSRV.supprimerCentreT(selectedcentre.getId());
        
        Parent homePage = FXMLLoader.load(getClass().getResource("../GUI/ListeCentreToilettage_Admin.fxml"));
        
       Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
        
    }

    @FXML
    private void selectionner(MouseEvent event)
    {
        modifiercentre.setVisible(true);
        supprimercentre.setVisible(true);
        adresse.setVisible(true);
        description.setVisible(true);
        libelle.setVisible(true);
        telephone.setVisible(true);
        image.setVisible(true);
        adresse_lab.setVisible(true);
        descrition_lab.setVisible(true);
        libelle_la.setVisible(true);
        telephone_lab.setVisible(true);
        imageview.setVisible(true);
        int index = listeViewCentreT.getSelectionModel().getSelectedIndex();
        selectedcentre = listeCentreT.get(index);
       
        libelle.setText(selectedcentre.getLibelle());
        adresse.setText(selectedcentre.getAdresse());
        telephone.setText("" +selectedcentre.getTel());
        description.setText(selectedcentre.getDescription());

        Image img = new Image(selectedcentre.getImage(), 388 , 309, true, true);
            imageview.imageProperty().unbind();
            imageview.setImage(img);
            path=selectedcentre.getImage();
            
        

        
    }

    @FXML
    private void changerimage(ActionEvent event) throws MalformedURLException {
                FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(imageview.getScene().getWindow());
        if (file != null) 
        {
            Image img = new Image(file.toURI().toString(), 388 , 309, true, true);
            imageview.imageProperty().unbind();
            imageview.setImage(img);
            
        }
        path = file.toURI().toURL().toString();
    }

    @FXML
    private void PDF(ActionEvent event) 
    {
        //Document doc = new Document();
        
        
        
        
    }

    @FXML
    private void createPdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        com.itextpdf.text.Document pdf = new com.itextpdf.text.Document();
        PdfWriter.getInstance(pdf, new FileOutputStream("listVet.pdf"));
        pdf.open();
        pdf.add(new Paragraph(listeViewVeterinaire.getItems().toString()));
        pdf.close();
    }
   
    
    
    
    
     
}
