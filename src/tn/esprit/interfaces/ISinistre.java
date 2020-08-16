/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.interfaces;

import java.util.ArrayList;
import tn.esprit.entite.Sinistre;
import tn.esprit.entite.User;

/**
 *
 * @author Tryvl
 */
public interface ISinistre 
{
    public void ajouterSinsitre(Sinistre s, User u);
    public void modifierSinistre(Sinistre s, User u);
    public void supprimerSinistre(Sinistre s, User u);
    public void traiterSinistre(Sinistre s);
    public ArrayList<Sinistre> sinistreTraiter();
    public ArrayList<Sinistre> sinistreNonTraiter();
    public ArrayList<Sinistre> sinistreClient(User u);
}
