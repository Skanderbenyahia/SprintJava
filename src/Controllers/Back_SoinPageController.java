/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.CentreDressage;
import Entity.User;
import Entity.centreToilettage;
import Services.CentreDressageService;
import Services.UserService;
import Services.centreToilettageServices;
import Services.reservation_veterinaire_Service;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class Back_SoinPageController implements Initializable {

    @FXML
    private TableView<User> listeViewVeterinaire;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> tel;
    @FXML
    private TableColumn<User, String> mail;
    @FXML
    private Button pdfBtn;
    @FXML
    private Button st;

    @FXML
    private TableColumn<centreToilettage, String> adresse_t;
    @FXML
    private TableColumn<centreToilettage, String> description_t;
    @FXML
    private TableColumn<centreToilettage, String> libelle_t;
    @FXML
    private TableColumn<centreToilettage, Integer> tel_t;
    @FXML
    private Button ModifierT;
    @FXML
    private Button SupprimerT;
    @FXML
    private Tab listeViewCentreT;
    @FXML
    private TableView<centreToilettage> CentreT;
    private ObservableList data = FXCollections.observableArrayList();
    private ObservableList data2 = FXCollections.observableArrayList();
    @FXML
    private JFXButton mail1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                // TODO
                ModifierT.setDisable(true);
                SupprimerT.setDisable(true);
                
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
                mail.setCellValueFactory(new PropertyValueFactory<>("email"));
                
                reservation_veterinaire_Service us = new reservation_veterinaire_Service();
                data = us.getObservableVet();
                listeViewVeterinaire.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(Back_SoinPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            adresse_t.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            description_t.setCellValueFactory(new PropertyValueFactory<>("description"));
            libelle_t.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            tel_t.setCellValueFactory(new PropertyValueFactory<>("tel"));
            centreToilettageServices cs= new centreToilettageServices();
            data2=cs.getObservableC();
            CentreT.setItems(data2);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Back_SoinPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @FXML
    private void back_admin(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("/GUI/adminLayout.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void envoyer_mail(ActionEvent event) throws IOException {
        User u = this.listeViewVeterinaire.getSelectionModel().getSelectedItem();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("emna.mimouna@esprit.tn", "emna151JMT1506<3");
                //props.put("mail.smtp.starttls.enable", "true");
            }
        }
        );
        try {
            System.err.println(mail.getText());

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("safouane.sma@esprit.tn "));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail()));
            message.setSubject(subject.getText());
            message.setText(body.getText());
            Transport.send(message);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    private void createPdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        User u = this.listeViewVeterinaire.getSelectionModel().getSelectedItem();
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\jabou\\Desktop\\emna\\SprintJava\\src\\Ressources\\Images\\veteinaire.pdf"));
        document.open();
        document.add(new Paragraph("Détails de veternaire ", FontFactory.getFont(FontFactory.TIMES)));
        document.add(new Paragraph(new Date().toString()));
        document.add(new Paragraph("-----------------------------------------------------------------"));
        com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
        com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Details du veterinaire"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(cell);

        table.addCell("nom");
        table.addCell(u.getNom());
        table.addCell("Prenom");
        table.addCell(u.getPrenom());
        table.addCell("telephone");
        table.addCell(u.getTel() + "");
        table.addCell("email");
        table.addCell(u.getEmail());

        table.addCell("email");

        document.add(table);
        document.close();
        JOptionPane.showMessageDialog(null, " données exportées en pdf evec succés ");

    }

    @FXML
    private void stat(ActionEvent event) {
        UserService a = new UserService();
        Scene scene = new Scene(new Group());
        Stage mainStage = new Stage();
        mainStage.setWidth(500);
        mainStage.setHeight(500);

        List<User> liste = new ArrayList<User>();
        //a.getStat() hedhi fergha tekhou feha wini requete eli tjib menha..?
        liste = a.getStat();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();
        for (int i = 0; i < liste.size(); i++) {
            pieChartData.add(new PieChart.Data(liste.get(i).getUsername(), liste.get(i).getStat()));

        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Vetrinaire et reservation");

        ((Group) scene.getRoot()).getChildren().add(chart);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void AjouterT(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjoutCentreToi.fxml"));
        Parent root=loader.load();
        pdfBtn.getScene().setRoot(root);
        
    }
    
    
    public void changeScene(ActionEvent event, String viewName,String title,centreToilettage d,SoinControllerClass controllerC) throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent=loader.load();
        
        controllerC=loader.getController();
        controllerC.preloadData(d);
        
         Scene CentreDPage= new Scene (parent);
         Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setTitle(title);
         window.setScene(CentreDPage);
         window.show();
   }
    
    
    @FXML
    private void modifierCentre(ActionEvent event) throws IOException 
    {
        centreToilettage d=this.CentreT.getSelectionModel().getSelectedItem();
        AjoutCentreToiController controllerC=new AjoutCentreToiController();
        changeScene(event, "../GUI/AjoutCentreToi.fxml", "Modifier le Centre", d, (SoinControllerClass) controllerC);
    }

    @FXML
    private void supprimerCentre(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> ok = alert.showAndWait();
        if (ok.get() == ButtonType.OK) {
            centreToilettage d = this.CentreT.getSelectionModel().getSelectedItem();
            centreToilettageServices cd = new centreToilettageServices();
            cd.supprimerCentreT(d.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Back_SoinPage.fxml"));
            Parent root = loader.load();
            CentreT.getScene().setRoot(root);
        }
    }

    @FXML
    private void selected(MouseEvent event) {
        User c= new User();
        c=this.listeViewVeterinaire.getSelectionModel().getSelectedItem();
        ModifierT.setDisable(false);
        SupprimerT.setDisable(false);
       //destinataire.setText(c.getEmail());
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         UserService us = new UserService();
        us.Desactivate(Entity.Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root=loader.load();
        SupprimerT.getScene().setRoot(root);
    }

}
