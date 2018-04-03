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
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private int tel;
    private String username;
    private String password;
    private String roles;
    private int etat;

    public User(int id, String nom, String prenom, String adresse, String email, int tel, String username, String password, String roles, int etat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.etat = etat;
    }

    public User() {
    }

    public User(String nom, String prenom, String adresse, String email, int tel, String username, String password, String roles, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.etat = etat;
    }

    public User(int id, String username, String password, String roles, int etat) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.etat = etat;
    }

    public User(String username, String password, String roles, int etat) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

   
    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email + ", tel=" + tel + ", roles=" + roles + ", username=" + username + ", password=" + password + ", etat=" + etat + '}';
    }

    public User(String nom, String prenom, String adresse, String email, int tel, String roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
        this.roles = roles;
    }

    

   
    
    
}
