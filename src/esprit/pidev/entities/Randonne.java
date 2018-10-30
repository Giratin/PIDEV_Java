/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.entities;

import com.jfoenix.controls.JFXButton;
import esprit.pidev.gui.RandonneController;
import esprit.pidev.services.CRUDRandonne;
import javafx.collections.ObservableList;
/**
 *
 * @author STA
 */
public class Randonne {
    private long idRando;
    private int capacite;
    private int nbreClient;
    private String dateRando;
    private String DestinationRando;
    private int nbreBus;
    private float prixPersonne;
    private String programmeRando;
    private JFXButton update;
    private JFXButton delete;
    private String pathImg1;
    private String pathImg2;
    private String pathImg3;
    private String googleMaps;

    public Randonne() {
        //dateRando = new Date();
    }

    public Randonne(int capacite, String dateRando, String DestinationRando, int nbreBus, float prixPersonne, String programmeRando) {
        this.capacite = capacite;
        this.dateRando = dateRando;
        this.DestinationRando = DestinationRando;
        this.nbreBus = nbreBus;
        this.prixPersonne = prixPersonne;
        this.programmeRando = programmeRando;
    }

    public Randonne(int capacite, int nbreClient, String dateRando, String DestinationRando, int nbreBus, float prixPersonne, String programmeRando) {
        this.capacite = capacite;
        this.nbreClient = nbreClient;
        this.dateRando = dateRando;
        this.DestinationRando = DestinationRando;
        this.nbreBus = nbreBus;
        this.prixPersonne = prixPersonne;
        this.programmeRando = programmeRando;
    }

    public Randonne(long idRando, int capacite, int nbreClient, String dateRando, String DestinationRando, int nbreBus, float prixPersonne, String programmeRando) {
        this.idRando = idRando;
        this.capacite = capacite;
        this.nbreClient = nbreClient;
        this.dateRando = dateRando;
        this.DestinationRando = DestinationRando;
        this.nbreBus = nbreBus;
        this.prixPersonne = prixPersonne;
        this.programmeRando = programmeRando;
    }

    public Randonne(long idRando, int capacite, int nbreClient, String dateRando, String DestinationRando, int nbreBus, float prixPersonne, String programmeRando, JFXButton update, JFXButton delete) 
    {
        this.idRando = idRando;
        this.capacite = capacite;
        this.nbreClient = nbreClient;
        this.dateRando = dateRando;
        this.DestinationRando = DestinationRando;
        this.nbreBus = nbreBus;
        this.prixPersonne = prixPersonne;
        this.programmeRando = programmeRando;
        this.update = update;
        this.delete = delete;
       // System.out.println("entredd here!! ");
        
        update.setOnAction(e -> {
           Randonne R = new Randonne();
           int idUpdate = -1;

            ObservableList<Randonne> randonnes = RandonneController.tab_update.getSelectionModel().getSelectedItems();
            
            for(int i=0; i< randonnes.size(); i++)
            {
               if(randonnes.get(i).getUpdate() == update)
               {
                   R.setIdRando(randonnes.get(i).getIdRando());
                   R.setCapacite(randonnes.get(i).getCapacite());
                   R.setDestinationRando(randonnes.get(i).getDestinationRando());
                   R.setDateRando(randonnes.get(i).getDateRando());
                   R.setNbreBus(randonnes.get(i).getNbreBus());
                   R.setPrixPersonne(randonnes.get(i).getPrixPersonne());
                   R.setProgrammeRando(randonnes.get(i).getProgrammeRando());
                   idUpdate = (int) randonnes.get(i).getIdRando();
                   System.out.println("detination :: " + randonnes.get(i).getDestinationRando());
                   break;
               }
            }
           
           if(idUpdate != -1)
           {
                CRUDRandonne CR = new CRUDRandonne();
                CR.updateRando(R);
           }    
        });

        delete.setOnAction( e -> {
           int idDelete = -1;           
            for(int i=0; i< RandonneController.tableData.size(); i++)
           {
               if(RandonneController.tableData.get(i).getDelete()== delete)
               {
                   System.out.println("delete  --> id: "+RandonneController.tableData.get(i).getIdRando());
                   idDelete = (int) RandonneController.tableData.get(i).getIdRando() ;
                   break;
               }
           }
            
            if(idDelete != -1)
            {
                CRUDRandonne CR = new CRUDRandonne();
                CR.removeRando(idDelete);
            }
        });
    }

