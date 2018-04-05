/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Conseils;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
        ResultSet req=statement.executeQuery("SELECT * FROM Conseils");
        List<Conseils> liste= new ArrayList<>();
        while(req.next())
        {
          int id =req.getInt(1);
            String titre=req.getString(2);
           
            String text=req.getString(3);
            String type=req.getString(4);
            liste.add(new Conseils(id,titre,text,type));
        }
        return liste;
    }
    public void supprimerConseils(int id) throws SQLException
    {
        String req="Delete From Conseils where id=(?)";
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setInt(1,id);
            prepared.executeUpdate();
        
    }
    public void modifierConseils(int id,Conseils p) throws SQLException
    {
            String req="update Conseils set titre=(?),text=(?),type=(?) where id='"+id+"'";
            PreparedStatement prepared= con.prepareStatement(req);
          
            prepared.setString(1,p.getTitre());
            prepared.setString(2,p.getTexte());
            prepared.setString(3,p.getType());
            prepared.executeUpdate();
        
    }
}
