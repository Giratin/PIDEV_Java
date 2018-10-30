/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.services;

import esprit.pidev.entities.Client;
import esprit.pidev.entities.Randonne;
import esprit.pidev.entities.RandonneClient;
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
public class CRUDReservationRandonne {
    Connection con = ConnectDB.getInstance().getConnection();
    
    public void ajouterClientRandonne(int id, Client c)
    {
        CRUDRandonne vr = new CRUDRandonne();
        ArrayList<Randonne> randonnes= new ArrayList<>();
        randonnes = vr.displayRando();
        
        CRUDClient cli = new CRUDClient();
        ArrayList<Client> clients= new ArrayList<>();
        clients = cli.afficherClient();
        
        Randonne r = new Randonne();
        Client cc =new Client();
        
        boolean cl = false;
        boolean rd = false;
        boolean exist = false;
        
        ArrayList<RandonneClient> list = new ArrayList<>();
        CRUDReservationRandonne crc = new CRUDReservationRandonne();
        
        list = crc.displayListClientRando();
        
        for(int i=0; i<randonnes.size(); i++)
        {
            if(randonnes.get(i).getIdRando() == id)
            {
                System.out.println("randonnée found!!");
                r.setIdRando(randonnes.get(i).getIdRando());
                r.setCapacite(randonnes.get(i).getCapacite());
                r.setNbreClient(randonnes.get(i).getNbreClient());
                r.setDateRando(randonnes.get(i).getDateRando());
                r.setDestinationRando(randonnes.get(i).getDestinationRando());
                r.setNbreBus(randonnes.get(i).getNbreBus());
                r.setPrixPersonne(randonnes.get(i).getPrixPersonne());
                r.setProgrammeRando(randonnes.get(i).getProgrammeRando());
                rd = true;
                break;
            }
            else{
                System.out.println("randonnée not found!");
            }
        }
        
        for(int i=0; i<clients.size(); i++)
        {
            if(clients.get(i).getEmail().equals(c.getEmail()))
            {
                System.out.println("user found!!");
                cc.setId(clients.get(i).getId());
                cl = true;
                break;
            }
            else{
                System.out.println("user not found!");
            }
        }
        System.out.println("cc: "+cc.getId() + " email: "+c.getEmail());
        System.out.println(r.getNbreClient()+ " "+ r.getCapacite());
        
        for(int i=0; i<list.size(); i++)
        {
            System.out.println(list.get(i));
            if(list.get(i).getIdClient() == cc.getId() && list.get(i).getIdRandonne() == r.getIdRando())
            {
                exist = true;
                break;
            }
        }
        
        if(exist)
            System.out.println("Client exist déjà !! ");
        
        String query= "INSERT INTO reservationRandonne (`id_randonne`, `id_client`) VALUES (?,?)";
       
        if(cl && rd && !exist)
        {
            if(r.getNbreClient() < r.getCapacite())
            {
                try {
                        System.out.println("r.getNbreClient() < r.getCapacite()" + (r.getNbreClient() < r.getCapacite()));
                        PreparedStatement pst = con.prepareStatement(query);
                        pst.setLong(1, r.getIdRando());
                        pst.setInt(2, cc.getId());

                        String query2 = "UPDATE `randonne` SET `nbreClient` = `nbreClient` + 1 WHERE `idRando`="+r.getIdRando();
                        try{
                            PreparedStatement st = con.prepareStatement(query2);
                            st.executeUpdate(); 
                            System.out.println("nbre client sucess update");
                        }
                        catch(SQLException e){
                            System.out.println("erreur "+e.getMessage());
                        }
                        pst.executeUpdate();
                        System.out.println("Client "+c.getEmail() +" added with success to the travel " +r.getDestinationRando());
                } catch (SQLException ex) {
                    System.out.println("unable to add client to a trip" + ex.getMessage());       
                }
            }
            else
                System.out.println("Capacité max!!");
        }
    }
    
    public ArrayList<RandonneClient> displayListClientRando()
    {
        ArrayList<RandonneClient> list =  new ArrayList<>();
        
        String query = "SELECT * FROM `reservationrandonne`";
        
         try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
            {
                RandonneClient ron = new RandonneClient(
                        rs.getInt(1),
                        rs.getInt(3),
                        rs.getInt(2)
                );
                list.add(ron);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return list;
    }
    
    
    public ArrayList<Randonne> DisplayForClient(Client C)
    {
        ArrayList<RandonneClient> list =  new ArrayList<>();
        list = this.displayListClientRando();
        ArrayList<Randonne> listClient =  new ArrayList<>();
         
        for(int i=0; i< list.size(); i++)
        {
            String queryCl = "SELECT * FROM `randonne` where idRando="+list.get(i).getIdRandonne();
            try {
               Statement statement = con.createStatement();
               ResultSet rs = statement.executeQuery(queryCl);
               while(rs.next())
               {
                   Randonne R = new Randonne(
                            rs.getLong(1), 
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6), 
                            rs.getFloat(7),
                            rs.getString(8)
                   );
                   
                   listClient.add(R);
               }
           } catch (SQLException ex) {
               System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
           }
            
        }
        
        
        
        
        
       

        return listClient;
    }
    
