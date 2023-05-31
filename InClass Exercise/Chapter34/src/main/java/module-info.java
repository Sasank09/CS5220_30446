module com.example.chapter34 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.chapter34 to javafx.fxml;
    exports com.example.chapter34;
}