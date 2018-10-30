/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.services;

import esprit.pidev.interfaces.IReservation_voyage;
import esprit.pidev.entities.Client;
import esprit.pidev.entities.Reservation_voyage;
import esprit.pidev.entities.Voyage;
import java.sql.Connection;
import java.util.ArrayList;
import esprit.pidev.utils.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sirine
 */
public class CRUDReservation implements IReservation_voyage {

    Connection cnx = ConnectDB.getInstance().getConnection();

    
    public void reserver_voyage(Voyage v, Client c,int nb_de_reservation) {
        //ajouter les reservations
        String query="INSERT INTO `reservationvoyage`( `idVoyage`, `id`,`nbre_place_reserv`) VALUES (?,?,?)";
        try {
            CRUDVoyage cv =new CRUDVoyage();
            CRUDClient cc=new CRUDClient();
            PreparedStatement  pst= cnx.prepareStatement(query);
            pst.setInt(1,cv.recuperer_id(v));
            pst.setInt(2,cc.reccuperer_id(c));
            pst.setInt(3,nb_de_reservation);

            pst.executeUpdate();
        //reccuperer le nbre de place dispo 
        String query2="select `nb_place_dispo` from `voyage` WHERE idVoyage=?  ";
        PreparedStatement st=cnx.prepareStatement(query2);
        st.setInt(1, cv.recuperer_id(v));
        ResultSet rs=st.executeQuery();
       
        while(rs.next()){
            v.setNb_place_dispo(rs.getInt("nb_place_dispo"));
           // System.out.println(v.getNb_place_dispo());
        //diminuer le bnre de place dispo
        String querry1="UPDATE `voyage` SET `nb_place_dispo` =? WHERE idVoyage=?";
        PreparedStatement pst1=cnx.prepareStatement(querry1);
        pst1.setInt(1,v.getNb_place_dispo()-nb_de_reservation);
        pst1.setInt(2, cv.recuperer_id(v));
        pst1.executeUpdate();}
       }
        catch (SQLException ex) {
            Logger.getLogger(CRUDReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    public void annuler_reservation_voyage(Voyage v, Client c) {
        
        
     //supprimer une reservation
        try{
             Reservation_voyage r= new Reservation_voyage();
            CRUDVoyage cv =new CRUDVoyage();
            CRUDClient cc=new CRUDClient();
        
        //reccuperer le nbre de places dispo
        String query3="select `nb_place_dispo` from `voyage` WHERE idVoyage=? ";
        PreparedStatement st3=cnx.prepareStatement(query3);
        st3.setInt(1, cv.recuperer_id(v));
        ResultSet rs3=st3.executeQuery();
        while(rs3.next()){
            v.setNb_place_dispo(rs3.getInt("nb_place_dispo"));
           System.out.println(v.getNb_place_dispo());
        String query5="select `nbre_place_reserv` from `reservationvoyage` WHERE idVoyage=? and id=? ";
        PreparedStatement st5=cnx.prepareStatement(query5);
        st5.setInt(1, cv.recuperer_id(v));
        st5.setInt(2, cc.reccuperer_id(c));
        ResultSet rs5=st5.executeQuery();
        while(rs5.next()){
           r.setNbre_place_reserv(rs5.getInt("nbre_place_reserv"));
        }
         System.out.println(r.getNbre_place_reserv());
       //faire la somme 
       int somme=v.getNb_place_dispo()+r.getNbre_place_reserv();
            System.out.println(somme);
            //augmenter nbre de place dispo 
        String querry1="UPDATE `voyage` SET `nb_place_dispo` =? WHERE idVoyage=?";
        PreparedStatement pst1=cnx.prepareStatement(querry1);
        pst1.setInt(1,somme);
        pst1.setInt(2, cv.recuperer_id(v));
        pst1.executeUpdate();
                }
           // System.out.println(v.getNb_place_dispo());
        String query="DELETE FROM `reservationvoyage`WHERE id=? and idVoyage=?";
            PreparedStatement  pst= cnx.prepareStatement(query);
            pst.setInt(1,cc.reccuperer_id(c));
            pst.setInt(2,cv.recuperer_id(v));
            
            pst.executeUpdate();}
        catch (SQLException ex) {
            Logger.getLogger(CRUDReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    public int recuperer_id(Client c,Voyage v) {
        int id = -1;
        ArrayList<Reservation_voyage> reservations = new ArrayList<Reservation_voyage>();
        reservations=this.afficher_Reservations();
        for (int i = 0; i <reservations.size(); i++) {
            if((reservations.get(i).getIdVoyage()==v.getIdVoyage())&&(reservations.get(i).getId()==c.getId()))
            {
                id=reservations.get(i).getIdReservation();
                System.out.println(id);
                break;
            }
            
        }
        
        return id;
    }

    public boolean existe(Reservation_voyage r){
   /* if(this.recuperer_id(r)==-1)
    {return false;}
    else{
        return true;
    }*/
   return true;
    }
    
    @Override
    public ArrayList<Reservation_voyage> afficher_Reservations() {

        ArrayList<Reservation_voyage> list = new ArrayList<Reservation_voyage>();
        String querry = "SELECT * FROM reservationVoyage ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(querry);
            Reservation_voyage rv = new Reservation_voyage();
            while (rs.next()) {
                rv.setIdReservation(rs.getInt("idReservation"));
                rv.setIdVoyage(rs.getInt("idVoyage"));
                rv.setId(rs.getInt("id"));
                list.add(rv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
