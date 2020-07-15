package com.google.sps.servlets;

import com.google.sps.authentication.AuthenticationHandler;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which manages logging into the website.
 */
public class LoginServlet extends HttpServlet {
    private final AuthenticationHandler authenticationHandler;

    /**
     * Constructs an instance of the LoginServlet class.
     */
    public LoginServlet() {
        authenticationHandler = new AuthenticationHandler();
    }

    /**
     * Called by the server to log the user into the website.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the server's request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print(authenticationHandler.getLoginUrl("/"));
    }
}

