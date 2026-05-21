package com.gestion.logements;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Contrat {
    private Date dateDebut;
    private Date dateFin;
    private double prixMensuel;
    private Locataire locataire;
    private Proprietaire proprietaire;
    private Logement logement;
    
    public Contrat(Date dateDebut, Date dateFin, double prixMensuel, 
                   Logement logement, Proprietaire proprietaire, Locataire locataire) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixMensuel = prixMensuel;
        this.logement = logement;
        this.proprietaire = proprietaire;
        this.locataire = locataire;
        
        // Lier le contrat aux objets
        logement.setContratActuel(this);
        proprietaire.ajouterContrat(this);
        locataire.signerContrat(this);
    }
    
    public boolean estActif() {
        Date maintenant = new Date();
        return maintenant.after(dateDebut) && maintenant.before(dateFin);
    }
    
    public boolean estFini() {
        Date maintenant = new Date();
        return maintenant.after(dateFin);
    }
    
    public boolean estAFutur() {
        Date maintenant = new Date();
        return maintenant.before(dateDebut);
    }
    
    public long getDureeEnMois() {
        long diff = dateFin.getTime() - dateDebut.getTime();
        long jours = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return jours / 30;
    }
    
    public double calculerMontantTotal() {
        return getDureeEnMois() * prixMensuel;
    }
    
    public String getStatut() {
        if (estActif()) return "ACTIF";
        if (estFini()) return "TERMINE";
        return "À VENIR";
    }
    
    // Getters et Setters
    public Date getDateDebut() { 
        return dateDebut; 
    }
    
    public void setDateDebut(Date dateDebut) { 
        this.dateDebut = dateDebut; 
    }
    
    public Date getDateFin() { 
        return dateFin; 
    }
    
    public void setDateFin(Date dateFin) { 
        this.dateFin = dateFin; 
    }
    
    public double getPrixMensuel() { 
        return prixMensuel; 
    }
    
    public void setPrixMensuel(double prixMensuel) { 
        this.prixMensuel = prixMensuel; 
    }
    
    public Locataire getLocataire() { 
        return locataire; 
    }
    
    public void setLocataire(Locataire locataire) { 
        this.locataire = locataire; 
    }
    
    public Proprietaire getProprietaire() { 
        return proprietaire; 
    }
    
    public void setProprietaire(Proprietaire proprietaire) { 
        this.proprietaire = proprietaire; 
    }
    
    public Logement getLogement() { 
        return logement; 
    }
    
    public void setLogement(Logement logement) { 
        this.logement = logement; 
    }
    
    public void afficherDetails() {
        System.out.println("\n--- DÉTAILS DU CONTRAT ---");
        System.out.println("Logement: " + logement.getAdresse());
        System.out.println("Propriétaire: " + proprietaire.getNomComplet());
        System.out.println("Locataire: " + locataire.getNomComplet());
        System.out.println("Période: du " + dateDebut + " au " + dateFin);
        System.out.println("Prix mensuel: " + prixMensuel + "€");
        System.out.println("Durée: " + getDureeEnMois() + " mois");
        System.out.println("Montant total: " + calculerMontantTotal() + "€");
        System.out.println("Statut: " + getStatut());
    }
    
    @Override
    public String toString() {
        return "Contrat " + logement.getAdresse() + " - " + locataire.getNomComplet() + 
               " - " + prixMensuel + "€/mois - " + getStatut();
    }
}