package application.view;

import java.util.ArrayList;
import application.controller.AppController;
import application.model.dao.*;
import application.model.data.*;
import application.view.tableau.*;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ressources.utils.Constants;

public class VueCommuneDetails {
    private Image icon, backgroundImage;
    private BackgroundImage background;
    private NavBarre navBarre;
    private Stage primaryStage;
    private ComboBox<String> anneeBox;
    private Label nomCommune, nomDepartementLabel, numeroLabel, prixm2Label, prixMoyenLabel, surfaceMoyenneLabel, nbMaisonLabel, nbAppartLabel, inflationLabel;
    private Button exportDataButton, ajouterGare, ajouterAeroport;
    private AeroportTableau aeroportTable;
    private GareTableau gareTable;
    private VoisineTableau voisineTable;
    private Commune commune;
    private Departement departement;
    private InfoCommune infoCommune;
    private Annee annee;

    public VueCommuneDetails(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.icon = new Image(Constants.ICON);
        this.backgroundImage = new Image(Constants.COMMUNEDETAILS);
        this.background = new BackgroundImage(this.backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        
        this.navBarre = new NavBarre();
        initUIComponents();
    }

    public void init(AppController appController, Boolean isAdmin, String idCommune) {
        this.gareTable = new GareTableau(new ArrayList<>(), appController);
        this.aeroportTable = new AeroportTableau(new ArrayList<>(), appController, isAdmin);
        this.voisineTable = new VoisineTableau(new ArrayList<>(), appController, idCommune);
    }

    public void init(AppController appController, Boolean isAdmin, String idCommune, GareFileAccess gareFileAccess, AeroportFileAccess aeroportFileAccess, CommuneFileAccess communeFileAccess) {
        // this.gareTable = new GareTableau(gareFileAccess.getGaresByCommune(idCommune), appController);
        // this.aeroportTable = new AeroportTableau(aeroportFileAccess.getAeroportsByDepartement(this.departement.getIdDepartement()), appController, isAdmin);
        // this.voisineTable = new VoisineTableau(communeFileAccess.getCommuneVoisines(idCommune), appController, idCommune);
    }

    public void initUIComponents() {
        this.anneeBox = new ComboBox<>();
        this.anneeBox.getItems().addAll("2018", "2019", "2020", "2021");
        this.nomCommune = new Label("Données");
        this.nomDepartementLabel = new Label("Données");
        this.numeroLabel = new Label("Données");
        this.prixm2Label = new Label("Données");
        this.prixMoyenLabel = new Label("Données");
        this.surfaceMoyenneLabel = new Label("Données");
        this.nbMaisonLabel = new Label("Données");
        this.nbAppartLabel = new Label("Données");
        this.inflationLabel = new Label("Données");
        this.exportDataButton = new Button("Exporter les données");
        this.ajouterGare = new Button("+");
        this.ajouterAeroport = new Button("+");

        this.commune = new Commune("idCommune", "nomCommune", "leDepartement");
        this.departement = new Departement("idDepartement", "nomDepartement", "inflationLabel");
    }

    public void setGareTable(ArrayList<Gare> gares) {
        this.gareTable.setItems(FXCollections.observableArrayList(gares));
    }

    public Scene creerSceneDonnee() {
        Pane root = creerRootDonnee(false, false);
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT); 
        scene.getStylesheets().add(getClass().getResource("../../resource/app.css").toExternalForm());
        this.primaryStage.getIcons().add(this.icon);
        this.primaryStage.setTitle(Constants.NAME);
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        return scene;
    }

    public Pane creerRootDonnee(boolean estConnecte, boolean estAdmin) {
        Pane root = new Pane();
        root.setBackground(new Background(this.background));
        configurerComposants(root, estAdmin);
        return root;
    }

    private void configurerComposants(Pane root, boolean estAdmin) {
        root.getChildren().add(this.navBarre);
        configurerLabel(this.nomCommune, 110, 430, "my-label-commune", root);
        configurerLabel(this.nomDepartementLabel, 110, 530, "my-label-commune", root);
        configurerLabel(this.numeroLabel, 110, 480, "my-label-commune", root);
        configurerLabel(this.prixm2Label, 570, 397, "my-label-commune-b", root);
        configurerLabel(this.prixMoyenLabel, 570, 462, "my-label-commune-b", root);
        configurerLabel(this.surfaceMoyenneLabel, 830, 462, "my-label-commune-b", root);
        configurerLabel(this.inflationLabel, 800, 397, "my-label-commune-b", root);
        configurerLabel(this.nbMaisonLabel, 1110, 462, "my-label-commune-b", root);
        configurerLabel(this.nbAppartLabel, 1110, 397, "my-label-commune-b", root);
        configurerBouton(this.exportDataButton, 140, 600, "my-button", root);

        if (estAdmin) {
            configurerBouton(this.ajouterGare, 570, 180, "my-button-add", root);
            configurerBouton(this.ajouterAeroport, 570, 305, "my-button-add", root);
        }
        
        configurerTable(this.aeroportTable, 650, 250, "my-table", root, 482, 105);
        configurerTable(this.gareTable, 650, 120, "my-table", root, 482, 105);
        configurerTable(this.voisineTable, 680, 533, "my-table", root, 492, 150);

        configurerComboBox(this.anneeBox, 345, 140, 135, 45, "my-combo-box", root);
    }
    
    private void configurerComboBox(ComboBox<String> anneeBox, int x, int y, int width, int height, String styleClass, Pane root) {
        anneeBox.setLayoutX(x);
        anneeBox.setLayoutY(y);
        anneeBox.setPrefWidth(width);
        anneeBox.setPrefHeight(height);
        anneeBox.getStyleClass().add(styleClass);
        root.getChildren().add(anneeBox);
    }

