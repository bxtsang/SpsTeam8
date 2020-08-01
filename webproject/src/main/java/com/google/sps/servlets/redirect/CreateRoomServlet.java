package com.google.sps.servlets.redirect;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.util.HtmlParser;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which parses the html file for create-room.
 */
public class CreateRoomServlet extends RedirectServlet {
    public String doGetAuthenticated() throws IOException {
        return HtmlParser.parseHtmlFromFile("create-room.html");
    };
}
