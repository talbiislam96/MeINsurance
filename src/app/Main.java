/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import tn.esprit.entite.Accident;
import tn.esprit.entite.User;
import tn.esprit.service.AccidentService;

/**
 *
 * @author Tryvl
 */
public class Main {
    public static void main(String[] args)
    {
        User u = new User();
        u.setUsername("username");
        u.setEmail("uiq@esprit.tn");
        u.setNom("nab");
        u.setPrenom("test");
        u.setCin(0);
        u.setPassword("azerty");
        u.setCodePostal(1010);
        u.setRole("ROLE_USER");
        u.setDateNaissance("");
        
        User u1 = new User();
        u1.setId(1);
        
        Accident a = new Accident();
        a.setIdVehicule(10);
        a.setAdresse("test");
        
        AccidentService ser = new AccidentService();
        //ser.ajouterAccident(u1, a);
        
        
        ArrayList<Accident> acc=  ser.afficherAccidentClient(u1);
        for(Accident aa : acc)
        {
            System.out.println(aa);
        }
        
    }
}
