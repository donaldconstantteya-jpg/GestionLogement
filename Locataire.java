package com.gestion.logements;

import java.util.ArrayList;
import java.util.List;

public class Locataire extends Individu {
    private String pieceIdentite;
    private List<Contrat> contrats;
    
    public Locataire(String nom, String prenom, String telephone, String email, String pieceIdentite) {
        super(nom, prenom, telephone, email);
        this.pieceIdentite = pieceIdentite;
        this.contrats = new ArrayList<>();
    }
    
    public void signerContrat(Contrat contrat) {
        contrats.add(contrat);
        contrat.setLocataire(this);
    }
    
    public List<Contrat> getContrats() {
        return contrats;
    }
    
    public String getPieceIdentite() {
        return pieceIdentite;
    }
    
    public void setPieceIdentite(String pieceIdentite) {
        this.pieceIdentite = pieceIdentite;
    }
    
    public Logement getLogementActuel() {
        for (Contrat contrat : contrats) {
            if (contrat.estActif()) {
                return contrat.getLogement();
            }
        }
        return null;
    }
    
    public boolean aUnLogement() {
        return getLogementActuel() != null;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Pièce d'identité: " + pieceIdentite;
    }
}