package application.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

public class Compte extends Pane {
    private Stage primaryStage;
    private Image icon, backgroundImage;
    private BackgroundImage background;
    private Button modificationButton, supprimerButton, deconnexionButton, listeCompteButton;
    private Label roleLabel, emailLabel, passwordLabel, errorLabel;
    private TextField emailField;
    private PasswordField passwordField;
    private NavBarre navBarre;

    public Compte(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.icon = new Image(Constants.ICON);
        this.backgroundImage = new Image(Constants.COMPTE);
        this.background = new BackgroundImage(this.backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        this.navBarre = new NavBarre();

        initUIComponents("role", "email", "password", false);
    }

    private void initUIComponents(String role, String email, String password, boolean estConnecte) {
        this.modificationButton = new Button("Modifier");
        this.supprimerButton = new Button("Supprimer");
        this.deconnexionButton = new Button("Déconnexion");
        this.errorLabel = new Label();
        this.roleLabel = new Label(role);
        this.emailLabel = new Label(email);
        this.passwordLabel = new Label(password);
        this.emailField = new TextField();
        this.passwordField = new PasswordField();
        this.listeCompteButton = new Button("Liste des comptes");
    }

    public Scene creerSceneCompte() {
        this.emailField.clear();
        this.passwordField.clear();
        Pane root = creerRootCompte("role : ", "email : ", "password : ", false);
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT); 
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.getIcons().add(this.icon);
        this.primaryStage.setTitle(Constants.NAME);
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        return scene;
    }

    public Pane creerRootCompte(String role, String ident, String password, boolean estConnecte) {
        this.emailField.clear();
        this.passwordField.clear();
        Pane root = new Pane();
        root.setBackground(new Background(this.background));
        initUIComponents(role, ident, password, estConnecte);
        configurerComposants(root, estConnecte);
        return root;
    }

    private void configurerComposants(Pane root, boolean estConnecte) {
        root.getChildren().add(this.navBarre);
        configurerBouton(this.supprimerButton, 350, 550, 150, 20, "my-button-supp", root);
        configurerLabel(this.errorLabel, 350, 330, 260, 45, "my-label-error", root);
        configurerBouton(this.modificationButton, 180, 550, 150, 20, "my-button-modif", root);
        configurerBouton(this.deconnexionButton, 520, 550, 150, 20, "my-button-deco", root);
        configurerLabel(this.roleLabel, 830, 540, 260, 45, "my-label", root);
        configurerLabel(this.emailLabel, 850, 450, 260, 45, "my-label", root);
        configurerLabel(this.passwordLabel, 930, 495, 260, 45, "my-label", root);
        configurerTextField(this.emailField, 300, 290, 260, 45, "Email", "my-field-user-con", root);
        configurerTextField(this.passwordField, 300, 390, 260, 45, "Mot de passe", "my-field-user-con", root);
        //Si le compte est admin
        if (estConnecte) {
            configurerBouton(this.listeCompteButton, 300, 600, 260, 50, "my-button-liste", root);
       }
    }

    private void configurerBouton(Button button, int x, int y, int width, int height, String style, Pane root) {
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefSize(width, height);
        button.getStyleClass().add(style);
        root.getChildren().add(button);
    }

    private void configurerLabel(Label label, int x, int y, int width, int height, String style, Pane root) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefSize(width, height);
        label.getStyleClass().add(style);
        root.getChildren().add(label);
    }

    private void configurerTextField(TextField field, int x, int y, int width, int height, String prompt, String style, Pane root) {
        field.setLayoutX(x);
        field.setLayoutY(y);
        field.setPrefSize(width, height);
        field.setPromptText(prompt);
        field.getStyleClass().add(style);
        root.getChildren().add(field);
    }

    public Button getModificationButton() {
        return this.modificationButton;
    }

    public TextField getEmailField() {
        return this.emailField;
    }

    public PasswordField getPasswordField() {
        return this.passwordField;
    }

    public void updateNavBarre(boolean estConnecte) {
        this.navBarre.initNavBarre(estConnecte, false);
    }

    public NavBarre getNavBarre() {
        return this.navBarre;
    }

    public void setNavBarre(NavBarre navBarre) {
        this.navBarre = navBarre;
    }

    public void setErrorLabel(String error) {
        this.errorLabel.setText(error);
    }

    public Label getErrorLabel() {
        return this.errorLabel;
    }

    public void setRoleLabel(String role) {
        this.roleLabel.setText(role);
    }

    public void setEmailLabel(String ident) {
        this.emailLabel.setText(ident);
    }

    public void setPasswordLabel(String password) {
        this.passwordLabel.setText(password);
    }

    public Button getSupprimerButton() {
        return this.supprimerButton;
    }

    public Label getEmailLabel() {
        return this.emailLabel;
    }

    public Label getPasswordLabel() {
        return this.passwordLabel;
    }

    public Label getRoleLabel() {
        return this.roleLabel;
    }

    public Button getListeCompteButton() {
        return this.listeCompteButton;
    }

    public Button getDeconnexiButton(){
        return this.deconnexionButton;
    }
}
    

