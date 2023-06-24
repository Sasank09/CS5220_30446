package com.example.chapter34;
import java.sql.*;

public class SimpleJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        //Connect to the database
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/javabook", "root", "root");
        System.out.println("Database connected");

        //Create a statement
        Statement statement = connection.createStatement();

        //Execute a statement
        ResultSet rs = statement.executeQuery
                ("select firstName, mi, lastName from Student where lastName "
                + " = 'Smith'");

        //Iterate through the result set and print student names
        while (rs.next())
            System.out.println(rs.getString(1) + "\t" +
                    rs.getString(2) + "\t" + rs.getString(3));

        String qs1 = "select capital, state from statecapital";
        rs = statement.executeQuery(qs1);
        System.out.println("---------------------------");
        System.out.println("Captial \t State");
        while (rs.next()) {
            System.out.println(rs.getString(1)+"\tz\t"+rs.getString(2));
        }
        //Close the connection
        connection.close();
    }
}
