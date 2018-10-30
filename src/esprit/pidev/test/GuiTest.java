/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.test;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author STA
 */
public class GuiTest extends Application{
    
    private double x, y;
    
    @Override
    public void start(Stage primaryStage) {

        try {
            //Parent root = FXMLLoader.load(getClass().getResource("/esprit/pidev/gui/RandonneAdmin.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/esprit/pidev/gui/Randonne.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/esprit/pidev/gui/Home.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/esprit/pidev/gui/Excursion.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/esprit/pidev/gui/ExcursionAdmin.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/esprit/pidev/guif/Home.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            
            //scene.setFill(Color.TRANSPARENT);
            
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
    
    public static void main(String[] args) {
        launch(args);
    }
}
