/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.test;

import esprit.pidev.entities.Client;
import esprit.pidev.entities.Randonne;
import esprit.pidev.entities.RandonneClient;
import esprit.pidev.entities.Reclamation;
import esprit.pidev.services.CRUDReservationRandonne;
import esprit.pidev.services.ReclamationService;
import java.util.ArrayList;

/**
 *
 * @author STA
 */
public class Main {

    public static void main(String[] args) {
        ReclamationService RS = new ReclamationService();
        
        ArrayList<Reclamation> claims = new ArrayList<>();
        claims= RS.displayClaim(1);
        
        for(int i=0; i< claims.size(); i++)
        {
            System.out.println(claims.get(i));
        }
    }

}
