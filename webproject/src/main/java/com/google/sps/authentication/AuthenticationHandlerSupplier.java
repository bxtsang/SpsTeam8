package com.google.sps.authentication;

public class AuthenticationHandlerSupplier {
    private static AuthenticationHandler auth;
    public static AuthenticationHandler getAuthenticationHandler() {
        if (auth == null) {
            auth = new AuthenticationHandler();
        }
        return auth;
    }
}
