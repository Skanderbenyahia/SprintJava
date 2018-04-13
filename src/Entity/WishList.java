/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;



/**
 *
 * @author Skeez
 */
public class WishList {

    private int id_client;
    private int id_animal;
     

    public WishList(int id_animal) {
        this.id_animal = id_animal;
        
    }

    public WishList(int id_client, int id_animal) {
        this.id_client = id_client;
        this.id_animal = id_animal;
        
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    

    
}
