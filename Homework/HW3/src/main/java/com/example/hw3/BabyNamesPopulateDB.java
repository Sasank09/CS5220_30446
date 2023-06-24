package com.example.hw3;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

//DON'T FORGET TO ADD the mysql jar file to your project. And delete mine.

public class BabyNamesPopulateDB {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/babynames", "root", "root");

 		//add the prepared statement that will insert tuple into babyname

        PreparedStatement insertStatement = connection.
                prepareStatement("insert into babyname(byear, bname, gender, bcount) values(?,?,?,?)");

		//declare variables
        int boyNum, girlNum;
        String boyName, girlName;
        Scanner data;

		//loop to read files
        for (int year = 2001; year <=2010; year++) {
            //go here to see how a single year of date is formatted
            //http://liveexample.pearsoncmg.com/data/babynamesranking2001.txt
            //there is a separate textfile for each year, 2001 thru 2010
            data = new Scanner(new URL(
                    "http://liveexample.pearsoncmg.com/data/babynamesranking" + year + ".txt").openStream());
            System.out.print(year + " has \n----------------\n");
            int count = 0;
            Scanner scanner = new Scanner(System.in);
            //loop to read process a single year and insert into db
            while (data.hasNext()){
                count++;
                data.next(); // Skip rank. It is not saved in the table.

                boyName = String.valueOf(data.next());
                boyNum = Integer.valueOf(data.next());

                insertStatement.setInt(1, Integer.valueOf(year));
                insertStatement.setString(2, boyName);
                insertStatement.setString(3, "M");
                insertStatement.setInt(4, boyNum);
                insertStatement.execute();

                girlName = String.valueOf(data.next());
                girlNum = Integer.valueOf(data.next());

                insertStatement.setInt(1, Integer.valueOf(year));
                insertStatement.setString(2, girlName);
                insertStatement.setString(3, "F");
                insertStatement.setInt(4, girlNum);
                insertStatement.execute();

                //read boyName
                //read boyNum
                //read girlName
                //read girlNum

                //System.out.println(boyName + " "+ boyNum + " " + girlName + " "+ girlNum );
                // uncomment above to see if file was read correctly before writing to database.



                //insert boy variables into prepared statement and execute

                //insert girl variables into prepared statement and execute


            }
            System.out.println("Records in year "+year+" has count= " + count);

        }



    }

}
