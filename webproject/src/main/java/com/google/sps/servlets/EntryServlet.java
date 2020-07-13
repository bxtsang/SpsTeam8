package com.google.sps.servlets;

import com.google.sps.authentication.AuthenticationHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which manages entry into website.
 */
public class EntryServlet extends HttpServlet {
    private final AuthenticationHandler authenticationHandler;

    /**
     * Constructs an instance of the EntryServlet class.
     */
    public EntryServlet() {
        authenticationHandler = new AuthenticationHandler();
    }

    /**
     * Called by the server to allow this servlet to handle a GET request upon entry into the website.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (authenticationHandler.isUserLoggedIn()) {
            // Redirect to listings page when it is finalised
            // response.sendRedirect("/listings");
        } else {
            response.sendRedirect("/landing");
        }
    }
}
