/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.interfaces;

import esprit.pidev.entities.Admin;
import java.util.ArrayList;

/**
 *
 * @author STA
 */
public interface IAdmin {
    
    public void ajouterAdmin(Admin c);
    public void supprimerAdmin(Admin a1, Admin a2);
    public void updateAdmin(Admin c);
    public ArrayList<Admin> afficherAdmin();
    
}
