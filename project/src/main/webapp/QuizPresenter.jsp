<%--
  Created by IntelliJ IDEA.
  User: thipp
  Date: 6/23/2023
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="randomIntegers" scope="session" class="com.sasank.project.RandomIntegerGeneration"/>
<jsp:useBean id="stateCapitalDB" scope="session" class="com.sasank.project.StateCapitalDB"/>


<%-- Get the selected quiz from the query string --%>
<% String quiz = request.getParameter("quiz"); %>

<%-- Generate random numbers and symbol for different quizzes --%>
<%

  String symbol = "";
  int noOfQuestions = 10;

  if (quiz.equals("Addition")) {
    randomIntegers.refresh(1, 100, quiz);
    symbol = "+";
  } else if (quiz.equals("Subtraction")) {
    randomIntegers.refresh(1, 100, quiz);
    symbol = "-";
  } else if (quiz.equals("Multiplication")) {
    randomIntegers.refresh(1, 25, quiz);
    symbol = "&times;";
  } else if (quiz.equals("Square")) {
    for(int i=0; i<10; i++) {
      randomIntegers.number1[i] = i+1;
    }
    symbol = "<sup>2</sup>";
  } else if (quiz.equals("StateCapital")) {
    noOfQuestions = 5;
    stateCapitalDB.initializeDbData();
  }


%>

<html>
<head>
  <title>
    <%= quiz %> Quiz
  </title>
  <style>
    table {
      border-collapse: collapse;
    }
    th, td {
      border: 1px dashed black;
      padding: 5px;
    }
  </style>
</head>
<body>
<h2><%= quiz %> Quiz</h2>
<form method="post" action="QuizResult.jsp">
  <table>
    <tr>
      <th>S.No</th>

      <% if (quiz.equals("Addition") ||quiz.equals("Subtraction") || quiz.equals("Multiplication"))  { %>
        <th colspan="3">Quiz Question</th>
      <% } else{ %>
      <th>Quiz Question</th>
      <% } %>
      <th>Result</th>
    </tr>
    <% int serialNumber = 1;
      for (int i = 0; i < noOfQuestions; i++) { %>
    <tr>
      <td><%= serialNumber++ %></td>
      <% if (!quiz.equals("StateCapital")) { %>
      <td><%= randomIntegers.number1[i] %>
        <% if (quiz.equals("Square")) { %>
        <sup>2</sup>
        <% } %>
      </td>
      <% if (!quiz.equals("Square")) { %>
      <td><%= symbol %></td>
      <td><%= randomIntegers.number2[i] %></td>
      <% } %>
      <td><input name="<%= "result" + i %>" size="2" type="number" title="Please enter a valid number" required/></td>
      <% } else if(quiz.equals("StateCapital")) { %>
      <td>What is the Capital of <%= stateCapitalDB.state[i] %> ? </td>
      <td><input name="<%= "result" + i %>" size="25" type="text" required/></td>
      <% } %>
    </tr>
    <% } %>
  </table>
  <br/>
  <%-- Add a hidden input field to pass the "quiz" parameter --%>
  <input type="hidden" name="quiz" value="<%= quiz %>">
  <input type="submit" name="Submit" value="Submit"/> Click the browser's Refresh button to get a new quiz
</form>
</body>
</html>
