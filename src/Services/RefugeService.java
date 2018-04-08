/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Refuge;
import Services.RefugeService;
import Technique.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




/**
 *
 * @author Skeez
 */
public class RefugeService {
    
      private Connection con= DataSource.getInstance().getConnexion();
      private Statement ste;

    public RefugeService() throws SQLException 
    {
      ste=con.createStatement();
    
    }
     public void ajouterRefuge(Refuge a) throws SQLException {
        String req = "INSERT INTO Refuge(libelle, adresse, num, region, email, description) VALUES(?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, a.getLibelle());
        pre.setString(2, a.getAdresse());
        pre.setInt(3, a.getNum());
        pre.setString(4, a.getRegion());
        pre.setString(5, a.getEmail());
        pre.setString(6, a.getDescription());
        pre.executeUpdate();
        System.out.println("Refuge Ajoutée");
    }
     
     public void ModifierRefuge(Refuge a) throws SQLException {
        String req = "UPDATE refuge SET libelle=(?),adresse=(?),num=(?),region=(?),email=(?),description=(?) WHERE id=(?)";
       PreparedStatement pre = con.prepareStatement(req);
       pre.setString(1, a.getLibelle());
        pre.setString(2, a.getAdresse());
        pre.setInt(3, a.getNum());
        pre.setString(4, a.getRegion());
        pre.setString(5, a.getEmail());
        pre.setString(6, a.getDescription());
        pre.setInt(7, a.getId());
        pre.executeUpdate();
    }
     
 public List<Refuge> AfficherRefuge()throws SQLException
    {
        String req="SELECT * FROM refuge";
        ResultSet r =ste.executeQuery(req);
        List<Refuge> Refuges =new ArrayList<>();
        while(r.next())
        {
             Refuges.add(new Refuge(r.getString(2), r.getInt(3), r.getString(4), r.getString(5), r.getString(6),r.getString(7), r.getString(8)));
        }
        return Refuges;
    }
    
    public ObservableList<Refuge> getObservableRefuge () throws SQLException
        {
          ObservableList<Refuge> ListRefuge = FXCollections.observableArrayList();
          List<Refuge> Refuges =Refuges=  AfficherRefuge();
             for (Refuge   c : Refuges)
               {
                 ListRefuge.add(c);
               }
                 return ListRefuge;    
        }
    
    
    public void SupprimerRefuge(int id) throws SQLException   
    {
           String req="DELETE FROM refuge WHERE id='"+id+"' ";
           PreparedStatement pre = con.prepareStatement(req);
           pre.executeUpdate();    
    }
    
  
}

    

