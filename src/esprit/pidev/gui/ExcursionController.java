/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.gui;

import com.jfoenix.controls.JFXButton;
import esprit.pidev.entities.Randonne;
import static esprit.pidev.gui.RandonneController.tableData;
import esprit.pidev.services.CRUDRandonne;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ExcursionController implements Initializable {

    @FXML 
    private ImageView btnclose;
    @FXML 
    private ImageView btnminimize;
    @FXML 
    AnchorPane parent;
    @FXML
    private TableColumn<Randonne, String> col_dest;
    @FXML
    private TableColumn<Randonne, Integer> col_nbre;
    @FXML
    private TableColumn<Randonne, String> col_date;
    @FXML
    private TableColumn<Randonne, Float> col_price;
    @FXML
    private TableColumn<Randonne, String> col_prog;
    @FXML
    private TableView<Randonne> tab;
    @FXML
    private JFXButton btnenroll;
    
    public static ObservableList<Randonne> tableData;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
        LoadData();
        btnenroll.setDisable(true);    
        
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
        
    private void init()
    {
        col_dest.setCellValueFactory(new PropertyValueFactory<>("DestinationRando"));
        col_nbre.setCellValueFactory(new PropertyValueFactory<>("nbreClient"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateRando"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("prixPersonne"));
        col_prog.setCellValueFactory(new PropertyValueFactory<>("programmeRando"));
        
    }
    
    private void LoadData()
    {
        CRUDRandonne CR = new CRUDRandonne();
        ArrayList<Randonne> randonnes = new ArrayList<>();
        tableData = FXCollections.observableArrayList(randonnes);
        randonnes = CR.displayRando();
        
        int nbreDispo = 0;
        for(int i=0; i< randonnes.size(); i++)
        {
            tableData.add(randonnes.get(i));
            
        }
         tab.setItems(tableData);  
    }  
    
    public Randonne getIdSelection()
    {
        Randonne R = tab.getSelectionModel().getSelectedItem();
        System.out.println(R.getIdRando() +" " + R.getDestinationRando());
        Randonne R1 = new Randonne();
        CRUDRandonne CR= new CRUDRandonne();
        System.out.println(CR.dispalyRandonne(R1));
        R1 = CR.dispalyRandonne(R);
        return R1;
    }
    
    @FXML
    public void enroll(ActionEvent event)
    {
        System.out.println("click");
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EnrollExcursion.fxml"));
            Parent root = (Parent) loader.load();
            EnrollExcursionController enroll = loader.getController();
            
            enroll.setText(getIdSelection());
            
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.showAndWait();
     
        } catch (IOException ex) {
            System.out.println("Error "+ex.getMessage());
        }
        

        //Close actual login window
/*      
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
*/
        //open Home window
        
    }
    
    public void get(MouseEvent event)
    {
        btnenroll.setDisable(false);
    }
    
    
    public void goHome(MouseEvent event)
    {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = (Parent) loader.load();
                HomeController home = loader.getController();
         
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
    
    
}
