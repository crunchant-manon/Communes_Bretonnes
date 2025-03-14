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
import application.model.data.Gare;

public class GareTableau extends TableView<Gare> {

    public GareTableau(ArrayList<Gare> gares, AppController controller) {
        ObservableList<Gare> data = FXCollections.observableArrayList(gares);
        this.setEditable(true);
        this.getSelectionModel().setCellSelectionEnabled(true);
        this.getStylesheets().add(getClass().getResource("../../../ressources/application.css").toExternalForm());

        TableColumn<Gare, String> nomColumn = createColumn("Nom", "nomGare", 230);
        TableColumn<Gare, String> idColumn = createColumn("Code", "codeGare", 130);
        TableColumn<Gare, String> otherColumn = createOtherColumn(controller);

        this.setItems(data);
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.getColumns().addAll(nomColumn, idColumn, otherColumn);

        // Ajouter des classes CSS
        for (TableColumn<Gare, ?> col : this.getColumns()) {
            col.getStyleClass().add("my-table-col");
        }
    }

    private TableColumn<Gare, String> createColumn(String title, String property, int width) {
        TableColumn<Gare, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setEditable(false);
        column.setMinWidth(width);
        return column;
    }

    private TableColumn<Gare, String> createOtherColumn(AppController controller) {
        TableColumn<Gare, String> otherColumn = new TableColumn<>("Détails");
        otherColumn.setCellFactory(col -> new TableCell<Gare, String>() {
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
                    Gare gare = getTableView().getItems().get(getIndex());
                    infoButton.setId(String.valueOf(gare.getCodeGare()));
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