    public Randonne(int capacite, int nbreClient, String dateRando, String DestinationRando, float prixPersonne, String programmeRando, String pathImg1, String pathImg2, String pathImg3) {
        this.capacite = capacite;
        this.nbreClient = nbreClient;
        this.dateRando = dateRando;
        this.DestinationRando = DestinationRando;
        this.prixPersonne = prixPersonne;
        this.programmeRando = programmeRando;
        this.pathImg1 = pathImg1;
        this.pathImg2 = pathImg2;
        this.pathImg3 = pathImg3;
    }

    public Randonne(long idRando, int capacite, int nbreClient, String dateRando, String DestinationRando, float prixPersonne, String programmeRando, String pathImg1, String pathImg2, String pathImg3) {
        this.idRando = idRando;
        this.capacite = capacite;
        this.nbreClient = nbreClient;
        this.dateRando = dateRando;
        this.DestinationRando = DestinationRando;
        this.prixPersonne = prixPersonne;
        this.programmeRando = programmeRando;
        this.pathImg1 = pathImg1;
        this.pathImg2 = pathImg2;
        this.pathImg3 = pathImg3;
    }

    public Randonne(int capacite, String dateRando, String DestinationRando, float prixPersonne, String programmeRando, String googleMaps) {
        this.capacite = capacite;
        this.dateRando = dateRando;
        this.DestinationRando = DestinationRando;
        this.prixPersonne = prixPersonne;
        this.programmeRando = programmeRando;
        this.googleMaps = googleMaps;
    }

    public Randonne(long idRando,int capacite, int nbreClient, String dateRando, String DestinationRando, float prixPersonne, String programmeRando, String googleMaps) {
        this.idRando = idRando;
        this.capacite = capacite;
        this.nbreClient = nbreClient;
        this.dateRando = dateRando;
        this.DestinationRando = DestinationRando;
        this.prixPersonne = prixPersonne;
        this.programmeRando = programmeRando;
        this.googleMaps = googleMaps;
    }
    
    

    public String getGoogleMaps() {
        return googleMaps;
    }

    public void setGoogleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
    }
    
    public String getPathImg1() {
        return pathImg1;
    }

    public void setPathImg1(String pathImg1) {
        this.pathImg1 = pathImg1;
    }

    public String getPathImg2() {
        return pathImg2;
    }

    public void setPathImg2(String pathImg2) {
        this.pathImg2 = pathImg2;
    }

    public String getPathImg3() {
        return pathImg3;
    }

    public void setPathImg3(String pathImg3) {
        this.pathImg3 = pathImg3;
    }
    
    

    public JFXButton getDelete() {
        return delete;
    }

    public void setDelete(JFXButton delete) {
        this.delete = delete;
    }
    
    public JFXButton getUpdate() {
        return update;
    }

    public void setUpdate(JFXButton update) {
        this.update = update;
    } 
    
    public void setUpdate() {
        this.update = new JFXButton ("Update");
    }

    public long getIdRando() {
        return idRando;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getNbreClient() {
        return nbreClient;
    }

    public String getProgrammeRando() {
        return programmeRando;
    }


    public String getDateRando() {
        return dateRando;
    }

    public String getDestinationRando() {
        return DestinationRando;
    }

    public int getNbreBus() {
        return nbreBus;
    }

    public float getPrixPersonne() {
        return prixPersonne;
    }


    public void setIdRando(long idRando) {
        this.idRando = idRando;
    }
    
     public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setNbreClient(int nbreClient) {
        this.nbreClient = nbreClient;
    }

    public void setProgrammeRando(String programmeRando) {
        this.programmeRando = programmeRando;
    }

    public void setDateRando(String dateRando) {
        this.dateRando = dateRando;
    }

    public void setDestinationRando(String DestinationRando) {
        this.DestinationRando = DestinationRando;
    }

    public void setNbreBus(int nbreBus) {
        this.nbreBus = nbreBus;
    }

    public void setPrixPersonne(float prixPersonne) {
        this.prixPersonne = prixPersonne;
    }


    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Randonne)
        {
            Randonne R = (Randonne) obj;
            return this.DestinationRando.equals(R.getDestinationRando()) && this.prixPersonne == R.getPrixPersonne() 
                    && this.programmeRando.equals(R.getProgrammeRando()) && this.dateRando.equals(R.getDateRando());
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "idRando=" + idRando + ", capacite=" + capacite + ", nbreClient=" + nbreClient 
                + ", dateRando=" + dateRando + ", DestinationRando=" + DestinationRando 
                + ", nbreBus=" + nbreBus + ", prixPersonne=" + prixPersonne + ", programmeRando=" 
                + programmeRando+" Path1= " + pathImg1 + " Path2= " + pathImg2+ " Path3= " + pathImg3;
    }
    
    
    
    
    
    
    
}
