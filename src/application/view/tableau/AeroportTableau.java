package application.view.tableau;

import java.util.ArrayList;
import application.controller.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import application.model.data.Aeroport;

public class AeroportTableau extends TableView<Aeroport> {

    public AeroportTableau(ArrayList<Aeroport> aeroports, AppController controller, Boolean isAdmin) {
        ObservableList<Aeroport> data = FXCollections.observableArrayList(aeroports);
        this.setEditable(true);
        this.getSelectionModel().setCellSelectionEnabled(true);
        this.getStylesheets().add(getClass().getResource("../../../ressources/application.css").toExternalForm());

        TableColumn<Aeroport, String> nomColumn = createColumn("Nom", "nom", 230);
        TableColumn<Aeroport, String> adresseColumn = createColumn("Adresse", "adresse", 300);
        TableColumn<Aeroport, String> otherColumn = createOtherColumn(controller, isAdmin);

        this.setItems(data);
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.getColumns().addAll(nomColumn, adresseColumn, otherColumn);

        // Ajouter des classes CSS
        for (TableColumn<Aeroport, ?> col : this.getColumns()) {
            col.getStyleClass().add("my-table-col");
        }
    }

    private TableColumn<Aeroport, String> createColumn(String title, String property, int width) {
        TableColumn<Aeroport, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setEditable(false);
        column.setMinWidth(width);
        return column;
    }

    private TableColumn<Aeroport, String> createOtherColumn(AppController controller, Boolean isAdmin) {
        TableColumn<Aeroport, String> otherColumn = new TableColumn<>("Actions");
        if (isAdmin) {
            otherColumn.setCellFactory(col -> new TableCell<Aeroport, String>() {
                private final Button deleteButton = new Button("Supprimer");
                private final FlowPane pane = new FlowPane(deleteButton);

                {
                    deleteButton.getStyleClass().add("my-button-sup");
                    pane.setAlignment(Pos.CENTER);
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Aeroport aeroport = getTableView().getItems().get(getIndex());
                        deleteButton.setId(String.valueOf(aeroport.getNom()));
                        deleteButton.setOnAction(controller);
                        setGraphic(pane);
                        setText(null);
                    }
                }
            });
            otherColumn.setMinWidth(130);
        }
        return otherColumn;
    }

    public void setAeroports(ArrayList<Aeroport> aeroports) {
        this.setItems(FXCollections.observableArrayList(aeroports));
    }
}
