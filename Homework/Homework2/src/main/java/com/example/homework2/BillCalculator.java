package com.example.homework2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class BillCalculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BillCalculator.class.getResource("BillCalculator.fxml"));
        primaryStage.setTitle("Purchase Calculator");
        primaryStage.setScene(fxmlLoader.load());
        primaryStage.show();


    }
}
