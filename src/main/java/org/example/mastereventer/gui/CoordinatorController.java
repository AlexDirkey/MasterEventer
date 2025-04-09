package org.example.mastereventer.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.example.mastereventer.Objects.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class CoordinatorController {

    @FXML private TableView<Event> eventTable;
    @FXML
    private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, String> dateColumn;
    @FXML private TableColumn<Event, String> locationColumn;

    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        eventTable.setItems(eventList);
    }

    @FXML
    private void handleCreateEvent() {
        // Brug en simpel dialog til at oprette en event
        Dialog<Event> dialog = new Dialog<>();
        dialog.setTitle("Opret ny event");

        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField titleField = new TextField();
        TextField locationField = new TextField();
        DatePicker datePicker = new DatePicker();

        grid.add(new Label("Titel:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Lokation:"), 0, 1);
        grid.add(locationField, 1, 1);
        grid.add(new Label("Dato:"), 0, 2);
        grid.add(datePicker, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String title = titleField.getText();
                String location = locationField.getText();
                LocalDate date = datePicker.getValue();
                LocalDateTime startDate = date.atStartOfDay(); // midlertidigt
                return new Event("festval", "tønder", "husk tæppe", startDate); // notes tom for nu
            }
            return null;
        });

        Optional<Event> result = dialog.showAndWait();
        result.ifPresent(event -> eventList.add(event));
    }

    @FXML
    private void handleDeleteEvent() {

        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {

            Alert alert = new Alert(Alert.AlertType.WARNING, "Vælg et event at slette", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.WARNING, "Er du sikker på at slette dette event?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {

            eventTable.getItems().remove(selectedEvent);
        }
    }
}

