package application;
import application.model.*;
import application.view.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.scene.Scene;
import application.controller.AppController;

/**
 * Main class
 * @author C.LEBRECH, M.CRUNCHANT, G.ZENSEN DA SILVA, A-M.ESKHADJIEV
 */
public class Main extends Application {

    /**
     * 
     * @param primaryStage principal scene
     */
    @Override
    public void start(Stage primaryStage){
        try{
            Connexion connexion = new Connexion (primaryStage);
            Inscription inscription = new Inscription(primaryStage);
            Contact contact = new Contact(primaryStage);
            APropos apropos = new APropos(primaryStage);
            Compte compte = new Compte(primaryStage);
            Accueil accueil = new Accueil(primaryStage);
            VueCommune commune = new VueCommune(primaryStage);
            
            new AppController(primaryStage, connexion, inscription,accueil,apropos,contact,compte, commune);
            primaryStage.setScene(connexion.creerSceneConnexion());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
    
        }
    }
    /**
     * 
     * @param args arguments
     */
    public static void main(String[] args){
        launch(args);
    
    }

}

