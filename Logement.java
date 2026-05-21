package com.gestion.logements;

public class Logement {
    private String adresse;
    private double superficie;
    private int nombrePieces;
    private Proprietaire proprietaire;
    private Quartier quartier;
    private Contrat contratActuel;
    
    public Logement(String adresse, double superficie, int nombrePieces) {
        this.adresse = adresse;
        this.superficie = superficie;
        this.nombrePieces = nombrePieces;
        this.proprietaire = null;
        this.quartier = null;
        this.contratActuel = null;
    }
    
    public boolean estLoue() {
        return contratActuel != null && contratActuel.estActif();
    }
    
    public Locataire getLocataireActuel() {
        if (estLoue()) {
            return contratActuel.getLocataire();
        }
        return null;
    }
    
    // Getters
    public String getAdresse() { 
        return adresse; 
    }
    
    public double getSuperficie() { 
        return superficie; 
    }
    
    public int getNombrePieces() { 
        return nombrePieces; 
    }
    
    public Proprietaire getProprietaire() { 
        return proprietaire; 
    }
    
    public Quartier getQuartier() { 
        return quartier; 
    }
    
    public Contrat getContratActuel() { 
        return contratActuel; 
    }
    
    // Setters
    public void setAdresse(String adresse) { 
        this.adresse = adresse; 
    }
    
    public void setSuperficie(double superficie) { 
        this.superficie = superficie; 
    }
    
    public void setNombrePieces(int nombrePieces) { 
        this.nombrePieces = nombrePieces; 
    }
    
    public void setProprietaire(Proprietaire proprietaire) { 
        this.proprietaire = proprietaire; 
    }
    
    public void setQuartier(Quartier quartier) { 
        this.quartier = quartier; 
    }
    
    public void setContratActuel(Contrat contratActuel) { 
        this.contratActuel = contratActuel; 
    }
    
    @Override
    public String toString() {
        String statut = estLoue() ? "Loué" : "Libre";
        return "Logement " + adresse + " - " + nombrePieces + " pièces - " + superficie + "m² - " + statut;
    }
}