/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.guif;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import esprit.pidev.entities.Client;
import esprit.pidev.entities.Randonne;
import esprit.pidev.services.CRUDRandonne;
import esprit.pidev.services.CRUDReservationRandonne;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author STA
 */
public class EnrollExcursionController implements Initializable, MapComponentInitializedListener {


    @FXML
    Text destination, price, program, date;
    @FXML
    ImageView btnclose, btnminimize, preview;
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    private GeocodingService geocodingService;
    private StringProperty address = new SimpleStringProperty();
    
    @FXML
    private Label longititude, latitude;
    Randonne R1 = new Randonne();
    CRUDRandonne CR= new CRUDRandonne();  
    
    public long id;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // mapView.addMapInializedListener(this);
       //mapView = new GoogleMapView();
      // mapView.addMapInializedListener(this);
      // Scene scene = new Scene(mapView);
       
       mapView.addMapInializedListener(this);
    }   

    public void setText(Randonne R)
    {
        //System.out.println("rando selected is: " + R);
        destination.setText(R.getDestinationRando());
        price.setText(Float.toString(R.getPrixPersonne()));
        program.setText(R.getProgrammeRando());
        date.setText(R.getDateRando());
        
        //System.out.println("this is: "+ R.getDestinationRando());
        
        R1 = CR.dispalyRandonne(R);
        id = R1.getIdRando();
        
        String coordoneString ="";
        ArrayList<Randonne> randonnes = new ArrayList<>();
        
        CRUDRandonne CR = new CRUDRandonne();
        randonnes = CR.displayRando();
        
        for(int i=0; i<randonnes.size(); i++)
        {
            if(randonnes.get(i).getIdRando() == id)
            {
                coordoneString = randonnes.get(i).getGoogleMaps();
                break;
            }
        }
        
        if(!coordoneString.equals(""))
        {
            System.out.println("coordonee "+coordoneString);
            //longititude, latitude
            
            String longit = "";
            String lati = "";
            
            lati = coordoneString.substring(coordoneString.lastIndexOf("-")+1, coordoneString.length());
            longit = (coordoneString.replace(lati, "")).replace("-", "");
            
            longititude.setText(longit);
            latitude.setText(lati);
            
            longititude.setVisible(false);
            latitude.setVisible(false);
            System.out.println("longit part: "+longit);
            System.out.println("lati part: "+lati);
        }
        
        
        
        
        
        File image = new File(R1.getPathImg1());
        Image im = new Image(image.toURI().toString());
        preview.setImage(im);
    }
    
    public void closnd(MouseEvent event)
    {
        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }
    
    public void btnminimize(MouseEvent event)
    {
        minimizeStageOfNode((Node) event.getSource());
    }
    
    private void minimizeStageOfNode(Node node) 
    {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }
    
    public void next(ActionEvent event)
    {
        File f =  new File("");
        Image im1 = new Image(f.toURI().toString());
        Image im2 = new Image(f.toURI().toString());
        Image im3 = new Image(f.toURI().toString());
        
        if(!R1.getPathImg1().equals(""))
        {
            File image1 = new File(R1.getPathImg1());
            im1 = new Image(image1.toURI().toString());
        }
        
        if(!R1.getPathImg2().equals(""))
        {
            File image2 = new File(R1.getPathImg2());
            im2 = new Image(image2.toURI().toString());
        }

        if(!R1.getPathImg3().equals(""))
        {
            File image3 = new File(R1.getPathImg3());
            im3 = new Image(image3.toURI().toString());
        }

        String url = preview.getImage().impl_getUrl();  
        
        
        
        url = url.replace("file:/", "");
        url = url.replace("%20", " ");


        System.out.println("im1 "+R1.getPathImg1());
        System.out.println("im2 "+R1.getPathImg2());
        System.out.println("im3 "+R1.getPathImg3());

        if(url.equalsIgnoreCase(R1.getPathImg1()))
        {
            preview.imageProperty().set(im2);
            System.out.println("image 1 is selected");
        }
        
        else if(url.equals(R1.getPathImg2()))
        {
            preview.imageProperty().set(im3);
            System.out.println("image 2 is selected");
        }
        
        else if(url.equals(R1.getPathImg3()))
        {
            preview.imageProperty().set(im1);
            System.out.println("image 3 is selected");
        }
        else{
            preview.imageProperty().set(im1);
        }
    }
    
    public void previous(ActionEvent event)
    {
        File image1 = new File(R1.getPathImg1());
        Image im1 = new Image(image1.toURI().toString());

        File image2 = new File(R1.getPathImg2());
        Image im2 = new Image(image2.toURI().toString());
        
        File image3 = new File(R1.getPathImg3());
        Image im3 = new Image(image3.toURI().toString());
         
        String url = preview.getImage().impl_getUrl();               
        
        url = url.replace("file:/", "");
        url = url.replace("%20", " ");


        /*
        System.out.println("im1 "+R1.getPathImg1());
        System.out.println("im2 "+R1.getPathImg2());
        System.out.println("im3 "+R1.getPathImg3());
        */
        if(url.equalsIgnoreCase(R1.getPathImg1()))
        {
            preview.imageProperty().set(im3);
            //System.out.println("image 3 is selected");
        }
        
        else if(url.equals(R1.getPathImg2()))
        {
            preview.imageProperty().set(im1);
            //System.out.println("image 1 is selected");
        }
        
        else if(url.equals(R1.getPathImg3()))
        {
            preview.imageProperty().set(im2);
            //System.out.println("image 2 is selected");
        }
        else{
            preview.imageProperty().set(im1);
        }
    }

    @Override
    public void mapInitialized() 
    {
         geocodingService = new GeocodingService();

        LatLong Location = new LatLong(Double.parseDouble(latitude.getText()), Double.parseDouble(longititude.getText()));

        
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        //LatLong Location = new LatLong(36.8981646, 10.1876613);
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
                .zoom(15);
        map = mapView.createMap(mapOptions);

    }
    
    @FXML
    public void enroll(ActionEvent event){
        CRUDReservationRandonne CRR = new CRUDReservationRandonne();
        
        Client C = new Client();
        C.setId(1);
        C.setEmail("yassine.sta@esprit.tn");
        CRR.ajouterClientRandonne((int) id, C);
        

        //System.out.println("idRando "+listClient.get(0).getIdRando());
        
        
    }
}
