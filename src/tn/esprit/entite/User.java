/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entite;

import java.util.Objects;

/**
 *
 * @author Tryvl
 */
public class User 
{
    private int id;
    private int cin;
    private String username;
    private String password;
    private String email;
    private String nom;
    private String prenom;
    private int codePostal;
    private String DateNaissance;
    private int tel;
    private int num_per_conf;
    private int idAgence;
    private String role;
    private int enabled;

    public User(int id, int cin, String username, String password, String email, String nom, String prenom, int codePostal, String DateNaissance, int tel, int num_per_conf, int idAgence, String role, int enabled) {
        this.id = id;
        this.cin = cin;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.codePostal = codePostal;
        this.DateNaissance = DateNaissance;
        this.tel = tel;
        this.num_per_conf = num_per_conf;
        this.idAgence = idAgence;
        this.role = role;
        this.enabled = enabled;
    }

    public User(int cin, String username, String password, String email, String nom, String prenom, int codePostal, String DateNaissance, int tel, int num_per_conf, int idAgence, String role, int enabled) {
        this.cin = cin;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.codePostal = codePostal;
        this.DateNaissance = DateNaissance;
        this.tel = tel;
        this.num_per_conf = num_per_conf;
        this.idAgence = idAgence;
        this.role = role;
        this.enabled = enabled;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", username=" + username + ", password=" + password + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", codePostal=" + codePostal + ", DateNaissance=" + DateNaissance + ", tel=" + tel + ", num_per_conf=" + num_per_conf + ", Agence=" + idAgence + ", role=" + role + ", enabled=" + enabled + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
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

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(String DateNaissance) {
        this.DateNaissance = DateNaissance;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getNum_per_conf() {
        return num_per_conf;
    }

    public void setNum_per_conf(int num_per_conf) {
        this.num_per_conf = num_per_conf;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }
    
    
}
