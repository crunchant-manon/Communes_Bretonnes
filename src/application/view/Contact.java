package application.view;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ressources.utils.Constants;

public class Contact {
    private Stage primaryStage;
    private Image icon;
    private BackgroundImage background;
    private Button envoyerButton;
    private Label emailLabel, messageLabel;
    private TextField emailField;
    private TextArea messageArea;
    private NavBarre navBarre;
   

    public Contact(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.icon = new Image(Constants.ICON);
        this.background = new BackgroundImage(new Image(Constants.CONTACT), 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, false, true));
            initUIComponents();
        this.navBarre = new NavBarre();

    }

    private void initUIComponents() {
        this.envoyerButton = new Button("Envoyer");
        this.emailLabel = new Label("Email :");
        this.emailField = new TextField();
        this.messageLabel = new Label("Message : ");
        this.messageArea = new TextArea();
    }

    
    public Scene sceneContact(){
        Pane root = creerRootContact();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT); 
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setTitle(Constants.NAME);
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        return scene;
    }

    public Pane creerRootContact(){
        this.emailField.clear();
        this.messageArea.clear();
        Pane root = new Pane();
        root.setBackground(new Background(this.background));
        configurerComposants(root);
        return root;
    }

    private void configurerComposants(Pane root) {
        configurerBouton(envoyerButton, 650, 600, 150, 40, "my-button", root);
        configurerLabel(emailLabel, 425, 250, "my-label-contact", root);
        configurerLabel(messageLabel, 425, 350, "my-label-contact", root);
        configurerTextField(emailField, 425, 300, 500, 30, "gwendal-lebreton@example.com", "my-field-email-contact", root);
        configurerTextArea(messageArea, 425, 400, 450, 150, "Écrivez votre message ici", "my-area-message-contact", root);
        root.getChildren().add(this.navBarre);
    }

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
    private void configurerTextArea(TextArea champ, int x, int y, int largeur, int hauteur, String promptText, String styleClass, Pane root) {
        champ.setLayoutX(x);
        champ.setLayoutY(y);
        champ.setPrefWidth(largeur);
        champ.setPrefHeight(hauteur);
        champ.setPromptText(promptText);
        champ.getStyleClass().add(styleClass);
        root.getChildren().add(champ);
    }

    //Confirmation de l'envoie du message
    public void envoyerMessage() {
        String email = emailField.getText();
        String message = messageArea.getText();

        if (email.isEmpty() || message.isEmpty()) {
            afficherAlerte("Erreur", "Veuillez remplir tous les champs.");
        } else {

            afficherAlerte("Succès", "Votre message a été envoyé avec succès.");
        }
    }
    //Cette méthode est appelé si les champs de textes ne sont pas remplis
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

         
    }

    public Button getEnvoyerButton(){
        return this.envoyerButton;
    }

    public TextField getEmailField(){
        return this.emailField;
    }

    public TextArea getMessageArea(){
        return this.messageArea;
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
}
