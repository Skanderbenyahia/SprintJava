/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.ReservationPetsitter;
import Entity.User;
import Entity.reservation_veterinaire;
import Technique.DataSource;
import com.jfoenix.controls.JFXDatePicker;
import static java.lang.String.format;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.text.html.ListView;

/**
 *
 * @author Mimouna
 */
public class reservation_veterinaire_Service {

    private Connection con = DataSource.getInstance().getConnexion();
    private Statement ste;
    static int count=0;

    public reservation_veterinaire_Service() throws SQLException {
        ste = con.createStatement();
    }

    public void ReserverVeterinaire(reservation_veterinaire r) throws SQLException, ParseException {
        String req = "INSERT INTO reservation_veterinaire (id_user_id,id_veterinaire_id,date_debut,date_fin,description) VALUES (?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, r.getId_user());
        pre.setInt(2, r.getId_veterinaire());
        Timestamp timestamp1 = Timestamp.valueOf(r.getDate_debut());
        Timestamp timestamp2 = Timestamp.valueOf(r.getDate_fin());
        pre.setTimestamp(3, timestamp1);
        pre.setTimestamp(4, timestamp2);
        pre.setString(5, r.getDescription());
        pre.executeUpdate();
    }

    public boolean ReserverVeterinaireExiste(reservation_veterinaire r) throws SQLException
    {
        String req = "SELECT count(*) from reservation_veterinaire where ? between date_debut and date_fin";
        PreparedStatement pre = con.prepareStatement(req);
        Timestamp timestamp1 = Timestamp.valueOf(r.getDate_debut());
        pre.setTimestamp(1, timestamp1);
        ResultSet rs=pre.executeQuery();
        
        
        while(rs.next())
        {
            count=rs.getInt(1);
        }
        System.out.println(count);
        if(count==0)
        {
            return true;
        }
      return false;
    }
    public List<reservation_veterinaire> AfficherReservationVeterinaire(int id ) throws SQLException {
        String req = "SELECT * FROM reservation_veterinaire where id_veterinaire_id = '"+id+"'";  
        ResultSet r = ste.executeQuery(req);
        List<reservation_veterinaire> reservationV = new ArrayList<>();
        while (r.next()) 
        {
      reservationV.add(new reservation_veterinaire(r.getInt("id_user_id"), r.getInt("id_veterinaire_id"), r.getTimestamp("date_debut").toLocalDateTime(), r.getTimestamp("date_fin").toLocalDateTime(), r.getString("description")));
           return reservationV;
        }
        return reservationV;
    }
    

     public List<reservation_veterinaire> AfficherReservationUser(int id ) throws SQLException {
        String req = "SELECT * FROM reservation_veterinaire where id_user_id ='"+id+"'"; 
        ResultSet r = ste.executeQuery(req);
        List<reservation_veterinaire> reservationV = new ArrayList<>();
        while (r.next()) 
        {
      reservationV.add(new reservation_veterinaire(r.getInt("id_user_id"), r.getInt("id_veterinaire_id"), r.getTimestamp("date_debut").toLocalDateTime(), r.getTimestamp("date_fin").toLocalDateTime(), r.getString("description")));
            return reservationV;
        }
        return reservationV;
    }
      
 public ObservableList<reservation_veterinaire> getObservableReservationUser (int id) throws SQLException
        {
          ObservableList<reservation_veterinaire> ListRV = FXCollections.observableArrayList();
          List<reservation_veterinaire> LRV=LRV=AfficherReservationUser(id);
             for (reservation_veterinaire  E : LRV)
               {
                 ListRV.add(E);
               }
                 return ListRV;    
        }
  public ObservableList<reservation_veterinaire> getObservableReservationVet (int id) throws SQLException
        {
          ObservableList<reservation_veterinaire> ListRV = FXCollections.observableArrayList();
          List<reservation_veterinaire> LRV=LRV=AfficherReservationVeterinaire(id);
             for (reservation_veterinaire  E : LRV)
               {
                 ListRV.add(E);
               }
                 return ListRV;    
        }
    
 public List<User> listeVeterinaire () throws SQLException
    {
        String req = "SELECT *  FROM user where roles = 'ROLE_VETERINAIRE' ";
        ResultSet rs = ste.executeQuery(req);        
        List<User>  liste = new ArrayList<>();
        while (rs.next())
        {
        
            liste.add(new User( rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),rs.getString("email"),rs.getInt("telephone")));
        }
        return liste ;
         
    }
    
     public ObservableList<User> getObservableVet () throws SQLException
        {
          ObservableList<User> Listvet = FXCollections.observableArrayList();
          List<User> vet =listeVeterinaire();
                  for (User   c : vet)
               {
                 Listvet.add(c);
               }
                 return Listvet;    
        }
    



}