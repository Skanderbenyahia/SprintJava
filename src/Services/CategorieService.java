/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie;
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
import javafx.scene.control.Alert;

/**
 *
 * @author bn-sk
 */
public class CategorieService {
     private Connection con= DataSource.getInstance().getConnexion();
    private Statement ste;
    public CategorieService()
    {
        try {
            ste= con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterCategorie(Categorie c) throws SQLException
    {
        String req="INSERT INTO categorie (libelle) VALUES (?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,c.getLibelle());
        pre.executeUpdate();     
    }
    
    public List<Categorie> AfficherCategorie()throws SQLException
    {
        String req="SELECT * FROM categorie";
        ResultSet r =ste.executeQuery(req);
        List<Categorie> categories =new ArrayList<>();
        while(r.next())
        {
             categories.add(new Categorie(r.getString("libelle")));
        }
        return categories;
    }
    public void SupprimerCentreDressage(int id) throws SQLException   
    {
        int count=0;
        String req="SELECT count(*) FROM produit where categorie='"+id+"'";
        ResultSet r =ste.executeQuery(req); 
        while(r.next())
        {
             count=r.getInt(1);
        }
        if(count==0)
        {
           String req2="DELETE FROM categorie WHERE id='"+id+"' ";
           PreparedStatement pre = con.prepareStatement(req2);
           pre.executeUpdate();    
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Categorie existante dans un produit");
            alert.setContentText("Veuillez supprimer les produits qui contient cette categorie");
            alert.showAndWait();
        }
            
    }
    
    public void ModifierCentreDressage(Categorie c) throws SQLException
    {
        String req="UPDATE categorie SET libelle=(?) WHERE id=(?) ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,c.getLibelle());
        pre.setInt(2,c.getId());
        pre.executeUpdate();    
    }
    
}
