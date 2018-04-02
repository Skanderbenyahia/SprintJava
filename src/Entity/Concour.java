/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author bn-sk
 */
public class Concour {

    private int id;
    private String description;
    private int nbredeplaces;
    private Date date;

    public Concour() {
    }

    public Concour(int id, String description, int nbredeplaces, Date date) {
        this.id = id;
        this.description = description;
        this.description = description;
        this.nbredeplaces = nbredeplaces;
        this.date = date;
    }

    public Concour(String description, int nbredeplaces, Date date) {
        this.description = description;
        this.nbredeplaces = nbredeplaces;
        this.date = date;
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

    public int getnbredeplaces() {
        return nbredeplaces;
    }

    public int getPlaces() {
        return nbredeplaces;
    }

    public void setnbredeplaces(int nbredeplaces) {
        this.nbredeplaces = nbredeplaces;
    }

    public Date getdate() {
        return date;
    }
    public Date getDate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "concour{" + "id=" + id + ", description=" + description + "nbre de places =" + nbredeplaces + ", date=" + date + '}';
    }

}
