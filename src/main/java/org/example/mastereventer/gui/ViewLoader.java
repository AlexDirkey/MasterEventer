package org.example.mastereventer.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewLoader {
    public static void loadView(String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(ViewLoader.class.getResource("/org/example/mastereventer/" + fxmlFile));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
