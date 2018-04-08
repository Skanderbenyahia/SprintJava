/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author bn-sk
 */
public class Commande {
    private int id;
    private String description;
    private Date dateCommande;
    private float amount;

    public Commande(int id, String description, Date dateCommande, float amount) {
        this.id = id;
        this.description = description;
        this.dateCommande = dateCommande;
        this.amount = amount;
    }

    public Commande(String description, Date dateCommande, float amount) {
        this.description = description;
        this.dateCommande = dateCommande;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", description=" + description + ", dateCommande=" + dateCommande + ", amount=" + amount + '}';
    }
    
    
}
