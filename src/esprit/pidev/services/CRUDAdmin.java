/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.services;

import esprit.pidev.interfaces.IAdmin;
import esprit.pidev.entities.Admin;
import esprit.pidev.entities.Confirmation;
import esprit.pidev.utils.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author STA
 */
public class CRUDAdmin implements IAdmin{

    Connection con = ConnectDB.getInstance().getConnection();
    
    @Override
    public void ajouterAdmin(Admin a) {
        
        Confirmation confirme = new Confirmation();
        
        String query =" INSERT INTO admins (name,lname,type,email,password) VALUES (?,?,?,?)";
        
        if(confirme.validateEmail(a.getEmail()))
        {
            try {
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, a.getPrenom());
                pst.setString(2, a.getNom());
                pst.setString(3, a.getType());
                pst.setString(4, a.getEmail());
                pst.setString(5, a.getPassword());
                pst.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("Unable to add a new admin---"+ex.getMessage());       
            }
        }
        
    }

    @Override
    public void supprimerAdmin(Admin a1, Admin a2) {
        
        ArrayList<Admin> admins = new ArrayList<>();
        admins = this.afficherAdmin();
        
        for(int i =0; i< admins.size(); i++)
        {
            if(admins.get(i).getType().equals("super") && admins.get(i).equals(a1))
            {
                if(!a1.equals(a2))
                {
                    System.out.println("You are a super admin, you're allowed to do such an action");
                    break;
                }
                else{
                    System.out.println("Sorry you cannot delete yourself!");
                    return;
                }
            }
            else{
                System.out.println("You are a normal admin, you're not allowed to do such an action!!");
                return;
            }   
        }
        
        String query="delete from admins where id=?";
        System.out.println("passed to query to delete admin");
        try {
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setInt(1, a1.getId());
            pst.executeUpdate();
            
            System.out.println("Normal admin deleted with success");
            
        } catch (SQLException ex) {
            System.out.println("Unable to delete admin" + ex.getMessage());
        }
        
    }

    @Override
    public void updateAdmin(Admin c) {
        String query="update users set name=? , lname=? , password=? , sexe =?"
                + " , email =? where id= ?";
        Confirmation verif = new Confirmation();
        if(this.uniqueEmail(c.getEmail()) && verif.validateEmail(c.getEmail()))
        {
            PreparedStatement pst ;
            try{
                pst = con.prepareStatement(query);
                pst.setString(1, c.getPrenom());
                pst.setString(2, c.getNom());
                pst.setString(3, c.getPassword());
                pst.setString(5, c.getEmail());
                pst.setInt(6, c.getId());
                pst.executeUpdate();
            }catch(SQLException ex)
            {
                System.out.println(ex.getMessage()+"erreur lors de la mise a jour des donnees");
            }
        }
        
    }

    @Override
    public ArrayList<Admin> afficherAdmin() {
        ArrayList<Admin> admins = new ArrayList<>();
        String query = "select * from admins";
        
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("name"));
                a.setPrenom(rs.getString("lname"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                a.setType(rs.getString("type"));
                
                admins.add(a);
                
            }
        } catch (SQLException ex) {
            System.out.println("An error has been accured while retrieving from admin table "+ex.getMessage());
        }
        Collections.sort(admins);
        return admins;
    }
    
    public boolean uniqueEmail(String mail)
    {
        String query="Select email from admins";
        ArrayList<String> email = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                email.add(rs.getString("email"));  
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        if(email.size()>0)
            return !email.contains(mail);
        
        return true;
    }
    
}
