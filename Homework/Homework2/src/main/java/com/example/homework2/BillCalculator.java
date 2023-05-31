/**
 * Create a JavaFX applictation to calculate a total bill. Your program will display amount due after
 * the user enters data into the textfields Item Price and Quantity purchased and click the
 * Calculate button.
 *
 * @Author: Venkata Lakshmi Sasank Tipparaju(700738838)
 */
package com.example.homework2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class BillCalculator extends Application {

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    // Override the start method in the Application class
    @Override
    public void start(Stage primaryStage) throws IOException {
        //utilized .fxml file to set the stage properties for this problem
        FXMLLoader fxmlLoader = new FXMLLoader(BillCalculator.class.getResource("BillCalculator.fxml"));
        primaryStage.setTitle("Purchase Calculator");
        primaryStage.setScene(fxmlLoader.load());
        primaryStage.show();

    }
}
