package application.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ressources.utils.Constants;

public class Connexion extends Pane{

    private Stage primaryStage;
    private Image icon, connexion;
    private BackgroundImage backgroundImg;
    private Button inscriptionButton, connexionButton, motDePasseOublieButton;
    private Label emailLabel, passwordLabel;
    private TextField emailField;
    private PasswordField passwordField;

    public Connexion(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.icon = new Image(Constants.ICON);
        this.connexion = new Image(Constants.CONNEXION);
        this.backgroundImg = new BackgroundImage(this.connexion, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        initUIComponents();
    }

    // Initialise les composants
    private void initUIComponents() {
        this.inscriptionButton = new Button("Inscrivez-vous !");
        this.connexionButton = new Button("Se Connecter");
        this.motDePasseOublieButton = new Button("Mot de passe oublié ?");
        this.emailLabel = new Label("Email :");
        this.emailField = new TextField();
        this.passwordLabel = new Label("Mot de passe :");
        this.passwordField = new PasswordField();

        // Ajouter un écouteur pour la validation de l'email
        this.connexionButton.setOnAction(event -> {
            if (!isValidEmail(emailField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de validation");
                alert.setHeaderText("Email invalide");
                alert.setContentText("Veuillez entrer une adresse email valide.");
                alert.showAndWait();
            } else {
                // Logique de connexion
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    public Scene creerSceneConnexion() {
        this.emailField.clear();
        this.passwordField.clear();
        Pane root = creerRootConnexion();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setTitle(Constants.NAME);
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        return scene;
    }

    public Pane creerRootConnexion() {
        this.emailField.clear();
        this.passwordField.clear();
        Pane root = new Pane();
        root.setBackground(new Background(this.backgroundImg));
        configurerComposants(root);
        return root;
    }

    // Personnalisation des composants
    private void configurerComposants(Pane root) {
        configurerBouton(inscriptionButton, 700, 480, 260, 40, "my-button", root); // my-button --> fichier application.css
        configurerBouton(connexionButton, 500, 420, 260, 50, "my-button", root);
        configurerBouton(motDePasseOublieButton, 500, 390, 260, 30, "my-button-label", root);
        configurerLabel(emailLabel, 500, 245, "my-label-user-con", root);
        configurerLabel(passwordLabel, 500, 320, "my-label-user-con", root);
        configurerTextField(emailField, 500, 270, 260, 45, "gwendal-lebreton@example.com", "my-field-user-con", root);
        configurerTextField(passwordField, 500, 340, 260, 45, "Mot de passe", "my-field-user-con", root);
    }

    // Méthodes de configuration de composants
    private void configurerBouton(Button bouton, int x, int y, int largeur, int hauteur, String styleClass, Pane root) {
        bouton.setLayoutX(x);
        bouton.setLayoutY(y);
        bouton.setPrefWidth(largeur);
        bouton.setPrefHeight(hauteur);
        bouton.getStyleClass().add(styleClass);
        root.getChildren().add(bouton);
    }

    private void configurerLabel(Label label, int x, int y, String styleClass, Pane root) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.getStyleClass().add(styleClass);
        root.getChildren().add(label);
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

    public Button getConnexionButton() {
        return connexionButton;
    }

    public Button getInscriptionButton() {
        return inscriptionButton;
    }

    public Button getMotDePasseOublieButton() {
        return motDePasseOublieButton;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }
}
