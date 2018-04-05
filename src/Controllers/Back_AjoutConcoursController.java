/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Concour;
import Entity.Conseils;
import Services.ServiceConcour;
import Services.ServiceConseils;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author wael
 */
public class Back_AjoutConcoursController implements Initializable {

    @FXML
    private Pane mainContentPane;
    @FXML
    private JFXButton addBt;
    @FXML
    private JFXTextField nbParticipants;
    @FXML
    private JFXTextField description;
    @FXML
    private DatePicker dateConcour;
    @FXML
    private JFXButton conseilAddBt;
    @FXML
    private JFXTextField conseilText;
    @FXML
    private JFXTextField conseilTitre;
    @FXML
    private JFXComboBox<String> conseilType;
    @FXML
    private TableView<Concour> concourTable;
    private ObservableList<Concour> dataConcours;
    @FXML
    private JFXTextField concourDescriptionMod;
    @FXML
    private JFXTextField concourNbrePlaceMod;
    @FXML
    private DatePicker concourDateMod;
    @FXML
    private JFXButton concourModBt;
    @FXML
    private JFXTextField concourIdMod;
    Concour toModifyConcour;
    @FXML
    private JFXTextField conseilid;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField text;
    @FXML
    private JFXButton conseilsModBt;
    @FXML
    private TableView<Conseils> conseilTable;
     private ObservableList<Conseils> dataConseil;
     Conseils toModifyConseils;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceConcour servCon = new ServiceConcour();
            
