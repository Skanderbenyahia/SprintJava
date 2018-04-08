/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie;
import Entity.Produit;
import Entity.User;
import Technique.DataSource;
import java.io.File;
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
import javafx.scene.image.Image;

/**
 *
 * @author bn-sk
 */
public class ProduitService {
     private Connection con= DataSource.getInstance().getConnexion();
    private Statement ste;
    public ProduitService()
    {
        try {
            ste= con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ResultSet selectProduits() 
    {
        ResultSet result = null;
       
        String req = "SELECT * FROM produit";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            result = ste.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void AjouterProduit(Produit p) throws SQLException
    {
        String req="INSERT INTO produit (categorie,libelle,description,prix,animal,Image,quantite) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,p.getCategorie());
        pre.setString(2,p.getLibelle());
        pre.setString(3,p.getDescription());
        pre.setInt(4,p.getPrix());
        pre.setString(5,p.getAnimal());
        pre.setString(6,p.getImage());
        pre.setInt(7,p.getQuantite());
        pre.executeUpdate();     
    }
    
    public List<Produit> AfficherProduit()throws SQLException
    {
        String req="SELECT * FROM produit";
        ResultSet r =ste.executeQuery(req);
        List<Produit> produits =new ArrayList<>();
        while(r.next())
        {
             produits.add(new Produit(r.getInt("id"),r.getInt("categorie"),r.getString("libelle"),r.getString("description"),r.getInt("prix"),r.getString("animal"),r.getString("Image"),r.getInt("quantite")));
        }
        return produits;
    }
    
     public ObservableList<Produit> getObservableProduit() throws SQLException
        {
          ObservableList<Produit> ListProduit = FXCollections.observableArrayList();
          List<Produit> produit =  AfficherProduit();
             for (Produit   p : produit)
               {
                 ListProduit.add(p);
               }
                 return ListProduit;    
        }
    
    public void SupprimerProduit(int id) throws SQLException   
    {
           String req="DELETE FROM Produit WHERE id='"+id+"' ";
           PreparedStatement pre = con.prepareStatement(req);
           pre.executeUpdate();    
     
    }
    
    public void ModifierProduit(Produit p) throws SQLException
    {
        String req="UPDATE Produit SET categorie=(?),libelle=(?),description=(?),prix=(?),animal=(?),Image=(?),quantite=(?) WHERE id=(?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,p.getCategorie());
        pre.setString(2,p.getLibelle());
        pre.setString(3,p.getDescription());
        pre.setInt(4,p.getPrix());
        pre.setString(5,p.getAnimal());
        pre.setString(6,p.getImage());
        pre.setInt(7,p.getQuantite());
       pre.setInt(8,p.getId());
        pre.executeUpdate();
    }
    
      
    
}
