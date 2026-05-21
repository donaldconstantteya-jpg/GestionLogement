package com.gestion.logements;

public abstract class Individu {
    protected String nom;
    protected String prenom;
    protected String telephone;
    protected String email;
    
    public Individu(String nom, String prenom, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
    }
    
    // Getters
    public String getNom() { 
        return nom; 
    }
    
    public String getPrenom() { 
        return prenom; 
    }
    
    public String getTelephone() { 
        return telephone; 
    }
    
    public String getEmail() { 
        return email; 
    }
    
    // Setters
    public void setNom(String nom) { 
        this.nom = nom; 
    }
    
    public void setPrenom(String prenom) { 
        this.prenom = prenom; 
    }
    
    public void setTelephone(String telephone) { 
        this.telephone = telephone; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    @Override
    public String toString() {
        return getNomComplet() + " - Tél: " + telephone;
    }
}