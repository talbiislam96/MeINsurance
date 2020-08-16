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
public class Accident 
{
    private int id;
    private int idClient;
    private String adresse;
    private int idVehicule;

    public Accident() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
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
        final Accident other = (Accident) obj;
        if (this.idClient != other.idClient) {
            return false;
        }
        if (this.idVehicule != other.idVehicule) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accident{" + "id=" + id + ", idClient=" + idClient + ", adresse=" + adresse + ", idVehicule=" + idVehicule + '}';
    }
    
    
}
