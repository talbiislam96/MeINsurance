/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.entite.Agence;
import tn.esprit.entite.User;
import tn.esprit.service.UserService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class AssureurSignUpController implements Initializable {

    double x,y;
    @FXML
    private TextField nameID;
    @FXML
    private TextField phoneID;
    @FXML
    private TextField usernameID;
    @FXML
    private TextField pwdID;
    @FXML
    private TextField emailID;
    @FXML
    private TextField conf_emailID;
    @FXML
    private TextField conf_pwdID;
    @FXML
    private Label AgenceID;
    @FXML
    private ComboBox<String> agenceID;
    @FXML
    private TextField lastnameID;
    @FXML
    private Text errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserService service = new UserService();
        ArrayList<Agence> agences = service.getAllAgence();
        for(Agence a : agences)
        {
            agenceID.getItems().add(a.getAddresse());
        }
    }    

    @FXML
    private void back(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = (Parent) loader.load();
            
            LoginController view = loader.getController();

            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    newStage.setX(event.getScreenX() - x);
                    newStage.setY(event.getScreenY() - y);
                }
            });

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.close();
            
            newStage.show();
            
        } catch (IOException ex) {
        }
    }

    @FXML
    private void create(ActionEvent event) 
    {
        String nom = nameID.getText();
        String prenom = lastnameID.getText();
        String username = usernameID.getText();
        String email = emailID.getText();
        String rEmail = conf_emailID.getText();
        String pwd = pwdID.getText();
        String rpwd = conf_pwdID.getText();
        String tel = phoneID.getText();
        
        
        if(!pwd.equals(rpwd))
        {
            errorLabel.setText("Les mots de passe ne sont pas identiques");
            return;
        }
        if(!email.equals(rEmail))
        {
            errorLabel.setText("Veuillez confirmer votre addresse e-mail");
            return;
        }
        
        try{
            int telI = Integer.parseInt(tel);
        }catch(NumberFormatException ex)
        {
            errorLabel.setText("Veuillez saisir un numéro de téléphone valide");
            return;
        }
        if(tel.length()!= 8)
        {
            errorLabel.setText("Veuillez saisir un numéro de téléphone valide");
            return;
        }
        
        if(nom.equals("") || prenom.equals("") || username.equals(""))
        {
            errorLabel.setText("Veuillez saisir tous les données");
            return;
        }
            
        UserService service = new UserService();
        
        if(!service.uniqueMail(email))
        {
            errorLabel.setText("Email déjà existe");
            return;
        }
        
        if(!service.uniqueUsername(username))
        {
            errorLabel.setText("Username déjà existe");
            return;
        }
        
        User u = new User();
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setPassword(pwd);
        u.setTel(Integer.parseInt(tel));
        u.setUsername(username);
        u.setEmail(email);
        
        String agence = agenceID.getValue();
        int idAgnece = service.getAgenceId(agence);
        u.setIdAgence(idAgnece);
        
        service.creerCompteAssureur(u);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Votre compte a été créé avec succès");
        alert.setContentText("Votre compte assureur est maintenant en attente d'activation par l'administrateur du système");
        alert.showAndWait();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = (Parent) loader.load();
            
            LoginController view = loader.getController();

            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    newStage.setX(event.getScreenX() - x);
                    newStage.setY(event.getScreenY() - y);
                }
            });

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.close();
            
            newStage.show();
            
        } catch (IOException ex) {
        }
    }
    
}
