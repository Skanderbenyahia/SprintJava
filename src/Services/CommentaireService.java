/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mimouna
 */
public class CommentaireService 
{
  
    private static Connection conn=Technique.DataSource.getInstance().getConnexion();
   
    private static Statement st;
    private static ResultSet r;
    private static PreparedStatement pst;
   
    Commentaire c=new Commentaire();
    


    public void ajouter_commentaire(Commentaire c) {
try {
            String requete="insert into commentaire (contenu,idUser,idCentre,nbrSignal,date_com) values (?,?,?,?,?)";
           
            pst=conn.prepareStatement(requete);
            pst.setString(1,c.getContenu());
            pst.setInt(2,c.getIdUser());
            pst.setInt(3,c.getIdCentre());
              pst.setInt(4,c.getNbrSignal());
              pst.setTimestamp(5, Timestamp.valueOf(c.getDate_com()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        
    }    }
    
    public void modifier_commentaire(Commentaire c, int id,int iduser) {
    try {
          String req="update commentaire set  contenu= '"+c.getContenu()+"',date_com='"+c.getDate_com()+"' where id="+id+" and idUser="+iduser+"";
          st=conn.createStatement();
            st.executeUpdate(req);
      } catch (SQLException ex) {
          Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    

    public List<Commentaire> AfficherCommentaireCentre(int id) {
        String req="select * from commentaire where idCentre="+id+"";
        List<Commentaire> commentaires = new ArrayList<>();
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               Commentaire c= new Commentaire ();
                c.setId(rs.getInt(1));
                c.setContenu(rs.getString(2));
                c.setNbrSignal(rs.getInt(3));
                c.setIdUser(rs.getInt(4));
                c.setIdCentre(rs.getInt(5));
                c.setDate_com(rs.getTimestamp(6).toLocalDateTime());
                commentaires.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentaires;
    }

    public void supprimer_commentaire(int id,int iduser) {
        String req="Delete from commentaire where id="+id+" and idUser="+iduser+"";
        try {
            st=conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Commentaire> findAll(int id) {
        String sql = "SELECT * FROM commentaire where idCentre="+id+"";
        List<Commentaire> commentaires = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 Commentaire c = new Commentaire();
                c.setId(resultSet.getInt(1));
                c.setContenu(resultSet.getString(2));
                c.setNbrSignal(resultSet.getInt(3));
                c.setIdUser(resultSet.getInt(4));
                c.setIdCentre(resultSet.getInt(5));
                
                commentaires.add(c);
            }
        } catch (SQLException c) {
            
        }
        return commentaires;
    }
   

    public String Username(int id,int id_comment) {
        String nom="";
        String prenom="";
        
        
    
        String sql = "SELECT u.nom,u.prenom FROM user u join commentaire c "
                + " where u.id="+id+" and c.id="+id_comment;
       

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                
                nom=resultSet.getString(1);
                prenom=resultSet.getString(2);
               
                
            }
        } catch (SQLException c) {
            
        }
        return nom+" "+prenom;
    }
        public void misajour(int id) {
        String req="Delete from commentaire where id="+id;
        try {
            st=conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
   }
    
