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
import tn.esprit.entite.Accident;
import tn.esprit.entite.User;
import tn.esprit.interfaces.IAccident;
import tn.esprit.util.ConnexionDB;

/**
 *
 * @author Tryvl
 */
public class AccidentService implements IAccident
{

    private Connection con;
    private PreparedStatement pst;

    public AccidentService() {
        con = ConnexionDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouterAccident(User u, Accident A) {
        
        String query = "INSERT INTO `accident`(`idClient`, `adresse`, `idVehicule`) VALUES ('"+u.getId()+"','"+A.getAdresse()+"' "
                + ", '"+A.getIdVehicule()+"')";
        
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate(query);
            
            System.out.println("creating accident success");
        } catch (SQLException ex) {
            System.out.println("error in creating accident : " + ex.getMessage());
        }
    }

    @Override
    public void modifierAccident(User u, Accident A) {
        
        String query = "UPDATE `accident` SET `adresse`='"+A.getAdresse()+"',`idVehicule`='"+A.getIdVehicule()+"' WHERE id="+A.getId();
        
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate(query);
            
            System.out.println("modiffication accident success");
        } catch (SQLException ex) {
            System.out.println("error modification accident : " + ex.getMessage());
        }
    }

    @Override
    public void supprimerAccident(int id) {
        String query ="DELETE FROM `accident` WHERE id="+id;
        
        try 
        {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("removing accident success");
        } catch (SQLException ex) {
            System.out.println("error while deleting accident : "+ex.getMessage());
        }
    }

    @Override
    public ArrayList<Accident> afficherAccident() {
        ArrayList<Accident> accidents = new ArrayList<>();
        String query = "SELECT * FROM accident";
        try {
            pst = con.prepareStatement(query);
            
            ResultSet result = pst.executeQuery();
            while(result.next())
            {
                Accident a = new Accident();
                a.setId(result.getInt(1));
                a.setAdresse(result.getString(2));
                a.setIdClient(result.getInt(2));
                a.setIdVehicule(result.getInt(4));
                
                accidents.add(a);
            }
        }catch(SQLException ex)
        {
            System.out.println("" + ex.getMessage());
        }
        
        return accidents;
    }

    @Override
    public ArrayList<Accident> afficherAccidentClient(User u) {
        ArrayList<Accident> accidents = new ArrayList<>();
        String query = "SELECT * FROM accident where idClient="+u.getId();
        try {
            pst = con.prepareStatement(query);
            
            ResultSet result = pst.executeQuery();
            while(result.next())
            {
                Accident a = new Accident();
                a.setId(result.getInt(1));
                a.setAdresse(result.getString(3));
                a.setIdClient(result.getInt(2));
                a.setIdVehicule(result.getInt(4));
                
                accidents.add(a);
            }
        }catch(SQLException ex)
        {
            System.out.println("" + ex.getMessage());
        }
        return accidents;
    }
    
    public int getIdAccident(String add)
    {
        Accident a = new Accident();
        String query = "SELECT * FROM accident where adresse='"+add+"'";
        try {
            pst = con.prepareStatement(query);
            
            ResultSet result = pst.executeQuery();
            while(result.next())
            {
                
                a.setId(result.getInt(1));
                a.setAdresse(result.getString(3));
                a.setIdClient(result.getInt(2));
                a.setIdVehicule(result.getInt(4));
                
                
            }
        }catch(SQLException ex)
        {
            System.out.println("" + ex.getMessage());
        }
        return a.getId();
    }
    
    public String getAddressAccident(int id)
    {
        Accident a = new Accident();
        String query = "SELECT * FROM accident where id="+id;
        try {
            pst = con.prepareStatement(query);
            
            ResultSet result = pst.executeQuery();
            while(result.next())
            {
                
                a.setId(result.getInt(1));
                a.setAdresse(result.getString(3));
                a.setIdClient(result.getInt(2));
                a.setIdVehicule(result.getInt(4));
                
                
            }
        }catch(SQLException ex)
        {
            System.out.println("" + ex.getMessage());
        }
        return a.getAdresse();
    }
    
    public boolean uniqueVehicule(int veh)
    {
        String query = "SELECT * FROM accident where idVehicule="+veh;
        
        try {
            pst= con.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            
            if(result.next())
                return false;
            else
                return true;
            
        } catch (SQLException ex) {
            System.out.println("unique vehicule error : "+ex.getMessage());
        }
        
        return false;
    }
    
}
