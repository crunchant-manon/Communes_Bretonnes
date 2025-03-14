package application.view.admin;

import application.controller.AppController;
import application.model.data.UtilisateurFileAccess;
import application.view.NavBarre;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ressources.utils.Constants;
import java.util.ArrayList;

public class AdministrateurCompteListe {

    private Stage primaryStage;
    private Image icon, listedescomptes;
    private BackgroundImage backgroundImg;
    private NavBarre navBarre;
    private AdministrateurTableComptes administrateurTableComptes;
    private Button ajouterButton, supprimerButton;
    private TextField emailField, passwordField;
    private Label errorLabel;

    public void init(AppController appController, UtilisateurFileAccess user) {
        if (user != null) {
            this.administrateurTableComptes = new AdministrateurTableComptes(user.getUtilisateurs(), appController);
        } else {
            this.administrateurTableComptes = new AdministrateurTableComptes(new ArrayList<>(), appController);
        }
    }

    public void initUIComponents() {
        this.ajouterButton = new Button("Ajouter à la liste");
        this.supprimerButton = new Button ("Supprimer de la liste");
        this.emailField = new TextField();
        this.passwordField = new TextField();
        this.errorLabel = new Label();
    }

    public AdministrateurCompteListe(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.icon = new Image(Constants.ICON);
        this.listedescomptes = new Image(Constants.LISTEDESCOMPTES);
        this.backgroundImg = new BackgroundImage(this.listedescomptes, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        this.navBarre = new NavBarre();
        initUIComponents();
    }

    public Scene creerSceneCompte() {
        Pane root = creerRootCompte();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT); 
        scene.getStylesheets().add(getClass().getResource("../../../ressources/application.css").toExternalForm());
        this.primaryStage.getIcons().add(this.icon);
        this.primaryStage.setTitle(Constants.NAME);
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        return scene;
    }

    public Pane creerRootCompte() {
        this.emailField.clear();
        this.passwordField.clear();
        Pane root = new Pane();
        root.setBackground(new Background(this.backgroundImg));
        configurerComposants(root);
        return root;
    }

    private void configurerComposants(Pane root) {
        if (this.navBarre != null) {
            root.getChildren().add(this.navBarre);
        }
        if (administrateurTableComptes != null) {
            configurerTable(administrateurTableComptes, 600, 250, "table-view", root);
        }
        if (ajouterButton != null) {
            configurerBouton(ajouterButton, 200, 450, 200, 30, "my-button", root);
        }
        if (supprimerButton != null){
            configurerBouton(supprimerButton, 200, 500, 200, 30, "my-button-supp", root);
        }
        if (emailField != null) {
            configurerTextField(emailField, 175, 305, 260, 45, "Identifiant", "my-field-user-con", root);
        }
        if (passwordField != null) {
            configurerTextField(passwordField, 175, 380, 260, 45, "Mot de passe", "my-field-user-con", root);
        }
        if (errorLabel != null) {
            configurerLabel(errorLabel, 200, 550, 300, 45, "my-label-error", root);
        }
    }

    private void configurerTable(AdministrateurTableComptes t, int x, int y, String style, Pane root) {
        t.setLayoutX(x);
        t.setLayoutY(y);
        t.getStyleClass().add(style);
        root.getChildren().add(t);
    }

    private void configurerLabel(Label label, int x, int y, int width, int height, String styleClass, Pane root) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        label.getStyleClass().add(styleClass);
        root.getChildren().add(label);
    }

    private void configurerBouton(Button button, int x, int y, int width, int height, String styleClass, Pane root) {
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.getStyleClass().add(styleClass);
        root.getChildren().add(button);
    }

    private void configurerTextField(TextField champ, int x, int y, int largeur, int hauteur, String promptText, String styleClass, Pane root) {
        champ.setLayoutX(x);
        champ.setLayoutY(y);
        champ.setPrefWidth(largeur);
        champ.setPrefHeight(hauteur);
        champ.setPromptText(promptText);
        champ.getStyleClass().add(styleClass);
        root.getChildren().add(champ);
    }

    public Label getErrorLabel() {
        return this.errorLabel;
    }

    public NavBarre getNavBarre() {
        return this.navBarre;
    }

    public void setNavBarre(NavBarre navBarre) {
        this.navBarre = navBarre;
    }

    public Button getAjouterButton() {
        return this.ajouterButton;
    }

    public Button getSupprimerButton(){
        return this.supprimerButton;
    }

    public TextField getLoginField() {
        return this.emailField;
    }

    public TextField getPasswordField() {
        return this.passwordField;
    }
}
