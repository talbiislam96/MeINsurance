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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import org.controlsfx.control.textfield.TextFields;
import tn.esprit.entite.Sinistre;
import tn.esprit.entite.User;
import tn.esprit.service.SinistreService;
import tn.esprit.service.UserService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class EspaceAssureurController implements Initializable {

    double x,y;
    @FXML
    private Pane activerComptePane;
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
    private Text errorLabel;
    @FXML
    private ImageView closeBtn;

    public static ObservableList<Sinistre> sinisObv;
    @FXML
    private TableColumn<Sinistre, String> traiter;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button viewBtn;
    public static Sinistre S;
    @FXML
    private TextField search;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sinistrePane.setVisible(true);
        profilePane.setVisible(false);
        
        deleteBtn.setDisable(true);
        viewBtn.setDisable(true);
        
        this.initData();
        this.loadData();
        
        SinistreService service = new SinistreService();
        ArrayList<Sinistre> sinistres = new ArrayList<>();
        ArrayList<Sinistre> sinistres2 = new ArrayList<>();
        sinisObv = FXCollections.observableArrayList(sinistres);
       
        sinistres2 = service.sinistreTraiter();
        sinistres = service.sinistreNonTraiter();
       
        for(Sinistre s : sinistres2)
        {
            sinistres.add(s);
        }
        
        ArrayList<String> possStrings = new ArrayList<>();

        for (int i = 0; i < sinistres.size(); i++) {
            possStrings.add(sinistres.get(i).getType());
        }
        TextFields.bindAutoCompletion(search, possStrings);
        
          // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Sinistre> filteredDataActive = new FilteredList<>(sinisObv, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((ObservableValue<? extends String> Observable, String oldValue, String newValue) -> {
            filteredDataActive.setPredicate(sinistre -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare destination of every excursion with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (sinistre.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Sinistre> sortedData = new SortedList<>(filteredDataActive);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tabSinistre.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabSinistre.setItems(sortedData);
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
    private void deleteSinistre(ActionEvent event) {
    }
    

    @FXML
    private void updateSinistre(ActionEvent event) {
        S = tabSinistre.getSelectionModel().getSelectedItem();
        UserService service = new UserService();
        User user = service.getClient(S.getIdClient());
        System.out.println("sinistre " + S);
        System.out.println("usr " + user);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterSinistre.fxml"));
            Parent root = (Parent) loader.load();

            ConsulterSinistreController view = loader.getController();

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
    private void sinistre(ActionEvent event) {
        sinistrePane.setVisible(true);
        profilePane.setVisible(false);
        this.initData();
        this.loadData();
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Sinistre> filteredData = new FilteredList<>(sinisObv, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((ObservableValue<? extends String> Observable, String oldValue, String newValue) -> {
            filteredData.setPredicate(sinistre -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare destination of every excursion with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (sinistre.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Sinistre> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tabSinistre.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabSinistre.setItems(sortedData);
        
    }

    @FXML
    private void profile(ActionEvent event) {
        sinistrePane.setVisible(false);
        profilePane.setVisible(true);
        
        nameID.setText(U.getNom());
        lastnameID.setText(U.getPrenom());
        emailID.setText(U.getEmail());
        phoneID.setText(Integer.toString(U.getTel()));
        
        UserService service = new UserService();
        agenceID.setValue(service.getAddressAgence(U.getIdAgence()));
    }

    @FXML
    private void logoutE(ActionEvent event) {
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
    
    public void initData()
    {
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Douverture.setCellValueFactory(new PropertyValueFactory<>("dateOuverture"));
        Dfermeture.setCellValueFactory(new PropertyValueFactory<>("dateFermeture"));
        Lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        traiter.setCellValueFactory(new PropertyValueFactory<>("traiter"));
    }
    
    public void loadData()
    {
        SinistreService service = new SinistreService();
        ArrayList<Sinistre> sinistres = new ArrayList<>();
        ArrayList<Sinistre> sinistres2 = new ArrayList<>();
        sinisObv = FXCollections.observableArrayList(sinistres);
       
        sinistres2 = service.sinistreTraiter();
        sinistres = service.sinistreNonTraiter();
       
       for(Sinistre s : sinistres2)
       {
           sinistres.add(s);
       }
       
       for(Sinistre s : sinistres)
       {
           sinisObv.add(s);
       }
           
       
       tabSinistre.setItems(sinisObv);
        //sinisObv
    }

    @FXML
    private void updateProfile(ActionEvent event) {
        String nom = nameID.getText();
        String prenom = lastnameID.getText();
        String email = emailID.getText();
        String pwd = pwdID.getText();
        String tel = phoneID.getText();
        
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
        
        String agence = agenceID.getValue();
        int idAgnece = service.getAgenceId(agence);
        u.setIdAgence(idAgnece);
        
        service.modifierCompte(U.getId(),u);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Votre profile a été bien mis à jour");
        alert.showAndWait();
    }

    @FXML
    private void SinistreSelected(MouseEvent event) {
        deleteBtn.setDisable(false);
        viewBtn.setDisable(false);
    }

    @FXML
    private void refresh(ActionEvent event) {
        sinisObv.clear();
        this.loadData();
    }
    
}
