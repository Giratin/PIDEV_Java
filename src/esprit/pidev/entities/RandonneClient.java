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
public class RandonneClient {
    private int id;
    private int idClient;
    private int idRandonne;

    public RandonneClient() {
    }
 
    public RandonneClient(int id, int idClient, int idRandonne) {
        this.id = id;
        this.idClient = idClient;
        this.idRandonne = idRandonne;
    }

    public RandonneClient(int idClient, int idRandonne) {
        this.idClient = idClient;
        this.idRandonne = idRandonne;
    }

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdRandonne() {
        return idRandonne;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdRandonne(int idRandonne) {
        this.idRandonne = idRandonne;
    }

    @Override
    public String toString() {
        return "RandonneClient{" + "id=" + id + ", idClient=" + idClient + ", idRandonne=" + idRandonne + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RandonneClient other = (RandonneClient) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idClient != other.idClient) {
            return false;
        }
        if (this.idRandonne != other.idRandonne) {
            return false;
        }
        return true;
    }
    
    
    
}
