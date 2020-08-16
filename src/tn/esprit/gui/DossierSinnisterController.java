/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static app.App.U;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import tn.esprit.entite.Accident;
import tn.esprit.entite.Sinistre;
import tn.esprit.service.AccidentService;
import tn.esprit.service.SinistreService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class DossierSinnisterController implements Initializable , MapComponentInitializedListener{

    @FXML
    private Pane activerComptePane;
    @FXML
    private Label Douverture;
    @FXML
    private Label Dfermeture;
    @FXML
    private Label lieu;
    @FXML
    private Label accident;
    @FXML
    private Label description;
    @FXML
    private TextField LieuID;
    @FXML
    private ComboBox<String> AccidentID;
    @FXML
    private DatePicker DateOID;
    @FXML
    private DatePicker DateFID;
    @FXML
    private TextArea desID;
    @FXML
    private Button selectID;
    @FXML
    private ComboBox<String> typeID;
    @FXML
    private ImageView justImageID;
    @FXML
    private Label type;
    @FXML
    private ImageView closeBTN;
    @FXML
    private ImageView logoutBTN;
    private ArrayList<String> listFiles;
    
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    
    private GeocodingService geocodingService;
    @FXML
    private Text errorLabel;

    private StringProperty address = new SimpleStringProperty();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeID.getItems().add("Naturel");
        typeID.getItems().add("Maladie");
        typeID.getItems().add("3");
        
        
        AccidentService service = new AccidentService();
        ArrayList<Accident> accidents = service.afficherAccidentClient(U);
        
        for(Accident a : accidents)
            AccidentID.getItems().add(a.getAdresse());
        
        
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
        
         mapView.addMapInializedListener(this);
    }    

    @FXML
    private void selectButton(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        System.out.println(f.toURI().toString());
            
        if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            justImageID.setImage(im);
        } 
        
    }

    @FXML
    private void add(ActionEvent event) {
        Image im = justImageID.getImage();
        String imgString = im.impl_getUrl();
        imgString = imgString.replace("file:/", "");
        imgString = imgString.replace("%20", " ");
        
        String lieu = LieuID.getText();
        String description = desID.getText();
        String dateO = DateOID.getValue().toString();
        String dateF = DateFID.getValue().toString();
        String type = typeID.getValue();
        
        String acc = AccidentID.getValue();
        
        
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
        if(dateO.equals(""))
        {
            errorLabel.setText("La date d'ouverture est vide!");
            return;
        }
        if(dateF.equals(""))
        {
            errorLabel.setText("La date de fermeture est vide!");
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
        s.setDateFermeture(dateF);
        s.setDateOuverture(dateO);
        s.setType(type);
        s.setImage(imgString);
        s.setLieu(lieu);
        s.setDescription(description);
        
        System.out.println("accc: " + acc);
        AccidentService accSer = new AccidentService();
        int idAccident = accSer.getIdAccident(acc);
        System.out.println("id acc : " + idAccident);
        s.setIdAccident(idAccident);
        
        System.out.println(s);
        SinistreService service = new SinistreService();
        service.ajouterSinsitre(s, U);
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Succès");
        alert.setContentText("Vous avez ajouter avec succès une nouveau dossier sinistre");
        
        alert.showAndWait();
        
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeBTN.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void logout(MouseEvent event) {
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        LatLong Location = new LatLong(36.8981646, 10.1876613);
        MarkerOptions markerOptions1 = new MarkerOptions();
        Marker m = new Marker(markerOptions1);
        System.out.println(Location);
        mapOptions.center(Location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(13);
        
       /* map.addMouseEventHandler(UIEventType.click, (event) -> {
            markerOptions1.position(event.getLatLong())
                    .visible(true)
                    ;
            m.setOptions(markerOptions1);
            map.addMarker(m);
            double longi = event.getLatLong().getLongitude();
            
            double lat = event.getLatLong().getLatitude();
            System.out.println(Double.toString(longi));
            System.out.println(Double.toString(lat));
            
            //longitude.setText(Double.toString(longi));
            //latitude.setText(Double.toString(lat));
            
           geocodingService.reverseGeocode(event.getLatLong().getLatitude(), event.getLatLong().getLongitude(), new GeocodingServiceCallback() {
                @Override
                public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
                 String  address= grs[2].toString().substring(grs[2].toString().indexOf("Address: "), grs[2].toString().indexOf(", Tunisia"));
                 address=address.substring(address.indexOf(" ")+1, address.length());
                    System.out.println(address);                
                } 
            });
            
        }); 
        */
        map = mapView.createMap(mapOptions);
        
        
    }
    
    
    @FXML
    public void adressDest(ActionEvent event)
    {
        System.out.println("typing");
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
            LatLong latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            
            map.setCenter(latLong);

        });
    }
    
}
