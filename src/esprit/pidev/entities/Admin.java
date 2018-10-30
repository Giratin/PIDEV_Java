/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.entities;

/**
 *
 * @author STA
 */
public class Admin extends User implements Comparable<Admin> {
    private String type;

    public Admin() {
    }

    public Admin(int id, String nom, String prenom, String email, String password, String sexe, String type) {
        super(id, nom, prenom, email, password, sexe);
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public boolean equals(Object o)
    {
        if(o instanceof Admin)
        {
            Admin a = (Admin) o;
            return (a.getId() == this.getId() && this.getEmail().equals(a.getEmail()) 
                    && this.getType().equals(a.getType()));
        }
        
        return false;
    }

    @Override
    public String toString() {
        return super.toString() +  ", type=" + type ;
    }
    
    @Override
    public int compareTo(Admin e)  
    {
        return Integer.compare(this.getId(), e.getId());
    }
    
    
}
