package com.google.sps.servlets;

import com.google.sps.authentication.AuthenticationHandler;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which handles username requests.
 */
public class UsernameServlet extends HttpServlet {
    private final AuthenticationHandler authenticationHandler;

    /**
     * Constructs an instance of the UsernameServlet class.
     */
    public UsernameServlet() {
        authenticationHandler = new AuthenticationHandler();
    }

    /**
     * Called by the server to get the user's username.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the server's request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print(authenticationHandler.getCurrentUser().getNickname());
    }
}
