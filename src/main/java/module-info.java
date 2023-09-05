module com.example.javafxdiplomawork {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires plexus.utils;
    requires com.jfoenix;


    opens com.example.javafxdiplomawork to javafx.fxml;
    exports com.example.javafxdiplomawork;
    exports com.example.javafxdiplomawork.database;
    opens com.example.javafxdiplomawork.database to javafx.fxml;
}