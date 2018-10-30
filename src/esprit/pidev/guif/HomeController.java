/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.guif;

import com.jfoenix.controls.JFXButton;
import esprit.pidev.entities.Client;
import esprit.pidev.entities.Randonne;
import esprit.pidev.entities.Reclamation;
import esprit.pidev.services.CRUDRandonne;
import esprit.pidev.services.CRUDReservationRandonne;
import esprit.pidev.services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author STA
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane HomePane, ExcursionPane,JourneyPane, HotelPane, ActivityPane, EventPane, ProfilePane ;
    @FXML
    private ImageView btnclose;
    
    /*
    * Excursion
    */
    @FXML
    private TableColumn<Randonne, String> col_dest, col_dest2, col_dest3;
    @FXML
    private TableColumn<Randonne, Integer> col_nbre, col_nbre2;
    @FXML
    private TableColumn<Randonne, String> col_date, col_date2, col_date3;
    @FXML
    private TableColumn<Randonne, Float> col_price, col_price2, col_price3;
    @FXML
    private TableColumn<Randonne, String> col_prog, col_prog2, col_prog3;
    @FXML
    private TableView<Randonne> tab,tab_archieve, tab_enrolled;
    
    /*
    *   Claim
    */
    @FXML 
    private TableView<Reclamation> tabClaim;
    @FXML
    private TableColumn<Reclamation, String> dateClaimCol, claimCol;
            
    @FXML
    private JFXButton btnenroll, btnView, btnDis;
    @FXML
    private TextField ExcursionSearch;
    
    public static ObservableList<Randonne> tableData, tableArchieve, tableEnrolled;
    public static ObservableList<Reclamation> obsClaims;
    
    @FXML
    private TextField nameClaim, emailClaim;
    @FXML
    private TextArea messageClaim;
    
    private double x,y;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        (HomePane).setVisible(true);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
        
        btnenroll.setDisable(true);
        btnView.setDisable(true);
        
        CRUDRandonne CR = new CRUDRandonne();
        ArrayList<Randonne> randonnes = new ArrayList<>();
        randonnes = CR.displayRando();
        ArrayList<String> possStrings =new ArrayList<>();
        
        for(int i=0; i< randonnes.size(); i++)
        {
            possStrings.add(randonnes.get(i).getDestinationRando());
        }
        TextFields.bindAutoCompletion(ExcursionSearch, possStrings);
    }    
    
    @FXML
    public void goHome(MouseEvent event)
    {
        (HomePane).setVisible(true);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
        
    }
    
    @FXML
    public void hotelhp(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(true);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
    }
    
    public void journeyhp(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(true);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
    }
    
    @FXML
    public void excursionhp(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(true);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
        
        init();
        LoadData();
    }
    
    @FXML
    public void eventhp(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(true);
        (ProfilePane).setVisible(false);
    }
    
    @FXML
    public void activityhp(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(true);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
    }
    
    @FXML
    public void excursion(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(true);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
        
        init();
        LoadData();
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Randonne> filteredDataActive = new FilteredList<>(tableData, p -> true);
        
        FilteredList<Randonne> filteredDataOld = new FilteredList<>(tableArchieve, p -> true);
        
         // 2. Set the filter Predicate whenever the filter changes.
        ExcursionSearch.textProperty().addListener((ObservableValue<? extends String> Observable, String oldValue, String newValue) ->{
            filteredDataActive.setPredicate(excursion ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                // Compare destination of every excursion with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if(excursion.getDestinationRando().toLowerCase().contains(lowerCaseFilter))
                    return true;
                
                return false;
            });
        });
        
        
         // 2. Set the filter Predicate whenever the filter changes.
        ExcursionSearch.textProperty().addListener((ObservableValue<? extends String> Observable, String oldValue, String newValue) ->{
            filteredDataOld.setPredicate(excursion ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                // Compare destination of every excursion with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if(excursion.getDestinationRando().toLowerCase().contains(lowerCaseFilter))
                    return true;
                
                return false;
            });
        });
        
        
         // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Randonne> sortedDataActive = new SortedList<>(filteredDataActive);
        SortedList<Randonne> sortedDataOld = new SortedList<>(filteredDataOld);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedDataActive.comparatorProperty().bind(tab.comparatorProperty());
        sortedDataOld.comparatorProperty().bind(tab_archieve.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        tab.setItems(sortedDataActive);
        tab_archieve.setItems(sortedDataOld);
    }
    
    @FXML
    public void get(MouseEvent event)
    {
        btnenroll.setDisable(false);
    }
    
    public Randonne getView()
    {
        Randonne R = tab_archieve.getSelectionModel().getSelectedItem();
        Randonne R1 = new Randonne();
        CRUDRandonne CR= new CRUDRandonne();
        R1 = CR.dispalyRandonne(R);
        return R1;
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
    
    public Randonne getEnrolledRando()
    {
        Randonne R = tab_enrolled.getSelectionModel().getSelectedItem();
        //System.out.println("R "+R.getDestinationRando());
        return R;
    }
    @FXML
    public void sendClaim(ActionEvent event)
    {
        /* TextField nameClaim, emailClaim;
            TextArea messageClaim;
        */
        
        String name = nameClaim.getText();
        String email = emailClaim.getText();
        String message = messageClaim.getText();
        
        if(name.equals(""))
            return;
        if(email.equals(""))
            return;
        if(message.equals(""))
            return;
        
        ReclamationService RS = new ReclamationService();
        Reclamation R = new Reclamation(1, message);
        RS.createClaim(R);
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sending claim");
        alert.setHeaderText(null);
        alert.setContentText("You've sent a new reclaim \nThank you for coperation");
        
        obsClaims.clear();
        LoadClaimsData();
        
        alert.showAndWait();
        
    }
    
    @FXML
    public void btnView(ActionEvent event)
    { 
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewExcursion.fxml"));
            Parent root = (Parent) loader.load();
            ViewExcursionController view = loader.getController();
            
            view.setText(getView());
            
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.initStyle(StageStyle.UNDECORATED);
            
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
                    newStage.setX(event.getScreenX() - x);
                    newStage.setY(event.getScreenY() - y);
                }
            });
            
            newStage.showAndWait();
     
        } catch (IOException ex) {
            System.out.println("Error "+ex.getMessage());
        }
    }
    
    @FXML
    public void btnDis(ActionEvent event)
    {
        Client C = new Client();
        C.setEmail("yassine.sta@esprit.tn");
        C.setId(1);
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you you want to unsubscribe from "+getEnrolledRando().getDestinationRando() +" ?");
        alert.setContentText("Please choose an option.");
        
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        
        alert.getButtonTypes().setAll(yesButton, noButton);
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == yesButton)
        {
            CRUDReservationRandonne CRR = new CRUDReservationRandonne();
            CRR.removeClientRando(C, getEnrolledRando());
            System.out.println("unsubscribed!!");
            refreshTableView();
        }
        else{
            System.out.println("operation cancledd");
        }
    }
    
    @FXML
    public void enroll(ActionEvent event)
    {
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
                    newStage.setX(event.getScreenX() - x);
                    newStage.setY(event.getScreenY() - y);
                }
            });
            
            newStage.showAndWait();
     
        } catch (IOException ex) {
            System.out.println("Error "+ex.getMessage());
        }
    }
    
    @FXML
    void getArchieve(MouseEvent event) 
    {
        btnView.setDisable(false);
    }
    
    @FXML 
    void getEnrolled(MouseEvent event )
    {
        btnDis.setDisable(false);
        System.out.println(getEnrolledRando());
    }
    
    private void init()
    {
        col_dest.setCellValueFactory(new PropertyValueFactory<>("DestinationRando"));
        col_nbre.setCellValueFactory(new PropertyValueFactory<>("nbreClient"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateRando"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("prixPersonne"));
        col_prog.setCellValueFactory(new PropertyValueFactory<>("programmeRando"));
        
        
        col_dest2.setCellValueFactory(new PropertyValueFactory<>("DestinationRando"));
        col_nbre2.setCellValueFactory(new PropertyValueFactory<>("nbreClient"));
        col_date2.setCellValueFactory(new PropertyValueFactory<>("dateRando"));
        col_price2.setCellValueFactory(new PropertyValueFactory<>("prixPersonne"));
        col_prog2.setCellValueFactory(new PropertyValueFactory<>("programmeRando"));
        
        col_dest3.setCellValueFactory(new PropertyValueFactory<>("DestinationRando"));
        col_date3.setCellValueFactory(new PropertyValueFactory<>("dateRando"));
        col_price3.setCellValueFactory(new PropertyValueFactory<>("prixPersonne"));
        col_prog3.setCellValueFactory(new PropertyValueFactory<>("programmeRando"));
        
    }
    
    private void LoadData()
    {
        CRUDRandonne CR = new CRUDRandonne();
        ArrayList<Randonne> randonnes = new ArrayList<>();
        tableData = FXCollections.observableArrayList(randonnes);
        tableArchieve = FXCollections.observableArrayList(randonnes);
        tableEnrolled = FXCollections.observableArrayList(randonnes);
        
        randonnes = CR.displayRando();
        
        //int nbreDispo = 0;
        for(int i=0; i< randonnes.size(); i++)
        {
            String date = randonnes.get(i).getDateRando();
            int nbreClient = randonnes.get(i).getNbreClient();
            int capacite = randonnes.get(i).getCapacite();
            int dispo = capacite - nbreClient;
                        
            try{
                
                Randonne activeRandonne = new Randonne();
                Randonne oldRonRandonne = new Randonne();

                Date today = new Date();
                
                String year = date.substring(0, date.indexOf("-"));
                String day = date.substring(date.lastIndexOf("-")+1, date.length());
                String month = ((date.replace(day,"")).replace(year,"")).replace("-", "");
                String ExcursionDate = day+"-"+month+"-"+year;
                
                Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(ExcursionDate);
                
                if(date1.compareTo(today) == -1)
                {
                    oldRonRandonne  = randonnes.get(i);
                    oldRonRandonne.setDateRando(date);
                    tableArchieve.add(oldRonRandonne);
                    System.out.println(date1 + "gduim");
                }  
                else
                {
                    activeRandonne = randonnes.get(i);
                    activeRandonne.setDateRando(date);
                    activeRandonne.setNbreClient(dispo);
                    tableData.add(activeRandonne);
                }                          
            }catch(ParseException ex)
            {
                System.out.println("date error " + ex.getMessage());
            }
        }
         tab.setItems(tableData); 
         tab_archieve.setItems(tableArchieve);
        
        CRUDReservationRandonne CRR = new CRUDReservationRandonne();

        Client C = new Client();
        C.setEmail("yassine.sta@esprit.tn");
        C.setId(1);
        ArrayList<Randonne> listClient =  new ArrayList<>();
        listClient = CRR.DisplayForClient(C);

        for(int i=0; i< listClient.size(); i++)
        {
            System.out.println("idRando "+listClient.get(i).getDestinationRando());
            
            Randonne R = new Randonne();
            R= listClient.get(i);
            
            tableEnrolled.add(R);
        }
        
        tab_enrolled.setItems(tableEnrolled);     
    }  
    
    private void initClaim()
    {
        dateClaimCol.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));
        claimCol.setCellValueFactory(new PropertyValueFactory<>("message"));
    }
    
    
    private void LoadClaimsData()
    {
        ReclamationService RS = new ReclamationService();
        
        ArrayList<Reclamation> claims = new ArrayList<>();
        claims= RS.displayClaim(1);
        
        obsClaims = FXCollections.observableArrayList(claims);
        /*for(int i=0; i< claims.size(); i++)
        { 
            Reclamation R = new Reclamation(
                    claims.get(i).getId(),
                    claims.get(i).getIdUtilisateur(),
                    claims.get(i).getDateReclamation(),
                    claims.get(i).getMessage()
            );*/
            
            //tableClaim.add(R);
        //}
        tabClaim.setItems(obsClaims);
    }
    
    
    
    @FXML
    public void btnminimize(MouseEvent event)
    {
      minimizeStageOfNode((Node) event.getSource());
    }

    private void minimizeStageOfNode(Node node) 
    {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }
    
    @FXML
    public void closnd(MouseEvent event)
    {
        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }
    
    public void refreshTableView()
    {

        tableEnrolled.clear();
        tableData.clear();
        tableArchieve.clear();
        LoadData();
    }
    
    @FXML
    public void refresh(MouseEvent event)
    {
        refreshTableView();
    }    

    
    @FXML
    public void goProfile(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(true);
        
        initClaim();
        LoadClaimsData();
    }
    
    @FXML
    public void goJourney(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(true);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
    }
    
    @FXML
    public void goHotel(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(true);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
    }
    
    @FXML
    public void goActivity(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(true);
        (EventPane).setVisible(false);
        (ProfilePane).setVisible(false);
    }
    
    @FXML
    public void goEvent(MouseEvent event)
    {
        (HomePane).setVisible(false);
        (ExcursionPane).setVisible(false);
        (JourneyPane).setVisible(false);
        (HotelPane).setVisible(false);
        (ActivityPane).setVisible(false);
        (EventPane).setVisible(true);
        (ProfilePane).setVisible(false);
    }
}
