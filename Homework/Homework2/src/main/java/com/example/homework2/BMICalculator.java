/**
 *Create a GUI program that will prompt the
 * user for name, age, height, and weight. When the user clicks the Calculate button the
 * textboxes for BMI and Status are populated. Refer to image below. Class BMI.java has
 * been provided. Refer to the LoanCalculator.java (Ch 15) example as a starting point.
 * BMI.java has been provided.
 * Suggested steps:
 * 1. Run LoanCalculator.java and review the code.
 * 2. Add BMI.java to your project
 * 3. Create BMICalculator.java as a JavaFX application
 * 4. Create the GUI elements as static, labels, textboxes, button
 * 5. Wire up the button so that when clicked the age, height, and weight textfields are
 * read
 * 6. Create BMI object from the numeric values.
 * 7. Populate BMI textfield and Status textfield by invoking the appropriate methods
 * on the BMI object.
 * @Author: Venkata Lakshmi Sasank Tipparaju(700738838)
 */
package com.example.homework2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    // Override the start method in the Application class
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
        try{
        // Get values from text fields
        String name = tfName.getText();
        double height = Double.parseDouble(tfHeight.getText());
        double weight = Double.parseDouble(tfWeight.getText());
        int age = Integer.parseInt(tfAge.getText());

        // Create a BMI object.
        BMI bmiObject = new BMI(name,age,weight,height);

        // Display BMI and Status
        tfBMI.setText(String.format("%.2f", bmiObject.getBMI()));
        tfStatus.setText(bmiObject.getStatus());
        }
        catch (Exception ex){
            tfBMI.clear();
            tfStatus.clear();
            displayErrorMessage();
        }
    }


    @FXML
    void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Please check your input!!");
        alert.showAndWait();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

}

