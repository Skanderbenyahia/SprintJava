/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CentreDressage;
import Entity.Concour;
import Entity.Conseils;
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
 * @author bn-sk
 */
public class ServiceConseils {
    private Connection con= DataSource.getInstance().getConnexion();
    private Statement statement;
    public ServiceConseils() throws SQLException
    {
            statement= con.createStatement();
    }
    
    
    
    public void AjouterConseils(Conseils p) throws SQLException
    {
        String req="insert into Conseils (titre,text,type) values(?,?,?)";
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setString(1,p.getTitre());
            prepared.setString(2,p.getTexte());
            prepared.setString(3,p.getType());
            
            prepared.executeUpdate();
        
    }
    public List<Conseils> AfficherListeConseils () throws SQLException
    {
       String req="SELECT * FROM conseils";
        ResultSet r =statement.executeQuery(req);
        List<Conseils> conseil =new ArrayList<>();
        while(r.next())
        {
             conseil.add(new Conseils(r.getInt("id"),r.getString("titre"),r.getString("text"),r.getString("type")));
        }
        return conseil;
    }
    
    public ObservableList<Conseils> getObservableConseils () throws SQLException
        {
          ObservableList<Conseils> ListConseil = FXCollections.observableArrayList();
          List<Conseils> conseil=AfficherListeConseils();
             for (Conseils   c : conseil)
               {
                 ListConseil.add(c);
               }
                 return ListConseil;    
        }
    public void supprimerConseils(int id) throws SQLException
    {
        String req="Delete From Conseils where id=(?)";
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setInt(1,id);
            prepared.executeUpdate();
        
    }
    public void modifierConseils(Conseils p) throws SQLException
    {
            String req="update Conseils set titre=(?),text=(?),type=(?) where id=(?)";
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setString(1,p.getTitre());
            prepared.setString(2,p.getTexte());
            prepared.setString(3,p.getType());
            prepared.setInt(4, p.getId());
            prepared.executeUpdate();
        
    }
    
    public ResultSet selectConseil() 
    {
        ResultSet result = null;
       
        String req = "SELECT * FROM conseils";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            result = ste.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
