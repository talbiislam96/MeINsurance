/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static app.App.U;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.entite.User;
import tn.esprit.service.UserService;

/**
 * FXML Controller class
 *
 * @author Tryvl
 */
public class LoginController implements Initializable {

    double x, y;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pswd;
    @FXML
    private Button LoginButtonID;
    @FXML
    private Label signUpID;
    @FXML
    private Text errorLabel;
    @FXML
    private ImageView colse;
     @FXML
    private ImageView closeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        errorLabel.setVisible(false);
    }    

    @FXML
    private void LoginButtonID(ActionEvent event) {
        UserService service = new UserService();
        
        String usn = username.getText();
        String pwd = pswd.getText();
        
        if(!(usn.equals("")) && ! (pwd.equals("")))
        {
            User u = new User();
            u = service.authentifier(usn, pwd);
            
            if(u.getId()!=0)
            {
                U = u;
                errorLabel.setVisible(false);
                if(u.getEnabled() == 1 || u.getEnabled() == 2)
                {
                    
                    if(u.getRole().equals("ROLE_USER"))
                    {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceClient.fxml"));
                            Parent root = (Parent) loader.load();

                            EspaceClientController view = loader.getController();

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
                    else if(u.getRole().equals("ROLE_ASSUREUR"))
                    {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceAssureur.fxml"));
                            Parent root = (Parent) loader.load();

                            EspaceAssureurController view = loader.getController();

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
                    else if(u.getRole().equals("ROLE_ADMIN"))
                    {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceAdmin.fxml"));
                            Parent root = (Parent) loader.load();

                            EspaceAdminController view = loader.getController();

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
                else{
                    errorLabel.setVisible(true);
                    errorLabel.setText("Votre compte n'est pas encore activé !");
                }
            }
            else{
                errorLabel.setVisible(true);
                errorLabel.setText("Vos données sont invalides !");
            }
        }
        
    }

    @FXML
    private void closeICON(MouseEvent event) {
        
    }
    
    @FXML
    void clodeEv(MouseEvent event) {
        System.out.println("clicked me");
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void newAccount(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Choix.fxml"));
            Parent root = (Parent) loader.load();
            
            ChoixController view = loader.getController();

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
