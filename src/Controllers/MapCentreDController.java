/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.CentreDressageService;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author bn-sk
 */
public class MapCentreDController implements Initializable,MapComponentInitializedListener {

    GoogleMapView mapView;
    GoogleMap map;
    static double lat;
    static double lng;
    @FXML
    private AnchorPane anchor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mapView = new GoogleMapView();
         mapView.addMapInializedListener(this);
         anchor.getChildren().add(mapView);
         System.out.println(ServiceController.id);
    }    
    
    
    @Override
    public void mapInitialized() {

        CentreDressageService cs = null;
        try {
            cs = new CentreDressageService();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs=cs.selectCentreDdistinct(ServiceController.id);
        try {
            while(rs.next())
            {
                     lat=rs.getDouble("lat");
                     lng=rs.getDouble("lng");

            }
        MapOptions mapOptions = new MapOptions();
        LatLong center = new LatLong(lat,lng);
        mapOptions.center(center)
                .mapMarker(true)
                .zoom(12)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .mapType(MapTypeIdEnum.ROADMAP);
        map = mapView.createMap(mapOptions);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
