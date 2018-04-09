/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Categorie;
import Entity.Commande;
import Entity.Ligne;
import Entity.Produit;
import Entity.Session;
import Entity.User;
import Technique.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    private Connection con = DataSource.getInstance().getConnexion();
    private Statement ste;

    public ProduitService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet selectProduits() {
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

    public ResultSet selectProduitsdistinct(int id) {
        ResultSet result = null;

        String req = "SELECT * FROM produit where id=?";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, id);
            result = ste.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void AjouterProduit(Produit p) throws SQLException {
        String req = "INSERT INTO produit (categorie,libelle,description,prix,animal,Image,quantite) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, p.getCategorie());
        pre.setString(2, p.getLibelle());
        pre.setString(3, p.getDescription());
        pre.setInt(4, p.getPrix());
        pre.setString(5, p.getAnimal());
        pre.setString(6, p.getImage());
        pre.setInt(7, p.getQuantite());
        pre.executeUpdate();
    }

    public List<Produit> AfficherProduit() throws SQLException {
        String req = "SELECT * FROM produit";
        ResultSet r = ste.executeQuery(req);
        List<Produit> produits = new ArrayList<>();
        while (r.next()) {
            produits.add(new Produit(r.getInt("id"), r.getInt("categorie"), r.getString("libelle"), r.getString("description"), r.getInt("prix"), r.getString("animal"), r.getString("Image"), r.getInt("quantite")));
        }
        return produits;
    }

    public ObservableList<Produit> getObservableProduit() throws SQLException {
        ObservableList<Produit> ListProduit = FXCollections.observableArrayList();
        List<Produit> produit = AfficherProduit();
        for (Produit p : produit) {
            ListProduit.add(p);
        }
        return ListProduit;
    }

    public void SupprimerProduit(int id) throws SQLException {
        String req = "DELETE FROM Produit WHERE id='" + id + "' ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate();

    }

    public void ModifierProduit(Produit p) throws SQLException {
        String req = "UPDATE Produit SET categorie=(?),libelle=(?),description=(?),prix=(?),animal=(?),Image=(?),quantite=(?) WHERE id=(?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, p.getCategorie());
        pre.setString(2, p.getLibelle());
        pre.setString(3, p.getDescription());
        pre.setInt(4, p.getPrix());
        pre.setString(5, p.getAnimal());
        pre.setString(6, p.getImage());
        pre.setInt(7, p.getQuantite());
        pre.setInt(8, p.getId());
        pre.executeUpdate();
    }

    public void ajoutpanier(Produit p, int u) throws SQLException {
        String req = "select produit,quantite,prix from ligne where produit=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, p.getId());
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            String req2 = "UPDATE ligne SET quantite=(?),prix=(?) WHERE produit=(?)";
            pre = con.prepareStatement(req2);
            pre.setInt(1, rs.getInt(2) + 1);
            pre.setFloat(2, rs.getInt(3));
            pre.setInt(3, rs.getInt(1));
            pre.executeUpdate();
        } else {
            String req3 = "INSERT INTO ligne (idClient,quantite,prix,Image,produit) VALUES (?,?,?,?,?)";
            pre = con.prepareStatement(req3);
            pre.setInt(1, u);
            pre.setInt(2, 1);
            pre.setInt(3, p.getPrix());
            pre.setString(4, p.getImage());
            pre.setInt(5, p.getId());
            pre.executeUpdate();
        }
    }

    public List<Ligne> AfficherPanier() {
        List<Ligne> lignes = new ArrayList<Ligne>();

        String req = "SELECT * FROM ligne";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

                Ligne l = new Ligne(rs.getInt(1), rs.getInt(6), rs.getInt(2), rs.getString(5), rs.getInt(3), rs.getInt(4));
                lignes.add(l);
                System.out.println(lignes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ligne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lignes;
    }

    public void SupprimerProduitPanier(int id) throws SQLException {
        String req = "DELETE FROM ligne WHERE id='" + id + "' ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate();

    }

    public void CommanderPanier(int id) throws SQLException {

        Date input=new Date();
        LocalDate Dated = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Instant i = Instant.from(Dated.atStartOfDay(ZoneId.systemDefault()));
        java.util.Date date = Date.from(i);
        java.sql.Date aujourdhui = new java.sql.Date(date.getTime());
        
        
        float total = 0;
        String description = "";

        String req1 = "select SUM(l.prix*l.quantite) as total from ligne l where l.idClient='" + id + "'";
        PreparedStatement pre1 = con.prepareStatement(req1);
        ResultSet r1 = pre1.executeQuery();
        while (r1.next()) {
            total = r1.getFloat(1);
        }
        System.out.println(total);
        String req2 = "select CONCAT(l.quantite,'*',p.libelle) as description FROM ligne l,produit p where l.idClient='" + id + "' and p.id=l.Produit";
        PreparedStatement pre2 = con.prepareStatement(req2);
        ResultSet r2 = pre2.executeQuery();
        while (r2.next()) {
            description = r2.getString(1);
        }
        System.out.println(description);
        Commande c = new Commande(description, aujourdhui, total);

        String req3 = "INSERT INTO commandes (amount,description,date_commande) VALUES (?,?,?)";
        PreparedStatement pre3 = con.prepareStatement(req3);
        pre3.setFloat(1, c.getAmount());
        pre3.setString(2, c.getDescription());
        pre3.setDate(3, (java.sql.Date) c.getDateCommande());
        pre3.execute();
        
        String req = "DELETE FROM ligne WHERE idClient='" + id + "' ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate();

    }
    
   public  float totalpanier(int id) throws SQLException
    {
        float total = 0;
        String description = "";

        String req1 = "select SUM(l.prix*l.quantite) as total from ligne l where l.idClient='" + id + "'";
        PreparedStatement pre1 = con.prepareStatement(req1);
        ResultSet r1 = pre1.executeQuery();
        while (r1.next()) {
            total = r1.getFloat(1);
        }
        return total;
    }
   
   
    
    

}
