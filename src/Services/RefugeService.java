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
        String req = "INSERT INTO Refuge(libelle, num, email, region, adresse, description, image) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, a.getLibelle());
        pre.setInt(2, a.getNum());
        pre.setString(3, a.getEmail());
        pre.setString(4, a.getRegion());
        pre.setString(5, a.getAdresse());
        pre.setString(6, a.getDescription());
        pre.setString(7, a.getImage());
        pre.executeUpdate();
        System.out.println("Refuge Ajoutée");
    }
     
     public void ModifierRefuge(Refuge a) throws SQLException {
        String req = "UPDATE refuge SET libelle=(?),num=(?), email=(?), region=(?),adresse=(?),description=(?), image=(?) WHERE id=(?)";
       PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, a.getLibelle());
        pre.setInt(2, a.getNum());
        pre.setString(3, a.getEmail());
        pre.setString(4, a.getRegion());
        pre.setString(5, a.getAdresse());
        pre.setString(6, a.getDescription());
        pre.setString(7, a.getImage());
        pre.setInt(8, a.getId());
        pre.executeUpdate();
    }
     
 public List<Refuge> AfficherRefuge()throws SQLException
    {
        String req="SELECT * FROM refuge";
        ResultSet r =ste.executeQuery(req);
        List<Refuge> Refuges =new ArrayList<>();
        while(r.next())
        {
             Refuges.add(new Refuge(r.getInt(1),r.getString(2), r.getInt(3), r.getString(4), r.getString(5), r.getString(6),r.getString(7), r.getString(8)));
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
           String req="DELETE FROM refuge WHERE id='"+ id +"' ";
           ste.execute(req);
        
            
    }
    
     public List<Refuge> selectRefuges() {
        ResultSet rs = null;
        List<Refuge> refuge = new ArrayList<Refuge>();
        String req = "SELECT * FROM refuge";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            rs = ste.executeQuery();
            while (rs.next()) {
                Refuge p = new Refuge(rs.getString(2),rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                refuge.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Refuge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return refuge;
    }
  
}

    

