package com.google.sps.util;

import com.google.sps.authentication.AuthenticationHandler;

public class AuthHandlerUtil {
    private static AuthenticationHandler auth;
    public static AuthenticationHandler getAuthenticationHandler() {
        if (auth == null) {
            auth = new AuthenticationHandler();
        }
        return auth;
    }
}
