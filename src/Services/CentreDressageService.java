/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CentreDressage;
import Entity.Produit;
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
 * @author jabou
 */
public class CentreDressageService 
{
      private Connection con= DataSource.getInstance().getConnexion();
      private Statement ste;

    public CentreDressageService() throws SQLException 
    {
      ste=con.createStatement();
    
    }
    
    public void AjouterCentreDressage(CentreDressage d) throws SQLException
    {
        String req="INSERT INTO centre_dressage (libelle,adresse,tel,lat,lng,description,image,ville,gouvernerat,code_postal) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,d.getLibelle());
        pre.setString(2,d.getAdresse());
        pre.setInt(3,d.getTel());
        pre.setDouble(4,d.getLat());
        pre.setDouble(5,d.getLng());
        pre.setString(6, d.getDescription());
        pre.setString(7,d.getImage());
         pre.setString(8, d.getVille());
        pre.setString(9, d.getGouvernerat());
        pre.setInt(10, d.getCode_psotale());
        pre.executeUpdate();     
    }
    
    public List<CentreDressage> AfficherCentreDressage()throws SQLException
    {
        String req="SELECT * FROM centre_dressage";
        ResultSet r =ste.executeQuery(req);
        List<CentreDressage> centresD =new ArrayList<>();
        while(r.next())
        {
             centresD.add(new CentreDressage(r.getInt("id"),r.getString("libelle"),r.getString("adresse"),r.getInt("tel"),r.getDouble("lat"),r.getDouble("lng"),r.getString("description"),r.getString("image"),r.getString("ville"),r.getString("gouvernerat"),r.getInt("code_postal")));
        }
        return centresD;
    }
    
    public ObservableList<CentreDressage> getObservableCentreDressage () throws SQLException
        {
          ObservableList<CentreDressage> ListCentreDressage = FXCollections.observableArrayList();
          List<CentreDressage> centresD =centresD=  AfficherCentreDressage();
             for (CentreDressage   c : centresD)
               {
                 ListCentreDressage.add(c);
               }
                 return ListCentreDressage;    
        }
    
    
    public void SupprimerCentreDressage(int id) throws SQLException   
    {
           String req="DELETE FROM centre_dressage WHERE id='"+id+"' ";
           PreparedStatement pre = con.prepareStatement(req);
           pre.executeUpdate();    
    }
    
    public void ModifierCentreDressage(CentreDressage d) throws SQLException
    {
        String req="UPDATE centre_dressage SET libelle=(?),adresse=(?),tel=(?),lat=(?),lng=(?),description=(?),image=(?)   WHERE id=(?) ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,d.getLibelle());
        pre.setString(2,d.getAdresse());
        pre.setInt(3,d.getTel());
        pre.setDouble(4,d.getLat());
        pre.setDouble(5,d.getLng());
        pre.setString(6, d.getDescription());
        pre.setString(7,d.getImage());
        pre.setInt(8,d.getId());
        pre.executeUpdate();    
    }
      public ResultSet selectCentreD() 
    {
        ResultSet result = null;
       
        String req = "SELECT * FROM centre_dressage";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            result = ste.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
