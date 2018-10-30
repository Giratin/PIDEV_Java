package esprit.pidev.services;

import esprit.pidev.entities.Voyage;
import esprit.pidev.utils.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;
import esprit.pidev.interfaces.IVoyage;

public class CRUDVoyage implements IVoyage {

    Connection cnct = ConnectDB.getInstance().getConnection();

    public void ajouter(Voyage v) {
    CRUDVoyage CR = new CRUDVoyage();
        try {
            if (!CR.voyage_existe(v)) 
            {
                if (CR.verifier_pays(v) && CR.verifier_nb(v) && CR.verfier_type(v)) {

                    String query = "INSERT INTO `voyage`(`destinationVoyage`, `dateVoyageAller`, `hdepartVoyageAller`, `harriveeVoyageAller`,  `departVoyage` ,`nb_place_dispo`,`type`,`dateVoyageRetour`,`hdepartVoyageRetour` ,`harriveeVoyageRetour`)" + " VALUES (?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pst = cnct.prepareStatement(query);

                    pst.setString(1, v.getDestinationVoyage());
                    pst.setDate(2, new java.sql.Date(v.getDateVoyageAller().getTime()));
                    pst.setDate(3, new java.sql.Date(v.getHdepartVoyageAller().getTime()));
                    pst.setDate(4, new java.sql.Date(v.getHarriveeVoyageAller().getTime()));
                    pst.setString(5, v.getDepartVoyage());
                    pst.setInt(6, v.getNb_place_dispo());
                    pst.setString(7, v.getType());
                    pst.setDate(8, new java.sql.Date(v.getDateVoyageRetour().getTime()));
                    pst.setDate(9, new java.sql.Date(v.getHdepartVoyageRetour().getTime()));
                    pst.setDate(10, new java.sql.Date(v.getHarriveeVoyageAller().getTime()));
                    pst.executeUpdate();
                    System.out.println("added");

                } else {
                    System.out.println("Verifier vos parametre !!!");
                }
            } else {
                System.out.println("le voyage existe déjà");
            }
        } catch (SQLException e) {
            System.out.println("erreur lors de l'ajout");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Voyage> afficher() {
        ArrayList<Voyage> myList = new ArrayList<>();
        try {
            String query = "Select * from voyage";
            Statement pst = cnct.createStatement();
            ResultSet rs = pst.executeQuery(query);
            while (rs.next()) {
                Voyage v = new Voyage();
                v.setIdVoyage(rs.getInt("idVoyage"));
                v.setDestinationVoyage(rs.getString("destinationVoyage"));
                v.setDateVoyageAller(rs.getDate("dateVoyageAller"));
                v.setHdepartVoyageAller(rs.getDate("hdepartVoyageAller"));
                v.setHarriveeVoyage(rs.getDate("harriveeVoyageAller"));
                v.setDepartVoyage(rs.getString("departVoyage"));
                v.setNb_place_dispo(rs.getInt("nb_place_dispo"));
                v.setType(rs.getString("type"));
                v.setDateVoyageRetour(rs.getDate("dateVoyageRetour"));
                v.setHdepartVoyageRetour(rs.getDate("hdepartVoyageRetour"));
                v.setHarriveeVoyageAller(rs.getDate("harriveeVoyageAller"));

                myList.add(v);
               // System.out.println(v);
               // System.out.println("affichage fait");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

    public void modifier(Voyage v1, Voyage v2) {
        try {

            if (!v1.equals(v2)) {
                String query = "UPDATE `voyage` SET `destinationVoyage`=?,`dateVoyageAller`=?,`hdepartVoyageAller`=?,`harriveeVoyageAller`=? ,`departVoyage`=?,`nb_place_dispo`=?,`type`=?, `dateVoyageRetour`=? ,`hdepartVoyageRetour`=?,`harriveeVoyageRetour`=? WHERE `idVoyage`=?";

                PreparedStatement pst = cnct.prepareStatement(query);
                CRUDVoyage cv = new CRUDVoyage();
                pst.setString(1, v2.getDestinationVoyage());
                pst.setDate(2, new java.sql.Date(v2.getDateVoyageAller().getTime()));
                pst.setDate(3, new java.sql.Date(v2.getHdepartVoyageAller().getTime()));
                pst.setDate(4, new java.sql.Date(v2.getHarriveeVoyageAller().getTime()));
                pst.setString(5, v2.getDepartVoyage());
                pst.setInt(6, v2.getNb_place_dispo());
                pst.setString(7, v2.getType());
                pst.setDate(8, new java.sql.Date(v2.getDateVoyageRetour().getTime()));
                pst.setDate(9, new java.sql.Date(v2.getHdepartVoyageRetour().getTime()));
                pst.setDate(10, new java.sql.Date(v2.getHarriveeVoyageRetour().getTime()));
                pst.setInt(11, cv.recuperer_id(v1));
                pst.executeUpdate();
                System.out.println("avec succes le voyage est modifié");
            } else {
                System.out.println("vous avez entré les même données !!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "erreur lors de la mise a jour des donnees");
        }
    }

    public ArrayList<Voyage> rechercher_Voyage(String destinationVoyage, String departVoyage) {

        ArrayList<Voyage> myList = new ArrayList<>();
        String query = "SELECT * FROM `voyage` WHERE `destinationVoyage`=? AND `departVoyage`= ?";
        try {
            PreparedStatement pst = cnct.prepareStatement(query);
            pst.setString(1, destinationVoyage);
            pst.setString(2, departVoyage);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Voyage v = new Voyage();
                v.setIdVoyage(rs.getInt("idVoyage"));
                v.setDestinationVoyage(rs.getString("destinationVoyage"));
                v.setDateVoyageAller(rs.getDate("dateVoyageAller"));
                v.setHdepartVoyageAller(rs.getDate("hdepartVoyageAller"));
                v.setHarriveeVoyageAller(rs.getDate("harriveeVoyageAller"));
                v.setDepartVoyage(rs.getString("departVoyage"));
                v.setDateVoyageRetour(rs.getDate("dateVoyageRetour"));
                v.setHdepartVoyageAller(rs.getDate("hdepartVoyageAller"));
                v.setHarriveeVoyageRetour(rs.getDate("harriveeVoyageRetour"));
                myList.add(v);
            }
            if (myList.isEmpty()) {
                System.out.println("No data found in your BD");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "erreur lors de la recherche");
        }
        if (!myList.isEmpty()) {
            return myList;
        } else {
            return null;
        }
    }

    public boolean verifier_pays(Voyage v) {
        return (!v.getDestinationVoyage().equals(v.getDepartVoyage()));
    }

    public boolean verifier_nb(Voyage v) {
        return (v.getNb_place_dispo() >= 0);
    }

    public boolean verfier_type(Voyage v) {
        return (v.getType().equals("Aller simple") || v.getType().equals("Aller et retour"));

    }

    @Override
    public void supprimer(Voyage v) {

        String query = "DELETE FROM `voyage` WHERE `idVoyage`=?";
        try {
            if (this.voyage_existe(v)) {
                CRUDVoyage cv = new CRUDVoyage();
                PreparedStatement pst = cnct.prepareStatement(query);
                pst.setInt(1, cv.recuperer_id(v));
                pst.executeUpdate();
                String query1 = "DELETE FROM `reservation` WHERE `idVoyage`=?";
                PreparedStatement pst1 = cnct.prepareStatement(query1);
                pst.setInt(1, cv.recuperer_id(v));
                pst.executeUpdate();
                System.out.println("suppression avec succes");

            } else {
                System.out.println("Voyages inexistant");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("erreur lors de la suppression");
        }

    }

    public int recuperer_id(Voyage v) {

        ArrayList<Voyage> v1 = new ArrayList<>();
        CRUDVoyage CR = new CRUDVoyage();
        v1 = CR.afficher();
       // System.out.println("size " + v1.size());
        int id = -1;
        //System.out.println("size: " + voyages.size());
        for (int i = 0; i < v1.size(); i++) 
        {
            if (v1.get(i).equals(v)) 
            {
                id = v1.get(i).getIdVoyage();
                break;
            }
        }
        return id;
    }

    public boolean voyage_existe(Voyage v) {
        CRUDVoyage CR = new CRUDVoyage();
        if (CR.recuperer_id(v) == -1) {
            return false;
        }
        return true;
    }
}
