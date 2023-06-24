package com.example.ncaa_practice;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.*;

public class NCAA_Stats extends Application {
    Statement stmt;
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        intializeDB();

        //pane to hole gender radio buttons, first letter drop down, and results scroll box
        BorderPane bp = new BorderPane();

        //set up the radio buttons and toggle group
        RadioButton y2018 = new RadioButton("2018");
        RadioButton y2019 = new RadioButton("2019");

        // RadioButton either = new RadioButton("Both");
        ToggleGroup yearGroup = new ToggleGroup();
        y2018.setToggleGroup(yearGroup);
        y2019.setToggleGroup(yearGroup);
        //either.setToggleGroup(yearGroup);

        //put radio button in VBox
        VBox yr = new VBox(3);
        yr.setPadding(new Insets(5, 5, 5, 5));
        yr.getChildren().addAll(y2018, y2019);
        bp.setTop(yr);

        //nodes to display the result of the search
        TextArea taResults = new TextArea();
        ScrollPane sp = new ScrollPane(taResults);
        Label results = new Label("Results");

        //combobox for selecting conferences
        ComboBox<String> cbo = new ComboBox<>(); // conferences
        String[] conferences = {"WCC", "ACC", "B10", "SEC", "B12", "BE", "MAC",
                "Amer", "MWC", "P12", "OVC", "WAC", "BSth",
                "BW", "AE", "CAA", "A10", "Ivy", "Horz", "SB",
                "CUSA", "Pat", "BSky", "MVC", "SInd" }; //plus more

        // Add combo box and description pane to the border pane
        BorderPane pane = new BorderPane();
        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select conference: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);
        cbo.setPrefWidth(400);
        cbo.setValue("WCC");
        bp.setCenter(pane);

        ObservableList<String> items =
                FXCollections.observableArrayList(conferences);
        cbo.getItems().addAll(items);


        Button search = new Button("Go");

        Font fontBold = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 20);

        //champ display
        VBox champ = new VBox();
        champ.setAlignment(Pos.CENTER);
        champ.setStyle("-fx-border-color: green");
        Label ch = new Label ("Champion");
        ch.setFont(fontBold);
        champ.getChildren().add(ch);

        //final 4 display
        VBox final4 = new VBox();
        final4.setAlignment(Pos.CENTER);
        final4.setStyle("-fx-border-color: green");
        Label f4 = new Label ("Final 4 Teams");
        f4.setFont(fontBold);
        final4.getChildren().add(f4);

        //
        VBox rslts = new VBox();
        rslts.getChildren().addAll(sp, champ, final4);
        bp.setRight(rslts);
        bp.setBottom(search);


        Scene scene = new Scene(bp, 1000, 400);
        primaryStage.setTitle("NCAA Final 4");
        primaryStage.setScene(scene);
        primaryStage.show();

        EventHandler<ActionEvent> eventHandler = e->{
            taResults.clear();
            //char c = letter.getValue().charAt(0);
            int t = items.indexOf(cbo.getValue());
            String confName = conferences[t];
            RadioButton yBtn = (RadioButton) yearGroup.getSelectedToggle();
            String yeer = yBtn.getText();
            System.out.println("Radio button selected " + yeer);
            int y = Integer.parseInt(yeer);

            String queryString = null;
            String champString = null;
            String final4String = null;


            //get teams in a particular conference
            queryString = "select team from ncaa_stats"+
                    " where conf ='" + confName + "'" +
                    " and " +
                    " = " + y;

            //Get the champ for a particular year
            champString = "select team from ncaa_stats"+
                    "  where postseason = 'Champions'"+
                    "  and year = " + y;

            //Get final 4 teams for a particular year
            final4String = "select team from ncaa_stats"+
                    "  where year = " + y +
                    " and  (postseason = 'F4'"+
                    "  or postseason = '2ND'" +
                    " or postseason = 'Champions')";

            try {
                ResultSet rs = stmt.executeQuery(queryString);
                taResults.clear();

                int rcount = 0;
                while (rs.next()) {
                    rcount++;
                    String name = rs.getString(1);
                    taResults.appendText(name + "\n");

                }
                rs=stmt.executeQuery(champString);
                champ.getChildren().clear();
                champ.getChildren().add(ch);
                if (rs.next())
                    champ.getChildren().add(new Label(rs.getString(1)));

                rs=stmt.executeQuery(final4String);
                final4.getChildren().clear();
                final4.getChildren().add(f4);
                while (rs.next())
                    final4.getChildren().add(new Label(rs.getString(1)));
                System.out.println("row count " + rcount);
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        };


        //set action for search
        search.setOnAction(eventHandler);

    }//end start

    public void intializeDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/ncaa", "root", "root");
        stmt = connection.createStatement();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
