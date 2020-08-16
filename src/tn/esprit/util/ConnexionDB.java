/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tryvl
 */
public class ConnexionDB {
    private String url="jdbc:mysql://localhost:3306/assurance";
     private String login="root";
     private String pwd="";
     private Connection cnx;
     
     private static ConnexionDB instance;
     

    private ConnexionDB() {
         try {
             // TODO code application logic here
             cnx=DriverManager.getConnection(url, login, pwd);
             System.out.println("Connexion établie");
         } catch (SQLException ex) {
             Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public static ConnexionDB getInstance(){
        if(instance==null)
            instance=new ConnexionDB();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
