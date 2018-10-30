/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author STA
 */
public class HomeController implements Initializable {

    @FXML 
    private ImageView btnclose;
    @FXML 
    private ImageView btnminimize;
    @FXML 
    AnchorPane parent;
    
    private double x =0, y=0;
    /**
     * Initializes the controller class.
     */
    @Override   
    public void initialize(URL url, ResourceBundle rb) {
        makeDragabale();
    } 
    
    public void closnd(MouseEvent event)
    {
        System.out.println("close clicked");
        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }
    
    public void btnminimize(MouseEvent event)
    {
        System.out.println("minimize clicked");
        minimizeStageOfNode((Node) event.getSource());
    }
    
    
    private void minimizeStageOfNode(Node node) 
    {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }
    
    private void makeDragabale()
    {
        parent.setOnMousePressed(event -> {     
            x = event.getSceneX();
            y= event.getSceneY();
        });
        
        parent.setOnMouseDragged(event -> { 
            Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getSceneY() - y);
            
            stage.setOpacity(0.8f);
        });
         
        parent.setOnDragDone(event -> {
            Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);

        });
        
        parent.setOnMouseReleased(event -> {  
            Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        });
    }
    
    
    public void excursion(MouseEvent event)
    {
        System.out.println("journey clicked");
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Excursion.fxml"));
                Parent root = (Parent) loader.load();
                ExcursionController journey = loader.getController();
         
                //Close actual login window
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                
                //open Home window
                Scene newScene = new Scene(root);
                Stage newStage = new Stage();
                newStage.setScene(newScene);
                newStage.initStyle(StageStyle.UNDECORATED);
                stage.close();
                newStage.showAndWait(); 
                
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
    
    /*
    *
    *
    *  ///////////     HyperLinks     /////////// 
    *
    *
    */
    
    @FXML public void hotelhp(MouseEvent event)
    {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            //hotel
            Parent root = (Parent) loader.load();
            HomeController pwd_forgot = loader.getController();

            //open Home window
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.show();

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML public void journeyhp(MouseEvent event)
    {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Parent root = (Parent) loader.load();
            HomeController pwd_forgot = loader.getController();

            //open Home window
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);
            
            //Close actual window
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            newStage.show();

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML public void excursionhp(MouseEvent event)
    {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Excursion.fxml"));
            Parent root = (Parent) loader.load();
            ExcursionController excursion = loader.getController();
            
            //open Excursion window
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);
            
            //Close actual window
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            newStage.show();

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML public void eventhp(MouseEvent event)
    {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            //event
            Parent root = (Parent) loader.load();
            HomeController eventC = loader.getController();

            //open Home window
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);
            
            //Close actual window
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            newStage.show();

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML public void activityhp(MouseEvent event)
    {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            //activity
            Parent root = (Parent) loader.load();
            HomeController activity = loader.getController();

            //open Home window
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);
            
            //Close actual window
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            newStage.show();

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
   
}
