/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Concour;
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

/**
 *
 * @author bn-sk
 */
public class ServiceConcour {
    
    private Connection con= DataSource.getInstance().getConnexion();
    private Statement statement;
    
    
    public ServiceConcour()
    {
        try {
            statement= con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceConcour.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AjouterConcour(Concour c) throws SQLException
    {
        String req="insert into concours (description,nbredeplaces,date) values(?,?,?)";
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setString(1,c.getDescription());
            prepared.setInt(2,c.getnbredeplaces());
            prepared.setDate(3,c.getdate());
            prepared.executeUpdate();
        
    }
    public List<Concour> AfficherListeConcours () throws SQLException
    {
        ResultSet req=statement.executeQuery("SELECT * FROM concours");
        List<Concour> liste= new ArrayList<>();
        while(req.next())
        {
            int id = req.getInt(1);
            String description=req.getString(2);
            int nbredeplaces=req.getInt(3);
            Date date=req.getDate(4);
          liste.add(new Concour( id,description, nbredeplaces, date));
        }
        return liste;
    }
    public void supprimerConcour(int id) throws SQLException
    {
        String req="Delete From concours where id=(?)";
            PreparedStatement prepared= con.prepareStatement(req);
            prepared.setInt(1,id);
            prepared.executeUpdate();
        
    }
    public void modifierConcour(int id,Concour c) throws SQLException
    {
            String req="update concours set description=(?), nbredeplaces=(?),date=(?) where id='"+id+"'";
            PreparedStatement prepared= con.prepareStatement(req);
                 prepared.setString(1,c.getDescription());
            prepared.setInt(2,c.getnbredeplaces());
                 prepared.setDate(3,c.getdate());
            prepared.executeUpdate();
        
    }
    
}
