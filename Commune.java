package com.gestion.logements;

import java.util.ArrayList;
import java.util.List;

public class Commune {
    private String nom;
    private String codePostal;
    private List<Quartier> quartiers;
    
    public Commune(String nom, String codePostal) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.quartiers = new ArrayList<>();
    }
    
    public void ajouterQuartier(Quartier quartier) {
        quartiers.add(quartier);
        quartier.setCommune(this);
    }
    
    public List<Quartier> getQuartiers() {
        return quartiers;
    }
    
    public List<Locataire> getHabitants() {
        List<Locataire> habitants = new ArrayList<>();
        for (Quartier quartier : quartiers) {
            habitants.addAll(quartier.getHabitants());
        }
        return habitants;
    }
    
    public List<Locataire> getHabitantsParQuartier(Quartier quartier) {
        if (quartiers.contains(quartier)) {
            return quartier.getHabitants();
        }
        return new ArrayList<>();
    }
    
    public int getNombreHabitants() {
        return getHabitants().size();
    }
    
    public int getNombreQuartiers() {
        return quartiers.size();
    }
    
    public int getNombreLogements() {
        int total = 0;
        for (Quartier quartier : quartiers) {
            total += quartier.getNombreLogements();
        }
        return total;
    }
    
    public int getNombreLogementsLoues() {
        int total = 0;
        for (Quartier quartier : quartiers) {
            total += quartier.getNombreLogementsLoues();
        }
        return total;
    }
    
    public void afficherHabitants() {
        System.out.println("\n=== HABITANTS DE LA COMMUNE " + nom.toUpperCase() + " ===");
        List<Locataire> habitants = getHabitants();
        if (habitants.isEmpty()) {
            System.out.println("Aucun habitant recensé dans la commune.");
        } else {
            for (Locataire habitant : habitants) {
                System.out.println("  - " + habitant);
            }
        }
        System.out.println("Total: " + habitants.size() + " habitant(s)");
    }
    
    public void afficherHabitantsParQuartier() {
        System.out.println("\n=== HABITANTS PAR QUARTIER ===");
        for (Quartier quartier : quartiers) {
            quartier.afficherHabitants();
        }
    }
    
    public void afficherStatistiques() {
        System.out.println("\n=== STATISTIQUES DE LA COMMUNE " + nom.toUpperCase() + " ===");
        System.out.println("Code postal: " + codePostal);
        System.out.println("Nombre de quartiers: " + getNombreQuartiers());
        System.out.println("Nombre total de logements: " + getNombreLogements());
        System.out.println("Logements loués: " + getNombreLogementsLoues());
        System.out.println("Taux d'occupation: " + String.format("%.1f", (double)getNombreLogementsLoues() / getNombreLogements() * 100) + "%");
        System.out.println("Nombre d'habitants: " + getNombreHabitants());
    }
    
    // Getters et Setters
    public String getNom() { 
        return nom; 
    }
    
    public void setNom(String nom) { 
        this.nom = nom; 
    }
    
    public String getCodePostal() { 
        return codePostal; 
    }
    
    public void setCodePostal(String codePostal) { 
        this.codePostal = codePostal; 
    }
    
    @Override
    public String toString() {
        return "Commune de " + nom + " (" + codePostal + ") - " + quartiers.size() + " quartiers";
    }
}