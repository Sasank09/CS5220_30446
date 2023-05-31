package com.example.ncaa_practice;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NcaaPopulateDB {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/ncaa", "root", "root");

        PreparedStatement insertStatement = connection.
                prepareStatement("insert into ncaa_stats(year, team, conf, games, wins, " +
                        "              postseason, seed)" +
                        " values(?,?,?,?,?,?,?)");
        int games;
        int wins;
        int seed;
        int yr;
        String team, conf, postseason;
        Scanner data;

        data = new Scanner(new File("src/main/java/com/example/ncaa_practice/cbb18_19mini.csv"));
        data.useDelimiter(",");
        //System.out.print("2019" + " has ");
        int count = 0;
        while (data.hasNext()) {
            count++;
            //data.next();//skip rank it is not saved in our table.
            String s = data.nextLine();
            String[] breakUp = s.split(",");

            team = breakUp[0];
            conf = breakUp[1];
            games = Integer.parseInt(breakUp[2]);
            wins = Integer.parseInt(breakUp[3]);
            postseason = breakUp[4];
            seed = Integer.parseInt(breakUp[5]);
            yr = Integer.parseInt(breakUp[6]);


            System.out.println(yr + " " + team + " " + conf + " " + games + " " + wins + " " +
                    postseason + " " + seed);

            insertStatement.setString(1, String.valueOf(yr));
            insertStatement.setString(2, team);
            insertStatement.setString(3, conf);
            insertStatement.setString(4, String.valueOf(games));
            insertStatement.setString(5, String.valueOf(wins));
            insertStatement.setString(6, postseason);
            insertStatement.setString(7, String.valueOf(seed));
            insertStatement.execute();

        }//end while
        System.out.println(count + " tuples.");
        data.close();
    }

}
