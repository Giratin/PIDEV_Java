package esprit.pidev.interfaces;

import esprit.pidev.entities.Voyage;
import java.sql.Date;
import java.util.ArrayList;

public interface IVoyage {

    public void ajouter(Voyage v);

    public void supprimer(Voyage v);

    public ArrayList<Voyage> afficher();

    public void modifier(Voyage v,Voyage v1);

    public ArrayList<Voyage> rechercher_Voyage(String destinationVoyage, String departVoyage);
   
    //public boolean voyage_diff(Voyage v1, Voyage v2) ;
        
    public int recuperer_id(Voyage v) ;
        
   


    
    

}
