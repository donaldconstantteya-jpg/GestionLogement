package com.gestion.logements;

import java.util.*;
import java.text.SimpleDateFormat;

public class GestionLogementsApp {

    private static List<Commune> communes = new ArrayList<>();
    private static List<Proprietaire> proprietaires = new ArrayList<>();
    private static List<Locataire> locataires = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choix;
        do {
            afficherMenu();
            choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 1 -> ajouterCommune();
                case 2 -> ajouterQuartier();
                case 3 -> ajouterProprietaire();
                case 4 -> ajouterLogement();
                case 5 -> ajouterLocataire();
                case 6 -> creerContrat();
                case 7 -> listerHabitantsCommune();
                case 8 -> listerHabitantsParQuartier();
                case 9 -> consulterLocatairesProprietaire();
                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 0);
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\n=== GESTION DES LOGEMENTS ===");
        System.out.println("1. Ajouter une commune");
        System.out.println("2. Ajouter un quartier (dans une commune)");
        System.out.println("3. Ajouter un propriétaire");
        System.out.println("4. Ajouter un logement (lié à un propriétaire et un quartier)");
        System.out.println("5. Ajouter un locataire");
        System.out.println("6. Créer un contrat de location");
        System.out.println("7. Lister tous les habitants d'une commune");
        System.out.println("8. Lister les habitants par quartier");
        System.out.println("9. Consulter les locataires d'un propriétaire");
        System.out.println("0. Quitter");
    }

    private static void ajouterCommune() {
        String nom = lireChaine("Nom de la commune : ");
        String cp = lireChaine("Code postal : ");
        communes.add(new Commune(nom, cp));
        System.out.println("Commune ajoutée.");
    }

    private static void ajouterQuartier() {
        if (communes.isEmpty()) { System.out.println("Aucune commune."); return; }
        Commune c = choisirCommune();
        if (c == null) return;
        String nom = lireChaine("Nom du quartier : ");
        String code = lireChaine("Code quartier : ");
        c.ajouterQuartier(new Quartier(nom, code));
        System.out.println("Quartier ajouté.");
    }

    private static void ajouterProprietaire() {
        String nom = lireChaine("Nom : ");
        String prenom = lireChaine("Prénom : ");
        String tel = lireChaine("Téléphone : ");
        String email = lireChaine("Email : ");
        proprietaires.add(new Proprietaire(nom, prenom, tel, email));
        System.out.println("Propriétaire ajouté.");
    }

    private static void ajouterLogement() {
        if (proprietaires.isEmpty()) { System.out.println("Aucun propriétaire."); return; }
        if (communes.isEmpty()) { System.out.println("Aucune commune."); return; }
        Proprietaire p = choisirProprietaire();
        if (p == null) return;
        Commune c = choisirCommune();
        if (c == null || c.getQuartiers().isEmpty()) { System.out.println("Cette commune n'a pas de quartier."); return; }
        Quartier q = choisirQuartier(c);
        if (q == null) return;
        String adresse = lireChaine("Adresse : ");
        double superficie = lireDouble("Superficie (m²) : ");
        int pieces = lireEntier("Nombre de pièces : ");
        Logement log = new Logement(adresse, superficie, pieces);
        p.ajouterLogement(log);
        q.ajouterLogement(log);
        System.out.println("Logement ajouté.");
    }

    private static void ajouterLocataire() {
        String nom = lireChaine("Nom : ");
        String prenom = lireChaine("Prénom : ");
        String tel = lireChaine("Téléphone : ");
        String email = lireChaine("Email : ");
        String id = lireChaine("Pièce d'identité : ");
        locataires.add(new Locataire(nom, prenom, tel, email, id));
        System.out.println("Locataire ajouté.");
    }

    private static void creerContrat() {
        if (locataires.isEmpty()) { System.out.println("Aucun locataire."); return; }
        // Récupérer tous les logements non loués
        List<Logement> logementsDispo = new ArrayList<>();
        for (Proprietaire p : proprietaires)
            for (Logement l : p.getLogements())
                if (!l.estLoue()) logementsDispo.add(l);
        if (logementsDispo.isEmpty()) { System.out.println("Aucun logement disponible."); return; }
        
        Locataire loc = choisirLocataire();
        if (loc == null) return;
        
        System.out.println("Logements disponibles :");
        for (int i = 0; i < logementsDispo.size(); i++)
            System.out.println((i+1) + ". " + logementsDispo.get(i));
        int idx = lireEntier("Votre choix : ") - 1;
        if (idx < 0 || idx >= logementsDispo.size()) return;
        Logement logement = logementsDispo.get(idx);
        Proprietaire prop = logement.getProprietaire();
        
        Date debut = lireDate("Date de début (yyyy-mm-dd) : ");
        Date fin = lireDate("Date de fin (yyyy-mm-dd) : ");
        double loyer = lireDouble("Loyer mensuel : ");
        
        new Contrat(debut, fin, loyer, logement, prop, loc);
        System.out.println("Contrat créé !");
    }

    private static void listerHabitantsCommune() {
        if (communes.isEmpty()) return;
        Commune c = choisirCommune();
        if (c != null) c.afficherHabitants();
    }

    private static void listerHabitantsParQuartier() {
        if (communes.isEmpty()) return;
        Commune c = choisirCommune();
        if (c != null) c.afficherHabitantsParQuartier();
    }

    private static void consulterLocatairesProprietaire() {
        if (proprietaires.isEmpty()) return;
        Proprietaire p = choisirProprietaire();
        if (p != null) p.afficherLocataires();
    }

    // --- Utilitaires ---
    private static Commune choisirCommune() {
        System.out.println("Choisissez une commune :");
        for (int i = 0; i < communes.size(); i++)
            System.out.println((i+1) + ". " + communes.get(i).getNom());
        int idx = lireEntier("Choix : ") - 1;
        return (idx >= 0 && idx < communes.size()) ? communes.get(idx) : null;
    }

    private static Quartier choisirQuartier(Commune c) {
        List<Quartier> qs = c.getQuartiers();
        for (int i = 0; i < qs.size(); i++)
            System.out.println((i+1) + ". " + qs.get(i).getNom());
        int idx = lireEntier("Choix : ") - 1;
        return (idx >= 0 && idx < qs.size()) ? qs.get(idx) : null;
    }

    private static Proprietaire choisirProprietaire() {
        for (int i = 0; i < proprietaires.size(); i++)
            System.out.println((i+1) + ". " + proprietaires.get(i).getNomComplet());
        int idx = lireEntier("Choix : ") - 1;
        return (idx >= 0 && idx < proprietaires.size()) ? proprietaires.get(idx) : null;
    }

    private static Locataire choisirLocataire() {
        for (int i = 0; i < locataires.size(); i++)
            System.out.println((i+1) + ". " + locataires.get(i).getNomComplet());
        int idx = lireEntier("Choix : ") - 1;
        return (idx >= 0 && idx < locataires.size()) ? locataires.get(idx) : null;
    }

    private static String lireChaine(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static int lireEntier(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Nombre attendu : ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private static double lireDouble(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextDouble()) {
            System.out.print("Nombre décimal attendu : ");
            scanner.next();
        }
        double val = scanner.nextDouble();
        scanner.nextLine();
        return val;
    }

    private static Date lireDate(String msg) {
        System.out.print(msg);
        String dateStr = scanner.nextLine();
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception e) {
            System.out.println("Format invalide, utilisation date actuelle.");
            return new Date();
        }
    }
}