/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.interfaces;

import esprit.pidev.entities.Client;
import java.util.ArrayList;



/**
 *
 * @author STA
 */
public interface IClient {
    
    public void ajouterClient(Client c);
    public void supprimerClient(Client c);
    public void updateClient(Client c);
    public ArrayList<Client> afficherClient();
    public boolean uniqueEmail(String mail);
}
