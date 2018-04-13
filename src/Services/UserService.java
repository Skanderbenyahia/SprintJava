/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CentreDressage;
import Entity.User;
import Technique.DataSource;
import java.io.Console;
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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author bn-sk
 */
public class UserService {

    private Connection con = DataSource.getInstance().getConnexion();
    private Statement statement;

    public UserService() {
        try {
            statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int VerificationUtilisateur(User u,String mdp) {
        int roles = -1;
        String req = "select id,username,password,roles,enabled from user where username=(?)  AND enabled IN (0,1)";
        try {
            PreparedStatement prepared = con.prepareStatement(req);
            prepared.setString(1, u.getUsername());
            ResultSet resultat = prepared.executeQuery();
            u.setId(0);
            while (resultat.next()) {

                u.setId(resultat.getInt(1));
                u.setUsername(resultat.getString(2));
                u.setPassword(resultat.getString(3));
                u.setRoles(resultat.getString(4));
                u.setEtat(resultat.getInt(5));

            }
            if (u.getId() != 0 && BCrypt.checkpw(mdp, u.getPassword().substring(0, 2) + "a" + u.getPassword().substring(3))) {
            if ((u.getRoles().equalsIgnoreCase("ROLE_CLIENT")) || (u.getRoles().equalsIgnoreCase("ROLE_PETSITTER")) || (u.getRoles().equalsIgnoreCase("ROLE_VETERINAIRE"))) {
                roles = 1;
            } else if (u.getRoles().equalsIgnoreCase("ROLE_ADMIN")) {
                roles = 0;
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return roles;
    }

    public void loggin(User u, int id) {
        String req = "UPDATE `user` SET `enabled`=0 WHERE `id`=? AND enabled=1 ";
        try {
            PreparedStatement prepared = con.prepareStatement(req);
            prepared.setInt(1, id);
            prepared.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void signIn(User u) {
        try {
            String req = "INSERT INTO `user`(nom,prenom,adresse,email,telephone,username,password,roles,enabled) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepared = con.prepareStatement(req);
            prepared.setString(1, u.getNom());
            prepared.setString(2, u.getPrenom());
            prepared.setString(3, u.getAdresse());
            prepared.setString(4, u.getEmail());
            prepared.setInt(5, u.getTel());
            prepared.setString(6, u.getUsername());
            String pw = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13));
            prepared.setString(7, pw.substring(0, 2) + "y" + pw.substring(3));
            prepared.setString(8, u.getRoles());
            prepared.setInt(9, 1);
            prepared.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> AfficherUser() throws SQLException {
        String req = "SELECT * FROM user";
        ResultSet r = statement.executeQuery(req);
        List<User> users = new ArrayList<>();
        while (r.next()) {
            users.add(new User(r.getString("nom"), r.getString("prenom"), r.getString("adresse"), r.getString("email"), r.getInt("telephone"), r.getString("roles")));
        }
        return users;
    }

    public ObservableList<User> getObservableUser() throws SQLException {
        ObservableList<User> Listuser = FXCollections.observableArrayList();
        List<User> user = AfficherUser();
        for (User u : user) {
            Listuser.add(u);
        }
        return Listuser;
    }

    public void Desactivate(int id) {

        try {
            String req = "UPDATE `user` SET `enabled`=0 WHERE `id`=?";

            PreparedStatement ste = con.prepareStatement(req);

            ste.setInt(1, id);
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public ResultSet userNom(int id) throws SQLException {
        PreparedStatement req = con.prepareStatement("SELECT nom FROM user WHERE id='" + id + "'");
        ResultSet nom = req.executeQuery();
        return nom;
    }

    public List<User> getStat() {
        String req = "select count(*) as nb,u.username from reservation_veterinaire r join user u on r.id_veterinaire_id=u.id GROUP BY id_veterinaire_id";
        List<User> liste = new ArrayList<User>();
        try {
            PreparedStatement pst = con.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User re = new User(rs.getString(2), rs.getDouble(1));

                liste.add(re);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

}
