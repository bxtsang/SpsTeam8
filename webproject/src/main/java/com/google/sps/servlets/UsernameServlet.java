package com.google.sps.servlets;

import com.google.sps.proto.UsernameProto.UsernameResponse;
import com.google.sps.proto.UsernameProto.UsernameRequest;
import com.google.sps.services.interfaces.UsernameService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which handles username requests.
 */
@Singleton
public class UsernameServlet extends HttpServlet {
    private UsernameService usernameService;

    /**
     * Constructs an instance of the UsernameServlet class with the specified UsernameService.
     * @param usernameService The specified UsernameService.
     */
    @Inject
    public UsernameServlet(UsernameService usernameService) {
        this.usernameService = usernameService;
    }

    /**
     * Called by the server to get the user's username.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the server's request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UsernameRequest usernameRequest = UsernameRequest.newBuilder().build();
        UsernameResponse usernameResponse = usernameService.execute(usernameRequest);

        response.getWriter().print(usernameResponse.getUsername());
    }
}
