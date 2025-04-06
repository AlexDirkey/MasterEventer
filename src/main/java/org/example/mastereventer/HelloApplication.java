package org.example.mastereventer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Parent pane;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label titleLabel = new Label("Login");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Brugernavn");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Adgangskode");

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        VBox vbox = new VBox (10, titleLabel, usernameField, passwordField, loginButton, messageLabel);
        vbox.setStyle ("-fx-padding: 20; -fx-alignment: center;");

        Scene loginScene = new Scene(vbox, 300, 250);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username. equals("admin") && password.equals("1234")) {
                messageLabel.setText ("Login OK");
                openNewWindow();
                primaryStage.close();
            } else {
                messageLabel.setText ("Login Failed :(");
            }
        });

       // Label label = new Label("Login");
       // StackPane root = new StackPane(label);
       // Scene scene = new Scene(root,400,300);

        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

        private void openNewWindow() {
        Stage newStage = new Stage();
        Label welcomeLabel = new Label("Welcome");
        StackPane root = new StackPane(welcomeLabel);
        Scene scene = new Scene (root,300,200);

        newStage.setTitle("Hovedvindue");
        newStage.setScene(scene);
        newStage.show();
        }
    public static void main(String[] args) {
        launch(args);
    }
}