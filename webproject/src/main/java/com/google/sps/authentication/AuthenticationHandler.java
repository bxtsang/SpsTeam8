package com.google.sps.authentication;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Utility class for handling user authentication.
 */
public class AuthenticationHandler {
    private final UserService userService;

    /**
     * Constructs an instance of the AuthenticationHandler class.
     */
    public AuthenticationHandler() {
        userService = UserServiceFactory.getUserService();
    }

    /**
     * Returns true if the user is logged in.
     * @return true If the user is logged in.
     */
    public boolean isUserLoggedIn() {
        return userService.isUserLoggedIn();
    }
}

