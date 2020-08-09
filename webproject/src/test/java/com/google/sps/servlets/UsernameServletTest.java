package com.google.sps.servlets;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import com.google.sps.services.implementations.UsernameServiceImpl;
import com.google.sps.services.interfaces.UsernameService;
import com.google.sps.authentication.AuthenticationHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for UsernameServlet.
 */
public class UsernameServletTest {
    private AuthenticationHandler authenticationHandler;
    private UsernameService usernameService;
    private UsernameServlet usernameServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter printWriter;

    @Before
    public void setUp() throws IOException {
        authenticationHandler = new AuthenticationHandler();
        usernameService = new UsernameServiceImpl(authenticationHandler);
        usernameServlet = new UsernameServlet(usernameService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void doGet_loggedIn_redirectsToLoggedInPath() throws IOException, ServletException {

        // Arrange
        LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig())
            .setEnvIsLoggedIn(true).setEnvEmail("test@mail.com").setEnvAuthDomain("test");
        helper.setUp();
        response.setContentType("text/html");

        // Act
        usernameServlet.doGet(request, response);

        // Assert
        verify(response.getWriter()).print("test@mail.com");
        helper.tearDown();
    }
}
