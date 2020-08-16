/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tn.esprit.entite.Agence;
import tn.esprit.entite.User;
import tn.esprit.util.ConnexionDB;
import tn.esprit.interfaces.IUser;

/**
 *
 * @author Tryvl
 */
public class UserService implements IUser{

    private Connection con;
    private PreparedStatement pst;
    public UserService()
    {
        con = ConnexionDB.getInstance().getCnx();
    }
    
    @Override
    public User authentifier(String username, String password) {
        
        String query ="SELECT * FROM USER WHERE login='"+username+"' AND password ='"+password+"'";
        User user = new User();
        try 
        {
            pst = con.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            if(result.next())
            {
                user.setId(result.getInt(1));
                user.setCin(result.getInt(2));
                user.setNom(result.getString(4));
                user.setPrenom(result.getString(5));
                user.setCodePostal(result.getInt(6));
                user.setDateNaissance(result.getString(7));
                user.setTel(result.getInt(8));
                user.setEmail(result.getString(9));
                user.setUsername(result.getString(11));
                user.setIdAgence(result.getInt(12));
                user.setRole(result.getString(13));
                user.setEnabled(result.getInt(14));
                
                
                System.out.println("user success"); 
                return user;
            }
            else
                System.out.println("login error");
        } catch (SQLException ex) {
            System.out.println("Error while autheticating " + ex.getMessage());
        }
        return user;
    }

    @Override
    public void activerCompte(int id) {
        
        String query ="UPDATE USER SET enabled=1 where id="+id;
        
        try 
        {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            
            System.out.println("enabled success");
        } catch (SQLException ex) {
            System.out.println("error while updating user enabled : "+ex.getMessage());
        }
        
    }

