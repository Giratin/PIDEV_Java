/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.guif;

import esprit.pidev.gui.*;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import esprit.pidev.entities.Randonne;
import esprit.pidev.services.CRUDRandonne;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author STA
 */
public class ViewExcursionController implements Initializable, MapComponentInitializedListener {


    @FXML
    Text destination, price, program, date;
    @FXML
    ImageView btnclose, btnminimize, preview;
    
    @FXML
    private GoogleMap map;
    @FXML
    private GoogleMapView mapView;

    Randonne R1 = new Randonne();
    CRUDRandonne CR= new CRUDRandonne();  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // mapView.addMapInializedListener(this);
       mapView = new GoogleMapView();
       mapView.addMapInializedListener(this);
       Scene scene = new Scene(mapView);
    }   
    

    @FXML
    public void setMap(MouseEvent event) 
    {
        /*mapView.addMapInializedListener(this);
        String i = R1.getDestinationRando();
        double longi;
        double lati;

        i = i.substring(i.indexOf("lat"));
        i = i.substring(i.indexOf(":"));
        Integer a = Integer.parseInt(i.substring(1, 3));
        //   System.out.println(a);
        i = i.substring(i.indexOf('"') + 1);
        String lat = i.substring(0, a);
        lati = Double.parseDouble(lat);
        //   System.out.println("lati: "+lati);
        i = i.substring(i.indexOf("lng"));
        i = i.substring(i.indexOf(":"));
        Integer b = Integer.parseInt(i.substring(1, 3));
        i = i.substring(i.indexOf('"') + 1);
        String lng = i.substring(0, b);
        longi = Double.parseDouble(lng);
        LatLong Location = new LatLong(lati, longi);

        MapOptions mapOptions = new MapOptions();
        mapOptions.center(Location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(false)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(13);

        map = mapView.createMap(mapOptions);

        MarkerOptions markerOptions1 = new MarkerOptions();
        Marker m = new Marker(markerOptions1);

        markerOptions1.position(Location)
                .visible(true);
        m.setOptions(markerOptions1);
        map.addMarker(m);*/

    }

       
    public void setText(Randonne R)
    {
        System.out.println("rando selected is: " + R);
        destination.setText(R.getDestinationRando());
        price.setText(Float.toString(R.getPrixPersonne()));
        program.setText(R.getProgrammeRando());
        date.setText(R.getDateRando());
        
        R1 = CR.dispalyRandonne(R);
        
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


        System.out.println("im1 "+R1.getPathImg1());
        System.out.println("im2 "+R1.getPathImg2());
        System.out.println("im3 "+R1.getPathImg3());

        if(url.equalsIgnoreCase(R1.getPathImg1()))
        {
            preview.imageProperty().set(im3);
            System.out.println("image 3 is selected");
        }
        
        else if(url.equals(R1.getPathImg2()))
        {
            preview.imageProperty().set(im1);
            System.out.println("image 1 is selected");
        }
        
        else if(url.equals(R1.getPathImg3()))
        {
            preview.imageProperty().set(im2);
            System.out.println("image 2 is selected");
        }
    }

    @Override
    public void mapInitialized() 
    {
        MapOptions mapOptions = new MapOptions();
        LatLong Location = new LatLong(36.8981646, 10.1876613);
        MarkerOptions markerOptions1 = new MarkerOptions();
        Marker m = new Marker(markerOptions1);
        //  System.out.println(Location);
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

    }
}
