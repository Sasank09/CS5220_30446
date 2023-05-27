module com.example.homework2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                        requires org.kordamp.bootstrapfx.core;
            
    opens com.example.homework2 to javafx.fxml;
    exports com.example.homework2;
}