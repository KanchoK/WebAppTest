package com.web;

import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by R500 on 9.7.2014 г..
 */
public class FirstServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");
        String pass = request.getParameter("password");

//        JSONObject obj = new JSONObject();
//        obj.put("ID","name");
//        out.write(obj.toString());

        try {
            if (LoginCheck.validate(name, pass))
            {
                RequestDispatcher rd = request.getRequestDispatcher("servlet2");
                rd.forward(request, response);
            }
            else
            {
                out.print("Wrong username or password");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.include(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.close();
    }
}
