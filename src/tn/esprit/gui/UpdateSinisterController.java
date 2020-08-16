/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static app.App.U;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.entite.Accident;
import tn.esprit.entite.Sinistre;
import static tn.esprit.gui.EspaceClientController.S;
import tn.esprit.service.AccidentService;
import tn.esprit.service.SinistreService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class UpdateSinisterController implements Initializable {

    @FXML
    private Pane activerComptePane;
    @FXML
    private TextArea descID;
    @FXML
    private ComboBox<String> typeID;
    @FXML
    private Button selectID;
    @FXML
    private ComboBox<String> accidentID;
    @FXML
    private TextField lieuID;
    @FXML
    private ImageView justImageID;
    @FXML
    private ImageView closeBTN;
    @FXML
    private ImageView logoutBTN;
    private ArrayList<String> listFiles;
    @FXML
    private Text errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeID.getItems().add("Naturel");
        typeID.getItems().add("Maladie");
        typeID.getItems().add("3");
        
        
        AccidentService service = new AccidentService();
        ArrayList<Accident> accidents = service.afficherAccidentClient(U);
        
        for(Accident a : accidents)
            accidentID.getItems().add(a.getAdresse());
                
        listFiles = new ArrayList<>();
        listFiles.add("*.PNG");
        listFiles.add("*.png");
        listFiles.add("*.JPEG");
        listFiles.add("*.jpeg");
        listFiles.add("*.JPG");
        listFiles.add("*.jpg");
        listFiles.add("*.BMP");
        listFiles.add("*.bmp");
        listFiles.add("*.GIF");
        listFiles.add("*.gif");
        
        typeID.setValue(S.getType());
        lieuID.setText(S.getLieu());
        descID.setText(S.getDescription());
        
       
        System.out.println("image: "+ S.getImage());
        File image = new File(S.getImage());
        Image im = new Image(image.toURI().toString());
        justImageID.setImage(im);
        
        AccidentService serviceAcc = new AccidentService();
        String acc = serviceAcc.getAddressAccident(S.getIdAccident());
        
        accidentID.setValue(acc);
    }    

    @FXML
    private void selectButton(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        System.out.println(f.toURI().toString());
            
        if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            justImageID.setImage(im);
        } 
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeBTN.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void logout(MouseEvent event) {
    }

    @FXML
    private void add(ActionEvent event) {
        Image im = justImageID.getImage();
        String imgString = im.impl_getUrl();
        imgString = imgString.replace("file:/", "");
        imgString = imgString.replace("%20", " ");
        
        String lieu = lieuID.getText();
        String description = descID.getText();
        String type = typeID.getValue();
        
        String acc = accidentID.getValue();
        
        
        if(lieu.equals(""))
        {
            errorLabel.setText("Lieu est vide!");
            return;
        }
        if(description.equals(""))
        {
            errorLabel.setText("La description est vide!");
            return;
        }
        
        if(type.equals(""))
        {
            errorLabel.setText("Veuillez choisir un type!");
            return;
        }
        if(acc.equals(""))
        {
            errorLabel.setText("Veuillez choisir un accident!");
            return;
        }
        
        
        Sinistre s = new Sinistre();

        s.setType(type);
        s.setImage(imgString);
        s.setLieu(lieu);
        s.setDescription(description);
        
        System.out.println("accc: " + acc);
        AccidentService accSer = new AccidentService();
        int idAccident = accSer.getIdAccident(acc);
        System.out.println("id acc : " + idAccident);
        s.setIdAccident(idAccident);
        s.setId(S.getId());
        
        SinistreService service = new SinistreService();
        service.modifierSinistre(s, U);
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Succès");
        alert.setContentText("Vous avez mis à jour avec succès votre dossier sinistre");
        
        alert.showAndWait();
    }
    
}
