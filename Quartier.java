package com.gestion.logements;

import java.util.ArrayList;
import java.util.List;

public class Quartier {
    private String nom;
    private String codeQuartier;
    private Commune commune;
    private List<Logement> logements;
    
    public Quartier(String nom, String codeQuartier) {
        this.nom = nom;
        this.codeQuartier = codeQuartier;
        this.logements = new ArrayList<>();
    }
    
    public void ajouterLogement(Logement logement) {
        logements.add(logement);
        logement.setQuartier(this);
    }
    
    public List<Logement> getLogements() {
        return logements;
    }
    
    public List<Locataire> getHabitants() {
        List<Locataire> habitants = new ArrayList<>();
        for (Logement logement : logements) {
            if (logement.estLoue()) {
                habitants.add(logement.getLocataireActuel());
            }
        }
        return habitants;
    }
    
    public int getNombreHabitants() {
        return getHabitants().size();
    }
    
    public int getNombreLogements() {
        return logements.size();
    }
    
    public int getNombreLogementsLoues() {
        int count = 0;
        for (Logement logement : logements) {
            if (logement.estLoue()) {
                count++;
            }
        }
        return count;
    }
    
    // Getters et Setters
    public String getNom() { 
        return nom; 
    }
    
    public void setNom(String nom) { 
        this.nom = nom; 
    }
    
    public String getCodeQuartier() { 
        return codeQuartier; 
    }
    
    public void setCodeQuartier(String codeQuartier) { 
        this.codeQuartier = codeQuartier; 
    }
    
    public Commune getCommune() { 
        return commune; 
    }
    
    public void setCommune(Commune commune) { 
        this.commune = commune; 
    }
    
    public void afficherHabitants() {
        System.out.println("  Habitants du " + this + ":");
        List<Locataire> habitants = getHabitants();
        if (habitants.isEmpty()) {
            System.out.println("    Aucun habitant");
        } else {
            for (Locataire habitant : habitants) {
                System.out.println("    - " + habitant);
            }
        }
    }
    
    @Override
    public String toString() {
        return "Quartier " + nom + " (" + codeQuartier + ")";
    }
}