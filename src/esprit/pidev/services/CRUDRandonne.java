/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.services;

import esprit.pidev.interfaces.IRandonne;
import esprit.pidev.entities.Randonne;
import esprit.pidev.utils.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author STA
 */
public class CRUDRandonne implements IRandonne{
    
    Connection con = ConnectDB.getInstance().getConnection();
            
    @Override
    public void createRando(Randonne r)
    {
        
        String query = "INSERT INTO `randonne`(`capacite`, `nbreClient`, `dateRando`, `destination`, `nbreBus`, `prixPersonne`, `programme`, `googlemaps`) VALUES (?,?,?,?,?,?,?,?)";
        
        CRUDRandonne cr = new CRUDRandonne();
        if(!cr.existe(r))
        {
            try {
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, r.getCapacite());
                pst.setInt(2, r.getNbreClient());
                pst.setString(3, r.getDateRando());
                pst.setString(4, r.getDestinationRando());
                pst.setInt(5, r.getNbreBus());
                pst.setFloat(6, r.getPrixPersonne());
                pst.setString(7, r.getProgrammeRando());
                pst.setString(8, r.getGoogleMaps());
                pst.executeUpdate();
                this.addImage(r);
            } catch (SQLException ex) {
                System.out.println("erreur d'ajout " +ex.getMessage());
            }
            System.out.println("ajouté avec succès");
        }
        else
        {
            System.out.println("Randonnée already exist! Leaving... " );
        }
    }
    
    @Override
    public void removeRando(int id)
    {
        String query = "DELETE FROM `randonne` WHERE `idRando`= ?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
            System.out.println("Remove success");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de supression :" + ex.getMessage());
        }
        
    }
    
    @Override
    public void updateRando(Randonne r)
    {
        String query = "UPDATE `randonne` SET `capacite`=?,`dateRando`=?,"
                     + "`destination`=?,`nbreBus`=?,`prixPersonne`=?,`programme`=? WHERE `idRando`= ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, r.getCapacite());
            ps.setString(2, r.getDateRando());
            ps.setString(3, r.getDestinationRando());
            ps.setInt(4, r.getNbreBus());
            ps.setFloat(5, r.getPrixPersonne());
            ps.setString(6, r.getProgrammeRando());
            ps.setLong(7, r.getIdRando());
            
            ps.executeUpdate();
            System.out.println("Update success");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour " + ex.getMessage());
        }
        
    }  
    
    @Override
    public ArrayList<Randonne> displayRando()
    {
        ArrayList<Randonne> randonnes = new ArrayList<>();
        String query = "Select * from randonne";
        
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
            {
                /*Randonne ron = new Randonne(
                                            rs.getLong(1), 
                                            rs.getInt(2),
                                            rs.getInt(3),
                                            rs.getString(4),
                                            rs.getString(5),
                                            rs.getInt(6), 
                                            rs.getFloat(7),
                                            rs.getString(8)
                                            );*/
                
                Randonne ron = new Randonne(
                                            rs.getLong(1),
                                            rs.getInt(2),
                                            rs.getInt(3),
                                            rs.getString(4),
                                            rs.getString(5),
                                            rs.getFloat(7),
                                            rs.getString(8),
                                            rs.getString(9)
                                        );
                randonnes.add(ron);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return randonnes;
    }
    

    @Override
    public void removeRando(Randonne r) {
         String query = "Delete from randonne where id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, r.getIdRando());
        } catch (SQLException ex) {
            System.out.println("Erreur lors de supression : " + ex.getMessage());
        }
    }
    
    public boolean existe(Randonne r)
    {
        CRUDRandonne cr = new CRUDRandonne();
        ArrayList<Randonne> randonnes = new ArrayList<>();
        randonnes = cr.displayRando();
        
        return randonnes.contains(r);
    }
    
    
   public void addImage(Randonne R)
   {
       CRUDReservationRandonne CRR = new CRUDReservationRandonne();
       Randonne R1 = new Randonne();
       R1 = CRR.existClientRando(R);
       
       if(R1.getIdRando() != 0)
       {
           String query = "INSERT INTO `imagesrando`(`imageUrl`, `idRando`) VALUES(?,?)";
           
           try {
               PreparedStatement pste = con.prepareStatement(query);
               pste.setString(1, R1.getPathImg1());
               pste.setLong(2, R1.getIdRando());
               pste.executeUpdate();
               System.out.println("image 1 is added");
           } catch (SQLException ex) {
               System.out.println("Error while saving image 1 to database:" + ex.getMessage());
           }
           
           try {
               PreparedStatement pste = con.prepareStatement(query);
               pste.setString(1, R1.getPathImg2());
               pste.setLong(2, R1.getIdRando());
               pste.executeUpdate();
               System.out.println("image 2 is added");
           } catch (SQLException ex) {
               System.out.println("Error while saving image2 to database:" + ex.getMessage());
           }
           
           try {
               PreparedStatement pste = con.prepareStatement(query);
               pste.setString(1, R1.getPathImg3());
               pste.setLong(2, R1.getIdRando());
               pste.executeUpdate();
               System.out.println("image 3 is added");
           } catch (SQLException ex) {
               System.out.println("Error while saving image 3 to database:" + ex.getMessage());
           }
       }
   }
   
   public ArrayList<Randonne> getAll()
   {
       ArrayList<Randonne> randonnes = new ArrayList<>();
       randonnes = this.displayRando();
       long idRando =0;
       
       for(int i=0; i<randonnes.size(); i++)
       {
           idRando = randonnes.get(i).getIdRando();
           try {
               String query = "Select * from imagesrando where idRando ="+idRando;
               
               //Statement statement = con.createStatement();
               PreparedStatement pste = con.prepareStatement(query);
               
               ResultSet result = pste.executeQuery(query);
               while(result.next())
               {
                   System.out.println("id img1 "+result.getInt("id"));
               }
           } catch (SQLException ex) {
               System.out.println("Error while catching idRando for images "+ex.getMessage());
           }
           break;
       }
       return randonnes;
   }
   
   
   public Randonne dispalyRandonne(Randonne R)
   {
       ArrayList<Randonne> randonnes = new ArrayList<>();
       randonnes = this.displayRando();
       long idRando =0;
       Randonne R1 = new Randonne();
       
       
       for(int i=0; i<randonnes.size(); i++)
       {
           
           if(randonnes.get(i).getDateRando().equals(R.getDateRando()) && randonnes.get(i).getDestinationRando().equals(R.getDestinationRando()))
           {
               idRando = randonnes.get(i).getIdRando();
               R1 = randonnes.get(i);
               System.out.println("id Rando "+idRando);
               try 
               {
                    String query = "Select * from imagesrando where idRando ="+idRando;
                    //Statement statement = con.createStatement();
                    PreparedStatement pste = con.prepareStatement(query);
                    ResultSet result = pste.executeQuery(query);
                    while(result.next())
                    {
                        //System.out.println("id img1 "+result.getInt("id"));
                        R1.setPathImg1(result.getString("imageUrl"));
                        break;
                    }
                    while(result.next())
                    {
                        //System.out.println("id img2 "+result.getInt("id"));
                        R1.setPathImg2(result.getString("imageUrl"));
                        break;
                    }
                    while(result.next())
                    {
                        //System.out.println("id img3 "+result.getInt("id"));
                        R1.setPathImg3(result.getString("imageUrl"));
                        break;
                    }
                    
                } catch (SQLException ex) {
                    System.out.println("Error while catching idRando for images "+ex.getMessage());
                }
                break;
            }
       }
       return R1;
   }
}
