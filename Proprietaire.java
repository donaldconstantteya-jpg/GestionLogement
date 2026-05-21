package com.gestion.logements;

import java.util.ArrayList;
import java.util.List;

public class Proprietaire extends Individu {
    private List<Logement> logements;
    private List<Contrat> contrats;
    
    public Proprietaire(String nom, String prenom, String telephone, String email) {
        super(nom, prenom, telephone, email);
        this.logements = new ArrayList<>();
        this.contrats = new ArrayList<>();
    }
    
    public void ajouterLogement(Logement logement) {
        logements.add(logement);
        logement.setProprietaire(this);
    }
    
    public List<Logement> getLogements() {
        return logements;
    }
    
    public void ajouterContrat(Contrat contrat) {
        contrats.add(contrat);
    }
    
    public List<Contrat> getContrats() {
        return contrats;
    }
    
    public List<Locataire> getLocataires() {
        List<Locataire> locataires = new ArrayList<>();
        for (Contrat contrat : contrats) {
            if (contrat.estActif()) {
                locataires.add(contrat.getLocataire());
            }
        }
        return locataires;
    }
    
    public void afficherLocataires() {
        System.out.println("Locataires du propriétaire " + getNomComplet() + ":");
        List<Locataire> locataires = getLocataires();
        if (locataires.isEmpty()) {
            System.out.println("  Aucun locataire pour le moment");
        } else {
            for (Locataire locataire : locataires) {
                System.out.println("  - " + locataire);
            }
        }
    }
    
    @Override
    public String toString() {
        return "Propriétaire: " + getNomComplet() + " - " + email;
    }
}