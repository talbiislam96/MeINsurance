/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static app.App.U;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.entite.User;
import tn.esprit.service.SendEmail;
import tn.esprit.service.UserService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class EspaceAdminController implements Initializable {

    @FXML
    private Pane activerComptePane;
    @FXML
    private TableColumn<User, String> nom_a;
    @FXML
    private TableColumn<User, String> prenom_a;
    @FXML
    private TableColumn<User, String> username_a;
    @FXML
    private TableColumn<User, String> email_a;
    @FXML
    private TableColumn<User, String> role_a;
    @FXML
    private TableView<User> tab1;
    
    @FXML
    private TableColumn<User, String> nom_d;
    @FXML
    private TableColumn<User, String> prenom_d;
    @FXML
    private TableColumn<User, String> username_d;
    @FXML
    private TableColumn<User, String> email_d;
    @FXML
    private TableColumn<User, String> role_d;
    @FXML
    private TableView<User> tab2;
    
    @FXML
    private ImageView close_btn;
    @FXML
    private ImageView logout_btn;
    @FXML
    private Button desactivateUserBtn;
    @FXML
    private Button activate_btn;

    public static ObservableList<User> oldUsers, newUsers;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        System.out.println("user connected " + U);
        
        initUserDisabled();
        loadUserDisabled();
        initUserEnabled();
        loadUserEnabled();
        
        activate_btn.setDisable(true);
        desactivateUserBtn.setDisable(true);
    }

    private void initUserDisabled()
    {
        nom_d.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_d.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        username_d.setCellValueFactory(new PropertyValueFactory<>("username"));
        email_d.setCellValueFactory(new PropertyValueFactory<>("email"));
        role_d.setCellValueFactory(new PropertyValueFactory<>("role"));
    }
    private void loadUserDisabled()
    {
        UserService service = new UserService();
        ArrayList<User> users = new ArrayList<>();
        
        newUsers = FXCollections.observableArrayList(users);
        users = service.getAllDisabledUser();
        
        for(User u : users)
            newUsers.add(u);
        
        tab2.setItems(newUsers);
    }
    private void initUserEnabled()
    {
        nom_a.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_a.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        username_a.setCellValueFactory(new PropertyValueFactory<>("username"));
        email_a.setCellValueFactory(new PropertyValueFactory<>("email"));
        role_a.setCellValueFactory(new PropertyValueFactory<>("role"));
    }
    private void loadUserEnabled()
    {
        UserService service = new UserService();
        ArrayList<User> users = new ArrayList<>();
        
        oldUsers = FXCollections.observableArrayList(users);
        users = service.getAllEnabledUser();
        
        for(User u : users)
            oldUsers.add(u);
        
        tab1.setItems(oldUsers);
    }

    @FXML
    private void activeTab(MouseEvent event) {
        desactivateUserBtn.setDisable(false);
    }

    @FXML
    private void deactivate(MouseEvent event) {
    }

    @FXML
    private void refresh(MouseEvent event) {
    }

    @FXML
    private void deactiveTab(MouseEvent event) {
        activate_btn.setDisable(false);
    }

    @FXML
    private void activate(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) close_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void logout(MouseEvent event) {
    }
    
    
    public void refreshOldTableView() {
        oldUsers.clear();
        loadUserEnabled();
    }
    
    public void refreshNewTableView() {
        newUsers.clear();
        loadUserDisabled();
    }
    
    private User getSelectedDisabledUser()
    {
        User u = tab2.getSelectionModel().getSelectedItem();
        return u;
    }
    
    private User getSelectedEnabledUser()
    {
        User u = tab1.getSelectionModel().getSelectedItem();
        return u;
    }
    
    @FXML
    void activateUser(ActionEvent event) 
    {
        //System.out.println("i've clicked :: " + this.getSelectedDisabledUser());
        UserService service = new UserService();
        service.activerCompte(this.getSelectedDisabledUser().getId());
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez autorisé Mr/Mme " + this.getSelectedDisabledUser().getNom() + " à rejoindre l'application");
        alert.showAndWait();
        
         String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "yassine.sta@esprit.tn";
        String password = "azertysta";
 
        // outgoing message information
        String mailTo = "stayassine3@gmail.com";
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
            "                                                                    <p style=\"font-family:'Ubuntu', sans-serif; font-size:18px; color:#202020; padding-left:20px; padding-right:20px; text-align:justify;\">Votre compte est maintenant activé, vous pouvez désormais utiliser notre application en toute sécurité!</p>\n" +
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
        activate_btn.setDisable(true);
        refreshNewTableView();
        refreshOldTableView();
    }
    
    @FXML
    void refresh2(ActionEvent event) {
        refreshOldTableView();
    }
    
     @FXML
    void desactiverUser(ActionEvent event) {
        UserService service = new UserService();
        service.desactiverCompte(this.getSelectedEnabledUser().getId());
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez désactivé ce compte " + 
                "\n Cet utilisateur ne pourra plus utiliser votre application");
        
        alert.showAndWait();
        
        desactivateUserBtn.setDisable(true);
        
        refreshNewTableView();
        refreshOldTableView();
    }
    
}
