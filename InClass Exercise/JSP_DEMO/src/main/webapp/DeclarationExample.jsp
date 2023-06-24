<%--
  Created by IntelliJ IDEA.
  User: thipp
  Date: 6/12/2023
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Declaration Example</title>
</head>
<body>
<%!
  String makeItLower(String str){
    return str.toLowerCase();
  }

%>
Lower case of "HELLO World!": <%= makeItLower("HELLO World!")%>
</body>
</html>

