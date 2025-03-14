package application.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ressources.utils.Constants;

public class Inscription extends Pane {
    private Stage primaryStage;
    private BackgroundImage background;
    private Image icon;
    private Button inscriptionButton, connexionButton;
    private TextField emailField;
    private PasswordField passwordField;
    private Label errorLabel, emailLabel, passwordLabel;

    public Inscription(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.icon = new Image(Constants.ICON);
        this.background = new BackgroundImage(new Image(Constants.INSCRIPTION), 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, false, true));
        initUIComponents();
    }

    private void initUIComponents() {
        this.inscriptionButton = new Button("S'inscrire");
        this.connexionButton = new Button("Déjà inscrit ? Connecte toi !");
        this.emailField = new TextField();
        this.passwordField = new PasswordField();
        this.emailLabel = new Label("Email : ");
        this.passwordLabel = new Label("Mot de passe : ");
        this.errorLabel = new Label();

    }

    

    public Scene creerSceneInscription() {
        this.emailField.clear();
        this.passwordField.clear();
        Pane root = creerRootInscription();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT); 
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.getIcons().add(this.icon);
        this.primaryStage.setTitle(Constants.NAME);
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        return scene;
    }

    public Pane creerRootInscription() {
        this.emailField.clear();
        this.passwordField.clear();
        Pane root = new Pane();
        root.setBackground(new Background(this.background));
        configurerComposants(root);
        return root;
    }

    private void configurerComposants(Pane root) {
        configurerBouton(inscriptionButton, 500, 480, 260, 50, "my-button", root);
        configurerBouton(connexionButton, 500, 430, 260, 50, "my-button-label", root);
        configurerLabel(emailLabel, 500, 275, 260, 20, "my-label-user-con", root);
        configurerLabel(passwordLabel, 500, 360, 260, 20, "my-label-user-con", root);
        configurerTextField(emailField, 500, 300, 260, 45, "gwendal-lebreton@example.com", "my-field-user-con", root);
        configurerTextField(passwordField, 500, 385, 260, 45, "Mot de passe", "my-field-user-con", root);
        configurerLabel(errorLabel, 500, 230, 500, 50, "my-label-error", root);
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

    private void configurerLabel(Label label, int x, int y, int width, int height, String style, Pane root) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefSize(width, height);
        label.getStyleClass().add(style);
        root.getChildren().add(label);
    }

    public Button getInscriptionButton() {
        return inscriptionButton;
    }

    public Button getConnexionButton() {
        return connexionButton;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String error) {
        this.errorLabel.setText(error);
    }
}
