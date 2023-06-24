package com.example.chapter37_130;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CircleArea", value = "/CircleArea")
public class CircleArea extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr=response.getWriter();
        response.setContentType("text/html");
        try

        {
            double radius=Integer.parseInt(request.getParameter("radius"));

            //Area = PI*radius*radius
            double area = Math.PI * (radius * radius);

            //Circumference = 2*PI*radius
            double circumference= Math.PI * 2*radius;

            pr.println("<body bgcolor='lightgreen'>");
            pr.println("<font face='arial' size='3'> ");
            pr.println("<h3> <font color='Blue'> Display Results </font> </h3>");
            pr.println("<br>");
            pr.println("The area of the circle is " + String.format("%.4f", area )+ ".<br>");
            pr.println("The circumference of the circle is " + String.format("%.4f", circumference) + ".<br>");

            pr.println("<br>");
            pr.println("<h3><font color='Blue'>  End Program </font> </h3>");
            pr.println("</font>");
        }
        catch(Exception e)
        {
            pr.println("Invalid Input value please try again");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}