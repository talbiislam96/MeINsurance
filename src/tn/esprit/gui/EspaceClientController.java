/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static app.App.U;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.entite.Accident;
import tn.esprit.entite.Agence;
import tn.esprit.entite.Sinistre;
import tn.esprit.entite.User;
import tn.esprit.service.AccidentService;
import tn.esprit.service.SinistreService;
import tn.esprit.service.UserService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class EspaceClientController implements Initializable {

    double x,y;
    @FXML
    private Pane activerComptePane;
    @FXML
    private Pane accidentPane;
    @FXML
    private TableView<Accident> tabAccident;
    @FXML
    private Pane sinistrePane;
    @FXML
    private TableView<Sinistre> tabSinistre;
    @FXML
    private TableColumn<Sinistre, String> type;
    @FXML
    private TableColumn<Sinistre, String> Description;
    @FXML
    private TableColumn<Sinistre, String> Douverture;
    @FXML
    private TableColumn<Sinistre, String> Dfermeture;
    @FXML
    private TableColumn<Sinistre, String> Lieu;
    @FXML
    private Pane profilePane;
    @FXML
    private TextField nameID;
    @FXML
    private TextField zipID;
    @FXML
    private TextField phoneID;
    @FXML
    private TextField pwdID;
    @FXML
    private TextField emailID;
    @FXML
    private Label AgenceID;
    @FXML
    private ComboBox<String> agenceID;
    @FXML
    private TextField lastnameID;
    @FXML
    private ImageView closeBtn;
    @FXML
    private Text errorLabel;
     @FXML
    private Button deleteSinistreBtn;

    @FXML
    private Button editSinistreBtn;
    
    public static ObservableList<Sinistre> sinisObv;
    
    public static ObservableList<Accident> accObv;
    @FXML
    private TableColumn<Accident, String> addresseAccident;
    @FXML
    private TableColumn<Accident, Integer> vehiculeAccident;
    @FXML
    private Button deleteAccidentBtn;
    @FXML
    private Button editAccidentBtn;

    public static Accident A;
    public static Sinistre S;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        profilePane.setVisible(false);
        sinistrePane.setVisible(false);
        accidentPane.setVisible(true);
        
        deleteSinistreBtn.setDisable(true);
        editSinistreBtn.setDisable(true);
        
        this.initTabAccident();
        this.loadAccidentData();
    }  
    
    @FXML
    void sinistre(ActionEvent event) {
        profilePane.setVisible(false);
        sinistrePane.setVisible(true);
        accidentPane.setVisible(false);
        
        this.initTabSinistre();
        this.loadSinistreData();
        editSinistreBtn.setDisable(true);
        deleteSinistreBtn.setDisable(true);
    }
    
     @FXML
    void accident(ActionEvent event) {
        profilePane.setVisible(false);
        sinistrePane.setVisible(false);
        accidentPane.setVisible(true);
        
        deleteSinistreBtn.setDisable(true);
        editSinistreBtn.setDisable(true);
        
        this.initTabAccident();
        this.loadAccidentData();
    }
    
    @FXML
    void profile(ActionEvent event) {
        profilePane.setVisible(true);
        sinistrePane.setVisible(false);
        accidentPane.setVisible(false);
        
        System.out.println("user " + U);
        
        UserService service = new UserService();
        ArrayList<Agence> agences = service.getAllAgence();
        for(Agence a : agences)
        {
            agenceID.getItems().add(a.getAddresse());
        }
        
        nameID.setText(U.getNom());
        lastnameID.setText(U.getPrenom());
        emailID.setText(U.getEmail());
        zipID.setText(Integer.toString(U.getCodePostal()));
        phoneID.setText(Integer.toString(U.getTel()));
        agenceID.setValue(service.getAddressAgence(U.getIdAgence()));
        
        System.out.println("Agence id : " + U.getIdAgence() + " Nom: " + service.getAddressAgence(U.getIdAgence()));
        
    }

    @FXML
    private void logout(MouseEvent event) {
        U = new User();
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
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addAccident(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accident.fxml"));
            Parent root = (Parent) loader.load();

            AccidentController view = loader.getController();

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
            newStage.show();

        } catch (IOException ex) {
        }
    }

    @FXML
    private void deleteAccident(ActionEvent event) 
    {
        Accident a = tabAccident.getSelectionModel().getSelectedItem();
         
        AccidentService service = new AccidentService();
        service.supprimerAccident(a.getId());
         
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("La supression de ce dossier sinistre a été effectué avec succès");
        alert.showAndWait();
    }

    @FXML
    private void updateAccident(ActionEvent event) {
        
        A = tabAccident.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccidentUpdate.fxml"));
            Parent root = (Parent) loader.load();

            AccidentUpdateController view = loader.getController();

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
            newStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addSinistre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DossierSinnister.fxml"));
            Parent root = (Parent) loader.load();

            DossierSinnisterController view = loader.getController();

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
            newStage.show();

        } catch (IOException ex) {
        }
    }

    @FXML
    private void deleteSinistre(ActionEvent event) {
        Sinistre s = tabSinistre.getSelectionModel().getSelectedItem();
        
        SinistreService service = new SinistreService();
        service.supprimerSinistre(s, U);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("La supression de ce dossier sinistre a été effectué avec succès");
        alert.showAndWait();
    }

    @FXML
    private void updateSinistre(ActionEvent event) {
        
        S = tabSinistre.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateSinister.fxml"));
            Parent root = (Parent) loader.load();

            UpdateSinisterController view = loader.getController();

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

            newStage.show();

        } catch (IOException ex) {
        }
        
    }
    
    @FXML
    void sinistreSelected(MouseEvent event) {
        editSinistreBtn.setDisable(false);
        deleteSinistreBtn.setDisable(false);
    }

    @FXML
    private void creerButton(ActionEvent event) {
        //Profile update
        String nom = nameID.getText();
        String prenom = lastnameID.getText();
        String email = emailID.getText();
        String pwd = pwdID.getText();
        String zip = zipID.getText();
        String tel = phoneID.getText();
        
        
        try{
            int zipI = Integer.parseInt(zip);
        }catch(NumberFormatException ex)
        {
            errorLabel.setText("Veuillez saisir un code postal valide");
            return;
        }
        if(zip.length()!= 4)
        {
            errorLabel.setText("Veuillez saisir un code postal valide");
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

        
        if(nom.equals("") || prenom.equals("") || email.equals("") || tel.equals("") || pwd.equals("")
                || zip.equals("") )
        {
            errorLabel.setText("Veuillez saisir tous les données");
            return;
        }
        
        
        UserService service = new UserService();
        if(!email.equals(U.getEmail()))
        {
            if(!service.uniqueMail(email))
            {
                errorLabel.setText("Email déjà existe");
                return;
            }
        }
        
        User u = new User();
        u = U;
        u.setNom(nom); 
        u.setPrenom(prenom);
        u.setPassword(pwd);
        u.setEmail(email);
        u.setTel(Integer.parseInt(tel));
        u.setCodePostal(Integer.parseInt(zip));
        
        String agence = agenceID.getValue();
        int idAgnece = service.getAgenceId(agence);
        u.setIdAgence(idAgnece);
        
        service.modifierCompte(U.getId(),u);
        
    }
    
    
    
    @FXML
    private void signout(ActionEvent event) {
        U = new User();
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
    
    public void initTabAccident()
    {
        //addresseAccident vehiculeAccident
        addresseAccident.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        vehiculeAccident.setCellValueFactory(new PropertyValueFactory<>("idVehicule"));
    }
    public void loadAccidentData()
    {
        //accObv
        ArrayList<Accident> accidents = new ArrayList<>();
        AccidentService service = new AccidentService();
        
        accObv = FXCollections.observableArrayList(accidents);
        
        accidents = service.afficherAccidentClient(U);
        for(Accident a : accidents)
            accObv.add(a);
        
        tabAccident.setItems(accObv);
    }
    
    public void initTabSinistre()
    {
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Douverture.setCellValueFactory(new PropertyValueFactory<>("dateOuverture"));
        Dfermeture.setCellValueFactory(new PropertyValueFactory<>("dateFermeture"));
        Lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
    }    
    
    public void loadSinistreData()
    {
        SinistreService service = new SinistreService();
        ArrayList<Sinistre> sinistres = new ArrayList<>();
        sinisObv = FXCollections.observableArrayList(sinistres);
       
       sinistres = service.sinistreClient(U);
       for(Sinistre s : sinistres)
           sinisObv.add(s);
       
       tabSinistre.setItems(sinisObv);
    }
    
    @FXML
    void refresh(ActionEvent event) {
        sinisObv.clear();
        loadSinistreData();
    }
    
    @FXML
    void refreshAccident(ActionEvent event) {
        accObv.clear();
        loadAccidentData();
    }

    @FXML
    private void clickTabAccident(MouseEvent event) {
        editSinistreBtn.setDisable(false);
        deleteSinistreBtn.setDisable(false);
    }
    
}
