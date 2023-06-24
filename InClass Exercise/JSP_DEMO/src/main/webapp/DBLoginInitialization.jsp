<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- DBLoginInitialization.jsp -->
<%@ page import = "com.example.jsp_demo.DBBean" %>
<jsp:useBean id = "dBBeanId" scope = "session"
             class = "com.example.jsp_demo.DBBean">
</jsp:useBean>
<jsp:setProperty name = "dBBeanId" property = "*" />
<html>
<head>
    <title>DBLoginInitialization</title>
</head>
<body>

<%-- Connect to the database --%>
<% dBBeanId.initializeJdbc(); %>

<% if (dBBeanId.getConnection() == null) { %>
Error: Login failed. Try again.
<% }
else {%>
<jsp:forward page = "Table.jsp" />
<% } %>
</body>
</html>