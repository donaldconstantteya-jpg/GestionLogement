L2INFO
DONALD CONSTANT 23A675FS


CAHIER DES CHARGES
Système de gestion des logements dans les communes
1. Contexte du projet
La croissance démographique dans les communes nécessite une identification précise des habitants. Une bonne identification passe par un système de gestion des logements, de leurs propriétaires, des locataires et des contrats de location. La mairie doit pouvoir recenser les habitants par commune et par quartier, et les propriétaires doivent pouvoir consulter leurs locataires.

2. Objectifs du projet
Modéliser les entités suivantes : commune, quartier, logement, propriétaire, locataire, contrat de location.

Permettre la gestion (création, consultation) de ces entités via une application console.

Offrir à la commune la possibilité d’obtenir la liste des habitants (locataires) à l’échelle de la commune ou par quartier.

Offrir au propriétaire la possibilité de visualiser ses locataires actuels.

Assurer le respect des contraintes métier :

Un logement appartient à un seul propriétaire et à un seul quartier.

Un contrat lie un locataire et un propriétaire pour un logement donné, pour une période définie et un loyer mensuel.

Un logement ne peut être loué que via un contrat actif.

Un locataire ne peut occuper qu’un seul logement à la fois (via contrat actif).

3. Portée du logiciel
Fonctionnalités incluses :

Ajout d’une commune (nom, code postal).

Ajout d’un quartier dans une commune (nom, code quartier).

Ajout d’un propriétaire (nom, prénom, téléphone, email).

Ajout d’un logement (adresse, superficie, nombre de pièces, rattaché à un propriétaire et à un quartier).

Ajout d’un locataire (nom, prénom, téléphone, email, pièce d’identité).

Création d’un contrat de location (dates début/fin, loyer mensuel, choix d’un logement disponible et d’un locataire).

Consultation des habitants (locataires) d’une commune (tous quartiers confondus).

Consultation des habitants par quartier (pour une commune donnée).

Consultation des locataires liés à un propriétaire donné.

Fonctionnalités exclues (hors périmètre actuel) :

Modification ou suppression d’entités existantes.

Persistance des données (les données sont stockées en mémoire vive et perdues à l’arrêt du programme).

Interface graphique (l’application est en mode console).

Gestion des utilisateurs et des droits (authentification).

Génération de documents PDF (quittances, contrats).

4. Acteurs du système
Acteur	Description
Agent communal	Personne habilitée à gérer les données de la commune : ajout de communes, quartiers, logements, propriétaires, locataires, contrats, et consultation des listes d’habitants.
Propriétaire (lecture seule dans la version actuelle)	Peut consulter la liste des locataires de ses logements.
Locataire (indirect)	N’interagit pas directement avec le logiciel ; ses données sont enregistrées par l’agent communal.
Note : dans l’implémentation actuelle, tous les menus sont accessibles sans authentification ; l’utilisateur joue le rôle de l’agent communal.

5. Règles de gestion (contraintes métier)
ID	Règle
RG1	Un propriétaire peut posséder plusieurs logements.
RG2	Un logement appartient à un seul propriétaire.
RG3	Un logement est situé dans un seul quartier.
RG4	Un quartier appartient à une seule commune.
RG5	Un locataire peut signer plusieurs contrats (successifs) mais un seul contrat actif à la fois.
RG6	Un contrat est associé à un seul logement, un seul propriétaire et un seul locataire.
RG7	Un logement ne peut avoir qu’un seul contrat actif à un instant donné.
RG8	Un contrat actif a sa date de début ≤ date courante et sa date de fin ≥ date courante.
RG9	La commune peut lister les habitants (locataires ayant un contrat actif).
6. Spécifications fonctionnelles détaillées
6.1. Gestion des communes
UC-01 : Ajouter une commune (nom, code postal).

UC-02 : Sélectionner une commune pour les opérations suivantes.

6.2. Gestion des quartiers
UC-03 : Ajouter un quartier (nom, code) dans une commune existante.

6.3. Gestion des propriétaires
UC-04 : Ajouter un propriétaire (nom, prénom, téléphone, email).

6.4. Gestion des logements
UC-05 : Ajouter un logement (adresse, superficie, nombre de pièces, propriétaire, quartier).

6.5. Gestion des locataires
UC-06 : Ajouter un locataire (nom, prénom, téléphone, email, pièce d’identité).

6.6. Gestion des contrats
UC-07 : Créer un contrat de location (dates, loyer, logement disponible, locataire). Le contrat est automatiquement lié au propriétaire du logement.

6.7. Consultation des habitants
UC-08 : Lister tous les habitants (locataires actifs) d’une commune.

UC-09 : Lister les habitants par quartier (pour une commune).

6.8. Consultation spécifique propriétaire
UC-10 : Afficher la liste des locataires (contrats actifs) d’un propriétaire donné.

7. Spécifications techniques (non exhaustives)
Langage : Java (version 17 ou supérieure).

Environnement de développement : Eclipse (ou tout IDE Java).

Architecture : Orientée objet, avec respect des principes de modélisation UML.

Interface : Console (terminal) avec menus textuels et saisies clavier.

Gestion des dates : Utilisation de java.util.Date et SimpleDateFormat (format yyyy-mm-dd).

Structures de données : Listes en mémoire (ArrayList).

8. Contraintes de qualité
Maintenabilité : Code commenté, respect des conventions Java, séparation en classes cohérentes.

Évolutivité : Ajout possible d’une couche de persistance (fichier, base de données) sans réécriture complète.

Fiabilité : Gestion des erreurs de saisie (boucles de contrôle, validation des choix).

Tests : Les classes métier peuvent être testées unitairement avec JUnit.

9. Livrables attendus
Modèle UML (diagramme de classes, diagramme de cas d’utilisation).

Code source Java complet (8 classes + une classe principale avec menu).

Exécutable (simple à lancer depuis Eclipse ou en ligne de commande).

Cahier des charges (ce document).

10. Planning indicatif
Phase	Durée estimée
Analyse et conception (UML)	2 jours
Développement des classes métier	3 jours
Développement du menu interactif	2 jours
Tests et correction	1 jour
Rédaction de la documentation	1 jour
11. Glossaire
Commune : entité administrative regroupant des quartiers.

Quartier : subdivision d’une commune.

Logement : bien immobilier destiné à l’habitation.

Propriétaire : personne physique possédant un ou plusieurs logements.

Locataire : personne physique ayant signé un contrat de location.

Contrat de location : document définissant la période et le prix de location d’un logement entre un propriétaire et un locataire.

Contrat actif : contrat dont la période courante est comprise entre la date de début et la date de fin.

