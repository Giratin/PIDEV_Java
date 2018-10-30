package esprit.pidev.entities;

public class Reservation_voyage {
    private int idReservation;
    private int idVoyage;
    private int id;
    private int nbre_place_reserv;

    public int getNbre_place_reserv() {
        return nbre_place_reserv;
    }

    public void setNbre_place_reserv(int nbre_place_reserv) {
        this.nbre_place_reserv = nbre_place_reserv;
    }

    
    
    public int getIdReservation() {
        return idReservation;
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public int getId() {
        return id;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservation_voyage{" + "idReservation=" + idReservation + ", idVoyage=" + idVoyage + ", id=" + id + ", nbre_place_reserv=" + nbre_place_reserv + '}';
    }

    
    
    
    
}
