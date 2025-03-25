module org.example.mastereventer {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.mastereventer to javafx.fxml;
    exports org.example.mastereventer;
}