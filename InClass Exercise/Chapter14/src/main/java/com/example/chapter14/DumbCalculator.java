package com.example.chapter14;
//This program displays a calculator, static GUI only.

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DumbCalculator extends Application {
    @Override
    // Override the start method in the Application class
    public void start(Stage primaryStage) {
        HBox pane = new HBox();

        //Labels and textfields for the input


        // Create four buttons


        // Create a scene and place it in the stage
        //Scene scene = new Scene(borderPane, 350, 100);
       // primaryStage.setTitle("Calculator"); // Set the stage title
       // primaryStage.setScene(scene); // Place the scene in the stage
       // primaryStage.show();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
} //end DumbCalculator

