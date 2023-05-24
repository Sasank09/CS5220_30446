module com.example.ch32_multithreading_parallel_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ch32_multithreading_parallel_javafx to javafx.fxml;
    exports com.example.ch32_multithreading_parallel_javafx;
}