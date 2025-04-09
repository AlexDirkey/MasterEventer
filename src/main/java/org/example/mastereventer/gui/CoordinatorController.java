package org.example.mastereventer.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.mastereventer.Objects.Event;
import org.example.mastereventer.Objects.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class CoordinatorController {

    // TableView for events
    @FXML private TableView<Event> eventTable;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, String> dateColumn;
    @FXML private TableColumn<Event, String> locationColumn;

    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    // TableView for tickets
    @FXML private TableView<Ticket> ticketTable;
    @FXML private TableColumn<Ticket, String> ticketIdColumn;
    @FXML private TableColumn<Ticket, String> ticketDescColumn;
    @FXML private TableColumn<Ticket, String> ticketCustomerColumn;

    private ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initier Event TableView
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        eventTable.setItems(eventList);

        // Initier Ticket TableView
        ticketIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ticketDescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        ticketCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        ticketTable.setItems(ticketList);
    }



    @FXML
    private void handleDeleteTicket() {
        // Hent den valgte billet i Ticket TableView
        Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();

        if (selectedTicket == null) {
            // Opret en simpel advarsels-popup uden Alert for at undgå AccessibleRole-problemer
            Stage warningStage = new Stage();
            warningStage.setTitle("Advarsel");
            VBox box = new VBox(10);
            box.setAlignment(javafx.geometry.Pos.CENTER);
            Label warningLabel = new Label("Vælg en billet at slette");
            Button okButton = new Button("OK");
            okButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ev) {
                    warningStage.close();
                }
            });
            box.getChildren().addAll(warningLabel, okButton);
            Scene warningScene = new Scene(box, 250, 100);
            warningStage.setScene(warningScene);
            warningStage.showAndWait();
            return;
        }

        // Opret en simpel bekræftelses-popup uden Alert
        Stage confirmStage = new Stage();
        confirmStage.setTitle("Bekræft sletning");
        VBox confirmBox = new VBox(10);
        confirmBox.setAlignment(javafx.geometry.Pos.CENTER);
        Label confirmLabel = new Label("Er du sikker på at slette denne billet?");
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        Button yesButton = new Button("Ja");
        Button noButton = new Button("Nej");

        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ticketList.remove(selectedTicket);
                confirmStage.close();
            }
        });
        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                confirmStage.close();
            }
        });
        buttonBox.getChildren().addAll(yesButton, noButton);
        confirmBox.getChildren().addAll(confirmLabel, buttonBox);
        Scene confirmScene = new Scene(confirmBox, 300, 150);
        confirmStage.setScene(confirmScene);
        confirmStage.showAndWait();
    }

    @FXML
    private void handleCreateEvent() {
        // Eksisterende kode til oprettelse af event, forbliver uændret:
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
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String title = titleField.getText();
                String location = locationField.getText();
                LocalDate date = datePicker.getValue();
                if (date != null) {
                    LocalDateTime startDate = date.atStartOfDay();
                    Event newEvent = new Event(title, location, "husk tæppe", startDate);
                    eventList.add(newEvent);
                }
                popupStage.close();
            }
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
            okButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ev) {
                    warningStage.close();
                }
            });
            box.getChildren().addAll(warningLabel, okButton);
            Scene warningScene = new Scene(box, 250, 100);
            warningStage.setScene(warningScene);
            warningStage.showAndWait();
            return;
        }

        Stage confirmStage = new Stage();
        confirmStage.setTitle("Bekræft sletning");

        VBox confirmBox = new VBox(10);
        confirmBox.setAlignment(javafx.geometry.Pos.CENTER);
        Label confirmLabel = new Label("Er du sikker på at slette dette event?");
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        Button yesButton = new Button("Ja");
        Button noButton = new Button("Nej");

        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                eventList.remove(selectedEvent);
                confirmStage.close();
            }
        });
        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                confirmStage.close();
            }
        });
        buttonBox.getChildren().addAll(yesButton, noButton);
        confirmBox.getChildren().addAll(confirmLabel, buttonBox);
        Scene confirmScene = new Scene(confirmBox, 300, 150);
        confirmStage.setScene(confirmScene);
        confirmStage.showAndWait();
    }

    @FXML
    private void handleEditEvent() {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            Stage warningStage = new Stage();
            warningStage.setTitle("Advarsel");
            VBox box = new VBox(10);
            box.setAlignment(javafx.geometry.Pos.CENTER);
            Label warningLabel = new Label("Vælg en event at redigere");
            Button okButton = new Button("OK");
            okButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ev) {
                    warningStage.close();
                }
            });
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
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String newTitle = titleField.getText();
                String newLocation = locationField.getText();
                LocalDate date = datePicker.getValue();
                LocalDateTime newStartDate = date.atStartOfDay();
                Event updatedEvent = new Event(newTitle, newLocation, selectedEvent.getNotes(), newStartDate);
                int selectedIndex = eventList.indexOf(selectedEvent);
                if (selectedIndex >= 0) {
                    eventList.set(selectedIndex, updatedEvent);
                }
                editStage.close();
            }
        });
        grid.add(btnOk, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        editStage.setScene(scene);
        editStage.show();
    }

    @FXML
    private void handleEditTicket() {
        // Hent den valgte billet fra ticketTable
        Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
        if (selectedTicket == null) {
            // Opret en simpel advarsels-popup uden Alert
            Stage warningStage = new Stage();
            warningStage.setTitle("Advarsel");
            VBox box = new VBox(10);
            box.setAlignment(javafx.geometry.Pos.CENTER);
            Label warningLabel = new Label("Vælg en billet at redigere");
            Button okButton = new Button("OK");
            okButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ev) {
                    warningStage.close();
                }
            });
            box.getChildren().addAll(warningLabel, okButton);
            Scene warningScene = new Scene(box, 250, 100);
            warningStage.setScene(warningScene);
            warningStage.showAndWait();
            return;
        }

        // Opret en ny Stage til redigeringsvinduet
        Stage editTicketStage = new Stage();
        editTicketStage.setTitle("Rediger billet");

        // Opret et GridPane til layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Opret tekstfelter og præ-populer med den valgte billets data
        TextField descField = new TextField(selectedTicket.getDescription());
        TextField nameField = new TextField(selectedTicket.getCustomerName());
        TextField emailField = new TextField(selectedTicket.getCustomerEmail());

        grid.add(new Label("Billetbeskrivelse:"), 0, 0);
        grid.add(descField, 1, 0);
        grid.add(new Label("Kundenavn:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Kunde-e-mail:"), 0, 2);
        grid.add(emailField, 1, 2);

        // Opret OK-knap for at gemme ændringerne
        Button okButton = new Button("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String newDesc = descField.getText();
                String newName = nameField.getText();
                String newEmail = emailField.getText();
                // Opret en ny Ticket med de opdaterede værdier.
                // Bemærk: Din Ticket-klasse genererer et nyt UUID, så hvis du vil bevare den gamle ID,
                // skal du enten tilføje setters til din Ticket-klasse eller opdatere feltværdierne direkte.
                Ticket updatedTicket = new Ticket(newDesc, newName, newEmail);
                int selectedIndex = ticketList.indexOf(selectedTicket);
                if (selectedIndex >= 0) {
                    ticketList.set(selectedIndex, updatedTicket);
                }
                editTicketStage.close();
            }
        });
        grid.add(okButton, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        editTicketStage.setScene(scene);
        editTicketStage.show();
    }


    @FXML
    private void handleCreateTicket() {
        Stage ticketStage = new Stage();
        ticketStage.setTitle("Opret billet");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField descField = new TextField();
        TextField nameField = new TextField();
        TextField emailField = new TextField();

        grid.add(new Label("Billetbeskrivelse:"), 0, 0);
        grid.add(descField, 1, 0);
        grid.add(new Label("Kundenavn:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Kunde-e-mail:"), 0, 2);
        grid.add(emailField, 1, 2);

        Button okButton = new Button("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String description = descField.getText();
                String customerName = nameField.getText();
                String customerEmail = emailField.getText();
                Ticket newTicket = new Ticket(description, customerName, customerEmail);
                ticketList.add(newTicket);
                System.out.println("Ny billet oprettet med ID: " + newTicket.getId());
                ticketStage.close();
            }
        });
        grid.add(okButton, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        ticketStage.setScene(scene);
        ticketStage.show();


    }
}









