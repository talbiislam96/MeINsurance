/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static app.App.U;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.entite.Accident;
import tn.esprit.service.AccidentService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class AccidentController implements Initializable {

    @FXML
    private ImageView closeBtn;
    @FXML
    private Label adresse;
    @FXML
    private TextField address;
    @FXML
    private TextField vehicule;
    @FXML
    private Text errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
        String add = address.getText();
        String veh = vehicule.getText();
        
        try{
            int i = Integer.parseInt(veh);
        }catch(NumberFormatException e)
        {
            errorLabel.setText("Veuillez entrer une matricule valide");
            return;
        }
        
        if(add.equals("") || veh.equals(""))
        {
            errorLabel.setText("Veuillez verifier vos données ! ");
            return;
        }
        
        
        Accident a = new Accident();
        a.setAdresse(add);
        a.setIdVehicule(Integer.parseInt(veh));
        a.setIdClient(U.getId());
        
        AccidentService service = new AccidentService();
        
        if(service.uniqueVehicule(Integer.parseInt(veh)))
        {
            service.ajouterAccident(U, a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout a été effectué avec succès");
            alert.showAndWait();
        }
        
        else{
            errorLabel.setText("Ce véhicule existe déjà! ");
            return;
        }
        
        
        
        
    }
    
}
