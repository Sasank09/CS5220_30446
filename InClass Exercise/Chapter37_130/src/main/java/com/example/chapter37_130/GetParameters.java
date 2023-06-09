package com.example.chapter37_130;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetParameters", value= "/GetParameters")
public class GetParameters extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtain parameters from the client
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String mi = request.getParameter("mi");
        String gender = request.getParameter("gender");
        String major = request.getParameter("major");
        String[] minors = request.getParameterValues("minor");
        String tennis = request.getParameter("tennis");
        String golf = request.getParameter("golf");
        String pingPong = request.getParameter("pingPong");
        String remarks = request.getParameter("remarks");

        out.println("Last Name: <b>" + lastName + "</b> First Name: <b>"
                + firstName + "</b> MI: <b>" + mi + "</b><br>");
        out.println("Gender: <b>" + gender + "</b><br>");
        out.println("Major: <b>" + major + "</b> Minor: <b>");

        if (minors != null)
            for (int i = 0; i < minors.length; i++)
                out.println(minors[i] + " ");

        out.println("</b><br> Tennis: <b>" + tennis + "</b> Golf: <b>" +
                golf + "</b> PingPong: <b>" + pingPong + "</b><br>");
        out.println("Remarks: <b>" + remarks + "</b>");
        out.close(); // Close stream
    }
}