    private void configurerBouton(Button bouton, int x, int y, String styleClass, Pane root) {
        bouton.setLayoutX(x);
        bouton.setLayoutY(y);
        bouton.getStyleClass().add(styleClass);
        root.getChildren().add(bouton);
    }

    private void configurerLabel(Label label, int x, int y, String styleClass, Pane root) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.getStyleClass().add(styleClass);
        root.getChildren().add(label);
    }

    private void configurerTable(TableView<?> table, int x, int y, String styleClass, Pane root, int width, int height) {
        table.setLayoutX(x);
        table.setLayoutY(y);
        table.setPrefWidth(width);
        table.setPrefHeight(height);
        table.getStyleClass().add(styleClass);
        root.getChildren().add(table);
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

    public void setLeDepartement(Departement departement) {
        this.departement = departement;
    }

    public String getComboBoxValue() {
        return this.anneeBox.getValue();
    }

    public ComboBox<String> getComboBox() {
        return this.anneeBox;
    }

    public void setAnnee(String annee) {
        this.anneeBox.setValue(annee);
        // this.inflationLabel.setText(this.infoCommune.getAnnee().getTauxInflation());
    }

    public void setLaCommune(String idCommune, CommuneFileAccess communeFileAccess, DepartementFileAccess departementFileAccess, AppController c, Boolean isAdmin) {
        this.commune = communeFileAccess.getCommuneById(idCommune);
        // this.commune.setCommuneVoisines(communeFileAccess.getCommuneVoisine(idCommune));
        // this.departement = departementFileAccess.getDepartementById(this.commune.getLeDepartement());
        // this.aeroportTable = new AeroportTableau(this.departement.getListeAeroports(), c, isAdmin);
        // this.gareTable = new GareTableau(this.commune.getListeGares(), c);
        // this.voisineTable = new VoisineTableau(this.commune.getCommuneVoisine(), c, idCommune);

        this.nomCommune.setText(this.commune.getNomCommune());
        this.numeroLabel.setText(idCommune);
        this.nomDepartementLabel.setText(this.departement.getNomDep());
    }

    public void setLaCommune(String idCommune, CommuneFileAccess communeFileAccess, DepartementFileAccess departementFileAccess, String annee, InfoCommuneFileAccess infoCommuneFileAccess, AnneeFileAccess anneeFileAccess, AppController c, Boolean isAdmin, GareDAO gareDAO) {
        if (annee != null) {
            this.commune = communeFileAccess.getCommuneById(idCommune);
            this.commune.setCommuneVoisines(communeFileAccess.getCommuneVoisine(idCommune));
            // this.departement = departementFileAccess.getDepartementById(this.commune.getLeDepartement());
            // this.aeroportTable = new AeroportTableau(this.departement.getListeAeroports(), c, isAdmin);
            // this.gareTable = new GareTableau(gareDAO.findByCommune(idCommune), c);
            // this.voisineTable = new VoisineTableau(this.commune.getCommuneVoisine(), c, idCommune);
            // this.annee = anneeFileAccess.getAnneeById(annee);
            // this.infoCommune = infoCommuneFileAccess.getInfoCommuneById(annee, idCommune);

            this.nomCommune.setText(this.commune.getNomCommune());
            this.numeroLabel.setText(idCommune);
            this.nomDepartementLabel.setText(this.departement.getNomDep());
            // this.prixm2Label.setText(this.infoCommune.getPrixM2Moyen());
            // this.prixMoyenLabel.setText(this.infoCommune.getPrixMoyen());
            // this.surfaceMoyenneLabel.setText(this.infoCommune.getSurfaceMoyenne());
            // this.nbMaisonLabel.setText(this.infoCommune.getNbMaisons());
            // this.nbAppartLabel.setText(this.infoCommune.getNbAppartements());
            // this.inflationLabel.setText(this.infoCommune.getAnnee().getTauxInflation());
            this.anneeBox.setValue(annee);
        } else {
            this.commune = communeFileAccess.getCommuneById(idCommune);
            this.commune.setCommuneVoisines(communeFileAccess.getCommuneVoisine(idCommune));
            // this.departement = departementFileAccess.getDepartementById(this.commune.getLeDepartement());
            // this.aeroportTable = new AeroportTableau(this.departement.getListeAeroports(), c, isAdmin);
            // this.gareTable = new GareTableau(gareDAO.findByCommune(idCommune), c);
            // this.voisineTable = new VoisineTableau(this.commune.getCommuneVoisine(), c, idCommune);
            // this.annee = anneeFileAccess.getAnneeById(annee);
            // this.infoCommune = infoCommuneFileAccess.getInfoCommuneById(annee, idCommune);

            this.nomCommune.setText(this.commune.getNomCommune());
            this.numeroLabel.setText(idCommune);
            this.nomDepartementLabel.setText(this.departement.getNomDep());
            this.anneeBox.setValue(annee);
        }
    }

    public void resetValues() {
        this.prixm2Label.setText("Données");
        this.prixMoyenLabel.setText("Données");
        this.surfaceMoyenneLabel.setText("Données");
        this.nbMaisonLabel.setText("Données");
        this.nbAppartLabel.setText("Données");
        this.inflationLabel.setText("Données");
    }

    public Button getExportDataButton() {
        return this.exportDataButton;
    }

    public Button getAjouterGare() {
        return this.ajouterGare;
    }

    public Button getAjouterAeroport() {
        return this.ajouterAeroport;
    }

    public Label getCodeCommune() {
        return this.numeroLabel;
    }

    public Commune getCommune() {
        return this.commune;
    }

    public void setAeroportTable(ArrayList<Aeroport> aeroports) {
        this.aeroportTable.setItems(FXCollections.observableArrayList(aeroports));
    }
}
