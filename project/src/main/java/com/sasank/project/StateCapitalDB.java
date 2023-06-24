package com.sasank.project;

import java.sql.*;

public class StateCapitalDB {

    public String[] state = new String[5];
    public String[] capital = new String[5];

    public StateCapitalDB(){}

    public  void initializeDbData() throws ClassNotFoundException, SQLException {
        //Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        //Connect to the database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "root", "root");
        System.out.println("Database connected");

        //Create a statement
        Statement statement = connection.createStatement();

        String qs1 = "SELECT state, capital FROM statecapital ORDER BY RAND() LIMIT 5";
        ResultSet rs  = statement.executeQuery(qs1);
        System.out.println("---------------------------");
        System.out.println("Captial \t State");
        int i =0;
        while (rs.next()) {
            state[i] = rs.getString(1);
            capital[i] = rs.getString(2);
            System.out.println(rs.getString(1)+capital[i]);
            i++;
        }
        //Close the connection
        connection.close();
    }

}

