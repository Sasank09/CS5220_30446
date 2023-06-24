<!-- StoreStudent.jsp -->
<%@ page import = "com.example.jsp_demo.Address" %>
<%@ page import="java.sql.SQLException" %>
<jsp:useBean id = "addressId" class = "com.example.jsp_demo.Address"
             scope = "session"></jsp:useBean>
<jsp:useBean id = "storeDataId" class = "com.example.jsp_demo.StoreData"
             scope = "application"></jsp:useBean>

<html>
<body>
<%
  try {
    storeDataId.storeStudent(addressId);
  } catch (SQLException e) {
    e.printStackTrace();
  }

  out.println(addressId.getFirstName() + " " +
          addressId.getLastName() +
          " is now registered in the database");
  out.close(); // Close stream
%>
</body>
</html>