            ServiceConseils scon =new ServiceConseils();
            
            
            conseilType.getItems().add("Cats");
            conseilType.getItems().add("Dogs");
            /* startconfiguration table view concours */
            dataConcours = FXCollections.observableArrayList();
            TableColumn<Concour, String> descColumn = new TableColumn("Description");
            descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            TableColumn<Concour, Integer> nbPlace = new TableColumn("Nbre Places");
            nbPlace.setCellValueFactory(new PropertyValueFactory<>("Places"));
            TableColumn<Concour, Date> dateC = new TableColumn("Date Concour");
            dateC.setCellValueFactory(new PropertyValueFactory<>("Date"));
            TableColumn actionColumn = new TableColumn("Action");
            actionColumn.setCellFactory(param -> new TableCell<Object, Object>() {
                final JFXButton btn = new JFXButton("[X]");
                
                @Override
                public void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setButtonType(JFXButton.ButtonType.RAISED);
                        btn.setStyle("-fx-background-color: #b12b2b; ");
                        btn.setTextFill(Paint.valueOf("WHITE"));
                        btn.setOnAction(event -> {
                            Concour c = (Concour) this.getTableView().getItems().get(this.getIndex());
                            try {
                                servCon.supprimerConcour(c.getId());
                                dataConcours.removeAll(dataConcours);
                                
                                servCon.AfficherListeConcours().forEach(e -> {
                                    if (!dataConcours.contains(e)) {
                                        dataConcours.add(e);
                                    }
                                });
                                concourTable.refresh();
                            } catch (SQLException ex) {
                                Logger.getLogger(Back_AjoutConcoursController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
            });
            actionColumn.setStyle("-fx-alignment: CENTER; ");
            concourTable.getColumns().clear();
            concourTable.getColumns().addAll(descColumn, nbPlace, dateC, actionColumn);
            
            try {
                servCon.AfficherListeConcours().forEach((e) -> {
                    dataConcours.add(e);
                });
            } catch (SQLException ex) {
                Logger.getLogger(Back_AjoutConcoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
            concourTable.setItems(dataConcours);
            /* end configuration tableview Concour */
            concourTable.setRowFactory(tv -> {
                TableRow<Concour> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                        toModifyConcour = row.getItem();
                        concourIdMod.setText(String.valueOf(toModifyConcour.getId()));
                        concourDescriptionMod.setText(toModifyConcour.getDescription());
                        concourDateMod.setValue(toModifyConcour.getDate().toLocalDate());
                        concourNbrePlaceMod.setText(String.valueOf(toModifyConcour.getnbredeplaces()));
                    }
                });
                return row;
            });
            
            /* startconfiguration table view coseils */
            
            
            
            dataConseil = FXCollections.observableArrayList();
            TableColumn<Conseils, String> titreColumn = new TableColumn("Titre");
            titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            
            TableColumn<Conseils, String> textColumn = new TableColumn("Texte");
            textColumn.setCellValueFactory(new PropertyValueFactory<>("texte"));
            
            TableColumn<Conseils, String> typeColumn = new TableColumn("Type");
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            
            TableColumn action1Column = new TableColumn("Action");
            action1Column.setCellFactory(param -> new TableCell<Object, Object>() {
                final JFXButton btn = new JFXButton("[X]");
                
                @Override
                public void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setButtonType(JFXButton.ButtonType.RAISED);
                        btn.setStyle("-fx-background-color: #b12b2b; ");
                        btn.setTextFill(Paint.valueOf("WHITE"));
                        btn.setOnAction(event -> {
                            Conseils c1 = (Conseils) this.getTableView().getItems().get(this.getIndex());
                            try {
                                scon.supprimerConseils(c1.getId());
                                dataConseil.removeAll(dataConseil);
                                scon.AfficherListeConseils().forEach(e -> {
                                    if (!dataConseil.contains(e)) {
                                        dataConseil.add(e);
                                    }
                                });
                                conseilTable.refresh();
                            } catch (SQLException ex) {
                                Logger.getLogger(Back_AjoutConcoursController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
            });
            
            
             conseilTable.setRowFactory(tv -> {
                TableRow<Conseils> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                        toModifyConseils = row.getItem();
                        conseilid.setText(String.valueOf(toModifyConseils.getId()));
                        titre.setText(toModifyConseils.getTitre());
                        text.setText(toModifyConseils.getTexte());
                        type.setText(toModifyConseils.getType());
                    }
                });
                return row;
            });
            
            
            actionColumn.setStyle("-fx-alignment: CENTER; ");
            conseilTable.getColumns().clear();
            conseilTable.getColumns().addAll(titreColumn, typeColumn, textColumn,action1Column);
            
            try {
                scon.AfficherListeConseils().forEach((e) -> { 
                    dataConseil.add(e);
                });
            } catch (SQLException ex) {
                Logger.getLogger(Back_AjoutConcoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
            conseilTable.setItems(dataConseil);
            
        } catch (SQLException ex) {
            Logger.getLogger(Back_AjoutConcoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    
    
    

    @FXML
    private void addConcour(ActionEvent event) throws SQLException {
        String descrp = description.getText();
        int nbPar = Integer.parseInt(nbParticipants.getText());
        Date dateC = java.sql.Date.valueOf(dateConcour.getValue());
        Concour con = new Concour(descrp, nbPar, dateC);
        ServiceConcour serviceC = new ServiceConcour();
        serviceC.AjouterConcour(con);
        System.out.println("added concour");
    }

    @FXML
    private void addConseil(ActionEvent event) throws SQLException {
        String typeC = conseilType.getValue();
        String titreC = conseilTitre.getText();
        String textC = conseilText.getText();
        Conseils cons = new Conseils(titreC, textC, typeC);
        ServiceConseils serviceCons = new ServiceConseils();
        serviceCons.AjouterConseils(cons);
        System.out.println("added conseil");
    }

    @FXML
    private void concourModifier(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(concourIdMod.getText());
        String desc = concourDescriptionMod.getText();
        int nbre = Integer.parseInt(concourNbrePlaceMod.getText());
        Date dateC = java.sql.Date.valueOf(concourDateMod.getValue());
        Concour con = new Concour(id, desc, nbre, dateC);
        ServiceConcour serviceC = new ServiceConcour();
        serviceC.modifierConcour(id, con);

        dataConcours.removeAll(dataConcours);

        serviceC.AfficherListeConcours().forEach(e -> {
            if (!dataConcours.contains(e)) {
                dataConcours.add(e);
            }
        });
        concourTable.refresh();
    }
    
    
    @FXML
    private void afficherConcour(Event event) {
        
    }

    @FXML
    private void conseilsModifier(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(conseilid.getText());
        String titre = this.titre.getText();
       String text = this.text.getText();
        String type = this.type.getText();
       
        Conseils con = new Conseils(id, titre, text, type);
        ServiceConseils scon = new ServiceConseils();
       scon.modifierConseils(id, con);

        dataConseil.removeAll(dataConseil);

        
        scon.AfficherListeConseils().forEach(e -> {
            if (!dataConseil.contains(e)) {
                dataConseil.add(e);
            }
        });
        concourTable.refresh();
    }

}
