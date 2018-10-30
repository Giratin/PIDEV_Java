package esprit.pidev.interfaces;

import esprit.pidev.entities.Client;
import esprit.pidev.entities.Reservation_voyage;
import esprit.pidev.entities.Voyage;
import java.util.ArrayList;

public interface IReservation_voyage {
    public void reserver_voyage(Voyage v,Client c,int nb_de_reservation);
    public void annuler_reservation_voyage(Voyage v, Client c);
    public int recuperer_id( Client c,Voyage v);
    public ArrayList<Reservation_voyage> afficher_Reservations();
    
}
