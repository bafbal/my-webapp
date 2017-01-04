package com.mywebapp.hello;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by balint on 2016.12.27..
 */
public class HtmlBackButton {

    public static void print (HttpServletResponse response) throws IOException {
        response.getWriter().print("<a href=\"index.jsp\">\n" +
                "   <input type=\"button\" value=\"Back\" />\n" +
                "   </a>");
    }
}
