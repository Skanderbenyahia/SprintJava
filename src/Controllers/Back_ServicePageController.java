/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.CentreDressage;
import Entity.ReservationPetsitter;
import Entity.Session;
import Services.CentreDressageService;
import Services.ReservationPetsitterService;
import Services.UserService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.util.SearchPath;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class Back_ServicePageController implements Initializable {

    @FXML
    private Button BackButton;
    @FXML
    private JFXTabPane ServiceTabPane;
    @FXML
    private Tab CentreDressagePane;
    @FXML
    private Tab PetsitterPan;
    @FXML
    private TableView<CentreDressage> CentreDressageView;
    @FXML
    private TableColumn<CentreDressage, String> Column_libelle;
    @FXML
    private TableColumn<CentreDressage, String> Column_adresse;
    @FXML
    private TableColumn<CentreDressage, Integer> Column_tel;
    @FXML
    private TableColumn<CentreDressage, String> Column_description;
    @FXML
    private TableColumn<CentreDressage, Double> Column_lan;
    @FXML
    private TableColumn<CentreDressage, Double> Column_lat;
    @FXML
    private TableColumn<CentreDressage, String> Column_image;
    @FXML
    private Button ajouterD;
    @FXML
    private Button modifierD;
    @FXML
    private Button supprimerD;
    @FXML
    private TableView<ReservationPetsitter> ReservationPetsitterView;
    @FXML
    private TableColumn<ReservationPetsitter, Date> Column_DateD;
    @FXML
    private TableColumn<ReservationPetsitter, Date> Column_DateF;
    @FXML
    private TableColumn<ReservationPetsitter, Float> Column_Prix;
    @FXML
    private TableColumn<ReservationPetsitter, Float> Column_Encaisser;
    @FXML
    private TableColumn<ReservationPetsitter, Integer> Column_Petsitter;
    @FXML
    private TableColumn<ReservationPetsitter, Integer> Column_Utilisateur;
    @FXML
    private JFXTextField search;
    private ObservableList data= FXCollections.observableArrayList();
    private ObservableList dataR= FXCollections.observableArrayList();
    @FXML
    private JFXTextField searchR;
    @FXML
    private Button pdfBTN;
  
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                                        /* CENTRE DRESSAGE */
                            //********************************************************//
                            
       modifierD.setDisable(true);
       supprimerD.setDisable(true);
        
        Column_libelle.setCellValueFactory(new PropertyValueFactory<> ("libelle"));
        Column_adresse.setCellValueFactory(new PropertyValueFactory<> ("adresse"));
        Column_tel.setCellValueFactory(new PropertyValueFactory<> ("tel"));
        Column_lat.setCellValueFactory(new PropertyValueFactory<> ("lat"));
        Column_lan.setCellValueFactory(new PropertyValueFactory<> ("lng"));
        Column_description.setCellValueFactory(new PropertyValueFactory<> ("description"));
        Column_image.setCellValueFactory(new PropertyValueFactory<> ("image"));
        
        try {
            CentreDressageService cd = new CentreDressageService();
            data=cd.getObservableCentreDressage();
            CentreDressageView.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(Back_ServicePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                                                /* ReservationPetsitter*/
                            //********************************************************//
                            
        Column_DateD.setCellValueFactory(new PropertyValueFactory<>("dateD"));
        Column_DateF.setCellValueFactory(new PropertyValueFactory<>("dateF"));
        Column_Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        Column_Encaisser.setCellValueFactory(new PropertyValueFactory<>("encaisser"));
        Column_Petsitter.setCellValueFactory(new PropertyValueFactory<>("idPetsitter"));
        Column_Utilisateur.setCellValueFactory(new PropertyValueFactory<>("idUser"));

            try {
                ReservationPetsitterService rs = new ReservationPetsitterService();
                dataR=rs.getObservableReservationPetsitter();
                ReservationPetsitterView.setItems(dataR);
        } catch (SQLException ex) {
            Logger.getLogger(Back_ServicePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            FilteredList<CentreDressage> filteredList= new FilteredList<>(data, e -> true);
            search.setOnKeyReleased(e-> {
                search.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredList.setPredicate((Predicate<? super CentreDressage>) centre-> {
                        if((newValue==null)|(newValue.isEmpty()))
                    {
                    return true;
                    }
                        String miniscule= newValue.toLowerCase();
                        if(centre.getLibelle().toLowerCase().contains(miniscule))
                            {
                            return true; 
                            }
                           else  if(centre.getAdresse().toLowerCase().contains(miniscule))
                            {
                            return true; 
                            }
                            else  if(centre.getDescription().toLowerCase().contains(miniscule))
                             {
                                  return true; 
                             }
                      
                        return false;
                    });
                });
                SortedList<CentreDressage> sortedDate= new SortedList<>(filteredList);
                sortedDate.comparatorProperty().bind(CentreDressageView.comparatorProperty());
                CentreDressageView.setItems(sortedDate);
            });
            
           
                                              /* recherche*/
                            //********************************************************//
            FilteredList<ReservationPetsitter> filteredListR= new FilteredList<>(dataR, e -> true);
            searchR.setOnKeyReleased(e-> {
                searchR.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredListR.setPredicate((Predicate<? super ReservationPetsitter>) reservation-> {
                        if((newValue==null)|(newValue.isEmpty()))
                    {
                    return true;
                    }
                        String miniscule= newValue.toLowerCase();
                        if(reservation.getDateD().toString().contains(newValue))
                            {
                            return true; 
                            }
                       else if(reservation.getDateF().toString().contains(newValue))
                            {
                            return true; 
                            }
                      
                        return false;
                    });
                });
                SortedList<ReservationPetsitter> sortedDateR= new SortedList<>(filteredListR);
                sortedDateR.comparatorProperty().bind(ReservationPetsitterView.comparatorProperty());
                ReservationPetsitterView.setItems(sortedDateR);
            });
            
         
    }    

                                              /* CENTRE DRESSAGE */
                            //********************************************************//
    @FXML
    private void Back_adminLayout(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/adminLayout.fxml"));
        Parent root =loader.load();
        CentreDressageView.getScene().setRoot(root);
    }

    @FXML
    private void selected(MouseEvent event) {
        modifierD.setDisable(false);
       supprimerD.setDisable(false);
    }

    @FXML
    private void supprimerCentreDressage(ActionEvent event) throws SQLException, IOException {
        Alert alert= new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de vouloir supprimer ?");
        Optional<ButtonType> ok =alert.showAndWait();
        if(ok.get()==ButtonType.OK)
        {
           CentreDressage d=this.CentreDressageView.getSelectionModel().getSelectedItem();
           CentreDressageService cd= new CentreDressageService();
           cd.SupprimerCentreDressage(d.getId());
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_ServicePage.fxml"));
           Parent root =loader.load();
           CentreDressageView.getScene().setRoot(root);
        }
    }

    @FXML
    private void AjouterCentreDressage(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_AjouterCDPage.fxml"));
           Parent root =loader.load();
           CentreDressageView.getScene().setRoot(root);
        
    }

    public void changeScene(ActionEvent event, String viewName,String title,CentreDressage d,ControllerClass controllerC) throws IOException
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
    private void ModifierCentreDressage(ActionEvent event) throws IOException {
        CentreDressage d=this.CentreDressageView.getSelectionModel().getSelectedItem();
        Back_AjouterCDPageController controllerC=new Back_AjouterCDPageController();
        changeScene(event, "../GUI/Back_AjouterCDPage.fxml", "Modifier le Centre", d, controllerC);
    }

                                            /* ReservationPetsitter*/
                            //********************************************************//

    @FXML
    private void ApportPetSitter(ActionEvent event) throws SQLException 
    {
        ReservationPetsitterService rp=new ReservationPetsitterService();
        int apport=rp.apportPetsitter();
        Alert alert= new Alert(AlertType.INFORMATION);
        alert.setTitle("Apport");
        alert.setHeaderText(null);
        alert.setContentText("Les Petsitters ont apporté : "+ apport +" DT");
        alert.showAndWait();
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException {
        UserService us = new UserService();
        us.Desactivate(Session.getCurrentSession());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Start.fxml"));
        Parent root=loader.load();
        CentreDressageView.getScene().setRoot(root);
    }

    @FXML
    private void creation_pdf(ActionEvent event) throws DocumentException, FileNotFoundException {
        CentreDressage selectedCentre=this.CentreDressageView.getSelectionModel().getSelectedItem();
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\jabou\\Desktop\\GitFinal\\SprintJava\\PDF.pdf"));
        document.open();
        document.add(new Paragraph(new java.util.Date().toString()));
        com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
        com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Centre de toilettage info"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(cell);

        table.addCell("libelle");
        table.addCell(selectedCentre.getLibelle());
        table.addCell("adresse");
        table.addCell(selectedCentre.getAdresse());
        table.addCell("telephone");
        table.addCell(selectedCentre.getTel()+"");
        table.addCell("description");
        table.addCell(selectedCentre.getDescription());

        table.addCell("email");

        document.add(table);
        document.close();
        JOptionPane.showMessageDialog(null, " données exportées en pdf evec succés ");
    }
}
