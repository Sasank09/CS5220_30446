package com.example.chapter14;
//Practice with JavaFX
//SnowPerson creates a stack of circles. The output looks
//a bit like a snowperson.


//See ShowCircle.java for hints.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class SnowPerson extends Application{
	@Override
	public void start(Stage primaryStage){

		//Declare a Pane object and call the constructor
        Pane pane = new Pane();

		//Bottom circle 100, 125 radius 20 filled white, outline in red

		//circle at 100, 90 with radius 15


		//cirlce at 100, 65 with radius 100



        //Add circles to pane


		//Create scene, add pane, and then add scene to stage
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stack of Circles");
        primaryStage.show();


	}





  public static void main(String[] args) {
    launch(args);
  }
}
