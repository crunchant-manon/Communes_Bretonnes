package application.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ressources.utils.Constants;

public class APropos extends Pane {

    private Stage primaryStage;
    private Image icon;
    private BackgroundImage background;
    private NavBarre navBarre;

   

    public APropos(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.icon = new Image(Constants.ICON);
        this.background = new BackgroundImage(new Image(Constants.APROPOS), 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
            new BackgroundSize(100, 100, true, true, false, true));
        this.navBarre = new NavBarre();
        

    }

    public Scene sceneAPropos(){
        Pane root = creerRootAPropos();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT); 
        scene.getStylesheets().add(getClass().getResource("../../ressources/application.css").toExternalForm());
        this.primaryStage.setTitle(Constants.NAME);
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        return scene;
    }

    public Pane creerRootAPropos(){
        Pane root = new Pane();
        root.setBackground(new Background(this.background));
        configurerComposants(root);
        return root;

    }

    
    private void configurerComposants(Pane root) {
        root.getChildren().add(this.navBarre);
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
