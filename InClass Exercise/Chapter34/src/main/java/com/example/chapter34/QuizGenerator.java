/*
 Statement for creating a table
 create table Quiz(
   questionId int,
   question varchar(4000),
   choicea varchar(1000),
   choiceb varchar(1000),
   choicec varchar(1000),
   choiced varchar(1000),
   answer varchar(5));
*/
package com.example.chapter34;

import java.io.*;
import java.util.*;
import java.sql.*;

public class QuizGenerator {
    private ArrayList<Quiz> chapters = new ArrayList<Quiz>();
    private PreparedStatement pstmt1;

    static class Quiz {
        String question = "";
        String choicea = "";
        String choiceb = "";
        String choicec = "";
        String choiced = "";
        String answer;
        String hint;
    }

    public static void main(String[] args) {
        new QuizGenerator();
    }

    /**
     * Initialize global variables
     */
    public QuizGenerator() {
        try {
            readTest(chapters);

            //initializeJdbc();

            int questionNo = 1;
            for (Quiz question : chapters) {
                //storeQuiz(questionNo++, question);
                System.out.println(question.question);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void readTest(List<Quiz> testForAChapter) throws
            Exception {
        // Create a buffered reader for reading questions from a file
        BufferedReader in = new BufferedReader(new FileReader(
                "src/main/java/com/example/chapter34/Quiz.txt"));
                //"src/Quiz.txt"));

        // Quiz count
        int questionCount = 0;
        boolean beginningOfQuiz = true; // for the first one

        // Text line from the question file
        String line = "";
        Quiz question = null;

        // Read and process each line from the text file
        //loop:
        while ((line = in.readLine()) != null) {
            // Process a blank line in the text file
            if (line.length() < 1) {
                continue;
            }

            // Determine question statement and multiple choices
            if (line.charAt(0) == 'a' && line.charAt(1) == '.') {
                question.choicea = line.substring(2);
            } else if (line.charAt(0) == 'b' && line.charAt(1) == '.') {
                question.choiceb = line.substring(2);
            } else if (line.charAt(0) == 'c' && line.charAt(1) == '.') {
                question.choicec = line.substring(2);
            } else if (line.charAt(0) == 'd' && line.charAt(1) == '.') {
                question.choiced = line.substring(2);
            } else if (line.matches("(\\d)+\\..*")) { // Start a new question
                beginningOfQuiz = true;
                questionCount++; // Increase question count
                question = new Quiz(); // Create a new test
                testForAChapter.add(question); // Add to the list
                question.question += line;
            } else if (line.toUpperCase().indexOf("ANSWER") == 0) { // End of question section
                // Extract answer and explanation
                StringTokenizer st = new StringTokenizer(line.substring(7),
                        ".\n\r\t ");

                question.answer = st.nextToken().toUpperCase();

                if (st.hasMoreTokens()) {
                    question.hint = st.nextToken("\n\r");
                }
            } else if (line.charAt(0) == ' ') { // Process spaces before line
                String spaces = "";
                for (int j = 0;
                     ((j < line.length()) && (line.charAt(j) == ' ')); j++) {
                    spaces += "&nbsp";
                }
                question.question += spaces;
                question.question += line;
            } else {
                if (beginningOfQuiz && Character.isDigit(line.charAt(0)) &&
                        line.charAt(1) == '.') {
                    question.question += line.substring(2);
                    beginningOfQuiz = false;
                } else if (beginningOfQuiz && Character.isDigit(line.charAt(0)) &&
                        Character.isDigit(line.charAt(1))
                        && line.charAt(2) == '.') {
                    question.question += line.substring(3);
                    beginningOfQuiz = false;
                } else {
                    question.question += line;
                }
            }
        }

        // Close the file
        in.close();
    }

    /**
     * Initialize database connection
     */
    private void initializeJdbc() {


    }

    private void storeQuiz(int questionNo,
                           Quiz question){

    }


}
