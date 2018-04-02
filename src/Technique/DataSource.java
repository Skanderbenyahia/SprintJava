/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bn-sk
 */
public class DataSource {
    
    private static DataSource data;

    public static DataSource getData() {
        return data;
    }
    private Connection connexion;
    private String user="root";
    private String password="";
    private String url="jdbc:mysql://localhost:3306/pidev";
    
    private DataSource()
    {
        try {
            connexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static DataSource getInstance()
    {
      if(data==null)
      {
          data = new DataSource();
      }
      return data;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }


    public Connection getConnexion() {
        return connexion;
    }
    
    
}
