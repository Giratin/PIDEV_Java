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
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import esprit.pidev.entities.Randonne;
import esprit.pidev.services.CRUDRandonne;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nejmi
 */
public class ExcursionAdminController implements Initializable, MapComponentInitializedListener {

    
    @FXML
    private JFXTextField dest,price,nbre;
    @FXML
    private JFXTextArea prog;
    @FXML
    private ImageView vimg1, vimg2, vimg3, btn_close, destError, priceError, nbreError, progError, dateError;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton addExcursion;
    private ArrayList<String> listFiles;
    @FXML
    private TextField longitude, latitude;
    @FXML
    private Label msg1, msg2;
    
    @FXML
    private GoogleMapView mapView;
     private GoogleMap map;
    
    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    
    @FXML
    public void img1(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        System.out.println(f.toURI().toString());
            
        if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            vimg1.setImage(im);
            addExcursion.setDisable(false);
        }
        else{
            addExcursion.setDisable(true);
        }
                      
    }

    @FXML
    public void img2(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        
         if(f != null)
        {
            addExcursion.setDisable(false);
            Image im = new Image(f.toURI().toString());
            vimg2.setImage(im);
        }    
         else
             addExcursion.setDisable(true);
    }
    
    @FXML
    public void img3(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("World Files", listFiles));
        
        File f = fc.showOpenDialog(null);
        
         if(f != null)
        {
            Image im = new Image(f.toURI().toString());
            vimg3.setImage(im);
            addExcursion.setDisable(false);
        } 
         else{
             addExcursion.setDisable(true);
         }         
    }
    
    public void reset(ActionEvent event)
    {
        dest.setText(null);
        price.setText(null);
        nbre.setText(null);
        prog.setText(null);
        File F = new File("D:/PIDEV Java/java/src/media/no-image.jpg");
        Image im1 = new Image(F.toURI().toString());
        vimg1.setImage(im1);
        vimg2.setImage(im1);
        vimg3.setImage(im1);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addExcursion.setDisable(true);
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
                
        File F = new File("D:/PIDEV Java/java/src/media/no-image.jpg");
        Image im1 = new Image(F.toURI().toString());
        vimg1.setImage(im1);
        vimg2.setImage(im1);
        vimg3.setImage(im1);
        
        
        mapView.addMapInializedListener(this);
        address.bind(dest.textProperty());
    }  
    
    public void btn_close(MouseEvent enent)
    {
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.close();
    }
    
    public void btn_minimize(MouseEvent event)
    {
        minimizeStageOfNode((Node) event.getSource());
    }
    
    private void minimizeStageOfNode(Node node) 
    {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }
    
    @FXML 
    public void add(ActionEvent event)
    { 
        Image img1 = vimg1.getImage();
        Image img2 = vimg2.getImage();
        Image img3 = vimg3.getImage();
        
        //recuperate images path
        String F1 = img1.impl_getUrl();
        String F2 = img2.impl_getUrl();
        String F3 = img3.impl_getUrl();

        F1 = F1.replace("file:/", "");      F1 = F1.replace("%20", " ");
        F2 = F2.replace("file:/", "");      F2 = F2.replace("%20", " ");
        F3 = F3.replace("file:/", "");      F3 = F3.replace("%20", " ");
        
        System.out.println("F1 url "+F1); 

        destError.setVisible(false);
        dateError.setVisible(false);
        progError.setVisible(false);
        priceError.setVisible(false);
        nbreError.setVisible(false);
        
        String destination = dest.getText();
        String programme = prog.getText();
        String capacite = nbre.getText();
        String prix = price.getText();
        
        //float prixRando = Float.parseFloat(prix);
        float prixRando = 0f;
        int capaciteRando = 0;
        String datechoosen ="";
        if(date.getValue() == null)
        {
            System.out.println("date non valide");
            return;
        }
        else{
            LocalDate datep = date.getValue();
            datechoosen = datep.toString();
        }
        
       
        if(destination.equals(""))
        {
            System.out.println("veuiller saisir la destination");
            destError.setVisible(true);
            dest.setText(null);
            return;
        }
        else{
            destError.setVisible(false);
        }
        
        if(datechoosen.equals(""))
        {
            System.out.println("veuiller saisir la date");
            dateError.setVisible(true);
            date.setValue(null);
            return;
        }
        
        if(programme.equals(""))
        {
            System.out.println("veuiller saisir le programme");
            progError.setVisible(true);
            prog.setText(null);
            return;
        }
        else{
            progError.setVisible(false);
        }
        
        try{
            prixRando  = Float.parseFloat(prix);
        }catch(NumberFormatException ex)
        {
            priceError.setVisible(true);
            price.setText(null);
            System.out.println("Prix erroné!!");
            System.out.println(ex.getMessage());
            return;
        }
        try{
            capaciteRando = Integer.parseInt(capacite);
        }catch(NumberFormatException ex)
        {
            nbreError.setVisible(true);
            nbre.setText(null);
            System.out.println("Capacité erroné!!");
            System.out.println(ex.getMessage());
            return;
        }
        
        if(longitude.getText().equals("") && latitude.getText().equals(""))
        {
            msg1.setVisible(true);
            msg2.setVisible(true);
            return;
        }
            
        String coordonne = longitude.getText() + "-" + latitude.getText();
        
        
        
        CRUDRandonne cr = new CRUDRandonne();
        Randonne r = new Randonne();
        r.setDateRando(datechoosen);
        r.setDestinationRando(destination);
        r.setProgrammeRando(programme);
        r.setPrixPersonne(prixRando);
        r.setCapacite(capaciteRando);
        r.setGoogleMaps(coordonne);
        r.setPathImg1(F1);
        r.setPathImg2(F2);
        r.setPathImg3(F3);
        
        cr.createRando(r);
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("You added a new Excursion");

        alert.showAndWait();
        
        dest.setText(null);
        date.setValue(null);
        prog.setText(null);
        nbre.setText(null);
        price.setText(null);
        msg1.setVisible(false);
        msg2.setVisible(false);
        addExcursion.setDisable(true);
        
        File F = new File("D:/PIDEV Java/java/src/media/no-image.jpg");
        Image im1 = new Image(F.toURI().toString());
        vimg1.setImage(im1);
        vimg2.setImage(im1);
        vimg3.setImage(im1);
    }
    
    
    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        LatLong Location = new LatLong(36.8981646, 10.1876613);
        MarkerOptions markerOptions1 = new MarkerOptions();
        Marker m = new Marker(markerOptions1);
        System.out.println(Location);
        mapOptions.center(Location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(13);
        map = mapView.createMap(mapOptions);
        
        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
            markerOptions1.position(event.getLatLong())
                    .visible(true)
                    ;
            m.setOptions(markerOptions1);
            map.addMarker(m);
            double longi = event.getLatLong().getLongitude();
            
            double lat = event.getLatLong().getLatitude();
            System.out.println(Double.toString(longi));
            System.out.println(Double.toString(lat));
            
            longitude.setText(Double.toString(longi));
            latitude.setText(Double.toString(lat));
            
           geocodingService.reverseGeocode(event.getLatLong().getLatitude(), event.getLatLong().getLongitude(), new GeocodingServiceCallback() {
                @Override
                public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
                 String  address= grs[2].toString().substring(grs[2].toString().indexOf("Address: "), grs[2].toString().indexOf(", Tunisia"));
                 address=address.substring(address.indexOf(" ")+1, address.length());
                    System.out.println(address);                
                }

                
            });
            
        }); 
        
    }
    
    
    @FXML
    public void adressDest(ActionEvent event)
    {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
            LatLong latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            
            map.setCenter(latLong);

        });
    }
    
}
