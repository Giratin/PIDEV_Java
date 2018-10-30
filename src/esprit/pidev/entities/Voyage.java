package esprit.pidev.entities;

import java.util.Date;
import java.util.Objects;

public class Voyage {

    private int idVoyage;
    private String destinationVoyage;
    private Date dateVoyageAller;
    private Date hdepartVoyageAller;
    private Date harriveeVoyageAller;
    private Date dateVoyageRetour;
    private Date hdepartVoyageRetour;
    private Date harriveeVoyageRetour;
    private String departVoyage;
    private int nb_place_dispo;
    private String type;

    public Voyage(String destinationVoyage, Date dateVoyageAller, Date hdepartVoyageAller, Date harriveeVoyageAller, String departVoyage, int nb_place_dispo, String type) {
        this.destinationVoyage = destinationVoyage;
        this.dateVoyageAller = dateVoyageAller;
        this.hdepartVoyageAller = hdepartVoyageAller;
        this.harriveeVoyageAller = harriveeVoyageAller;
        this.departVoyage = departVoyage;
        this.nb_place_dispo = nb_place_dispo;
        this.type = type;
    }

    public Voyage(String destinationVoyage, Date dateVoyageAller, Date hdepartVoyageAller, Date harriveeVoyageAller, Date dateVoyageRetour, Date hdepartVoyageRetour, Date harriveeVoyageRetour, String departVoyage, int nb_place_dispo, String type) {
        this.destinationVoyage = destinationVoyage;
        this.dateVoyageAller = dateVoyageAller;
        this.hdepartVoyageAller = hdepartVoyageAller;
        this.harriveeVoyageAller = harriveeVoyageAller;
        this.dateVoyageRetour = dateVoyageRetour;
        this.hdepartVoyageRetour = hdepartVoyageRetour;
        this.harriveeVoyageRetour = harriveeVoyageRetour;
        this.departVoyage = departVoyage;
        this.nb_place_dispo = nb_place_dispo;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setHarriveeVoyageAller(Date harriveeVoyageAller) {
        this.harriveeVoyageAller = harriveeVoyageAller;
    }

    public void setDateVoyageRetour(Date dateVoyageRetour) {
        this.dateVoyageRetour = dateVoyageRetour;
    }

    public void setHdepartVoyageRetour(Date hdepartVoyageRetour) {
        this.hdepartVoyageRetour = hdepartVoyageRetour;
    }

    public void setHarriveeVoyageRetour(Date harriveeVoyageRetour) {
        this.harriveeVoyageRetour = harriveeVoyageRetour;
    }

    public Date getDateVoyageRetour() {
        return dateVoyageRetour;
    }

    public Date getHdepartVoyageRetour() {
        return hdepartVoyageRetour;
    }

    public Date getHarriveeVoyageRetour() {
        return harriveeVoyageRetour;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNb_place_dispo() {
        return nb_place_dispo;
    }

    public void setNb_place_dispo(int nb_place_dispo) {
        this.nb_place_dispo = nb_place_dispo;
    }

    public Voyage(int idVoyage, String destinationVoyage, Date dateVoyageAller, Date hdepartVoyageAller, Date harriveeVoyageAller, String departVoyage) {
        this.idVoyage = idVoyage;
        this.destinationVoyage = destinationVoyage;
        this.dateVoyageAller = dateVoyageAller;
        this.hdepartVoyageAller = hdepartVoyageAller;
        this.harriveeVoyageAller = harriveeVoyageAller;
        this.departVoyage = departVoyage;
    }

    public Voyage() {
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public String getDestinationVoyage() {
        return destinationVoyage;
    }

    public Date getDateVoyageAller() {
        return dateVoyageAller;
    }

    public Date getHdepartVoyageAller() {
        return hdepartVoyageAller;
    }

    public Date getHarriveeVoyageAller() {
        return harriveeVoyageAller;
    }

    public String getDepartVoyage() {
        return departVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public void setDestinationVoyage(String destinationVoyage) {
        this.destinationVoyage = destinationVoyage;
    }

    public void setDateVoyageAller(Date dateVoyageAller) {
        this.dateVoyageAller = dateVoyageAller;
    }

    public void setHdepartVoyageAller(Date hdepartVoyageAller) {
        this.hdepartVoyageAller = hdepartVoyageAller;
    }

    public void setHarriveeVoyage(Date harriveeVoyageAller) {
        this.harriveeVoyageAller = harriveeVoyageAller;
    }

    public void setDepartVoyage(String departVoyage) {
        this.departVoyage = departVoyage;
    }

    @Override
    public String toString() {
        return "Voyage{" + "idVoyage=" + idVoyage + ", destinationVoyage=" + destinationVoyage + ", dateVoyageAller=" + dateVoyageAller + ", hdepartVoyageAller=" + hdepartVoyageAller + ", harriveeVoyageAller=" + harriveeVoyageAller + ", dateVoyageRetour=" + dateVoyageRetour + ", hdepartVoyageRetour=" + hdepartVoyageRetour + ", harriveeVoyageRetour=" + harriveeVoyageRetour + ", departVoyage=" + departVoyage + ", nb_aplace dispo=" + nb_place_dispo + ", type=" + type + '}';
    }

    public Voyage(int idVoyage, String destinationVoyage, Date dateVoyageAller, Date hdepartVoyageAller, Date harriveeVoyageAller, Date dateVoyageRetour, Date hdepartVoyageRetour, Date harriveeVoyageRetour, String departVoyage, int nb_place_dispo, String type) {
        this.idVoyage = idVoyage;
        this.destinationVoyage = destinationVoyage;
        this.dateVoyageAller = dateVoyageAller;
        this.hdepartVoyageAller = hdepartVoyageAller;
        this.harriveeVoyageAller = harriveeVoyageAller;
        this.dateVoyageRetour = dateVoyageRetour;
        this.hdepartVoyageRetour = hdepartVoyageRetour;
        this.harriveeVoyageRetour = harriveeVoyageRetour;
        this.departVoyage = departVoyage;
        this.nb_place_dispo = nb_place_dispo;
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /*
    public boolean equals(Object obj) {

        if(obj instanceof Voyage)
        {
            Voyage v = (Voyage) obj;
            if((!this.destinationVoyage.equals(v.destinationVoyage)) && (!this.departVoyage.equals(v.departVoyage)) && (!this.type.equals(v.type)) && (!this.dateVoyageAller.equals(v.dateVoyageAller)) && (!this.hdepartVoyageAller.equals(v.hdepartVoyageAller)) && (!this.harriveeVoyageAller.equals(v.harriveeVoyageAller)) && (!this.dateVoyageRetour.equals(v.dateVoyageRetour)) && (!this.hdepartVoyageRetour.equals(v.hdepartVoyageRetour)) && (!this.harriveeVoyageRetour.equals(v.harriveeVoyageRetour)));
            {
                return false;
            }
        }
        return true;

    }
    */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voyage other = (Voyage) obj;
        if (this.idVoyage != other.idVoyage) {
            return false;
        }
        if (this.nb_place_dispo != other.nb_place_dispo) {
            return false;
        }
        if (!Objects.equals(this.destinationVoyage, other.destinationVoyage)) {
            return false;
        }
        if (!Objects.equals(this.departVoyage, other.departVoyage)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.dateVoyageAller, other.dateVoyageAller)) {
            return false;
        }
        if (!Objects.equals(this.hdepartVoyageAller, other.hdepartVoyageAller)) {
            return false;
        }
        if (!Objects.equals(this.harriveeVoyageAller, other.harriveeVoyageAller)) {
            return false;
        }
        if (!Objects.equals(this.dateVoyageRetour, other.dateVoyageRetour)) {
            return false;
        }
        if (!Objects.equals(this.hdepartVoyageRetour, other.hdepartVoyageRetour)) {
            return false;
        }
        if (!Objects.equals(this.harriveeVoyageRetour, other.harriveeVoyageRetour)) {
            return false;
        }
        return true;
    }
    
    

}