    public void removeClientRando(Client c, Randonne r)
    {
        
        CRUDReservationRandonne CRC = new CRUDReservationRandonne();
        RandonneClient RC = new RandonneClient(); 
        RC = CRC.clientAffecteRandonne(c, r); 
        Randonne R1 = new Randonne();
        R1 = CRC.existClientRando(r);
        
        if(RC.getId() != 0)
        {
            String query  ="DELETE FROM `reservationrandonne` where id =" + RC.getId();
                try {
                    PreparedStatement pste = con.prepareStatement(query);
                    pste.executeUpdate();
                    System.out.println("Client désaffecté avec succès de la randonné");
                   
                } catch (SQLException ex) {
                    System.out.println("Erreur lors de supression " + ex.getMessage());
                }
                
            String query2 ="UPDATE `randonne` SET `nbreClient`= `nbreClient`-1 Where `idRando`=" + R1.getIdRando();  
                try {
                    PreparedStatement pst = con.prepareStatement(query2);
                    pst.executeUpdate();
                    System.out.println("Décrémentation du nombre des clients effectué avec succès");

                } catch (SQLException ex) {
                    System.out.println("Erreur lors de la décrémentation " + ex.getMessage());
                }
            //System.out.println("ID Rando: " + RC.getId());
        }
        
        else{
            System.out.println("Ce Client n'est pas affecté à la randonnée d'ID: ");
        }
    }
    
    public Client existClientRando(Client c)
    {
        CRUDClient CrClient = new CRUDClient();
        ArrayList<Client> clients = new ArrayList<>();
        clients = CrClient.afficherClient();
        
        Client C1 = new Client();
        
        for(int i=0; i<clients.size(); i++)
        {
            if(clients.get(i).getEmail().equals(c.getEmail()))
            {
                C1.setId(clients.get(i).getId());
                C1.setEmail(clients.get(i).getEmail());
                break;
            }
        }
        return C1;
    }
    
    public Randonne existClientRando(Randonne r)
    {
        CRUDRandonne CrRando = new CRUDRandonne();
        ArrayList<Randonne> randonnes = new ArrayList<>();
        randonnes = CrRando.displayRando();
        
        Randonne R1 = new Randonne();
        R1 = r;
        
        for(int i =0; i<randonnes.size(); i++)
        {
            if(randonnes.get(i).equals(r))
            {
                R1.setIdRando(randonnes.get(i).getIdRando());
                R1.setDestinationRando(randonnes.get(i).getDestinationRando());
                R1.setCapacite(randonnes.get(i).getCapacite());
                R1.setNbreClient(randonnes.get(i).getNbreClient());
                
                break;
            }
        }
        
        return R1;
    }
    
    public RandonneClient clientAffecteRandonne(Client c, Randonne r)
    {
        CRUDReservationRandonne existe = new CRUDReservationRandonne();
        
        Client C1 = new Client();
        Randonne R1 = new Randonne();
        
        RandonneClient RC = new RandonneClient();
        
        C1 = existe.existClientRando(c);
        R1 = existe.existClientRando(r);
        
        boolean clientExiste = false;
        boolean RandonExiste = false;
        
        ArrayList<RandonneClient> list =  new ArrayList<>();
        list = existe.displayListClientRando();
        
        if(C1.getId() == 0)
            clientExiste = false;
        else
            clientExiste = true;
        
        if(R1.getIdRando() == 0)
            RandonExiste = false;
        else
            RandonExiste = true;
        
        if(RandonExiste && clientExiste)
        {
            for(int i =0; i< list.size(); i++)
            {
                if(list.get(i).getIdClient() == C1.getId() && list.get(i).getIdRandonne() == R1.getIdRando())
                {
                    RC.setId(list.get(i).getId());
                    RC.setIdClient(list.get(i).getIdClient());
                    RC.setIdRandonne(list.get(i).getIdRandonne());
                    break;
                }
            }
        }
        return RC;
    }  
}
