
# Application Communes Bretonnes

Cette application Java/JavaFX permet de gérer et de consulter des informations relatives aux communes bretonnes. Elle illustre une architecture de type MVC (Modèle-Vue-Contrôleur), gère une base de données (MySQL) et propose une interface graphique réalisée avec JavaFX.

## 1. Fonctionnalités principales
- **Gestion des utilisateurs** (inscription, connexion, liste des comptes administrateurs, suppression, modification du compte).
- **Visualisation et exportation de données** sur :
  - Les communes (informations, départements, aéroports et gares associées).
  - Les années (inflation, données diverses).
  - Les statistiques (prix moyen, population, etc.).
- **Navigation** à travers plusieurs écrans (Accueil, À propos, Contact, Compte, etc.) grâce à une barre de navigation.
- **Interface d’administration** permettant la gestion des utilisateurs et la suppression ou la modification de certains éléments (gares, aéroports, communes, etc.), sous condition d’avoir le rôle `administrateur`.

## 2. Organisation du projet

```
SAE201
├───class
│   ├───application
│   │   ├───controller
│   │   ├───model
│   │   │   ├───dao
│   │   │   └───data
│   │   └───view
│   │       ├───admin
│   │       └───tableau
│   └───ressources
│       ├───img
│       └───utils
├───lib
└───src
    ├───application
    │   ├───controller
    │   ├───model
    │   │   ├───dao
    │   │   └───data
    │   └───view
    │       ├───admin
    │       └───tableau
    └───ressources
        ├───img
        └───utils
```

## 3. Utilisation et navigation
- **Au lancement**, l’appli affiche la fenêtre de Connexion si l’utilisateur n’est pas connecté.  
- **Connexion/Inscription** :  
  - Inscrire un nouvel utilisateur (rôle par défaut : `utilisateur`).  
  - Se connecter pour accéder aux autres fonctionnalités (Commune, Contact, Compte, etc.).  
- **Accueil** :  
  - Accès aux vues `Compte`, `Contact`, `À propos`, et `Commune`.  
- **Vue `Commune`** :  
  - Présente la liste de toutes les communes.  
  - Possibilité d’afficher des détails, exporter des données en CSV.  
- **Vue `Compte`** :  
  - Modifier/supprimer son compte. Se déconnecter.  
  - Si l’utilisateur est admin, possibilité d’afficher la liste des comptes (`Liste des comptes`).   
- **Vue `À propos`** :  
  - Présente des informations sur l’application.  

## 4. Auteurs
- Réalisé par :  
  - C. LEBRECH  
  - M. CRUNCHANT  
  - G. ZENSEN DA SILVA  
  - A.-M. ESKHADJIEV  
