/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.interfaces;

import java.util.ArrayList;
import tn.esprit.entite.Accident;
import tn.esprit.entite.User;

/**
 *
 * @author Tryvl
 */
public interface IAccident {
    public void ajouterAccident(User u, Accident A);
    public void modifierAccident(User u, Accident A);
    public void supprimerAccident(int id);
    public ArrayList<Accident> afficherAccident();
    public ArrayList<Accident> afficherAccidentClient(User u);
}
