package com.mywebapp.hello;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ConnenctionManager {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/csaladtagok";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "live2fly";

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public void createResultSet(String sql, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
       // ResultSet rs = null;
        try {
           rs = stmt.executeQuery(sql);
            out.print("resultset created");
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
        //return rs;
    }

    public void createConnection(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            out.println("Connecting to database...<br>");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            out.print("conection created<br>");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        //return conn;
    }

    public void createStatement (HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("Creating statement...<br>");
        try {
            stmt = conn.createStatement();
            out.print("statement created<br>");

        } catch (SQLException e) {
            out.print("creating statement gone wrong <br>");
            out.print(e);
            e.printStackTrace();
        }
        //return stmt;
    }

    public void closeEverything(){
        try {
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
    }

    public Connection getConn(){
        return conn;
    }

    public Statement getStmt(){
        return stmt;
    }

    public ResultSet getRs(){
        return rs;
    }
}