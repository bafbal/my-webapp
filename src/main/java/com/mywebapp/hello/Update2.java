package com.mywebapp.hello;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mywebapp.hello.HtmlBackButton;

public class Update2 extends HttpServlet {
    private String message;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/csaladtagok";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "live2fly";

    public void init() throws ServletException
    {
        // Do required initialization
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, nev, kor FROM csalad WHERE nev=\"" + request.getParameter("nev") + "\"";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set

            String nev = "";
            int kor = 0;

            while (rs.next()) {
                //Retrieve by column name
                nev = rs.getString("nev");
                kor = rs.getInt("kor");

                //Display values
//                out.print("Nev: " + nev);
//                out.print(", Kor: " + kor + "<br>");
            }

            out.print("<html>\n" +
                    "   <body>\n" +
                    "<h1>UPDATE2</h1>\n" +
                    "      <form action=\"update\" method=\"GET\">\n" +
                    "         Regi nev: <input type=\"text\" name=\"nev1\" value=\"" + nev + "\">\n" +
                    "         Uj nev: <input type=\"text\" name=\"nev2\" value=\"" + nev +"\">\n" +
                    "<br>\n" +
                    "        Regi kor: \n" +
                    "         Uj kor: <input type=\"text\" name=\"kor\" value=" + kor + ">\n" +
                    "         <input type=\"submit\" value=\"Update\" />\n" +
                    "      </form>\n" +
                    "<br>\n" +
                    "      <a href=\"index.jsp\">\n" +
                    "   <input type=\"button\" value=\"Back\" />\n" +
                    "   </a>\n" +
                    "   </body>\n" +
                    "</html>");

            out.print("<br>");
            HtmlBackButton.print(response);
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");


//        // Set response content type
//        response.setContentType("text/html");
//
//        // Actual logic goes here.
//        PrintWriter out = response.getWriter();
//        out.println("<h1>" + message + "</h1>");
    }

    public void destroy()
    {
        // do nothing.
    }
}
