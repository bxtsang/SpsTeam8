package com.google.sps.servlets;

import com.google.sps.util.HtmlParser;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which renders the website's listings page.
 */
public class ListingsServlet extends HttpServlet {

    /**
     * Called by the server to allow this servlet to handle a GET request from the listings page.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(HtmlParser.parseHtmlFromFile("listings.html"));
    }
}

