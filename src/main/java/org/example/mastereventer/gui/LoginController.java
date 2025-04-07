package org.example.mastereventer.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Stage stage = (Stage) messageLabel.getScene().getWindow();

        if ("admin".equals(username) && "1234".equals(password)) {
            ViewLoader.loadView("adminview.fxml", "Administrator");
            stage.close();
        } else if ("eko".equals(username) && "4321".equals(password)) {
            ViewLoader.loadView("coordinatorview.fxml", "Event Koordinator");
            stage.close();
        } else {
            messageLabel.setText("Login mislykkedes");
        }
    }
}
