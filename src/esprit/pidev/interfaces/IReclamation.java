/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.interfaces;

import esprit.pidev.entities.Reclamation;
import java.util.ArrayList;

/**
 *
 * @author STA
 */
public interface IReclamation {
    public void createClaim(Reclamation R);
    public ArrayList<Reclamation> displayClaim(int idUser);
    public ArrayList<Reclamation> displayClaims();
    public void deleteClaim(Reclamation R);
}
