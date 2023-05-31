module com.example.ncaa_practice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ncaa_practice to javafx.fxml;
    exports com.example.ncaa_practice;
}