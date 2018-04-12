/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CentreDressage;
import Entity.Concour;
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
import java.sql.Date;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bn-sk
 */
public class ServiceConcour {

    private Connection con = DataSource.getInstance().getConnexion();
    private Statement statement;

    public ServiceConcour() {
        try {
            statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceConcour.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AjouterConcour(Concour c) throws SQLException {
        String req = "insert into concours (description,nbredeplaces,date) values(?,?,?)";
        PreparedStatement prepared = con.prepareStatement(req);
        prepared.setString(1, c.getDescription());
        prepared.setInt(2, c.getnbredeplaces());
        prepared.setDate(3, c.getdate());
        prepared.executeUpdate();

    }

    public List<Concour> AfficherListeConcours() throws SQLException {
         String req="SELECT * FROM concours";
        ResultSet r =statement.executeQuery(req);
        List<Concour> concour =new ArrayList<>();
        while(r.next())
        {
             concour.add(new Concour(r.getInt("id"),r.getString("description"),r.getInt("nbredeplaces"),r.getDate("date")));
        }
        return concour;
    }

    public ObservableList<Concour> getObservableConcour () throws SQLException
        {
          ObservableList<Concour> ListConcour = FXCollections.observableArrayList();
          List<Concour> concour=AfficherListeConcours()  ;
             for (Concour   c : concour)
               {
                 ListConcour.add(c);
               }
                 return ListConcour;    
        }
    
    public void supprimerConcour(int id) throws SQLException {
        String req = "Delete From concours where id=(?)";
        PreparedStatement prepared = con.prepareStatement(req);
        prepared.setInt(1, id);
        prepared.executeUpdate();

    }

    public void modifierConcour(Concour c) throws SQLException {
        String req = "update concours set description=(?), nbredeplaces=(?),date=(?) WHERE id=(?)";
        PreparedStatement prepared = con.prepareStatement(req);
        prepared.setString(1, c.getDescription());
        prepared.setInt(2, c.getnbredeplaces());
        prepared.setDate(3, c.getdate());
        prepared.setInt(4, c.getId());
        prepared.executeUpdate();

    }

    public void participation(int idc, int idp) throws SQLException {
        String req = "insert into participation (idc,idp) values(?,?)";
        PreparedStatement prepared = con.prepareStatement(req);
        prepared.setInt(1, idc);
        prepared.setInt(2, idp);
        prepared.executeUpdate();
    }

    public int concourPlaces(int idConcour) throws SQLException {
        String req = "SELECT count(*) as total from participation where idc = ?";
        PreparedStatement prepared = con.prepareStatement(req);
        prepared.setInt(1, idConcour);
        ResultSet results = prepared.executeQuery();
        int count = 0;
        if (results.next()) {
            count = results.getInt("total");
        }
        return count;
    }
    
    
    
      public void rating(int idc, float note) throws SQLException {
        String req = "insert into rating (idc,note) values(?,?)";
        PreparedStatement prepared = con.prepareStatement(req);
        prepared.setInt(1,idc);
        prepared.setFloat(2,note);
        prepared.executeUpdate();
    }
      
      
      
        public float ratingcount(int idConcour) throws SQLException {
        String req = "SELECT count(*) as total from rating where idc = ?";
        PreparedStatement prepared = con.prepareStatement(req);
        prepared.setInt(1, idConcour);
        ResultSet results = prepared.executeQuery();
        int count = 0;
        float rating =0;
        if (results.next()) {
           
            count = results.getInt("total");
        }
        
       String req1 = "SELECT SUM(note) as note from rating where idc = ?";
        PreparedStatement prepared1 = con.prepareStatement(req1);
       prepared1.setInt(1, idConcour);
        ResultSet results1 = prepared1.executeQuery();
     
        if (results1.next()) {
           
            count = results.getInt("total");
            rating = (results1.getFloat("note")/count); 
        }
        
        return rating;
    }
    
       public ResultSet selectConcour() 
    {
        ResultSet result = null;
       
        String req = "SELECT * FROM concours";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            result = ste.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
