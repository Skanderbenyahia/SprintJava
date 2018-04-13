/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Skeez
 */
public class Refuge {

    public Refuge(String libelle, int num, String email ,String region,  String adresse, String description, String image) {
        this.libelle = libelle;
        this.adresse = adresse;
        this.num = num;
        this.region = region;
        this.email = email;
        this.description = description;
        this.image = image;
    }

    public Refuge(int id,String libelle, int num ,String email ,String region,  String adresse, String description, String image) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
        this.num = num;
        this.region = region;
        this.email = email;
        this.description = description;
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.libelle);
        hash = 29 * hash + Objects.hashCode(this.adresse);
        hash = 29 * hash + this.num;
        hash = 29 * hash + Objects.hashCode(this.region);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.image);
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
        final Refuge other = (Refuge) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.num != other.num) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
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

    @Override
    public String toString() {
        return "Refuge{" + "id=" + id + ", libelle=" + libelle + ", adresse=" + adresse + ", num=" + num + ", region=" + region + ", email=" + email + ", description=" + description + ", image=" + image + '}';
    }
    private int id;
    private String libelle;
    private String adresse;
    private int num ;
    private String region;
    private String email;
    private String description;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
