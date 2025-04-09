package org.example.mastereventer.gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.mastereventer.Objects.Event;

import java.time.LocalDateTime;

public class AdminController {

    @FXML private TableView<Event> eventTable;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, String> dateColumn;
    @FXML private TableColumn<Event, String> locationColumn;


    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    @FXML
public void initialize() {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    dateColumn.setCellValueFactory( new PropertyValueFactory<>("startDateTime"));
    locationColumn.setCellValueFactory( new PropertyValueFactory<>("location"));

    eventList.add(new Event ("1", "Festival", "25/8", LocalDateTime.of(2025,8,25,15,0)));

    eventTable.setItems(eventList);}


}
