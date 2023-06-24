package com.example.homework4;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "BabyNames", value = "/BabyNames")
public class BabyNames extends HttpServlet {
    // Use statement to query the database
    private Statement stmt;

    /**
     * Initialize variables
     */
    public void init() throws ServletException {
        initializeJdbc();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtain parameters from the client
        String gender = request.getParameter("gender");
        String firstLetter = request.getParameter("firstLetter");
        Integer year = Integer.valueOf(request.getParameter("year"));


        String queryString = "Select bname FROM babyname Where bname LIKE '" + firstLetter + "%'";

        if(year!=0){
            queryString+=" AND byear="+ year;
        }
        if(gender!=""){
            queryString+= " AND gender= '"+gender+"'";
        }
        ResultSet rs = null;
        try {
            queryString+= " ORDER By bname";
            out.println("BabyNames are:");
            rs = stmt.executeQuery(queryString);
            int rcount = 0;
            out.println("<ol>");
            while (true) {
                if (!rs.next()) break;
                rcount++;
                String name = null;
                name = rs.getString(1);
                out.println("<li>"+name+"</li>");
            }
            out.println("</ol>");
            out.println("Number of Names(row count) returned: " + rcount); //this is just to check that displayed number of rows returned

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        out.close(); // Close stream
    }


    private void initializeJdbc() {
        try {
            System.out.println("In initializeJdbc");
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/babynames", "root", "root");

            // Create a Statement
            stmt = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}