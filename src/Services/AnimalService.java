/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Animal;
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
 * @author Skeez
 */
public class AnimalService {

    private Connection con = DataSource.getInstance().getConnexion();
    private Statement ste;

    public AnimalService() throws SQLException {
        ste = con.createStatement();

    }

    public void AjouterAnimal(Animal a) throws SQLException {
        String req = "INSERT INTO animal(nom, race, sexe, age, etat, description, demande, taille, region, espece, image) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, a.getNom());
        pre.setString(2, a.getRace());
        pre.setString(3, a.getSexe());
        pre.setString(4, a.getAge());
        pre.setString(5, a.getEtat());
        pre.setString(6, a.getDescription());
        pre.setInt(7, a.getDemande());
        pre.setString(8, a.getTaille());
        pre.setString(9, a.getRegion());
        pre.setString(10, a.getEspece());
        pre.setString(11, a.getImage());
        pre.executeUpdate();
        System.out.println("animal Ajoutée");
    }

    public List<Animal> AfficherAnimal() throws SQLException {
        String req = "SELECT * FROM animal";
        ResultSet r = ste.executeQuery(req);
        List<Animal> Animals = new ArrayList<>();
        while (r.next()) {
            Animals.add(new Animal(r.getString(3), r.getString(4), r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9), r.getString(10), r.getString(11), r.getString(12), r.getInt(13)));
        }
        return Animals;
    }

    public ObservableList<Animal> getObservableAnimal() throws SQLException {
        ObservableList<Animal> ListAnimal = FXCollections.observableArrayList();
        List<Animal> Animals = Animals = AfficherAnimal();
        for (Animal c : Animals) {
            ListAnimal.add(c);
        }
        return ListAnimal;
    }

    public void SupprimerAnimal(int id) throws SQLException {
        String req = "DELETE FROM animal WHERE id='" + id + "' ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate();
    }

    public void ModifierAnimal(Animal a) throws SQLException {
        String req = "UPDATE animal SET nom=?, race=? age=?, etat=?, description=?, demande=?, taille=?, region=?, espece=? WHERE id=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, a.getNom());
        pre.setString(2, a.getRace());
        pre.setString(3, a.getAge());
        pre.setString(4, a.getEtat());
        pre.setString(5, a.getDescription());
        pre.setInt(6, a.getDemande());
        pre.setString(7, a.getTaille());
        pre.setString(8, a.getRegion());
        pre.setString(9, a.getEspece());
        pre.setInt(10, a.getId());
        pre.executeUpdate();
    }

    public void ChangerEtat(int id) throws SQLException {
        String req = "UPDATE animal set etat=? WHERE id='" + id + "' ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, "adopte");
        pre.executeUpdate();
    }

    public List<Animal> selectAnimals() {
        ResultSet rs = null;
        List<Animal> animal = new ArrayList<Animal>();
        String req = "SELECT * FROM animal";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            rs = ste.executeQuery();
            while (rs.next()) {
                Animal p = new Animal(rs.getInt(1),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13));
                animal.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return animal;
    }

    public ResultSet selectAnimalFront(int id) {
        ResultSet result = null;

        String req = "SELECT * FROM animal where id='" + id + "'";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            result = ste.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void increment(Animal a) throws SQLException {
        String req = "UPDATE animal SET demande=? WHERE id=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, a.getDemande() + 1);
        pre.setInt(2, a.getId());
        pre.executeUpdate();
    }

}
