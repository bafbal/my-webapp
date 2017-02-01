package com.mywebapp.hello;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {

    public void init() throws ServletException {
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sql = "SELECT id, nev, kor FROM csalad";

        ConnenctionManager connenctionManager = new ConnenctionManager();

        connenctionManager.createConnection(response);
        connenctionManager.createStatement(response);
        connenctionManager.createResultSet(sql, response);

        //STEP 5: Extract data from result set

        try {
            while (connenctionManager.getRs().next()) {
                //Retrieve by column name
                int id = connenctionManager.getRs().getInt("id");
                String nev = connenctionManager.getRs().getString("nev");
                int kor = connenctionManager.getRs().getInt("kor");

                //Display values
                out.print("ID: " + id);
                out.print(", Nev: " + nev);
                out.print(", Kor: " + kor + "<br>");
            }
        } catch (SQLException e) {
            out.println("Retrieving resultset gone wrong...<br>");
            out.println(e+"<br>");
            e.printStackTrace();
        }

        out.print("<br>");
        HtmlBackButton.print(response);
        connenctionManager.closeEverything();
        System.out.println("Goodbye!");
    }

    public void destroy() {
        // do nothing.
    }
}
