package org.example.mastereventer.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.mastereventer.Objects.Event;
import org.example.mastereventer.bll.EventManager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminController {


    @FXML private TableView<Event> eventTable;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, String> dateColumn;
    @FXML private TableColumn<Event, String> locationColumn;

    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    // Instans af EventManager – BLL-laget
    private EventManager eventManager = new EventManager();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        eventList.clear();
        eventList.addAll(eventManager.getAllEvents());
        eventTable.setItems(eventList);
    }

    @FXML
    private void handleCreateEvent() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Opret ny event");

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

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            String title = titleField.getText();
            String location = locationField.getText();
            LocalDate date = datePicker.getValue();
            if (date != null) {
                LocalDateTime startDate = date.atStartOfDay();
                Event newEvent = new Event(title, location, "husk tæppe", startDate);
                eventManager.createEvent(newEvent);
                // Opdater eventList med de nyeste data fra DB
                eventList.clear();
                eventList.addAll(eventManager.getAllEvents());
            }
            popupStage.close();
        });
        grid.add(okButton, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        popupStage.setScene(scene);
        popupStage.show();
    }

    @FXML
    private void handleDeleteEvent() {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            Stage warningStage = new Stage();
            warningStage.setTitle("Advarsel");
            VBox box = new VBox(10);
            box.setAlignment(javafx.geometry.Pos.CENTER);
            Label warningLabel = new Label("Vælg et event at slette");
            Button okButton = new Button("OK");
            okButton.setOnAction(e -> warningStage.close());
            box.getChildren().addAll(warningLabel, okButton);
            Scene warningScene = new Scene(box, 250, 100);
            warningStage.setScene(warningScene);
            warningStage.showAndWait();
            return;
        }
        eventManager.deleteEvent(selectedEvent.getId());
        eventList.remove(selectedEvent);
    }

    @FXML
    private void handleEditEvent() {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            Stage warningStage = new Stage();
            warningStage.setTitle("Advarsel");
            VBox box = new VBox(10);
            box.setAlignment(javafx.geometry.Pos.CENTER);
            Label warningLabel = new Label("Vælg et event at redigere");
            Button okButton = new Button("OK");
            okButton.setOnAction(e -> warningStage.close());
            box.getChildren().addAll(warningLabel, okButton);
            Scene warningScene = new Scene(box, 250, 100);
            warningStage.setScene(warningScene);
            warningStage.showAndWait();
            return;
        }
        Stage editStage = new Stage();
        editStage.setTitle("Rediger event");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField titleField = new TextField(selectedEvent.getTitle());
        TextField locationField = new TextField(selectedEvent.getLocation());
        DatePicker datePicker = new DatePicker(selectedEvent.getStartDateTime().toLocalDate());

        grid.add(new Label("Titel:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Lokation:"), 0, 1);
        grid.add(locationField, 1, 1);
        grid.add(new Label("Dato:"), 0, 2);
        grid.add(datePicker, 1, 2);

        Button btnOk = new Button("OK");
        btnOk.setOnAction(event -> {
            String newTitle = titleField.getText();
            String newLocation = locationField.getText();
            LocalDate date = datePicker.getValue();
            LocalDateTime newStartDate = date.atStartOfDay();
            Event updatedEvent = new Event(newTitle, newLocation, selectedEvent.getNotes(), newStartDate);
            eventManager.updateEvent(updatedEvent);
            eventList.clear();
            eventList.addAll(eventManager.getAllEvents());
            editStage.close();
        });
        grid.add(btnOk, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        editStage.setScene(scene);
        editStage.show();
    }
}
