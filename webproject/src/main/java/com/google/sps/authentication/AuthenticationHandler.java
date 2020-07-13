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
}
