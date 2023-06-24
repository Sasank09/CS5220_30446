package com.example.hw3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

//GUI is pretty much in place, you will need to add EventHandling.  See the NCAA example for referencce.
public class BabyNames extends Application {

    Statement stmt;

    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        initializeDB();

        BorderPane bp = new BorderPane();

        RadioButton boy = new RadioButton("Boy");
        RadioButton girl = new RadioButton("Girl");
        RadioButton either = new RadioButton("Both");
        ToggleGroup genderGroup = new ToggleGroup();
        boy.setToggleGroup(genderGroup);
        girl.setToggleGroup(genderGroup);
        either.setToggleGroup(genderGroup);

        VBox gender = new VBox(3);
        gender.setPadding(new Insets(5, 5, 5, 5));
        // gender.setStyle("=fx-border-width: 2px; -fx-border-color: green");
        gender.getChildren().addAll(boy, girl, either);
        bp.setTop(gender);

        TextArea taResults = new TextArea();
        ScrollPane sp = new ScrollPane(taResults);
        Label results = new Label("Results");
        BorderPane resultPane = new BorderPane();
        resultPane.setTop(results);
        resultPane.setBottom(sp);
        bp.setBottom(resultPane);
        BorderPane masterPane = new BorderPane();
        masterPane.setCenter(bp);

        ComboBox<String> letter = new ComboBox<>();
        letter.setPrefWidth(200);

        Button search = new Button("Search");
        BorderPane fl = new BorderPane();
        fl.setCenter(letter);
        fl.setTop(new Label("Starts with..."));
        fl.setBottom(search);


        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        ObservableList<String> items = FXCollections.observableArrayList(alphabet);
        letter.getItems().addAll(items);

        BorderPane yearList = new BorderPane();

        ComboBox<String> yearDropDown = new ComboBox<>();
        yearDropDown.setPrefWidth(200);
        String[] yr = {"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010"};
        ObservableList<String> yritems = FXCollections.observableArrayList(yr);
        yearDropDown.getItems().addAll(yr);
        yearList.setCenter(yearDropDown);
        yearList.setTop(new Label("Year or interest"));
        yearList.setBottom(search);

        VBox vb = new VBox();
        vb.getChildren().addAll(fl, yearList);
        bp.setCenter(vb);


        Scene scene = new Scene(masterPane, 200, 400);
        primaryStage.setTitle("Baby Name Widget");
        primaryStage.setScene(scene);
        primaryStage.show();

        //event handling
        EventHandler<ActionEvent> eventHandler = e -> {
            taResults.clear();
            char c = letter.getValue().charAt(0);  //letter selected in drop down box.
            int y = Integer.parseInt(yearDropDown.getValue());
            String queryString = "Select bname FROM babyname Where byear=" + y + " AND bname LIKE '" + c + "%'";

            if (boy.isSelected()) {

                System.out.println("Selected Boy Names and Starts with Letter: " + c);
                char g = 'M';
                queryString += " AND gender='" + g + "'";

                //#1 queryString is set to string that is mysql statement to
                //find all boys in y with names that start with c
            } else if (girl.isSelected()) {

                System.out.println("Selected Girl Names and Starts with letter: " + c);
                char g = 'F';
                queryString += " AND gender='" + g + "'";
                //#2 queryString is set to string that is mysql statement to
                //find all girls in y with names that start with c
            } else {
                //#3 queryString is set to string that is mysql statement to
                //find all boys and girls in y with names that start with c
                System.out.println("Displays all boys and girl names with letter: "+c);
            }


            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(queryString);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            taResults.clear();

            int rcount = 0;
            while (true) {
                try {
                    if (!rs.next()) break;
                    rcount++;
                    String name = null;
                    name = rs.getString(1);
                    taResults.appendText(name + "\n");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            System.out.println("Number of Names(row count) " + rcount); //this is just to check that displayed number of rows returned

        };
        search.setOnAction(eventHandler);  //associate event handling on the search button.


    }//end start


    public void initializeDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/babynames", "root", "root");
        stmt = connection.createStatement();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
