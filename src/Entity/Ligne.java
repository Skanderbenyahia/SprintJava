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
public class Ligne {
    
    private int id;
    private int id_produit;
    private int id_client;
    private String image;
    private int quantite;
    private float prix;

    public Ligne(int id, int id_produit, int id_client, String image, int quantite, float prix) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_client = id_client;
        this.image = image;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Ligne(int id_produit, int id_client, String image, int quantite, float prix) {
        this.id_produit = id_produit;
        this.id_client = id_client;
        this.image = image;
        this.quantite = quantite;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    
    
}
