/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import Entity.jaime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author ASUS
 */
public class JaimeService {
     private Connection cnx = Technique.DataSource.getInstance().getConnexion();

    

 
public void ajouterJaime(jaime j){
        try {
            String requete = "INSERT INTO jaime (id_user,id_centre,etat,etat2) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, j.getId_user());
            pst.setInt(2, j.getId_centre());
            pst.setInt(3, j.getEtat());
            pst.setInt(4, j.getEtat2());
            pst.executeUpdate();
            System.out.println("j'aime");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }

public void supprimerJaime(int a,int b){
        try {
            String requete3 = "DELETE FROM jaime WHERE id_user="+a+" and etat=1 and etat2=0 and id_centre="+b;
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.executeUpdate();
            System.out.println("j'aime pas");  
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
public void supprimerJaimePas(int a,int b){
        try {
            String requete3 = "DELETE FROM jaime WHERE id_user="+a+" and etat2=1 and etat=0 and id_centre="+b;
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.executeUpdate();
            System.out.println("j'aime pas");  
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
public boolean GetJaimeByIdCentre(int a,int b)
{
    try {
            System.out.println("fonction GetJaimeByIdCentre(int a,int b) ");
            String requete3 = "Select * FROM jaime WHERE id_centre="+a+" and  etat=1 and id_user="+b;
            PreparedStatement pst = cnx.prepareStatement(requete3);
            ResultSet r =  pst.executeQuery();
           if (r.next())
           {
                System.out.println("l9itou"); 
               return true;
               
           }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("mal9itouch");
        }
    return false;
}

public int GetNbrJaime(int a)
{
    int nbr=0;
    try {
            String requete3 = "Select count(*) FROM  jaime WHERE id_centre="+a+" and etat=1";
            PreparedStatement pst = cnx.prepareStatement(requete3);
               ResultSet resultSet = pst.executeQuery();
           while (resultSet.next())
               nbr=resultSet.getInt(1);
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return nbr;
}
public int GetNbrJaimepas(int a)
{
    int nbr=0;
    try {
            String requete3 = "Select count(*) FROM  jaime WHERE id_centre="+a+" and etat2=1";
            PreparedStatement pst = cnx.prepareStatement(requete3);
               ResultSet resultSet = pst.executeQuery();
           while (resultSet.next())
               nbr=resultSet.getInt(1);
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return nbr;
}
}


