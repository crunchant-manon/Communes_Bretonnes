package application.view;
import java.util.ArrayList;

import application.controller.AppController;
import application.model.data.Commune;
import application.model.data.CommuneFileAccess;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import application.view.tableau.CommuneTableau;;

public class VueCommune extends Pane {

    private Image icon, commune;
    private BackgroundImage backgroundImg;
    private NavBarre navBarre;
    private Stage primaryStage;
    private CheckBox tri1, tri2, tri3, tri4, tri5, tri6;
    private Label donneLabel, errorLabel;
    private TextField searchField, prixField;
    private CommuneTableau communeTableau;
    private Button exportDataButton;

    public VueCommune(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.icon = new Image(Constants.ICON);
        this.commune = new Image(Constants.COMMUNE);
        this.backgroundImg = new BackgroundImage(this.commune, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        
        this.navBarre  = new NavBarre();
        initUIComponents();
    }

    public void init(AppController appController, CommuneFileAccess communeFileAccess, Boolean isAdmin) {
        this.communeTableau = new CommuneTableau(communeFileAccess, appController);
    }

    public void setCommuneTable(ArrayList<Commune> communes) {
        this.communeTableau.setCommunes(communes);
    }

    public void initUIComponents() {

        this.donneLabel = new Label("Communes");
        this.errorLabel = new Label("");
        this.exportDataButton = new Button("Exporter les données");
        this.searchField = new TextField();
        this.prixField = new TextField("0");
        this.searchField.setId("searchField");
        this.prixField.setId("prixField");
    }


    public Scene creerSceneDonnee() {
        Pane root = creerRootDonnee(false, false);
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT); 
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.getIcons().add(this.icon);
        this.primaryStage.setTitle(Constants.NAME);
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        return scene;
    }

    public Pane creerRootDonnee(boolean estConnecte, boolean estAdmin) {
        Pane root = new Pane();
        root.setBackground(new Background(this.backgroundImg));
        configurerComposants(root);
        return root;
    }

    private void configurerComposants(Pane root) {
        root.getChildren().add(this.navBarre);
        configurerBouton(this.exportDataButton, 160, 500, "my-button", root);

        configurerTable(this.communeTableau, 380, 195, "my-table", root, 522, 430);
    }

    private void configurerBouton(Button bouton, int x, int y, String styleClass, Pane root) {
        bouton.setLayoutX(x);
        bouton.setLayoutY(y);
        bouton.getStyleClass().add(styleClass);
        root.getChildren().add(bouton);
    }

    private void configurerLabel(Label label, int x, int y, int width, int height, String style, Pane root) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefSize(width, height);
        label.getStyleClass().add(style);
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

    private void configurerTable(CommuneTableau t, int x, int y, String style, Pane root, int width, int height) {
        t.setLayoutX(x);
        t.setLayoutY(y);
        t.setPrefWidth(width);
        t.setPrefHeight(height);
        t.getStyleClass().add(style);
        root.getChildren().add(t);
    }

    public void updateNavBarre(boolean estConnecte) {
        this.navBarre.initNavBarre(estConnecte, true);
    }

    public NavBarre getNavBarre() {
        return this.navBarre;
    }

    public void setNavBarre(NavBarre navBarre) {
        this.navBarre = navBarre;
    }

    public CheckBox getTri1() {
        return this.tri1;
    }

    public CheckBox getTri2() {
        return this.tri2;
    }

    public CheckBox getTri3() {
        return this.tri3;
    }

    public CheckBox getTri4() {
        return this.tri4;
    }

    public CheckBox getTri5() {
        return this.tri5;
    }

    public CheckBox getTri6() {
        return this.tri6;
    }

    public Label getDonneLabel() {
        return this.donneLabel;
    }

    public Label getErrorLabel() {
        return this.errorLabel;
    }

    public TextField getSearchField() {
        return this.searchField;
    }

    public Button getExportDataButton() {
        return this.exportDataButton;
    }

    public TextField getPrixField() {
        return this.prixField;
    }

    public void resetCheckBox() {
        this.tri1.setSelected(false);
        this.tri2.setSelected(false);
        this.tri3.setSelected(false);
        this.tri4.setSelected(false);
        this.tri5.setSelected(false);
        this.tri6.setSelected(false);
    }

}




    