    @Override
    public void creerCompteClient(User u) {
        u.setRole("ROLE_USER");
        String query = "INSERT INTO USER ( `cin`, `password`, `nom`, `prenom`, `codePostal`, `dateNaissance`, `tel`, "
                + "`email`, `login`, `role`, `idAgence`) VALUES ("+u.getCin()+",'"+u.getPassword()+"','"+u.getNom()+"','"+u.getPrenom()+"',"
                +u.getCodePostal()+",'"+u.getDateNaissance()+"',"+u.getTel()+",'"+u.getEmail()+"','"+u.getUsername()+"','"+u.getRole()+"', '"+u.getIdAgence()+"')";

        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate(query);
            
            System.out.println("creating user success");
        } catch (SQLException ex) {
            System.out.println("error in creating client account : " + ex.getMessage());
        }
    }

    @Override
    public void creerCompteAssureur(User u) {
        u.setRole("ROLE_ASSUREUR");
        String query = "INSERT INTO USER ( `cin`, `password`, `nom`, `prenom`, `codePostal`, `dateNaissance`, `tel`, "
                + "`email`, `login`, `role`, `idAgence`) VALUES ("+u.getCin()+",'"+u.getPassword()+"','"+u.getNom()+"','"+u.getPrenom()+"',"
                +u.getCodePostal()+",'"+u.getDateNaissance()+"',"+u.getTel()+",'"+u.getEmail()+"','"+u.getUsername()+"','"+u.getRole()+"', '"+u.getIdAgence()+"')";
        
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate(query);
            
            System.out.println("creating user success");
        } catch (SQLException ex) {
            System.out.println("error in creating client account : " + ex.getMessage());
        }
    }

    @Override
    public void modifierCompte(int id, User u) {
        String query = "UPDATE USER set nom='"+u.getNom()+"' , "
                + "prenom='"+u.getPrenom()+"' , "
                + "tel="+u.getTel()+" , "
                + "password='"+u.getPassword()+"' , "
                + "codePostal="+u.getCodePostal()+" , "
                + "email='"+u.getEmail()+"' , "
                + "idAgence="+u.getIdAgence()+" WHERE id="+id;
    
        try {
            pst = con.prepareStatement(query);
            
            pst.executeUpdate();
            System.out.println("update success");
        } catch (SQLException ex) {
            System.out.println("Error while updating user Data : "+ex.getMessage());
        }
    }

    @Override
    public void supprimerCompte(int id) {
        String query ="DELETE FROM user WHERE id="+id;
        
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("removing account success");
        } catch (SQLException ex) {
            System.out.println("error while deleting account : "+ex.getMessage());
        }
    }

    @Override
    public boolean uniqueUsername(String username) {
        String query = "SELECT * FROM user where login='"+username+"'";
        
        try {
            pst= con.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            
            if(result.next())
                return false;
            else
                return true;
            
        } catch (SQLException ex) {
            System.out.println("unique username error : "+ex.getMessage());
        }
        
        return false;
    }

    @Override
    public boolean uniqueMail(String mail) {
        String query = "SELECT * FROM user where email='"+mail+"'";
        
        try {
            pst= con.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            
            if(result.next())
                return false;
            else
                return true;
            
        } catch (SQLException ex) {
            System.out.println("unique mail error : "+ex.getMessage());
        }
        
        return false;
    }

    @Override
    public ArrayList<User> getAllEnabledUser() {
        
        ArrayList<User> users = new ArrayList<>();
        
        String query = "SELECT * FROM user where enabled =1";
        try {
            pst = con.prepareStatement(query);
            
            ResultSet result = pst.executeQuery();
            while(result.next())
            {
                User u = new User();
                u.setId(result.getInt(1));
                u.setCin(result.getInt(2));
                u.setPassword(result.getString(3));
                u.setNom(result.getString(4));
                u.setPrenom(result.getString(5));
                u.setCodePostal(result.getInt(6));
                u.setDateNaissance(result.getString(7));
                u.setTel(result.getInt(8));
                u.setEmail(result.getString(9));
                u.setUsername(result.getString(11));
                u.setRole(result.getString(13));
                
                users.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("error while extracting enabled users " + ex.getMessage());
        }
        
        return users;
    }

    @Override
    public ArrayList<User> getAllDisabledUser() {
        ArrayList<User> users = new ArrayList<>();
        
        String query = "SELECT * FROM user where enabled =0";
        try {
            pst = con.prepareStatement(query);
            
            ResultSet result = pst.executeQuery();
            while(result.next())
            {
                User u = new User();
                u.setId(result.getInt(1));
                u.setCin(result.getInt(2));
                u.setPassword(result.getString(3));
                u.setNom(result.getString(4));
                u.setPrenom(result.getString(5));
                u.setCodePostal(result.getInt(6));
                u.setDateNaissance(result.getString(7));
                u.setTel(result.getInt(8));
                u.setEmail(result.getString(9));
                u.setUsername(result.getString(11));
                u.setRole(result.getString(13));
                
                users.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("error while extracting disabled users " + ex.getMessage());
        }
        
        return users;
    }
    
    public ArrayList<Agence> getAllAgence()
    {
        ArrayList<Agence> agences = new ArrayList<>();
        
        String query = "SELECT * FROM agence";
        
        try {
            pst = con.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while(result.next())
            {
                Agence a = new Agence();
                a.setId(result.getInt(1));
                a.setAddresse(result.getString(2));
                a.setCodePostal(result.getInt(3));
                a.setTel(result.getInt(5));
                a.setEmail(result.getString(6));
                
                agences.add(a);
            }
        } catch (SQLException ex) {
        }
        
        
        return agences;
    }
    
    public int getAgenceId(String addresse)
    {
        String query = "SELECT * FROM agence WHERE adresse='"+addresse+"'";
        Agence a = new Agence();
        try {
            pst = con.prepareStatement(query);
            
            ResultSet res = pst.executeQuery();
            if(res.next())
            {
                a.setId(res.getInt(1));
                a.setAddresse(res.getString(2));
            }
        } catch (SQLException ex) {
        }
        
        return a.getId();
    }
    
    public String getAddressAgence(int id)
    {
        String query = "SELECT * FROM agence WHERE id="+id;
        Agence a = new Agence();
        try {
            pst = con.prepareStatement(query);
            
            ResultSet res = pst.executeQuery();
            if(res.next())
            {
                a.setId(res.getInt(1));
                a.setAddresse(res.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("exception  " + ex.getMessage());
        }
        
        return a.getAddresse();
    }

    @Override
    public void desactiverCompte(int id) {
        String query ="UPDATE USER SET enabled=0 where id="+id;
        
        try 
        {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            
            System.out.println("disabled success");
        } catch (SQLException ex) {
            System.out.println("error while updating user enabled : "+ex.getMessage());
        }
    }
    
    public User getClient(int id)
    {
        String query = "SELECT * FROM user WHERE id="+id;
        User user = new User();
        try {
            pst = con.prepareStatement(query);
            
            pst = con.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            if(result.next())
            {
                user.setId(result.getInt(1));
                user.setCin(result.getInt(2));
                user.setNom(result.getString(4));
                user.setPrenom(result.getString(5));
                user.setCodePostal(result.getInt(6));
                user.setDateNaissance(result.getString(7));
                user.setTel(result.getInt(8));
                user.setEmail(result.getString(9));
                user.setUsername(result.getString(11));
                user.setIdAgence(result.getInt(12));
                user.setRole(result.getString(13));
                user.setEnabled(result.getInt(14));
            }    
                return user;
                
        } catch (SQLException ex) {
            System.out.println("exception  " + ex.getMessage());
        }
        
        return user;
    }
    
}
