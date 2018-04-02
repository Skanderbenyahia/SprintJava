/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.ReservationPetsitter;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jabou
 */
public class ReservationPetsitterService {
    private Connection con= DataSource.getInstance().getConnexion();
    private Statement ste;

    public ReservationPetsitterService() throws SQLException 
    {
     ste=con.createStatement();
    }

    public void ReserverPetsitter(ReservationPetsitter r) throws SQLException
    {
        String req="INSERT INTO reservation_petsitter (dateD,dateF,prix,encaisser,idPetsitter,idUser) VALUES (?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1,r.getDateD());
        pre.setDate(2,r.getDateF());
        pre.setDouble(3,r.getPrix());
        pre.setDouble(4,r.getEncaisser());
        pre.setInt(5, r.getIdPetsitter());
        pre.setInt(6, r.getIdUser());
        pre.executeUpdate();     
    }
    
    public List<ReservationPetsitter> AfficherReservationPetsitter()throws SQLException
    {
        String req="SELECT * FROM reservation_petsitter";
        ResultSet r =ste.executeQuery(req);
        List<ReservationPetsitter> reservationP =new ArrayList<>();
        while(r.next())
        {
             reservationP.add(new ReservationPetsitter(r.getInt("id"),r.getDate("dateD"),r.getDate("dateF"),r.getDouble("prix"),r.getDouble("encaisser"),r.getInt("idPetsitter"),r.getInt("iduser")));
        }
        return reservationP;
    }
    
      public ObservableList<ReservationPetsitter> getObservableReservationPetsitter () throws SQLException
        {
          ObservableList<ReservationPetsitter> Listreservations = FXCollections.observableArrayList();
          List<ReservationPetsitter> reservations =  AfficherReservationPetsitter();
             for (ReservationPetsitter   r : reservations)
               {
                 Listreservations.add(r);
               }
                 return Listreservations;    
        }
    
    
    public void SupprimerReservationPetsitter(int id) throws SQLException   
    {
           String req="DELETE FROM reservation_petsitter WHERE id='"+id+"' ";
           PreparedStatement pre = con.prepareStatement(req);
           pre.executeUpdate();    
    }
    
    public void ModifierReservationPetsitter(int id,ReservationPetsitter r) throws SQLException
    {
        String req="UPDATE reservation_petsitter SET dateD=(?),dateF=(?),idPetsitter=(?) WHERE id='"+id+"' ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1,r.getDateD());
        pre.setDate(2,r.getDateF());
        pre.setInt(3, r.getIdPetsitter());
        pre.executeUpdate();        
    }
    public int apportPetsitter() throws SQLException
    {
        int apport=0;
        String req="SELECT SUM(encaisser) FROM reservation_petsitter ";      
        ResultSet r =ste.executeQuery(req);
       while(r.next())
       {
           apport=r.getInt(1);
           
       }
        return apport;
    
    }
    
}
