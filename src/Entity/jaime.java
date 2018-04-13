/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class jaime {
    private int id_jaime;
    private int id_user;
    private int id_centre;
    private int etat;
    private int etat2;

    public jaime(int id_user, int id_centre, int etat, int etat2) {
        this.id_user = id_user;
        this.id_centre = id_centre;
        this.etat = etat;
        this.etat2 = etat2;
    }

    public int getEtat2() {
        return etat2;
    }

    public void setEtat2(int etat2) {
        this.etat2 = etat2;
    }

    
    public jaime() {
    }

    public jaime(int id_user, int id_centre, int etat) {
        this.id_user = id_user;
        this.id_centre = id_centre;
        this.etat = etat;
    }

    public int getId_jaime() {
        return id_jaime;
    }

    public void setId_jaime(int id_jaime) {
        this.id_jaime = id_jaime;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_centre() {
        return id_centre;
    }

    public void setId_centre(int id_centre) {
        this.id_centre = id_centre;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    
}
