package com.google.sps.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import org.junit.Test;

/**
 * Test class for AuthenticationHandler.
 */
public class AuthenticationHandlerTest {
    @Test
    public void isUserLoggedIn_loggedIn_returnsTrue() {
        LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig())
            .setEnvIsLoggedIn(true);
        helper.setUp();
        AuthenticationHandler authenticationHandler = new AuthenticationHandler();
        assertTrue(authenticationHandler.isUserLoggedIn());
        helper.tearDown();
    }

    @Test
    public void isUserLoggedIn_notLoggedIn_returnsFalse() {
        LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig());
        helper.setUp();
        AuthenticationHandler authenticationHandler = new AuthenticationHandler();
        assertFalse(authenticationHandler.isUserLoggedIn());
        helper.tearDown();
    }

    @Test
    public void getCurrentUser_returnsCurrentUser() {
        LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig())
            .setEnvIsLoggedIn(true).setEnvEmail("test@gmail.com").setEnvAuthDomain("test");
        helper.setUp();
        UserService userService = UserServiceFactory.getUserService();
        AuthenticationHandler authenticationHandler = new AuthenticationHandler();
        assertEquals(userService.getCurrentUser(), authenticationHandler.getCurrentUser());
        helper.tearDown();
    }
}
