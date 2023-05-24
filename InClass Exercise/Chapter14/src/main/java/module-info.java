module com.example.chapter14 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chapter14 to javafx.fxml;
    exports com.example.chapter14;
}