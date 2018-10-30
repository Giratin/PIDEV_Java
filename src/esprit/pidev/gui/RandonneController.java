/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.gui;

import com.jfoenix.controls.JFXButton;
import esprit.pidev.entities.Randonne;
import esprit.pidev.services.CRUDRandonne;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author STA
 */
public class RandonneController implements Initializable {

    @FXML
    private TableColumn<Randonne, Integer> id;
    @FXML
    private TableColumn<Randonne, String> dest;
    @FXML
    private TableColumn<Randonne, Integer> nbre;
    @FXML
    private TableColumn<Randonne, String> date;
    @FXML
    private TableColumn<Randonne, Float> prix;
    @FXML
    private TableColumn<Randonne, String> programme;
    @FXML
    private TableColumn<Randonne, JFXButton> update;
    @FXML
    private TableColumn<Randonne, JFXButton> delete;
    @FXML
    private TableView<Randonne> tab;
    public static TableView<Randonne> tab_update;
    
    public static ObservableList<Randonne> tableData;    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tab_update = tab;
        initTable();
        LoadData();
    }    
    
    private void initTable()
    {
        initCols();
    }
    
    private void initCols()
    {
        id.setCellValueFactory(new PropertyValueFactory<>("idRando"));
        dest.setCellValueFactory(new PropertyValueFactory<>("DestinationRando"));
        nbre.setCellValueFactory(new PropertyValueFactory<>("nbreClient"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateRando"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prixPersonne"));
        programme.setCellValueFactory(new PropertyValueFactory<>("programmeRando"));
        update.setCellValueFactory(new PropertyValueFactory<>("update"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        
        editableCols();
    }
    
    private void editableCols()
    {
       /* id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        id.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setIdRando(e.getNewValue())
        );*/
        
        dest.setCellFactory(TextFieldTableCell.forTableColumn());
        dest.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setDestinationRando(e.getNewValue())
        );
        
       /* nbre.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nbre.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setNbreClient(e.getNewValue())
        );*/
        
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setDateRando(e.getNewValue())
        );
        
       /* prix.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        prix.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrixPersonne(e.getNewValue())
        );*/
        
        programme.setCellFactory(TextFieldTableCell.forTableColumn());
        programme.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setProgrammeRando(e.getNewValue())
        );
               
        tab.setEditable(true);
    }
    
    private void LoadData()
    {
         
        CRUDRandonne CR = new CRUDRandonne();
        ArrayList<Randonne> randonnes = new ArrayList<>();
        tableData = FXCollections.observableArrayList(randonnes);
        randonnes = CR.displayRando();
        
        for(int i=0; i< randonnes.size(); i++)
        {
            randonnes.get(i).setUpdate(new JFXButton("update"));
            randonnes.get(i).setDelete(new JFXButton("delete"));
            Randonne R = new Randonne(randonnes.get(i).getIdRando(),
                                      randonnes.get(i).getCapacite(),
                                      randonnes.get(i).getNbreClient(),
                                      randonnes.get(i).getDateRando(),
                                      randonnes.get(i).getDestinationRando(),
                                      randonnes.get(i).getNbreBus(),
                                      randonnes.get(i).getPrixPersonne(),
                                      randonnes.get(i).getProgrammeRando(),
                                      randonnes.get(i).getUpdate(),
                                      randonnes.get(i).getDelete()
            );
            //randonnes.get(i).setUpdate(new JFXButton("Update"));
            //randonnes.get(i).setDelete(new JFXButton("delete"));
            tableData.add(R);
  
        }
        
         tab.setItems(tableData);  
    }          
    
    public void delete(ActionEvent e)
    {
        System.out.println("selected");
    }
    
}
