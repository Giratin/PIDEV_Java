/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.interfaces;

import esprit.pidev.entities.Randonne;
import java.util.ArrayList;

/**
 *
 * @author STA
 */
public interface IRandonne {
    
    public void createRando(Randonne r);
    public void removeRando(Randonne r);
    public void removeRando(int id);
    public void updateRando(Randonne r);   
    public ArrayList<Randonne> displayRando();
}
