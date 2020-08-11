package com.google.sps.services.implementations;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.proto.UsernameProto.UsernameResponse;
import com.google.sps.proto.UsernameProto.UsernameRequest;
import com.google.sps.services.interfaces.UsernameService;

@Singleton
public class UsernameServiceImpl implements UsernameService {
    private AuthenticationHandler authenticationHandler;

    @Inject
    public UsernameServiceImpl(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public UsernameResponse execute(UsernameRequest usernameRequest) throws ServletException {
        String username = authenticationHandler.getCurrentUser().getNickname();

        return UsernameResponse.newBuilder()
                .setUsername(username)
                .build();
    }
}
