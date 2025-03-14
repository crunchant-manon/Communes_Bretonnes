package application.view.admin;

import application.controller.AppController;
import application.model.data.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import java.util.ArrayList;

public class AdministrateurTableComptes extends TableView<Utilisateur> {

    private AppController controller;

    public AdministrateurTableComptes(ArrayList<Utilisateur> u, AppController controller) {
        this.controller = controller;
        ObservableList<Utilisateur> data = FXCollections.observableArrayList(u);
        this.setEditable(true);
        this.getSelectionModel().setCellSelectionEnabled(true);
        this.getStylesheets().add(getClass().getResource("../../../ressources/application.css").toExternalForm());
        this.setPrefWidth(450); // Ajustez la largeur totale de la table
        initializeTable();
        this.setItems(data);
    }

    private void initializeTable() {
        TableColumn<Utilisateur, String> loginColumn = new TableColumn<>("Email");
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        loginColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        loginColumn.setOnEditCommit(event -> {
            Utilisateur user = event.getRowValue();
            String oldLogin = user.getLogin();
            user.setLogin(event.getNewValue());
            controller.updateLogin(oldLogin, event.getNewValue());
        });
        loginColumn.setMinWidth(150);

        TableColumn<Utilisateur, String> pwdColumn = new TableColumn<>("Mot de passe");
        pwdColumn.setCellValueFactory(new PropertyValueFactory<>("pwd"));
        pwdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pwdColumn.setOnEditCommit(event -> {
            Utilisateur user = event.getRowValue();
            user.setPwd(event.getNewValue());
            controller.updatePwd(user, event.getNewValue());
        });
        pwdColumn.setMinWidth(150);

        TableColumn<Utilisateur, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setOnEditCommit(event -> {
            Utilisateur user = event.getRowValue();
            user.setRole(event.getNewValue());
            controller.updateRole(user, event.getNewValue());
        });
        roleColumn.setMinWidth(150);

        getColumns().addAll(loginColumn, pwdColumn, roleColumn);
    }
}
