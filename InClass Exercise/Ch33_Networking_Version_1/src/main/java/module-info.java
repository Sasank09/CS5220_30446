module com.example.ch33_networking {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ch33_networking to javafx.fxml;
    exports com.example.ch33_networking;
}