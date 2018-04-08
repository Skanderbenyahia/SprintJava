/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.CentreDressage;
import Services.CentreDressageService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author jabou
 */
public class Back_AjouterCDPageController implements Initializable,ControllerClass,MapComponentInitializedListener {

    @FXML
    private Label titre;
    @FXML
    private JFXTextField libelle;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField description;
   /* @FXML
    private JFXTextField lan;
    @FXML
    private JFXTextField lat;*/
    @FXML
    private JFXTextField image;
    
    private CentreDressage d;

    Marker myMarker = null;
    private String str_address;
    private LatLong ll;

    protected GoogleMapView mapComponent;
    private GoogleMap map;
     int count = 0;
    @FXML
    private BorderPane map_container;
 
    @FXML
    private JFXTextField gouverneratTXT;
    @FXML
    private JFXTextField codepostalTXT;
    @FXML
    private JFXTextField id_lat;
    @FXML
    private JFXTextField id_lng;
    @FXML
    private JFXTextField villeTXT;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       mapComponent = new GoogleMapView(Locale.ENGLISH.getLanguage(), "AIzaSyDmK68Bq5oD_6YfMsK-Nh848i5KLRpO61Y&libraries=places&language=en");
       mapComponent.addMapInializedListener((MapComponentInitializedListener) this);
       map_container.setCenter(mapComponent);
       map_container.setVisible(true);
    }    

    @FXML
    private void AjouterCentreDressage(ActionEvent event) throws SQLException, IOException {
        
        CentreDressageService cd=new CentreDressageService();
        if(ValidateFields()){
        if(d!=null)
        {
            updateCentre();
            cd.ModifierCentreDressage(d);
        }
        else
        {
        CentreDressage d=new CentreDressage();
        d.setLibelle(libelle.getText());
        d.setAdresse(adresse.getText());
        d.setDescription(description.getText());
        d.setImage(image.getText());
        d.setTel(Integer.parseInt(tel.getText()));
        d.setLat(Float.parseFloat(id_lat.getText()));
        d.setLng(Float.parseFloat(id_lng.getText()));
        d.setVille(villeTXT.getText());
        d.setGouvernerat(gouverneratTXT.getText());
        d.setCode_psotale(Integer.parseInt(codepostalTXT.getText()));
        cd.AjouterCentreDressage(d);
        }
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/Back_ServicePage.fxml"));
        Parent root =loader.load();
        tel.getScene().setRoot(root);
        }
    }

    @Override
    public void preloadData(CentreDressage d) {
        this.d=d;
        this.libelle.setText(d.getLibelle());
        this.adresse.setText(d.getAdresse());
        this.tel.setText(Integer.toString(d.getTel()));
        this.id_lat.setText(Double.toString(d.getLat()));
        this.id_lng.setText(Double.toString(d.getLng()));
        this.villeTXT.setText(d.getVille());
        this.gouverneratTXT.setText(d.getGouvernerat());
        this.codepostalTXT.setText(Integer.toString(d.getCode_psotale()));
        this.description.setText(d.getDescription());
        this.image.setText(d.getImage());
        this.titre.setText("Modifier Un Centre de dressage");
    }
    
     public void updateCentre()
    {
    d.setLibelle(libelle.getText());
    d.setAdresse(adresse.getText());
    d.setTel(Integer.parseInt(tel.getText()));
    d.setLat(Float.parseFloat(id_lat.getText()));
    d.setLng(Float.parseFloat(id_lng.getText()));
    d.setVille(villeTXT.getText());
    d.setGouvernerat(gouverneratTXT.getText());
    d.setCode_psotale(Integer.parseInt(codepostalTXT.getText()));
    d.setDescription(description.getText());
    d.setImage(image.getText());
    }

     private boolean ValidateFields()
     {
         if(libelle.getText().isEmpty() | adresse.getText().isEmpty() | description.getText().isEmpty() 
                 | image.getText().isEmpty() |tel.getText().isEmpty())
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tout les champs !");
                alert.showAndWait();
         return false;
         }
         
          if((Integer.parseInt(tel.getText()))<0)
         {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Champs");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez Vérifier le numéro de téléphone !");
                alert.showAndWait();
         return false;
         }
        return true;
     
     }
    @Override
    public void preloadData(Categorie c) {
    }
    
     public void mapInitialized() {
        LatLong center = new LatLong(36.78811632710675, 10.186042785644531);

        MapOptions options = new MapOptions();
        options.center(center)
                .mapMarker(true)
                .zoom(12)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .mapType(MapTypeIdEnum.ROADMAP)
                .styleString("[ { 'featureType': 'administrative', 'elementType': 'labels.text.fill', 'stylers': [ { 'lightness': '-23' }, { 'saturation': '100' }, { 'weight': '8.21' }, { 'color': '#f0730e' } ] }, { 'featureType': 'administrative', 'elementType': 'labels.text.stroke', 'stylers': [ { 'weight': '3.67' }, { 'saturation': '64' }, { 'lightness': '100' }, { 'visibility': 'on' }, { 'gamma': '1.60' }, { 'color': '#ffffff' } ] }, { 'featureType': 'landscape', 'elementType': 'all', 'stylers': [ { 'lightness': '70' }, { 'saturation': '-4' } ] }, { 'featureType': 'landscape', 'elementType': 'labels', 'stylers': [ { 'visibility': 'off' } ] }, { 'featureType': 'landscape.man_made', 'elementType': 'all', 'stylers': [ { 'visibility': 'on' }, { 'color': '#d3d3d3' }, { 'lightness': '60' }, { 'saturation': '0' }, { 'gamma': '2.00' } ] }, { 'featureType': 'poi.medical', 'elementType': 'geometry.fill', 'stylers': [ { 'saturation': '80' } ] }, { 'featureType': 'poi.park', 'elementType': 'geometry.fill', 'stylers': [ { 'color': '#c8e354' } ] }, { 'featureType': 'road.arterial', 'elementType': 'geometry.fill', 'stylers': [ { 'hue': '#ff9700' }, { 'saturation': '43' }, { 'lightness': '-10' } ] }, { 'featureType': 'transit', 'elementType': 'labels.text.fill', 'stylers': [ { 'color': '#0025bc' } ] }, { 'featureType': 'transit', 'elementType': 'labels.text.stroke', 'stylers': [ { 'weight': '5' }, { 'gamma': '1.85' }, { 'color': '#ffffff' } ] }, { 'featureType': 'water', 'elementType': 'geometry', 'stylers': [ { 'visibility': 'on' }, { 'hue': '#00b3ff' }, { 'saturation': '69' }, { 'lightness': '-38' } ] }, { 'featureType': 'water', 'elementType': 'labels.text.fill', 'stylers': [ { 'lightness': '-97' }, { 'saturation': '93' } ] }, { 'featureType': 'water', 'elementType': 'labels.text.stroke', 'stylers': [ { 'weight': '3.5' }, { 'lightness': '100' }, { 'saturation': '0' }, { 'gamma': '1.02' }, { 'visibility': 'on' }, { 'color': '#ffffff' } ] } ]");
        map = mapComponent.createMap(options, false);
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
        
            count++;
            ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            id_lat.setText(""+ll.getLatitude());
            id_lng.setText(""+ll.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong markerLatLong = new LatLong(ll.getLatitude(), ll.getLongitude());
            // LatLong markerLatLong1 = new LatLong(ll.getLatitude(), ll.getLongitude());
            
            String forrrAdd = "";
            forrrAdd = adressss(ll.getLatitude(), ll.getLongitude());

            //System.out.println(forrrAdd);
            str_address = between(forrrAdd, "\"formatted_address\" : \"", "\",         \"geometry\"");
            markerOptions.position(markerLatLong)
                    .title("My new Marker")
                    .icon(MarkerImageFactory.createMarkerImage("mymarker.png", "png"))
                    .animation(Animation.DROP)
                    .visible(true);
            if (myMarker == null) {
                myMarker = new Marker(markerOptions);
                map.addMarker(myMarker);
            }else{
                myMarker.setOptions(markerOptions);
            }
           
        });
     }    
        public String adressss(double lat, double ln) {
        try {
            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + ln + "&sensor=true");
            // making connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            // Reading data's from url
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String outt = "";
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                outt += output;
            }

            return outt;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
        
        static String between(String value, String a, String b) {
        // Return a substring between the two strings.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.indexOf(b);
        if (posB == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB);
    }

    @FXML
    private void back_admin(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/adminLayout.fxml"));
        Parent root =loader.load();
        libelle.getScene().setRoot(root);
    }
}
