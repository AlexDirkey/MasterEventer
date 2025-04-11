module org.example.mastereventer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;
    requires com.google.zxing;
    requires java.desktop;
    requires org.apache.pdfbox;


    opens org.example.mastereventer to javafx.fxml;
    exports org.example.mastereventer;
    exports org.example.mastereventer.gui;
    opens org.example.mastereventer.gui to javafx.fxml;
    opens org.example.mastereventer.Objects to javafx.base;
}