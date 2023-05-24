package com.example.chapter14;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //set up the Image objects
        String path = System.getProperty("user.dir") + "/image/x.gif";
        Image imageX = new Image(path);
        path = System.getProperty("user.dir") + "/image/o.gif";
        Image imageO = new Image(path);

        //gRIDPANE TO hold x o or blank

        //populate board


        Pane pane = new HBox(10);
        pane.getChildren().addAll(new ImageView(imageX));

        //Add to scene and stage
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
