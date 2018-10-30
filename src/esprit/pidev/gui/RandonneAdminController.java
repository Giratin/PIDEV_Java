/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import esprit.pidev.entities.Randonne;
import esprit.pidev.services.CRUDRandonne;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author STA
 */
public class RandonneAdminController implements Initializable {

    
    
    private ArrayList<String> listFiles;
    @FXML
    JFXButton img1;
    @FXML
    JFXButton img2;
    @FXML
    JFXButton img3;
    @FXML
    JFXButton img4;
    @FXML
    JFXButton img5;
    @FXML
    ImageView vimg1;
    @FXML
    ImageView vimg2;
    @FXML
    ImageView vimg3;
    @FXML
    ImageView vimg4;
    @FXML
    ImageView vimg5;
    @FXML
    JFXTextField dest;
    @FXML
    JFXTextField price;
    @FXML
    JFXDatePicker date;
    @FXML
    JFXTextField cap;
    @FXML
    JFXTextArea prog;
    
    
    @FXML
    public void imgl(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        System.out.println(f.toURI().toString());
            
        if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            vimg1.setImage(im);
        }
                      
    }

    
    @FXML
    public void img2(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        
         if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            vimg2.setImage(im);
        }        
    }
    
    @FXML
    public void img3(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        
         if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            vimg3.setImage(im);
        }          
    }
    
    @FXML
    public void img4(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        
         if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            vimg4.setImage(im);
        }          
    }
    
    @FXML
    public void img5(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        
        if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            vimg5.setImage(im);
        }          
    }
    
    @FXML 
    public void add(ActionEvent event)
    {
        /*String destination = dest.getText();
        LocalDate datep = date.getValue();
        String datechoosen = datep.toString();
        String programme = prog.getText();
        String capacite = cap.getText();
        String prix = price.getText();
        
        float prixRando = Float.parseFloat(prix);
        int capaciteRando = Integer.parseInt(capacite);
        
        
        CRUDRandonne cr = new CRUDRandonne();
        Randonne r = new Randonne();
        r.setDateRando(datechoosen);
        r.setDestinationRando(destination);
        r.setProgrammeRando(programme);
        r.setPrixPersonne(prixRando);
        r.setCapacite(capaciteRando);
        
        cr.createRando(r);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("You have added new Randonne");
        alert.setContentText("You can already show all added Randonnes");
        alert.showAndWait();
        
       /* Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Randonne.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(RandonneAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        */
        
        Image img1 = vimg1.getImage();
        Image img2 = vimg2.getImage();
        Image img3 = vimg3.getImage();
        
        String F1 = img1.impl_getUrl();
        String F2 = img2.impl_getUrl();
        String F3 = img3.impl_getUrl();

        F1 = F1.replace("file:/", "");
        F1 = F1.replace("%20", " ");
        
        F2 = F2.replace("file:/", "");
        F2 = F2.replace("%20", " ");
        
        F3 = F3.replace("file:/", "");
        F3 = F3.replace("%20", " ");
        System.out.println("F1 url "+F1); 
        
        String destination = dest.getText();
        
        
        String programme = prog.getText();
        String capacite = cap.getText();
        String prix = price.getText();
        
        //float prixRando = Float.parseFloat(prix);
        float prixRando = 0f;
        int capaciteRando = 0;
        String datechoosen ="";
        if(date.getValue() == null)
        {
            System.out.println("date non valide");
        }
        else{
            LocalDate datep = date.getValue();
            datechoosen = datep.toString();
        }
        
       
        if(destination.equals(""))
        {
            System.out.println("veuiller saisir la destination");
            return;
        }
        
        if(datechoosen.equals(""))
        {
            System.out.println("veuiller saisir la date");
            return;
        }
        
        if(programme.equals(""))
        {
            System.out.println("veuiller saisir le programme");
            return;
        }
        
        try{
            prixRando  = Float.parseFloat(prix);
        }catch(NumberFormatException ex)
        {
            System.out.println("Prix erroné!!");
        }
        try{
            capaciteRando = Integer.parseInt(capacite);
        }catch(NumberFormatException ex)
        {
            System.out.println("Capacité erroné!!");
        }
        
        
        
        /*CRUDRandonne cr = new CRUDRandonne();
        Randonne r = new Randonne();
        r.setDateRando(datechoosen);
        r.setDestinationRando(destination);
        r.setProgrammeRando(programme);
        r.setPrixPersonne(prixRando);
        r.setCapacite(capaciteRando);
        r.setPathImg1(F1);
        r.setPathImg2(F2);
        r.setPathImg3(F3);
        
        cr.createRando(r);
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("Success");
        alert.setContentText("You added a new Excursion");

        alert.showAndWait();
        
        dest.setText(null);
        date.setValue(null);
        prog.setText(null);
        cap.setText(null);
        price.setText(null);
        vimg1.setImage(null);
        vimg2.setImage(null);
        vimg3.setImage(null);*/
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        
        //vimg1.setImage(new Image("D:\\PIDEV Java\\java\\src\\esprit\\pidev\\images\\maldives-666122_1280.jpg"));
        
        File F = new File("D:/PIDEV Java/java/src/media/no-image.jpg");
        Image im1 = new Image(F.toURI().toString());
        vimg1.setImage(im1);
        vimg2.setImage(im1);
        vimg3.setImage(im1);
        
        
    }    
    
}
