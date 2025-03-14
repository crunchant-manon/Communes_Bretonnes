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
import application.model.data.Commune;

public class VoisineTableau extends TableView<Commune> {

    public VoisineTableau(ArrayList<Commune> communes, AppController controller, String idCommune) {
        ObservableList<Commune> data = FXCollections.observableArrayList(communes);
        this.setEditable(true);
        this.getSelectionModel().setCellSelectionEnabled(true);
        this.getStylesheets().add(getClass().getResource("../../../ressources/application.css").toExternalForm());

        TableColumn<Commune, String> nomColumn = createColumn("Nom", "nomCommune", 230);
        TableColumn<Commune, String> idColumn = createColumn("Code Postal", "idCommune", 130);
        TableColumn<Commune, String> otherColumn = createOtherColumn(controller);

        this.setItems(data);
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.getColumns().addAll(nomColumn, idColumn, otherColumn);

        // Ajouter des classes CSS
        for (TableColumn<Commune, ?> col : this.getColumns()) {
            col.getStyleClass().add("my-table-col");
        }
    }

    private TableColumn<Commune, String> createColumn(String title, String property, int width) {
        TableColumn<Commune, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setEditable(false);
        column.setMinWidth(width);
        return column;
    }

    private TableColumn<Commune, String> createOtherColumn(AppController controller) {
        TableColumn<Commune, String> otherColumn = new TableColumn<>("Détails");
        otherColumn.setCellFactory(col -> new TableCell<Commune, String>() {
            private final Button infoButton = new Button("+Infos");
            private final FlowPane pane = new FlowPane(infoButton);

            {
                infoButton.getStyleClass().add("my-button-info");
                pane.setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Commune commune = getTableView().getItems().get(getIndex());
                    infoButton.setId(String.valueOf(commune.getIdCommune()));
                    infoButton.setOnAction(controller);
                    setGraphic(pane);
                    setText(null);
                }
            }
        });
        otherColumn.setMinWidth(130);
        return otherColumn;
    }
}
