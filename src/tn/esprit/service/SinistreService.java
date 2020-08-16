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
import tn.esprit.entite.Sinistre;
import tn.esprit.entite.User;
import tn.esprit.interfaces.ISinistre;
import tn.esprit.util.ConnexionDB;

/**
 *
 * @author Tryvl
 */
public class SinistreService implements ISinistre{

    private Connection con;
    private PreparedStatement pst;

    public SinistreService() {
        con = ConnexionDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouterSinsitre(Sinistre s, User u) {
        String query = "INSERT INTO `sinistre`(`type`, `justificatif`, `description`, `dateOuverture`, `dateFermeture`, `lieu`, `googleMap`, `idAccident`,`idClient`, `traiter`) VALUES ("
                + "'"+s.getType()+"' , '"+s.getImage()+"' , '"+s.getDescription()+"' , '"+s.getDateOuverture()+"' , "
                + " '"+s.getDateFermeture()+"' , '"+s.getLieu()+"' , '"+s.getGoogleMaps()+"' , "+s.getIdAccident()+" , "+u.getId()+" ,'Non traiter')";
    
         try {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("success");
        } catch (SQLException ex) {
            System.out.println("error "+ex.getMessage());
        }
    }

    @Override
    public void modifierSinistre(Sinistre s, User u) {
        String query ="UPDATE sinistre set type='"+s.getType()+"' , justificatif='"+s.getImage()+"' , "
                + "description='"+s.getDescription()+"' ,lieu='"+s.getLieu()+"' , googleMap='"+s.getGoogleMaps()+"' "
                + "WHERE id="+s.getId();
        
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("update  success");
        } catch (SQLException ex) {
            System.out.println("error while updating  : "+ex.getMessage());
        }
    }

    @Override
    public void supprimerSinistre(Sinistre s, User u) {
        String query ="DELETE FROM sinistre WHERE id="+s.getId();
        
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("removing  success");
        } catch (SQLException ex) {
            System.out.println("error while deleting  : "+ex.getMessage());
        }
    }

    @Override
    public void traiterSinistre(Sinistre s) {
        String query ="UPDATE sinistre set traiter='Traiter' WHERE id="+s.getId();
        
        System.out.println("id " + s.getId());
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("traiter  success");
        } catch (SQLException ex) {
            System.out.println("error while traiter  : "+ex.getMessage());
        }
    }

    @Override
    public ArrayList<Sinistre> sinistreTraiter() {
        ArrayList<Sinistre> sinistres = new ArrayList<>();
        
        String query = "SELECT * FROM sinistre where traiter='Traiter'";
        
        try {
            pst = con.prepareStatement(query);
            ResultSet res = pst.executeQuery();
            
            while(res.next())
            {
                Sinistre s = new Sinistre();
                
                s.setId(res.getInt(1));
                s.setType(res.getString(2));
                s.setImage(res.getString(3));
                s.setDescription(res.getString(4));
                s.setDateOuverture(res.getString(5));
                s.setDateFermeture(res.getString(6));
                s.setLieu(res.getString(7));
                s.setGoogleMaps(res.getString(8));
                s.setIdAccident(res.getInt(9));
                s.setIdClient(res.getInt(10));
                s.setTraiter(res.getString(11));
                
                sinistres.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println("error while extracting data  : "+ex.getMessage());
        }
        
        
        return sinistres;
    }

    @Override
    public ArrayList<Sinistre> sinistreNonTraiter() {
        ArrayList<Sinistre> sinistres = new ArrayList<>();
        String query = "SELECT * FROM sinistre where traiter='Non traiter'";
        
        try {
            pst = con.prepareStatement(query);
            ResultSet res = pst.executeQuery();
            
            while(res.next())
            {
                Sinistre s = new Sinistre();
                
                s.setId(res.getInt(1));
                s.setType(res.getString(2));
                s.setImage(res.getString(3));
                s.setDescription(res.getString(4));
                s.setDateOuverture(res.getString(5));
                s.setDateFermeture(res.getString(6));
                s.setLieu(res.getString(7));
                s.setGoogleMaps(res.getString(8));
                s.setIdAccident(res.getInt(9));
                s.setIdClient(res.getInt(10));
                s.setTraiter(res.getString(11));
                
                sinistres.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println("error while extracting data  : "+ex.getMessage());
        }
        return sinistres;
    }

    @Override
    public ArrayList<Sinistre> sinistreClient(User u) {
        ArrayList<Sinistre> sinistres = new ArrayList<>();
        String query = "SELECT * FROM sinistre where idClient="+u.getId();
        
        try {
            pst = con.prepareStatement(query);
            ResultSet res = pst.executeQuery();
            
            while(res.next())
            {
                Sinistre s = new Sinistre();
                
                s.setId(res.getInt(1));
                s.setType(res.getString(2));
                s.setImage(res.getString(3));
                s.setDescription(res.getString(4));
                s.setDateOuverture(res.getString(5));
                s.setDateFermeture(res.getString(6));
                s.setLieu(res.getString(7));
                s.setGoogleMaps(res.getString(8));
                s.setIdAccident(res.getInt(9));
                s.setIdClient(res.getInt(10));
                s.setTraiter(res.getString(11));
                
                sinistres.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println("error while extracting data  : "+ex.getMessage());
        }
        return sinistres;
    }  
}
