/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.signaler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mimouna
 */
public class signalerService {

    private static Connection conn = Technique.DataSource.getInstance().getConnexion();

    private static Statement st;
    private static ResultSet r;
    private static PreparedStatement pst;

    signaler c = new signaler();

    public void ajouter_signal(signaler s) {
        try {
            String requete = "insert into signaler (cause,iduser,idcom) values (?,?,?)";

            pst = conn.prepareStatement(requete);
            pst.setString(1, s.getCause());
            pst.setInt(2, s.getIduser());
            pst.setInt(3, s.getIdcom());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(signalerService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public boolean GetSignaltest(int a, int b) {
        try {

            String requete3 = "Select * FROM signaler WHERE iduser=" + a + "  and idcom=" + b;
            PreparedStatement pst = conn.prepareStatement(requete3);
            ResultSet r = pst.executeQuery();
            if (r.next()) {

                return true;

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return false;
    }

    public int GetNbrSignal(int a) {
        int nbr = 0;
        try {
            String requete3 = "Select count(*) FROM  signaler Where idcom=" + a;
            PreparedStatement pst = conn.prepareStatement(requete3);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                nbr = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbr;
    }

}
