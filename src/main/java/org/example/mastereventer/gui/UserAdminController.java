package org.example.mastereventer.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.mastereventer.Objects.User;
import org.example.mastereventer.bll.UserManager;

import java.sql.SQLException;

public class UserAdminController {

    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, Integer> idColumn;
    @FXML private TableColumn<User, String> usernameColumn;
    @FXML private TableColumn<User, String> roleColumn;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    private UserManager userManager = new UserManager();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        try {
            userList.clear();
            userList.addAll(userManager.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userTable.setItems(userList);
    }

    @FXML
    private void handleCreateUser() {
        Stage popup = new Stage();
        popup.setTitle("Opret ny bruger");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        TextField roleField = new TextField();

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("Role:"), 0, 2);
        grid.add(roleField, 1, 2);

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();

            User newUser = new User(username, password, role);
            try {
                userManager.createUser(newUser);
                userList.clear();
                userList.addAll(userManager.getAllUsers());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            popup.close();
        });
        grid.add(okButton, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        popup.setScene(scene);
        popup.show();
    }

    @FXML
    private void handleEditUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            // Vis advarsel
            Stage warning = new Stage();
            warning.setTitle("Advarsel");
            VBox box = new VBox(10);
            box.setAlignment(javafx.geometry.Pos.CENTER);
            box.getChildren().add(new Label("Vælg en bruger at redigere"));
            Scene scene = new Scene(box, 250, 100);
            warning.setScene(scene);
            warning.showAndWait();
            return;
        }

        Stage popup = new Stage();
        popup.setTitle("Rediger bruger");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField usernameField = new TextField(selectedUser.getUsername());
        TextField passwordField = new TextField(selectedUser.getPassword());
        TextField roleField = new TextField(selectedUser.getRole());

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("Role:"), 0, 2);
        grid.add(roleField, 1, 2);

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            selectedUser.setUsername(usernameField.getText());
            selectedUser.setPassword(passwordField.getText());
            selectedUser.setRole(roleField.getText());
            try {
                userManager.updateUser(selectedUser);
                userList.clear();
                userList.addAll(userManager.getAllUsers());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            popup.close();
        });
        grid.add(okButton, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        popup.setScene(scene);
        popup.show();
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Stage warning = new Stage();
            warning.setTitle("Advarsel");
            VBox box = new VBox(10);
            box.setAlignment(javafx.geometry.Pos.CENTER);
            box.getChildren().add(new Label("Vælg en bruger at slette"));
            Scene scene = new Scene(box, 250, 100);
            warning.setScene(scene);
            warning.showAndWait();
            return;
        }
        try {
            userManager.deleteUser(selectedUser.getId());
            userList.remove(selectedUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
