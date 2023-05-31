package com.example.homework2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BMICalculator extends Application {
    private static TextField tfName = new TextField();
    private static TextField tfAge = new TextField();
    private static TextField tfHeight = new TextField();
    private static TextField tfWeight = new TextField();
    private static TextField tfBMI = new TextField();
    private static TextField tfStatus = new TextField();
    private static Button btCalculate = new Button("Calculate");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(tfName, 1, 0);
        gridPane.add(new Label("Age:"), 0, 1);
        gridPane.add(tfAge, 1, 1);
        gridPane.add(new Label("Height:"), 0, 2);
        gridPane.add(tfHeight, 1, 2);
        gridPane.add(new Label("Weight:"), 0, 3);
        gridPane.add(tfWeight, 1, 3);
        gridPane.add(new Label("BMI:"), 0, 4);
        gridPane.add(tfBMI, 1, 4);
        gridPane.add(new Label("Status:"), 0, 5);
        gridPane.add(tfStatus, 1, 5);
        gridPane.add(btCalculate, 2, 5);

        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        tfName.setAlignment(Pos.BOTTOM_RIGHT);
        tfAge.setAlignment(Pos.BOTTOM_RIGHT);
        tfHeight.setAlignment(Pos.BOTTOM_RIGHT);
        tfWeight.setAlignment(Pos.BOTTOM_RIGHT);
        tfBMI.setAlignment(Pos.BOTTOM_RIGHT);
        tfStatus.setAlignment(Pos.BOTTOM_RIGHT);
        tfBMI.setEditable(false);
        tfStatus.setEditable(false);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);

        // Process events
        btCalculate.setOnAction(e -> calculateBMI());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("BMI Calculator"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void calculateBMI() {
        // Get values from text fields
        String name = tfName.getText();
        double height = Double.parseDouble(tfHeight.getText());
        double weight = Double.parseDouble(tfWeight.getText());
        int age = Integer.parseInt(tfAge.getText());

        // Create a loan object. Loan defined in Listing 10.2
        BMI bmiObject = new BMI(name,age,weight,height);

        // Display monthly payment and total payment
        tfBMI.setText(String.format("%.2f",
                bmiObject.getBMI()));
        tfStatus.setText(bmiObject.getStatus());
    }

    public static void main(String[] args) {
        launch(args);
    }

}

