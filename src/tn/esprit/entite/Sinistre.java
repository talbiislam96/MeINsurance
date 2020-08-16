/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entite;

/**
 *
 * @author Tryvl
 */
public class Sinistre {
    
    private int id;
    private String type;
    private String dateOuverture;
    private String dateFermeture;
    private String lieu;
    private String libelle;
    private String googleMaps;
    private String description;
    private String image;
    private int idAccident;
    private int idClient;
    private String traiter;
    

    public Sinistre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public String getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(String dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getGoogleMaps() {
        return googleMaps;
    }

    public void setGoogleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
    }

    public String getTraiter() {
        return traiter;
    }

    public void setTraiter(String traiter) {
        this.traiter = traiter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdAccident() {
        return idAccident;
    }

    public void setIdAccident(int idAccident) {
        this.idAccident = idAccident;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Sinistre{" + "id=" + id + ", type=" + type + ", dateOuverture=" + dateOuverture + ", dateFermeture=" + dateFermeture + ", lieu=" + lieu + ", libelle=" + libelle + ", googleMaps=" + googleMaps + ", description=" + description + ", image=" + image + ", idAccident=" + idAccident + ", idClient=" + idClient + ", traiter=" + traiter + '}';
    }
}
