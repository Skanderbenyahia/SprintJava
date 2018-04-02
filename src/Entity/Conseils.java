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

public class Conseils {
    
    private int id;
   
    private String titre;
    private String texte;
    private String type;

    public Conseils() {
    }

    public Conseils(String titre, String texte, String type) {
        this.titre = titre;
        this.texte = texte;
        this.type = type;
    }

    public Conseils(int id, String titre, String texte, String type) {
        this.id = id;
        this.titre = titre;
        this.texte = texte;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "conseils{" + "id=" + id + ", titre=" + titre + ", texte=" + texte + ", type=" + type + '}';
    }
  

}