package application.controller;

import java.util.ArrayList;
import java.util.regex.Pattern;

import application.model.dao.*;
import application.model.data.*;
import application.view.*;
import application.view.admin.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ressources.utils.Constants;

public class AppController implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private Connexion connexion;
    private Accueil accueil;
    private APropos aPropos;
    private Inscription inscription;
    private Contact contact;
    private Compte compte;
    private UtilisateurDAO utilisateurDAO;
    private CommuneDAO communeDAO;
    private AeroportDAO aeroportDAO;
    private AnneeDAO anneeDAO;
    private GareDAO gareDao;
    private InfoCommuneDAO infoCommuneDAO;
    private DepartementDAO depDAO;
    private Utilisateur utilisateur;
    private boolean role = false;
    private boolean estConnecte = false;
    private UtilisateurFileAccess utilisateurFileAccess;
    private AdministrateurCompteListe administrateurCompteListe;
    private VueCommune vueCommune;
    private VueCommuneDetails vueCommuneD;
    private CommuneFileAccess communeFileAccess;
    private DepartementFileAccess departementFileAccess;
    private GareFileAccess gareFileAccess;
    private InfoCommuneFileAccess infoCommuneFileAccess;
    private AeroportFileAccess aeroportFileAccess;
    private AnneeFileAccess anneeFileAccess;
    String querry;
    
     private ArrayList<Commune> commune;
    
    

    public AppController(Stage primary, Connexion connexion, Inscription inscription, Accueil accueil, APropos apropos, Contact contact, Compte compte, VueCommune vueCommune ) {
        this.primaryStage = primary;
        this.connexion = connexion;
        this.inscription = inscription;
        this.accueil = accueil;
        this.aPropos = apropos;
        this.contact = contact;
        this.compte = compte;
        this.utilisateurDAO = new UtilisateurDAO();
        this.communeDAO = new CommuneDAO();
        this.aeroportDAO = new AeroportDAO();
        this.gareDao = new GareDAO();
        this.depDAO = new DepartementDAO();
        this.anneeDAO = new AnneeDAO();
        this.infoCommuneDAO = new InfoCommuneDAO();
        this.vueCommune = vueCommune;



        this.administrateurCompteListe = new AdministrateurCompteListe(primaryStage);
        this.utilisateurFileAccess = new UtilisateurFileAccess();
        this.communeFileAccess = new CommuneFileAccess();
       
        
        
        // this.gareFileAccess = new GareFileAccess();
        // this.infoCommuneFileAccess = new InfoCommuneFileAccess();
        // this.aeroportFileAccess = new AeroportFileAccess();
        // this.anneeFileAccess = new AnneeFileAccess();

        this.vueCommune.init(this, communeFileAccess, true);

        initEventHandlers();
    }

    public void updateAppController() {
        initEventHandlers();
    }

    private void initEventHandlers() {
        // Événements Accueil
        this.accueil.getNavBarre().getCompteButton().setOnAction(this);
        this.accueil.getNavBarre().getAProposButton().setOnAction(this);
        this.accueil.getNavBarre().getContactButton().setOnAction(this);
        this.accueil.getNavBarre().getCommuneButton().setOnAction(this);

        // Événements Connexion
        this.connexion.getInscriptionButton().setOnAction(this);
        this.connexion.getMotDePasseOublieButton().setOnAction(this);
        this.connexion.getConnexionButton().setOnAction(this);

        // Événements Inscription
        this.inscription.getInscriptionButton().setOnAction(this);
        this.inscription.getConnexionButton().setOnAction(this);

        // Événements Contact
        this.contact.getEnvoyerButton().setOnAction(this);
        this.contact.getNavBarre().getCompteButton().setOnAction(this);
        this.contact.getNavBarre().getAProposButton().setOnAction(this);
        this.contact.getNavBarre().getAccueilButton().setOnAction(this);
        this.contact.getNavBarre().getCommuneButton().setOnAction(this);


        // Événements APropos
        this.aPropos.getNavBarre().getCompteButton().setOnAction(this);
        this.aPropos.getNavBarre().getContactButton().setOnAction(this);
        this.aPropos.getNavBarre().getAccueilButton().setOnAction(this);
        this.aPropos.getNavBarre().getCommuneButton().setOnAction(this);

        // Événements Compte
        this.compte.getNavBarre().getAccueilButton().setOnAction(this);
        this.compte.getNavBarre().getAProposButton().setOnAction(this);
        this.compte.getNavBarre().getContactButton().setOnAction(this);
        this.compte.getListeCompteButton().setOnAction(this);
        this.compte.getDeconnexiButton().setOnAction(this);
        this.compte.getSupprimerButton().setOnAction(this);
        this.compte.getModificationButton().setOnAction(this);
        this.compte.getNavBarre().getCommuneButton().setOnAction(this);

        //Événement ListeDesComptes
        this.administrateurCompteListe.getAjouterButton().setOnAction(this);
        this.administrateurCompteListe.getSupprimerButton().setOnAction(this);
        this.administrateurCompteListe.getNavBarre().getAccueilButton().setOnAction(this);
        this.administrateurCompteListe.getNavBarre().getAProposButton().setOnAction(this);
        this.administrateurCompteListe.getNavBarre().getContactButton().setOnAction(this);
        this.administrateurCompteListe.getNavBarre().getCompteButton().setOnAction(this);
        this.administrateurCompteListe.getNavBarre().getCompteButton().setOnAction(this);
        this.administrateurCompteListe.getNavBarre().getCommuneButton().setOnAction(this);


        //Événement VueCommune
        this.vueCommune.getExportDataButton().setOnAction(this);
        this.vueCommune.getSearchField().setOnAction(this);
        this.vueCommune.getPrixField().setOnAction(this);
        this.vueCommune.getNavBarre().getAccueilButton().setOnAction(this);
        this.vueCommune.getNavBarre().getAProposButton().setOnAction(this);
        this.vueCommune.getNavBarre().getContactButton().setOnAction(this);
        this.vueCommune.getNavBarre().getCompteButton().setOnAction(this);

        
        
    }

    @Override
    public void handle(ActionEvent event) {
        Object source = event.getSource();

        // ***Accueil***
        if (source == this.accueil.getNavBarre().getCompteButton()) {
            buttonCompteAccueilClick();
        } else if (source == this.accueil.getNavBarre().getAProposButton()) {
            buttonAProposAccueilClick();
        } else if (source == this.accueil.getNavBarre().getContactButton()) {
            buttonContactAccueilClick();
        }else if (source == this.accueil.getNavBarre().getCommuneButton() && this.estConnecte){
             buttonCommuneNavBarreClick();


            // ***Contact***
        } else if (source == this.contact.getEnvoyerButton()) {
            envoyerMessage();
        } else if (source == this.contact.getNavBarre().getCompteButton()) {
            buttonCompteContactClick();
        } else if (source == this.contact.getNavBarre().getAProposButton()) {
            buttonAProposContactClick();
        } else if (source == this.contact.getNavBarre().getAccueilButton()) {
            buttonAccueilContactClick();
        }else if (source == this.contact.getNavBarre().getCommuneButton() && this.estConnecte){
            buttonCommuneNavBarreClick();

            // ***APropos***
        } else if (source == this.aPropos.getNavBarre().getAccueilButton()) {
            buttonAccueilAProposClick();
        } else if (source == this.aPropos.getNavBarre().getCompteButton()) {
            buttonCompteAProposClick();
        } else if (source == this.aPropos.getNavBarre().getContactButton()) {
            buttonContactAProposClick();
        }else if (source == this.aPropos.getNavBarre().getCommuneButton() && this.estConnecte){
            buttonCommuneNavBarreClick();

            // ***Connexion***
        } else if (source == this.connexion.getInscriptionButton()) {
            buttonInscriptionConnexionClick();
        } else if (source == this.connexion.getConnexionButton() && !this.estConnecte && !this.connexion.getEmailField().getText().isEmpty() && !this.connexion.getPasswordField().getText().isEmpty()) {
            buttonConnexionConnexion();

            // ***Inscription***
        } else if (source == this.inscription.getConnexionButton()) {
            buttonConnexionInscriptionClick();
        } else if (source == this.inscription.getInscriptionButton()) {
            buttonInscriptionInscriptionClick();

            // ***Compte***
        } else if (source == this.compte.getNavBarre().getAccueilButton()) {
            buttonAccueilCompteClick();
        } else if (source == this.compte.getNavBarre().getAProposButton()) {
            buttonAProposCompteClick();
        } else if (source == this.compte.getNavBarre().getContactButton()) {
            buttonContactCompteClick();
        } else if (source == this.compte.getDeconnexiButton()) {
            buttonDeconnexionCompteClick();
        } else if (source == this.compte.getSupprimerButton()) {
            buttonSupprimerCompteClick();
        } else if (source == this.compte.getModificationButton() && this.estConnecte) {
            buttonModifierCompteClick();
        } else if (source == this.compte.getListeCompteButton()) {
            buttonListeDesComptesComptesClick();
        }else if (source == this.compte.getNavBarre().getCommuneButton() && this.estConnecte){
            buttonCommuneNavBarreClick();
        
        // ***ListeDesComptes ***
        }else if (source == this.administrateurCompteListe.getAjouterButton()){
            buttonAjouterListeDesComptesClick(); 
        }else if (source == this.administrateurCompteListe.getSupprimerButton()){
            buttonSupprimerListeDesComptesClick(this.administrateurCompteListe.getLoginField().getText());
        } else if (source == this.administrateurCompteListe.getNavBarre().getAccueilButton()) {
            buttonAccueilListeDesComptesClick();
        } else if (source == this.administrateurCompteListe.getNavBarre().getAProposButton()) {
            buttonAProposListeDesComptesClick();
        } else if (source == this.administrateurCompteListe.getNavBarre().getContactButton()) {
            buttonContactListeDesComptesClick();
        } else if (source == this.administrateurCompteListe.getNavBarre().getCompteButton()){
            buttonComptetListeDesComptesClick();
        }else if (source == this.administrateurCompteListe.getNavBarre().getCommuneButton() && this.estConnecte){
            buttonCommuneNavBarreClick();
        

        // ***VueCommune ***
        }else if (source == this.vueCommune.getExportDataButton()){
            buttonExportDataButtonClick();
        }else if (source == this.vueCommune.getNavBarre().getAccueilButton()){
            buttonAccueilListeDesComptesClick();
        }else if (source == this.vueCommune.getNavBarre().getAProposButton()){
            buttonAProposListeDesComptesClick();
        }else if (source == this.vueCommune.getNavBarre().getContactButton()){
            buttonContactListeDesComptesClick();
        }else if (source == this.vueCommune.getNavBarre().getCompteButton()){
            buttonComptetListeDesComptesClick();
    
        }
    }            
    

    

    // ***Connexion***

    private void buttonConnexionConnexion() {
        connecterUtilisateur();
        Pane root = this.accueil.creerRootAccueil();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonInscriptionConnexionClick() {
        Pane root = this.inscription.creerRootInscription();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void connecterUtilisateur() {
        this.utilisateur = utilisateurExiste();
        if (this.utilisateur == null) {
            return;
        } else {
            this.utilisateur = utilisateurDAO.findByLoginPwd(this.utilisateur.getLogin(), this.utilisateur.getPwd());
            if (this.utilisateur.getRole().equals("administrateur")) {
                this.role = true;
            } else {
                this.role = false;
            }
            this.estConnecte = true;
            updateAppController();
        }
    }

    public Utilisateur utilisateurExiste() {
        return utilisateurDAO.findByLoginPwd(this.connexion.getEmailField().getText(), this.connexion.getPasswordField().getText());
    }

    // ***Inscription***

    public void buttonConnexionInscriptionClick() {
        Pane root = this.connexion.creerRootConnexion();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void inscrireUtilisateur() {
        utilisateur = new Utilisateur(this.inscription.getEmailField().getText(), this.inscription.getPasswordField().getText());
        utilisateurDAO.create(utilisateur);
    }

    public void buttonInscriptionInscriptionClick() {
    String email = inscription.getEmailField().getText();

    if (!emailEstValide(email)) {
        inscription.setErrorLabel("Email invalide. Veuillez entrer un email valide.");
    } else if (this.utilisateurDAO.exists(email)) {
        this.inscription.getErrorLabel().setText("Cet identifiant est déjà utilisé");
    } else if (email.isEmpty() || this.inscription.getPasswordField().getText().isEmpty()) {
        this.inscription.getErrorLabel().setText("Veuillez remplir les champs de textes");
    } else {
        Pane root = this.connexion.creerRootConnexion();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        inscrireUtilisateur();
    }

    updateAppController();
}

private boolean emailEstValide(String email) {
    String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(email).matches();
}

    // ***Accueil***

    public void buttonCompteAccueilClick() {
        this.utilisateur = utilisateurDAO.findByLoginPwd(this.utilisateur.getLogin(), this.utilisateur.getPwd());
        Pane root = this.compte.creerRootCompte(this.utilisateur.getRole(), this.utilisateur.getLogin(), this.utilisateur.getPwd(), this.role);
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonContactAccueilClick() {
        Pane root = this.contact.creerRootContact();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonAProposAccueilClick() {
        Pane root = this.aPropos.creerRootAPropos();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    // ***APropos***

    public void buttonAccueilAProposClick() {
        Pane root = this.accueil.creerRootAccueil();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonCompteAProposClick() {
        this.utilisateur = utilisateurDAO.findByLoginPwd(this.utilisateur.getLogin(), this.utilisateur.getPwd());
        Pane root = this.compte.creerRootCompte(this.utilisateur.getRole(), this.utilisateur.getLogin(), this.utilisateur.getPwd(), this.role);
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonContactAProposClick() {
        Pane root = this.contact.creerRootContact();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    // ***Contact***

    public void buttonCompteContactClick() {
        this.utilisateur = utilisateurDAO.findByLoginPwd(this.utilisateur.getLogin(), this.utilisateur.getPwd());
        Pane root = this.compte.creerRootCompte(this.utilisateur.getRole(), this.utilisateur.getLogin(), this.utilisateur.getPwd(), this.role);
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonAProposContactClick() {
        Pane root = this.aPropos.creerRootAPropos();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonAccueilContactClick() {
        Pane root = this.accueil.creerRootAccueil();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    private void envoyerMessage() {
        this.contact.envoyerMessage();
    }

    // ***Compte***

    public void buttonAccueilCompteClick() {
        Pane root = this.accueil.creerRootAccueil();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonAProposCompteClick() {
        Pane root = this.aPropos.creerRootAPropos();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonContactCompteClick() {
        Pane root = this.contact.creerRootContact();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonDeconnexionCompteClick() {
        deconnecterUtilisateur();
        Pane root = this.connexion.creerRootConnexion();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void deconnecterUtilisateur() {
        this.estConnecte = false;
        updateAppController();
    }

    //modifier son compte
    public void buttonModifierCompteClick() {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        this.utilisateur = new Utilisateur(this.compte.getEmailLabel().getText(), this.compte.getPasswordField().getText(), this.compte.getRoleLabel().getText());
        if (!utilisateurDAO.exists(this.compte.getEmailLabel().getText()) && !this.compte.getEmailLabel().getText().isEmpty() && !this.compte.getPasswordField().getText().isEmpty()) {
            utilisateurDAO.update(utilisateur, this.compte.getEmailLabel().getText(), this.compte.getRoleLabel().getText());
            buttonCompteAProposClick();
            buttonCompteAccueilClick();
            buttonCompteContactClick();
        } else {
            this.compte.getErrorLabel().setText("Cet email est déjà utilisé");
        }
    }

    //Supprimer son compte
    public void buttonSupprimerCompteClick() {
        this.utilisateur = new Utilisateur(this.compte.getEmailField().getText(), this.compte.getPasswordField().getText());
        this.utilisateurDAO.delete(utilisateur, this.compte.getEmailField().getText());
        deconnecterUtilisateur();
        buttonConnexionInscriptionClick();
    }

    //Bouton liste des comptes
    public void buttonListeDesComptesComptesClick() {
        this.administrateurCompteListe.init(this, utilisateurFileAccess);
        Pane root = this.administrateurCompteListe.creerRootCompte();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void updateLogin(String initLogin, String newLogin) {
        if (this.utilisateurDAO.exists(newLogin)) {
            this.utilisateurFileAccess.setList();
            buttonListeDesComptesComptesClick();
            buttonListeDesComptesComptesClick();
            return;
        } else {
            this.utilisateurDAO.updateLogin(initLogin, newLogin);
            this.utilisateurFileAccess.setList();
            buttonListeDesComptesComptesClick();
            buttonListeDesComptesComptesClick();
        }
    }

    public void updatePwd(Utilisateur utilisateur, String newPwd) {
        String login = utilisateur.getLogin();
        this.utilisateurDAO.updatePwd(login, newPwd);
        this.utilisateurFileAccess.setList();
        buttonListeDesComptesComptesClick();
        buttonListeDesComptesComptesClick();
    }

    public void updateRole(Utilisateur utilisateur, String newRole) {
        String login = utilisateur.getLogin();
        this.utilisateurDAO.updateRole(login, newRole);
        this.utilisateurFileAccess.setList();
        buttonListeDesComptesComptesClick();
        buttonListeDesComptesComptesClick();
    }


    /*** LISTE DES COMPTE ***/

    // Supprimer un utilisateur en tant qu'admin
    public void buttonSupprimerListeDesComptesClick(String login) {
        if (!login.isEmpty()) {
            if (this.utilisateurDAO.exists(login)) {
                Utilisateur utilisateur = this.utilisateurFileAccess.getUtilisateur(login);
                if (utilisateur != null) {
                    this.utilisateurFileAccess.deleteUtilisateur(utilisateur);
                    this.utilisateurFileAccess.setList();
                    buttonListeDesComptesComptesClick();
                } else {
                    this.administrateurCompteListe.getErrorLabel().setText("Cet utilisateur n'existe pas");
                }
            } else {
                this.administrateurCompteListe.getErrorLabel().setText("Cet utilisateur n'existe pas");
            }
        } else {
            this.administrateurCompteListe.getErrorLabel().setText("Les champs ne doivent pas être vides");
        }
    }
    //Ajouter un utilisateur en tant qu'admin
    public void buttonAjouterListeDesComptesClick() {
        String login = this.administrateurCompteListe.getLoginField().getText();
        String password = this.administrateurCompteListe.getPasswordField().getText();

        if (login.isEmpty() || password.isEmpty()) {
            this.administrateurCompteListe.getErrorLabel().setText("Veuillez remplir tous les champs.");
            return;
        }

        if (this.utilisateurDAO.exists(login)) {
            this.administrateurCompteListe.getErrorLabel().setText("Cet email est déjà utilisé.");
        } else {
            Utilisateur utilisateur = new Utilisateur(login, password);
            this.utilisateurDAO.create(utilisateur);
            this.utilisateurFileAccess.setList();
            buttonListeDesComptesComptesClick();
        }
    }

    public void buttonAccueilListeDesComptesClick(){
        Pane root = this.accueil.creerRootAccueil();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonContactListeDesComptesClick(){
        Pane root = this.contact.creerRootContact();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonAProposListeDesComptesClick(){
        Pane root = this.aPropos.creerRootAPropos();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
    }

    public void buttonComptetListeDesComptesClick(){
        this.utilisateur = utilisateurDAO.findByLoginPwd(this.utilisateur.getLogin(), this.utilisateur.getPwd());
        Pane root = this.compte.creerRootCompte(this.utilisateur.getRole(), this.utilisateur.getLogin(), this.utilisateur.getPwd(), this.role);
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();

    }

    /***VUECOMMUNE***/
    public void buttonCommuneNavBarreClick() {
        // this.vueCommune.getSearchField().clear();
        // resetCommuneTable();
        // this.vueCommune.resetCheckBox();
        Pane root = this.vueCommune.creerRootDonnee(estConnecte, role);
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT); 
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        updateAppController();
        // this.vueCommuneD.getComboBox().setValue(null);
        // this.vueCommuneD.resetValues();
    }

    public void resetCommuneTable() {
        this.vueCommune.setCommuneTable(commune);
    }

    public void buttonExportDataButtonClick() {
        this.communeFileAccess.writeDonneeToCSVFile("BaseDeDonnee");
    }
}
