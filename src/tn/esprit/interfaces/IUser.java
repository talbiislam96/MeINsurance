/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.interfaces;

import java.util.ArrayList;
import java.util.List;
import tn.esprit.entite.User;

/**
 *
 * @author Tryvl
 */
public interface IUser {
    public void creerCompteClient(User u);
    public void creerCompteAssureur(User u);
    public User authentifier(String username, String password);
    public void activerCompte(int id);
    public void desactiverCompte(int id);
    public void modifierCompte(int id, User u);
    public void supprimerCompte(int id);
    public boolean uniqueUsername(String username);
    public boolean uniqueMail(String mail);
    public ArrayList<User> getAllEnabledUser();
    public ArrayList<User> getAllDisabledUser();
    
}
