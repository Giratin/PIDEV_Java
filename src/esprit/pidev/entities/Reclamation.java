/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author STA
 */
public class Reclamation {
    private int id;
    private int idUtilisateur;
    private String dateReclamation;
    private String message;

    public Reclamation() {
    }

    public Reclamation(int id, int idUtilisateur, String dateReclamation, String message) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateReclamation = dateReclamation;
        this.message = message;
    }

    public Reclamation(int idUtilisateur, String message) 
    {
        this.idUtilisateur = idUtilisateur;
        this.message = message;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.dateReclamation = format.format( new Date());
        System.out.println("date: "+this.dateReclamation);
    }

    public Reclamation(String dateReclamation, String message) {
        this.dateReclamation = dateReclamation;
        this.message = message;
    }

    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(String dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

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
        final Reclamation other = (Reclamation) obj;
        if (this.idUtilisateur != other.idUtilisateur) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id user : " + idUtilisateur +", date :  "+dateReclamation +", message : " + message;
    }
    
    
}
