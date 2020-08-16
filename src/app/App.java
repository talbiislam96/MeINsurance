/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.entite.User;

/**
 *
 * @author Tryvl
 */
public class App extends Application {
    
    private double x, y;
    public static User U;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Login.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            
            
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            
            
            root.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event)
                {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }
            });
            
            root.setOnMouseDragged(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event)
                {
                    primaryStage.setX(event.getScreenX() - x);
                    primaryStage.setY(event.getScreenY() - y);
                }
            });
            
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("Unable to open: "+ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
