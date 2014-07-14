package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
* Created by R500 on 9.7.2014 г..
*/
public class NewUserServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");

        String name = request.getParameter("username");
        String pass = request.getParameter("password");

        try {
            UserCreator.CreateUser(name, pass);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
