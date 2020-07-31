package com.google.sps.authentication;

import javax.inject.Inject;

import com.google.appengine.api.users.User;
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
    @Inject
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

    /**
     * Returns a login URL based on the specified redirect URL.
     * @param urlToRedirectToAfterUserLogsIn The URL to redirect to after logging in.
     * @return A login URL based on the specified redirect URL.
     */
    public String getLoginUrl(String urlToRedirectToAfterUserLogsIn) {
        return userService.createLoginURL(urlToRedirectToAfterUserLogsIn);
    }

    /**
     * Returns a logout URL based on the specified redirect URL.
     * @param urlToRedirectToAfterUserLogsOut The URL to redirect to after logging out.
     * @return A logout URL based on the specified redirect URL.
     */
    public String getLogoutUrl(String urlToRedirectToAfterUserLogsOut) {
        return userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);
    }

    /**
     * Returns the current user, or null if the user is not logged in.
     * @return The current user.
     */
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }
}
