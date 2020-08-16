/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static app.App.U;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.entite.User;
import static tn.esprit.gui.EspaceAssureurController.S;
import tn.esprit.service.AccidentService;
import tn.esprit.service.SendEmail;
import tn.esprit.service.SinistreService;
import tn.esprit.service.UserService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class ConsulterSinistreController implements Initializable {

    double x,y;
    @FXML
    private Pane activerComptePane;
    @FXML
    private Text type;
    @FXML
    private Text description;
    @FXML
    private Text Douverture, Dfermeture;
    @FXML
    private Text lieu;
    @FXML
    private Text accident;
    @FXML
    private ImageView justImageID;
    @FXML
    private Text prenomClient;
    @FXML
    private Text nomClient;
    @FXML
    private ImageView closeBTN;
    @FXML
    private ImageView logoutBTN;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        UserService service = new UserService();
        User user = service.getClient(S.getIdClient());
        nomClient.setText(user.getNom());
        prenomClient.setText(user.getPrenom());
        
        
        
        description.setText(S.getDescription());
        lieu.setText(S.getLieu());
        type.setText(S.getType());
        Douverture.setText(S.getDateOuverture());
        Dfermeture.setText(S.getDateFermeture());
        
        File image = new File(S.getImage());
        Image im = new Image(image.toURI().toString());
        justImageID.setImage(im);
        
        AccidentService serviceAcc = new AccidentService();
        String acc = serviceAcc.getAddressAccident(S.getIdAccident());
        
        accident.setText(acc);
        
        
    }    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeBTN.getScene().getWindow();
        stage.close();
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
    private void Traiter(ActionEvent event) {
        SinistreService service = new SinistreService();
        
        if(S.getTraiter().equals("Traiter"))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Ce document sinistre est déjà traiter!");
            alert.showAndWait();
        }
        
        else
        {
            service.traiterSinistre(S);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Ce document sinistre est maintenant traiter!\n Un email de confirmation est envoyé");
            
       
            UserService serviceU = new UserService();
            User user = serviceU.getClient(S.getIdClient());
        
            String host = "smtp.gmail.com";
            String port = "587";
            String mailFrom = "yassine.sta@esprit.tn";
            String password = "azertysta";

            // outgoing message information
            String mailTo = user.getEmail();
            String subject = "Bonjour";

           // message contains HTML markups


           SendEmail mailer = new SendEmail();

            try {
                String message = 
                "    <table width=\"100%\" height=\"500px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">        \n" +
                "        <tbody>\n" +
                "            \n" +
                "            <tr>\n" +
                "                \n" +
                "                <td align=\"center\" bgcolor=\"#12CB06\">\n" +
                "                    \n" +
                "                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "                        <tbody>\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\" align=\"center\">\n" +
                "                                    \n" +
                "                                                                    \n" +
                "                                    <!-- START LOGO -->\n" +
                "                                    <table width=\"200\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "                                        <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td width=\"100%\" align=\"center\">\n" +
                "                                                    <img width=\"200\" src=\"https://i.ibb.co/SsMCJsW/52833796-771009279952586-6241389141028765696-n.png\" alt=\"CodeWiz Logo\" border=\"0\" style=\"text-align: center;\"/>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </tbody>\n" +
                "                                    </table>\n" +
                "                                    <!-- END LOGO -->\n" +
                "                                    \n" +
                "                                    <!-- START SPACING -->\n" +
                "                                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "                                        <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td height=\"40\">&nbsp;</td>\n" +
                "                                            </tr>\n" +
                "                                        </tbody>\n" +
                "                                    </table>\n" +
                "                                    <!-- END SPACING -->\n" +
                "                                    \n" +
                "                                    <!-- START CONTENT -->\n" +
                "                                    <table width=\"500\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"padding-left:20px; padding-right:20px;\" class=\"responsive_at_550\">\n" +
                "                                        <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td align=\"center\" bgcolor=\"#ffffff\">\n" +
                "                                                    \n" +
                "                                                    <!-- START BORDER COLOR -->\n" +
                "                                                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "                                                        <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td width=\"100%\" height=\"7\" align=\"center\" border=\"0\"  style=\"background-color: #10ad06;\"></td>\n" +
                "                                                            </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                    <!-- END BORDER COLOR -->\n" +
                "                                                    \n" +
                "                                                    <!-- START SPACING -->\n" +
                "                                                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "                                                        <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td height=\"30\">&nbsp;</td>\n" +
                "                                                            </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                    <!-- END SPACING -->\n" +
                "                                                    \n" +
                "                                                    <!-- START HEADING -->\n" +
                "                                                    <table width=\"90%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "                                                        <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td width=\"100%\" align=\"center\">\n" +
                "                                                                    <h1 style=\"font-family:'Ubuntu Mono', monospace; font-size:24px; color:#202020; font-weight:bold; padding-left:20px; padding-right:20px;\">Merci de votre inscription!</h1>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                    <!-- END HEADING -->\n" +
                "                                                    \n" +
                "                                                    <!-- START PARAGRAPH -->\n" +
                "                                                    <table style=\"margin-bottom: 50px\" width=\"90%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "                                                        <tbody>\n" +
                "                                                            <tr>\n" +
                "                                                                <td width=\"100%\" align=\"center\">\n" +
                "                                                                    <p style=\"font-family:'Ubuntu', sans-serif; font-size:18px; color:#202020; padding-left:20px; padding-right:20px; text-align:justify;\">Votre dossier sinistre a été bien traiter</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                        </tbody>\n" +
                "                                                    </table>\n" +
                "                                                    <!-- END PARAGRAPH -->\n" +
                "                                                \n" +
                "                                                    \n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </tbody>\n" +
                "                                    </table>                                    \n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                    \n" +
                "                </td>\n" +
                "                \n" +
                "            </tr>\n" +
                "            \n" +
                "        </tbody>        \n" +
                "    </table>";
                mailer.SendEmail(host, port, mailFrom, password, mailTo,subject, message);
                System.out.println("Email sent.");
            } catch (Exception ex) {
                System.out.println("Failed to sent email.");
                ex.printStackTrace();
            }
                alert.showAndWait();
            }
    }
    
}
