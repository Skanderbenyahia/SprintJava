/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Mimouna
 */
public class centreToilettage {
    private int id ;
    private String libelle ;
    private String adresse; 
    private int tel ;
    private String description ;
    private String image ;

    public centreToilettage() {
    }

    public centreToilettage(int id, String libelle, String adresse, int tel, String description, String image) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
        this.tel = tel;
        this.description = description;
        this.image = image;
    }

    public centreToilettage(String libelle, String adresse, int tel, String description, String image) {
        this.libelle = libelle;
        this.adresse = adresse;
        this.tel = tel;
        this.description = description;
        this.image = image;
    }
    
    
    

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

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
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
