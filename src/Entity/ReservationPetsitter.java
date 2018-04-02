/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author jabou
 */
public class ReservationPetsitter {
    private int id;
    private Date dateD;
    private Date dateF;
    private double prix;
    private double encaisser;
    private int idPetsitter;
    private int idUser;

    public ReservationPetsitter() {
    }

    public ReservationPetsitter(Date dateD, Date dateF, double prix, double encaisser, int idPetsitter, int idUser) {
        this.dateD = dateD;
        this.dateF = dateF;
        this.prix = prix;
        this.encaisser = encaisser;
        this.idPetsitter = idPetsitter;
        this.idUser = idUser;
    }

    public ReservationPetsitter(int id, Date dateD, Date dateF, double prix, double encaisser, int idPetsitter, int idUser) {
        this.id = id;
        this.dateD = dateD;
        this.dateF = dateF;
        this.prix = prix;
        this.encaisser = encaisser;
        this.idPetsitter = idPetsitter;
        this.idUser = idUser;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public Date getDateD() {return dateD;}
    public void setDateD(Date dateD) {this.dateD = dateD;}

    public Date getDateF() {return dateF;}
    public void setDateF(Date dateF) {this.dateF = dateF;}

    public double getPrix() {return prix;}
    public void setPrix(double prix) {this.prix = prix;}

    public double getEncaisser() {return encaisser;}
    public void setEncaisser(double encaisser) {this.encaisser = encaisser;}

    public int getIdPetsitter() {return idPetsitter;}
    public void setIdPetsitter(int idPetsitter) {this.idPetsitter = idPetsitter;}

    public int getIdUser() {return idUser;}
    public void setIdUser(int idUser) {this.idUser = idUser;}

    @Override
    public String toString() 
    {
        return "ReservationPetsitter{" + "id=" + id + ", dateD=" + dateD + ", dateF=" + dateF + ", prix=" + prix + ", encaisser=" + encaisser + ", idPetsitter=" + idPetsitter + ", idUser=" + idUser + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.dateD);
        hash = 37 * hash + Objects.hashCode(this.dateF);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.prix) ^ (Double.doubleToLongBits(this.prix) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.encaisser) ^ (Double.doubleToLongBits(this.encaisser) >>> 32));
        hash = 37 * hash + this.idPetsitter;
        hash = 37 * hash + this.idUser;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationPetsitter other = (ReservationPetsitter) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (Double.doubleToLongBits(this.encaisser) != Double.doubleToLongBits(other.encaisser)) {
            return false;
        }
        if (this.idPetsitter != other.idPetsitter) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (!Objects.equals(this.dateD, other.dateD)) {
            return false;
        }
        if (!Objects.equals(this.dateF, other.dateF)) {
            return false;
        }
        return true;
    }
    
    
    
    

}
