/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author jabou
 */

  
public class CentreDressage {

    private int id;
    private String libelle;
    private String adresse;
    private int tel;
    private double lat;
    private double lng;
    private String description;
    private String image;
    private String ville;
    private String gouvernerat;
    private int code_psotale;
    
       public CentreDressage(String libelle, String adresse, int tel, double lat, double lng, String description, String image) 
       {
        this.libelle = libelle;
        this.adresse = adresse;
        this.tel = tel;
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.image = image;
    }


    public CentreDressage()
    {
    }

    public CentreDressage(String libelle, String adresse, int tel, double lat, double lng, String description, String image, String ville, String gouvernerat, int code_psotale) {
        this.libelle = libelle;
        this.adresse = adresse;
        this.tel = tel;
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.image = image;
        this.ville = ville;
        this.gouvernerat = gouvernerat;
        this.code_psotale = code_psotale;
    }

    public CentreDressage(int id, String libelle, String adresse, int tel, double lat, double lng, String description, String image, String ville, String gouvernerat, int code_psotale) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
        this.tel = tel;
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.image = image;
        this.ville = ville;
        this.gouvernerat = gouvernerat;
        this.code_psotale = code_psotale;
    }
    
    

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getLibelle() {return libelle;}
    public void setLibelle(String libelle) {this.libelle = libelle;}

    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse; }

    public int getTel() {return tel;}
    public void setTel(int tel) { this.tel = tel;}

    public double getLat() {return lat;}
    public void setLat(double lat) {this.lat = lat;}

    public double getLng() {return lng;}
    public void setLng(double lng) {this.lng = lng;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}
    
    public String getVille() {return ville;}
    public void setVille(String ville) {this.ville = ville;}

    public String getGouvernerat() {return gouvernerat;}
    public void setGouvernerat(String gouvernerat) {this.gouvernerat = gouvernerat;}

    public int  getCode_psotale() {return code_psotale;}
    public void setCode_psotale(int  code_psotale) {this.code_psotale = code_psotale;}

    @Override
    public String toString() {
        return "CentreDressage{" + "id=" + id + ", libelle=" + libelle + ", adresse=" + adresse + ", tel=" + tel + ", lat=" + lat + ", lng=" + lng + ", description=" + description + ", image=" + image + ", ville=" + ville + ", gouvernerat=" + gouvernerat + ", code_psotale=" + code_psotale + '}';
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.libelle);
        hash = 59 * hash + Objects.hashCode(this.adresse);
        hash = 59 * hash + this.tel;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.lat) ^ (Double.doubleToLongBits(this.lat) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.lng) ^ (Double.doubleToLongBits(this.lng) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CentreDressage other = (CentreDressage) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }
        if (Double.doubleToLongBits(this.lat) != Double.doubleToLongBits(other.lat)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lng) != Double.doubleToLongBits(other.lng)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }   
}
