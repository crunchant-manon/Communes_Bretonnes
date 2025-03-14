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
import application.model.data.CommuneFileAccess;

public class CommuneTableau extends TableView<Commune> {

    public CommuneTableau(CommuneFileAccess communeFileAccess, AppController controller) {
        ObservableList<Commune> communes = FXCollections.observableArrayList(communeFileAccess.getCommunes());
        this.setEditable(true);
        this.getSelectionModel().setCellSelectionEnabled(true);
        this.getStylesheets().add(getClass().getResource("../../../ressources/application.css").toExternalForm());

        TableColumn<Commune, String> nomColumn = createColumn("Nom", "nomCommune", 130);
        TableColumn<Commune, String> departColumn = createColumn("Département", "leDepartement", 130);
        TableColumn<Commune, String> idColumn = createColumn("Code Postal", "idCommune", 130);
        TableColumn<Commune, String> detailsColumn = createDetailsColumn(controller);

        this.setItems(communes);
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        // this.getColumns().addAll(nomColumn, departColumn, idColumn, detailsColumn);
        this.getColumns().add(nomColumn);
        this.getColumns().add(departColumn);
        this.getColumns().add(idColumn);
        this.getColumns().add(detailsColumn);



        // Apply CSS classes to columns
        for (TableColumn<Commune, ?> col : this.getColumns()) {
            col.getStyleClass().add("my-table-col");
        }

        this.setMaxSize(600, 800);
    }

    private TableColumn<Commune, String> createColumn(String title, String property, int width) {
        TableColumn<Commune, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setEditable(false);
        column.setMinWidth(width);
        return column;
    }

    private TableColumn<Commune, String> createDetailsColumn(AppController controller) {
        TableColumn<Commune, String> detailsColumn = new TableColumn<>("Détails");
        detailsColumn.setCellFactory(col -> new TableCell<Commune, String>() {
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
        detailsColumn.setMinWidth(130);
        return detailsColumn;
    }

    public void setCommunes(ArrayList<Commune> communes) {
        ObservableList<Commune> data = FXCollections.observableArrayList(communes);
        this.setItems(data);
    }

    public void refreshTable(CommuneFileAccess communeFileAccess) {
        ObservableList<Commune> data = FXCollections.observableArrayList(communeFileAccess.getCommunes());
        this.setItems(data);
    }
}
