package application.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NavBarre extends GridPane {
    private Button accueilButton;
    private Button compteButton;
    private Button carteButton;
    private Button departementButton;
    private Button communeButton;
    private Button aproposButton;
    private Button contactButton;
    //private Button deconnexion;


    public NavBarre() {
        initNavBarre(false, false);
    }


    public void initNavBarre(boolean estConnecte, boolean modifie) {
        this.getChildren().clear(); 
        this.setHgap(20); // Set horizontal gap between buttons

        // Initialize buttons
        this.accueilButton = new Button ("Accueil");
        this.compteButton = new Button("Compte");
        //this.carteButton = new Button("Carte");
        this.departementButton = new Button("Département");
        this.communeButton = new Button("Commune");
        this.aproposButton = new Button("À propos");
        this.contactButton = new Button("Contact");
        //this.deconnexion = new Button("Déconnexion");

        // Add buttons to GridPane
        this.add(accueilButton ,0,0);
        this.add(compteButton, 1, 0);
        //this.add(carteButton, 2, 0);
        this.add(departementButton, 2, 0);
        this.add(communeButton, 3, 0);
        this.add(aproposButton, 4, 0);
        this.add(contactButton, 5, 0);


        // Style the buttons and GridPane
        this.getStyleClass().add("nav-barre");
        this.accueilButton.getStyleClass().add("my-button-nav-barre");
        this.compteButton.getStyleClass().add("my-button-nav-barre");
        //this.carteButton.getStyleClass().add("my-button-nav-barre");
        this.departementButton.getStyleClass().add("my-button-nav-barre");
        this.communeButton.getStyleClass().add("my-button-nav-barre");
        this.aproposButton.getStyleClass().add("my-button-nav-barre");
        this.contactButton.getStyleClass().add("my-button-nav-barre");

        this.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
    }

    public Button getAccueilButton(){
        return this.accueilButton;
    }

    public Button getCompteButton() {
        return this.compteButton;
    }

    public Button getAProposButton(){
        return this.aproposButton;
    }

    public Button getContactButton(){
        return this.contactButton;
    }

    public Button getCommuneButton(){
        return this.communeButton;
    }

    public Button getDepartementButton(){
        return this.departementButton;
    }
}

    