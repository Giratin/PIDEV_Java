/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.services;

import esprit.pidev.entities.Reclamation;
import esprit.pidev.interfaces.IReclamation;
import esprit.pidev.utils.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author STA
 */
public class ReclamationService implements IReclamation {

    
    Connection con = ConnectDB.getInstance().getConnection();
    
    @Override
    public void createClaim(Reclamation R) {
        
        
        String query = "INSERT INTO `reclamation`(`idUser`,`date`,`message`) VALUES (?,?,?)";
        
        try {
            PreparedStatement pste = con.prepareStatement(query);
            
            pste.setInt(1, R.getIdUtilisateur());
            pste.setString(2, R.getDateReclamation());
            pste.setString(3, R.getMessage());
            
            pste.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while creating claim "+ ex.getMessage());
        }
        
    }

    @Override
    public ArrayList<Reclamation> displayClaim(int idUser) 
    {
        ArrayList<Reclamation> reclamations = new ArrayList<>();
        String query = "SELECT * FROM `reclamation` where idUser="+idUser;
        
        try {
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);
            
            while(result.next())
            {
                Reclamation R = new Reclamation(
                        result.getInt("id"),
                        result.getInt("idUser"),
                        result.getString("date"),
                        result.getString("message")
                );
                reclamations.add(R);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error while extracting claims "+ ex.getMessage());
        }
        
        return reclamations;
    }

    @Override
    public void deleteClaim(Reclamation R) 
    {
        String query = "DELETE FROM `reclamation` where id=?";
        
        try {
            PreparedStatement pste = con.prepareStatement(query);
            pste.setInt(1, R.getId());
            pste.executeUpdate();
            
            System.out.println("deleted");
        } catch (SQLException ex) {
            System.out.println("Error while deleting claim "+ ex.getMessage());
        }
    }
    
    @Override
    public ArrayList<Reclamation> displayClaims()
    {
         ArrayList<Reclamation> reclamations = new ArrayList<>();
        String query = "SELECT * FROM `reclamation`";
        
        try {
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);
            
            while(result.next())
            {
                Reclamation R = new Reclamation(
                        result.getInt("id"),
                        result.getInt("idUser"),
                        result.getString("date"),
                        result.getString("message")
                );
                reclamations.add(R);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error while extracting claims "+ ex.getMessage());
        }
        
        return reclamations;
    }
    
}
