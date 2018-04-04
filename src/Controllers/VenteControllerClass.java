/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.Produit;

/**
 *
 * @author jabou
 */
public interface VenteControllerClass {
     public abstract void preloadData(Produit p);
    public abstract void preloadData(Categorie c);
}
