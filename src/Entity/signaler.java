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
public class signaler {
    private int id;
    private String cause;
    private int iduser;
    private int idcom;

    public signaler() {
    }

    public signaler(String cause, int iduser, int idcom) {
        this.cause = cause;
        this.iduser = iduser;
        this.idcom = idcom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdcom() {
        return idcom;
    }

    public void setIdcom(int idcom) {
        this.idcom = idcom;
    }
    
    
}

