module org.example.mastereventer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.mastereventer to javafx.fxml;
    exports org.example.mastereventer;
    exports org.example.mastereventer.gui;
    opens org.example.mastereventer.gui to javafx.fxml;
}