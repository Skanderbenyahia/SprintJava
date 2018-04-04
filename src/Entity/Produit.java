/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author bn-sk
 */

public class Produit {
    private int id;
    private int categorie;
    private String libelle;
    private String description;
    private int prix;
    private String animal;
    private String image;
    private int quantite;

    public Produit(int id, int categorie, String libelle, String description, int prix, String animal, String image, int quantite) {
        this.id = id;
        this.categorie = categorie;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.animal = animal;
        this.image = image;
        this.quantite = quantite;
    }

    public Produit(int categorie, String libelle, String description, int prix, String animal, String image, int quantite) {
        this.categorie = categorie;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.animal = animal;
        this.image = image;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "categorie=" + categorie + ", libelle=" + libelle + ", description=" + description + ", prix=" + prix + ", animal=" + animal + ", image=" + image + ", quantite=" + quantite + '}';
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    
}
