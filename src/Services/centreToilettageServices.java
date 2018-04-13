/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import Technique.DataSource;
import Entity.centreToilettage;
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
 * @author Mimouna
 */
public class centreToilettageServices 
{
   private Connection con= DataSource.getInstance().getConnexion();
    private Statement ste;
    
     
      public centreToilettageServices()
    {
        try 
        {
            ste = con.createStatement();
        } catch (SQLException ex) 
        {
            Logger.getLogger(centreToilettageServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      public void AjouterCentreT(centreToilettage c  ) throws  SQLException
    {
       String req = "INSERT INTO centre_toilettage (libelle,adresse,tel,description,image) VALUES (?,?,?,?,?)" ;
       
        PreparedStatement pre = con.prepareStatement(req);
        
        
        pre.setString(1, c.getLibelle());
        pre.setString(2, c.getAdresse());
        pre.setInt(3, c.getTel());
        pre.setString(4, c.getDescription());
        pre.setString(5, c.getImage());
        
        pre.executeUpdate();
    }
      
      public List<centreToilettage> afficherCentreT() throws SQLException
    {
        String req = "SELECT * FROM centre_toilettage ";
        ResultSet rs = ste.executeQuery(req);        
        List<centreToilettage>  centresT = new ArrayList<>();
        while (rs.next())
        {
            centresT.add(new centreToilettage(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),
            rs.getString(6)));
        }
        return centresT ;
    }
      
      public ObservableList<centreToilettage> getObservableC () throws SQLException
        {
          ObservableList<centreToilettage> ListCentreT = FXCollections.observableArrayList();
          List<centreToilettage> cen =afficherCentreT();
                  for (centreToilettage   c : cen)
               {
                 ListCentreT.add(c);
               }
                 return ListCentreT;    
        }
      
      public void supprimerCentreT (int idd) throws SQLException
      {
          String req = "DELETE FROM centre_toilettage where id='" + idd + "' ";
          PreparedStatement pre = con.prepareStatement(req);
          pre.execute();

      }
      public void modifierCentreT (centreToilettage c) throws SQLException 
      {
          String req = "UPDATE centre_toilettage set libelle=(?),adresse=(?),tel=(?),description=(?),image=(?) WHERE id= (?)";
        
         PreparedStatement pre = con.prepareStatement(req);

        pre.setString(1, c.getLibelle());
        pre.setString(2, c.getAdresse());
        pre.setInt(3, c.getTel());
        pre.setString(4, c.getDescription());
        pre.setString(5, c.getImage());
        pre.setInt(6, c.getId());
        pre.executeUpdate();
          
      }

    }
    

