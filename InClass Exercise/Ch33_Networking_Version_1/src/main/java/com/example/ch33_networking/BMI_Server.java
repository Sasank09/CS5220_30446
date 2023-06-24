package com.example.ch33_networking;

import java.io.*;
import java.net.*;
import java.util.*;

//import com.sun.security.ntlm.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/*  We will put these lines in our program, just saving us some typing
Platform.runLater(() ->
               ta.appendText("BMI server started at " + new Date() + "\n"));

Platform.runLater(() ->
               ta.appendText("Connected to client  at " + new Date() + "\n"));
*/

public class BMI_Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 200, 200);
    primaryStage.setTitle("BMI Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    //create a thread that connects to the client
      new Thread(() -> {
          try {
              connectToClient();
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }).start();

  }

  public void connectToClient() throws IOException {
      try {
          //create a server socket  #1
          ServerSocket serverSocket = new ServerSocket(8000);

          Platform.runLater(() ->
                  ta.appendText("BMI server started at " + new Date() + "\n"));

          //#2 create socket to listen to requests
          Socket connectToClient = serverSocket.accept();
          Platform.runLater(() ->
                  ta.appendText("Connected to client  at " + new Date() + "\n"));

          // #3 Create input and out streams
          DataInputStream isFromClient = new DataInputStream(connectToClient.getInputStream());
          DataOutputStream osToClient = new DataOutputStream(connectToClient.getOutputStream());


          //continuously serve the client
          while (true) {
              //receive weight from the client
              double weight = isFromClient.readDouble();

              //receive heigth from client
              double height = isFromClient.readDouble();

              BMI bmi = new BMI("", weight, height);
              String status = "BMI is " + bmi.getBMI() + ". " + bmi.getStatus();

              //send results to the client
              osToClient.writeUTF(status);

              Platform.runLater(() -> {
                  ta.appendText("Weight: " + weight +
                          "\nHeight" + height + "\n");
                  ta.appendText(status + "\n");
              });
          }
      } catch (IOException e) {
          System.out.println(e);
      }
  }
  }  //end connectToClient



  /**
   * The pulic main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
 /* public static void main(String[] args) {
    launch(args);
  }
}
*/

