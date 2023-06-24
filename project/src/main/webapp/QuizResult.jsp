<%--
  Created by IntelliJ IDEA.
  User: thipp
  Date: 6/23/2023
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="randomIntegers" scope="session" class="com.sasank.project.RandomIntegerGeneration"/>
<jsp:useBean id="stateCapitalDB" scope="session" class="com.sasank.project.StateCapitalDB"/>

<%-- Retrieve the "quiz" parameter --%>
<% String quiz = request.getParameter("quiz"); %>

<%-- Get the submitted answers from the request parameters --%>
<% int noOfQuestions = 1;
    if (quiz.equals("StateCapital")) {
        noOfQuestions = 5;
    } else {
        noOfQuestions = 10;
    }
    String[] submittedAnswers = new String[noOfQuestions];
    for (int i = 0; i < noOfQuestions; i++) {
        submittedAnswers[i] = String.valueOf(request.getParameter("result" + i).trim());
    }
%>


<%-- Calculate the results and store the correct answers --%>
<% int correctCount = 0;
    boolean[] isCorrectArray = new boolean[noOfQuestions];
    String[] correctAnswers = new String[noOfQuestions];
    for (int i = 0; i < noOfQuestions; i++) {
        String result = submittedAnswers[i];
        String expected;
        if (quiz.equals("Addition")) {
            expected = Integer.toString(randomIntegers.number1[i] + randomIntegers.number2[i]);
        } else if (quiz.equals("Subtraction")) {
            expected = Integer.toString(randomIntegers.number1[i] - randomIntegers.number2[i]);
        } else if (quiz.equals("Multiplication")) {
            expected = Integer.toString(randomIntegers.number1[i] * randomIntegers.number2[i]);
        } else if (quiz.equals("Square")) {
            expected = Integer.toString(randomIntegers.number1[i] * randomIntegers.number1[i]);
        } else if (quiz.equals("StateCapital")) {
            expected = stateCapitalDB.capital[i].trim();
        } else {
            expected = "";
        }
        isCorrectArray[i] = result.compareToIgnoreCase(expected) == 0 ? true : false;
        if (isCorrectArray[i]) {
            correctCount++;
        }
        correctAnswers[i] = expected;

    } %>

<html>
<head>
    <title>Quiz Result</title>
    <style>
        .column {
            float: left;
            width: 50%;
        }

        table {
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
        }

        .correct {
            background-color: #b3ffb3;
        }

        .wrong {
            background-color: #ff9999;
        }
    </style>
</head>
<body>
<h1>Quiz Result</h1>
<div>
    <div class="column">
        <table>
            <tr>
                <th>Question</th>
                <th>Your Answer</th>
                <th>Result</th>
                <th>Correct Answer</th>
            </tr>
            <% for (int i = 0; i < noOfQuestions; i++) {
                String answerClass = isCorrectArray[i] ? "correct" : "wrong";
            %>
            <tr>
                <% if (quiz.equals("StateCapital")) { %>
                <td>The Capital of State <%= stateCapitalDB.state[i]%>
                </td>
                <% } else { %>
                <td><%= randomIntegers.number1[i] %>
                    <% if (quiz.equals("Addition")) { %>
                    + <%= randomIntegers.number2[i] %>
                    <% } else if (quiz.equals("Subtraction")) { %>
                    - <%= randomIntegers.number2[i] %>
                    <% } else if (quiz.equals("Multiplication")) { %>
                    &times; <%= randomIntegers.number2[i] %>
                    <% } else if (quiz.equals("Square")) { %>
                    <sup>2</sup>
                    <% } %>
                </td>
                <% } %>
                <td><%= submittedAnswers[i] %>
                </td>
                <td class="<%= answerClass %>">
                    <% if (isCorrectArray[i]) { %>
                    Correct
                    <% } else { %>
                    Wrong
                    <% } %>
                </td>
                <td><% if (quiz.equals("StateCapital")) { %> The Capital is <% } %> <%= correctAnswers[i] %>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
    <div class="column">
        <h2>Results Summary</h2>
        <p>Total Questions: <%= noOfQuestions %>
        </p>
        <p>Correct Answers: <%= correctCount %>
        </p>
        <p>Incorrect Answers: <%= noOfQuestions - correctCount %>
        </p>

        <%-- Check if all answers are correct to display the trophy --%>
        <% if (correctCount == noOfQuestions) { %>
        <img src="https://media.giphy.com/media/Lxh8lpst6AQDf38FfQ/giphy.gif" alt="Trophy" width="100" height="100">
        <p>Congratulations! You answered all questions correctly!</p>
        <% } else { %>
        <img src=" https://media.giphy.com/media/OzsUonxE7kkqNxAvwL/giphy.gif" alt="Trophy" width="100" height="100">
        <% } %>
        <a href="index.jsp">Back to Quiz Selections Menu</a>
    </div>
</div>
</body>
</html>
