package Entity;


import java.time.LocalDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mimouna
 */
public class Commentaire {
     private int id ;
    private String contenu;
    private int nbrSignal;
    private int idUser;
    private int idCentre;
    private LocalDateTime date_com;

    public Commentaire(String contenu, int nbrSignal, int idUser, int idCentre, LocalDateTime date_com) {
        this.contenu = contenu;
        this.nbrSignal = nbrSignal;
        this.idUser = idUser;
        this.idCentre = idCentre;
        this.date_com = date_com;
    }
    

    public LocalDateTime getDate_com() {
        return date_com;
    }

    public void setDate_com(LocalDateTime date_com) {
        this.date_com = date_com;
    }

    public Commentaire() {
    }

    public Commentaire(String contenu, int nbrSignal, int idUser, int idCentre) {
        this.contenu = contenu;
        this.nbrSignal = nbrSignal;
        this.idUser = idUser;
        this.idCentre = idCentre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getNbrSignal() {
        return nbrSignal;
    }

    public void setNbrSignal(int nbrSignal) {
        this.nbrSignal = nbrSignal;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(int idCentre) {
        this.idCentre = idCentre;
    }
    
}

    

