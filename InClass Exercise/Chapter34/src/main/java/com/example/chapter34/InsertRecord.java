package com.example.chapter34;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Statement;

public class InsertRecord extends Application {
    private Button btView = new Button("View");
    private Button btInsert = new Button("Insert");
    private Button btUpdate = new Button("Update");
    private Button btClear = new Button("Clear");
    private TextField tfID = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMi = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfTelephone = new TextField();
    private Label lblStatus = new Label();

    // The Statement for processing queries
    private Statement stmt;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        VBox vBox = new VBox(5);

        HBox hBox1 = new HBox(5);
        hBox1.getChildren().addAll(new Label("ID"), tfID);
        HBox hBox2 = new HBox(5);
        hBox2.getChildren().addAll(new Label("Last Name"), tfLastName,
                new Label("First Name"), tfFirstName, new Label("MI"), tfMi);
        tfLastName.setPrefColumnCount(8);
        tfFirstName.setPrefColumnCount(8);
        tfMi.setPrefColumnCount(1);

        HBox hBox3 = new HBox(5);
        hBox3.getChildren().addAll(new Label("Address"), tfAddress);
        HBox hBox4 = new HBox(5);
        hBox4.getChildren().addAll(new Label("City"), tfCity,
                new Label("State"), tfState);
        HBox hBox5 = new HBox(5);
        hBox5.getChildren().addAll(new Label("Telephone"), tfTelephone);

        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5);

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(btView, btInsert, btUpdate, btClear);
        hBox.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(vBox);
        pane.setTop(lblStatus);
        pane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Staff Manager"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        //initializeDB();

        //btView.setOnAction(e -> view());
        //btInsert.setOnAction(e -> insert());
        //btUpdate.setOnAction(e -> update());
        //btClear.setOnAction(e -> clear());
    }
}
